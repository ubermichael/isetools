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
package ca.nines.ise.writer;


import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.constants.*;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Text;

public class XMLWriter extends Writer{
	
	  private static final String[] RENEWABLE = {XML.S, XML.TITLE, XML.MS};
	  
	  public class XMLStack extends LinkedList<Element>{
		  	Document xml;
		  	private LinkedList<Element> renewing;
		  	private Hashtable<String, Boolean> page_children;
		  	private Boolean endSplitLine;
		  	private String align;
		  	
		  	public XMLStack(Document xml) throws ParserConfigurationException{
		  		this.xml = xml;
		  		renewing = new LinkedList<Element>();
		  		page_children = new Hashtable<String, Boolean>();
		  		endSplitLine = false;
		  		align = null;
		  	}
		  	
		  	
		  	/*renew methods*/
		  	
			private void renew(String name){
				if (Arrays.asList(RENEWABLE).contains(name))
					renewing.push(new_element(name));
			}
			
			private void end_renew(String name){
				Element er = null;
				for (Element e : renewing){
					if (e.getLocalName().equals(name)){
						er = e;
						break;
					}
				}
				if (er != null)
					renewing.remove(er);
			}
		  	
		  	/*align methods*/
		  	
			private void set_align(Element e){
		    	if (align != null)
		    		e.addAttribute(new Attribute("align", align));
			}
			
			private void change_align(){
	    		for(int i=0; i<size(); i++){
	    			if (get(i).getLocalName().equals(XML.LINE)){
	    				set_align(get(i));
	    				return;
	    			}
	    		}
			}
		  	
		  	/*line methods*/
		  	
	    	public boolean in_line(){
	    		return in_tag(XML.LINE);
	    	}
	    	
			public void end_line(){
				if (!in_line())
					return;
				//pop out of all till and including line
				Element e = super.pop();
				while (!e.getLocalName().equals(XML.LINE)){
					renew(e.getLocalName());
					e = super.pop();
				}
				//if ending splitline, pop that as well
				if (endSplitLine)
					super.pop();		    
			}
			
			public void end_till_line(){
				if (!in_line())
					return;
				//pop out of all till line
				Element e = super.pop();
				while (!e.getLocalName().equals(XML.LINE)){
					renew(e.getLocalName());
					e = super.pop();
				}
				super.push(e);
			}
			public void new_line(TagNode node){
				if (in_page())
					end_page();
				endSplitLine = false;
		    	for (String name : node.getAttributeNames()) {
		    		//only attribute we care about
		    		if (name.equals("part")){
		    			if (node.getAttribute(name).equals("i")){
		    				//new splitline element
		    				Element e = new_element("splitline");
		        			super.peekFirst().appendChild(e);
		        			super.push(e);
		    			}else if (node.getAttribute(name).equals("f")){
		    				//close splitline element
		    				//flag that splitline is closing on next line close
		    				endSplitLine = true;
		    			}
		    		}
		    	}
		    	Element e = new_element(XML.LINE);
		    	set_align(e);
		    	super.peekFirst().appendChild(e);
		    	super.push(e);
		    	
		    	while(!renewing.isEmpty()){
		    		e = renewing.pop();
		    		if (e.getLocalName().equals(XML.S))
		    			e.addAttribute(new Attribute("n", String.valueOf(get_last_speech_index())));
		    		super.peekFirst().appendChild(e);
		    		super.push(e);
		    	}
			}
			
		  	public void ensure_in_line(){
		  		if (!in_line())
		  			new_line(new EmptyNode());
		  	}
		  	
		  	/*page methods*/
		  	
	    	public boolean in_page(){
	    		return in_tag(XML.PAGE);
	    	}
	    	
			public void end_page(){
				if (!in_page())
					return;
				//pop out of all till and including page
				Element e = super.pop();
				while (!e.getLocalName().equals(XML.PAGE))
					e = super.pop();
				//reset page children
				page_children = new Hashtable<String, Boolean>();
			}
			
			public void end_till_page(){
				if (!in_page())
					return;
				//pop out of all till line
				Element e = super.pop();
				while (!e.getLocalName().equals(XML.PAGE))
					e = super.pop();
				super.push(e);
			}
			
