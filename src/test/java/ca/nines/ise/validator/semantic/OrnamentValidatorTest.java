/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.semantic;

import ca.nines.ise.validator.semantic.OrnamentValidator;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.log.Log;
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
public class OrnamentValidatorTest {
	Log log;
	
	@Before
	public void setUp() {
		log = Log.getInstance();
		log.clear();
	}

	/**
	 * Test of validate method, of class OrnamentValidator.
	 */
	@Test
	public void testValidateEmpty() throws Exception {
		
		DOM dom = new DOMBuilder("<ORNAMENT />").build();
		OrnamentValidator instance = new OrnamentValidator();
		instance.validate(dom);
		assertEquals(0, log.count());
	}
	
	/**
	 * Test of validate method, of class OrnamentValidator.
	 */
	@Test
	public void testValidateNonOrnament() throws Exception {
		
		DOM dom = new DOMBuilder("<SPAN>\ncontent</SPAN>").build();
		OrnamentValidator instance = new OrnamentValidator();
		instance.validate(dom);
		assertEquals(0, log.count());
	}
	
	/**
	 * Test of validate method, of class OrnamentValidator.
	 */
	@Test
	public void testValidateSingleLine() throws Exception {
		
		DOM dom = new DOMBuilder("<ORNAMENT>some stuff</ORNAMENT>").build();
		OrnamentValidator instance = new OrnamentValidator();
		instance.validate(dom);
		assertEquals(0, log.count());
	}
	
	/**
	 * Test of validate method, of class OrnamentValidator.
	 */
	@Test
	public void testValidateMultiline() throws Exception {
		
		DOM dom = new DOMBuilder("<ORNAMENT>some\nstuff</ORNAMENT>").build();
		OrnamentValidator instance = new OrnamentValidator();
		instance.validate(dom);
		assertEquals(1, log.count());
	}
	
	/**
	 * Test of validate method, of class OrnamentValidator.
	 */
	@Test
	public void testValidateMissingEnd() throws Exception {
		
		DOM dom = new DOMBuilder("<ORNAMENT>some\nstuff<ORNAMENT>").build();
		OrnamentValidator instance = new OrnamentValidator();
		instance.validate(dom);
		assertEquals(2, log.count()); // unclosed ornament, unclosed ornament.
	}
	
	/**
	 * Test of validate method, of class OrnamentValidator.
	 */
	@Test
	public void testValidateOrnamentAtEnd() throws Exception {
		
		DOM dom = new DOMBuilder("some\nstuff<ORNAMENT>").build();
		OrnamentValidator instance = new OrnamentValidator();
		instance.validate(dom);
		assertEquals(1, log.count());
	}
}
