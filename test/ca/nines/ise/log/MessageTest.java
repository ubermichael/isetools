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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class MessageTest {

  @Test
  public void testBuilderDefaults() {
    Message m = Message.builder("foo").build();
    assertEquals(m.getCode(), "foo");
    assertEquals(m.getColumnNumber(), 0);
    assertEquals(m.getLine(), "");
    assertEquals(m.getMessage(), "unknown");
    assertArrayEquals(m.getNotes(), new String[]{});
    assertEquals(m.getSeverity(), "unknown");
    assertEquals(m.getSource(), "unknown");
    assertEquals(m.getTLN(), "unknown");
  }

  @Test
  public void testBuilderSetters() {
    Message m = Message.builder("bar")
            .setColumnNumber(3)
            .setLine("yes no")
            .setLineNumber(5)
            .setSource("ok go")
            .setTLN("42.3")
            .addNote("note 1")
            .addNote("note 2")
            .build();
    assertEquals(m.getCode(), "bar");
    assertEquals(m.getColumnNumber(), 3);
    assertEquals(m.getLine(), "yes no");
    assertEquals(m.getMessage(), "unknown");
    assertArrayEquals(m.getNotes(), new String[]{"note 1", "note 2"});
    assertEquals(m.getSeverity(), "unknown");
    assertEquals(m.getSource(), "ok go");
    assertEquals(m.getTLN(), "42.3");
  }

  /**
   * Test of compareTo method, of class Message.
   */
  @Test
  public void testCompareTo() {
    Message a;
    Message b;
    a = Message.builder("m1").setSource("bar").build();
    b = Message.builder("m1").setSource("foo").build();
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);

    a = Message.builder("m1").setSource("bar").setLineNumber(3).build();
    b = Message.builder("m1").setSource("bar").setLineNumber(5).build();
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
    
    a = Message.builder("m1").setSource("bar").setLineNumber(3).setColumnNumber(43).build();
    b = Message.builder("m1").setSource("bar").setLineNumber(3).setColumnNumber(384).build();
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
    
    a = Message.builder("m1").setSource("bar").setLineNumber(3).setColumnNumber(43).build();
    b = Message.builder("m1").setSource("bar").setLineNumber(3).setColumnNumber(43).build();
    assertTrue(a.compareTo(b) == 0);
    assertTrue(b.compareTo(a) == 0);
  }

}
