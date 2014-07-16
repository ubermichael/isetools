/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import ca.nines.ise.util.XMLDriver;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class TagTest {

  @Test
  public void testBuilderDefaults() {
    Tag t = Tag.builder().build();
    assertEquals(0, t.countAttributes());
    assertNull(t.getAttribute("foo"));
    assertArrayEquals(new String[]{}, t.getAttributeNames());
    assertArrayEquals(new Attribute[]{}, t.getAttributes());
    assertEquals("", t.getDepreciated());
    assertEquals("No description provided.", t.getDescription());
    assertEquals("no", t.getEmpty());
    assertEquals(0, t.getLineNumber());
    assertEquals("", t.getName());
    assertEquals("", t.getSource());
    assertFalse(t.isDepreciated());
    assertFalse(t.isEmpty());
    assertFalse(t.maybeEmpty());
  }

  @Test
  public void testBuilderSetters() {
    Tag t = Tag.builder()
            .addAttribute(Attribute.builder().setName("foo").build())
            .addAttribute(Attribute.builder().setName("bar").build())
            .setDepreciated("so very depreciated.")
            .setDesc("so very described.")
            .setEmpty("yes")
            .setLineNumber(32)
            .setName("chachacha")
            .setSource("file")
            .build();
    assertEquals(2, t.countAttributes());
    assertNull(t.getAttribute("abc"));
    assertNotNull(t.getAttribute("foo"));
    assertNotNull(t.getAttribute("bar"));
    assertNotNull(t.getAttribute("FOO"));
    assertArrayEquals(new String[]{"bar", "foo"}, t.getAttributeNames());
    assertEquals("so very depreciated.", t.getDepreciated());
    assertEquals("so very described.", t.getDescription());
    assertEquals(32, t.getLineNumber());
    assertEquals("chachacha", t.getName());
    assertEquals("file", t.getSource());
    assertTrue(t.isDepreciated());
    assertTrue(t.isEmpty());
    assertTrue(t.maybeEmpty());
  }

  @Test
  public void testBuilderFromNode() throws ParserConfigurationException, TransformerConfigurationException, SAXException, TransformerException {
    String data = ""
            + "    <tag name=\"ONEATTR\" where=\"all\">\n"
            + "      <desc>tag with one attribute</desc>\n"
            + "      <attribute name=\"n\" type=\"number\" renumber=\"yes\">\n"
            + "        <desc>one renumberable number attribute</desc>\n"
            + "      </attribute>\n"
            + "    </tag>";
    Document doc = new XMLDriver().drive(data);
    Tag t = Tag.builder().from(doc.getElementsByTagName("tag").item(0)).build();
    assertEquals(1, t.countAttributes());
    assertNull(t.getAttribute("a"));
    assertNotNull(t.getAttribute("n"));
    assertNotNull(t.getAttribute("N"));
    assertArrayEquals(new String[]{"n"}, t.getAttributeNames());
    assertEquals("", t.getDepreciated());
    assertEquals("tag with one attribute", t.getDescription());
    assertEquals(1, t.getLineNumber());
    assertEquals("ONEATTR", t.getName());
    assertEquals("", t.getSource());
    assertFalse(t.isDepreciated());
    assertFalse(t.isEmpty());
    assertFalse(t.maybeEmpty());

  }

  @Test
  public void testBuilderFromString() throws SAXException, ParserConfigurationException, TransformerException {
    String data = ""
            + "    <tag name=\"ONEATTR\" where=\"all\">\n"
            + "      <desc>tag with one attribute</desc>\n"
            + "      <attribute name=\"n\" type=\"number\" renumber=\"yes\">\n"
            + "        <desc>one renumberable number attribute</desc>\n"
            + "      </attribute>\n"
            + "    </tag>";
    Tag t = Tag.builder().from(data).build();
    assertEquals(1, t.countAttributes());
    assertNull(t.getAttribute("a"));
    assertNotNull(t.getAttribute("n"));
    assertNotNull(t.getAttribute("N"));
    assertArrayEquals(new String[]{"n"}, t.getAttributeNames());
    assertEquals("", t.getDepreciated());
    assertEquals("tag with one attribute", t.getDescription());
    assertEquals(1, t.getLineNumber());
    assertEquals("ONEATTR", t.getName());
    assertEquals("", t.getSource());
    assertFalse(t.isDepreciated());
    assertFalse(t.isEmpty());
    assertFalse(t.maybeEmpty());
  }

  @Test
  public void testCompareTo() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
    Tag a = Tag.builder().from("<tag name='bar'/>").build();
    Tag b = Tag.builder().from("<tag name='foo'/>").build();

    assertTrue(a.compareTo(b) < 0);
    assertEquals(0, a.compareTo(a));
    assertTrue(b.compareTo(a) > 0);
  }
}
