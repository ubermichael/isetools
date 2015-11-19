/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.node;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
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
     * Maps lowercase name to original name.
     */
    private final Map<String, String> originalNames;
    
    /**
     * Maps lowercase name to value, preserving insertion order.
     */
    private final Map<String, String> attributes;
    
    public AttributeSet() {
        this.originalNames = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.attributes = new LinkedHashMap<>();
    }
    
    public boolean hasAttribute(String name) {
        return originalNames.containsKey(name);
    }
    
    public void clearAttributes() {
        originalNames.clear();
        attributes.clear();
    }
    
    public void deleteAttribute(String name) {
        this.originalNames.remove(name);
        this.attributes.remove(name.toLowerCase());
    }
    
    public String getAttribute(String name) {
        return this.attributes.get(name.toLowerCase());
    }
    
    public void setAttribute(String name, String value) {
        this.originalNames.put(name, name);
        this.attributes.put(name.toLowerCase(), value);
    }
    
    public int countAttributes() {
        return this.attributes.size();
    }
    
    public String[] getAttributeNames() {
        int size = this.attributes.size();
        String[] names = this.attributes.keySet().toArray(new String[size]);
        for(int i = 0; i < size; i++) {
            names[i] = originalNames.get(names[i]);
        }
        return names;
    }

}
