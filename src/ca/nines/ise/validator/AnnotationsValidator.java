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

  // validates the markup in the note's levels
  public void validate_levels(Note n) {
  }
  
  // validate a note against the dom.
  public void validate(DOM dom, Note n) {
    super.validate(dom, n);
    validate_levels(n);
  }
  
  public void validate(DOM dom, Annotations a) {
    for (Note n : a) {
      validate(n);
      validate(dom, n);
    }
    
  }
  
}
