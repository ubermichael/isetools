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
import ca.nines.ise.node.EndNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 * Validate an end node.
 * <p>
 * Validations performed:
 * <p>
 * <ul>
 * <li>The tag is defined in the schema.</li>
 * <li>The tag is not required to be empty.</li>
 * </ul>
 * <p>
 * WARNING: Depreciated end tags are not reported.
 * <p>
 * @author Michael Joyce <michael@negativespace.net>
 */
public class EndNodeValidator extends TagNodeValidator<EndNode> {

  /**
   * Validate a node against a schema.
   * 
   * @param n EndNode to validate
   * @param schema Schema to validate against
   */
  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.endempty",})
  @Override
  public void validate(EndNode n, Schema schema) {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = Message.builder("validator.tag.unknown")
              .fromNode(n)
              .addNote("Tag " + n.getName() + " is not defined in the schema.")
              .build();
      Log.addMessage(m);
      return;
    }
    if (t.isEmpty()) {
      Message m = Message.builder("validator.tag.endempty")
              .fromNode(n)
              .addNote("End tag " + n.getName() + " should not occur.")
              .build();
      Log.addMessage(m);
    }
  }

}
