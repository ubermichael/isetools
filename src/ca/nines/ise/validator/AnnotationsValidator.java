/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */


package ca.nines.ise.validator;

import ca.nines.ise.document.Annotations;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.lemma.Note;
import java.io.IOException;

/**
 *
 * @author michael
 */
public class AnnotationsValidator extends ApparatusValidator<Annotations> {

  // validates the markup in the note's levels
  public void validate_levels(Note n) {
  }
  
  // validate a note against the dom.
  public void validate(DOM dom, Note n) throws IOException {
    super.validate(dom, n);
    validate_levels(n);
  }
  
  public void validate(DOM dom, Annotations a) throws IOException {
    for (Note n : a) {
      validate(n);
      validate(dom, n);
    }
    
  }
  
}
