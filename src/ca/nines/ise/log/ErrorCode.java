/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
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
package ca.nines.ise.log;

import ca.nines.ise.util.BuilderInterface;
import ca.nines.ise.util.LocationData;
import ca.nines.ise.util.XMLDriver;
import java.util.Formatter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * ErrorCode models a single error code. Error codes map a short string code to
 * a proper error message.
 *
 * @author michael
 */
public class ErrorCode implements Comparable<ErrorCode> {

  /**
   * Error code.
   */
  private final String code;

  /**
   * the line number where the code is defined.
   */
  private final int lineNumber;

  /**
   * the fancy-pants message description.
   */
  private final String message;

  /**
   * The severity of the message: fatal, error, warning, or unknown.
   */
  private final String severity;

  /**
   * The location where the code was defined.
   */
  private final String source;

  /**
   * Construct a builder.
   *
   * @return ErrorCodeBuilder
   */
  public static ErrorCodeBuilder builder() {
    return new ErrorCodeBuilder();
  }

  /**
   * ErrorCodeBuilder implements the Builder design pattern.
   */
  public static class ErrorCodeBuilder implements BuilderInterface<ErrorCode> {

    /**
     * The error code.
     */
    private String code;

    /**
     * The line number where the error code is defined.
     */
    private int lineNumber;

    /**
     * The human-readable error message.
     */
    private String message;

    /**
     * The severity of the message.
     */
    private String severity;

    /**
     * The source of the error code.
     */
    private String source;

    /**
     * Construct an ErrorCodeBuilder.
     *
     * Use ErrorCode.builder() instead.
     */
    private ErrorCodeBuilder() {
      code = "unknown";
      lineNumber = 0;
      message = "";
      severity = "";
      source = "";
    }

    /**
     * Once the data for an error code is collected, build the ErrorCode.
     *
     * @return
     */
    @Override
    public ErrorCode build() {
      return new ErrorCode(source, lineNumber, code, severity, message);
    }

    /**
     * Collect error code data from a string.
     *
     * @param str the String to parse
     * @return ErrorCodeBuidler to enable method chaining.
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     */
    public ErrorCodeBuilder from(String str) throws ParserConfigurationException, SAXException, TransformerException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(str);
      Node n = doc.getElementsByTagName("message").item(0);
      return from(n);
    }

    /**
     * Collect error code data from an XML Node.
     *
     * @param n the XML node containing the data.
     * @return ErrorCodeBuidler to enable method chaining.
     */
    public ErrorCodeBuilder from(Node n) {
      setCode(n.getAttributes().getNamedItem("code").getNodeValue());
      setSeverity(n.getAttributes().getNamedItem("severity").getNodeValue());
      LocationData loc = (LocationData) n.getUserData(LocationData.LOCATION_DATA_KEY);
      setMessage(n.getTextContent().trim());
      setSource(loc.getSystemId());
      setLineNumber(loc.getStartLine());
      return this;
    }

    /**
     * Set the error code
     *
     * @param code the code to set
     * @return ErrorCodeBuidler to enable method chaining.
     */
    public ErrorCodeBuilder setCode(String code) {
      this.code = code;
      return this;
    }

    /**
     * Set the error code's line number.
     *
     * @param lineNumber the lineNumber to set
     * @return ErrorCodeBuidler to enable method chaining.
     */
    public ErrorCodeBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * Set the error code's human-readable version.
     *
     * @param message the message to set
     * @return ErrorCodeBuidler to enable method chaining.
     */
    public ErrorCodeBuilder setMessage(String message) {
      this.message = message;
      return this;
    }

    /**
     * Set the severity of the error.
     *
     * @param severity the severity to set
     * @return ErrorCodeBuidler to enable method chaining.
     */
    public ErrorCodeBuilder setSeverity(String severity) {
      this.severity = severity;
      return this;
    }

    /**
     * Set the source of the error code.
     *
     * @param source the source to set
     * @return ErrorCodeBuidler to enable method chaining.
     */
    public ErrorCodeBuilder setSource(String source) {
      this.source = source;
      return this;
    }

  }

  private ErrorCode(String source, int lineNumber, String code, String severity, String message) {
    this.source = source;
    this.lineNumber = lineNumber;
    this.code = code;
    this.severity = severity;
    this.message = message;
  }

  /**
   * Compare one error code to another.
   *
   * @param o the ErrorCode to compare to
   * @return a number consistent with the compareTo contract. Yes, I'm lazy and
   * don't want to write it out.
   */
  @Override
  public int compareTo(ErrorCode o) {
    return this.code.compareTo(o.code);
  }

  /**
   * Get the Error's code.
   *
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * Get the line number where the error is defined.
   *
   * @return the lineNumber
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * Get the human readable version of the error.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Get the human readable severity.
   *
   * @return the severity
   */
  public String getSeverity() {
    return severity;
  }

  /**
   * Get the source of the error message, where it was defined.
   *
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * Return a string representation of the error message. Only useful for
   * debugging.
   *
   * @return
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s:%s:%s:%s%n", source, lineNumber, code, severity);
    formatter.format("%s%n", message);
    return formatter.toString();
  }

}
