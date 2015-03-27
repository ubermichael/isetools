package ca.nines.ise.normalizer;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.NodeFactory;
import nu.xom.Nodes;

public class NormalizingFactory extends NodeFactory {

	    private Nodes empty = new Nodes();
	    
	    //currently normalizing everything
	    public Nodes makeText(String data) {
	    	data = normalizeSpace(data);
	        if ("".equals(data)) return empty;
	        return super.makeText(data); 
	    }    
	    
	    //always normalize attributes
	    public Nodes makeAttribute(String name, String URI, 
	    	String value, Attribute.Type type) {
	        value = normalizeSpace(value);
	        return super.makeAttribute(name, URI, value, type);
	    }
	    
	    public Element startMakingElement(String name, String namespace){
	    	return super.startMakingElement(name,namespace);
	    }
	    
	    public Nodes finishMakingElement(Element e){
	    	return super.finishMakingElement(e);
	    }	    
	    
	    /**
	     * removes bounding white space (strings which are entirely whitespace)
	     * @param data
	     * @return
	     */
	    private static String normalizeBoundary(String data){
	    	String temp = normalizeSpace(data);
	    	if (temp.equals(" ") || temp.equals(""))
	    		return temp;
	    	return data;
	    }
	    
	    private static String normalizeSpace(String data) {
	        data = data.replace('\t', ' ');
	        data = data.replace('\n', ' ');
	        data = data.replace('\r', ' ');
	        data = data.trim();
	        
	        StringBuffer result = new StringBuffer();
	        for (int i = 0; i < data.length(); i++) {
	            if (i == 0 || data.charAt(i-1) != ' ' 
	              || data.charAt(i) != ' ') {
	                result.append(data.charAt(i));
	            }
	        }
	        
	        return result.toString();
	    }
}
