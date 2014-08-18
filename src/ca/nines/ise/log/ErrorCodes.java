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
import ca.nines.ise.util.XMLDriver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * ErrorCodes maps short error codes to proper ErrorCode objects.
 *
 * @author michael
 */
public class ErrorCodes {

  /**
   * Mapping of codes and ErrorCode objects.
   */
  private final Map<String, ErrorCode> list;

  /**
   * Create an ErrorCodesBuilder object.
   */
  public static class ErrorCodesBuilder implements BuilderInterface<ErrorCodes> {

    /**
     * Map of codes to objects.
     */
    private final Map<String, ErrorCode> list;

    /**
     * Construct an ErrorCodesBuilder object. Use ErrorCodes.builder() instead
     * of calling new.
     */
    private ErrorCodesBuilder() {
      list = new HashMap<>();
      list.put("unknown", ErrorCode.builder().setCode("unknown").build());
    }

    /**
     * Add an error code to the list of error codes.
     *
     * @param ec the error code to add
     * @return ErrorCodesBuilder to enable method chaining.
     */
    public ErrorCodesBuilder addErrorCode(ErrorCode ec) {
      list.put(ec.getCode(), ec);
      return this;
    }

    /**
     * Create the ErrorCodes object and return it.
     *
     * @return ErrorCodes the constructed object.
     */
    @Override
    public ErrorCodes build() {
      return new ErrorCodes(list);
    }

    /**
     * Construct error codes from a string.
     *
     * @param str containing the XML error code definitions.
     * @return ErrorCodesBuilder to enable method chaining.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     */
    public ErrorCodesBuilder from(String str) throws ParserConfigurationException, SAXException, TransformerException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(str);
      Node n = doc.getElementsByTagName("messages").item(0);
      return from(n);
    }

    /**
     * Construct error codes from an input stream, and set the location of the
     * data.
     *
     * @param loc the location of the input stream.
     * @param in the stream containing the xml definition of the error codes.
     * @return ErrorCodesBuilder to enable method chaining.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     * @throws IOException
     */
    public ErrorCodesBuilder from(String loc, InputStream in) throws ParserConfigurationException, SAXException, TransformerException, IOException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(loc, in);
      Node n = doc.getElementsByTagName("messages").item(0);
      return from(n);
    }

    /**
     * Construct error codes from an XML node.
     *
     * @param in an XML Node containing the data.
     * @return ErrorCodesBuilder to enable method chaining.
     */
    public ErrorCodesBuilder from(Node in) {
      NodeList nl = ((Element) in).getElementsByTagName("message");
      int length = nl.getLength();
      for (int i = 0; i < length; i++) {
        ErrorCode ec = ErrorCode.builder().from(nl.item(i)).build();
        addErrorCode(ec);
      }
      return this;
    }

    /**
     * Construct error codes from a file.
     *
     * @param in the File object containing the data.
     * @return ErrorCodesBuilder to enable method chaining.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     * @throws IOException
     */
    public ErrorCodesBuilder from(File in) throws ParserConfigurationException, SAXException, TransformerException, IOException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(in);
      Node n = doc.getElementsByTagName("messages").item(0);
      return from(n);
    }

  }

  /**
   * Create an ErrorCodesBuilder object.
   *
   * @return ErrorCodesBuilder
   */
  public static ErrorCodesBuilder builder() {
    return new ErrorCodesBuilder();
  }

  /**
   * Construct the default set of error codes.
   *
   * @return ErrorCodes
   * @throws IOException
   * @throws TransformerException
   * @throws SAXException
   * @throws ParserConfigurationException
   */
  public static ErrorCodes defaultErrorCodes() throws IOException, TransformerException, SAXException, ParserConfigurationException {
    String loc = "/resources/data/errors.xml";
    InputStream in = ErrorCodes.class.getResourceAsStream(loc);
    return builder().from(loc, in).build();
  }

  /**
   * Construct a set of ErrorCodes. Use .builder() to get a builder object
   * instead of calling new().
   *
   * @param list
   */
  private ErrorCodes(Map<String, ErrorCode> list) {
    this.list = new HashMap<>(list);
  }

  /**
   * Get an ErrorCode from the list or the default if the code doesn't exist.
   * 
   * @param code describing the error
   * @return ErrorCode 
   */
  public ErrorCode getErrorCode(String code) {
    if (list.containsKey(code)) {
      return list.get(code);
    }
    return list.get("unknown");
  }

  /**
   * Return a list of error codes sorted by code.
   * 
   * @return ErrorCode[] list
   */
  public ErrorCode[] getErrorCodes() {
    ErrorCode[] codes = list.values().toArray(new ErrorCode[list.size()]);
    Arrays.sort(codes);
    return codes;
  }

  /**
   * Check if an error code is defined.
   * 
   * @param code to check
   * @return true if the error code is defined.
   */
  public boolean hasErrorCode(String code) {
    return list.containsKey(code);
  }

  /**
   * Count the error codes.
   * 
   * @return the number of error codes defined.
   */
  public int size() {
    return list.size();
  }

  /**
   * Convert the list of error codes into a string. Mostly only useful for
   * debugging.
   * 
   * @return String serialized representation.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ErrorCode code : getErrorCodes()) {
      sb.append(code).append("\n");
    }
    return sb.toString();
  }

}
