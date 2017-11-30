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
import ca.nines.ise.node.Node;
import ca.nines.ise.schema.Schema;

/**
 * NodeValidator defines all the functionality for the individual node
 * validators. Each node validator is responsible for validating a single node,
 * via the validate() method.
 * <p>

 * @param <T> extends Node
 */
abstract public interface NodeValidator<T extends Node> {

  /**
   * Perform the text node validation, as described above.
   * <p>
   * @param n A node to validate.
   * @param schema Schema to validate against
   * @throws ca.nines.ise.exceptions.AttributeTypeException if the node has an
   * attribute with an unknown type.
   */
  abstract public void validate(T n, Schema schema) throws AttributeTypeException;

}
