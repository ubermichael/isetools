/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.node.attribute;

import ca.nines.ise.node.Attribute;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A case-insensitive, case-preserving, order-preserving collection of 
 * attributes.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class AttributeSet {
    private static final Logger logger = Logger.getLogger( AttributeSet.class.getName() );

    /**
     * Maps lowercase name to value, preserving insertion order.
     */
    private final Map<String, Attribute> attributes;
    
    public AttributeSet() {
        this.attributes = new LinkedHashMap<>();
    }
    
    public boolean hasAttribute(String name) {
        return attributes.containsKey(name.toLowerCase());
    }
    
    public void clearAttributes() {
        attributes.clear();
    }
    
    public void deleteAttribute(String name) {
        this.attributes.remove(name.toLowerCase());
    }
    
    public Attribute getAttribute(String name) {
        return this.attributes.get(name.toLowerCase());
    }
    
    public void setAttribute(String name, String value) {
		Attribute a = new Attribute();
		a.setName(name);
		a.setValue(value);
        this.setAttribute(a);
    }
	
	public void setAttribute(String name, String value, int line, int column) {
		Attribute a = new Attribute();
		a.setName(name);
		a.setValue(value);
		a.setLine(line);
		a.setColumn(column);
        this.setAttribute(a);
	}
    
    public void setAttribute(Attribute value) {
        this.attributes.put(value.getName(), value);
    }
    
    public int countAttributes() {
        return this.attributes.size();
    }
    
    public String[] getAttributeNames() {
        return getAttributeNames(false);
    }

    public String[] getAttributeNames(boolean originalCase) {
        int size = this.attributes.size();
        String[] names = this.attributes.keySet().toArray(new String[size]);
        for(int i = 0; i < size; i++) {
            names[i] = attributes.get(names[i]).getName(originalCase);
        }
        return names;
    }
    
    public Attribute[] getAttributes() {
        int size = this.attributes.size();
        Attribute[] list = new Attribute[size];
        String names[] = getAttributeNames(true);
        
        for(int i = 0; i < size; i++) {
            list[i] = new Attribute(this.attributes.get(names[i]));
        }
        return list;
    }

}
