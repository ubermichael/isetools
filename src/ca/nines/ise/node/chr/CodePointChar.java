/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class CodePointChar extends CharNode {

  Pattern p = Pattern.compile("\\\\u(\\p{XDigit}+)");
  
  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.UNICODE;
  }

  @Override
  public Fragment expanded() {
    Matcher m = p.matcher(innerText());
    if(m.matches()) {
      String hex = m.group(1);
      int codePoint = Integer.parseInt(hex, 16);
      char[] c = Character.toChars(codePoint);
      String str = Normalizer.normalize(new String(c), Normalizer.Form.NFC);
      return wrap("CODEPOINT", str);
    }
    
    return wrap("CODEPOINT", (String)null);
  }

}
