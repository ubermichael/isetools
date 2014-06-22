/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator;

import ca.nines.ise.log.Message;
import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class EmptyValidator extends TagValidator<EmptyNode> {

  public EmptyValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.emptystart",
    "validator.tag.depreciated"
  })
  @Override
  public void validate(EmptyNode n) {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if (!t.maybeEmpty()) {
      Message m = error("validator.tag.emptystart", n);
      m.addNote("Tag " + n.getName() + " should not be self-closing.");
    }
    if (t.isDepreciated()) {
      Message m = error("validator.tag.depreciated", n);
      m.addNote(t.getDepreciated());
    }
    validate_attributes(n);
  }

}
