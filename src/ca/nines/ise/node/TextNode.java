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

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class TextNode extends Node {

  public TextNode() {
    super();
  }

  public TextNode(Node n) {
    super(n);
  }

  @Override
  public Fragment expanded() {
    Fragment f = new Fragment();
    TextNode node = new TextNode(this);
    f.add(node);
    return f;
  }

  @Override
  public String plain() {
    return text;
  }

  @Override
  public NodeType type() {
    return NodeType.TEXT;
  }

  @Override
  public String unicode() {
    return text;
  }

}
