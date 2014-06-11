/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Schema {

  HashMap<String, Tag> tags;

  public Schema() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

    // @TODO this should be a hashmap.
    tags = new HashMap<>();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    InputStream in = Class.class.getResourceAsStream("/resources/schemas/default.xml");
    Document document = builder.parse(in);

    XPathFactory xpfactory = XPathFactory.newInstance();
    XPath xpath = xpfactory.newXPath();
    XPathExpression expr = xpath.compile("/schema/tags/tag");

    NodeList nl = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
    for (int i = 0; i < nl.getLength(); i++) {
      Node n = nl.item(i);
      Tag t = new Tag(n);
      tags.put(t.getName().toUpperCase(), t);
    }
  }

  public Tag getTag(String name) {
    return tags.get(name.toUpperCase());
  }

  public String[] getTagNames() {
    String[] names = tags.keySet().toArray(new String[tags.size()]);
    Arrays.sort(names);
    return names;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Iterator<Tag> i = tags.values().iterator();
    while (i.hasNext()) {
      Tag t = i.next();
      sb.append(t);
    }

    return sb.toString();
  }

}
