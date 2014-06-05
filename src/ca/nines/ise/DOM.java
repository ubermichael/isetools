/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import ca.nines.ise.node.Node;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class DOM extends ArrayList<Node> {

  @Override
  public String toString() {
    String s = "";
    ListIterator<Node> i = this.listIterator();

    while(i.hasNext()) {
      Node n = i.next();
      s += n + "\n";
    }

    return s;
  }

}
