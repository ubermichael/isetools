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
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class TypographicCharNode extends CharNode {

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.TYPOGRAPHIC;
  }

  @Override
  public Fragment expanded() {
    Fragment dom = new Fragment();

    StartNode start = new StartNode(this);
    start.setName("TYPEFORM");
    start.setAttribute("setting", text);
    dom.add(start);

    TextNode node = new TextNode(this);
    switch (innerText()) {
      case "s":
        start.setAttribute("t", "long");
        node.setText("\u017F");
        break;
      case "r":
        start.setAttribute("t", "long");
        node.setText("\uA75B");
        break;
      case "R":
        start.setAttribute("t", "long");
        node.setText("\uA75A");
        break;
      case "w":
        start.setAttribute("t", "w");
        node.setText("vv");
        break;
      case "W":
        start.setAttribute("t", "W");
        node.setText("ww");
        break;
      default:
        node.setText("\uFFFD");
        Message m = Log.getInstance().error("char.typographic.unknown", this);
        m.addNote("Typographic character " + text + " is unknown and cannot be transformed into unicode.");
    }
    dom.add(node);

    EndNode end = new EndNode(this);
    end.setName("TYPEFORM");
    dom.add(end);
    return dom;
  }

}
