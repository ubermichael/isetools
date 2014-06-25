/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class DigraphCharNode extends CharNode {

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.DIGRAPH;
  }

  @Override
  public Fragment expanded() {
    Fragment dom = new Fragment();

    StartNode start = new StartNode(this);
    start.setName("DIGRAPH");
    start.setAttribute("setting", innerText());
    dom.add(start);

    TextNode node = new TextNode(this);
    switch (innerText()) {
      case "ae":
        node.setText("æ");
        break;
      case "AE":
        node.setText("Æ");
        break;
      case "oe":
        node.setText("œ");
        break;
      case "OE":
        node.setText("Œ");
        break;
      case "qp":
        node.setText("ȹ");
        break;
      case "db":
        node.setText("ȸ");
        break;
      default:
        node.setText("�");
        Message m = Log.getInstance().error("char.digraph.unknown", this);
        m.addNote("Character " + text + " cannot be turned into a digraph.");
    }
    dom.add(node);

    EndNode end = new EndNode(this);
    end.setName("DIGRAPH");
    dom.add(end);

    return dom;
  }
}
