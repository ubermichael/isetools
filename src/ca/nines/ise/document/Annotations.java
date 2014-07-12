/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Note;
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
public class Annotations extends Apparatus<Note> {
  
  private Annotations(String source, List<Note> lemmas) {
    super(source, lemmas);
  }

  public static class AnnotationsBuilder extends ApparatusBuilder<Note> implements BuilderInterface<Annotations> {

    private AnnotationsBuilder() {
      
    }
    
    @Override
    public Annotations build() {
      return new Annotations(source, lemmas);
    }

    public AnnotationsBuilder fromNode(Node in) throws ParserConfigurationException, XPathExpressionException {
      return fromXML(in, new XMLResourceReader(in));
    }

    public AnnotationsBuilder fromString(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      return fromXML(new XMLResourceReader(in));
    }

    public AnnotationsBuilder fromFile(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      return fromXML(new XMLFileReader(in));
    }

    public AnnotationsBuilder fromXML(XMLReader xmlIn) throws XPathExpressionException {
      return fromXML(xmlIn.xpathNode("/annotations"), xmlIn);
    }

    public AnnotationsBuilder fromXML(Node in, XMLReader xmlIn) throws XPathExpressionException {
      for (Node n : xmlIn.xpathList("note", in)) {
        addLemma(Note.builder().fromXML(n, xmlIn).build());
      }
      return this;
    }

  }

  public static AnnotationsBuilder builder() {
    return new AnnotationsBuilder();
  }
}
