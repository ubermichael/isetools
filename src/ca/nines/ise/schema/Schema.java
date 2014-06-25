/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import ca.nines.ise.util.XMLResourceReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Schema {

  private final HashMap<String, Tag> tags;
  private final String source;

  public Schema() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new File("/resources/schemas/default.xml"));
  }

  public Schema(String in) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    this(new XMLResourceReader(in));
  }

  public Schema(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new XMLResourceReader(in));
  }

  public Schema(Node in) throws XPathExpressionException, ParserConfigurationException {
    this(new XMLResourceReader(in));
  }

  public Schema(XMLResourceReader xmlIn) throws XPathExpressionException, ParserConfigurationException {
    tags = new HashMap<>();
    source = xmlIn.getSource();
    for (Node n : xmlIn.xpathList("//tag")) {
      Tag t = new Tag(n, xmlIn);
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

  public Tag[] getTags() {
    Tag[] t = tags.values().toArray(new Tag[tags.size()]);

    Arrays.sort(t);
    return t;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Tag t : getTags()) {
      sb.append(t);
    }
    return sb.toString();
  }

}
