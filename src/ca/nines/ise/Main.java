/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import ca.nines.ise.dom.*;
import ca.nines.ise.log.*;
import ca.nines.ise.node.*;
import java.util.Iterator;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Main {

  public static void main(String[] args)  {
    DOM d = new DOM();
    d.add(new StartNode());
    d.add(new CommentNode());
    DOMIterator i = d.getIterator();
    while(i.hasNext()) {
      Node n = i.next();
      System.out.println(n);
    }
  }

}
