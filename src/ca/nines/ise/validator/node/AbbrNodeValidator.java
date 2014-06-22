/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.schema.Schema;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class AbbrNodeValidator extends NodeValidator<AbbrNode> {

  public final static int ABBR_LENGTH = 12;
  
  public AbbrNodeValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.abbr.depreciated"
  })
  @Override
  public void validate(AbbrNode n) {
    Message m = error("validator.abbr.depreciated", n);
    m.addNote("The old abbreviation found was " + n.getText());
    if (n.getText().length() > ABBR_LENGTH) {
      m = error("validator.abbr.long", n);
      m.addNote("The long abbreviation starts with " + n.getText().substring(0, ABBR_LENGTH));
      m.addNote("The abbreviation cannot be corrected automatically.");
    }
    n.expanded();
  }

}
