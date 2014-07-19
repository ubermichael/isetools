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
package ca.nines.ise.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

  public Document drive(String source, InputStream in) throws TransformerException, IOException {
    Document doc = docBuilder.newDocument();
    DOMResult domResult = new DOMResult(doc);
    LocationAnnotator locationAnnotator = new LocationAnnotator(source, xmlReader, doc);

    InputSource inputSource = new InputSource(in);
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

  public String serialize(Element element) throws TransformerConfigurationException, TransformerException, UnsupportedEncodingException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    DOMSource source = new DOMSource(element);
    StreamResult stream = new StreamResult(out);
    transformer.transform(source, stream);
    return new String(out.toByteArray(), "UTF-8");
  }
}
