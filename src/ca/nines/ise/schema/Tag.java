/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import ca.nines.ise.util.XMLReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Tag implements Comparable<Tag>{

  private String name;
  private String depreciated;
  private String where;
  private String empty;
  private String desc;

  private HashMap<String, Attribute> attributes;

  public Tag(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    XMLReader xmlIn = new XMLReader(in);
    attributes = new HashMap<>();
    name = xmlIn.attrValue("name");
    depreciated = xmlIn.attrValue("depreciated");
    where = xmlIn.attrValue("where");
    empty = xmlIn.attrValue("empty");
    desc = xmlIn.xpathString("desc");
    
    for(Node n : xmlIn.xpathList("attributes")) {
      Attribute attr = new Attribute(n, xmlIn);
      attributes.put(attr.getName().toLowerCase(), attr);
    }
  }
  
  public Tag(Node in) throws XPathExpressionException {
    this(in, new XMLReader(in));
  }
  
  public Tag(Node in, XMLReader xmlIn) throws XPathExpressionException {
    attributes = new HashMap<>();
    name = xmlIn.attrValue("name");
    depreciated = xmlIn.attrValue("depreciated", in);
    where = xmlIn.attrValue("where", in);
    empty = xmlIn.attrValue("empty", in);
    desc = xmlIn.xpathString("desc", in);
    
    for(Node n : xmlIn.xpathList("attribute", in)) {
      Attribute attr = new Attribute(n, xmlIn);
      attributes.put(attr.getName().toLowerCase(), attr);
    }
  }
  
  public Attribute getAttribute(String attrName) {
    return attributes.get(attrName.toLowerCase());
  }

  public String[] getAttributeNames() {
    String[] names = attributes.keySet().toArray(new String[attributes.size()]);
    Arrays.sort(names);
    return names;
  }

  public Attribute[] getAttributes() {

    Attribute[] a = attributes.values().toArray(new Attribute[attributes.size()]);
    Arrays.sort(a);
    return a;
  }

  public int countAttributes() {
    return attributes.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);

    formatter.format("%s:%s:%s%n", name, depreciated, where);
    Iterator<Attribute> i = attributes.values().iterator();
    while (i.hasNext()) {
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

  public boolean isEmpty() {
    return empty == "yes";
  }
  
  public boolean maybeEmpty() {
    return empty == "yes" || empty =="optional";
  }
  
  public String getEmpty() {
    return (empty == "" ? "no" : empty);
  }
  
  /**
   * @return the depreciated
   */
  public String getDepreciated() {
    return depreciated;
  }

  public boolean isDepreciated() {
    return depreciated != "";
  }

  /**
   * @return the where
   */
  public String getWhere() {
    return where;
  }

  public String getDescription() {
    if (desc != "") {
      return desc;
    }
    return "no description provided.";
  }

  @Override
  public int compareTo(Tag t) {
    return this.name.toLowerCase().compareTo(t.name.toLowerCase());
  }
}
