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
import ca.nines.ise.dom.DOM;
import ca.nines.ise.exceptions.AttributeTypeException;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 * Validate a start node.
 * <p>
 * Validations performed:
 * <p>
 * <ul>
 * <li>The tag must be defined in the schema.</li>
 * <li>The tag is not required to be empty.</li>
 * <li>The tag is not depreciated.</li>
 * </ul>
 *
 * Attribute validations are handled by TagNodeValidator.
 * <p>

 */
public class StartNodeValidator extends TagNodeValidator<StartNode> {

  /**
   * Validate the start node.
   *
   * @param n StartNode to validate
   * @param schema Schema to validate against
   * @throws AttributeTypeException if the node contains an attribute of an
   * unknown type.
   */
  @Override
  public void validate(StartNode n, Schema schema) throws AttributeTypeException {
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
      Message m = Message.builder("validator.tag.startempty")
              .fromNode(n)
              .addNote("Start tag " + n.getName() + " should be self-closing.")
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
