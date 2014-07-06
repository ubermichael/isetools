/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Note;
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
public class Annotations extends Apparatus<Note> {

  public Annotations(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public Annotations(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public Annotations(Node in) throws ParserConfigurationException, XPathExpressionException {
    super(in);
  }

  public Annotations(XMLFileReader in) throws XPathExpressionException {
    super(in);
  }

  @Override
  public Note buildLemma(XMLFileReader in, Node n) {
    return null;
    
  }

  @Override
  public String nodeXPath() {
    return "/annotations";
  }

  @Override
  public String rootXPath() {
    return "note";
  }
}
