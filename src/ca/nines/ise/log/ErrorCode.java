/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.log;

import java.util.Formatter;

/**
 *
 * @author michael
 */
public class ErrorCode implements Comparable<ErrorCode> {

  private final String code;
  private final String message;
  private final String severity;

  public ErrorCode(String code, String severity, String message) {
    this.code = code;
    this.severity = severity;
    this.message = message.trim();
  }

  @Override
  public int compareTo(ErrorCode o) {
    return this.code.compareTo(o.code);
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @return the severity
   */
    public String getSeverity() {
      return severity;
    }

  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s:%s:%s%n", code, severity, message);
    return formatter.toString();
  }

}
