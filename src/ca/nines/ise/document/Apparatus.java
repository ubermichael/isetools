/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Lemma;
import ca.nines.ise.util.XMLFileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 * @param <T>
 */
abstract public class Apparatus<T extends Lemma> {

  private final ArrayList<T> lemmas;
  private final String source;
  
  public Apparatus() {
    lemmas = new ArrayList<>();
    source = "";
  }
  
  public Apparatus(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new XMLFileReader(in));
  }

  public Apparatus(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new XMLFileReader(in));
  }
  
  public Apparatus(Node in) throws ParserConfigurationException {
    this(new XMLFileReader(in));
  }
  
  public Apparatus(XMLFileReader in) {
    lemmas = new ArrayList<>();
    source = in.getSource();    
  }
  
  public abstract String rootXPath();
  
  public abstract String nodeXpath();
}
