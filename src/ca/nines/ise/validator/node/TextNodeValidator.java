/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.TextNode;
import ca.nines.ise.schema.Schema;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
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
      Message m = log.error("validator.text.depreciatedhash", n);
      m.setComponent(this.getClass().getSimpleName());
    }
    if (n.getText().contains("\uFFFD")) {
      Message m = log.error("validator.text.badunicode", n);
      m.setComponent(this.getClass().getSimpleName());
    }
  }

}
