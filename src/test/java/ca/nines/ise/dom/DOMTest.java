/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.dom;

import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.node.TextNode;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class DOMTest {

	/**
	 * Test of add method, of class DOM.
	 */
	@Test
	public void testAdd() {
		Node n = null;
		DOM instance = new DOM();
		instance.add(n);
		assertEquals(1, instance.size());
	}

	/**
	 * Test of addAll method, of class DOM.
	 *
	 * @throws java.io.IOException
	 */
	@Test
	public void testAddAll() throws IOException {
		DOM dom = new DOMBuilder("<a>text</a>").build();
		DOM instance = new DOM();
		instance.addAll(dom);
		assertEquals(3, instance.size());
	}

	/**
	 * Test of remove method, of class DOM.
	 */
	@Test
	public void testRemove() {
		EndNode n = new EndNode();
		DOM instance = new DOM();
		instance.add(n);
		instance.remove(n);
		assertEquals(0, instance.size());
	}

	/**
	 * Test of replace method, of class DOM.
	 */
	@Test
	public void testReplace() {
		Node n = new StartNode();
		Node e = new EndNode();
		DOM instance = new DOM();
		instance.add(n);
		instance.replace(n, e);
		assertEquals("EndNode", instance.get(0).getClass().getSimpleName());
	}

	/**
	 * Test of expanded method, of class DOM.
	 */
	@Test
	public void testExpanded() throws Exception {
		DOM instance = new DOMBuilder("<a>text {s} etc.</a>").build();
		DOM result = instance.expanded();
		//<a>text <UNICODE setting="{s}">ſ</UNICODE> etc.</a>
		assertEquals(7, result.size());
	}

	/**
	 * Test of get method, of class DOM.
	 */
	@Test
	public void testGet() {
		Node n = new StartNode();
		Node e = new EndNode();
		DOM instance = new DOM();
		instance.add(n);
		instance.add(e);
		assertEquals("EndNode", instance.get(1).getClass().getSimpleName());
		assertEquals("StartNode", instance.get(0).getClass().getSimpleName());
	}

	/**
	 * Test of getLine method, of class DOM.
	 * @throws java.io.IOException
	 */
	@Test
	public void testGetLine() throws IOException {
		DOM instance = new DOMBuilder("<a>text\n{s}\netc.</a>").build();
		assertEquals("<a>text", instance.getLine(0));
		assertEquals("{s}", instance.getLine(1));
		assertEquals("etc.</a>", instance.getLine(2));
	}

	/**
	 * Test of getLineFragment method, of class DOM.
	 * @throws java.io.IOException
	 */
	@Test
	public void testGetLineFragment() throws IOException {
		int n = 0;
		DOM instance =  new DOMBuilder("<a>text\n{s}\netc.</a>").build();
		assertEquals(3, instance.getLineFragment(1).size());
		assertEquals(2, instance.getLineFragment(2).size());
		assertEquals(2, instance.getLineFragment(3).size());
	}

	/**
	 * Test of getLines method, of class DOM.
	 * @throws java.io.IOException
	 */
	@Test
	public void testGetLines() throws IOException {
		DOM instance =  new DOMBuilder("<a>text\n{s}\netc.</a>").build();
		String[] expResult = new String[]{"<a>text", "{s}", "etc.</a>"};
		String[] result = instance.getLines();
		assertArrayEquals(expResult, result);
	}

	/**
	 * Test of getSource method, of class DOM.
	 * @throws java.io.IOException
	 */
	@Test
	public void testGetSource() throws IOException {
		DOM instance = new DOMBuilder("<a>text\n{s}\netc.</a>").build();
		String expResult = "#STRING";
		String result = instance.getSource();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getStatus method, of class DOM.
	 * @throws java.io.IOException
	 */
	@Test
	public void testGetStatus() throws IOException {
		DOM instance = new DOMBuilder("<a>text\n{s}\netc.</a>").build();
		DOM.DOMStatus expResult = DOM.DOMStatus.CLEAN;
		DOM.DOMStatus result = instance.getStatus();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getTln method, of class DOM.
	 * @throws java.io.IOException
	 */
	@Test
	public void testGetTln() throws IOException {
		String tln = "";
		DOM instance = new DOMBuilder("<tln n='1'>text\n<tln n='3'>{s}etc.</a>").build();
		TagNode n = (TagNode) instance.getTln("1");
		assertEquals("tln", n.getName());
		assertEquals(1, n.getLine());
	}

	/**
	 * Test of getTlnFragment method, of class DOM.
	 * @throws java.io.IOException
	 */
	@Test
	public void testGetTlnFragment() throws IOException {
		DOM instance = new DOMBuilder("<tln n='1'>text\n<tln n='3'>{s}etc.</a><tln n='4'>").build();
		assertEquals(3, instance.getTlnFragment("1", 1).size());
		assertEquals(4, instance.getTlnFragment("3", 1).size());
	}

	/**
	 * Test of find_forward method, of class DOM.
	 */
	@Test
	public void testFind_forward() throws IOException {
		DOM instance = new DOMBuilder("<tln n='1'>text\n<tln n='3'>{s}<a id='dd'>etc.</a><tln n='4'>").build();
		Node tln = instance.getTln("3");		
		Node result = instance.findForward(tln, "a");
		assertNotNull(result);
		assertEquals("dd", ((TagNode)result).getAttribute("id"));
	}
	
	/**
	 * Test of plain method, of class DOM.
	 */
	@Test
	public void testPlain() throws Exception {
		DOM instance = new DOMBuilder("<tln n='1'>text\n<tln n='3'>{s}<a id='dd'>etc.</a><tln n='4'>").build();
		String expResult = "text\nsetc.";
		String result = instance.plain();
		assertEquals(expResult, result);
	}

	/**
	 * Test of splitTextNode method, of class DOM.
	 * @throws java.io.IOException
	 */
	@Test
	public void testSplitTextNode() throws IOException {
		DOM instance = new DOMBuilder("This is a text node.").build();
		TextNode tn = (TextNode)instance.get(0);
		TextNode n = new TextNode();
		n.setText("great ");
		instance.splitTextNode(tn, 10, n);
		assertEquals(3, instance.size());
		assertEquals("This is a great text node.", instance.plain());
	}
	
	/**
	 * Test of unicode method, of class DOM.
	 */
	@Test
	public void testUnicode() throws Exception {
		DOM instance = new DOMBuilder("<a>Cres{s}ida.").build();
		String expResult = "Cresſida.";
		String result = instance.unicode();
		assertEquals(expResult, result);
	}
}
