/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.document.Corpus;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.chr.NestedCharNode;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      String Chars[] = {
        "{e{s}h}",
        "{{s}f}",
        "{{'e}e}",
        "{{s}p}",
        "{{s}b}",
        "{{s}c}",
        "{{s}sl}",
        "{{s}s}",
        "{{s}t}",
        "{{s}l}",
        "{{s}{s}}",
        "{{s}h}",
        "{{s}i}",
        "{{s}{s}i}",
        "{{s}{s}l}"
      };
      for(String c : Chars) {
        CharNode chr = new NestedCharNode();
        chr.setText(c);
        System.out.println(c);
        System.out.println(chr.expanded());
      }
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      ex.printStackTrace(System.err);
    }
    System.out.println(log);
  }
}
