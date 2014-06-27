/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.log;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class MessageTest {

  /**
   * Test of addNote method, of class Message.
   */
  @Test
  public void testAddNote() {
    Message m = new Message("");
    String[] notes = m.getNotes();
    assertEquals(0, notes.length);

    m.addNote("note 1");
    notes = m.getNotes();
    assertEquals(1, notes.length);
    assertEquals("note 1", notes[0]);

    m.addNote("note 2");
    notes = m.getNotes();
    assertEquals(2, notes.length);
    assertEquals("note 1", notes[0]);
    assertEquals("note 2", notes[1]);
  }

  /**
   * Test of compareTo method, of class Message.
   */
  @Test
  public void testCompareTo() {
    Message a = new Message("");
    Message b = new Message("");

    a.setSource("foo");
    b.setSource("bar");
    assertTrue(0 < a.compareTo(b));
    assertEquals(0, a.compareTo(a));
    assertTrue(b.compareTo(a) < 0);

    a.setSource("foo");
    b.setSource("foo");
    a.setLineNumber(2);
    b.setLineNumber(1);
    assertTrue(0 < a.compareTo(b));
    assertEquals(0, a.compareTo(a));
    assertTrue(b.compareTo(a) < 0);

    a.setLineNumber(2);
    a.setColumnNumber(3);
    b.setLineNumber(2);
    b.setColumnNumber(1);
    assertTrue(0 < a.compareTo(b));
    assertEquals(0, a.compareTo(a));
    assertTrue(b.compareTo(a) < 0);
  }
}
