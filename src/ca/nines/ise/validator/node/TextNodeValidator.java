/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.TextNode;
import ca.nines.ise.schema.Schema;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class TextNodeValidator extends NodeValidator<TextNode> {

  public TextNodeValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.text.depreciatedhash",
    "validator.text.badunicode"
  })
  @Override
  public void validate(TextNode n) {
    if (n.getText().contains("#")) {
      error("validator.text.depreciatedhash", n);
    }
    if (n.getText().contains("\uFFFD")) {
      error("validator.text.badunicode", n);
    }
  }

}
