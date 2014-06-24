/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.dom;

import ca.nines.ise.log.Log;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author michael
 */
public class DOMStreamTest {

  private Log log;

  @Before
  public void setUp() {
    log = Log.getInstance();
    log.clear();
  }

  @Test
  public void testUTF8NoBOM() throws IOException {
    URL url = this.getClass().getResource("/resources/data/utf8.txt");
    File f = new File(url.getFile());

    DOMStream ds = new DOMStream(f);
    String lines[] = ds.getLines();
    assertEquals(0, log.count());
    assertNull(ds.getBOM());
    assertEquals("UTF-8", ds.getEncoding());
    assertEquals("cģіမ䒗멄좇", lines[0]);
  }

  @Test
  public void testUTF8WithBOM() throws IOException {
    URL url = this.getClass().getResource("/resources/data/utf8-bom.txt");
    File f = new File(url.getFile());
    DOMStream ds = new DOMStream(f);
    String lines[] = ds.getLines();
    assertEquals(1, log.count());
    assertEquals("builder.bom", log.get(0).getCode());
    assertEquals("UTF-8", ds.getBOM().getCharsetName());
    assertEquals("UTF-8", ds.getEncoding());
    assertEquals("cģіမ䒗멄좇", lines[0]);
  }

  @Test
  public void testUTF16BigEndianWithBOM() throws IOException {
    URL url = this.getClass().getResource("/resources/data/utf16be-bom.txt");
    File f = new File(url.getFile());
    DOMStream ds = new DOMStream(f);
    String lines[] = ds.getLines();
    assertEquals(2, log.count());
    assertEquals("builder.bom", log.get(0).getCode());
    assertEquals("builder.notutf8", log.get(1).getCode());
    assertEquals("UTF-16BE", ds.getBOM().getCharsetName());
    assertEquals("UTF-16BE", ds.getEncoding());
    assertEquals("cģіမ䒗멄좇", lines[0]);
  }

  @Test
  public void testUTF16LittleEndianWithBOM() throws IOException {
    URL url = this.getClass().getResource("/resources/data/utf16le-bom.txt");
    File f = new File(url.getFile());
    DOMStream ds = new DOMStream(f);
    String lines[] = ds.getLines();
    assertEquals(2, log.count());
    assertEquals("builder.bom", log.get(0).getCode());
    assertEquals("builder.notutf8", log.get(1).getCode());
    assertEquals("UTF-16LE", ds.getBOM().getCharsetName());
    assertEquals("UTF-16LE", ds.getEncoding());
    assertEquals("cģіမ䒗멄좇", lines[0]);
  }
}
