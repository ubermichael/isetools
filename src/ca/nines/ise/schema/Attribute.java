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
 * An attribute as defined in a schema. Attribute is an immutable class. Use
 * AttributeBuilder to create them.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Attribute implements Comparable<Attribute> {

  /**
   * The source of the attribute.
   */
  private final String source;

  /**
   * The line number the attribute is defined on.
   */
  private final int lineNumber;

  /**
   * The attribute's default value.
   */
  private final String defaultValue;

  /**
   * A depreciation message. Should never be null: attributes that are not
   * depreciated have an empty depreciated string.
   */
  private final String depreciated;

  /**
   * A description of the attribute
   */
  private final String desc;

  /**
   * Can the attribute be empty.
   */
  private final boolean empty;

  /**
   * The name of the attribute.
   */
  private final String name;

  /**
   * Is the attribute optional.
   */
  private final boolean optional;

  /**
   * A list of valid attribute values.
   */
  private List<String> options = null;

  /**
   * Is the attribute renumberable.
   */
  private final boolean renumber;

  /**
   * The attribute's type.
   */
  private final AttributeType type;

  /**
   * Get an attribute builder. Use it to build the attribute.
   *
   * @return AttributeBuilder
   */
  public static AttributeBuilder builder() {
    return new AttributeBuilder();
  }

  /**
   * A class to construct an attribute. There are too many options for a simple
   * constructor, so use a builder.
   *
   * All the setters return the AttributeBuilder object, to enable method
   * chaining.
   */
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

    /**
     * Don't create AttributeBuilders directly. Use Attribute.builder() instead.
     */
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

    /**
     * Add an option for the attribute.
     *
     * @param option String to add as an option
     * @return AttributeBuilder
     */
    public AttributeBuilder addOption(String option) {
      this.options.add(option);
      return this;
    }

    /**
     * Construct the attribute and return it.
     *
     * @return Attribute
     */
    @Override
    public Attribute build() {
      return new Attribute(
              source, lineNumber, name, type, desc, empty, optional, renumber, defaultValue, depreciated, options
      );
    }

    /**
     * Construct an attribute from an XML Node.
     *
     * @param n Node to build the attribute
     * @return AttributeBuilder
     */
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

    /**
     * Build an attribute from an XML String.
     *
     * @param str String to build the attribute
     * @return AttributeBuilder
     * @throws SAXException if the XML is invalid
     * @throws ParserConfigurationException if Java's default parser isn't
     * configured correctly
     * @throws TransformerException if you look at it funny.
     */
    public AttributeBuilder from(String str) throws SAXException, ParserConfigurationException, TransformerException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(str);
      Node n = doc.getElementsByTagName("attribute").item(0);
      return from(n);
    }

    /**
     * Set the default value.
     *
     * @param defaultValue the defaultValue to set
     * @return AttributeValue
     */
    public AttributeBuilder setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    /**
     * Set the depreciation message.
     *
     * @param depreciated the depreciated to set
     * @return AttributeValue
     */
    public AttributeBuilder setDepreciated(String depreciated) {
      this.depreciated = depreciated;
      return this;
    }

    /**
     * Set the attribute's description
     *
     * @param desc the desc to set
     * @return AttributeValue
     */
    public AttributeBuilder setDesc(String desc) {
      this.desc = desc;
      return this;
    }

    /**
     * Set the attribute's empty status.
     *
     * @param empty the empty to set
     * @return AttributeValue
     */
    public AttributeBuilder setEmpty(boolean empty) {
      this.empty = empty;
      return this;
    }

    /**
     * Set the attribute's line number.
     *
     * @param lineNumber the lineNumber to set
     * @return AttributeValue
     */
    public AttributeBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * Set the attribute's name.
     *
     * @param name the name to set
     * @return AttributeValue
     */
    public AttributeBuilder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the attribute's optional status.
     *
     * @param optional the optional to set
     * @return AttributeValue
     */
    public AttributeBuilder setOptional(boolean optional) {
      this.optional = optional;
      return this;
    }

    /**
     * Set the attribute's renumberable status.
     *
     * @param renumber the renumber to set
     * @return AttributeValue
     */
    public AttributeBuilder setRenumber(boolean renumber) {
      this.renumber = renumber;
      return this;
    }

    /**
     * Set the attribute's source.
     *
     * @param source the source to set
     * @return AttributeValue
     */
    public AttributeBuilder setSource(String source) {
      this.source = source;
      return this;
    }

    /**
     * Set the attribute's type.
     *
     * @param type AttributeType of the attribute
     * @return AttributeValue
     */
    public AttributeBuilder setType(AttributeType type) {
      this.type = type;
      return this;
    }

    /**
     * Set type of the attribute from a string.
     *
     * @param type String of the AttributeType's name
     * @return AttributeValue
     */
    public AttributeBuilder setType(String type) {
      this.type = AttributeType.valueOf(type.toUpperCase());
      return this;
    }

  }

  /**
   * Attribute types
   */
  public enum AttributeType {

    /**
     * Attribute is a string
     */
    STRING,
    /**
     * Attribute is a comma separated list of values from the list of options
     */
    LIST,
    /**
     * Attribute is one value from the list of options
     */
    SELECT,
    /**
     * Attribute is a number.
     */
    NUMBER,
  }

  /**
   * Use AttributeBuilder to construct Attributes.
   *
   * @param source
   * @param lineNumber
   * @param name
   * @param type
   * @param desc
   * @param empty
   * @param optional
   * @param renumber
   * @param defaultValue
   * @param depreciated
   * @param options
   */
  private Attribute(
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

  /**
   * Compare attributes by comparing names case insensitively.
   *
   * @param a the attribute to compare to
   * @return the value 0 if the argument attribute's name is equal to this
   * attribute's name; a value less than 0 if this attribute's name is
   * lexicographically less than the argument attribute's name; and a value
   * greater than 0 if this attribute's name is lexicographically greater than
   * the argument attribute's name.
   */
  @Override
  public int compareTo(Attribute a) {
    return this.name.toLowerCase().compareTo(a.name.toLowerCase());
  }

  /**
   * Get the attribute's default value.
   *
   * @return the default value
   */
  public String getDefaultValue() {
    return defaultValue;
  }

  /**
   * Get the attribute's depreciation message, or the empty string if the
   * attribute is not depreciated.
   *
   * @return the depreciated message
   */
  public String getDepreciated() {
    return depreciated;
  }

  /**
   * Get the attribute's description
   *
   * @return the description
   */
  public String getDescription() {
    if (desc.equals("")) {
      return "No description provided.";
    }
    return desc;
  }

  /**
   * Get the attribute's line number.
   *
   * @return the lineNumber
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * Get the attribute's name
   *
   * @return String the name
   */
  public String getName() {
    return name;
  }

  /**
   * Get the allowable options
   *
   * @return the options as a sorted array of strings
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
   * Get the attribute's source
   *
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * Get the attribute type
   *
   * @return the type
   */
  public AttributeType getType() {
    return type;
  }

  /**
   * Get the name of the attribute type
   *
   * @return String the attribute type
   */
  public String getTypeName() {
    return type.name().toLowerCase();
  }

  public boolean isDepreciated() {
    return !depreciated.equals("");
  }

  /**
   * Return true if the attribute is allowed to be empty.
   *
   * @return the empty
   */
  public boolean isEmpty() {
    return empty;
  }

  /**
   * Return true if the attribute is optional.
   *
   * @return the optional
   */
  public boolean isOptional() {
    return optional;
  }

  /**
   * Return true if the attribute can be renumbered.
   *
   * @return the renumber
   */
  public boolean isRenumberable() {
    return type.equals("number") && renumber;
  }

  /**
   * Stringify the attribute. Only useful for development and debugging.
   *
   * @return
   */
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
