/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.schema;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class AttributeTest {
  
  public AttributeTest() {
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
   * Test of toString method, of class Attribute.
   */
  @Test
  public void testToString() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute instance = new Attribute("<attribute name='at' type='string' empty='yes' optional='yes' />");
    String expResult = "name:";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

//  /**
//   * Test of getName method, of class Attribute.
//   */
//  @Test
//  public void testGetName() {
//    System.out.println("getName");
//    Attribute instance = null;
//    String expResult = "";
//    String result = instance.getName();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getType method, of class Attribute.
//   */
//  @Test
//  public void testGetType() {
//    System.out.println("getType");
//    Attribute instance = null;
//    String expResult = "";
//    String result = instance.getType();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of isOptional method, of class Attribute.
//   */
//  @Test
//  public void testIsOptional() {
//    System.out.println("isOptional");
//    Attribute instance = null;
//    boolean expResult = false;
//    boolean result = instance.isOptional();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getDepreciated method, of class Attribute.
//   */
//  @Test
//  public void testGetDepreciated() {
//    System.out.println("getDepreciated");
//    Attribute instance = null;
//    String expResult = "";
//    String result = instance.getDepreciated();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of isDepreciated method, of class Attribute.
//   */
//  @Test
//  public void testIsDepreciated() {
//    System.out.println("isDepreciated");
//    Attribute instance = null;
//    boolean expResult = false;
//    boolean result = instance.isDepreciated();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getMatch method, of class Attribute.
//   */
//  @Test
//  public void testGetMatch() {
//    System.out.println("getMatch");
//    Attribute instance = null;
//    String expResult = "";
//    String result = instance.getMatch();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of isRenumberable method, of class Attribute.
//   */
//  @Test
//  public void testIsRenumberable() {
//    System.out.println("isRenumberable");
//    Attribute instance = null;
//    boolean expResult = false;
//    boolean result = instance.isRenumberable();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getDefaultValue method, of class Attribute.
//   */
//  @Test
//  public void testGetDefaultValue() {
//    System.out.println("getDefaultValue");
//    Attribute instance = null;
//    String expResult = "";
//    String result = instance.getDefaultValue();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of isEmpty method, of class Attribute.
//   */
//  @Test
//  public void testIsEmpty() {
//    System.out.println("isEmpty");
//    Attribute instance = null;
//    boolean expResult = false;
//    boolean result = instance.isEmpty();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getOptions method, of class Attribute.
//   */
//  @Test
//  public void testGetOptions() {
//    System.out.println("getOptions");
//    Attribute instance = null;
//    String[] expResult = null;
//    String[] result = instance.getOptions();
//    assertArrayEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getDescription method, of class Attribute.
//   */
//  @Test
//  public void testGetDescription() {
//    System.out.println("getDescription");
//    Attribute instance = null;
//    String expResult = "";
//    String result = instance.getDescription();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of compareTo method, of class Attribute.
//   */
//  @Test
//  public void testCompareTo() {
//    System.out.println("compareTo");
//    Attribute a = null;
//    Attribute instance = null;
//    int expResult = 0;
//    int result = instance.compareTo(a);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
  
}
