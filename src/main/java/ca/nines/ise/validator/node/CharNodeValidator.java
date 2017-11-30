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

import ca.nines.ise.node.CharNode;
import ca.nines.ise.schema.Schema;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Validate a character node.
 * <p>
 * Validations are performed by calling out to CharNode.expanded().
 * <p>
 * @see CharNode#expanded()
 * <p>

 */
public class CharNodeValidator implements NodeValidator<CharNode> {

  /**
   * Validate the character node.
   *
   * @param n CharNode to validate
   * @param schema Schema to validate against.
   */
  @Override
  public void validate(CharNode n, Schema schema) {
    try {
      n.expanded();
    } catch (IOException ex) {
      Logger.getLogger(CharNodeValidator.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
