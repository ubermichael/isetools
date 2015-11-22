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
package ca.nines.ise.node;

import ca.nines.ise.node.chr.CharType;
import ca.nines.ise.dom.Fragment;
import ca.nines.ise.validator.node.TestBase;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class CharNodeTest extends TestBase {

  /**
   * Test of innerText method, of class CharNode.
   */
  @Test
  public void testInnerText() {
    CharNode node = new CharNodeImpl();
    node.setText("{s}");
    assertEquals("s", node.innerText());
    node.setText("{ct}");
    assertEquals("ct", node.innerText());
    node.setText("{^e}");
    assertEquals("^e", node.innerText());
    node.setText("{ae}");
    assertEquals("ae", node.innerText());
    node.setText("{#}");
    assertEquals("#", node.innerText());
    node.setText("{{s}{s}h}");
    assertEquals("{s}{s}h", node.innerText());
  }

  public class CharNodeImpl extends CharNode {

    @Override
    public Fragment expanded() throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CharType getCharType() {
      return null;
    }
  }

}
