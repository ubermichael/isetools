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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
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
    URL url = this.getClass().getResource("/data/test-schema.xml");
    File file = new File(url.getFile());
    Schema schema = Schema.builder().from(file).build();
    assertEquals("test", schema.getEdition());
    assertEquals("test", schema.getGroup());
    assertEquals(3, schema.getLineNumber());
    assertTrue(schema.getSource().endsWith("data/test-schema.xml"));
    assertTrue(schema.getTagNames().length > 1);
  }

  @Test
  public void testBuilderFromString() {

  }

  @Test
  public void testBuilderFromStream() throws TransformerException, ParserConfigurationException, SAXException, IOException {
    String loc = "/data/test-schema.xml";
    InputStream stream = SchemaTest.class.getResourceAsStream(loc);
    Schema schema = Schema.builder().from(loc, stream).build();

    assertEquals("test", schema.getEdition());
    assertEquals("test", schema.getGroup());
    assertEquals(3, schema.getLineNumber());
    assertTrue(schema.getSource().endsWith("data/test-schema.xml"));
    assertTrue(schema.getTagNames().length > 1);
  }

}
