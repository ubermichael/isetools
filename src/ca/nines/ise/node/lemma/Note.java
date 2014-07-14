/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.lemma;

import ca.nines.ise.util.BuilderInterface;
import java.io.IOException;
import java.util.Arrays;
import java.util.Formatter;
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
      return new Note(lem, lineNumber, node, source, tln, xml, notes);
    }

    @Override
    public NoteBuilder from(Node in) throws ParserConfigurationException, XPathExpressionException {
      super.from(in);
      return this;
    }

    @Override
    public NoteBuilder from(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      super.from(in);
      return this;
    }

  }

  public static NoteBuilder builder() {
    return new NoteBuilder();
  }

  private Note(String lem, int lineNumber, String node, String source, String tln, String xml, Map<String, String> notes) {
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
    Formatter formatter = new Formatter();
    formatter.format("%s%n", super.toString());
    for (String lvl : getNoteLevels()) {
      formatter.format("    %s:%s%n", lvl, getNote(lvl));
    }
    return formatter.toString();
  }

}
