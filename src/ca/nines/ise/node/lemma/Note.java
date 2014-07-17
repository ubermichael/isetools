/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.lemma;

import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.XMLDriver;
import java.io.IOException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
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

  private final Map<String, String> notes;

  public static class NoteBuilder extends Lemma.LemmaBuilder implements BuilderInterface<Note> {

    private final Map<String, String> notes;

    private NoteBuilder() {
      super();
      notes = new HashMap<>();
    }

    public NoteBuilder addNote(String level, String note) {
      notes.put(level, note);
      return this;
    }

    @Override
    public Note build() {
      return new Note(lem, lineNumber, node, source, tln, asl, notes);
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
        addNote(level.getAttributeNode("n").getValue(), level.getTextContent().trim());
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

  private Note(String lem, int lineNumber, String node, String source, String tln, String asl, Map<String, String> notes) {
    super(lem, lineNumber, node, source, tln, asl);
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
    Formatter formatter = new Formatter();
    formatter.format("%s%n", super.toString());
    for (String lvl : getNoteLevels()) {
      formatter.format("    %s:%s%n", lvl, getNote(lvl));
    }
    return formatter.toString();
  }

}
