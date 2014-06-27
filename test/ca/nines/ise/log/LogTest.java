/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.log;

import ca.nines.ise.node.TextNode;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class LogTest {

  /**
   * Test of getInstance method, of class Log.
   */
  @Test
  public void testGetInstance() {
    Log l1 = Log.getInstance();
    Log l2 = Log.getInstance();
    assertEquals(l1, l2);
    assertTrue(l1 == l2); // object reference equality on purpose.
  }

  /**
   * Test of error method, of class Log.
   */
  @Test
  public void testError_String() {
    Log log = Log.getInstance();
    log.clear();
    Message m = log.error("foo");
    assertEquals(1, log.count());
    assertEquals("foo", m.getCode());
    assertEquals(0, m.getColumnNumber());
    assertEquals("", m.getLine());
    assertEquals(0, m.getLineNumber());
    assertEquals("(unknown)", m.getSource());
    assertEquals("(unknown)", m.getTLN());
    log.clear();
  }

  /**
   * Test of error method, of class Log.
   */
  @Test
  public void testError_String_Node() {
    Log log = Log.getInstance();
    log.clear();
    TextNode node = new TextNode();
    node.setLine(32);
    node.setColumn(3);
    node.setTLN("33.2");
    node.setSource("FOO");
    node.setText("abc");

    Message m = log.error("", node);
    assertEquals(1, log.count());
    assertEquals(32, m.getLineNumber());
    assertEquals(3, m.getColumnNumber());
    assertEquals("", m.getCode());
    assertEquals("FOO", m.getSource());
    assertEquals("33.2", m.getTLN());
  }

  /**
   * Test of clear method, of class Log.
   */
  @Test
  public void testClear() {
    Log log = Log.getInstance();
    log.clear();
    assertEquals(0, log.count());
    log.clear();
    assertEquals(0, log.count());
    log.error("");
    assertEquals(1, log.count());
    log.clear();
    assertEquals(0, log.count());
  }

  /**
   * Test of messages method, of class Log.
   */
  @Test
  public void testMessages() {
    Log log = Log.getInstance();
    log.clear();
    assertEquals(0, log.count());
    Message[] m = log.messages();
    assertNotNull(m);
    assertEquals(0, m.length);

    Message msg = log.error("foo");
    m = log.messages();
    assertNotNull(m);
    assertEquals(1, m.length);
  }

  /**
   * Test of count method, of class Log.
   */
  @Test
  public void testCount() {
    Log log = Log.getInstance();
    log.clear();
    assertEquals(0, log.count());
    log.error("");
    assertEquals(1, log.count());
    log.clear();
  }

}
