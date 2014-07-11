/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class LigatureCharNode extends CharNode {

  @Override
  public Fragment expanded() {
    return wrap("LIG", this.innerText());
  }

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.LIGATURE;
  }

}
