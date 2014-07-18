/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.chr.AccentCharNode;
import java.io.IOException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
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

  public static CharNode accentChar() {
    return new AccentCharNode();
  }

  public static CharNode codePoint() {
    return null;
  }

  public static CharNode digraphChar() {
    return null;
  }

  public static CharNode ligatureChar() {
    return null;
  }

  public static CharNode nestedChar() {
    return null;
  }

  public static CharNode spaceChar() {
    return null;
  }

  public static CharNode typographicChar() {
    return null;
  }

  public static CharNode unicodeChar() {
    return null;
  }
}
