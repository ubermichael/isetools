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

import ca.nines.ise.validator.node.TestBase;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class TagNodeTest extends TestBase {

  /**
   * Test of clearAttributes method, of class TagNode.
   */
  @Test
  public void testClearAttributes() {
    TagNode instance = new TagNodeImpl();
    instance.setAttribute("foo", "bar");
    instance.clearAttributes();
    assertArrayEquals(new String[]{}, instance.getAttributeNames());
  }

  /**
   * Test of getAttribute method, of class TagNode.
   */
  @Test
  public void testGetAttribute() {
    TagNode instance = new TagNodeImpl();
    instance.setAttribute("foo", "bar");
    assertEquals("bar", instance.getAttribute("foo"));
    assertEquals("bar", instance.getAttribute("FOO"));
    assertEquals(null, instance.getAttribute("f"));
  }

  /**
   * Test of getAttribute method, of class TagNode.
   */
  @Test
  public void testGetAttributeEncoded() {
    TagNode instance = new TagNodeImpl();
    instance.setAttribute("foo", "super {s}imple");
    assertEquals("super {s}imple", instance.getAttribute("foo"));
    assertEquals("super ſimple", instance.getAttribute("foo", true));
  }

  /**
   * Test of setAttribute method, of class TagNode.
   */
  @Test
  public void testSetAttribute() {
    TagNode instance = new TagNodeImpl();
    instance.setAttribute("foo", "bar");
    assertEquals("bar", instance.getAttribute("foo"));
    instance.setAttribute("FOO", "chicanery");
    assertEquals("chicanery", instance.getAttribute("FOO"));
    assertEquals("chicanery", instance.getAttribute("foo"));
  }

  /**
   * Test of getAttributeNames method, of class TagNode.
   */
  @Test
  public void testGetAttributeNames() {
    String names[] = {"foo", "a", "yes"};
    TagNode instance = new TagNodeImpl();
    for (String n : names) {
      instance.setAttribute(n, "a");
    }
    Arrays.sort(names);
    assertArrayEquals(names, instance.getAttributeNames());
  }

  /**
   * Test of getAttributeNames method, of class TagNode.
   */
  @Test
  public void testCopyAttributes() {
    String names[] = {"foo", "a", "yes"};
    TagNode original = new TagNodeImpl();
    for (String n : names) {
      original.setAttribute(n, "a");
    }
    TagNode duplicate = new TagNodeImpl(original);
    Arrays.sort(names);
    assertArrayEquals(names, duplicate.getAttributeNames());

    original.setAttribute("foo", "b");
    assertEquals("a", duplicate.getAttribute("foo"));
  }

}
