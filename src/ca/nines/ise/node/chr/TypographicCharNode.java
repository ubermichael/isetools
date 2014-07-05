/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.TagNode;
import java.util.HashMap;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class TypographicCharNode extends CharNode {

  private static final HashMap<String, String> charMap = new HashMap<>();

  static {
    charMap.put("{w}", "vv");
    charMap.put("{W}", "VV");
  }

  @Override
  public Fragment expanded() {
    Fragment dom = wrap("TYPEFORM", charMap.get(text));
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
