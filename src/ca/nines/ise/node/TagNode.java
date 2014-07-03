/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
abstract public class TagNode extends Node {

  protected LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
  protected String tagname;

  public TagNode() {
    super();
    this.tagname = "";
    this.attributes = new LinkedHashMap<>();
  }

  public TagNode(Node n) {
    super(n);
  }

  public TagNode(TagNode n) {
    super(n);
    this.tagname = n.tagname;
    this.attributes = new LinkedHashMap<>(n.attributes);
  }

  /**
   *
   * @param tagname
   */
  public TagNode(String tagname) {
    this.tagname = tagname;
  }

  public String setName(String name) {
    return this.tagname = name;
  }

  @Override
  public String getName() {
    return tagname;
  }

  @Override
  public Fragment expanded() {
    Fragment f = new Fragment();
    f.add(this);
    return f;
  }

  @Override
  public String plain() {
    return "";
  }

  @Override
  public String unicode() {
    return "";
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(super.toString());
    Formatter formatter = new Formatter(sb);
    formatter.format(":%s(", tagname);
    Iterator<String> i = attributes.keySet().iterator();

    while (i.hasNext()) {
      String name = i.next();
      formatter.format("@%s=%s", name, attributes.get(name));
      if (i.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append(')');
    return sb.toString();
  }

  public void clearAttributes() {
    attributes.clear();
  }

  public String getAttribute(String name) {
    return attributes.get(name.toLowerCase());
  }

  public void setAttribute(String name, String value) {
    attributes.put(name.toLowerCase(), value);
  }

  public void deleteAttribute(String name) {
    attributes.remove(name);
  }

  public String[] getAttributeNames() {
    String[] names = attributes.keySet().toArray(new String[attributes.size()]);
    Arrays.sort(names);
    return names;
  }

}
