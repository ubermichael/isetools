/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.document.Corpus;
import ca.nines.ise.log.Log;
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

      String s = "1HW_F1.txt";
      Pattern p = Pattern.compile("_([a-zA-Z0-9]+)\\.txt$");
      Matcher m = p.matcher(s);
      if (m.find()) {
        System.out.println("matched: " + m.group(1));
      } else {
        System.out.println("no match.");
      }

      Corpus c = new Corpus(new File("data/sgml"));
      System.out.println(c);
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      ex.printStackTrace(System.err);
    }
    System.out.println(log);
  }
}
