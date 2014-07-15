/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;

/**
 * An interface for attribute validators.
 *
 * @author michael
 */
abstract public interface AttributeValidator {

  public void validate(TagNode n, Attribute a);

}
