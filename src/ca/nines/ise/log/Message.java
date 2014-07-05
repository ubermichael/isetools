/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.log;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Message captures all the information about a parse, validation, or other
 * error.
 * <p>
 * @author michael
 */
public class Message implements Comparable<Message> {

  private static final ErrorCodes errorCodes;

  static {
    ErrorCodes tmp = null;
    try {
      tmp = new ErrorCodes();
    } catch (Exception ex) {
      Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
    }
    errorCodes = tmp;
  }
  private String TLN = "(unknown)";
  private String code = "(unknown)";
  private int columnNumber = 0;
  private String line = "";
  private int lineNumber = 0;
  private final ArrayList<String> notes = new ArrayList<>();
  private String source = "(unknown)";

  /**
   * Construct a message object for the given message code.
   * <p>
   * @param code
   */
  public Message(String code) {
    if (code == null) {
      this.code = "(unknown)";
    } else {
      this.code = code;
    }
  }

  /**
   * @param note the note to add
   */
  public void addNote(String note) {
    notes.add(note);
  }

  @Override
  public int compareTo(Message o) {
    if (!this.source.equals(o.source)) {
      return this.source.compareTo(o.source);
    }
    if (this.lineNumber != o.lineNumber) {
      return this.lineNumber - o.lineNumber;
    }
    return this.columnNumber - o.columnNumber;
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

  public String getMessage() {
    if (errorCodes != null) {
      return errorCodes.getMessage(code);
    } else {
      return "unknown";
    }
  }

  /**
   * @return the notes
   */
  public String[] getNotes() {
    return notes.toArray(new String[notes.size()]);
  }

  /**
   * @return the severity
   */
  public String getSeverity() {
    if (errorCodes != null) {
      return errorCodes.getSeverity(code);
    } else {
      return "unknown";
    }
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);
    formatter.format("%s:%d:%d:%s%n", source, lineNumber, columnNumber, code);
    formatter.format("  %s:%s%n", getSeverity(), getMessage());
    formatter.format("  near TLN %s%n", TLN);
    if (!line.equals("")) {
      formatter.format("  %s%n", line);
    }
    for (String note : notes) {
      formatter.format("    * %s%n", note);
    }
    return sb.toString();
  }
}
