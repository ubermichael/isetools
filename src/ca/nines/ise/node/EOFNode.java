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
public class EOFNode extends Node {

  public String type() {
    return "#EOF";
  }

  @Override
  public String getName() {
    return "#EOF";
  }

  @Override
  public Fragment expanded() {
    Fragment f = new Fragment();
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
