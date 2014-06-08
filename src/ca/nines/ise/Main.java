/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import ca.nines.ise.dom.*;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Main {

  public static void main(String[] args) {
    String in = "<work genre='cat'><t n='3'/>\n</work>";
    Builder b = new Builder(in);

    DOM dom = b.getDOM();
    System.out.println(dom);
  }

}
