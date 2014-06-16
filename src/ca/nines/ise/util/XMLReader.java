/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class XMLReader {

  private Node root;
  private DocumentBuilderFactory factory;
  private DocumentBuilder builder;
  private XPathFactory xpfactory;
  private XPath xpath;

  private void __construct() {
    factory = DocumentBuilderFactory.newInstance();
    xpfactory = XPathFactory.newInstance();
    xpath = xpfactory.newXPath();
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public XMLReader(String in) throws ParserConfigurationException, SAXException, IOException {
    __construct();
    InputStream stream = IOUtils.toInputStream(in, "UTF-8");
    root = builder.parse(stream);
  }

  public XMLReader(File in) throws ParserConfigurationException, SAXException, IOException {
    __construct();
    InputStream stream = Class.class.getResourceAsStream(in.getPath());
    root = builder.parse(stream);
  }

  public XMLReader(Node in) {
    __construct();
    root = in;
  }

  public NodeList xpathList(String xp) throws XPathExpressionException {
    XPathExpression expr = xpath.compile(xp);
    return (NodeList) expr.evaluate(root, XPathConstants.NODESET);
  }

  public NodeList xpathList(String xp, Node node) throws XPathExpressionException {
    XPathExpression expr = xpath.compile(xp);
    return (NodeList) expr.evaluate(node, XPathConstants.NODESET);
  }

  public String xpathString(String xp) throws XPathExpressionException {
    XPathExpression expr = xpath.compile(xp);
    return (String) expr.evaluate(root, XPathConstants.STRING);
  }

  public String xpathString(String xp, Node node) throws XPathExpressionException {
    XPathExpression expr = xpath.compile(xp);
    return (String) expr.evaluate(node, XPathConstants.STRING);
  }

  public boolean xpathBoolean(String xp) throws XPathExpressionException {
    XPathExpression expr = xpath.compile(xp);
    return (boolean) expr.evaluate(root, XPathConstants.BOOLEAN);
  }
  
  public boolean xpathBoolean(String xp, Node node) throws XPathExpressionException {
    XPathExpression expr = xpath.compile(xp);
    return (boolean) expr.evaluate(node, XPathConstants.BOOLEAN);
  }
  
  public String attrValue(String attrName) {
    NamedNodeMap nodeAttrs = root.getAttributes();
    Node attribute = nodeAttrs.getNamedItem(attrName);
    if (attribute == null) {
      return "";
    }
    return attribute.getNodeValue();    
  }

  public String attrValue(String attrName, Node node) {
    NamedNodeMap nodeAttrs = node.getAttributes();
    Node attribute = nodeAttrs.getNamedItem(attrName);
    if (attribute == null) {
      return "";
    }
    return attribute.getNodeValue();    
  }
}
