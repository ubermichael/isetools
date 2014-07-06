/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import java.util.HashMap;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
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

  public static HashMap<String, String> mapping() {
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
