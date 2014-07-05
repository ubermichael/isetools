/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.lemma;

import ca.nines.ise.util.XMLFileReader;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
abstract public class Lemma {

  private String source;
  private String tln;
  private String lem;
  private String lineNumber;
  private String xml;
  private String node;

  
  public Lemma(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new XMLFileReader(in));
  }

  public Lemma(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new XMLFileReader(in));
  }

  public Lemma(Node in) throws ParserConfigurationException, XPathExpressionException {
    this(new XMLFileReader(in));
  }

  public Lemma(XMLFileReader in) throws XPathExpressionException {
  }

  /**
   * @return the lem
   */
  public String getLem() {
    return lem;
  }

  /**
   * @param lem the lem to set
   */
  public void setLem(String lem) {
    this.lem = lem;
  }

  /**
   * @return the lineNumber
   */
  public String getLineNumber() {
    return lineNumber;
  }

  /**
   * @param lineNumber the lineNumber to set
   */
  public void setLineNumber(String lineNumber) {
    this.lineNumber = lineNumber;
  }

  /**
   * @return the node
   */
  public String getNode() {
    return node;
  }

  /**
   * @param node the node to set
   */
  public void setNode(String node) {
    this.node = node;
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * @param source the source to set
   */
  public void setSource(String source) {
    this.source = source;
  }

  /**
   * @return the tln
   */
  public String getTln() {
    return tln;
  }

  /**
   * @param tln the tln to set
   */
  public void setTln(String tln) {
    this.tln = tln;
  }

  /**
   * @return the xml
   */
  public String getXml() {
    return xml;
  }

  /**
   * @param xml the xml to set
   */
  public void setXml(String xml) {
    this.xml = xml;
  }

  
}
