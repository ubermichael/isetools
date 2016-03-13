/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.attribute;

import ca.nines.ise.node.Attribute;
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
     */
    @Test
    public void testGetName_boolean() {        
        Attribute instance = new Attribute();
        instance.setName("Foo");
        assertEquals("Foo", instance.getName(true));
        assertEquals("foo", instance.getName(false));
    }

    /**
     * Test of getName method, of class Attribute.
     */
    @Test
    public void testGetName() {
        Attribute instance = new Attribute();
        instance.setName("Foo");
        assertEquals("foo", instance.getName());
    }

    /**
     * Test of getValue method, of class Attribute.
     */
    @Test
    public void testGetValue_simple_0args() throws Exception {
        Attribute instance = new Attribute();
        instance.setValue("simple attribute.");
        assertEquals("simple attribute.", instance.getValue());
    }

    /**
     * Test of getValue method, of class Attribute.
     */
    @Test
    public void testGetValue_complex_0args() throws Exception {
        Attribute instance = new Attribute();
        instance.setValue("simple {s}imon.");
        assertEquals("simple {s}imon.", instance.getValue());
    }

    /**
     * Test of getValue method, of class Attribute.
     */
    @Test
    public void testGetValue_complex_boolean() throws Exception {
        Attribute instance = new Attribute();
        instance.setValue("simple {s}imon.");
        assertEquals("simple ſimon.", instance.getValue(true));
        assertEquals("simple {s}imon.", instance.getValue(false));
    }

}
