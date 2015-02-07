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

import ca.nines.ise.util.XMLDriver;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
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
