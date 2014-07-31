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
 *
 * @author michael
 */
public class ErrorCode implements Comparable<ErrorCode> {

  private final String code;
  private final int lineNumber;
  private final String message;
  private final String severity;
  private final String source;

  public static ErrorCodeBuilder builder() {
    return new ErrorCodeBuilder();
  }

  public static class ErrorCodeBuilder implements BuilderInterface<ErrorCode> {

    private String code;
    private int lineNumber;
    private String message;
    private String severity;
    private String source;

    private ErrorCodeBuilder() {
      code = "unknown";
      lineNumber = 0;
      message = "";
      severity = "";
      source = "";
    }

    @Override
    public ErrorCode build() {
      return new ErrorCode(source, lineNumber, code, severity, message);
    }

    public ErrorCodeBuilder from(String str) throws ParserConfigurationException, SAXException, TransformerException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(str);
      Node n = doc.getElementsByTagName("message").item(0);      
      return from(n);
    }
    
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
     * @param code the code to set
     */
    public ErrorCodeBuilder setCode(String code) {
      this.code = code;
      return this;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public ErrorCodeBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * @param message the message to set
     */
    public ErrorCodeBuilder setMessage(String message) {
      this.message = message;
      return this;
    }

    /**
     * @param severity the severity to set
     */
    public ErrorCodeBuilder setSeverity(String severity) {
      this.severity = severity;
      return this;
    }

    /**
     * @param source the source to set
     */
    public ErrorCodeBuilder setSource(String source) {
      this.source = source;
      return this;
    }

  }

  public ErrorCode(String source, int lineNumber, String code, String severity, String message) {
    this.source = source;
    this.lineNumber = lineNumber;
    this.code = code;
    this.severity = severity;
    this.message = message;
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
   * @return the lineNumber
   */
  public int getLineNumber() {
    return lineNumber;
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

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s:%s:%s:%s%n", source, lineNumber, code, severity);
    formatter.format("%s%n", message);
    return formatter.toString();
  }

}
