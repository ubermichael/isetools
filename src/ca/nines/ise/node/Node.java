/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import java.util.Formatter;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
abstract public class Node {

  public enum NodeType {
    ABBR,
    CHAR,
    COMMENT,
    EOF,
    EMPTY,
    END,
    START,
    TEXT
  }
  
  protected int line;
  protected int column;
  protected String source = "";
  protected String text = "";
  protected String asl = "";
  protected String tln = "";

  public Node() {
    // do nothing.
  }
  
  public Node(Node n) {
    this.asl = n.asl;
    this.column = n.column;
    this.line = n.line;
    this.source = n.source;
    this.text = n.text;
    this.tln = n.tln;
  }
  
  /**
   * Get the name of the tag that this node came from. For non-tag nodes, this
   * is one of
   * #ABBR, #CHAR, #COMMENT, #EOF, or #TEXT
   *
   * @return String
   */
  abstract public String getName();

  /**
   * Get the type of the tag that this node came from. For non-tag nodes, this
   * is one of#ABBR, #CHAR, #COMMENT, #EOF, or #TEXT. For tag nodes it is
   * one of #START, #EMPTY, #END.
   *
   * @return String
   */
  abstract public NodeType type();

  /**
   * Expand the node into more tags, if possible and return them.
   *
   * @return DOMFragment
   */
  abstract public Fragment expanded();

  /**
   * Convert the node into plain text. Any unicode text in the node is
   * untouched and chars are converted into plain text.
   *
   * @return String
   */
  abstract public String plain();

  /**
   * Convert the node into unicode. Any unicode in the node is untouched and
   * chars are converted into their unicode equivalents
   *
   * @return String
   */
  abstract public String unicode();

  /**
   * Convert a node to a string, mostly for debugging purposes.
   *
   * @return String
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    return formatter.format("%s:%s:%d:%d:%s", source, this.type(), line, column, this.text.replaceAll("\n", "\\\\n")).toString();
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
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * @param source the source to set
   */
  public void setSource(String source) {
    this.source = source;
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
   * @return the tln
   */
  public String getTln() {
    return tln;
  }

  /**
   * @param tln the tln to set
   */
  public void setTln(String tln) {
    this.tln = tln;
  }

}
