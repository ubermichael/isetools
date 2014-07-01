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

  
}
