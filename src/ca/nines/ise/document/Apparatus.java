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
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @todo should this class just extend ArrayList<T> ?
 * <p>
 * @author michael
 * @param <T>
 */
abstract public class Apparatus<T extends Lemma> extends ArrayList<T> {

  private final String source;

  public Apparatus() {
    source = "";
  }

  public Apparatus(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new XMLFileReader(in));
  }

  public Apparatus(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new XMLFileReader(in));
  }

  public Apparatus(Node in) throws ParserConfigurationException, XPathExpressionException {
    this(new XMLFileReader(in));
  }

  public Apparatus(XMLFileReader in) throws XPathExpressionException {
    source = in.getSource();
    Node root = in.xpathNode(rootXPath());
    Node nodes[] = in.xpathList(nodeXPath(), root);
    for (Node n : nodes) {
      T lemma;
      lemma = buildLemma(in, n);
      add(lemma);
    }
  }

  public abstract T buildLemma(XMLFileReader in, Node n);

  public abstract String nodeXPath();

  @Override
  public Iterator<T> iterator() {
    return super.iterator();
  }
  public abstract String rootXPath();
}
