/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Coll;
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
public class Collations extends Apparatus<Coll> {

  public Collations() {
    super();
  }
  
  public Collations(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }
  
  public Collations(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }
  
  public Collations(Node in) throws ParserConfigurationException, XPathExpressionException {
    super(in);
  }
  
  public Collations(XMLFileReader in) throws XPathExpressionException {
    super(in);
  }

  @Override
  public Coll buildLemma(XMLFileReader in, Node n) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String nodeXPath() {
    return "/collations";
  }

  @Override
  public String rootXPath() {
    return "coll";
  }

}
