/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Coll;
import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.XMLFileReader;
import ca.nines.ise.util.XMLReader;
import ca.nines.ise.util.XMLResourceReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class Collations extends Apparatus<Coll> {

  private Collations(String source, List<Coll> lemmas) {
    super(source, lemmas);
  }
  
  public static CollationsBuilder builder() {
    return new CollationsBuilder();
  }

  public static class CollationsBuilder extends ApparatusBuilder<Coll> implements BuilderInterface<Collations> {

    private CollationsBuilder() {
    }
    
    @Override
    public Collations build() {
      return new Collations(source, lemmas);
    }

    public CollationsBuilder from(Node in) throws ParserConfigurationException, XPathExpressionException {
      return from(in, new XMLResourceReader(in));
    }

    public CollationsBuilder from(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      return from(new XMLResourceReader(in));
    }

    public CollationsBuilder from(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      return from(new XMLFileReader(in));
    }

    public CollationsBuilder from(XMLReader xmlIn) throws XPathExpressionException {
      return from(xmlIn.xpathNode("/collations"), xmlIn);
    }

    public CollationsBuilder from(Node in, XMLReader xmlIn) throws XPathExpressionException {
      for(Node n : xmlIn.xpathList("coll", in)) {
        addLemma(Coll.builder().from(n, xmlIn).build());
      }
      return this;
    }

  }

}
