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
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
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
