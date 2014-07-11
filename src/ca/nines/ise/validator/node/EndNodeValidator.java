/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 * Validate an end node.
 * <p>
 * Validations performed:
 * <p>
 * <ul>
 * <li>The tag is defined in the schema.</li>
 * <li>The tag is not required to be empty.</li>
 * </ul>
 * <p>
 * WARNING: Depreciated end tags are not reported.
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class EndNodeValidator extends TagNodeValidator<EndNode> {

  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.endempty",})
  @Override
  public void validate(EndNode n, Schema schema) {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = Message.builder("validator.tag.unknown")
              .fromNode(n)
              .addNote("Tag " + n.getName() + " is not defined in the schema.")
              .build();
      Log.addMessage(m);
      return;
    }
    if (t.isEmpty()) {
      Message m = Message.builder("validator.tag.endempty")
              .fromNode(n)
              .addNote("End tag " + n.getName() + " should not occur.")
              .build();
      Log.addMessage(m);
    }
  }

}
