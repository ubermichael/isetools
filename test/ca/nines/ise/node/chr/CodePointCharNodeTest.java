/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.validator.node.TestBase;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class CodePointCharNodeTest extends TestBase {

  /**
   * Test of expanded method, of class CodePointChar.
   */
  @Test
  public void testExpanded() throws IOException {
    // leading zeros
    testExpansion("{\\u007A}", "z"); // extended latin
    testExpansion("{\\u0106}", "\u0106"); // extended latin
    testExpansion("{\\u03A6}", "\u03A6"); // greek PHI
    testExpansion("{\\u042F}", "\u042F"); // cyrillic YA.
    
    // no leading zeros
    testExpansion("{\\u7A}", "z"); // extended latin
    testExpansion("{\\u106}", "\u0106"); // extended latin
    testExpansion("{\\u3A6}", "\u03A6"); // greek PHI
    testExpansion("{\\u42F}", "\u042F"); // cyrillic YA.

    // lower case
    testExpansion("{\\u7a}", "z"); // extended latin
    testExpansion("{\\u3a6}", "\u03A6"); // greek PHI
    testExpansion("{\\u42f}", "\u042F"); // cyrillic YA.

    // higher characters
    testExpansion("{\\u6c34}", "\u6C34"); // CJK ideograph water
    
    // now this is just silly. MUSICAL SYMBOL G CLEF.
    testExpansion("{\\u1D11E}", new String(Character.toChars(0x1d11e))); 
  }

  private void testExpansion(String text, String unicode) throws IOException {
    testExpansion(text, unicode, new String[]{});
  }

  private void testExpansion(String text, String unicode, String[] errors) throws IOException {
    CharNode charNode = new CodePointCharNode();
    charNode.setText(text);
    charNode.setAsl("3.2.1");
    charNode.setColumn(42);
    charNode.setLine(420);
    charNode.setTLN("11.3");
    Fragment dom = charNode.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node node;

    assertEquals(3, dom.size());
    node = iterator.next();
    assertEquals("START", node.type().name());
    assertEquals("CODEPOINT", node.getName());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertArrayEquals(new String[]{"setting"}, ((StartNode) node).getAttributeNames());
    assertEquals(text, ((StartNode) node).getAttribute("setting"));

    node = iterator.next();
    assertEquals("TEXT", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertEquals(Normalizer.normalize(unicode, Normalizer.Form.NFC), node.getText());

    node = iterator.next();
    assertEquals("END", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertEquals("CODEPOINT", node.getName());
    checkLog(errors);
  }

}
