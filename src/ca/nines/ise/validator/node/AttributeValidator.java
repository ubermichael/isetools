/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.validator.node;

import ca.nines.ise.log.Log;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;

/**
 *
 * @author michael
 */
abstract public class AttributeValidator {
  
  protected final Log log;

  public AttributeValidator() {
    log = Log.getInstance();
  }
  
  public abstract void validate(TagNode n, Attribute a);
  
}
