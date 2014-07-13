/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.log;

import ca.nines.ise.util.XMLDriver;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class ErrorCodes {

  private final Map<String, ErrorCode> list;

  public ErrorCodes() throws ParserConfigurationException, SAXException, TransformerException, IOException {
    this(new File("src/resources/data/errors.xml"));
  }

  public ErrorCodes(File in) throws ParserConfigurationException, SAXException, TransformerException, IOException {
    this.list = new HashMap<>();
    XMLDriver xml = new XMLDriver();
    Document doc = xml.drive(in);
    NodeList nl = doc.getElementsByTagName("message");
    int length = nl.getLength();
    for (int i = 0; i < length; i++) {
        Node n = nl.item(i);
        ErrorCode ec = ErrorCode.builder().from(n).build();
        list.put(ec.getCode(), ec);
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

  public boolean hasErrorCode(String code) {
    return list.containsKey(code);
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
