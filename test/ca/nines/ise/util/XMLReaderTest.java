/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

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
public class XMLReaderTest {

  /**
   * Test of xpathList method, of class XMLReader.
   */
  @Test
  public void testXpathList_String() throws Exception {
    XMLReader xmlIn;
    Node[] nl;
    
    xmlIn = new XMLReader("<foo><bar n='1'/><bar n='2'/></foo>");
    nl = xmlIn.xpathList("/foo/bar");
    assertEquals(2, nl.length);
  }

  /**
   * Test of xpathList method, of class XMLReader.
   */
  @Test
  public void testXpathNode_String() throws Exception {
    XMLReader xmlIn;
    Node n;
    
    xmlIn = new XMLReader("<foo><bar n='1'/><qux n='2'/></foo>");
    n = xmlIn.xpathNode("/foo/node()[position() = 1]");
    
    assertEquals("bar", n.getNodeName());
    
    n = xmlIn.xpathNode("/foo/barley");
    assertEquals(null, n);
  }

  /**
   * Test of xpathList method, of class XMLReader.
   */
  @Test
  public void testXpathNode_String_Node() throws Exception {
    XMLReader xmlIn;
    Node a, n;
    
    xmlIn = new XMLReader("<foo><a><bar n='1'/><qux n='2'/></a></foo>");
    a = xmlIn.xpathNode("/foo/a");
    assertNotNull(a);
    n = xmlIn.xpathNode("*[@n=1]", a);
    assertNotNull(n);
    assertEquals("bar", n.getNodeName());
    n = xmlIn.xpathNode("*[@n=5]", a);
    assertNull(n);
  }

  /**
   * Test of xpathString method, of class XMLReader.
   */
  @Test
  public void testXpathString_String() throws Exception {
    XMLReader xmlIn;
    
    xmlIn = new XMLReader("<foo bar='3'/>");
    assertEquals("3", xmlIn.xpathString("/foo/@bar"));
    assertEquals("", xmlIn.xpathString("/foo/@blorp"));
  }

  /**
   * Test of xpathString method, of class XMLReader.
   */
  @Test
  public void testXpathString_String_Node() throws Exception {
    XMLReader xmlIn;
    
    xmlIn = new XMLReader("<foo><bar a='3'/></foo>");
    Node n = xmlIn.xpathNode("/foo/bar");
    
    assertEquals("3", xmlIn.xpathString("@a", n));
    assertEquals("", xmlIn.xpathString("@blorp", n));
  }
}