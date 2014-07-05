/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
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
 * @author Michael Joyce <michael@negativespace.net>
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
