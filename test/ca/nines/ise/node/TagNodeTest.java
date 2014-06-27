/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class TagNodeTest {
  
  public TagNodeTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of setName method, of class TagNode.
   */
  @Test
  public void testSetName() {
    System.out.println("setName");
    String name = "";
    TagNode instance = new TagNodeImpl();
    String expResult = "";
    String result = instance.setName(name);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getName method, of class TagNode.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");
    TagNode instance = new TagNodeImpl();
    String expResult = "";
    String result = instance.getName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of expanded method, of class TagNode.
   */
  @Test
  public void testExpanded() {
    System.out.println("expanded");
    TagNode instance = new TagNodeImpl();
    Fragment expResult = null;
    Fragment result = instance.expanded();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of plain method, of class TagNode.
   */
  @Test
  public void testPlain() {
    System.out.println("plain");
    TagNode instance = new TagNodeImpl();
    String expResult = "";
    String result = instance.plain();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of unicode method, of class TagNode.
   */
  @Test
  public void testUnicode() {
    System.out.println("unicode");
    TagNode instance = new TagNodeImpl();
    String expResult = "";
    String result = instance.unicode();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class TagNode.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    TagNode instance = new TagNodeImpl();
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of clearAttributes method, of class TagNode.
   */
  @Test
  public void testClearAttributes() {
    System.out.println("clearAttributes");
    TagNode instance = new TagNodeImpl();
    instance.clearAttributes();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAttribute method, of class TagNode.
   */
  @Test
  public void testGetAttribute() {
    System.out.println("getAttribute");
    String name = "";
    TagNode instance = new TagNodeImpl();
    String expResult = "";
    String result = instance.getAttribute(name);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setAttribute method, of class TagNode.
   */
  @Test
  public void testSetAttribute() {
    System.out.println("setAttribute");
    String name = "";
    String value = "";
    TagNode instance = new TagNodeImpl();
    instance.setAttribute(name, value);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAttributeNames method, of class TagNode.
   */
  @Test
  public void testGetAttributeNames() {
    System.out.println("getAttributeNames");
    TagNode instance = new TagNodeImpl();
    String[] expResult = null;
    String[] result = instance.getAttributeNames();
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  public class TagNodeImpl extends TagNode {

    @Override
    public NodeType type() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  }
  
}
