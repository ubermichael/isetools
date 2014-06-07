/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import java.util.LinkedHashMap;

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
  Fragment expanded() {
    Fragment f = new Fragment();    
    f.add(this);
    return f;
  }

  @Override
  String plain() {
    return getText();
  }

  @Override
  String unicode() {
    return getText();
  }

  public String toString() {
    return super.toString() + ":" + tagname;
  }
  
  public String getAttribute(String name) {
    return (String) attributes.get(name);
  }
  
  public void setAttribute(String name, String value) {
    attributes.put(name, value);
  }
  
  public String[] attributeNames() {
    return (String[]) attributes.keySet().toArray();
  }
  
}
