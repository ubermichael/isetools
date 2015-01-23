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
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import java.util.Arrays;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Tag node.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class TagNode extends Node {

  /**
   * Name,value pairs for attributes.
   */
  protected Map<String, String> attributes = new LinkedHashMap<>();
  
  /**
   * Name of the tag.
   */
  protected String tagname;

  /**
   * {@inheritDoc}
   */
  public TagNode() {
    super();
    this.tagname = "";
    this.attributes = new LinkedHashMap<>();
  }

  /**
   * {@inheritDoc}
   */
  public TagNode(Node n) {
    super(n);
  }

  /**
   * {@inheritDoc}
   */
  public TagNode(TagNode n) {
    super(n);
    this.tagname = n.tagname;
    this.attributes = new LinkedHashMap<>(n.attributes);
  }

  /**
   * {@inheritDoc}
   */
  public TagNode(String tagname) {
    this.tagname = tagname;
  }

  /**
   * Remove all the attributes on a tag.
   */
  public void clearAttributes() {
    if (attributes.containsKey("n") && ownerDom != null) {
      ownerDom.requestReindex();
    }
    attributes.clear();
  }

  /**
   * Delete one attribute.
   * @param name 
   */
  public void deleteAttribute(String name) {
    if (attributes.containsKey("n")) {
      ownerDom.requestReindex();
    }
    attributes.remove(name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Fragment expanded() {
    Fragment f = new Fragment();
    f.add(this);
    return f;
  }

  /**
   * Get an attribute value. Attribute names are case insensitive.
   * @param name
   * @return String
   */
  public String getAttribute(String name) {
    return attributes.get(name.toLowerCase());
  }

  /**
   * Check if a node has an attribute. Attribute names are case insensitive.
   * 
   * @param name
   * @return boolean
   */  
  public boolean hasAttribute(String name) {
    return attributes.containsKey(name.toLowerCase());
  }

  /**
   * Return a list of attribute names, in their original cases.
   * 
   * @return 
   */
  public String[] getAttributeNames() {
    String[] names = attributes.keySet().toArray(new String[attributes.size()]);
    Arrays.sort(names);
    return names;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return tagname;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String plain() {
    return "";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String sgml() {
    StringBuilder sb = new StringBuilder();

    sb.append("<").append(getName());
    for (String name : getAttributeNames()) {
      sb.append(" ").append(name).append('=').append('"').append(getAttribute(name)).append('"');
    }
    if (this instanceof EmptyNode) {
      sb.append(" /");
    }
    sb.append(">");
    return sb.toString();
  }

  /**
   * Set an attribute value. May cause the owner DOM to be reindexed.
   * @param name
   * @param value 
   */
  public void setAttribute(String name, String value) {
    if (name.equals("n") && ownerDom != null) {
      ownerDom.requestReindex();
    }
    attributes.put(name.toLowerCase(), value);
  }

  /**
   * Set the tag name. May cause the owner DOM to be reindexed.
   * @param name
   * @return String the name.
   */
  public String setName(String name) {
    switch (name) {
      case "ACT":
      case "SCENE":
      case "L":
      case "TLN":
        ownerDom.requestReindex();
        break;
    }
    return this.tagname = name;
  }

  /**
   * Return a human friendly string representation.
   * @return String
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s", super.toString());
    formatter.format(":%s(", tagname);
    for (String name : attributes.keySet()) {
      formatter.format("@%s=%s ", name, attributes.get(name));
    }
    formatter.format(")");
    return formatter.toString();
  }

  /**
   * Tag nodes don't have a unicode equivalent.
   * @return empty string
   */
  @Override
  public String unicode() {
    return "";
  }

}
