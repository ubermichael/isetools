/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
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
   */
  public final static int ABBR_LENGTH = 12;

  /**
   * Construct an Abbreviation node validator.
   * <p>
   * @param schema The schema for validation. Unused in abbreviation validation.
   */
  public AbbrNodeValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.abbr.depreciated",
    "validator.abbr.long"
  })
  @Override
  public void validate(AbbrNode n) {
    Message m = log.error("validator.abbr.depreciated", n);
    m.addNote("The old abbreviation found was " + n.getText());
    if (n.getText().length() > ABBR_LENGTH) {
      m = log.error("validator.abbr.long", n);
      m.addNote("The long abbreviation starts with " + n.getText().substring(0, ABBR_LENGTH));
      m.addNote("The abbreviation cannot be corrected automatically.");
    }
    n.expanded();
  }

}
