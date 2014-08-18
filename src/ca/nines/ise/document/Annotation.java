/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
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
 * Annotation is a collection of Notes.
 *
 * @author michael
 */
public class Annotation extends Apparatus<Note> {

  /**
   * AnnotationBuilder constructs Annotation objects in the Builder pattern.
   */
  public static class AnnotationBuilder extends ApparatusBuilder<Note> implements BuilderInterface<Annotation> {

    /**
     * Construct an AnnotationBuilder. Use Annotation.builder() to get an
     * AnnotationBuilder object.
     */
    private AnnotationBuilder() {
      super();
    }

    /**
     * Once the data for an Annotation has been collected construct it.
     * 
     * @return Annotation the constructed Annotation
     */
    @Override
    public Annotation build() {
      return new Annotation(source, lemmas);
    }

    /**
     * Collect annotation data from an XML Node.
     * 
     * @param in the node with the data
     * @return AnnotationBuilder to enable method chaining
     * @throws ParserConfigurationException
     * @throws XPathExpressionException 
     */
    public AnnotationBuilder from(Node in) throws ParserConfigurationException, XPathExpressionException {
      LocationData loc = (LocationData) in.getUserData(LocationData.LOCATION_DATA_KEY);
      setSource(loc.getSystemId());

      NodeList nl = ((Element) in).getElementsByTagName("note");
      int length = nl.getLength();
      for (int i = 0; i < length; i++) {
        addLemma(Note.builder().from(nl.item(i)).build());
      }
      return this;
    }

    /**
     * Collect annotation data from a string.
     * 
     * @param in the String with the data
     * @return AnnotationBuilder to enable method chaining
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public AnnotationBuilder from(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerConfigurationException, TransformerException {
      org.w3c.dom.Document doc = new XMLDriver().drive(in);
      return from(doc.getElementsByTagName("annotations").item(0));
    }

    /**
     * Collect annotation data from a file.
     * 
     * @param in the File with the data
     * @return AnnotationBuilder to enable method chaining
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public AnnotationBuilder from(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerConfigurationException, TransformerException {
      org.w3c.dom.Document doc = new XMLDriver().drive(in);
      return from(doc.getElementsByTagName("annotations").item(0));
    }
    
    /**
     * Parse Annotation data from an InputStream.
     * 
     * @param source a description of the location of the stream data
     * @param stream The ResourceStream with the data
     * @return AnnotationBuilder to enable method chaining
     * @throws ParserConfigurationException
     * @throws TransformerConfigurationException
     * @throws SAXException
     * @throws TransformerException
     * @throws IOException
     * @throws XPathExpressionException 
     */
    public AnnotationBuilder from(String source, InputStream stream) throws ParserConfigurationException, TransformerConfigurationException, SAXException, TransformerException, IOException, XPathExpressionException {
      org.w3c.dom.Document doc = new XMLDriver().drive(source, stream);
      return from(doc.getElementsByTagName("annotations").item(0));
    }

  }

  /**
   * Construct an AnnotationBuilder and return it.
   * @return AnnotationBuilder object
   */
  public static AnnotationBuilder builder() {
    return new AnnotationBuilder();
  }

  /**
   * Construct an Annotation object.
   * 
   * @param source the source of the data
   * @param lemmas the lemmas in the annotation.
   */
  private Annotation(String source, List<Note> lemmas) {
    super(source, lemmas);
  }
}
