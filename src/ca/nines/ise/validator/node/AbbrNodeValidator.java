/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.schema.Schema;

/**
 * Validate an abbreviation node.
 * <p>
 * Validations performed:
 * <p>
 * <ul>
 * <li>All abbreviations are depreciated and generate a warning.</li>
 * <li>Any abbreviation longer than ABBR_LENGTH generate an additional
 * error.</li>
 * </ul>
 * <p>
 * Additionally, the validator will attempt to expand the abbreviation, which
 * may cause validation errors.
 * <p>
 * @see AbbrNode#expanded()
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class AbbrNodeValidator extends NodeValidator<AbbrNode> {

  /**
   * The maximum length of abbreviation markup.
   * 
   * TODO make this a configuration variable.
   */
  public final static int ABBR_LENGTH = 12;

  @ErrorCode(code = {
    "validator.abbr.depreciated",
    "validator.abbr.long"
  })
  @Override
  public void validate(AbbrNode n, Schema schema) {
    Message m;
    m = Message.builder("validator.abbr.depreciated")
            .fromNode(n)
            .addNote("The old abbreviation found was " + n.getText())
            .build();
    Log.addMessage(m);
            
    if (n.getText().length() > ABBR_LENGTH) {
      m = Message.builder("validator.abbr.long")
              .fromNode(n)
              .addNote("The long abbreviation starts with " + n.getText().substring(0, ABBR_LENGTH))
              .addNote("The abbreviation cannot be corrected automatically.")
              .build();
      Log.addMessage(m);
    }
    n.expanded();
  }

}
