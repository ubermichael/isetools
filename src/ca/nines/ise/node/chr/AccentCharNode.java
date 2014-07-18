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
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class AccentCharNode extends CharNode {

  private static final HashMap<String, String> charMap = new HashMap<>();

  static {
    charMap.put("^", "\u0302");
    charMap.put("\"", "\u0308");
    charMap.put("'", "\u0301");
    charMap.put("`", "\u0300");
    charMap.put("_", "\u0304");
    charMap.put("~", "\u0303");
  }

  public static Map<String, String> mapping() {
    return new HashMap<>(charMap);
  }
  
  @Override
  public Fragment expanded() {
    char[] cs = super.innerText().toCharArray();

    String accent = charMap.get(innerText().substring(0,1));
    if (accent == null) {
      accent = "\uFFFD";
      Message m = Message.builder("char.accent.unknown")
              .fromNode(this)
              .addNote("Character " + text + " cannot be expanded.")
              .build();
      Log.getInstance().add(m);
    }
    String str = "" + cs[1] + accent;
    str = Normalizer.normalize(str, Form.NFC);
    return wrap("ACCENT", str);
  }

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.ACCENT;
  }

}
