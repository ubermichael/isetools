/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.EmptyNode;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class SpaceCharNode extends CharNode {

  // doesn't make sense to use wrap() here.

  @Override
  public Fragment expanded() {
    Fragment dom = new Fragment();
    EmptyNode node = new EmptyNode(this);
    node.setAttribute("setting", text);

    switch (innerText()) {
      case " ":
        node.setName("SPACE");
        node.setAttribute("t", "extra");
        node.setAttribute("n", "1");
        break;
      case "-":
        node.setName("SHY");
        break;
      case "#":
        node.setName("SPACE");
        node.setAttribute("t", "missing");
        node.setAttribute("n", "1");
        break;
      default:
        Message m = Log.getInstance().error("char.space.unknown", this);
        m.addNote("Space markup " + text + " cannot be transformed.");
    }

    dom.add(node);
    return dom;
  }

  /**
   * @return the charType
   */  @Override
  public CharNode.CharType getCharType() {
    return CharType.SPACE;
  }

}
