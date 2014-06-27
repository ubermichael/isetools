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
 * @author Michael Joyce <michael@negativespace.net>
 */
public class DigraphCharNode extends CharNode {

  private static final HashMap<String, String> charMap = new HashMap<>();
  static {
    charMap.put("{ae}", "\u00e6");
    charMap.put("{AE}", "\u00c6");
    charMap.put("{oe}", "\u0153");
    charMap.put("{OE}", "\u0152");
    charMap.put("{qp}", "\u0239");
    charMap.put("{db}", "\u0238");
  }
  
  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.DIGRAPH;
  }

  @Override
  public Fragment expanded() {
    return wrap("DIGRAPH", charMap.get(text));
  }
}
