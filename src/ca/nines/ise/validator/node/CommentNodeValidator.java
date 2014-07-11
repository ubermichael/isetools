/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.schema.Schema;

/**
 * Validate a comment node.
 * <p>
 * Validations performed:
 * <p>
 * <ul>
 * <li>Comments must start with {@code <!--}</li>
 * <li>Comments must end with {@code -->}</li>
 * <li>Comments must not contain {@code --} except at the beginning and
 * end.</li>
 * </ul>
 * <p>
 * @author Michael Joyce <michael@negativespace.net>
 */
public class CommentNodeValidator extends NodeValidator<CommentNode> {

  @ErrorCode(code = {
    "validator.comment.badstart",
    "validator.comment.badend",
    "validator.comment.dashes"
  })
  @Override
  public void validate(CommentNode n, Schema schema) {
    Message m;
    String text = n.getText();
    if (!text.startsWith("<!--")) {
      m = Message.builder("validator.comment.badstart")
              .fromNode(n)
              .addNote("The comment started with " + text.substring(0, Math.min(text.length(), 4)))
              .build();
      Log.addMessage(m);
    }
    if (!text.endsWith("-->")) {
      m = Message.builder("validator.comment.badend")
              .fromNode(n)
              .addNote("The comment ended with " + text.substring(Math.max(0, text.length() - 3)))
              .build();
      Log.addMessage(m);
    }
    text = text.replaceAll("^(<!--)|(-->)$", "");
    if (text.contains("--")) {
      m = Message.builder("validator.comment.dashes")
              .fromNode(n)
              .addNote("after replace: " + text)
              .build();
      Log.addMessage(m);
    }
  }

}
