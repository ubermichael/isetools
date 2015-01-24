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
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.EmptyNode;

/**
 * Non-standard space characters.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class SpaceCharNode extends CharNode {

  /**
   * Turn a space character into a fragment.
   * 
   * @return Fragment
   */
  @Override
  public Fragment expanded() {
    Fragment dom = new Fragment();
    EmptyNode node = new EmptyNode(this);
    node.setAttribute("setting", text);

    switch (innerText()) {
      case " ":
        node.setName("SPACE");
        node.setAttribute("t", "extra");
        node.setAttribute("n", "1");
        break;
      case "-":
        node.setName("SHY");
        break;
      case "#":
        node.setName("SPACE");
        node.setAttribute("t", "missing");
        node.setAttribute("n", "1");
        break;
      default:
        Message m = Message.builder("char.space.unknown")
                .fromNode(this)
                .addNote("Space markup " + text + " cannot be transformed.")
                .build();
        Log.getInstance().add(m);
    }

    dom.add(node);
    return dom;
  }

  /**
   * @return the charType
   */  
  @Override
  public CharNode.CharType getCharType() {
    return CharType.SPACE;
  }

}
