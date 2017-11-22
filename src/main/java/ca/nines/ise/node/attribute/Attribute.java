/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.attribute;

import ca.nines.ise.dom.DOMBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *

 */
public class Attribute {
    
    private static final Logger logger = Logger.getLogger(Attribute.class.getName());
    
    private String name;
    
    private String value;
    
    public Attribute() {
        
    }
    
    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName(boolean originalCase) {
        if(originalCase) {
            return name;
        }
        return name.toLowerCase();
    }
    
    public String getName() {
        return getName(false);
    }
    
    public String getValue() {
        return value;
    }
    
    public String getValue(boolean unicodify) throws IOException {
        if( ! unicodify || getValue().indexOf('{') == -1) {
            return value;
        }
        // value may include a curly markup. 
        try {
            return new DOMBuilder(value).build().unicode();
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Cannot parse attribute value", e);
            return value;
        }
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    
}
