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
