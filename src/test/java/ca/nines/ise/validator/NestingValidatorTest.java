/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.validator.node.ValidatorTestBase;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class NestingValidatorTest {
        
    private final Schema schema;

    public NestingValidatorTest() throws Exception {
        String loc = "/data/test-schema.xml";
        InputStream stream = ValidatorTestBase.class.getResourceAsStream(loc);
        schema = Schema.builder().from(loc, stream).build();
    }
    
    @Test
    public void testAllowedSplitting() {
        try {
            Log log = Log.getInstance();
            String s = "<WORK><MODE><PAGE></MODE></PAGE></WORK>";            
            DOM dom = new DOMBuilder(s).build();
            NestingValidator nv = new NestingValidator(schema);
            log.clear();
            nv.validate(dom);
            assertEquals(1, log.count());
            Message m = log.get(0);
            assertEquals("validator.nesting.split_tag", m.getCode());
        } catch (IOException ex) {
            Logger.getLogger(NestingValidatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testNotAllowedSplitting() {
        try {
            Log log = Log.getInstance();
            String s = "<WORK><ACT><SCENE></ACT></SCENE></WORK>";            
            DOM dom = new DOMBuilder(s).build();
            NestingValidator nv = new NestingValidator(schema);
            log.clear();
            nv.validate(dom);
            assertEquals(1, log.count());
            Message m = log.get(0);
            assertEquals("validator.nesting.split_tag", m.getCode());
        } catch (IOException ex) {
            Logger.getLogger(NestingValidatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testMixedSplitting() {
        try {
            String s = "";            
            DOM dom = new DOMBuilder(s).build();
        } catch (IOException ex) {
            Logger.getLogger(NestingValidatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
