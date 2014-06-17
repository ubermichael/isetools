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
  private final String severity;
  private final String message;

  public ErrorCode(String code, String severity, String message) {
    this.code = code;
    this.severity = severity;
    this.message = message.trim();
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @return the severity
   */
  public String getSeverity() {
    return severity;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  @Override
  public int compareTo(ErrorCode o) {
    return this.code.compareTo(o.code);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);
    formatter.format("%s:%s:%s%n", code, severity, message);
    return sb.toString();    
  }

}
