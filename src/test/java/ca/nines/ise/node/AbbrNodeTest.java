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

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.validator.node.TestBase;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class AbbrNodeTest extends TestBase {

  /**
   * Test of expanded method with a caret abbr, of class AbbrNode.
   */
  @Test
  public void testExpandedCaret() {
    AbbrNode abbr = new AbbrNode();
    abbr.setText(("|a^b|"));
    abbr.setAsl("3.2.1");
    abbr.setColumn(42);
    abbr.setLine(420);
    abbr.setTLN("11.3");

    Fragment result = abbr.expanded();
    Iterator<Node> iterator = result.iterator();
    Node node;

    assertEquals(6, result.size());

    node = iterator.next();
    assertEquals("START", node.type().name());
    assertEquals("ABBR", node.getName());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    Assert.assertArrayEquals(new String[]{"expan"}, ((StartNode) node).getAttributeNames());
    assertEquals("xxxxx", ((StartNode) node).getAttribute("expan"));

    node = iterator.next();
    assertEquals("TEXT", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertEquals("a", node.getText());

    node = iterator.next();
    assertEquals("START", node.type().name());
    assertEquals("SUP", node.getName());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    Assert.assertArrayEquals(new String[]{}, ((StartNode) node).getAttributeNames());

    node = iterator.next();
    assertEquals("TEXT", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertEquals("b", node.getText());

    node = iterator.next();
    assertEquals("END", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertEquals("SUP", node.getName());

    node = iterator.next();
    assertEquals("END", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertEquals("ABBR", node.getName());

    checkLog();
  }

  /**
   * Test of expanded method with a simple abbr, of class AbbrNode.
   */
  @Test
  public void testExpandedNoCaret() {
    AbbrNode abbr = new AbbrNode();
    abbr.setText(("|ab|"));
    abbr.setAsl("3.2.1");
    abbr.setColumn(42);
    abbr.setLine(420);
    abbr.setTLN("11.3");

    Fragment result = abbr.expanded();
    Iterator<Node> iterator = result.iterator();
    Node node;

    assertEquals(3, result.size());

    node = iterator.next();
    assertEquals("START", node.type().name());
    assertEquals("ABBR", node.getName());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    Assert.assertArrayEquals(new String[]{"expan"}, ((StartNode) node).getAttributeNames());
    assertEquals("xxxxx", ((StartNode) node).getAttribute("expan"));

    node = iterator.next();
    assertEquals("TEXT", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertEquals("ab", node.getText());

    node = iterator.next();
    assertEquals("END", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertEquals("ABBR", node.getName());

    checkLog();
  }

  /**
   * Test of plain method, of class AbbrNode.
   */
  @Test
  public void testPlain() {
    AbbrNode abbr = new AbbrNode();

    abbr.setText(("|ab|"));
    assertEquals("ab", abbr.plain());

    abbr.setText(("|a^b|"));
    assertEquals("ab", abbr.plain());

    // yes, this really happens in the data.
    abbr.setText(("|{{s}t}^b|"));
    assertEquals("stb", abbr.plain());
  }

  /**
   * Test of unicode method, of class AbbrNode.
   */
  @Test
  public void testUnicode() {
    AbbrNode abbr = new AbbrNode();

    abbr.setText(("|ab|"));
    assertEquals("ab", abbr.unicode());

    abbr.setText(("|a^b|"));
    assertEquals("ab", abbr.unicode());

    // yes, this really happens in the data.
    abbr.setText(("|{{s}t}^b|"));
    assertEquals("stb", abbr.unicode());
  }

}
