/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.attribute;

import ca.nines.ise.node.Attribute;
import ca.nines.ise.node.attribute.AttributeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class AttributeSetTest {
    
    public AttributeSetTest() {
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
     * Test of hasAttribute method, of class AttributeSet.
     */
    @Test
    public void testHasAttribute() {
        AttributeSet instance = new AttributeSet();
        Attribute a = new Attribute();
		a.setName("foo");
		a.setValue("bar");
        instance.setAttribute(a);
        assertTrue(instance.hasAttribute("foo"));
        assertTrue(instance.hasAttribute("FOO"));
        assertFalse(instance.hasAttribute("rama"));
    }

    /**
     * Test of clearAttributes method, of class AttributeSet.
     */
    @Test
    public void testCountAttributes() {
        AttributeSet instance = new AttributeSet();
        assertEquals(0, instance.countAttributes());
        instance.setAttribute("foo", "bar");
        assertEquals(1, instance.countAttributes());
        instance.setAttribute("FOO", "bar");
        assertEquals(1, instance.countAttributes());
    }

    /**
     * Test of clearAttributes method, of class AttributeSet.
     */
    @Test
    public void testClearAttributes() {
        AttributeSet instance = new AttributeSet();
        instance.setAttribute("foo", "bar");
        assertTrue(instance.hasAttribute("foo"));
        instance.clearAttributes();
        assertEquals(0, instance.countAttributes());
    }

    /**
     * Test of deleteAttribute method, of class AttributeSet.
     */
    @Test
    public void testDeleteAttribute() {
        AttributeSet instance = new AttributeSet();
        instance.setAttribute("foo", "bar");
        assertEquals(1, instance.countAttributes());
        instance.deleteAttribute("foo");
        assertEquals(0, instance.countAttributes());
    }

    /**
     * Test of case-insensitive deleteAttribute method, of class AttributeSet.
     */
    @Test
    public void testCaseInsensitiveDeleteAttribute() {
        AttributeSet instance = new AttributeSet();
        instance.setAttribute("foo", "bar");
        assertEquals(1, instance.countAttributes());
        instance.deleteAttribute("FOO");
        assertEquals(0, instance.countAttributes());
    }

    /**
     * Test of getAttribute method, of class AttributeSet.
     */
    @Test
    public void testGetAttribute() {
        AttributeSet instance = new AttributeSet();
        instance.setAttribute("foo", "bar");
        assertEquals("bar", instance.getAttribute("foo").getValue());
        assertEquals("bar", instance.getAttribute("FOO").getValue());
        assertEquals(null, instance.getAttribute("FO"));
    }

    /**
     * Test of setAttribute method, of class AttributeSet.
     */
    @Test
    public void testSetAttribute() {
        AttributeSet instance = new AttributeSet();
        instance.setAttribute("foo", "bar");
        assertEquals("bar", instance.getAttribute("foo").getValue());
        assertEquals("bar", instance.getAttribute("FOO").getValue());
        assertEquals(1, instance.countAttributes());

        instance.setAttribute("FOO", "bzr");
        assertEquals("bzr", instance.getAttribute("foo").getValue());
        assertEquals("bzr", instance.getAttribute("FOO").getValue());
        assertEquals(1, instance.countAttributes());
    }

    /**
     * Test of getAttributeNames method, of class AttributeSet.
     */
    @Test
    public void testGetAttributeNames() {
        String names[] = {"foo", "a", "yes", "FOO", "A"};
        String namesCI[] = {"FOO", "A", "yes"};
        AttributeSet instance = new AttributeSet();
        for (String n : names) {
            instance.setAttribute(n, "a");
        }
        assertArrayEquals(namesCI, instance.getAttributeNames(true));
    }
    
}
