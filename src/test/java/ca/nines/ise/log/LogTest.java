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
