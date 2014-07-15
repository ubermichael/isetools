/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node.attribute;

import ca.nines.ise.validator.node.*;
import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;
import org.apache.commons.lang3.StringUtils;

/**
 * Validate a string attribute value. A string is valid
 * if it contains at least one non-whitespace character.
 * <p>
 * @author Michael Joyce <michael@negativespace.net>
 */
public class StringAttributeValidator implements AttributeValidator {

  /**
   * Validate a string attribute.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>The string must include at least one non-whitespace character.</li>
   * </ul>
   * <p>
   * @param n TagNode to validate
   * @param attr attribute to validate against
   */
  @ErrorCode(code = {
    "validator.attribute.badstring"
  })
  @Override
  public void validate(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    if (StringUtils.isWhitespace(value)) {
      Message m = Message.builder("validator.attribute.badstring")
              .fromNode(n)
              .addNote("Attribute " + attr.getName() + "=\"" + value + "\" only contains whitespace.")
              .build();
      Log.addMessage(m);
    }
  }

}
