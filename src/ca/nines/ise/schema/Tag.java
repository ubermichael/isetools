/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import ca.nines.ise.util.XMLResourceReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Tag implements Comparable<Tag> {

  // @TODO make the fields final and create a Builder.
  
  private Map<String, Attribute> attributes;
  private String depreciated;
  private String desc;
  private String empty;
  private String name;

  private String where;

  public Tag(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    XMLResourceReader xmlIn = new XMLResourceReader(in);
    name = xmlIn.xpathString("@name");
    depreciated = xmlIn.xpathString("@depreciated");
    where = xmlIn.xpathString("@where");
    empty = xmlIn.xpathString("@empty");
    desc = xmlIn.xpathString("desc/text()");

    attributes = new HashMap<>();
    for (Node n : xmlIn.xpathList("attribute")) {
      Attribute attr = new Attribute(n, xmlIn);
      attributes.put(attr.getName().toLowerCase(), attr);
    }
  }

  public Tag(Node in) throws XPathExpressionException, ParserConfigurationException {
    this(in, new XMLResourceReader(in));
  }

  public Tag(Node in, XMLResourceReader xmlIn) throws XPathExpressionException {
    name = xmlIn.xpathString("@name", in);
    depreciated = xmlIn.xpathString("@depreciated", in);
    where = xmlIn.xpathString("@where", in);
    empty = xmlIn.xpathString("@empty", in);
    desc = xmlIn.xpathString("desc/text()", in);

    attributes = new HashMap<>();
    for (Node n : xmlIn.xpathList("attribute", in)) {
      Attribute attr = new Attribute(n, xmlIn);
      attributes.put(attr.getName().toLowerCase(), attr);
    }
  }

  @Override
  public int compareTo(Tag t) {
    return this.name.toLowerCase().compareTo(t.name.toLowerCase());
  }

  public int countAttributes() {
    return attributes.size();
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

  /**
   * @return the depreciated
   */
  public String getDepreciated() {
    return depreciated;
  }

  public String getDescription() {
    if (desc.equals("")) {
      return "No description provided.";
    }
    return desc;
  }

  public String getEmpty() {
    if (empty.equals("yes") || empty.equals("optional")) {
      return empty;
    }
    return "no";
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the where
   */
  public String getWhere() {
    if (where.equals("")) {
      return "anywhere";
    }
    return where;
  }

  public boolean isDepreciated() {
    return !depreciated.equals("");
  }

  public boolean isEmpty() {
    return empty.equals("yes");
  }

  public boolean maybeEmpty() {
    return empty.equals("yes") || empty.equals("optional");
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
}
