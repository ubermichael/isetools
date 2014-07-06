/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 * Validate a start node.
 * <p>
 * Validations performed:
 * <p>
 * <ul>
 * <li>The tag must be defined in the schema.</li>
 * <li>The tag is not required to be empty.</li>
 * <li>The tag is not depreciated.</li>
 * </ul>
 *
 * Attribute validations are handled by TagNodeValidator.
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class StartNodeValidator extends TagNodeValidator<StartNode> {

  /**
   * Construct a start node validator.
   * <p>
   * @param schema The schema for validation.
   */
  public StartNodeValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.startempty",
    "validator.tag.depreciated"
  })
  @Override
  public void validate(StartNode n) throws Exception {
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
      Message m = Message.builder("validator.tag.startempty")
              .fromNode(n)
              .addNote("Start tag " + n.getName() + " should be self-closing.")
              .build();
      Log.addMessage(m);
    }
    if (t.isDepreciated()) {
      Message m = Message.builder("validator.tag.depreciated")
              .fromNode(n)
              .addNote(t.getDepreciated())
              .build();
      Log.addMessage(m);
    }
    validate_attributes(n);
  }

}
