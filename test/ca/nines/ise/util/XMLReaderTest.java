/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.util;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class XMLReaderTest {
  
  public XMLReaderTest() {
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

//  /**
//   * Test of xpathList method, of class XMLReader.
//   */
//  @Test
//  public void testXpathList_String() throws Exception {
//    System.out.println("xpathList");
//    String xp = "";
//    XMLReader instance = null;
//    Node[] expResult = null;
//    Node[] result = instance.xpathList(xp);
//    assertArrayEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of xpathList method, of class XMLReader.
//   */
//  @Test
//  public void testXpathList_String_Node() throws Exception {
//    System.out.println("xpathList");
//    String xp = "";
//    Node node = null;
//    XMLReader instance = null;
//    Node[] expResult = null;
//    Node[] result = instance.xpathList(xp, node);
//    assertArrayEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of xpathString method, of class XMLReader.
//   */
//  @Test
//  public void testXpathString_String() throws Exception {
//    System.out.println("xpathString");
//    String xp = "";
//    XMLReader instance = null;
//    String expResult = "";
//    String result = instance.xpathString(xp);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of xpathString method, of class XMLReader.
//   */
//  @Test
//  public void testXpathString_String_Node() throws Exception {
//    System.out.println("xpathString");
//    String xp = "";
//    Node node = null;
//    XMLReader instance = null;
//    String expResult = "";
//    String result = instance.xpathString(xp, node);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of xpathBoolean method, of class XMLReader.
//   */
//  @Test
//  public void testXpathBoolean_String() throws Exception {
//    System.out.println("xpathBoolean");
//    String xp = "";
//    XMLReader instance = null;
//    boolean expResult = false;
//    boolean result = instance.xpathBoolean(xp);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of xpathBoolean method, of class XMLReader.
//   */
//  @Test
//  public void testXpathBoolean_String_Node() throws Exception {
//    System.out.println("xpathBoolean");
//    String xp = "";
//    Node node = null;
//    XMLReader instance = null;
//    boolean expResult = false;
//    boolean result = instance.xpathBoolean(xp, node);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
  /**
   * Test of attrValue method, of class XMLReader.
   */
  @Test
  public void testAttrValue_String() throws ParserConfigurationException, SAXException, IOException {
    String attrName = "bar";
    XMLReader instance = new XMLReader("<foo bar='3'/>");
    String expResult = "3";
    String result = instance.attrValue(attrName);
    assertEquals(expResult, result);
  }
//
//  /**
//   * Test of attrValue method, of class XMLReader.
//   */
//  @Test
//  public void testAttrValue_String_Node() {
//    System.out.println("attrValue");
//    String attrName = "";
//    Node node = null;
//    XMLReader instance = null;
//    String expResult = "";
//    String result = instance.attrValue(attrName, node);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
  
}
