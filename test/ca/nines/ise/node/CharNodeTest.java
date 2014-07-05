/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.validator.node.TestBase;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
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
