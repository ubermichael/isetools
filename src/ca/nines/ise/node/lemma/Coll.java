/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.lemma;

import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.XMLReader;
import ca.nines.ise.util.XMLResourceReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;

/**
 *
 * @author michael
 */
public class Coll extends Lemma {

  private final String lemResp;
  private final String lemNote;
  private final Map<String, String> readings;
  private final Map<String, String> readingNotes;

  public Coll(String lem,
              String lineNumber,
              String node,
              String source,
              String tln,
              String xml,
              String lemResp,
              String lemNote,
              Map<String, String> readings,
              Map<String, String> readingNotes) {
    super(lem, lineNumber, node, source, tln, xml);
    this.lemResp = lemResp;
    this.lemNote = lemNote;
    this.readings = new HashMap<>(readings);
    this.readingNotes = new HashMap<>(readingNotes);
  }
  
  public static CollBuilder builder() {
    return new CollBuilder();
  }

  public static class CollBuilder extends Lemma.LemmaBuilder
          implements BuilderInterface<Coll> {

    private String lemNote;
    private String lemResp;
    private final Map<String, String> readingNotes;
    private final Map<String, String> readings;

    public CollBuilder() {
      super();
      lemResp = "";
      lemNote = "";
      readings = new HashMap<>();
      readingNotes = new HashMap<>();
    }

    public CollBuilder fromNode(Node in) throws ParserConfigurationException, XPathExpressionException {
      return fromXML(in, new XMLResourceReader(in));
    }

    public CollBuilder fromString(Node in) throws ParserConfigurationException, XPathExpressionException {
      return fromXML(new XMLResourceReader(in));
    }

    public CollBuilder fromXML(XMLReader xmlIn) throws XPathExpressionException {
      setSource(xmlIn.getSource());
      setLemResp(xmlIn.xpathString("lem/@resp"));
      setLem(xmlIn.xpathString("lem/text()"));
      String lnote = xmlIn.xpathString("lem/note/text()");
      if( ! lnote.equals("")) {
        setLemNote(lnote);
      }
      setTln(xmlIn.xpathString("l/@tln"));
      setLineNumber(xmlIn.xpathString("ln/text()"));
      for(Node n : xmlIn.xpathList("rdg")){
        String rdg = xmlIn.xpathString("@resp", n);
        addReading(rdg, xmlIn.xpathString("text()", n));
        String note = xmlIn.xpathString("note/text()", n);
        if( ! note.equals("")) {
          addReadingNote(rdg, note);
        }
      }
      return this;
    }

    public CollBuilder fromXML(Node in, XMLReader xmlIn) throws XPathExpressionException {
      setSource(xmlIn.getSource());
      setLemResp(xmlIn.xpathString("lem/@resp", in));
      setLem(xmlIn.xpathString("lem/text()", in));
      String lemNote = xmlIn.xpathString("lem/note//descendant::*/text()", in);
      if( ! lemNote.equals("")) {
        setLemNote(lemNote);
      }
      setTln(xmlIn.xpathString("l/@tln", in));
      setLineNumber(xmlIn.xpathString("ln/text()", in));
      for(Node n : xmlIn.xpathList("rdg", in)){
        String rdg = xmlIn.xpathString("@resp", n);
        addReading(rdg, xmlIn.xpathString("descendant::*/text()", n));
        String note = xmlIn.xpathString("note/descendant::*/text()", n);
        if( ! note.equals("")) {
          addReadingNote(rdg, note);
        }
      }
      return this;
    }

    public CollBuilder addReading(String resp, String reading) {
      readings.put(resp, reading);
      return this;
    }

    public CollBuilder addReadingNote(String resp, String readingNote) {
      readingNotes.put(resp, readingNote);
      return this;
    }

    @Override
    public Coll build() {
      return new Coll(lem, lineNumber, node, source, tln, xml, lemResp, lemNote, readings, readingNotes);
    }

    /**
     * @param lemNote the lemNote to set
     */
    public CollBuilder setLemNote(String lemNote) {
      this.lemNote = lemNote;
      return this;
    }

    /**
     * @param lemResp the lemResp to set
     */
    public CollBuilder setLemResp(String lemResp) {
      this.lemResp = lemResp;
      return this;
    }

  }

  public String getReading(String resp) {
    return readings.get(resp);
  }

  public String getReadingNote(String resp) {
    return readingNotes.get(resp);
  }

  public boolean hasReading(String resp) {
    return readings.containsKey(resp);
  }

  public boolean hasReadingNote(String resp) {
    return readingNotes.containsKey(resp);
  }

  public String[] getReadings() {
    String[] rdgs = readings.keySet().toArray(new String[readings.size()]);
    Arrays.sort(rdgs);
    return rdgs;
  }

  public String[] getReadingNotes() {
    String[] rdgs = readingNotes.keySet().toArray(new String[readingNotes.size()]);
    Arrays.sort(rdgs);
    return rdgs;
  }

  /**
   * @return the lemNote
   */
  public String getLemNote() {
    return lemNote;
  }

  /**
   * @return the lemResp
   */
  public String getLemResp() {
    return lemResp;
  }

  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s%n", super.toString());
    for (String rdg : getReadings()) {
      formatter.format("    %s:%s%n", rdg, getReading(rdg));
      if (hasReadingNote(rdg)) {
        formatter.format("    note: %s%n", getReadingNote(rdg));
      }
    }
    return formatter.toString();
  }

}
