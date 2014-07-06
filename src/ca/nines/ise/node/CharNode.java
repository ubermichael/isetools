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
 * @author Michael Joyce <michael@negativespace.net>
 */
abstract public class CharNode extends Node {

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

  public String getCharName() {
    return getCharType().name();
  }

  public abstract CharType getCharType();

  @Override
  public String plain() throws IOException {
    return expanded().plain();
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

  @Override
  public NodeType type() {
    return NodeType.CHAR;
  }

  @Override
  public String unicode() throws IOException {
    return expanded().unicode();
  }

  protected String innerText() {
    return text.replaceAll("^\\{|\\}$", "");
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
      Message m = Message.builder("char." + tagName.toLowerCase() + ".unknown")
              .fromNode(this)
              .addNote("Character " + text + " cannot be expanded.")
              .build();
      Log.getInstance().add(m);
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

}
