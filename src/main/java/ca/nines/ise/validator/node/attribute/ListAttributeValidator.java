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

package ca.nines.ise.validator.node.attribute;

import ca.nines.ise.validator.node.*;
import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Validate a list attribute value. A list attribute is valid
 * if it contains a comma separated list of values, and each
 * of those values is defined in the schema.
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class ListAttributeValidator implements AttributeValidator {

  /**
   * Validate a list attribute.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>The attribute value is treated as a comma separated list, and each item
   * in the list must be one of the allowed values.</li>
   * </ul>
   * <p>
   * @param n    TagNode to validate
   * @param attr attribute to validate against
   */
  @ErrorCode(code = {
    "validator.attribute.badlist"
  })
  @Override
  public void validate(TagNode n, Attribute attr) {
    String values[] = n.getAttribute(attr.getName()).split(", ?");
    String[] options = attr.getOptions();
    for (String value : values) {
      if (!ArrayUtils.contains(options, value)) {
        Message m = Message.builder("validator.attribute.badlist")
                .fromNode(n)
                .addNote("Attribute " + attr.getName() + " cannot contain " + value)
                .build();
        Log.addMessage(m);
      }
    }
  }

}
