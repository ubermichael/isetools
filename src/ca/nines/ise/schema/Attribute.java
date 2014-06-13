/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Attribute {

  // @TODO maybe a hashmap would be good here. Store the namednodemap
  // obj, get the values as needed and store them in the map
  // as a caching thing.
  // but maybe there's already a better caching thing.
  private final String name;
  private final String type;
  private final String optional;
  private final String depreciated;
  private final String match;
  private final String renumber;
  private final String defaultValue;
  private final String empty;
  private final String desc;

  private ArrayList<String> options = null;

  private static XPathFactory xpfactory = XPathFactory.newInstance();
  private static XPath xpath = xpfactory.newXPath();
  private static XPathExpression expr = null;

  Attribute(Node n) throws XPathExpressionException {
    NamedNodeMap nodeAttrs = n.getAttributes();
    name = namedAttrVal(nodeAttrs, "name");
    type = namedAttrVal(nodeAttrs, "type");
    optional = namedAttrVal(nodeAttrs, "optional");
    depreciated = namedAttrVal(nodeAttrs, "depreciated");
    match = namedAttrVal(nodeAttrs, "match");
    renumber = namedAttrVal(nodeAttrs, "renumber");
    defaultValue = namedAttrVal(nodeAttrs, "defaultValue");
    empty = namedAttrVal(nodeAttrs, "empty");

    XPathExpression descXPath = xpath.compile("desc/text()");
    desc = (String) descXPath.evaluate(n, XPathConstants.STRING);

    if (expr == null) {
      expr = xpath.compile("option/text()");
    }
    NodeList nl = (NodeList) expr.evaluate(n, XPathConstants.NODESET);
    if (nl.getLength() > 0) {
      options = new ArrayList<String>();
      for (int i = 0; i < nl.getLength(); i++) {
        Node o = nl.item(i);
        options.add(o.getTextContent());
      }
    }
  }

  private String namedAttrVal(NamedNodeMap nodeAttrs, String name) throws DOMException {
    Node a;
    a = nodeAttrs.getNamedItem(name);
    if (a != null) {
      return a.getNodeValue();
    }
    return null;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);

    formatter.format("  @%s(%s:%s:%s:%s:%s:%s:%s)%n", name, type, optional, depreciated, match, renumber, defaultValue, empty);
    if(options != null) {
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
  public String getOptional() {
    return optional;
  }

  /**
   * @return the depreciated
   */
  public String getDepreciated() {
    return depreciated;
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
  public String getRenumber() {
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
  public String getEmpty() {
    return empty;
  }

  /**
   * @return the options
   */
  public String[] getOptions() {
    if(options != null && options.size() > 0) {
    String[] names = options.toArray(new String[options.size()]);
    Arrays.sort(names);
    return names;
    } else {
      return new String[0];
    }
  }
  
  public String getDescription() {
    if(desc != "") {
    return desc;
    }
    return "no description provided.";
  }

}
