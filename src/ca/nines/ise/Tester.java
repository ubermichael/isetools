/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.document.Annotations;
import ca.nines.ise.log.Log;
import java.io.File;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      String in = data();
      File f = new File("input/withTitlePage/Oth/apparatus/Oth_M_annotation.xml");
      System.out.println("parsing.");
      Annotations a = Annotations.builder().fromFile(f).build();
      System.out.println("done.");
      System.out.println(a);
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    } finally {
      System.out.println(log);
    }
  }

  public static String data() {
    return "<annotations>\n"
            + "<!--Othello, commentary by Jessica Slights. Revised 2013-06-27-->\n"
            + "\n"
            + "<note>\n"
            + "<ln tln=\"2\"/>\n"
            + "<lem>Enter Roderigo and Iago.</lem>\n"
            + "<level n=\"2\">\n"
            + "The Q texts list Iago's name before Roderigo's. Playing the entrances in this order could operate as an early sign of the dominance that Iago has over Roderigo throughout the play.\n"
            + "</level>\n"
            + "</note>\n"
            + "\n"
            + "<note>\n"
            + "<ln tln=\"4\"/>\n"
            + "<lem>Tush,</lem>\n"
            + "<level n=\"1\">\n"
            + "Exclamation of impatience.\n"
            + "</level>\n"
            + "</note>\n"
            + "\n"
            + "<note>\n"
            + "<ln tln=\"6\"/>\n"
            + "<lem>this.</lem>\n"
            + "<level n=\"1\">\n"
            + "I.e. Othello's marriage to Desdemona.\n"
            + "</level>\n"
            + "<level n=\"2\">\n"
            + "The referent seems deliberately unclear, and it becomes obvious only later in the scene that the two men are discussing Othello's marriage to Desdemona.\n"
            + "</level>\n"
            + "</note>\n"
            + "\n"
            + "<note>\n"
            + "<ln tln=\"7\"/>\n"
            + "<lem>'Sblood,</lem>\n"
            + "<level n=\"1\">\n"
            + "Contraction of \"God's blood,\" a common oath.\n"
            + "</level>\n"
            + "</note>"
            + "</annotations>";
  }
}
