/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package ca.nines.ise.node.lemma;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.XMLDriver;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A single note in a collection of annotations. Notes are immutable objects.
 *
 * @author michael
 */
public class Note extends Lemma {

  /**
   * The list of notes, indexed by level (which is interpreted as a string).s
   */
  private final Map<String, DOM> notes;

  /**
   * Notes are immutable, so NoteBuilder constructs them. Use Note.builder() to get a
   * builder object.
   */
  public static class NoteBuilder extends Lemma.LemmaBuilder implements BuilderInterface<Note> {

    /**
     * The note objects, indexed by level.
     */
    private final Map<String, DOM> notes;

    /**
     * private constructor. Use Note.builder() instead.
     */
    private NoteBuilder() {
      super();
      notes = new HashMap<>();
    }

    /**
     * Add a note for this annotation.
     * 
     * @param level the level of the note
     * @param note the text of the note (ISE SGML)
     * @return NoteBuilder to enable method chaining
     */
    public NoteBuilder addNote(String level, DOM note) {
      notes.put(level, note);
      return this;
    }

    /**
     * Construct and return the note.
     * 
     * @return the note
     */
    @Override
    public Note build() {
      id = NEXT_ID.incrementAndGet();
      return new Note(lem, lineNumber, source, tln, asl, id, notes);
    }

    /**
     * Construct a note from an XML node.
     * 
     * @param in the node to construct the note from
     * @return NoteBuilder to enable method chaining
     */
    @Override
    public NoteBuilder from(Node in) {
      super.from(in);
      Element e = (Element) in;
      NodeList nl;

      nl = e.getElementsByTagName("ln");
      int length = nl.getLength();
      for (int i = 0; i < length; i++) {
        Element ln = (Element) nl.item(i);
        if (!ln.getTextContent().equals("")) {
          setAsl(ln.getTextContent());
        }
        Node tlnNode = ln.getAttributeNode("tln");
        if (tlnNode != null) {
          setTln(tlnNode.getNodeValue());
        }
      }

      setLem(e.getElementsByTagName("lem").item(0).getTextContent());

      nl = e.getElementsByTagName("level");
      length = nl.getLength();
      for (int i = 0; i < length; i++) {
        Element level = (Element) nl.item(i);
        try {
          XMLDriver xd = new XMLDriver();
          String xmlStr = xd.serialize(level);
          
          DOM dom = new DOMBuilder(xmlStr).build();
          addNote(level.getAttributeNode("n").getValue(), dom);
          
        } catch (ParserConfigurationException ex) {
          Logger.getLogger(Note.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
          Logger.getLogger(Note.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
          Logger.getLogger(Note.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
          Logger.getLogger(Note.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
          Logger.getLogger(Note.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

      return this;
    }

    /**
     * Construct a Note from a string
     * 
     * @param in the string from which to construct the note
     * @return NoteBuilder to enable method chaining
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public NoteBuilder from(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerConfigurationException, TransformerException {
      Document doc = new XMLDriver().drive(in);
      Node n = doc.getElementsByTagName("note").item(0);
      return from(n);
    }

  }

  /**
   * Create and return a NoteBuilder
   * @return NoteBuilder to enable method chaining
   */
  public static NoteBuilder builder() {
    return new NoteBuilder();
  }

  /**
   * Construct a note. Only called by a NoteBuilder
   * 
   * @param lem
   * @param lineNumber
   * @param source
   * @param tln
   * @param asl
   * @param notes 
   */
  private Note(String lem, int lineNumber, String source, String tln, String asl, long id, Map<String, DOM> notes) {
    super(lem, lineNumber, source, tln, asl, id);
    this.notes = new HashMap<>(notes);
  }

  /**
   * Get a note for a particular level.
   * 
   * @param level the level of the requested note
   * @return the DOM for the note
   */
  public DOM getNote(String level) {
    return notes.get(level);
  }

  /**
   * Get a list of the levels available for this annotation sorted
   * in lexicographic order.
   * 
   * @return a list of levels
   */
  public String[] getNoteLevels() {
    String levels[] = notes.keySet().toArray(new String[notes.size()]);
    Arrays.sort(levels);
    return levels;
  }
  
  /**
   * Check if the annotation has a note for a level
   * 
   * @param lvl the level to check
   * 
   * @return true if the annotation has a note for the level
   */
  public boolean hasNoteLevel(String lvl) {
    return notes.containsKey(lvl);
  }

  /**
   * Stringify the annotation. Mostly useful for debugging.
   * 
   * @return a stringish representation of the annotation.
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s%n", super.toString());
    for (String lvl : getNoteLevels()) {
      try {
        formatter.format("    %s:%s%n", lvl, getNote(lvl).unicode());
      } catch (IOException ex) {
        Logger.getLogger(Note.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return formatter.toString();
  }

}
