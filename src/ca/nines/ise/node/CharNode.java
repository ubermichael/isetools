/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class CharNode extends Node {

  private boolean nested = false;

  public String type() {
    return "#CHAR";
  }

  @Override
  String getName() {
    return "#CHAR";
  }

  @Override
  Fragment expanded() {
    TextNode n = new TextNode();
    Fragment f = new Fragment();
    f.add(n);
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

}
