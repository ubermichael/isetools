/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import ca.nines.ise.dom.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Main {

  public static void main(String[] args) {
//    String in = "<work genre='cat'><t n='3'/>\n</work>";
    File in = new File("data/sgml/withTitlePage/Oth/Oth_F1.txt");
    Builder b = null;
    try {
      b = new Builder(in);
    } catch (IOException ex) {
      System.err.print("Cannot open file '" + in + "': ");
      System.err.println(ex.getMessage());
      System.err.println("Stopped.");
      System.exit(0);
    }

    DOM dom = b.getDOM();
    System.out.println(dom);
  }

}
