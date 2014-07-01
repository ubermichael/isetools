/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.document.Corpus;
import ca.nines.ise.document.Edition;
import ca.nines.ise.document.Work;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      Corpus corpus = new Corpus();
      Work w = corpus.getWork("Oth");
      Edition e = w.getEdition("M");
      DOM d = e.getDOM();
      System.out.println(d);
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      ex.printStackTrace(System.err);
    }
    System.out.println(log);
  }
}
