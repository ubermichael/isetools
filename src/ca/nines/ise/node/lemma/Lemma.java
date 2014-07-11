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
  
  // @TODO write a builder Lemma.LemmaBuilder or something.
  
  private final String lem;
  private final String lineNumber;
  private final String node;

  private final String source;
  private final String tln;
  private final String xml;

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
  this.lem = "";
  this.lineNumber = "";
  this.node = "";

  this.source = "";
  this.tln = "";
  this.xml = "";
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
  public String getLineNumber() {
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
}
