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

/**
 * Start node type.
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class StartNode extends TagNode {

  /**
   * {@inheritDoc}
   */
  public StartNode() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  public StartNode(Node n) {
    super(n);
  }

  /**
   * {@inheritDoc}
   */
  public StartNode(String tagname) {
    super(tagname);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public NodeType type() {
    return NodeType.START;
  }

}
