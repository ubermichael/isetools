/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import java.io.IOException;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
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
    DIGRAPH,
    LIGATURE,
    SPACE,
    TYPOGRAPHIC,
    UNICODE,
    NESTED
  };

}
