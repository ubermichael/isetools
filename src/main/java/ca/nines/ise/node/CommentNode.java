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

/**
 * Comment node
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class CommentNode extends Node {

  /**
   * Create a comment node.
   */
  public CommentNode() {
    super();
  }

  /**
   * Copy-constructor. Because clone() sucks.
   */
  public CommentNode(Node node) {
    super(node);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Fragment expanded() {
    CommentNode node = new CommentNode(this);
    Fragment f = new Fragment();
    f.add(node);
    return f;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String plain() {
    return "";
  }
  
  public String comment() {
    return text.replaceAll("(?:<!-?-?)|(?:-?-?>)", "");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String sgml() {
    return "<!-- " + text.replaceAll("(?:<!-?-?)|(?:-?-?>)", "") + " -->";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public NodeType type() {
    return NodeType.COMMENT;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String unicode() {
    return "";
  }

}
