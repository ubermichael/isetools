/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.lemma;

import ca.nines.ise.util.XMLFileReader;
import ca.nines.ise.util.XMLReader;
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
public class Note extends Lemma {

  public Note(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public Note(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public Note(Node in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public Note(XMLFileReader in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

}
