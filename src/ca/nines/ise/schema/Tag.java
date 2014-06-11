/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Tag {

  private String name;
  private String depreciated;
  private String where;
  private String empty;

  private HashMap<String, Attribute> attributes;

  private static XPathFactory xpfactory = XPathFactory.newInstance();
  private static XPath xpath = xpfactory.newXPath();
  private static XPathExpression expr = null;

  Tag(Node n) throws XPathExpressionException {
    attributes = new HashMap<>();

    NamedNodeMap nodeAttrs = n.getAttributes();
    Node a;

    a = nodeAttrs.getNamedItem("name");
    if (a != null) {
      name = a.getNodeValue();
    }

    a = nodeAttrs.getNamedItem("depreciated");
    if (a != null) {
      depreciated = a.getNodeValue();
    }

    a = nodeAttrs.getNamedItem("where");
    if(a != null) {
      where = a.getNodeValue();
    }

    a = nodeAttrs.getNamedItem("empty");
    if(a != null) {
      empty = a.getNodeValue();
    }

    if(expr == null) {
      expr = xpath.compile("attribute");
    }

    NodeList nl = (NodeList) expr.evaluate(n, XPathConstants.NODESET);
    for(int i = 0; i < nl.getLength(); i++) {
      Attribute attribute = new Attribute(nl.item(i));
      attributes.put(attribute.getName().toLowerCase(), attribute);
    }
  }

  public Attribute getAttribute(String name) {
    return attributes.get(name.toLowerCase());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);

    formatter.format("%s:%s:%s%n", name, depreciated, where);
    Iterator<Attribute> i = attributes.values().iterator();
    while(i.hasNext()) {
      Attribute a = i.next();
      sb.append(a);
    }

    return sb.toString();
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  public boolean isDepreciated() {
    return depreciated != null;
  }
  
  public String getEmpty() {
    return empty;
  }

  /**
   * @return the depreciated
   */
  public String getDepreciated() {
    return depreciated;
  }

  /**
   * @return the where
   */
  public String getWhere() {
    return where;
  }

}
