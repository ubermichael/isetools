/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
