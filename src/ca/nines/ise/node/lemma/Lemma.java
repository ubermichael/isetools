/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.lemma;

import ca.nines.ise.util.LocationData;
import java.io.IOException;
import java.util.Formatter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
abstract public class Lemma {

  private final String lem;
  private final int lineNumber;
  private final String node;
  private final String source;
  private final String tln;
  private final String xml;

  public abstract static class LemmaBuilder {

    protected String lem;
    protected int lineNumber;
    protected String node;
    protected String source;
    protected String tln;
    protected String xml;

    public LemmaBuilder() {
      lem = "";
      lineNumber = 0;
      node = "";
      source = "";
      tln = "";
      xml = "";
    }
    
    public LemmaBuilder from(Node n) throws ParserConfigurationException, XPathExpressionException {
      LocationData loc = (LocationData) n.getUserData(LocationData.LOCATION_DATA_KEY);
      setSource(loc.getSystemId());
      setLineNumber(loc.getStartLine());

      return this;
    }

    public LemmaBuilder from(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      return this;
    }

    /**
     * @param lem the lem to set
     */
    public LemmaBuilder setLem(String lem) {
      this.lem = lem;
      return this;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public LemmaBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * @param node the node to set
     */
    public LemmaBuilder setNode(String node) {
      this.node = node;
      return this;
    }

    /**
     * @param source the source to set
     */
    public LemmaBuilder setSource(String source) {
      this.source = source;
      return this;
    }

    /**
     * @param tln the tln to set
     */
    public LemmaBuilder setTln(String tln) {
      this.tln = tln;
      return this;
    }

    /**
     * @param xml the xml to set
     */
    public LemmaBuilder setXml(String xml) {
      this.xml = xml;
      return this;
    }
  
  }

  protected Lemma(String lem, int lineNumber, String node, String source, String tln, String xml) {
    this.lem = lem;
    this.lineNumber = lineNumber;
    this.node = node;
    this.source = source;
    this.tln = tln;
    this.xml = xml;
  }

  /**
   * @return the lem
   */
  public String getLem() {
    return lem;
  }

  /**
   * @return the lineNumber
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * @return the node
   */
  public String getNode() {
    return node;
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * @return the tln
   */
  public String getTln() {
    return tln;
  }

  /**
   * @return the xml
   */
  public String getXml() {
    return xml;
  }
  
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    return formatter.format("%s:%s @%s (%s)", source, lineNumber, tln, lem).toString();
  }
}
