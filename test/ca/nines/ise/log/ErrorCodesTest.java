/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class ErrorCodesTest {

  ErrorCodes errorCodes;

  @Test
  public void testGetErrorCodes() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    File f = new File("/resources/data/test-errors.xml");

    errorCodes = new ErrorCodes(f);
    ErrorCode[] codes = errorCodes.getErrorCodes();
    assertNotNull(codes);
    assertEquals(4, codes.length);
    assertEquals("test.error", codes[0].getCode());
    assertEquals("test.fatal", codes[1].getCode());
    assertEquals("test.unknown", codes[2].getCode());
    assertEquals("test.warning", codes[3].getCode());
  }

  /**
   * Test of getErrorCode method, of class ErrorCodes.
   */
  @Test
  public void testGetErrorCode() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    File f = new File("/resources/data/test-errors.xml");

    errorCodes = new ErrorCodes(f);
    assertEquals("test.error", errorCodes.getErrorCode("test.error").getCode());
    assertEquals("test.fatal", errorCodes.getErrorCode("test.fatal").getCode());
    assertEquals("test.unknown", errorCodes.getErrorCode("test.unknown").getCode());
    assertEquals("test.warning", errorCodes.getErrorCode("test.warning").getCode());
  }

  /**
   * Test of getSeverity method, of class ErrorCodes.
   */
  @Test
  public void testGetSeverity() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    File f = new File("/resources/data/test-errors.xml");

    errorCodes = new ErrorCodes(f);
    assertEquals("error", errorCodes.getSeverity("test.error"));
    assertEquals("fatal", errorCodes.getSeverity("test.fatal"));
    assertEquals("unknown", errorCodes.getSeverity("test.unknown"));
    assertEquals("warning", errorCodes.getSeverity("test.warning"));
  }

  /**
   * Test of getMessage method, of class ErrorCodes.
   */
  @Test
  public void testGetMessage() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    File f = new File("/resources/data/test-errors.xml");

    errorCodes = new ErrorCodes(f);
    assertEquals("error code", errorCodes.getMessage("test.error"));
    assertEquals("fatal code", errorCodes.getMessage("test.fatal"));
    assertEquals("unknown code", errorCodes.getMessage("test.unknown"));
    assertEquals("warning code", errorCodes.getMessage("test.warning"));
  }
}
