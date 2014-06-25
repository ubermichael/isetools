/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class LigatureCharNode extends CharNode {

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.LIGATURE;
  }

  @Override
  public Fragment expanded() {
    Fragment dom = new Fragment();

    StartNode start = new StartNode(this);
    start.setName("LIG");
    start.setAttribute("setting", text);
    dom.add(start);

    TextNode node = new TextNode(this);
    node.setText(innerText());
    dom.add(this);

    EndNode end = new EndNode(this);
    end.setName("LIG");
    dom.add(end);

    return dom;
  }

}
