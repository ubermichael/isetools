/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.log;

import ca.nines.ise.util.XMLResourceReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class ErrorCodes {

  private final HashMap<String, ErrorCode> list;

  public ErrorCodes() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new File("/resources/data/errors.xml"));
  }

  public ErrorCodes(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new XMLResourceReader(in));
  }

  public ErrorCodes(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new XMLResourceReader(in));
  }

  public ErrorCodes(XMLResourceReader xmlIn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    list = new HashMap<>();
    for (Node n : xmlIn.xpathList("/messages/errorCodes/message")) {
      String code = xmlIn.xpathString("@code", n).toLowerCase();
      ErrorCode ec = new ErrorCode(code, xmlIn.xpathString("@severity", n), xmlIn.xpathString("text()", n));
      list.put(code, ec);
    }
  }

  public ErrorCode getErrorCode(String code) {
    if (list.containsKey(code)) {
      return list.get(code);
    }
    return list.get("unknown");
  }

  public ErrorCode[] getErrorCodes() {
    ErrorCode[] codes = list.values().toArray(new ErrorCode[list.size()]);
    Arrays.sort(codes);
    return codes;
  }

  public String getMessage(String code) {
    ErrorCode ec = list.get(code);
    if (ec != null) {
      return ec.getMessage();
    }
    return "unknown";
  }

  public String getSeverity(String code) {
    ErrorCode ec = list.get(code);
    if (ec != null) {
      return ec.getSeverity();
    }
    return "unknown";
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ErrorCode code : getErrorCodes()) {
      sb.append(code);
    }
    return sb.toString();
  }

}