		  	private Boolean page_child_exists(String str){
		  		if (page_children.contains(str))
		  			return page_children.get(str);
		  		return false;
		  	}
		  	private void add_page_child(String str){
		  		page_children.put(str, true);
		  	}
		  	public void start_page_child(String name){
		  		Element e = new_element(name);
		  		Element page = get_last_tag(XML.PAGE);
		  		if (page == null)
		  			return; //no page has been started... (error ?)
		  		//can't have more than one of any in a single page
		  		if (page_child_exists(e.getLocalName()))
		  			return;
		  		//if in a page, close current child
		  		end_till_page();
		  		//append child to last page
		  		page.appendChild(e);
		  		super.push(e);
		  		//notify that this page child can't occur again
		  		add_page_child(e.getLocalName());
		  	}
		  	private Boolean in_page_child(){
		  		for (String name : page_children.keySet())
		  			if (in_tag(name))
		  				return true;
		  		return false;
		  	}
		  	
		  	/*speech methods*/
		  	
		  	public void ensure_in_speech(){
	   			if (!in_tag(XML.S)){
	   				ensure_in_line();
		   	       	start_element(new_element(XML.S));
	   			}
		  	}
		  	private int get_last_speech_index(){
		  		Element s = get_last_tag(XML.S);
		  		if (s == null)
		  			return 0;
		  		if (s.getAttribute("n") != null)
		  			return Integer.valueOf(s.getAttributeValue("n"));
		  		else
		  			return 0;
		  	}
		  	
		  	/*element methods*/
		  	
			private void new_ms_element(String ln, String n){
				if (in_line()){
					Elements nodes = get_last_tag(XML.LINE).getChildElements();
					for(int i=0;i<nodes.size();i++)
						if (nodes.get(i).getLocalName().equals(XML.MS)){
							end_line();
							new_line(new EmptyNode());
							break;
						}
				}
				else
					new_line(new EmptyNode());
		    	Element e = new_element(XML.MS);
		    	e.addAttribute(new Attribute("t", ln));
		    	e.addAttribute(new Attribute("n", n));
		    	super.peekFirst().appendChild(e);
			}
	   		
			//if not in a line, but should be, push new line
	    	private void start_element(StartNode node){
				if (Arrays.asList(IML.LINE_CHILDREN).contains(node.getName()) && !in_line())
		   			new_line(new EmptyNode());
	    	}    	
	    	//if closing node that's not a child of current line, close line
	    	private void end_element(EndNode node){    		
		    	if (peekFirst().getLocalName().equals(IML.LINE))
		    		end_line();
	    	}
	    	public void pop_element(String name){
	    		//if being renewed, end renew
	    		end_renew(name);
	    		if (!in_tag(name))
	    			return;
	    		Element e = super.pop();
	    		while (!e.getLocalName().equals(name))
	    			e = super.pop();
	    	}
	    	
	    	public void start_element(Element e){
	    		peekFirst().appendChild(e);
	    		push(e);
	    	}
	    	
	    	public void empty_element(Element e){
	    		peekFirst().appendChild(e);
	    	}
	    	
	    	public Element new_element(String name){
	    		return new Element(name, XML.NAMESPACE);
	    	}
	    	public Element new_element(Element e){
	    		return new Element(e);
	    	}
	    	public Element create_element(String name, HashMap<String,String> atts){
	    		Element e = new_element(name);
	    		for (String key : atts.keySet())
	    			e.addAttribute(new Attribute(key, atts.get(key)));
	    		return e;
	    	}
		  	
	    	/*special case elements*/
	    	
	    	public Element new_quote(){
	   			if (!in_line())
	   				return new_element(XML.QUOTE);
	   			else
	   				return new_element(XML.Q);
	    	}
	    	public void new_align(String name){
				align = name;
	   			if (in_line())
	   				change_align();
	    	}
	    	public void new_mode(Element e){
	    		rm_last_child(XML.MODE);
	   			if (in_line())
	       			append_to_work(e, -1);
	       		else
	       			append_to_work(e, 0);
	    	}
	    	public void new_speech(){
	    		Element e = new_element(XML.S);
	       		e.addAttribute(new Attribute("n", String.valueOf(get_last_speech_index()+1)));
	       		start_element(e);
	    	}
	    	
