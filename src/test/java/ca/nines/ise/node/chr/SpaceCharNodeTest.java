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
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.validator.node.TestBase;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *

 */
public class SpaceCharNodeTest extends TestBase {

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

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
