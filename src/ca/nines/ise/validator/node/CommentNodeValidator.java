/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
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
public class CommentNodeValidator implements NodeValidator<CommentNode> {

  /**
   * Validate the comment node.
   * 
   * @param n CommentNode to validate
   * @param schema Schema to validate against.
   */
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
