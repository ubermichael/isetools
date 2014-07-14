/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.LocationData;
import ca.nines.ise.util.XMLDriver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
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
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Attribute implements Comparable<Attribute> {

  private final String source;
  private final int lineNumber;

  private final String defaultValue;
  private final String depreciated;
  private final String desc;
  private final boolean empty;
  private final String name;
  private final boolean optional;

  private List<String> options = null;
  private final boolean renumber;
  private final AttributeType type;

  public static AttributeBuilder builder() {
    return new AttributeBuilder();
  }

  public static class AttributeBuilder implements BuilderInterface<Attribute> {

    private String defaultValue;
    private String depreciated;
    private String desc;
    private boolean empty;
    private int lineNumber;
    private String name;
    private boolean optional;

    private List<String> options;
    private boolean renumber;

    private String source;
    private AttributeType type;

    private AttributeBuilder() {
      this.source = "";
      this.lineNumber = 0;

      this.name = "";
      this.type = null;
      this.desc = "";
      this.empty = false;
      this.optional = false;
      this.renumber = false;
      this.defaultValue = "";
      this.depreciated = "";
      this.options = new ArrayList<>();
    }

    public AttributeBuilder addOption(String option) {
      this.options.add(option);
      return this;
    }

    @Override
    public Attribute build() {
      return new Attribute(
              source, lineNumber, name, type, desc, empty, optional, renumber, defaultValue, depreciated, options
      );
    }

    public AttributeBuilder from(Node n) {
      NamedNodeMap map = n.getAttributes();
      Node tmp;

      LocationData loc = (LocationData) n.getUserData(LocationData.LOCATION_DATA_KEY);
      setSource(loc.getSystemId());
      setLineNumber(loc.getStartLine());

      setName(map.getNamedItem("name").getTextContent());
      setType(map.getNamedItem("type").getTextContent());

      setDesc(((Element) n).getElementsByTagName("desc").item(0).getTextContent());

      tmp = map.getNamedItem("empty");
      setEmpty(tmp != null && tmp.getTextContent().equals("yes"));

      tmp = map.getNamedItem("optional");
      setOptional(tmp != null && tmp.getTextContent().equals("yes"));

      tmp = map.getNamedItem("empty");
      setOptional(tmp != null && tmp.getTextContent().equals("yes"));

      tmp = map.getNamedItem("renumber");
      setRenumber(tmp != null && tmp.getTextContent().equals("yes"));

      tmp = map.getNamedItem("default");
      if (tmp != null) {
        setDefaultValue(tmp.getTextContent());
      }

      tmp = map.getNamedItem("depreciated");
      if (tmp != null) {
        setDepreciated(tmp.getTextContent());
      }

      NodeList list = ((Element) n).getElementsByTagName("option");
      int length = list.getLength();
      for (int i = 0; i < length; i++) {
        addOption(list.item(i).getTextContent());
      }
      return this;
    }

    public AttributeBuilder from(String str) throws SAXException, ParserConfigurationException, TransformerException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(str);
      Node n = doc.getElementsByTagName("attribute").item(0);
      return from(n);
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public AttributeBuilder setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    /**
     * @param depreciated the depreciated to set
     */
    public AttributeBuilder setDepreciated(String depreciated) {
      this.depreciated = depreciated;
      return this;
    }

    /**
     * @param desc the desc to set
     */
    public AttributeBuilder setDesc(String desc) {
      this.desc = desc;
      return this;
    }

    /**
     * @param empty the empty to set
     */
    public AttributeBuilder setEmpty(boolean empty) {
      this.empty = empty;
      return this;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public AttributeBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * @param name the name to set
     */
    public AttributeBuilder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * @param optional the optional to set
     */
    public AttributeBuilder setOptional(boolean optional) {
      this.optional = optional;
      return this;
    }

    /**
     * @param renumber the renumber to set
     */
    public AttributeBuilder setRenumber(boolean renumber) {
      this.renumber = renumber;
      return this;
    }

    /**
     * @param source the source to set
     */
    public AttributeBuilder setSource(String source) {
      this.source = source;
      return this;
    }

    /**
     * @param type the type to set
     */
    public AttributeBuilder setType(AttributeType type) {
      this.type = type;
      return this;
    }

    public AttributeBuilder setType(String type) {
      this.type = AttributeType.valueOf(type.toUpperCase());
      return this;
    }

  }

  public enum AttributeType {

    STRING,
    LIST,
    SELECT,
    NUMBER,
  }

  public Attribute(
          String source,
          int lineNumber,
          String name,
          AttributeType type,
          String desc,
          boolean empty,
          boolean optional,
          boolean renumber,
          String defaultValue,
          String depreciated,
          List<String> options) {
    this.source = source;
    this.lineNumber = lineNumber;
    this.name = name;
    this.type = type;
    this.desc = desc;
    this.empty = empty;
    this.optional = optional;
    this.renumber = renumber;
    this.defaultValue = defaultValue;
    this.depreciated = depreciated;
    this.options = new ArrayList<>(options);
  }

  @Override
  public int compareTo(Attribute a) {
    return this.name.toLowerCase().compareTo(a.name.toLowerCase());
  }

  /**
   * @return the defaultValue
   */
  public String getDefaultValue() {
    return defaultValue;
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

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * @return the type
   */
  public AttributeType getType() {
    return type;
  }

  public String getTypeName() {
    return type.name().toLowerCase();
  }

  public boolean isDepreciated() {
    return !depreciated.equals("");
  }

  /**
   * @return the empty
   */
  public boolean isEmpty() {
    return empty;
  }

  /**
   * @return the optional
   */
  public boolean isOptional() {
    return optional;
  }

  /**
   * @return the renumber
   */
  public boolean isRenumberable() {
    return type.equals("number") && renumber;
  }

  @Override
  public String toString() {
    Formatter formatter = new Formatter();

    formatter.format("%s:%s:%s(%s)%n", source, lineNumber, name, type);
    formatter.format("  %s%n", desc);
    formatter.format("  (%s:%s:%s:%s)%n", optional, depreciated, renumber, defaultValue, empty);
    if (options != null) {
      formatter.format("    %s%n", options);
    }
    return formatter.toString();
  }

}
