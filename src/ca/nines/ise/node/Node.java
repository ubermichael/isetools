/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.node;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
abstract public class Node {

  private int line;
  private int column;
  private String source;
  private String text;
  private String asl;

  abstract String tagname();
  abstract String expanded();
  abstract String plain();
  abstract String unicode();

  @Override
  public String toString() {
    return this.tagname() + ":" + line + ":" + column + ":" + this.text;
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

}
