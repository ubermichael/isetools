/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.schema.Schema;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class CommentNodeValidator extends NodeValidator<CommentNode> {

  public CommentNodeValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code = {
    "validator.comment.badstart",
    "validator.comment.badend",
    "validator.comment.dashes"
  })
  @Override
  public void validate(CommentNode n) {
    Message m;
    String text = n.getText();
    if (!text.startsWith("<!--")) {
      m = log.error("validator.comment.badstart", n);
      m.setComponent(this.getClass().getSimpleName());
      m.addNote("The comment started with " + text.substring(0, 4));
    }
    if (!text.endsWith("-->")) {
      m = log.error("validator.comment.badend", n);
      m.setComponent(this.getClass().getSimpleName());
      m.addNote("The comment ended with " + text.substring(text.length() - 3));
    }
    text = text.replaceAll("^(<!--)|(-->)$", "");
    if (text.contains("--")) {
      m = log.error("validator.comment.dashes", n);
      m.setComponent(this.getClass().getSimpleName());
      m.addNote("after replace: " + text);
    }
  }

}
