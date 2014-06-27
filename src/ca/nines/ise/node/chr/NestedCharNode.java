/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.Node;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class NestedCharNode extends CharNode {

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.NESTED;
  }

  @Override
  public Fragment expanded() throws IOException {
    Fragment dom = new Fragment();

    DOM inner = new Builder(innerText()).getDOM();
    Iterator<Node> iterator = inner.iterator();
    while (iterator.hasNext()) {
      Node node = iterator.next();
      dom.addAll(node.expanded());
    }

    return wrap("LIG", dom);
  }
}
