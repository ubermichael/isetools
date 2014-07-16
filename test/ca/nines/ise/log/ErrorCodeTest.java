/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.log;

import ca.nines.ise.util.XMLDriver;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class ErrorCodeTest {

  @Test
  public void testBuilderDefaults() {
    ErrorCode a = ErrorCode.builder().build();
    assertEquals(a.getCode(), "unknown");
    assertEquals(a.getLineNumber(), 0);
    assertEquals(a.getMessage(), "");
    assertEquals(a.getSeverity(), "");
    assertEquals(a.getSource(), "");
  }

  @Test
  public void testBuilderSetters() {
    ErrorCode a = ErrorCode.builder().setCode("foo").setLineNumber(3)
            .setMessage("yes please.").setSeverity("great")
            .setSource("stringish").build();

    assertEquals(a.getCode(), "foo");
    assertEquals(a.getLineNumber(), 3);
    assertEquals(a.getMessage(), "yes please.");
    assertEquals(a.getSeverity(), "great");
    assertEquals(a.getSource(), "stringish");
  }

  @Test
  public void testBuilderFromString() throws ParserConfigurationException, SAXException, TransformerException {
    String data = "<message code=\"test.error\" severity=\"error\">\n"
            + "      error code\n"
            + "    </message>\n";
    ErrorCode a = ErrorCode.builder().from(data).build();

    assertEquals(a.getCode(), "test.error");
    assertEquals(a.getLineNumber(), 1);
    assertEquals(a.getMessage(), "error code");
    assertEquals(a.getSeverity(), "error");
    assertEquals(a.getSource(), "");
  }
  
  @Test
  public void testBuilderFromNode() throws ParserConfigurationException, SAXException, TransformerException {
    String data = "<message code=\"test.error\" severity=\"error\">\n"
            + "      error code\n"
            + "    </message>\n";
    Document doc = new XMLDriver().drive(data);
    ErrorCode a = ErrorCode.builder().from(doc.getElementsByTagName("message").item(0)).build();

    assertEquals(a.getCode(), "test.error");
    assertEquals(a.getLineNumber(), 1);
    assertEquals(a.getMessage(), "error code");
    assertEquals(a.getSeverity(), "error");
    assertEquals(a.getSource(), "");
  }
  

  /**
   * Test of compareTo method, of class ErrorCode.
   */
  @Test
  public void testCompareTo() {
    ErrorCode a;
    ErrorCode b;
    a = ErrorCode.builder().setCode("bar").build();
    b = ErrorCode.builder().setCode("foo").build();
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);

    a = ErrorCode.builder().setCode("bar").setLineNumber(3).build();
    b = ErrorCode.builder().setCode("bar").setLineNumber(4).build();
    assertTrue(a.compareTo(b) == 0);
  }

}
