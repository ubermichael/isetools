/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
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
 * @author Michael Joyce <michael@negativespace.net>
 */
public class EmptyNodeValidator extends TagNodeValidator<EmptyNode> {

  /**
   * Construct an empty node validator.
   * <p>
   * @param schema The schema for validation.
   */
  public EmptyNodeValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.emptystart",
    "validator.tag.depreciated"
  })
  @Override
  public void validate(EmptyNode n) throws Exception {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = log.error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if (!t.maybeEmpty()) {
      Message m = log.error("validator.tag.emptystart", n);
      m.addNote("Tag " + n.getName() + " should not be self-closing.");
    }
    if (t.isDepreciated()) {
      Message m = log.error("validator.tag.depreciated", n);
      m.addNote(t.getDepreciated());
    }
    validate_attributes(n);
  }

}
