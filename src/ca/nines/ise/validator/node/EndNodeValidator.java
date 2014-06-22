/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator;

import ca.nines.ise.log.Message;
import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class EndValidator extends TagValidator<EndNode> {

  public EndValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.comment.badstart",
    "validator.comment.badend",
    "validator.comment.dashes"
  })
  @Override
  public void validate(EndNode n) {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if (t.isEmpty()) {
      Message m = error("validator.tag.endempty", n);
      m.addNote("End tag " + n.getName() + " should not occur.");
    }
  }

}
