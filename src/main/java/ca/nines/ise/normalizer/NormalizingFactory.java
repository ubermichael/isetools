/*
 * Copyright (C) 2014 Malcolm Moran <malcolm.moran@outlook.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package ca.nines.ise.normalizer;

import ca.nines.ise.constants.XML;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.NodeFactory;
import nu.xom.Nodes;
import nu.xom.Text;

public class NormalizingFactory extends NodeFactory {
	
		Boolean in_line = false;
	    
	    //don't normalize within line
	    public Nodes makeText(String data) {
	    	//if (in_line)
	    		//return super.makeText(data);
	    	data = normalizeSpace(data);
	        if ("".equals(data)) return new Nodes();
	        return super.makeText(data); 
	    }    
	    
	    //always normalize attributes
	    public Nodes makeAttribute(String name, String URI, 
	    	String value, Attribute.Type type) {
	        value = normalizeSpace(value);
	        return super.makeAttribute(name, URI, value, type);
	    }
	    
	    public Element startMakingElement(String name, String namespace){
	    	if (name.equals(XML.LINE))
	    		in_line = true;
	    	return super.startMakingElement(name,namespace);
	    }
	    
	    public Nodes finishMakingElement(Element e){
	    	/*if (e.getLocalName().equals(XML.LINE)){
	    		in_line = false;
	    		int count = e.getChildCount();
	    		if (count <= 1)
	    			return super.finishMakingElement(e);
	    		else{
	    			Text x;
	    			//if first child of line is text, normalize if boundary
	    			if (e.getChild(0) instanceof Text){
	    				x = (Text)e.getChild(0);
	    				x.setValue(normalizeBoundary(x.getValue()));
	    			}
	    			//if last child of line is text, normalize if boundary
	    			if (e.getChild(count-1) instanceof Text){
	    				x = (Text)e.getChild(count-1);
	    				x.setValue(normalizeBoundary(x.getValue()));
	    			}
	    		}
	    	}*/
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
