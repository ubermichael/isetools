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
public class ErrorCodeTest {
  
  /**
   * Test of compareTo method, of class ErrorCode.
   */
  @Test
  public void testCompareTo() {
    ErrorCode a = new ErrorCode("foo", "unknown", "unknown");
    ErrorCode b = new ErrorCode("bar", "unknown", "unknown");
    assertTrue(0 < a.compareTo(b));
    assertEquals(0, a.compareTo(a));
    assertTrue(b.compareTo(a) < 0);    
  }
}