		  	/*other methods*/
		  	
		  	private void append_to_work(Element e, int pos){
		  		Element t = xml.getRootElement();
		  		if (pos <= 0)
		  			t.insertChild(e, t.getChildCount() + pos);
		  		else
		  			t.insertChild(e, pos-1);
		  	}
		  	private Element get_last_tag(String name){
		  		Elements list = xml.getRootElement().getChildElements(name, XML.NAMESPACE);
		  		Element e = null;
		  		if (list != null && list.size() > 0)
		  			e = list.get(list.size()-1);
		  		return e;
		  	}
	    	private boolean in_tag(String tag){
	    		for(int i=0; i<size(); i++){
	    			if (get(i).getLocalName().equals(tag))
	    				return true;
	    		}
	    		return false;
	    	}
	    	private Boolean is_last_child(String name){
	    		Element work = xml.getRootElement();
	    		Elements children = work.getChildElements();
	    		return (children.size() > 0 && children.get(children.size()-1).getLocalName().equals(name));
	    	}
	    	public void rm_last_child(String name){
	    		Element work = xml.getRootElement();
	    		if (is_last_child(name))
	    			work.removeChild(work.getChildElements().size()-1);
	    	}
	  }
	  
	  public Document doc;
	  
	  /**
	   * Construct an XMLWriter and send output to System.out.
	   *
	   * @throws ParserConfigurationException
	   * @throws UnsupportedEncodingException
	   */
	  public XMLWriter() throws ParserConfigurationException, UnsupportedEncodingException {
	    this(new PrintStream(System.out, true, "UTF-8"));
  		doc = null;
	  }

	  /**
	   * Construct an XMLWriter and send the output to the print stream.
	   *
	   * @param out the output destination.
	   *
	   * @throws ParserConfigurationException
	   * @throws UnsupportedEncodingException
	   */
	  public XMLWriter(PrintStream out) throws ParserConfigurationException, UnsupportedEncodingException {
	    super(out);
	    doc = null;
	  }
	  
	  /**
	   * Construct an XMLWrite and do not print to a stream
	   * fills an xml document with the content during rendering
	   *
	   * @param doc the xml document to be filled
	   *
	   * @throws ParserConfigurationException
	   * @throws UnsupportedEncodingException
	   */
	  public XMLWriter(Document doc) throws ParserConfigurationException, UnsupportedEncodingException {
		  super(null);
		  this.doc = doc;
	  }
	  
	@Override
	public void render(DOM dom) throws TransformerConfigurationException, TransformerException, IOException, Exception {
		//First tag must be work; will now simply ignore start work tags
		Element e = new Element(XML.WORK, XML.NAMESPACE);
		doc = new Document(e);
		XMLStack xmlStack = new XMLStack(doc);
		xmlStack.push(e);
	
	    for (Node n : dom.expanded()) {
	    	switch (n.type()) {
	    	case COMMENT:
	    		break;
	        case ABBR:
	        	throw new UnsupportedOperationException("Cannot serialize depreciated abbreviation markup.");
	        case EMPTY:
	        	parse_empty((EmptyNode)n, xmlStack.new_element(n.getName().toLowerCase()), xmlStack);
	        	break;
	        case END:
	        	parse_end((EndNode)n, xmlStack);
	       		break;
	      	case START:
	       		parse_start((StartNode)n, xmlStack.new_element(n.getName().toLowerCase()), xmlStack);
	       		break;
	       	case TEXT:
	       		Text t = new Text(n.getText());
	       		if (parse_text(t, xmlStack))
	       			break;
	       		xmlStack.peekFirst().appendChild(t);
	       		break;
	       	default:
	       		throw new Exception("Cannot convert " + n.getName() + " to XML");
	    	}
	    }    
	    if (super.out != null)
	    	output();
	}
	
