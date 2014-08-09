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

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.Fragment;
import java.io.IOException;
import java.util.Formatter;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
abstract public class Node {

  protected String asl;
  protected int column;
  protected int line;
  protected DOM ownerDom;
  protected String text;
  protected String tln;

  public Node() {
    this.tln = "";
    this.column = 0;
    this.line = 0;    
    this.text = "";
    this.ownerDom = null;
    this.asl = "";
    // do nothing.
  }

  public Node(Node n) {
    this.asl = n.asl;
    this.column = n.column;
    this.line = n.line;
    this.ownerDom = n.ownerDom;
    this.text = n.text;
    this.tln = n.tln;
  }

  /**
   * Expand the node into more tags, if possible and return them.
   * <p>
   * @return DOMFragment
   * @throws java.io.IOException
   */
  abstract public Fragment expanded() throws IOException;

  /**
   * @return the asl
   */
  public String getAsl() {
    return asl;
  }

  /**
   * @param asl the asl to set
   */
  public void setAsl(String asl) {
    this.asl = asl;
  }

  /**
   * @return the column
   */
  public int getColumn() {
    return column;
  }

  /**
   * @param column the column to set
   */
  public void setColumn(int column) {
    this.column = column;
  }

  /**
   * @return the line
   */
  public int getLine() {
    return line;
  }

  /**
   * @param line the line to set
   */
  public void setLine(int line) {
    this.line = line;
  }

  /**
   * Get the name of the tag that this node came from. For non-tag nodes, this
   * is one of #ABBR, #CHAR, #COMMENT, #EOF, or #TEXT
   * <p>
   * @return String
   */
  public String getName() {
    return "#" + this.type().toString();

  }

  /**
   * @return the ownerDom
   */
  public String getSource() {
    return ownerDom.getSource();
  }
  
  public DOM getOwner() {
    return ownerDom;
  }

  public void setOwner(DOM dom) {
    this.ownerDom = dom;
    ownerDom.requestReindex();
  }

  /**
   * @return the tln
   */
  public String getTLN() {
    return tln;
  }

  /**
   * @param tln the tln to set
   */
  public void setTLN(String tln) {
    this.tln = tln;
  }

  /**
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Convert the node into plain text. Any unicode text in the node is untouched
   * and chars are converted into plain text.
   * <p>
   * @return String
   * <p>
   * @throws java.io.IOException
   */
  public abstract String plain() throws IOException;

  /**
   * Convert a node to a string, mostly for debugging purposes.
   * <p>
   * @return String
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    return formatter.format("%s:%s:%d:%d:%s", ownerDom.getSource(), this.type(), line, column, this.text.replaceAll("\n", "\\\\n")).toString();
  }

  /**
   * Get the type of the tag that this node came from. For non-tag nodes, this
   * is one of#ABBR, #CHAR, #COMMENT, #EOF, or #TEXT. For tag nodes it is one of
   * #START, #EMPTY, #END.
   * <p>
   * @return String
   */
  public abstract NodeType type();

  /**
   * Convert the node into unicode. Any unicode in the node is untouched and
   * chars are converted into their unicode equivalents
   * <p>
   * @return String
   * <p>
   * @throws java.io.IOException
   */
  public abstract String unicode() throws IOException;

  public abstract String sgml();
  
}
