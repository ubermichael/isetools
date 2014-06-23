/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class StartNodeValidator extends TagNodeValidator<StartNode> {

  public StartNodeValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.startempty",
    "validator.tag.depreciated"
  })
  @Override
  public void validate(StartNode n) {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = log.error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if (t.isEmpty()) {
      Message m = log.error("validator.tag.startempty", n);
      m.addNote("Start tag " + n.getName() + " should be self-closing.");
    }
    if (t.isDepreciated()) {
      Message m = log.error("validator.tag.depreciated", n);
      m.addNote(t.getDepreciated());
    }
    validate_attributes(n);
  }

}
