/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author michael
 */
public class XMLDriver {

  private final DocumentBuilder docBuilder;
  private final Transformer nullTransformer;
  private final XMLReader xmlReader;

  public XMLDriver() throws ParserConfigurationException, TransformerConfigurationException, SAXException, TransformerException {
    docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    nullTransformer = TransformerFactory.newInstance().newTransformer();

    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    saxParserFactory.setNamespaceAware(true);
    SAXParser saxParser = saxParserFactory.newSAXParser();

    xmlReader = saxParser.getXMLReader();
  }

  public Document drive(File in) throws TransformerException, IOException {
    Document doc = docBuilder.newDocument();
    DOMResult domResult = new DOMResult(doc);
    LocationAnnotator locationAnnotator = new LocationAnnotator(xmlReader, doc);

    String systemId = in.getCanonicalPath();
    InputSource inputSource = new InputSource(systemId);
    SAXSource saxSource = new SAXSource(locationAnnotator, inputSource);
    nullTransformer.transform(saxSource, domResult);

    return doc;
  }
  
  public Document drive(String in) throws TransformerException {
    Document doc = docBuilder.newDocument();
    DOMResult domResult = new DOMResult(doc);
    LocationAnnotator locationAnnotator = new LocationAnnotator(xmlReader, doc);

    InputSource inputSource = new InputSource(IOUtils.toInputStream(in));
    SAXSource saxSource = new SAXSource(locationAnnotator, inputSource);
    nullTransformer.transform(saxSource, domResult);

    return doc;
  }
}
