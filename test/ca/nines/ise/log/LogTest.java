/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.log;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class LogTest {

  @Test
  public void testAddMessage() {
    Log log = Log.getInstance();
    log.clear();
    
    Message m = Message.builder("foo").build();
    Log.addMessage(m);
    assertEquals(log.count(), 1);
    assertEquals(log.get(0).getCode(), "foo");
    log.clear();
  }

  /**
   * Test of add method, of class Log.
   */
  @Test
  public void testAdd() {
    Log log = Log.getInstance();
    log.clear();
    
    Message m = Message.builder("foo").build();
    log.add(m);
    assertEquals(log.count(), 1);
    assertEquals(log.get(0).getCode(), "foo");
    log.clear();
  }

  /**
   * Test of clear method, of class Log.
   */
  @Test
  public void testClear() {
    Log log = Log.getInstance();
    Message m = Message.builder("foo").build();
    log.add(m);
    assertTrue(log.count() >= 1);
    log.clear();
    assertTrue(log.count() == 0);
  }


}
