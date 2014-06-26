/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Thin wrapper around W3C Document and XPath APIs, for reading XML from the
 * file system.. Because they're a PITA. This class targets resources stored in
 * JARs.
 * <p>
 * Warning: This class is not appropriate for reading XML files from inside
 * JARs. Use XMLResourceReader for that.
 * <p>
 * @author michael
 */
public class XMLFileReader extends XMLReader {

  public XMLFileReader(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(in);
  }

  public XMLFileReader(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super(new FileInputStream(in));
    source = in.getCanonicalPath();
  }

  public XMLFileReader(Node in) throws ParserConfigurationException {
    super(in);
  }
}
