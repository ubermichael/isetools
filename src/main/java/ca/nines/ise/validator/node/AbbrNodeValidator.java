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
import ca.nines.ise.log.Log;
import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.schema.Schema;

/**
 * Validate an abbreviation node.
 * <p>
 * Validations performed:
 * <p>
 * <ul>
 * <li>All abbreviations are depreciated and generate a warning.</li>
 * <li>Any abbreviation longer than ABBR_LENGTH generate an additional
 * error.</li>
 * </ul>
 * <p>
 * Additionally, the validator will attempt to expand the abbreviation, which
 * may cause validation errors.
 * <p>
 * @see AbbrNode#expanded()
 * <p>

 */
public class AbbrNodeValidator implements NodeValidator<AbbrNode> {

  /**
   * The maximum length of abbreviation markup.
   *
   * TODO make this a configuration variable.
   */
  public final static int ABBR_LENGTH = 12;

  /**
   * Validate the abbreviation.
   *
   * @param n AbbrNode to validate.
   * @param schema Schema to validate against.
   */
  @Override
  public void validate(AbbrNode n, Schema schema) {
    Message m;
    m = Message.builder("validator.abbr.depreciated")
            .fromNode(n)
            .addNote("The old abbreviation found was " + n.getText())
            .build();
    Log.addMessage(m);

    if (n.getText().length() > ABBR_LENGTH) {
      m = Message.builder("validator.abbr.long")
              .fromNode(n)
              .addNote("The long abbreviation starts with " + n.getText().substring(0, ABBR_LENGTH))
              .addNote("The abbreviation cannot be corrected automatically.")
              .build();
      Log.addMessage(m);
    }
    n.expanded();
  }

}
