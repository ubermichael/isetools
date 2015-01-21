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
import ca.nines.ise.util.CodePointTable;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael Joyce <michael@negativespatternace.net>
 */
public class CodePointCharNode extends CharNode {

  private static final Pattern hexEntity = Pattern.compile("&#x(\\p{XDigit}+)");
  private static final Pattern decimalEntity = Pattern.compile("&#(\\p{Digit}+)");
  private static final Pattern namedEntity = Pattern.compile("&(\\p{Alnum}+)");

  private static CodePointTable tbl = null;
  
  private Fragment expandNumeric(String value, int base) {
    int codePoint = Integer.parseInt(value, base);
    char[] c = Character.toChars(codePoint);
    String str = Normalizer.normalize(new String(c), Normalizer.Form.NFC);
    return wrap("CODEPOINT", str);
  }
  
  private Fragment expandNamed(String value) throws IOException {
	if(tbl == null) {
	  tbl = CodePointTable.defaultCodePointTable();
	}
	return wrap("CODEPOINT", tbl.getCodePoint(value).getValue());
  }

  @Override
  public Fragment expanded() throws IOException {

    Matcher m;

	// @TODO these should be refactored into classes, and the parser
	// should distinguish them.
    m = hexEntity.matcher(innerText());
    if (m.matches()) {
      return expandNumeric(m.group(1), 16);
    }
    m = decimalEntity.matcher(innerText());
    if (m.matches()) {
      return expandNumeric(m.group(1), 10);
    }
    m = namedEntity.matcher(innerText());
    if (m.matches()) {
      return expandNamed(m.group(1));
    }
    Message message = Message.builder("char.codepoint.unknown")
            .fromNode(this)
            .addNote("Cannot parse " + innerText() + " as a code point.")
            .build();
    Log.getInstance().add(message);
    return null;
  }

  /**
   * @return the charTypatterne
   */
  @Override
  public CharType getCharType() {
    return CharType.CODEPOINT;
  }

}
