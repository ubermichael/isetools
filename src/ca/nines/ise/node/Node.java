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
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class Node {

  protected Fragment _expanded;
  protected String _plain;
  protected String _unicode;
  protected String asl = "";
  protected int column;

  protected int line;
  protected String source = "";
  protected String text = "";
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
   * Expand the node into more tags, if possible and return them.
   * <p>
   * @return DOMFragment
   */
  abstract public Fragment expanded();

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

  /**
   * Convert the node into plain text. Any unicode text in the node is untouched
   * and chars are converted into plain text.
   * <p>
   * @return String
   */
  public abstract String plain();

  /**
   * Convert a node to a string, mostly for debugging purposes.
   * <p>
   * @return String
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    return formatter.format("%s:%s:%d:%d:%s", source, this.type(), line, column, this.text.replaceAll("\n", "\\\\n")).toString();
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
   */
  public abstract String unicode();

  public enum NodeType {

    ABBR,
    CHAR,
    COMMENT,
    EMPTY,
    END,
    START,
    TEXT
  }

}
