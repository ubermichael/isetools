/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
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
 *
 * @author michael
 */
public class Note extends Lemma {

  private final Map<String, DOM> notes;

  public static class NoteBuilder extends Lemma.LemmaBuilder implements BuilderInterface<Note> {

    private final Map<String, DOM> notes;

    private NoteBuilder() {
      super();
      notes = new HashMap<>();
    }

    public NoteBuilder addNote(String level, DOM note) {
      notes.put(level, note);
      return this;
    }

    @Override
    public Note build() {
      return new Note(lem, lineNumber, source, tln, asl, notes);
    }

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

    public NoteBuilder from(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerConfigurationException, TransformerException {
      Document doc = new XMLDriver().drive(in);
      Node n = doc.getElementsByTagName("note").item(0);
      return from(n);
    }

  }

  public static NoteBuilder builder() {
    return new NoteBuilder();
  }

  private Note(String lem, int lineNumber, String source, String tln, String asl, Map<String, DOM> notes) {
    super(lem, lineNumber, source, tln, asl);
    this.notes = new HashMap<>(notes);
  }

  public DOM getNote(String level) {
    return notes.get(level);
  }

  public String[] getNoteLevels() {
    String levels[] = notes.keySet().toArray(new String[notes.size()]);
    Arrays.sort(levels);
    return levels;
  }

  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s%n", super.toString());
    for (String lvl : getNoteLevels()) {
      formatter.format("    %s:%s%n", lvl, getNote(lvl));
    }
    return formatter.toString();
  }

}
