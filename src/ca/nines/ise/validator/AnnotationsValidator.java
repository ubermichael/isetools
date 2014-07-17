/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.validator;

import ca.nines.ise.document.Annotations;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.lemma.Note;

/**
 *
 * @author michael
 */
public class AnnotationsValidator extends ApparatusValidator<Annotations> {

  public void validate(DOM dom, Note n) {
    // do stuff.
  }
  
  public void validate(DOM dom, Annotations a) {
    for (Note n : a) {
      validate(dom, n);
    }
    
  }
  
}
