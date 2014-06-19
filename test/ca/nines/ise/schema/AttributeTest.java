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
   * Test of getName method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
//  @Test
//  public void testGetName()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
//    System.out.println("getName");
//    Attribute instance = null;
//    String expResult = "";
//    String result = instance.getName();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of getType method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
//  @Test
//  public void testGetType()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
//    System.out.println("getType");
//    Attribute instance = null;
//    String expResult = "";
//    String result = instance.getType();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of isOptional method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
//  @Test
//  public void testIsOptional()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
//    System.out.println("isOptional");
//    Attribute instance = null;
//    boolean expResult = false;
//    boolean result = instance.isOptional();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of getDepreciated method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
//  @Test
//  public void testGetDepreciated()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
//    System.out.println("getDepreciated");
//    Attribute instance = null;
//    String expResult = "";
//    String result = instance.getDepreciated();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of isDepreciated method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
//  @Test
//  public void testIsDepreciated()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
//    System.out.println("isDepreciated");
//    Attribute instance = null;
//    boolean expResult = false;
//    boolean result = instance.isDepreciated();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of isRenumberable method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testIsRenumberable()   throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute a;
    
    a = new Attribute("<attribute name='foo' />");
    assertFalse(a.isRenumberable());
    
    a = new Attribute("<attribute name='foo' renumber='yes' />");
    assertFalse(a.isRenumberable());

    a = new Attribute("<attribute name='foo' type='number' />");
    assertFalse(a.isRenumberable());

    a = new Attribute("<attribute name='foo' type='number' renumber='yes' />");
    assertTrue(a.isRenumberable());
}

  /**
   * Test of getDefaultValue method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testGetDefaultValue()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute a;
    
    a = new Attribute("<attribute name='foo'/>");
    assertEquals("", a.getDefaultValue());
    
    a = new Attribute("<attribute name='foo' default='abc'/>");
    assertEquals("abc", a.getDefaultValue());
  }

  /**
   * Test of isEmpty method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testIsEmpty() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute a;
    
    a = new Attribute("<attribute name='foo'/>");
    assertFalse(a.isEmpty());
    a = new Attribute("<attribute name='foo' empty=''/>");
    assertFalse(a.isEmpty());
    a = new Attribute("<attribute name='foo' empty='foo'/>");
    assertFalse(a.isEmpty());
    a = new Attribute("<attribute name='foo' empty='yes'/>");
    assertTrue(a.isEmpty());
  }


  /**
   * Test of getOptions method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testGetOptions() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute a;
    
    a = new Attribute("<attribute name='foo'/>");
    assertArrayEquals(new String[0], a.getOptions());

    a = new Attribute("<attribute name='foo'><option>yes</option><option>no</option><option>maybe</option></attribute>");

    assertArrayEquals(new String[]{"maybe", "no", "yes"}, a.getOptions());
}

  /**
   * Test of getDescription method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testGetDescription() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute a;
    
    a = new Attribute("<attribute name='foo'/>");
    assertEquals("no description provided.", a.getDescription());
    
    a = new Attribute("<attribute name='foo'><desc>yes please</desc></attribute>");
    assertEquals("yes please", a.getDescription());
    
  }

  /**
   * Test of compareTo method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testCompareTo() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute a = new Attribute("<attribute name='foo'/>");
    Attribute b = new Attribute("<attribute name='boo'/>");
    
    assertTrue(0 < a.compareTo(b));
    assertEquals(0, a.compareTo(a));
    assertTrue(b.compareTo(a) < 0);
  }
  
}
