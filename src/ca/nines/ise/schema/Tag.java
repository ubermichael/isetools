/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import ca.nines.ise.schema.Attribute.AttributeBuilder;
import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.LocationData;
import ca.nines.ise.util.XMLDriver;
import java.io.File;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Tag implements Comparable<Tag> {

  private final Map<String, Attribute> attributes;
  private final String depreciated;
  private final String desc;
  private final String empty;

  private final int lineNumber;
  private final String name;
  private final String source;

  public static class TagBuilder implements BuilderInterface<Tag> {

    private Map<String, Attribute> attributes;
    private String depreciated;
    private String desc;
    private String empty;

    private int lineNumber;
    private String name;
    private String source;

    private TagBuilder() {
      attributes = new HashMap<>();
      depreciated = "";
      desc = "";
      empty = "";
      name = "";
      lineNumber = 0;
      source = "";
    }

    public void addAttribute(Attribute attr) {
      attributes.put(attr.getName(), attr);
    }

    @Override
    public Tag build() {
      return new Tag(name, desc, empty, depreciated, attributes, source, lineNumber);
    }

    public TagBuilder from(Node n) {
      NamedNodeMap map = n.getAttributes();
      Node tmp;

      LocationData loc = (LocationData) n.getUserData(LocationData.LOCATION_DATA_KEY);
      setSource(loc.getSystemId());
      setLineNumber(loc.getStartLine());

      setName(map.getNamedItem("name").getTextContent());
      
      tmp = map.getNamedItem("empty");
      if (tmp != null) {
        setEmpty(tmp.getTextContent());
      }

      tmp = map.getNamedItem("depreciated");
      if (tmp != null) {
        setDepreciated(tmp.getTextContent());
      }

      setDesc(((Element) n).getElementsByTagName("desc").item(0).getTextContent());

      NodeList list = ((Element) n).getElementsByTagName("attribute");
      int length = list.getLength();
      for (int i = 0; i < length; i++) {
        AttributeBuilder ab = Attribute.builder();
        addAttribute(ab.from(list.item(i)).build());
      }

      return this;
    }
    
    public TagBuilder from(String str) throws SAXException, ParserConfigurationException, TransformerException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(str);
      Node n = doc.getElementsByTagName("tag").item(0);
      return from(n);
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(Map<String, Attribute> attributes) {
      this.attributes = new HashMap<>(attributes);
    }

    /**
     * @param depreciated the depreciated to set
     */
    public void setDepreciated(String depreciated) {
      this.depreciated = depreciated;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
      this.desc = desc;
    }

    /**
     * @param empty the empty to set
     */
    public void setEmpty(String empty) {
      this.empty = empty;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public void setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
      this.name = name;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
      this.source = source;
    }

  }

  public static TagBuilder builder() {
    return new TagBuilder();
  }

  private Tag(String name, String desc, String empty, String depreciated, Map<String, Attribute> attributes, String source, int lineNumber) {
    this.name = name;
    this.desc = desc;
    this.empty = empty;
    this.depreciated = depreciated;
    this.attributes = new HashMap<>(attributes);
    this.source = source;
    this.lineNumber = lineNumber;
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
   * @return the lineNumber
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
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
    Formatter formatter = new Formatter();

    formatter.format("%s:%s%n", source, lineNumber);
    formatter.format("%s:%s:%s%n", name, empty, depreciated);
    Iterator<Attribute> i = attributes.values().iterator();
    while (i.hasNext()) {
      Attribute a = i.next();
      formatter.format("%s", a);
    }

    return formatter.toString();
  }
}
