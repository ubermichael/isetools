/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.lemma;

import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.XMLReader;
import ca.nines.ise.util.XMLResourceReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class Note extends Lemma {

  private final Map<String, String> notes;

  public static class NoteBuilder extends Lemma.LemmaBuilder implements BuilderInterface<Note> {

    private final Map<String, String> notes;

    public NoteBuilder() {
      super();
      notes = new HashMap<>();
    }
    
    public NoteBuilder addNote(String level, String note) {
      notes.put(level, note);
      return this;
    }

    @Override
    public Note build() {
      return new Note(lem, lineNumber, node, source, tln, xml, notes);
    }
    
    public NoteBuilder fromNode(Node in) throws ParserConfigurationException, XPathExpressionException {
      return fromXML(in, new XMLResourceReader(in));
    }
    
    public NoteBuilder fromString(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      return fromXML(new XMLResourceReader(in));
    }
        
    // @TODO make the xpath expressions into private static final XPathExpression constants
    // most of the parse time is in building the xpaths.  
    public NoteBuilder fromXML(XMLReader xmlIn) throws XPathExpressionException {
      setLem(xmlIn.xpathString("lem/text()"));
      setSource(xmlIn.getSource());
      setTln(xmlIn.xpathString("ln/@tln"));
      for(Node n : xmlIn.xpathList("level")) {
        addNote(xmlIn.xpathString("@n", n), xmlIn.xpathString("text()", n));
      }
      return this;      
    }
    
    public NoteBuilder fromXML(Node in, XMLReader xmlIn) throws XPathExpressionException {
      setLem(xmlIn.xpathString("lem/text()", in));
      setSource(xmlIn.getSource());
      setTln(xmlIn.xpathString("ln/@tln", in));
      for(Node n : xmlIn.xpathList("level", in)) {
        addNote(xmlIn.xpathString("@n", n), xmlIn.xpathString("text()", n));
      }
      return this;
    }
  }
  
  public static NoteBuilder builder() {
    return new NoteBuilder();
  }

  protected Note(String lem, String lineNumber, String node, String source, String tln, String xml, Map<String, String> notes) {
    super(lem, lineNumber, node, source, tln, xml);
    this.notes = new HashMap<>(notes);
  }

  public String getNote(String level) {
    return notes.get(level);
  }

  public String[] getNoteLevels() {
    String levels[] = notes.keySet().toArray(new String[notes.size()]);
    Arrays.sort(levels);
    return levels;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append("\n");
    for(String lvl : getNoteLevels()) {
      sb.append("  ").append(lvl).append(":").append(getNote(lvl)).append("\n");
    }
    return sb.toString();
  }
  
}
