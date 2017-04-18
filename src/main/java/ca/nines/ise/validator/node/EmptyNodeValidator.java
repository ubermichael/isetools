/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
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
import ca.nines.ise.exceptions.AttributeTypeException;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 * Validate an empty node.
 * <p>
 * Validations performed:
 * <p>
 * <ul>
 * <li>The tag must be defined in the schema.</li>
 * <li>The tag is not allowed to be start/end in the schema.</li>
 * <li>The tag is not depreciated.</li>
 * </ul>
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class EmptyNodeValidator extends TagNodeValidator<EmptyNode> {

  /**
   * Validate the end node.
   *
   * @param n EmptyNode to validate.
   * @param schema Schema to validate against.
   * @throws ca.nines.ise.exceptions.AttributeTypeException for unknown
   * attribute types.
   */
  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.emptystart",
    "validator.tag.depreciated"
  })
  @Override
  public void validate(EmptyNode n, Schema schema) throws AttributeTypeException {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = Message.builder("validator.tag.unknown")
              .fromNode(n)
              .addNote("Tag " + n.getName() + " is not defined in the schema.")
              .build();
      Log.addMessage(m);
      return;
    }
    if (!t.maybeEmpty()) {
      Message m = Message.builder("validator.tag.emptystart")
              .fromNode(n)
              .addNote("Tag " + n.getName() + " should not be self-closing.")
              .build();
      Log.addMessage(m);
    }
    if (t.isDepreciated()) {
      Message m = Message.builder("validator.tag.depreciated")
              .fromNode(n)
              .addNote(t.getDepreciated())
              .build();
      Log.addMessage(m);
    }
    validate_attributes(n, schema);
  }

}
