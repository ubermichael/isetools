/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.LocationData;
import ca.nines.ise.util.XMLDriver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Schema {

  private final String edition;
  private final String group;
  private final int lineNumber;
  private final String source;

  private final Map<String, Tag> tags;

  public static class SchemaBuilder implements BuilderInterface<Schema> {

    private String edition;
    private String group;
    private int lineNumber;
    private String source;

    private Map<String, Tag> tags;

    private SchemaBuilder() {
      tags = new HashMap<>();
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
    public void setEdition(String edition) {
      this.edition = edition;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
      this.group = group;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public void setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
      this.source = source;
    }

    public SchemaBuilder setTags(Map<String, Tag> tags) {
      this.tags = new HashMap<>(tags);
      return this;
    }

  }

  public static SchemaBuilder builder() {
    return new SchemaBuilder();
  }

  public static Schema defaultSchema() throws IOException, SAXException, ParserConfigurationException, TransformerException {
    String loc = "/resources/schemas/default.xml";
    InputStream in = Schema.class.getResourceAsStream(loc);    
    return Schema.builder().from(loc, in).build();
  }

  private Schema(String source, int lineNumber, String edition, String group, Map<String, Tag> tags) {
    this.source = source;
    this.lineNumber = lineNumber;
    this.edition = edition;
    this.group = group;
    this.tags = new HashMap<>(tags);
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
