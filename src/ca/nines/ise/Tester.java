/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import ca.nines.ise.schema.Schema;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michael
 */
public class Tester {
  public static void main(String[] args) {
    try {
      Schema s = new Schema();
      System.out.println(s);
    } catch (Exception ex) {
      Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
