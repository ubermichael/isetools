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

import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.LocationData;
import ca.nines.ise.util.XMLDriver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A schema is a collection of Tags, and a little bit of metadata to describe
 * the schema.
 *
 * Schemas are immutable. Use SchemaBuilder to create them.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Schema {
  /**
   * The edition for the schema
   */
  private final String edition;
  /**
   * The group for the schema. "ise" or "qme" or "dre"
   */
  private final String group;

  /**
   * The line number where the schema is defined.
   */
  private final int lineNumber;

  /**
   * The source where the schema is defined.
   */
  private final String source;

  /**
   * A mapping of tag names to tags.
   */
  private final Map<String, Tag> tags;

  /**
   * A class to construct a Schema. Schema construction is too complicated for a
   * constructor, so use a builder.
   *
   * All the setters return the SchemaBuilder object, to enable method chaining.
   */
  public static class SchemaBuilder implements BuilderInterface<Schema> {

    private String edition;
    private String group;
    private int lineNumber;
    private String source;

    private Map<String, Tag> tags;

    private SchemaBuilder() {
      tags = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
      edition = "";
      group = "";
      lineNumber = 0;
      source = "";
    }

    public SchemaBuilder addTag(Tag tag) {
      tags.put(tag.getName(), tag);
      return this;
    }

    @Override
    public Schema build() {
      return new Schema(source, lineNumber, edition, group, tags);
    }

    public SchemaBuilder from(Node n) {
      NamedNodeMap map = n.getAttributes();

      LocationData loc = (LocationData) n.getUserData(LocationData.LOCATION_DATA_KEY);
      setSource(loc.getSystemId());
      setLineNumber(loc.getStartLine());

      setEdition(map.getNamedItem("edition").getTextContent());
      setGroup(map.getNamedItem("group").getTextContent());

      NodeList list = ((Element) n).getElementsByTagName("tag");
      int length = list.getLength();
      for (int i = 0; i < length; i++) {
        Tag.TagBuilder tb = Tag.builder();
        addTag(tb.from(list.item(i)).build());
      }

      return this;
    }

    public SchemaBuilder from(File file) throws ParserConfigurationException, SAXException, TransformerException, IOException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(file);
      Node n = doc.getElementsByTagName("schema").item(0);
      return from(n);
    }

    public SchemaBuilder from(String str) throws TransformerException, ParserConfigurationException, TransformerConfigurationException, SAXException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(str);
      Node n = doc.getElementsByTagName("schema").item(0);
      return from(n);
    }

    public SchemaBuilder from(String source, InputStream in) throws TransformerException, ParserConfigurationException, TransformerConfigurationException, SAXException, IOException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(source, in);
      Node n = doc.getElementsByTagName("schema").item(0);
      return from(n);
    }

    /**
     * @param edition the edition to set
     */
    public SchemaBuilder setEdition(String edition) {
      this.edition = edition;
      return this;
    }

    /**
     * @param group the group to set
     */
    public SchemaBuilder setGroup(String group) {
      this.group = group;
      return this;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public SchemaBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * @param source the source to set
     */
    public SchemaBuilder setSource(String source) {
      this.source = source;
      return this;
    }

    public SchemaBuilder setTags(Map<String, Tag> tags) {
      this.tags = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
      this.tags.putAll(tags);
      return this;
    }

  }

  public static SchemaBuilder builder() {
    return new SchemaBuilder();
  }

  public static Schema defaultSchema() throws IOException, SAXException, ParserConfigurationException, TransformerException {
    String loc = "/schemas/default.xml";
    InputStream in = Schema.class.getResourceAsStream(loc);
    return Schema.builder().from(loc, in).build();
  }

  private Schema(String source, int lineNumber, String edition, String group, Map<String, Tag> tags) {
    this.source = source;
    this.lineNumber = lineNumber;
    this.edition = edition;
    this.group = group;
    this.tags = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    this.tags.putAll(tags);
  }

  /**
   * @return the edition
   */
  public String getEdition() {
    return edition;
  }

  /**
   * @return the group
   */
  public String getGroup() {
    return group;
  }

  /**
   * @return the lineNumber
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  public Tag getTag(String name) {
    return tags.get(name);
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
  

  public List<String>  get_Inline_tags() {
    List<String> list = new ArrayList<String>();
    Tag[] tags = getTags();
    for(int i=0; i<tags.length; i++){
      if (tags[i].getXMLInline().equals("yes")){
        list.add(tags[i].getName());
      }
    }
    return list;
  }
  public List<String>  get_flatten_tags() {
    List<String> list = new ArrayList<String>();
    Tag[] tags = getTags();
    for(int i=0; i<tags.length; i++){
      if (tags[i].getXMLFlatten().equals("yes")){
        list.add(tags[i].getName());
      }
    }
    return list;
  }
  public List<String>  get_typeface_tags() {
    List<String> list = new ArrayList<String>();
    Tag[] tags = getTags();
    for(int i=0; i<tags.length; i++){
      if (tags[i].getXMLTypeface().equals("yes")){
        list.add(tags[i].getName());
      }
    }
    return list;
  }
  public List<String>  get_line_parent_tags() {
    List<String> list = new ArrayList<String>();
    Tag[] tags = getTags();
    for(int i=0; i<tags.length; i++){
      if (tags[i].getXMLLineParent().equals("yes")){
        list.add(tags[i].getName());
      }
    }
    return list;
  }
  public List<String>  get_unparsed_text_tags() {
    List<String> list = new ArrayList<String>();
    Tag[] tags = getTags();
    for(int i=0; i<tags.length; i++){
      if (tags[i].getXMLUnparsedText().equals("yes")){
        list.add(tags[i].getName());
      }
    }
    return list;
  }

}
