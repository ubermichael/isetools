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
 * Collection of ErrorCode objects, indexed by code.
 *
 * @author michael
 */
public class ErrorCodes {

  /**
   * List of ErrorCode objects indexed by code.
   */
  private final Map<String, ErrorCode> list;

  /**
   * Build the list of error codes.
   */
  public static class ErrorCodesBuilder implements BuilderInterface<ErrorCodes> {

	/**
	 * List of ErrorCode objects in progress.
	 */
    private final Map<String, ErrorCode> list;

	/**
	 * Use ErrorCodes.builder() to get a builder.
	 */
    private ErrorCodesBuilder() {
      list = new HashMap<>();
      list.put("unknown", ErrorCode.builder().setCode("unknown").build());
    }

	/**
	 * Add an error code to the list.
	 * 
	 * @param ec
	 * @return ErrorCodesBuilder
	 */
    public ErrorCodesBuilder addErrorCode(ErrorCode ec) {
      list.put(ec.getCode(), ec);
      return this;
    }

	/**
	 * Build and return the error codes list.
	 * 
	 * @return ErrorCodes
	 */
    @Override
    public ErrorCodes build() {
      return new ErrorCodes(list);
    }

	/**
	 * Add error codes from a string.
	 * 
	 * @param str
	 * @return ErrorCodesBuilder
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
	 * Add error codes from an InputStream, located at loc.
	 * 
	 * @param loc
	 * @param in
	 * @return ErrorCodesBuilder
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
	 * Add error codes from an XML Node.
	 * 
	 * @param in
	 * @return ErrorCodesBuilder
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
	 * Add error codes from an XML file.
	 * 
	 * @param in
	 * @return ErrorCodesBuilder
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
   * Create and return a builder.
   * @return object to build the error codes.
   */
  public static ErrorCodesBuilder builder() {
    return new ErrorCodesBuilder();
  }

  /**
   * Build the default set of error codes.
   * 
   * @return ErrorCodesBuilder
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
   * Use #builder to get an ErrorCodeBuilder.
   * @param list 
   */
  private ErrorCodes(Map<String, ErrorCode> list) {
    this.list = new HashMap<>(list);
  }

  /**
   * Get an error code from a code.
   * @param code
   * @return ErrorCode
   */
  public ErrorCode getErrorCode(String code) {
    if (list.containsKey(code)) {
      return list.get(code);
    }
    return list.get("unknown");
  }

  /**
   * Get a list of error codes sorted by code.
   * @return ErrorCode[]
   */
  public ErrorCode[] getErrorCodes() {
    ErrorCode[] codes = list.values().toArray(new ErrorCode[list.size()]);
    Arrays.sort(codes);
    return codes;
  }

  /**
   * Check if the list contains an error code.
   * @param code
   * @return boolean
   */
  public boolean hasErrorCode(String code) {
    return list.containsKey(code);
  }

  /**
   * Get the size of the error codes.
   * @return int
   */
  public int size() {
    return list.size();
  }

  /**
   * Return a human-readable list of error codes.
   * @return debug string.
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
