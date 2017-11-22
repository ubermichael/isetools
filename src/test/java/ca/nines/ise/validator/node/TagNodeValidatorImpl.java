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

import ca.nines.ise.exceptions.AttributeTypeException;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Schema;

/**
 *

 */
public class TagNodeValidatorImpl extends TagNodeValidator<TagNode> {

  @Override
  public void validate(TagNode node, Schema schema) throws AttributeTypeException {
    validate_attributes(node, schema);
  }
}
