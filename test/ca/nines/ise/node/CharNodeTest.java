/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import java.io.IOException;
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
public class CharNodeTest {

  public CharNodeTest() {
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
   * Test of getCharType method, of class CharNode.
   */
  @Test
  public void testGetCharType() {
    System.out.println("getCharType");
    CharNode instance = new CharNodeImpl();
    CharNode.CharType expResult = null;
    CharNode.CharType result = instance.getCharType();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCharName method, of class CharNode.
   */
  @Test
  public void testGetCharName() {
    System.out.println("getCharName");
    CharNode instance = new CharNodeImpl();
    String expResult = "";
    String result = instance.getCharName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of innerText method, of class CharNode.
   */
  @Test
  public void testInnerText() {
    System.out.println("innerText");
    CharNode instance = new CharNodeImpl();
    String expResult = "";
    String result = instance.innerText();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of type method, of class CharNode.
   */
  @Test
  public void testType() {
    System.out.println("type");
    CharNode instance = new CharNodeImpl();
    Node.NodeType expResult = null;
    Node.NodeType result = instance.type();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class CharNode.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    CharNode instance = new CharNodeImpl();
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of unicode method, of class CharNode.
   */
  @Test
  public void testUnicode() throws Exception {
    System.out.println("unicode");
    CharNode instance = new CharNodeImpl();
    String expResult = "";
    String result = instance.unicode();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of plain method, of class CharNode.
   */
  @Test
  public void testPlain() throws Exception {
    System.out.println("plain");
    CharNode instance = new CharNodeImpl();
    String expResult = "";
    String result = instance.plain();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
