/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Note;
import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.LocationData;
import ca.nines.ise.util.XMLDriver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class Annotations extends Apparatus<Note> {

  public static class AnnotationsBuilder extends ApparatusBuilder<Note> implements BuilderInterface<Annotations> {

    private AnnotationsBuilder() {
      super();
    }
    
    @Override
    public Annotations build() {
      return new Annotations(source, lemmas);
    }

    public AnnotationsBuilder from(Node in) throws ParserConfigurationException, XPathExpressionException {
      LocationData loc = (LocationData) in.getUserData(LocationData.LOCATION_DATA_KEY);
      setSource(loc.getSystemId());

      NodeList nl = ((Element) in).getElementsByTagName("note");
      int length = nl.getLength();
      for(int i = 0; i < length; i++) {
        addLemma(Note.builder().from(nl.item(i)).build());
      }
      return this;
    }

    public AnnotationsBuilder from(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerConfigurationException, TransformerException {
      org.w3c.dom.Document doc = new XMLDriver().drive(in);
      return from(doc.getElementsByTagName("annotations").item(0));      
    }

    public AnnotationsBuilder from(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerConfigurationException, TransformerException {
      org.w3c.dom.Document doc = new XMLDriver().drive(in);
      return from(doc.getElementsByTagName("annotations").item(0));      
    }
    
    public AnnotationsBuilder from(String source, InputStream stream) throws ParserConfigurationException, TransformerConfigurationException, SAXException, TransformerException, IOException, XPathExpressionException {
      org.w3c.dom.Document doc = new XMLDriver().drive(source, stream);
      return from(doc.getElementsByTagName("annotations").item(0));      
    }

  }

  public static AnnotationsBuilder builder() {
    return new AnnotationsBuilder();
  }
  
  private Annotations(String source, List<Note> lemmas) {
    super(source, lemmas);
  }
}
