/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.validator.node.TestBase;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author michael
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

  public class TagNodeImpl extends TagNode {

    private TagNodeImpl() {
      super();
    }

    private TagNodeImpl(TagNode original) {
      super(original);
    }

    @Override
    public NodeType type() {
      throw new UnsupportedOperationException("Not supported yet."); // don't care.
    }
  }

}
