/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.Node;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

/**
 *
 * @author michael
 */
public class TestBase {

  protected final Log log;

  @Before
  public void setUp() {
    log.clear();
  }

  public TestBase() {
    log = Log.getInstance();
  }

  public void checkLog(String[] codes) {
    if (codes.length != log.count()) {
      System.out.println(log);
    }
    assertEquals(codes.length, log.count());
    for (int i = 0; i < log.count(); i++) {
      assertEquals(codes[i], log.get(i).getCode());
    }
    log.clear();
  }

  public void checkLog() {
    if (0 != log.count()) {
      System.out.println(log);
    }
    assertEquals(0, log.count());
  }

  public void checkFragment(String[] expected, Fragment actual) {
    assertEquals(expected.length, actual.size());
    for (int i = 0; i < expected.length; i++) {
      String exp = expected[i];
      String val = expected[i].substring(1);
      Node act = actual.get(i);
      switch (exp.charAt(0)) {
        case '+':
          assertEquals("START", act.type().name());
          assertEquals(val, act.getName());
          break;
        case '-':
          assertEquals("END", act.type().name());
          assertEquals(val, act.getName());
          break;
        case '#':
          assertEquals("TEXT", act.type().name());
          assertEquals(val, act.getText());
          break;
        default:
          assertEquals("EMPTY", act.type().name());
          assertEquals(val, act.getName());
      }
    }
  }

}
