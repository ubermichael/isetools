/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import ca.nines.ise.util.XMLResourceReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Attribute implements Comparable<Attribute> {

  private final String name;
  private final String type;
  private final boolean optional;
  private final String depreciated;
  private final boolean renumber;
  private final String defaultValue;
  private final boolean empty;
  private final String desc;

  private ArrayList<String> options = null;

  public Attribute(String in) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    XMLResourceReader xmlIn = new XMLResourceReader(in);
    name = xmlIn.xpathString("@name");
    type = xmlIn.xpathString("@type");
    optional = "yes".equals(xmlIn.xpathString("@optional"));
    depreciated = xmlIn.xpathString("@depreciated");
    renumber = "yes".equals(xmlIn.xpathString("@renumber"));
    defaultValue = xmlIn.xpathString("@default");
    empty = "yes".equals(xmlIn.xpathString("@empty"));
    desc = xmlIn.xpathString("desc/text()");
    options = new ArrayList<>();
    for (Node n : xmlIn.xpathList("option")) {
      options.add(n.getTextContent());
    }
  }

  public Attribute(Node in) throws XPathExpressionException, ParserConfigurationException {
    this(in, new XMLResourceReader(in));
  }

  public Attribute(Node in, XMLResourceReader xmlIn) throws XPathExpressionException {
    name = xmlIn.xpathString("@name", in);
    type = xmlIn.xpathString("@type", in);
    optional = "yes".equals(xmlIn.xpathString("@optional", in));
    depreciated = xmlIn.xpathString("@depreciated", in);
    renumber = "yes".equals(xmlIn.xpathString("@renumber", in));
    defaultValue = xmlIn.xpathString("@default", in);
    empty = "yes".equals(xmlIn.xpathString("@empty", in));
    desc = xmlIn.xpathString("desc/text()", in);
    options = new ArrayList<>();
    for (Node n : xmlIn.xpathList("option", in)) {
      options.add(n.getTextContent());
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);

    formatter.format("  @%s(%s:%s:%s:%s:%s:%s)%n", name, type, optional, depreciated, renumber, defaultValue, empty);
    if (options != null) {
      sb.append("    ").append(options).append("\n");
    }

    return sb.toString();
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the type
   */
  public AttributeType getType() {
    switch(type) {
      case "list": return AttributeType.LIST;
      case "number": return AttributeType.NUMBER;
      case "select": return AttributeType.SELECT;
      case "string": return AttributeType.STRING;
      default: return null;
    }
  }
  
  public String getTypeName() {
    return type;
  }

  /**
   * @return the optional
   */
  public boolean isOptional() {
    return optional;
  }

  /**
   * @return the depreciated
   */
  public String getDepreciated() {
    return depreciated;
  }

  public boolean isDepreciated() {
    return !depreciated.equals("");
  }

  /**
   * @return the renumber
   */
  public boolean isRenumberable() {
    return type.equals("number") && renumber;
  }

  /**
   * @return the defaultValue
   */
  public String getDefaultValue() {
    return defaultValue;
  }

  /**
   * @return the empty
   */
  public boolean isEmpty() {
    return empty;
  }

  /**
   * @return the options
   */
  public String[] getOptions() {
    if (options != null && options.size() > 0) {
      String[] names = options.toArray(new String[options.size()]);
      Arrays.sort(names);
      return names;
    } else {
      return new String[0];
    }
  }

  public String getDescription() {
    if (desc.equals("")) {
      return "No description provided.";
    }
    return desc;
  }

  @Override
  public int compareTo(Attribute a) {
    return this.name.toLowerCase().compareTo(a.name.toLowerCase());
  }

  public enum AttributeType {
    STRING,
    LIST,
    SELECT,
    NUMBER,
  }
  
}
