/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.Fragment;
import java.io.IOException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michael
 */
public class Attribute extends Node {
    
    private static final Logger logger = Logger.getLogger(Attribute.class.getName());
    
    private String name;
    
    private String value;
	
   public Attribute() {
        this.name = null;
		this.value = null;
		this.column = 0;
		this.line = 0;
    }
	
	public Attribute(Attribute a) {
		this.name = a.name;
		this.value = a.value;
		this.column = a.column;
		this.line = a.line;
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

	@Override
	public Fragment expanded() throws IOException {
		throw new UnsupportedOperationException("Attributes cannot be expanded to DOM fragments.");
	}

	@Override
	public String plain() throws IOException {
		if(value.indexOf('{') == -1) {
			return value;
		}
        try {
            return new DOMBuilder(value).build().plain();
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Cannot parse attribute value", e);
            return value;
        }
	}

	@Override
	public NodeType type() {
		return NodeType.ATTR;
	}

	@Override
	public String unicode() throws IOException {
		if(value.indexOf('{') == -1) {
			return value;
		}
        try {
            return new DOMBuilder(value).build().unicode();
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Cannot parse attribute value", e);
            return value;
        }
	}

	@Override
	public String sgml() {
		throw new UnsupportedOperationException("Attributes cannot be expanded to SGML.");
	}
	
	public String toString() {
		Formatter formatter = new Formatter();
		return formatter.format("#%s:%d:%d:@%s=%s", this.type(), line, column, name, value).toString();
	}
    
}
