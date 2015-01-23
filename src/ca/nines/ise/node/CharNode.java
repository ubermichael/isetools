/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
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
import ca.nines.ise.node.chr.CodePointCharNode;
import ca.nines.ise.node.chr.DigraphCharNode;
import ca.nines.ise.node.chr.LigatureCharNode;
import ca.nines.ise.node.chr.NestedCharNode;
import ca.nines.ise.node.chr.SpaceCharNode;
import ca.nines.ise.node.chr.TypographicCharNode;
import ca.nines.ise.node.chr.UnicodeCharNode;
import java.io.IOException;

/**
 * Char nodes. There are many char node sub-types. Has many convenience methods
 * to create the subtypes.
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
abstract public class CharNode extends Node {

  /**
   * Character types.
   */
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

  /**
   * Get the name of a character type.
   * @return 
   */
  public String getCharName() {
    return getCharType().name();
  }

  /**
   * Get the character node's character type.
   * @return 
   */
  public abstract CharType getCharType();

  /**
   * Return the plain text equivalent of a character node, which is just
   * the ascii characters used to build the character.
   * @return 
   */
  @Override
  public String plain() {
    return innerText().replaceAll("[^a-zA-Z]*", "");
  }

  /**
   * Return a human-friendly string representation.
   * @return 
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    if (this.getCharType() != null) {
      sb.append(" (").append(this.getCharName()).append(')');
    }
    return sb.toString();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String sgml() {
    return text;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public NodeType type() {
    return NodeType.CHAR;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String unicode() throws IOException {
    return expanded().unicode();
  }

  /**
   * Return the text inside the curly-brace markup.
   * @return 
   */
  protected String innerText() {
    return text.replaceAll("^\\{|\\}$", "");
  }

  /**
   * Create a fragment which is a node wrapping a piece of text.
   * @param tagName
   * @param content
   * @return 
   */
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

  /**
   * Wrap a fragment in a new tag called tagName.
   * 
   * @param tagName
   * @param fragment
   * @return 
   */
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

  /**
   * Convenience method to return a new AccentCharNode.
   * 
   * @return CharNode
   */
  public static CharNode accentChar() {
    return new AccentCharNode();
  }

  /**
   * Convenience method to return a new CodePointCharNode.
   * 
   * @return CharNode
   */
  public static CharNode codePoint() {
    return new CodePointCharNode();
  }

  /**
   * Convenience method to return a new DigraphCharNode.
   * 
   * @return CharNode
   */
  public static CharNode digraphChar() {
    return new DigraphCharNode();
  }

  /**
   * Convenience method to return a new LigatureCharNode.
   * 
   * @return CharNode
   */
  public static CharNode ligatureChar() {
    return new LigatureCharNode();
  }

  /**
   * Convenience method to return a new NestedCharNode.
   * 
   * @return CharNode
   */
  public static CharNode nestedChar() {
    return new NestedCharNode();
  }

  /**
   * Convenience method to return a new SpaceCharNode.
   * 
   * @return CharNode
   */
  public static CharNode spaceChar() {
    return new SpaceCharNode();
  }

  /**
   * Convenience method to return a new TypographicCharNode.
   * 
   * @return CharNode
   */
  public static CharNode typographicChar() {
    return new TypographicCharNode();
  }

  /**
   * Convenience method to return a new UnicodeCharNode.
   * 
   * @return CharNode
   */
  public static CharNode unicodeChar() {
    return new UnicodeCharNode();
  }
}
