/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class CharNode extends Node {

  private boolean nested = false;
  private CharType type;

  @Override
  public Fragment expanded() {
    TextNode n = new TextNode();
    n.setText("(unimplemented)" + text);
    Fragment f = new Fragment();
    f.add(n);
    return f;
  }

  @Override
  public String getName() {
    return "#CHAR";
  }

  /**
   * @return the type
   */
  public CharType getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(CharType type) {
    this.type = type;
  }

  /**
   * @return the nested
   */
  public boolean isNested() {
    return nested;
  }

  /**
   * @param nested the nested to set
   */
  public void setNested(boolean nested) {
    this.nested = nested;
  }

  @Override
  public String plain() {
    return getText();
  }

  @Override
  public NodeType type() {
    return NodeType.CHAR;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    if (this.type != null) {
      sb.append(" (").append(this.type.name()).append(')');
    }
    return sb.toString();
  }

  @Override
  public String unicode() {
    return getText();
  }

  public enum CharType {

    ACCENT,
    DIGRAPH,
    LIGATURE,
    SPACE,
    TYPOGRAPHIC,
    UNICODE,
    NESTED
  };

}
