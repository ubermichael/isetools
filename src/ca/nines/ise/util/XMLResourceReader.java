/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Thin wrapper around W3C Document and XPath APIs. Because they're a PITA. This
 * class targets resources stored in JARs.
 * <p>
 * Warning: This class is not appropriate for reading XML files from the file
 * system. It is intended to read files from inside JARs.
 * <p>
 * @author michael
 */
public class XMLResourceReader extends XMLReader {

  public XMLResourceReader(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public XMLResourceReader(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(XMLResourceReader.class.getResourceAsStream(in.getCanonicalPath()));
    source = in.getCanonicalPath();
  }

  public XMLResourceReader(InputStream stream) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
    super(stream);
  }

  public XMLResourceReader(Node in) throws ParserConfigurationException {
    super(in);
  }
}
