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
public class EOFNode extends Node {

  public String type() {
    return "#EOF";
  }

  @Override
  String getName() {
    return "#EOF";
  }

  @Override
  Fragment expanded() {
    Fragment f = new Fragment();
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

}