	private Boolean parse_text(Text t, XMLStack xmlStack){
		String text = t.getValue();
		//replace all -- with dash
		text = text.replaceAll(IML.DASH,XML.DASH);
		t.setValue(text);
		if (text.trim().isEmpty() || xmlStack.in_page_child())
			return false;
		xmlStack.ensure_in_line();
		String[] lines = null;
		//if there is no newline character, return false and handle normally
		if ((lines = text.split("\n")).length == text.length())
			return false;
		for(int i=0; i<lines.length; i++){
			if (lines[i].isEmpty())
				continue;
			xmlStack.ensure_in_line();
			xmlStack.peekFirst().appendChild(new Text(lines[i]));
			/*end line if not on  last line*/
			if (i < (lines.length -1))
				xmlStack.end_line();
		}
		return true;
	}
	
	private String get_alignment(String str){
		return XML.ALIGNMENT[Arrays.asList(IML.ALIGNMENT).indexOf(str)];
	}
	
	private void parse_start(StartNode node, Element e, XMLStack xmlStack){
    	xmlStack.start_element(node);
  		
   		switch (node.getName().toUpperCase()){
   		//page tags
   		//if not in page, add them to last page
   		case IML.CW:
   			xmlStack.start_page_child(XML.CW);
   			break;
   		case IML.SIG:
   			xmlStack.start_page_child(XML.SIG);
   			break;
   		case IML.RT:
   			xmlStack.start_page_child(XML.RT);
   			break;
   		case IML.PN:
   			xmlStack.start_page_child(XML.PN);
   			break;
   		case IML.ACCENT:
   			break;
   		case IML.DIV:
   		case IML.BACKMATTER:
   			xmlStack.end_line();
   			xmlStack.empty_element(set_attributes(node,e));
        	break;
   		case IML.SD:
   			xmlStack.end_till_line();
   			xmlStack.start_element(set_attributes(node, e));
   			break;
   		case IML.S:
       		xmlStack.new_speech();
       		break;
   		case IML.MODE:
   			xmlStack.new_mode(set_attributes(node,e));
   			break;
   		case IML.ACT:
   		case IML.SCENE:
   			xmlStack.end_line();
        	xmlStack.empty_element(set_attributes(node, e));
        	break;
   		case IML.WORK:
   			break;
   		//alignments
   		case IML.RA:
   		case IML.C:
   		case IML.J:
   			xmlStack.new_align(get_alignment(node.getName()));       			
   			break;
   		case IML.COL:
   			xmlStack.end_line();
   	    	xmlStack.empty_element(xmlStack.new_element(XML.COL));
   	    	break;
   		case IML.ISEHEADER:
   			break;
   		case IML.SP:
   			xmlStack.ensure_in_speech();
       		xmlStack.start_element(set_attributes(node, e));
   			break;
   		case IML.TITLEHEAD:
   			xmlStack.ensure_in_line();
   	    	xmlStack.start_element(xmlStack.new_element(XML.TITLE));
   	    	break;
   		case IML.PAGE:
       		xmlStack.start_element(set_attributes(node, e));
   			break;
   		case IML.LINEGROUP:
   			xmlStack.start_element(set_attributes(node,e, map_put(new_map(),"form","metric"),null,null));
   			break;
   		case IML.QUOTE:
   			xmlStack.start_element(set_attributes(node, xmlStack.new_quote()));
   			break;
   		case IML.VERSEQUOTE:
   			xmlStack.end_line();
       		xmlStack.start_element(set_attributes(node, xmlStack.new_element(XML.QUOTE)));
   			break;
   		case IML.ORNAMENT:
       		xmlStack.start_element(set_attributes(node, e, null,new String[] {"letter"}, null));
   			break;
   		default:
       		xmlStack.start_element(set_attributes(node, e, null, null,null));
   			break;
   		}
	}
	
