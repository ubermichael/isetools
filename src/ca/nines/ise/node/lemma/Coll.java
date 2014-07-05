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
public class Coll extends Lemma {

  public Coll(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public Coll(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public Coll(Node in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public Coll(XMLFileReader in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

}
