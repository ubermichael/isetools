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

import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.Node;
import java.io.IOException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class NestedCharNode extends CharNode {

  @Override
  public Fragment expanded() throws IOException {
    Fragment dom = new Fragment();

    DOM inner = new DOMBuilder(innerText()).build();
    for(Node node : inner) {
      dom.addAll(node.expanded());
    }

    return wrap("LIG", dom);
  }

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.NESTED;
  }
}
