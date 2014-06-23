/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.output.TextOutput;
import java.io.File;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      DOM dom = new Builder(new File("test/resources/data/utf16le-bom.txt")).getDOM();
      TextOutput out = new TextOutput(new File("tester.txt"));
      TextOutput logOut = new TextOutput(new File("tester.log"));
      out.print(dom);
      logOut.print(log);
    } catch (Exception ex) {
      Message m = log.error("unknown");
      m.addNote(ex.getMessage());
    }
    System.out.println(log);
  }
}
