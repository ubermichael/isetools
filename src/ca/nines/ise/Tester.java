/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.AbbrNode;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      AbbrNode a = new AbbrNode();
      a.setText("|y^e|");
      System.out.println(a.expanded());
      System.out.println(a.plain());
    } catch (Exception ex) {
      Message m = log.add("unknown");
      m.addNote(ex.getMessage());
    }
    System.out.println(log);
  }
}
