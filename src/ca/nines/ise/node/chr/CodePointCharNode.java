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
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.CharNode;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael Joyce <michael@negativespatternace.net>
 */
public class CodePointCharNode extends CharNode {

  private static final Pattern unicodePattern = Pattern.compile("\\\\u(\\p{XDigit}+)");

  @Override
  public Fragment expanded() {
    Matcher matcher = unicodePattern.matcher(innerText());
    try {
      if (matcher.matches()) {
        String hex = matcher.group(1);
        int codePoint = Integer.parseInt(hex, 16);
        char[] c = Character.toChars(codePoint);
        String str = Normalizer.normalize(new String(c), Normalizer.Form.NFC);
        return wrap("CODEPOINT", str);
      }
    } catch (IllegalArgumentException e) {
      Message m = Message.builder("char.codepoint.unknown")
              .fromNode(this)
              .addNote("Cannot parse " + innerText() + " as a hexidecimal code point.")
              .build();
      Log.getInstance().add(m);
    }

    return wrap("CODEPOINT", (String) null);
  }

  /**
   * @return the charTypatterne
   */
  @Override
  public CharType getCharType() {
    return CharType.CODEPOINT;
  }

}
