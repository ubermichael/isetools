/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class TagNode extends Node {

  private final LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
  private String tagname;

  public TagNode() {
    this.tagname = "";
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
    return getText();
  }

  @Override
  public String unicode() {
    return getText();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(super.toString());
    Formatter formatter = new Formatter(sb);
    formatter.format(":%s(", tagname);
    Iterator i = attributes.keySet().iterator();

    while (i.hasNext()) {
      String name = (String) i.next();
      formatter.format("@%s=%s", name, attributes.get(name));
      if(i.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append(')');
    return sb.toString();
  }

  public String getAttribute(String name) {
    return (String) attributes.get(name);
  }

  public void setAttribute(String name, String value) {
    attributes.put(name, value);
  }

  public String[] attributeNames() {
    String[] names = (String[]) attributes.keySet().toArray();
    return names;
  }

}
