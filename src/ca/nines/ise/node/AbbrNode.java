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
public class AbbrNode extends Node {

  @Override
  public String type() {
    return "#ABBR";
  }

  @Override
  public String getName() {
    return "#ABBR";
  }

  @Override
  public Fragment expanded() {
    TextNode n = new TextNode();
    Fragment f = new Fragment();
    f.add(n);
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

}
