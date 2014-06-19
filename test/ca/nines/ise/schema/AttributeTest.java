/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.schema;

import ca.nines.ise.util.XMLReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class AttributeTest {

  /**
   * Test of getName method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testGetName()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {    
    Attribute a;
    
    a = new Attribute("<attribute name='foo' />");
    assertEquals("foo", a.getName());
    
    XMLReader xmlIn = new XMLReader("<tag><attribute name='foo'/></tag>");
    Node n = xmlIn.xpathNode("/tag/attribute");
    a = new Attribute(n);
    assertEquals("foo", a.getName());
    
    n = xmlIn.xpathNode("/tag/attribute");
    a = new Attribute(n, xmlIn);
    assertEquals("foo", a.getName());
  }

  /**
   * Test of getType method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testGetType()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute a;
    
    a = new Attribute("<attribute type='string' />");
    assertEquals("string", a.getType());
  }

  /**
   * Test of isOptional method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testIsOptional()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute a;
    
    a = new Attribute("<attribute />");
    assertFalse(a.isOptional());
    
    a = new Attribute("<attribute optional='' />");
    assertFalse(a.isOptional());

    a = new Attribute("<attribute optional='because' />");
    assertFalse(a.isOptional());

    a = new Attribute("<attribute optional='yes' />");
    assertTrue(a.isOptional());
  }

  /**
   * Test of isDepreciated method, of class Attribute.
   * @throws javax.xml.xpath.XPathExpressionException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  @Test
  public void testDepreciated()  throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    Attribute a;
    
    a = new Attribute("<attribute />");
    assertFalse(a.isDepreciated());
    assertEquals("", a.getDepreciated());
    
    a = new Attribute("<attribute depreciated='' />");
    assertFalse(a.isDepreciated());
    assertEquals("", a.getDepreciated());

    a = new Attribute("<attribute depreciated='because of reasons.' />");
    assertTrue(a.isDepreciated());
    assertEquals("because of reasons.", a.getDepreciated());  
  }

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
    
    a = new Attribute("<attribute />");
    assertFalse(a.isRenumberable());
    
    a = new Attribute("<attribute renumber='yes' />");
    assertFalse(a.isRenumberable());

    a = new Attribute("<attribute type='number' />");
    assertFalse(a.isRenumberable());

    a = new Attribute("<attribute type='number' renumber='yes' />");
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
    
    a = new Attribute("<attribute/>");
    assertEquals("", a.getDefaultValue());
    
    a = new Attribute("<attribute default='abc'/>");
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
    
    a = new Attribute("<attribute/>");
    assertFalse(a.isEmpty());
    a = new Attribute("<attribute empty=''/>");
    assertFalse(a.isEmpty());
    a = new Attribute("<attribute empty='foo'/>");
    assertFalse(a.isEmpty());
    a = new Attribute("<attribute empty='yes'/>");
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
    
    a = new Attribute("<attribute/>");
    assertArrayEquals(new String[0], a.getOptions());

    a = new Attribute("<attribute><option>yes</option><option>no</option><option>maybe</option></attribute>");

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
    
    a = new Attribute("<attribute/>");
    assertEquals("no description provided.", a.getDescription());
    
    a = new Attribute("<attribute><desc>yes please</desc></attribute>");
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
