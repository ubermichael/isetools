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
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;
import java.text.Normalizer;
import java.text.Normalizer.Form;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class AccentCharNode extends CharNode {

  /**
   * @return the charType
   */
  @Override
  public CharType getCharType() {
    return CharType.ACCENT;
  }

  @Override
  public Fragment expanded() {
    Fragment dom = new Fragment();
    char[] cs = super.innerText().toCharArray();

    StartNode start = new StartNode(this);
    start.setName("ACCENT");
    start.setAttribute("setting", text);
    dom.add(start);

    String str = "";

    switch (cs[0]) {
      case '^':
        str = cs[1] + "\u0302";
        break;
      case '"':
        str = cs[1] + "\u0308";
        break;
      case '\'':
        str = cs[1] + "\u0301";
        break;
      case '`':
        str = cs[1] + "\u0300";
        break;
      case '_':
        str = cs[1] + "\u0304";
        break;
      case '~':
        str = cs[1] + "\u0303";
        break;
      default:
        str = cs[1] + "\uFFFD";
        Message m = Log.getInstance().error("char.accent.unknown", this);
        m.addNote("Accent character " + cs[0] + " is unknown and cannot be transformed into unicode.");
    }

    str = Normalizer.normalize(str, Form.NFC);
    TextNode node = new TextNode(this);
    node.setText(str);
    dom.add(node);

    EndNode end = new EndNode(this);
    end.setName("ACCENT");
    dom.add(end);

    return dom;
  }

}
