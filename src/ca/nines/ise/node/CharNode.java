/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Iterator;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class CharNode extends Node {

  private boolean nested = false;
  private CharType charType;

  @Override
  public Fragment expanded() throws IOException {
    switch (charType) {
      case UNICODE:
        return expandUnicode();
      case DIGRAPH:
        return expandDigraph();
      case SPACE:
        return expandSpace();
      case ACCENT:
        return expandAccent();
      case TYPOGRAPHIC:
        return expandTypographic();
      case LIGATURE:
        return expandLigature();
      case NESTED:
        return expandNested();
      default:
        Message m = Log.getInstance().error("char.type.unknown", this);
        m.setComponent(this.getClass().getSimpleName());
        m.addNote("Expansion of " + charType.name() + " characters is not supported.");
    }
    return new Fragment();
  }

  /**
   * @return the charType
   */
  public CharType getCharType() {
    return charType;
  }

  /**
   * @param charType the charType to set
   */
  public void setCharType(CharType charType) {
    this.charType = charType;
  }

  private String innerText() {
    return text.replaceAll("^\\{|\\}$", "");
  }

  private Fragment expandUnicode() {
    Fragment dom = new Fragment();
    TextNode node = new TextNode(this);

    switch (innerText()) {
      case "c":
        node.setText("ç");
        break;
      case "C":
        node.setText("Ç");
        break;
      case "th":
        node.setText("þ");
        break;
      case "TH":
        node.setText("Þ");
        break;
      default:
        node.setText("�");
        Message m = Log.getInstance().error("char.unicode.unknown", this);
        m.setComponent(this.getClass().getSimpleName());
        m.addNote("Character " + text + " cannot be turned into unicode.");
    }
    dom.add(node);
    return dom;
  }

  private Fragment expandDigraph() {
    Fragment dom = new Fragment();

    StartNode start = new StartNode(this);
    start.setName("DIGRAPH");
    start.setAttribute("setting", innerText());
    dom.add(start);

    TextNode node = new TextNode(this);
    switch (innerText()) {
      case "ae":
        node.setText("æ");
        break;
      case "AE":
        node.setText("Æ");
        break;
      case "oe":
        node.setText("œ");
        break;
      case "OE":
        node.setText("Œ");
        break;
      case "qp":
        node.setText("ȹ");
        break;
      case "db":
        node.setText("ȸ");
        break;
      default:
        node.setText("�");
        Message m = Log.getInstance().error("char.digraph.unknown", this);
        m.setComponent(this.getClass().getSimpleName());
        m.addNote("Character " + text + " cannot be turned into a digraph.");
    }
    dom.add(node);

    EndNode end = new EndNode(this);
    end.setName("DIGRAPH");
    dom.add(end);

    return dom;
  }

  public Fragment expandSpace() {
    Fragment dom = new Fragment();
    EmptyNode node = new EmptyNode(this);

    switch (innerText()) {
      case " ":
        node.setName("SPACE");
        node.setAttribute("t", "extra");
        node.setAttribute("n", "1");
        break;
      case "-":
        node.setName("SHY");
        break;
      case "#":
        node.setName("SPACE");
        node.setAttribute("t", "missing");
        node.setAttribute("n", "1");
        break;
      default:
        Message m = Log.getInstance().error("char.space.unknown", this);
        m.setComponent(this.getClass().getSimpleName());
        m.addNote("Space markup " + text + " cannot be transformed.");
    }

    dom.add(node);
    return dom;
  }

  public Fragment expandAccent() {
    Fragment dom = new Fragment();
    char[] cs = innerText().toCharArray();

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
        str = cs[1] + "\u0302";
        break;
      case '\'':
        str = cs[1] + "\u0302";
        break;
      case '`':
        str = cs[1] + "\u0302";
        break;
      case '_':
        str = cs[1] + "\u0302";
        break;
      case '~':
        str = cs[1] + "\u0302";
        break;
      default:
        str = "\uFFFD" + cs[1];
        Message m = Log.getInstance().error("char.accent.unknown", this);
        m.setComponent(this.getClass().getSimpleName());
        m.addNote("Accent character " + cs[0] + " is unknown and cannot be transformed into unicode.");
    }

    Normalizer.normalize(str, Form.NFC);
    TextNode node = new TextNode(this);
    node.setText(str);
    dom.add(node);

    EndNode end = new EndNode(this);
    end.setName("ACCENT");
    dom.add(end);

    return dom;
  }

  private Fragment expandTypographic() {
    Fragment dom = new Fragment();

    StartNode start = new StartNode(this);
    start.setName("TYPEFORM");
    start.setAttribute("setting", text);
    dom.add(start);

    TextNode node = new TextNode(this);
    switch (innerText()) {
      case "s":
        start.setAttribute("t", "long");
        node.setText("\u017F");
        break;
      case "r":
        start.setAttribute("t", "long");
        node.setText("\uA75B");
        break;
      case "R":
        start.setAttribute("t", "long");
        node.setText("\uA75A");
        break;
      case "w":
        start.setAttribute("t", "w");
        node.setText("vv");
        break;
      case "W":
        start.setAttribute("t", "W");
        node.setText("ww");
        break;
      default:
        node.setText("\uFFFD");
        Message m = Log.getInstance().error("char.typographic.unknown", this);
        m.setComponent(this.getClass().getSimpleName());
        m.addNote("Typographic character " + text + " is unknown and cannot be transformed into unicode.");
    }
    dom.add(node);

    EndNode end = new EndNode(this);
    end.setName("TYPEFORM");
    dom.add(end);
    return dom;
  }

  private Fragment expandLigature() {
    Fragment dom = new Fragment();

    StartNode start = new StartNode(this);
    start.setName("LIG");
    start.setAttribute("setting", text);
    dom.add(start);

    TextNode node = new TextNode(this);
    node.setText(innerText());
    dom.add(this);

    EndNode end = new EndNode(this);
    end.setName("LIG");
    dom.add(end);

    return dom;
  }

  private Fragment expandNested() throws IOException {
    Fragment dom = new Fragment();

    StartNode start = new StartNode(this);
    start.setName("LIG");
    start.setAttribute("setting", text);
    dom.add(start);

    DOM inner = new Builder(innerText()).getDOM();
    Iterator<Node> iterator = inner.iterator();
    while (iterator.hasNext()) {
      Node node = iterator.next();
      dom.addAll(node.expanded());
    }

    EndNode end = new EndNode(this);
    end.setName("LIG");
    dom.add(end);

    return dom;
  }

  /**
   * @return the charType
   */
  public CharType getType() {
    return getCharType();
  }

  /**
   * @param type the charType to set
   */
  public void setType(CharType type) {
    this.setCharType(type);
  }

  /**
   * @return the nested
   */
  public boolean isNested() {
    return nested;
  }

  /**
   * @param nested the nested to set
   */
  public void setNested(boolean nested) {
    this.nested = nested;
  }

  @Override
  public String plain() {
    return getText();
  }

  @Override
  public NodeType type() {
    return NodeType.CHAR;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    if (this.getCharType() != null) {
      sb.append(" (").append(this.getCharType().name()).append(')');
    }
    return sb.toString();
  }

  @Override
  public String unicode() {
    return getText();
  }

  public enum CharType {

    ACCENT,
    DIGRAPH,
    LIGATURE,
    SPACE,
    TYPOGRAPHIC,
    UNICODE,
    NESTED
  };

}
