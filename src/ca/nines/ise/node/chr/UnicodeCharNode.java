/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class UnicodeCharNode extends CharNode {

  private static final HashMap<String, String> charMap = new HashMap<>();

  static {
    charMap.put("{s}", "\u017F");
    charMap.put("{P}", "\u00B6");
    charMap.put("{r}", "\uA75B");
    charMap.put("{R}", "\uA75A");
    charMap.put("{c}", "\u00e7");
    charMap.put("{C}", "\u00c7");
    charMap.put("{th}", "\u00fe");
    charMap.put("{TH}", "\u00de");
  }

  public static Map<String, String> mapping() {
    return new HashMap<>(charMap);
  }

  @Override
  public Fragment expanded() {
    return wrap("UNICODE", charMap.get(text));
  }

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.UNICODE;
  }

}
