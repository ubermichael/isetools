/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import java.io.IOException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class CharNode extends Node {

  abstract public CharType getCharType();

  public String getCharName() {
    return getCharType().name();
  }

  protected String innerText() {
    return text.replaceAll("^\\{|\\}$", "");
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
      sb.append(" (").append(this.getCharName()).append(')');
    }
    return sb.toString();
  }

  protected Fragment wrap(String tagName, String content) {
    Fragment dom = new Fragment();
    TagNode node;

    node = new StartNode(this);
    node.setName(tagName);
    node.setAttribute("setting", this.text);
    dom.add(node);

    TextNode textNode = new TextNode(this);
    if (content == null) {
      content = "\uFFFD";
      Message m = Log.getInstance().error("char." + tagName.toLowerCase() + ".unknown", this);
      m.addNote("Character " + text + " cannot be expanded.");
    }
    textNode.setText(content);
    dom.add(textNode);

    node = new EndNode(this);
    node.setName(tagName);
    dom.add(node);

    return dom;
  }

  protected Fragment wrap(String tagName, Fragment fragment) {
    Fragment dom = new Fragment();
    TagNode node;

    node = new StartNode(this);
    node.setName(tagName);
    node.setAttribute("setting", this.text);
    dom.add(node);

    dom.addAll(fragment);

    node = new EndNode(this);
    node.setName(tagName);
    dom.add(node);

    return dom;
  }

  @Override
  public String unicode() throws IOException {
    return expanded().unicode();
  }

  @Override
  public String plain() throws IOException {
    return expanded().plain();
  }

  public enum CharType {

    ACCENT,
    CODEPOINT,
    DIGRAPH,
    LIGATURE,
    SPACE,
    TYPOGRAPHIC,
    UNICODE,
    NESTED
  };

}
