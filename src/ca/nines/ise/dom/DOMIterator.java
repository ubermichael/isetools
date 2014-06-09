/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.dom;

import ca.nines.ise.node.Node;
import java.util.Iterator;

/**
 *
 * @author michael
 */
public class DOMIterator implements java.util.Iterator<Node> {

  private final Iterator<Node> i;

  public DOMIterator(DOM d) {
    i = d.iterator();
  }

  @Override
  public boolean hasNext() {
    return i.hasNext();
  }

  @Override
  public Node next() {
    return (Node) i.next();
  }

  @Override
  public void remove() {
    i.remove();
  }

}
