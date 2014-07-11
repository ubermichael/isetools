/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 * Validate an empty node.
 * <p>
 * Validations performed:
 * <p>
 * <ul>
 * <li>The tag must be defined in the schema.</li>
 * <li>The tag is not allowed to be start/end in the schema.</li>
 * <li>The tag is not depreciated.</li>
 * </ul>
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class EmptyNodeValidator extends TagNodeValidator<EmptyNode> {

  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.emptystart",
    "validator.tag.depreciated"
  })
  @Override
  public void validate(EmptyNode n, Schema schema) throws Exception {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = Message.builder("validator.tag.unknown")
              .fromNode(n)
              .addNote("Tag " + n.getName() + " is not defined in the schema.")
              .build();
      Log.addMessage(m);
      return;
    }
    if (!t.maybeEmpty()) {
      Message m = Message.builder("validator.tag.emptystart")
              .addNote("Tag " + n.getName() + " should not be self-closing.")
              .build();
      Log.addMessage(m);
    }
    if (t.isDepreciated()) {
      Message m = Message.builder("validator.tag.depreciated")
              .addNote(t.getDepreciated())
              .build();
      Log.addMessage(m);
    }
    validate_attributes(n, schema);
  }

}
