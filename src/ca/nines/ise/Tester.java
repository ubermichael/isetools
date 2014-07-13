/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.document.Collations;
import ca.nines.ise.log.Log;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      String in = "<collations>"
              + "<coll>\n"
              + "<l tln=\"3105\"/>\n"
              + "<ln>5.4.119.1</ln>\n"
              + "<lem resp=\"Capell 1767\">[He puts down the body.] <note>substantively</note></lem>\n"
              + "<rdg resp=\"Capell 1767\">[<i>throwing down his Load.</i>]</rdg>\n"
              + "<rdg resp=\"Weil and Weil\">[<i>He lays the body on the ground</i>]</rdg>\n"
              + "<rdg resp=\"Kastan\">[<i>He drops Hotspur's body.</i>]</rdg>\n"
              + "<rdg resp=\"Q1\"><note>not in Q1</note></rdg>\n"
              + "</coll>"
              + "</collations>";
      Collations a = Collations.builder().from(in).build();
      System.out.println(a);
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    } finally {
      System.out.println(log);
    }
  }
}
