/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author michael
 */
public class ErrorCodes {

  private final Map<String, ErrorCode> list;

  public static class ErrorCodesBuilder implements BuilderInterface<ErrorCodes> {

    private Map<String, ErrorCode> list;

    private ErrorCodesBuilder() {
      list = new HashMap<>();
      list.put("unknown", ErrorCode.builder().setCode("unknown").build());
    }

    public ErrorCodesBuilder addErrorCode(ErrorCode ec) {
      list.put(ec.getCode(), ec);
      return this;
    }

    @Override
    public ErrorCodes build() {
      return new ErrorCodes(list);
    }

    public ErrorCodesBuilder from(String str) throws ParserConfigurationException, SAXException, TransformerException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(str);
      Node n = doc.getElementsByTagName("messages").item(0);
      return from(n);
    }

    public ErrorCodesBuilder from(String loc, InputStream in) throws ParserConfigurationException, SAXException, TransformerException, IOException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(loc, in);
      Node n = doc.getElementsByTagName("messages").item(0);
      return from(n);
    }

    public ErrorCodesBuilder from(Node in) {
      NodeList nl = ((Element) in).getElementsByTagName("message");
      int length = nl.getLength();
      for (int i = 0; i < length; i++) {
        ErrorCode ec = ErrorCode.builder().from(nl.item(i)).build();
        addErrorCode(ec);
      }
      return this;
    }

    public ErrorCodesBuilder from(File in) throws ParserConfigurationException, SAXException, TransformerException, IOException {
      XMLDriver xd = new XMLDriver();
      Document doc = xd.drive(in);
      Node n = doc.getElementsByTagName("messages").item(0);
      return from(n);
    }

  }

  public static ErrorCodesBuilder builder() {
    return new ErrorCodesBuilder();
  }

  public static ErrorCodes defaultErrorCodes() throws IOException, TransformerException, SAXException, ParserConfigurationException {
    String loc = "/resources/data/errors.xml";
    InputStream in = ErrorCodes.class.getResourceAsStream(loc);
    return builder().from(loc, in).build();
  }

  private ErrorCodes(Map<String, ErrorCode> list) {
    this.list = new HashMap<>(list);
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

  public boolean hasErrorCode(String code) {
    return list.containsKey(code);
  }
  
  public int size() {
    return list.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ErrorCode code : getErrorCodes()) {
      sb.append(code).append("\n");
    }
    return sb.toString();
  }

}
