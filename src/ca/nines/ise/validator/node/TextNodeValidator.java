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
import ca.nines.ise.node.TextNode;
import ca.nines.ise.schema.Schema;

/**
 * TextNodeValidator performs validations on pure text nodes. In particular,
 * text nodes cannot contain the octothorpe. This validator also creates an
 * error for bad unicode characters (\uFFFD).
 * <p>
 * @author Michael Joyce <michael@negativespace.net>
 */
public class TextNodeValidator implements NodeValidator<TextNode> {

  /**
   * Perform validation on a text node.
   * 
   * @param n TextNode to validate
   * @param schema Schema to validate against.
   */
  @ErrorCode(code = {
    "validator.text.depreciatedhash",
    "validator.text.badunicode"
  })
  @Override
  public void validate(TextNode n, Schema schema) {
    if (n.getText().contains("#")) {
      Message m = Message.builder("validator.text.depreciatedhash")
              .fromNode(n)
              .build();
      Log.addMessage(m);
    }
    if (n.getText().contains("\uFFFD")) {
      Message m = Message.builder("validator.text.badunicode")
              .fromNode(n)
              .build();
      Log.addMessage(m);
    }
  }

}
