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

package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;

/**
 * Two or more characters ligatured together.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class LigatureCharNode extends CharNode {

  /**
   * Expand the ligature to a DOM fragment.
   * 
   * @return 
   */
  @Override
  public Fragment expanded() {
    return wrap("LIG", this.innerText());
  }

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.LIGATURE;
  }

}
