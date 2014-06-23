/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class TagTest {

  /**
   * Test of getAttribute method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testGetAttribute() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag tag = new Tag("<tag/>");
    assertEquals(null, tag.getAttribute("a"));

    tag = new Tag("<tag><attribute name='a'/></tag>");
    assertEquals("a", tag.getAttribute("a").getName());
    assertEquals("a", tag.getAttribute("A").getName());
  }

  /**
   * Test of getAttributeNames method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testGetAttributeNames() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag tag = new Tag("<tag/>");
    assertArrayEquals(new String[0], tag.getAttributeNames());

    tag = new Tag("<tag></tag>");
    assertArrayEquals(new String[0], tag.getAttributeNames());

    tag = new Tag("<tag><attribute name='a'/></tag>");
    assertArrayEquals(new String[]{"a"}, tag.getAttributeNames());

    tag = new Tag("<tag><attribute name='a'/><attribute name='a'/></tag>");
    assertArrayEquals(new String[]{"a"}, tag.getAttributeNames());

    tag = new Tag("<tag><attribute name='b'/><attribute name='a'/></tag>");
    assertArrayEquals(new String[]{"a", "b"}, tag.getAttributeNames());
  }

  /**
   * Test of getAttributes method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testGetAttributes() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag tag = new Tag("<tag/>");
    assertArrayEquals(new Attribute[0], tag.getAttributes());

    tag = new Tag("<tag></tag>");
    assertArrayEquals(new Attribute[0], tag.getAttributes());

    tag = new Tag("<tag><attribute name='a'/></tag>");
    Attribute a[] = tag.getAttributes();
    assertEquals("a", a[0].getName());

    tag = new Tag("<tag><attribute name='a'/><attribute name='a'/></tag>");
    a = tag.getAttributes();
    assertEquals("a", a[0].getName());
    assertEquals(1, a.length);

    tag = new Tag("<tag><attribute name='b'/><attribute name='a'/></tag>");
    a = tag.getAttributes();
    assertEquals("a", a[0].getName());
    assertEquals("b", a[1].getName());
    assertEquals(2, a.length);
  }

  /**
   * Test of countAttributes method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testCountAttributes() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag a = new Tag("<tag/>");
    assertEquals(0, a.countAttributes());

    a = new Tag("<tag></tag>");
    assertEquals(0, a.countAttributes());

    a = new Tag("<tag><attribute name='a'/></tag>");
    assertEquals(1, a.countAttributes());

    a = new Tag("<tag><attribute name='a'/><attribute name='a'/></tag>");
    assertEquals(1, a.countAttributes());

    a = new Tag("<tag><attribute name='a'/><attribute name='b'/></tag>");
    assertEquals(2, a.countAttributes());
  }

  /**
   * Test of getName method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testGetName() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag a = new Tag("<tag name='foo' />");
    assertEquals("foo", a.getName());
  }

  /**
   * Test of getEmpty method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testEmpty() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag a = new Tag("<tag />");
    assertFalse(a.isEmpty());
    assertFalse(a.maybeEmpty());
    assertEquals("no", a.getEmpty());

    a = new Tag("<tag empty='' />");
    assertFalse(a.isEmpty());
    assertFalse(a.maybeEmpty());
    assertEquals("no", a.getEmpty());

    a = new Tag("<tag empty='no' />");
    assertFalse(a.isEmpty());
    assertFalse(a.maybeEmpty());
    assertEquals("no", a.getEmpty());

    a = new Tag("<tag empty='yes' />");
    assertTrue(a.isEmpty());
    assertTrue(a.maybeEmpty());
    assertEquals("yes", a.getEmpty());

    a = new Tag("<tag empty='optional' />");
    assertFalse(a.isEmpty());
    assertTrue(a.maybeEmpty());
    assertEquals("optional", a.getEmpty());

    a = new Tag("<tag empty='foobr' />");
    assertFalse(a.isEmpty());
    assertFalse(a.maybeEmpty());
    assertEquals("no", a.getEmpty());
  }

  /**
   * Test of isDepreciated method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testDepreciated() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag a = new Tag("<tag />");
    assertFalse(a.isDepreciated());
    assertEquals("", a.getDepreciated());

    a = new Tag("<tag depreciated=''/>");
    assertFalse(a.isDepreciated());
    assertEquals("", a.getDepreciated());

    a = new Tag("<tag depreciated='foo'/>");
    assertTrue(a.isDepreciated());
    assertEquals("foo", a.getDepreciated());
  }

  /**
   * Test of getWhere method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   * @throws javax.xml.xpath.XPathExpressionException
   */
  @Test
  public void testGetWhere() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag a = new Tag("<tag />");
    assertEquals("anywhere", a.getWhere());

    a = new Tag("<tag where=''/>");
    assertEquals("anywhere", a.getWhere());

    a = new Tag("<tag where='foo'/>");
    assertEquals("foo", a.getWhere());
  }

  /**
   * Test of getDescription method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   * @throws javax.xml.xpath.XPathExpressionException
   */
  @Test
  public void testGetDescription() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag a = new Tag("<tag />");
    assertEquals("No description provided.", a.getDescription());

    a = new Tag("<tag><desc>a description.</desc></tag>");
    assertEquals("a description.", a.getDescription());
  }

  /**
   * Test of compareTo method, of class Tag.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   * @throws javax.xml.xpath.XPathExpressionException
   */
  @Test
  public void testCompareTo() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Tag a = new Tag("<tag name='foo'/>");
    Tag b = new Tag("<tag name='bar'/>");

    assertTrue(0 < a.compareTo(b));
    assertEquals(0, a.compareTo(a));
    assertTrue(b.compareTo(a) < 0);
  }

}
