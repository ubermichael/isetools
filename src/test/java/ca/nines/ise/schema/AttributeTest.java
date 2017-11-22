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
package ca.nines.ise.schema;

import ca.nines.ise.schema.Attribute.AttributeType;
import ca.nines.ise.util.XMLDriver;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *

 */
public class AttributeTest {

  @Test
  public void testBuilderDefaults() {
    Attribute a = Attribute.builder().build();
    assertEquals("", a.getDefaultValue());
    assertEquals("", a.getDepreciated());
    assertEquals("No description provided.", a.getDescription());
    assertEquals(0, a.getLineNumber());
    assertEquals("", a.getName());
    assertArrayEquals(new String[]{}, a.getOptions());
    assertEquals("", a.getSource());
    assertNull(a.getType());
    assertEquals("", a.getTypeName());

    assertFalse(a.isDepreciated());
    assertFalse(a.isEmpty());
    assertFalse(a.isOptional());
    assertFalse(a.isRenumberable());
  }

  @Test
  public void testBuilderSetters() {
    Attribute a = Attribute.builder()
            .setDefaultValue("d1")
            .setDepreciated("depr")
            .setDesc("d3")
            .setEmpty(true)
            .setLineNumber(34)
            .setName("yes")
            .setOptional(true)
            .setRenumber(true)
            .addOption("o1")
            .addOption("o2")
            .setSource("s2")
            .setType(AttributeType.STRING)
            .build();
    assertEquals("d1", a.getDefaultValue());
    assertEquals("depr", a.getDepreciated());
    assertEquals("d3", a.getDescription());
    assertEquals(34, a.getLineNumber());
    assertEquals("yes", a.getName());
    assertArrayEquals(new String[]{"o1", "o2"}, a.getOptions());
    assertEquals("s2", a.getSource());
    assertEquals(AttributeType.STRING, a.getType());
    assertEquals("string", a.getTypeName());

    assertTrue(a.isDepreciated());
    assertTrue(a.isEmpty());
    assertTrue(a.isOptional());
    assertFalse(a.isRenumberable());
  }

  @Test
  public void testBuilderFromString() throws ParserConfigurationException, TransformerConfigurationException, SAXException, TransformerException {
    String data = ""
            + "<attribute name=\"foo\" type=\"number\" depreciated=\"no no no\">\n"
            + "  <desc>depreciated attribute</desc>\n"
            + "</attribute>";
    Attribute a = Attribute.builder().from(data).build();
    assertEquals("foo", a.getName());
    assertEquals("", a.getDefaultValue());
    assertEquals("no no no", a.getDepreciated());
    assertEquals("depreciated attribute", a.getDescription());
    assertEquals(1, a.getLineNumber());
    assertArrayEquals(new String[]{}, a.getOptions());
    assertEquals("", a.getSource());
    assertEquals(AttributeType.NUMBER, a.getType());
    assertEquals("number", a.getTypeName());

    assertTrue(a.isDepreciated());
    assertFalse(a.isEmpty());
    assertFalse(a.isOptional());
    assertFalse(a.isRenumberable());
  }

  @Test
  public void testBuilderFromNode() throws ParserConfigurationException, TransformerConfigurationException, SAXException, TransformerException {
    String data = ""
            + "<attribute name=\"foo\" type=\"number\" optional=\"yes\">\n"
            + "  <desc>optional attribute</desc>\n"
            + "</attribute>";

    Document doc = new XMLDriver().drive(data);
    Attribute a = Attribute.builder().from(doc.getElementsByTagName("attribute").item(0)).build();
    assertEquals("foo", a.getName());
    assertEquals("", a.getDefaultValue());
    assertEquals("", a.getDepreciated());
    assertEquals("optional attribute", a.getDescription());
    assertEquals(1, a.getLineNumber());
    assertArrayEquals(new String[]{}, a.getOptions());
    assertEquals("", a.getSource());
    assertEquals(AttributeType.NUMBER, a.getType());
    assertEquals("number", a.getTypeName());

    assertFalse(a.isDepreciated());
    assertFalse(a.isEmpty());
    assertTrue(a.isOptional());
    assertFalse(a.isRenumberable());
  }

}
