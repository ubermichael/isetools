/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.log;

import java.util.ArrayList;
import java.util.Formatter;

/**
 * Message captures all the information about a parse, validation,
 * or other error.
 * 
 * @author michael
 */
public class Message implements Comparable<Message> {

  private String code = "(unknown)";
  private String component = "(unknown)";
  private String TLN = "(unknown)";
  private String source = "(unknown)";
  private String line = "";
  private int lineNumber = 0;
  private int columnNumber = 0;  
  private char severity = 'U';
  private ArrayList<String> notes = new ArrayList<>();

  
  Message(String code) {
    if (code == null) {
      this.code = "(unknown)";
    } else {
      this.code = code;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);
    formatter.format("%s:%d:%d:%s%n", source, lineNumber, columnNumber, code);
    formatter.format("  near TLN %s%n", TLN);
    formatter.format("  %s%n", line);
    for (String note : notes) {
      formatter.format("    * %s%n", note);
    }
    return sb.toString();
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the component
   */
  public String getComponent() {
    return component;
  }

  /**
   * @param component the component to set
   */
  public void setComponent(String component) {
    this.component = component;
  }

  /**
   * @return the TLN
   */
  public String getTLN() {
    return TLN;
  }

  /**
   * @param TLN the TLN to set
   */
  public void setTLN(String TLN) {
    this.TLN = TLN;
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
   * @return the line
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * @param line the line to set
   */
  public void setLineNumber(int line) {
    this.lineNumber = line;
  }

  /**
   * @return the column
   */
  public int getColumnNumber() {
    return columnNumber;
  }

  /**
   * @param column the column to set
   */
  public void setColumnNumber(int column) {
    this.columnNumber = column;
  }

  /**
   * @return the severity
   */
  public char getSeverity() {
    return severity;
  }

  /**
   * @param severity the severity to set
   */
  public void setSeverity(char severity) {
    this.severity = severity;
  }

  /**
   * @return the notes
   */
  public String[] getNotes() {
    return (String[]) notes.toArray();
  }

  /**
   * @param note the note to add
   */
  public void addNote(String note) {
    notes.add(note);
  }

  @Override
  public int compareTo(Message o) {
    if (this.lineNumber != o.lineNumber) {
      return this.lineNumber - o.lineNumber;
    }
    return this.columnNumber - o.columnNumber;
  }

  /**
   * @return the line
   */
  public String getLine() {
    return line;
  }

  /**
   * @param line the line to set
   */
  public void setLine(String line) {
    this.line = line;
  }
}
