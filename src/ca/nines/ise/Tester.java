/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.output.TextOutput;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      DOM dom = new Builder("{c}{s}{ae}{ct}{{s}{s}h}").getDOM();
      TextOutput out = new TextOutput(System.out);
      TextOutput logOut = new TextOutput(System.out);
      out.print(dom);
      out.print("expanded:\n");
      out.print(dom.expanded());
      logOut.print(log);
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      ex.printStackTrace(System.err);
    }
    System.out.println(log);
  }
}
