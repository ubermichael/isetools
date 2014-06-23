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
public class SchemaTest {

  /**
   * Test of getTag method, of class Schema.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   * @throws javax.xml.xpath.XPathExpressionException
   */
  @Test
  public void testGetTag() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Schema schema = new Schema();

    Tag t = schema.getTag("ABBR");
    assertEquals("ABBR", t.getName());

    t = schema.getTag("abbr");
    assertEquals("ABBR", t.getName());

    t = schema.getTag("foorb");
    assertNull(t);

    t = schema.getTag("");
    assertNull(t);
  }

  /**
   * Test of getTagNames method, of class Schema.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   * @throws javax.xml.xpath.XPathExpressionException
   */
  @Test
  public void testGetTagNames() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Schema schema = new Schema();
    String names[] = schema.getTagNames();
    assertTrue(names.length > 1);
    for (int i = 0; i < names.length - 1; i++) {
      if (names[i].compareTo(names[i + 1]) > 0) {
        fail("Array of tag names isn't sorted.");
      }
    }
  }

  /**
   * Test of getTags method, of class Schema.
   * <p>
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   * @throws javax.xml.xpath.XPathExpressionException
   */
  @Test
  public void testGetTags() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Schema schema = new Schema();
    Tag tags[] = schema.getTags();
    assertTrue(tags.length > 1);
  }
}
