/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node.attribute;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;
import ca.nines.ise.validator.node.AttributeValidator;

/**
 * Validate a number value. A number is valid if it
 * matches {@code [+-]?\\d+(\\.\\d+)?}.
 * <p>
 * @author Michael Joyce <michael@negativespace.net>
 */
public class NumberAttributeValidator implements AttributeValidator {

  /**
   * Validate a number attribute.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>The attribute must be a number, by matching against the regular
   * expression {@code "^[+-]?\\d+(\\.\\d+)?$"}</li>
   * </ul>
   * <p>
   * Warning: decimal numbers between -1 and 1 require a leading zero: 0.2 is
   * OK, but .2 is not.
   * <p>
   * @param n    TagNode to validate
   * @param attr attribute to validate against
   */
  @ErrorCode(code = {
    "validator.attribute.badnumber"
  })
  @Override
  public void validate(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    if (!value.matches("^[+-]?\\d+(\\.\\d+)?$")) {
        Message m = Message.builder("validator.attribute.badnumber")
                .fromNode(n)
                .addNote("Attribute " + attr.getName() + "=" + value + " does not look like a number.")
                .build();
        Log.addMessage(m);
    }
  }

}
