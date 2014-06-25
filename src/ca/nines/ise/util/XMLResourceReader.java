/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
public class XMLResourceReader {

  private final Node root;
  private final DocumentBuilderFactory factory;
  private final DocumentBuilder builder;
  private final XPathFactory xpfactory;
  private final XPath xpath;
  private String source;

  public XMLResourceReader(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(IOUtils.toInputStream(in, "UTF-8"));
    source = "#STRING";
  }

  public XMLResourceReader(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(XMLResourceReader.class.getResourceAsStream(in.getCanonicalPath()));
    source = in.getCanonicalPath();
  }

  public XMLResourceReader(InputStream stream) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
    factory = DocumentBuilderFactory.newInstance();
    builder = factory.newDocumentBuilder();
    xpfactory = XPathFactory.newInstance();
    xpath = xpfactory.newXPath();
    source = "#STREAM";

    Document doc = builder.parse(stream);
    XPathExpression expr = xpath.compile("/node()");
    root = (Node) expr.evaluate(doc, XPathConstants.NODE);
  }

  public XMLResourceReader(Node in) throws ParserConfigurationException {
    factory = null;
    builder = null;
    xpfactory = XPathFactory.newInstance();
    xpath = xpfactory.newXPath();
    source = "#NODE";

    root = in;
  }

  public Node[] xpathList(String xp) throws XPathExpressionException {
    return xpathList(xp, root);
  }

  public Node[] xpathList(String xp, Node node) throws XPathExpressionException {
    ArrayList<Node> nodes = new ArrayList<>();
    XPathExpression expr = xpath.compile(xp);
    NodeList nl = (NodeList) expr.evaluate(node, XPathConstants.NODESET);
    int length = nl.getLength();
    for (int i = 0; i < length; i++) {
      nodes.add(nl.item(i));
    }
    return nodes.toArray(new Node[nodes.size()]);
  }

  public Node xpathNode(String xp) throws XPathExpressionException {
    return xpathNode(xp, root);
  }

  public Node xpathNode(String xp, Node node) throws XPathExpressionException {
    XPathExpression expr = xpath.compile(xp);
    Node n = (Node) expr.evaluate(node, XPathConstants.NODE);
    return n;
  }

  public String xpathString(String xp) throws XPathExpressionException {
    return xpathString(xp, root);
  }

  public String xpathString(String xp, Node node) throws XPathExpressionException {
    XPathExpression expr = xpath.compile(xp);
    return (String) expr.evaluate(node, XPathConstants.STRING);
  }

  public String getSource() {
    return source;
  }
}
