/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import ca.nines.ise.node.AbstractNode;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Main {

  public static void main(String[] args) throws IOException {
    String in = "Hello-|wo|rld.";
    Tokenizer t = new Tokenizer(in);
    AbstractNode n = t.getNode();
    while(n != null) {
      System.out.println("node: " + n);
      n = t.getNode();
    }
  }

}
