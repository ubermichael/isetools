/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import ca.nines.ise.util.XMLReader;
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
  private final String match;
  private final boolean renumber;
  private final String defaultValue;
  private final boolean empty;
  private final String desc;

  private ArrayList<String> options = null;

  public Attribute(String in) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    XMLReader xmlIn = new XMLReader(in);
    name = xmlIn.attrValue("name");
    type = xmlIn.attrValue("type");
    optional = xmlIn.attrValue("optional") == "yes";
    depreciated = xmlIn.attrValue("depreciated");
    match = xmlIn.attrValue("match");
    renumber = xmlIn.attrValue("renumber") == "yes";
    defaultValue = xmlIn.attrValue("default");
    empty = xmlIn.attrValue("empty") == "yes";
    desc = xmlIn.xpathString("desc");
    for(Node n : xmlIn.xpathList("option")) {
      options.add(n.getTextContent());
    }
  }

  public Attribute(Node in) throws XPathExpressionException, ParserConfigurationException {
    this(in, new XMLReader(in));
  }

  public Attribute(Node in, XMLReader xmlIn) throws XPathExpressionException {
    name = xmlIn.attrValue("name", in);
    type = xmlIn.attrValue("type", in);
    optional = xmlIn.attrValue("optional", in) == "yes";
    depreciated = xmlIn.attrValue("depreciated", in);
    match = xmlIn.attrValue("match", in);
    renumber = xmlIn.attrValue("renumber", in) == "yes";
    defaultValue = xmlIn.attrValue("default", in);
    empty = xmlIn.attrValue("empty", in) == "yes";
    desc = xmlIn.xpathString("desc", in);
    options = new ArrayList<>();
    for(Node n : xmlIn.xpathList("option", in)) {
      options.add(n.getTextContent());
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);

    formatter.format("  @%s(%s:%s:%s:%s:%s:%s:%s)%n", name, type, optional, depreciated, match, renumber, defaultValue, empty);
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
  public String getType() {
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
    return depreciated != "";
  }

  /**
   * @return the match
   */
  public String getMatch() {
    return match;
  }

  /**
   * @return the renumber
   */
  public boolean isRenumberable() {
    return renumber;
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
    if (desc != "") {
      return desc;
    }
    return "no description provided.";
  }

  @Override
  public int compareTo(Attribute a) {
    return this.name.toLowerCase().compareTo(a.name.toLowerCase());
  }

}
