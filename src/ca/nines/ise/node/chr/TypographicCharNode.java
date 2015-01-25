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
import ca.nines.ise.node.TagNode;
import java.util.HashMap;
import java.util.Map;

/**
 * Named unicode typographic characters.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class TypographicCharNode extends CharNode {

  private static final HashMap<String, String> charMap = new HashMap<>();

  /**
   * Mapping.
   */
  static {
	charMap.put("{w}", "vv");
	charMap.put("{W}", "VV");
  }

  /**
   * @return copy of the mapping.
   */
  public static Map<String, String> mapping() {
	return new HashMap<>(charMap);
  }

  /**
   * Expand the char into a Fragment, which is a TYPEFORM tag with the unicode
   * content.
   *
   * @return Fragment
   */
  @Override
  public Fragment expanded() {
	Fragment dom;
	if (charMap.containsKey(text)) {
	  dom = wrap("TYPEFORM", charMap.get(text));
	} else {
	  dom = wrap("TYPEFORM", this.innerText());
	}
	((TagNode) dom.get(0)).setAttribute("t", this.innerText());
	return dom;
  }

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
	return CharType.TYPOGRAPHIC;
  }

}
