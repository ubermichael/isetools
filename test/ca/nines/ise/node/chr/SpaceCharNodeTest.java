/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.validator.node.TestBase;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class SpaceCharNodeTest extends TestBase {

  // @TODO move this up a level, to an abstract CharTestBase
  // along with the common code for checking line, col, etc.
  private CharNode buildNode(CharNode charNode, String text) {
    charNode.setAsl("3.2.1");
    charNode.setColumn(42);
    charNode.setLine(420);
    charNode.setTLN("11.3");
    charNode.setText(text);
    return charNode;
  }

  /**
   * Test of expanded method, of class SpaceCharNode.
   */
  @Test
  public void testExpandedSpace() throws IOException {
    CharNode charNode = buildNode(new SpaceCharNode(), "{ }");

    Fragment dom = charNode.expanded();
    assertEquals(1, dom.size());
    assertEquals("EMPTY", dom.get(0).type().name());
    EmptyNode node = (EmptyNode) dom.get(0);
    assertEquals("SPACE", node.getName());
    assertArrayEquals(new String[]{"n", "setting", "t"}, node.getAttributeNames());
    assertEquals("1", node.getAttribute("n"));
    assertEquals("{ }", node.getAttribute("setting"));
    assertEquals("extra", node.getAttribute("t"));
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
  }

  /**
   * Test of expanded method, of class SpaceCharNode.
   */
  @Test
  public void testExpandedShy() throws IOException {
    CharNode charNode = buildNode(new SpaceCharNode(), "{-}");

    Fragment dom = charNode.expanded();
    assertEquals(1, dom.size());
    assertEquals("EMPTY", dom.get(0).type().name());
    EmptyNode node = (EmptyNode) dom.get(0);
    assertEquals("SHY", node.getName());
    assertArrayEquals(new String[]{"setting"}, node.getAttributeNames());
    assertEquals("{-}", node.getAttribute("setting"));
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
  }
  
  /**
   * Test of expanded method, of class SpaceCharNode.
   */
  @Test
  public void testExpandedSpaceMissing() throws IOException {
    CharNode charNode = buildNode(new SpaceCharNode(), "{#}");

    Fragment dom = charNode.expanded();
    assertEquals(1, dom.size());
    assertEquals("EMPTY", dom.get(0).type().name());
    EmptyNode node = (EmptyNode) dom.get(0);
    assertEquals("SPACE", node.getName());
    assertArrayEquals(new String[]{"n", "setting", "t"}, node.getAttributeNames());
    assertEquals("1", node.getAttribute("n"));
    assertEquals("{#}", node.getAttribute("setting"));
    assertEquals("missing", node.getAttribute("t"));
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
  }
  
}
