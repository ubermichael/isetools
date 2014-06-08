/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.dom;

import ca.nines.ise.node.Node;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ListIterator;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class DOM extends ArrayList<Node> {

  private String source;

  public String getSource() {
    return source;
  }

  protected void setSource(String source) {
    this.source = source;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);

    ListIterator<Node> i = this.listIterator();

    while(i.hasNext()) {
      Node n = i.next();
      formatter.format("%s:%s%n", this.getSource(), n);
    }

    return sb.toString();
  }

  public DOMIterator getIterator() {
    return new DOMIterator(this);
  }

}
