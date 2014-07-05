/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.log.Log;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.chr.CodePointCharNode;
import java.io.IOException;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      CharNode c = new CodePointCharNode();
      c.setText("{\\u151}");
      System.out.println(c.expanded());
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
      ex.printStackTrace(System.err);
    }
    System.out.println(log);
  }
}
