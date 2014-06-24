/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.schema.Schema;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class CharNodeValidator extends NodeValidator<CharNode> {

  public CharNodeValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.comment.badstart",
    "validator.comment.badend",
    "validator.comment.dashes"
  })
  @Override
  public void validate(CharNode n) {
    try {
      n.expanded();
    } catch (IOException ex) {
      Logger.getLogger(CharNodeValidator.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
