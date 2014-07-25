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
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class TagNode extends Node {

  protected Map<String, String> attributes = new LinkedHashMap<>();
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

  public void clearAttributes() {
    attributes.clear();
  }

  public void deleteAttribute(String name) {
    attributes.remove(name);
  }

  @Override
  public Fragment expanded() {
    Fragment f = new Fragment();
    f.add(this);
    return f;
  }

  public String getAttribute(String name) {
    return attributes.get(name.toLowerCase());
  }

  public boolean hasAttribute(String name) {
    return attributes.containsKey(name.toLowerCase());
  }
  
  public String[] getAttributeNames() {
    String[] names = attributes.keySet().toArray(new String[attributes.size()]);
    Arrays.sort(names);
    return names;
  }

  @Override
  public String getName() {
    return tagname;
  }

  @Override
  public String plain() {
    return "";
  }
  
  @Override
  public String sgml() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("<").append(getName());
    for(String name : getAttributeNames()) {
      sb.append(" ").append(name).append('=').append('"').append(getAttribute(name)).append('"');
    }
    if(this instanceof EmptyNode) {
      sb.append(" /");
    }
    sb.append(">");
    return sb.toString();
  }

  public void setAttribute(String name, String value) {
    attributes.put(name.toLowerCase(), value);
  }

  public String setName(String name) {
    return this.tagname = name;
  }

  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s", super.toString());
    formatter.format(":%s(", tagname);
    for(String name : attributes.keySet()) {
      formatter.format("@%s=%s ", name, attributes.get(name));
    }
    formatter.format(")");
    return formatter.toString();
  }

  @Override
  public String unicode() {
    return "";
  }

}
