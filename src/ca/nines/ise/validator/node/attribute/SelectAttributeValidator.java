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
import org.apache.commons.lang3.ArrayUtils;

/**
 * Abstract class to handle the commonalities in tag validations. Also provides
 * attribute validation.
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class SelectAttributeValidator extends AttributeValidator {

  /**
   * Validate a select attribute.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>The attribute value must be one of the allowed values.</li>
   * </ul>
   * <p>
   * @param n    TagNode to validate
   * @param attr attribute to validate against
   */
  @ErrorCode(code = {
    "validator.attribute.badselect"
  })
  @Override
  public void validate(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    String[] options = attr.getOptions();
    if (!ArrayUtils.contains(options, value)) {
      Message m = Message.builder("validator.attribute.badselect")
              .fromNode(n)
              .addNote("Attribute " + attr.getName() + " cannot contain " + value)
              .build();
      Log.addMessage(m);
    }
  }

}
