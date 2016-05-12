/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package ca.nines.ise.schema;

import ca.nines.ise.schema.Attribute.AttributeBuilder;
import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.LocationData;
import ca.nines.ise.util.XMLDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A tag as defined in a schema. Tags are immutable, use TagBuilder to create
 * them.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Tag implements Comparable<Tag> {

  /**
   * A mapping of attribute names and attribute objects.
   */
  private final Map<String, Attribute> attributes;

  /**
   * A depreciation message, or the empty string if the the tag isn't
   * depreciated.
   */
  private final String depreciated;

  /**
   * The tag description.
   */
  private final String desc;

  /**
   * Can the tag be empty: yes, no, or optional.
   */
  private final String empty;

  /**
   * The line number where the tag is defined.
   */
  private final int lineNumber;

  /**
   * The name of the tag.
   */
  private final String name;

  /**
   * The source of the tag.
   */
  private final String source;
  
  /**
   * Where the tag is valid.
   */
  private final String where;
  
  /**
   * Tags this tag cannot split
   */
  private final List<String> noSplit;
  
  /**
   * Tag this tag should be a child of
   */
  private final String ancestor;
  
  /**
   * Whether or not nesting this tag is redundant
   * yes/no
   */
  private final String redundant;
  
  /**
   * Whether or not this tag may span multiple lines
   * yes/no
   */
  private final String spansLines;

  /**
   * A builder class for constructing tags. Use Tag#builder() to get a
   * TagBuilder object and construct a tag from it.
   */
  public static class TagBuilder implements BuilderInterface<Tag> {

    private Map<String, Attribute> attributes;
    private String depreciated;
    private String desc;
    private String empty;
    private String where;
    private List<String> noSplit;
    private String ancestor;
    private String redundant;
    private String spansLines;

    private int lineNumber;
    private String name;
    private String source;
    
    /**
     * Don't create TagBuilders directly. Use Tag#builder().
     *
     * All the setters return the TagBuilder object, to enable method chaining.
     */
    private TagBuilder() {
      attributes = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
      depreciated = "";
      desc = "";
      empty = "";
      where = "";
      noSplit = null;
      ancestor = "";
      redundant = "";
      spansLines = "";
      name = "";
      lineNumber = 0;
      source = "";
    }

    /**
     * Add an attribute.
     *
     * @param attr Attribute to add
     * @return TagBuilder
     */
    public TagBuilder addAttribute(Attribute attr) {
      attributes.put(attr.getName(), attr);
      return this;
    }

    /**
     * Build a Tag and return it.
     *
     * @return Tag
     */
    @Override
    public Tag build() {
      return new Tag(name, desc, empty, where, depreciated, attributes, source, lineNumber, noSplit, ancestor, redundant, spansLines);
    }

    /**
     * Construct a tag from an XML Node.
     *
     * @param n Node to build the tag from
     * @return TagBuilder
     */
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

      tmp = map.getNamedItem("where");
      if (tmp != null) {
        setWhere(tmp.getTextContent());
      }
      
      tmp = map.getNamedItem("noSplit");
      if (tmp != null) {
        setNoSplit(tmp.getTextContent());
      }
      
      tmp = map.getNamedItem("ancestor");
      if (tmp != null) {
        setAncestor(tmp.getTextContent());
      }
      
      tmp = map.getNamedItem("redundant");
      if (tmp != null) {
        setRedundant(tmp.getTextContent());
      }
      
      tmp = map.getNamedItem("spansLines");
      if (tmp !=  null){
        setSpansLines(tmp.getTextContent());
      }

      tmp = map.getNamedItem("depreciated");
      if (tmp != null) {
        setDepreciated(tmp.getTextContent());
      }

      tmp = ((Element) n).getElementsByTagName("desc").item(0);
      if (tmp != null) {
        setDesc(tmp.getTextContent());
      }

      NodeList list = ((Element) n).getElementsByTagName("attribute");
      int length = list.getLength();
      for (int i = 0; i < length; i++) {
        AttributeBuilder ab = Attribute.builder();
        addAttribute(ab.from(list.item(i)).build());
      }

      return this;
    }

    /**
     * Construct a tag from an XML String.
     *
     * @param str
     * @return TagBuilder
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public TagBuilder from(String str) throws SAXException, ParserConfigurationException, TransformerException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(str);
      Node n = doc.getElementsByTagName("tag").item(0);
      return from(n);
    }

    /**
     * Set the attributes for the tag.
     *
     * @param attributes the attributes to set
     * @return TagBuilder
     */
    public TagBuilder setAttributes(Map<String, Attribute> attributes) {
      this.attributes = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
      this.attributes.putAll(attributes);
      return this;
    }

    /**
     * Set the depreciation message.
     *
     * @param depreciated the depreciated to set
     * @return TagBuilder
     */
    public TagBuilder setDepreciated(String depreciated) {
      this.depreciated = depreciated;
      return this;
    }

    /**
     * Set the tag's description.
     *
     * @param desc the desc to set
     * @return TagBuilder
     */
    public TagBuilder setDesc(String desc) {
      this.desc = desc;
      return this;
    }

    /**
     * Set the tag's empty status.
     *
     * @param empty the empty to set
     * @return TagBuilder
     */
    public TagBuilder setEmpty(String empty) {
      this.empty = empty;
      return this;
    }
    
    /**
     * Set where the tag should be.
     * 
     * @param where the where to set
     * @return
     */
    public TagBuilder setWhere(String where) {
      this.where = where;
      return this;
    }
    
    /**
     * Set the tags this tag should not split
     * 
     * @param noSplit comma delimited list of tags not to split
     * @return
     */
    public TagBuilder setNoSplit(String noSplit) {
      this.noSplit = Arrays.asList(noSplit.split(",[ ]*"));
      return this;
    }
    
    /**
     * Ancestor of this tag
     * 
     * @param ancestor the ancestor to set
     * @return
     */
    public TagBuilder setAncestor(String ancestor) {
      this.ancestor = ancestor;
      return this;
    }
    
    /**
     * Sets whether or not this tag is redundant
     * 
     * @param redundant the redundancy to set
     * @return
     */
    public TagBuilder setRedundant(String redundant) {
      this.redundant = redundant;
      return this;
    }
    
    /**
     * Sets whether or not this tag may span lines
     */
    public TagBuilder setSpansLines(String spansLines) {
      this.spansLines = spansLines;
      return this;
    }

    /**
     * Set the tag's line number
     *
     * @param lineNumber the lineNumber to set
     * @return TagBuilder
     */
    public TagBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * Set the name of the tag.
     *
     * @param name the name to set
     * @return TagBuilder
     */
    public TagBuilder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the source of the tag.
     *
     * @param source the source to set
     * @return TagBuilder
     */
    public TagBuilder setSource(String source) {
      this.source = source;
      return this;
    }

  }

  /**
   * Return TagBuilder instance.
   *
   * @return TagBuilder
   */
  public static TagBuilder builder() {
    return new TagBuilder();
  }

  /**
   * Use TagBuilder to construct Tags.
   *
   * @param name
   * @param desc
   * @param empty
   * @param depreciated
   * @param attributes
   * @param source
   * @param lineNumber
   */
  private Tag(String name, String desc, String empty, String where, String depreciated, Map<String, Attribute> attributes, String source, int lineNumber, List noSplit, String ancestor, String redundant, String spansLines) {
    this.name = name;
    this.desc = desc;
    this.where = where;
    this.noSplit = noSplit;
    this.ancestor = ancestor;
    this.redundant = redundant;
    this.spansLines = spansLines;
    this.empty = empty;
    this.depreciated = depreciated;
    this.attributes = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    this.attributes.putAll(attributes);
    this.source = source;
    this.lineNumber = lineNumber;
  }

  /**
   * Compare tags by comparing tag names case insensitively.
   *
   * @param t the tag to compare to.
   * @return the value 0 if the argument tag's name is equal to this tag's name;
   * a value less than 0 if this tag's name is lexicographically less than the
   * argument tag's name; and a value greater than 0 if this tag's name is
   * lexicographically greater than the argument tag's name.
   */
  @Override
  public int compareTo(Tag t) {
    return this.name.toLowerCase().compareTo(t.name.toLowerCase());
  }

  /**
   * return the number of attributes in the tag.
   *
   * @return a count of the attributes
   */
  public int countAttributes() {
    return attributes.size();
  }

  /**
   * Fetch an attribute
   *
   * @param attrName the name of the attribute to fetch
   * @return the Attribute object or null if it doesn't exist.
   */
  public Attribute getAttribute(String attrName) {
    return attributes.get(attrName);
  }

  /**
   * Get a sorted list of attribute names.
   *
   * @return String[]
   */
  public String[] getAttributeNames() {
    String[] names = attributes.keySet().toArray(new String[attributes.size()]);
    Arrays.sort(names);
    return names;
  }

  /**
   * Get a copy of all the attributes for the tag.
   *
   * @return Array of Attributes, possibly empty.
   */
  public Attribute[] getAttributes() {
    Attribute[] a = attributes.values().toArray(new Attribute[attributes.size()]);
    Arrays.sort(a);
    return a;
  }

  /**
   * Get the deprecation message.
   *
   * @return the depreciated message
   */
  public String getDepreciated() {
    return depreciated;
  }

  /**
   * Get the description
   *
   * @return string description, or "No description provided."
   */
  public String getDescription() {
    if (desc.equals("")) {
      return "No description provided.";
    }
    return desc;
  }

  /**
   * Get the empty status of the tag. Will be one of yes, no, or optional.
   *
   * @return String
   */
  public String getEmpty() {
    if (empty.equals("yes") || empty.equals("optional")) {
      return empty;
    }
    return "no";
  }
  
  /**
   * returns where this tag should be in a document.
   * @return
   */
  public String getWhere() {
      if(where.equals("")) {
          return "anywhere";
      }
      return where;
  }

  /**
   * Returns a list of tags that this tag may not split.
   * @return
   */
  public List getNoSplit() {
    return noSplit;
  }
  
  /**
   * Returns this tag's mandatory ancestor.
   * @return
   */
  public String getAncestor() {
    return ancestor;
  }
  
  /**
   * Returns the redundant field of this tag
   * @return
   */
  public String getRedundant() {
    return redundant;
  }
  
  /**
   * returns the spansLines field of this tag
   * @return
   */
  public String getSpansLines() {
    return spansLines;
  }
  
  /**
   * Get the line number where the tag is defined.
   *
   * @return the lineNumber
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * Get the name of the tag.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Get the source of the tag.
   *
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * Returns true if the tag contains the named attribute.
   *
   * @param name
   * @return boolean
   */
  public boolean hasAttribute(String name) {
    return attributes.containsKey(name);
  }

  /**
   * True if the tag is depreciated.
   *
   * @return boolean
   */
  public boolean isDepreciated() {
    return !depreciated.equals("");
  }

  /**
   * Returns true if the tag MUST be empty.
   *
   * @return boolean
   */
  public boolean isEmpty() {
    return empty.equals("yes");
  }

  /**
   * Return true if the tag must be empty, or if it may be empty.
   *
   * @return boolean
   */
  public boolean maybeEmpty() {
    return empty.equals("yes") || empty.equals("optional");
  }

  /**
   * Turn the tag into a string. Useful only for debugging.
   *
   * @return String
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();

    formatter.format("%s:%s%n", source, lineNumber);
    formatter.format("%s:%s:%s%n", name, empty, depreciated);
    for (Attribute a : attributes.values()) {
      formatter.format("%s", a);
    }

    return formatter.toString();
  }

}
