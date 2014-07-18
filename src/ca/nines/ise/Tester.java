/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.document.Annotations;
import ca.nines.ise.document.Edition;
import ca.nines.ise.document.Work;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.validator.AnnotationsValidator;
import java.io.File;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      Work w = new Work(new File("input/withTitlePage/Oth"));
      Edition e = w.getEdition("M");
      DOM d = e.getDOM();
      Fragment frag = d.getTlnFragment("42", 2);
      System.out.println(frag);
//      Annotations a = e.getAnnotations();
//      AnnotationsValidator av = new AnnotationsValidator();
//      av.validate(d, a);
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    } finally {
      System.out.println(log);
    }
  }
}
