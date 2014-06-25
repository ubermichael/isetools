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
import ca.nines.ise.node.TextNode;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class UnicodeCharNode extends CharNode {

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.UNICODE;
  }

  @Override
  public Fragment expanded() {
    Fragment dom = new Fragment();
    TextNode node = new TextNode(this);

    switch (innerText()) {
      case "c":
        node.setText("ç");
        break;
      case "C":
        node.setText("Ç");
        break;
      case "th":
        node.setText("þ");
        break;
      case "TH":
        node.setText("Þ");
        break;
      default:
        node.setText("�");
        Message m = Log.getInstance().error("char.unicode.unknown", this);
        m.addNote("Character " + text + " cannot be turned into unicode.");
    }
    dom.add(node);
    return dom;
  }

}
