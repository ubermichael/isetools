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
 * @author Michael Joyce <ubermichael@gmail.com>
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
