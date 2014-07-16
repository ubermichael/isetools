/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class SchemaTest {
  
  @Test
  public void testBuilderDefaults() {
    Schema schema = Schema.builder().build();
    assertEquals("", schema.getEdition());
    assertEquals("", schema.getGroup());
    assertEquals(0, schema.getLineNumber());
    assertEquals("", schema.getSource());
    assertArrayEquals(new String[]{}, schema.getTagNames());
    assertArrayEquals(new Tag[]{}, schema.getTags());
  }

  @Test
  public void testBuilderSetters() {
    Schema schema = Schema.builder()
            .setEdition("ed1")
            .setGroup("g3")
            .setLineNumber(32)
            .setSource("yes")
            .addTag(Tag.builder().setName("a").build())
            .addTag(Tag.builder().setName("b").build())            
            .build();
    assertEquals("ed1", schema.getEdition());
    assertEquals("g3", schema.getGroup());
    assertEquals(32, schema.getLineNumber());
    assertEquals("yes", schema.getSource());
    assertArrayEquals(new String[]{"a", "b"}, schema.getTagNames());
    assertNotNull(schema.getTag("a"));
    assertNotNull(schema.getTag("A"));
    assertNotNull(schema.getTag("b"));
  }

  @Test
  public void testBuilderFromFile() throws ParserConfigurationException, SAXException, TransformerException, IOException {
    File file = new File("test/resources/data/test-schema.xml");
    Schema schema = Schema.builder().from(file).build();
    assertEquals("test", schema.getEdition());
    assertEquals("test", schema.getGroup());
    assertEquals(3, schema.getLineNumber());
    assertTrue(schema.getSource().endsWith("test/resources/data/test-schema.xml"));
    assertTrue(schema.getTagNames().length > 1);
  }

  @Test
  public void testBuilderFromString() {
    
  }

  @Test
  public void testBuilderFromStream() throws TransformerException, ParserConfigurationException, SAXException, IOException {
    String loc = "/resources/data/test-schema.xml";
    InputStream stream = SchemaTest.class.getResourceAsStream(loc);
    Schema schema = Schema.builder().from(loc, stream).build();
    
    assertEquals("test", schema.getEdition());
    assertEquals("test", schema.getGroup());
    assertEquals(3, schema.getLineNumber());
    assertTrue(schema.getSource().endsWith("/resources/data/test-schema.xml"));
    assertTrue(schema.getTagNames().length > 1);
  }

}