	private void parse_end(EndNode node, XMLStack xmlStack){
    	xmlStack.end_element(node);
    	
   		switch (node.getName().toUpperCase()){
   		case IML.CW:
   			xmlStack.pop_element(XML.CW);
   			break;
   		case IML.SIG:
   			xmlStack.pop_element(XML.SIG);
   			break;
   		case IML.RT:
   			xmlStack.pop_element(XML.RT);
   			break;
   		case IML.PN:
   			xmlStack.pop_element(XML.PN);
   			break;
   		case IML.AMBIG:
   			xmlStack.pop_element(XML.AMBIG);
   			break;
   		case IML.RDG:
   			xmlStack.pop_element(XML.RDG);
   			break;
   		case IML.PROP:
   			xmlStack.pop_element(XML.PROP);
   			break;
   		case IML.SD:
   			xmlStack.pop_element(XML.SD);
   			break;
   		case IML.S:
   			xmlStack.pop_element(XML.S);
   			break;
   		case IML.TITLEHEAD:
   			xmlStack.pop_element(XML.TITLE);
   			break;
   		case IML.QUOTE:
   			xmlStack.pop_element(XML.Q);
   			xmlStack.pop_element(XML.QUOTE);
   			break;
   		case IML.MODE:
        	xmlStack.append_to_work(xmlStack.create_element(XML.MODE,map_put(new_map(),"t","uncertain")), 0);
        	break;
   		case IML.ACCENT:
   		case IML.BACKMATTER:
   		case IML.DIV:
   		case IML.COL:
   		case IML.ISEHEADER:
   		case IML.ACT:
   		case IML.SCENE:
   			break;
   		case IML.RA:
   		case IML.C:
   		case IML.J:
   			xmlStack.align = null;
   			break;
   		case IML.SP:
   			xmlStack.pop_element(XML.SP);
   			break;
   		case IML.PAGE:
   			xmlStack.end_page();
   			break;
   		case IML.LINEGROUP:
   			xmlStack.pop_element(XML.LINEGROUP);
   			break;
   		case IML.MARG:
   			xmlStack.pop_element(XML.MARG);
   			break;
   		case IML.VERSEQUOTE:
   			xmlStack.end_line();
   			xmlStack.pop_element(XML.QUOTE);
   			break;
   		case IML.WORK:
   			xmlStack.rm_last_child(XML.MODE);
   			break;
   		}
	}
	
	private void parse_empty(EmptyNode node, Element e, XMLStack xmlStack){
   		switch (node.getName().toUpperCase()){
   		case IML.LINE:
        	xmlStack.end_line();
        	xmlStack.new_line(node);
        	break;
   		case IML.QLN:
   			xmlStack.new_ms_element(XML.QLN, node.getAttribute("n"));
   			break;
   		case IML.WLN:
   			xmlStack.new_ms_element(XML.WLN, node.getAttribute("n"));
   			break;
   		case IML.TLN:
   			xmlStack.new_ms_element(XML.TLN, node.getAttribute("n"));
   			break;
   		case IML.LINK:
   			break;
   		case IML.META:
   			break;
   		case IML.RULE:
   			xmlStack.end_line();
   			xmlStack.empty_element(set_attributes(node, e, map_put(new_map(),"n", "l"), null, null));
   			break;
   		case IML.SPACE:
   			xmlStack.ensure_in_line();
   			xmlStack.empty_element(set_attributes(node, e, map_put(new_map(),"n", "l"), new String[] {"setting"}, null));
   			break;
		case IML.BR:
			xmlStack.end_line();
   			xmlStack.new_line(new EmptyNode());
   			break;
   		}
	}
	
	public HashMap<String,String> new_map(){
			return new HashMap<String, String>();
	}
	public HashMap<String,String> map_put(HashMap<String,String> hm, String s1, String s2){
		hm.put(s1,s2);
		return hm;
	}
	
	public Element set_attributes(TagNode node,Element e){
		return set_attributes(node,e,null,null,null);
	}
	public Element set_attributes(TagNode node,Element e, HashMap<String, String> changes, String[] ignore, Attribute[] add){
   		for (String name : node.getAttributeNames()){
	   		if (ignore != null && Arrays.asList(ignore).contains(name))
	   			continue;
   			if (changes != null && changes.containsKey(name))
   				e.addAttribute(new Attribute(changes.get(name),node.getAttribute(name).replace(',', ' ')));
   			else
   				e.addAttribute(new Attribute(name, node.getAttribute(name).replace(',', ' ')));
   		}
		if (add != null)
			for (Attribute attr : add)
				e.addAttribute(attr);
       	return e;
	}
	
	private void output(){
	    try {
	        out.print(doc.toXML());
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }		
	}

	@Override
	public void render(DOM dom, Annotation ann)
			throws TransformerConfigurationException, TransformerException,
			IOException, Exception {
		System.err.println("invalid renderer");
	}

}

