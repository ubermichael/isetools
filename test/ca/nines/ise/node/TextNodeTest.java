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
public class TextNodeTest {
  
  public TextNodeTest() {
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
   * Test of type method, of class TextNode.
   */
  @Test
  public void testType() {
    System.out.println("type");
    TextNode instance = new TextNode();
    Node.NodeType expResult = null;
    Node.NodeType result = instance.type();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of expanded method, of class TextNode.
   */
  @Test
  public void testExpanded() {
    System.out.println("expanded");
    TextNode instance = new TextNode();
    Fragment expResult = null;
    Fragment result = instance.expanded();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of plain method, of class TextNode.
   */
  @Test
  public void testPlain() {
    System.out.println("plain");
    TextNode instance = new TextNode();
    String expResult = "";
    String result = instance.plain();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of unicode method, of class TextNode.
   */
  @Test
  public void testUnicode() {
    System.out.println("unicode");
    TextNode instance = new TextNode();
    String expResult = "";
    String result = instance.unicode();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
