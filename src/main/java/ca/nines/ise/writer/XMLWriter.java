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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Text;
import nu.xom.Serializer;

public class XMLWriter extends Writer{
    public static final String DOC_NS = "http://internetshakespeare.uvic.ca/exist/rest/db/apps/iseapp/content/schema/text/documentClass/dramaticWork.rng";
    public static final String LINK_NS = "http://internetshakespeare.uvic.ca/#internal-linking";
    public static final String HTML_NS = "http://www.w3.org/1999/xhtml";
	  
	  public class XMLStack extends LinkedList<Element>{
		  	private final String[] RENEWABLE = {XML.S, XML.TITLE, XML.MS};
		  	Document xml;
		  	private LinkedList<Element> renewing;
		  	private Hashtable<String, Boolean> page_children;
		  	private Boolean endSplitLine;
		  	private String align;
		  	
		  	public XMLStack(Document xml) {
		  		this.xml = xml;
		  		renewing = new LinkedList<Element>();
		  		page_children = new Hashtable<String, Boolean>();
		  		endSplitLine = false;
		  		align = null;
		  	}
		  	
		  	/*renew methods*/
		  	
		  	/**
		  	 * Sets a tag to be renewed on next line if it's in RENEWABLE
		  	 * @param name name of tag to be renewed
		  	 */
			private void renew(String name){
				if (Arrays.asList(RENEWABLE).contains(name))
					renewing.push(new_element(name));
			}
			
			/**
			 * Stops a renewing tag from being renewed (used when end tags are met)
			 * @param name name of tag
			 */
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
		  	
			/**
			 * Adds an alignment attribute to @e (a line element)
			 * @param e element to which the attribute is added
			 */
			private void set_align(Element e){
		    	if (align != null)
		    		e.addAttribute(new Attribute("align", align));
			}
			
			/**
			 * Changes the current alignment being used
			 */
			private void change_align(){
	    		for(int i=0; i<size(); i++){
	    			if (get(i).getLocalName().equals(XML.LINE)){
	    				set_align(get(i));
	    				return;
	    			}
	    		}
			}
		  	
		  	/*line methods*/
		  	
			/**
			 * Determines if currently in a line tag 
			 * or one of its children
			 * @return true if in line, false otherwise
			 */
	    	public boolean in_line(){
	    		return in_tag(XML.LINE);
	    	}
	    	
	    	/**
	    	 * Ends the current line and all of its children (in order).
	    	 * Used to break out of a current line. Saves closed children in RENEWABLE,
	    	 * which will be renewed on next line.
	    	 */
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
					end_element(XML.SPLITLINE);
			}
			
			/**
			 * Ends all children until the current line, but does not end the line.
			 * Saves closed children in RENEWABLE
			 */
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
			
			/**
			 * Starts a new line tag. Creates splitline tag is @node
			 * has such attributes. Renews any tags that were renewed
			 * on a previous end_line().
			 * @node the node from which to create the line element
			 */
			public void new_line(TagNode node){
				if (in_page())
					end_page();
				endSplitLine = false;
		    	for (String name : node.getAttributeNames()) {
		    		//only attribute we care about
		    		if (name.equals("part")){
		    			if (node.getAttribute(name).equals("i")){
		    				//new splitline element
		    				Element e = new_element(XML.SPLITLINE);
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
		    	start_element(e);
		    	
		    	while(!renewing.isEmpty()){
		    		e = renewing.pop();
		    		if (e.getLocalName().equals(XML.S))
		    			e.addAttribute(new Attribute("n", String.valueOf(get_last_speech_index())));
		    		start_element(e);
		    	}
			}
			
			/**
			 * Ensures that a line tag is open:
			 * if not in a line, one is started
			 * otherwise, nothing happens
			 */
		  	public void ensure_in_line(){
		  		if (!in_line())
		  			new_line(new EmptyNode());
		  	}
		  	
		  	/*page methods*/
		  	
		  	/**
			 * Determines if currently in a page tag 
			 * or one of its children
			 * @return true if in line, false otherwise
		  	 */
	    	public boolean in_page(){
	    		return in_tag(XML.PAGE);
	    	}
	    	
	    	/**
	    	 * Ends the current page tag. 
	    	 * All children currently open are closed.
	    	 */
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
			
			/**
			 * Ends all children of the current page tag.
			 */
			public void end_till_page(){
				if (!in_page())
					return;
				//pop out of all till line
				Element e = super.pop();
				while (!e.getLocalName().equals(XML.PAGE))
					e = super.pop();
				super.push(e);
			}
			
			/**
			 * Determines if the current page has a child with tag name @name
			 * @param name name of tag to check
			 * @return true if page has child of @name, false otherwise
			 */
		  	private Boolean page_child_exists(String name){
		  		if (page_children.contains(name))
		  			return page_children.get(name);
		  		return false;
		  	}
		  	
		  	/**
		  	 * Current page now has child of name @name
		  	 * @param name name of new page child
		  	 */
		  	private void add_page_child(String name){
		  		page_children.put(name, true);
		  	}
		  	
		  	/**
		  	 * Starts a new page child with name of given @name.
		  	 * Does nothing if current page already has a child of name @name
		  	 * @param name of the page child
		  	 */
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
		  	
		  	/**
		  	 * Determines if currently in page tag's child
		  	 * @return true/false
		  	 */
		  	public Boolean in_page_child(){
		  		for (String name : page_children.keySet())
		  			if (in_tag(name))
		  				return true;
		  		return false;
		  	}
		  	
		  	/*speech methods*/
		  	
		  	/**
		  	 * Ensures that a speech tag is currently open.
		  	 * If one is not, it starts one. (Also ensures a line is started)
		  	 * Otherwise, nothing happens
		  	 */
		  	public void ensure_in_speech(){
	   			if (!in_tag(XML.S)){
	   				ensure_in_line();
		   	       	start_element(new_element(XML.S));
	   			}
		  	}
		  	
		  	/**
		  	 * Gets the speech number of the last speech
		  	 * @return speech number
		  	 */
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
		  	
		  	/**
		  	 * Starts a new ms element. 
		  	 * If not in a line or the current line has an ms element,
		  	 * a new line is started with this ms element. 
		  	 * @param ln type of ms (tln/qln/wln)
		  	 * @param n number of the ms element
		  	 */
			public void new_ms_element(String ln, String n){
				if (in_line()){
					List<Element> nodes = get_all_elements(get_last_tag(XML.LINE));
					for(int i=0;i<nodes.size();i++)
						//if current line has a milestone, start new line
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
		    	empty_element(e);
			}
	   		
			/**
			 * If starting an element which should be the child of a line,
			 * ensures a line element is started if not in one
			 * @param node
			 */
	    	private void start_element(StartNode node){
				if (Arrays.asList(IML.LINE_CHILDREN).contains(node.getName()))
		   			ensure_in_line();
	    	}
	    	
	    	/**
	    	 * If currently in an element of name @name, 
	    	 * ends the element and all of its children.
	    	 * @param name name of element to end
	    	 */
	    	public void end_element(String name){
	    		//if being renewed, end renew
	    		end_renew(name);
	    		if (!in_tag(name))
	    			return;
	    		Element e = super.pop();
	    		while (!e.getLocalName().equals(name))
	    			e = super.pop();
	    	}
	    	
	    	/**
	    	 * starts a new start element (<tag>)
	    	 * @param e element to start
	    	 */
	    	public void start_element(Element e){
	    		peekFirst().appendChild(e);
	    		push(e);
	    	}
	    	
	    	/**
	    	 * creates a new empty element (<tag/>)
	    	 * @param e element to create
	    	 */
	    	public void empty_element(Element e){
	    		peekFirst().appendChild(e);
	    	}
	    	
	    	/**
	    	 * Creates a new Element object of name @name
	    	 * with given namespace
	    	 * @param name name of element
	    	 * @param namespace namespace of element
	    	 * @return new Element
	    	 */
	    	public Element new_element(String name, String namespace){
	    		return new Element(name, namespace);
	    	}
	    	
	    	/**
	    	 * Creates a new Element object of name @name
	    	 * with correct namespace
	    	 * @param name name of element
	    	 * @return new Element
	    	 */
	    	public Element new_element(String name){
	    		return new Element(name, DOC_NS);
	    	}
	    	
	    	/**
	    	 * Duplicates given element e
	    	 * @param e element to duplicate
	    	 * @return duplicate element
	    	 */
	    	public Element new_element(Element e){
	    		return new Element(e);
	    	}
	    	
	    	/**
	    	 * Creates an Element of name @name and 
	    	 * adds attributes to it from the @atts
	    	 * @param name the name of the new Element
	    	 * @param atts HashMap of attributes (<name,value>)
	    	 * @return Element created
	    	 */
	    	public Element create_element(String name, HashMap<String,String> atts){
	    		Element e = new_element(name);
	    		for (String key : atts.keySet())
	    			e.addAttribute(new Attribute(key, atts.get(key)));
	    		return e;
	    	}
		  	
	    	/*special case elements*/
	    	
	    	
	    	/**
	    	 * Creates a new quote element. If in a line,
	    	 * returns an XML.QUOTE element (to quote lines),
	    	 * otherwise returns an XML.Q element
	    	 * @return created quote element
	    	 */
	    	public Element new_quote(){
	   			if (!in_line())
	   				return new_element(XML.QUOTE);
	   			else
	   				return new_element(XML.Q);
	    	}
	    	
	    	/**
	    	 * Changes the current line alignment
	    	 * @param name new alignment value
	    	 * 		  if null, alignment is set to default (nothing)
	    	 */
	    	public void new_align(String name){
				align = name;
	   			if (in_line())
	   				change_align();
	    	}
	    	
	    	/**
	    	 * Places a given mode element in the corrent place.
	    	 * If in a line, the mode is placed before the line
	    	 * Otherwise, placed at current position
	    	 * @param e mode element
	    	 */
	    	public void new_mode(Element e){
	    		rm_last_child(XML.MODE);
	   			if (in_line())
	       			append_to_work(e, -1);
	       		else
	       			append_to_work(e, 0);
	    	}
	    	
	    	/**
	    	 * Starts a new speech element
	    	 * Speech is automatically given an "n" attribute
	    	 * to differentiate speeches
	    	 */
	    	public void new_speech(){
	    		Element e = new_element(XML.S);
	       		e.addAttribute(new Attribute("n", String.valueOf(get_last_speech_index()+1)));
	       		start_element(e);
	    	}
	    	
		  	/*other methods*/
		  	
	    	/**
	    	 * Places an Element @e at position @pos in work's children
	    	 * If @pos is positive, indexes start at 1 (first child is 1)
	    	 * If @pos is 0, @e will be work's last child
	    	 * If @pos is negative, indexes from the end (second to last child is -1)
	    	 * @param e
	    	 * @param pos
	    	 */
		  	public void append_to_work(Element e, int pos){
		  		Element t = xml.getRootElement();
		  		if (pos <= 0)
		  			t.insertChild(e, t.getChildCount() + pos);
		  		else
		  			t.insertChild(e, pos-1);
		  	}
		  	
		  	/**
		  	 * Returns a List of all descendants of the given node, in order,
		  	 * including the given parent node
		  	 * @param node whose descendants to list
		  	 * @return list of descendants
		  	 */
		    public List<Element> get_all_elements(Element current) {
		        List<Element> elements =  new ArrayList<Element>();
		        elements.add(current);
		        Elements list = current.getChildElements();
		    	for (int i = 0; i < list.size(); i++) {
		        	elements.addAll(get_all_elements(list.get(i)));
		        }
		    	return elements;
		    }
		  	
		  	/**
		  	 * Gets the Element of the last tag with name @name
		  	 * @param name name of the Element
		  	 * @return the Element
		  	 */
		  	private Element get_last_tag(String name){
		  		List<Element> list = get_all_elements(xml.getRootElement());
		  		for(int i=list.size()-1; i>=0; i--)
		  			if (list.get(i).getLocalName().equals(name))
		  				return list.get(i);
		  		return null;
		  	}
		  	
		  	/**
		  	 * Determines if currently in a tag or one of its descendants 
		  	 * @param tag name of tag
		  	 * @return true if in tag, false otherwise
		  	 */
	    	private boolean in_tag(String tag){
	    		for(int i=0; i<size(); i++){
	    			if (get(i).getLocalName().equals(tag))
	    				return true;
	    		}
	    		return false;
	    	}
	    	
	    	/**
	    	 * Determines if a tag of name @name is the last child of work
	    	 * @param name @name of tag
	    	 * @return true if last child, otherwise
	    	 */
	    	private Boolean is_last_child(String name){
	    		Element work = xml.getRootElement();
	    		Elements children = work.getChildElements();
	    		return (children.size() > 0 && children.get(children.size()-1).getLocalName().equals(name));
	    	}
	    	
	    	/**
	    	 * Removes the last child of work of given name @name
	    	 * @param name name of work's child to remove
	    	 */
	    	public void rm_last_child(String name){
	    		Element work = xml.getRootElement();
	    		if (is_last_child(name))
	    			work.removeChild(work.getChildElements().size()-1);
	    	}
	  }
	  
	  
	  /**
	   * Construct an XMLWriter and send output to System.out.
	   *
	   * @throws ParserConfigurationException
	   * @throws UnsupportedEncodingException
	   */
	  public XMLWriter() throws ParserConfigurationException, UnsupportedEncodingException {
	    this(new PrintStream(System.out, true, "UTF-8"));
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
	  }
	  
	  
      /**
       * Renders the given IML, and serializes to the configured PrintStream
       */
	  @Override
      public void render(DOM dom) throws TransformerConfigurationException, TransformerException, IOException, Exception {
        Document doc = renderToXOM(dom);
        Serializer ser = new Serializer(out);
        ser.write(doc);
      }

      /**
       * Render the given IML
       *
       * @return the rendered DOM (as a XOM Document)
       */
    public Document renderToXOM(DOM dom) throws IOException {
		//First tag must be work; will now simply ignore start work tags
		Element e = new Element(XML.WORK, DOC_NS);
		Document doc = new Document(e);
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
				  throw new UnsupportedOperationException("Cannot convert " + n.getName() + " to XML");
			  }
		  }       
	    return doc;
	  }
	
	private void parse_start(StartNode node, Element e, XMLStack xmlStack){
    	xmlStack.start_element(node);
  		
   		switch (node.getName().toUpperCase()){
   		case IML.INDENT:
   			xmlStack.ensure_in_line();
   			xmlStack.start_element(set_attributes(node, xmlStack.new_element(XML.INDENT), map_put(new_map(),"n","l"),null,
   													new Attribute[] {new Attribute("t","formatting")}));
   			break;
   		case IML.I:
   			xmlStack.ensure_in_line();
   			xmlStack.start_element(set_attributes(node, xmlStack.new_element(XML.I,HTML_NS)));
   			break;
   		case IML.IEMBED:
   			xmlStack.ensure_in_line();
   			xmlStack.start_element(set_attributes(node, xmlStack.new_element(XML.IEMBED,LINK_NS)));
   			break;
   		case IML.ILINK:
   			xmlStack.ensure_in_line();
   			xmlStack.start_element(set_attributes(node, xmlStack.new_element(XML.ILINK, LINK_NS)));
   			break;
   		case IML.SUP:
   			xmlStack.ensure_in_line();
   			xmlStack.start_element(set_attributes(node, xmlStack.new_element(XML.SUP, HTML_NS)));
   			break;
   		case IML.SUB:
   			xmlStack.ensure_in_line();
   			xmlStack.start_element(set_attributes(node, xmlStack.new_element(XML.SUB, HTML_NS)));
   			break;
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
   		switch (node.getName().toUpperCase()){
   		case IML.CW:
   			xmlStack.end_element(XML.CW);
   			break;
   		case IML.SIG:
   			xmlStack.end_element(XML.SIG);
   			break;
   		case IML.RT:
   			xmlStack.end_element(XML.RT);
   			break;
   		case IML.PN:
   			xmlStack.end_element(XML.PN);
   			break;
   		case IML.AMBIG:
   			xmlStack.end_element(XML.AMBIG);
   			break;
   		case IML.RDG:
   			xmlStack.end_element(XML.RDG);
   			break;
   		case IML.PROP:
   			xmlStack.end_element(XML.PROP);
   			break;
   		case IML.SD:
   			xmlStack.end_element(XML.SD);
   			break;
   		case IML.S:
   			xmlStack.end_element(XML.S);
   			break;
   		case IML.TITLEHEAD:
   			xmlStack.end_element(XML.TITLE);
   			break;
   		case IML.QUOTE:
   			xmlStack.end_element(XML.Q);
   			xmlStack.end_element(XML.QUOTE);
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
   			xmlStack.end_element(XML.SP);
   			break;
   		case IML.PAGE:
   			xmlStack.end_page();
   			break;
   		case IML.LINEGROUP:
   			xmlStack.end_element(XML.LINEGROUP);
   			break;
   		case IML.MARG:
   			xmlStack.end_element(XML.MARG);
   			break;
   		case IML.VERSEQUOTE:
   			xmlStack.end_line();
   			xmlStack.end_element(XML.QUOTE);
   			break;
   		case IML.WORK:
   			xmlStack.rm_last_child(XML.MODE);
   			break;
   		case IML.ORNAMENT:
   			xmlStack.end_element(XML.ORNAMENT);
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
	
	/**
	 * Parses a text node for newline characters and 
	 * splits splits the text node into separate nodes placed in their own lines.
	 * First node is added to the current line (if in one)
	 * Last line is not ended
	 * @param t text node
	 * @param xmlStack XML stack
	 * @return true if parsed, false otherwise (in page/no newline characters)
	 */
	private Boolean parse_text(Text t, XMLStack xmlStack){
		String text = t.getValue();
		/* replace all -- with dash */
		text = text.replaceAll(IML.DASH,XML.DASH);
		t.setValue(text);
		/* if text is all whitespace or currently in the child of a page element, don't parse */
		if (text.trim().isEmpty() || xmlStack.in_page_child())
			return false;
		/* if not in a line, start one; text can not be the child of work */
		xmlStack.ensure_in_line();
		String[] lines = null;
		/* if there is no newline character, don't parse */
		if ((lines = text.split("\n")).length == text.length())
			return false;
		
		for(int i=0; i<lines.length; i++){
			/* if text started or ended with \n, split will have created empty lines; ignore these */
			if (lines[i].isEmpty())
				continue;
			/* add each line as its own line */
			xmlStack.ensure_in_line();
			xmlStack.peekFirst().appendChild(new Text(lines[i]));
			/* don't end the last line */
			if (i < (lines.length -1))
				xmlStack.end_line();
		}
		
		/* text parse */
		return true;
	}
	
	/**
	 * gets the XML name of an IML alignment name
	 * ex. C => center
	 * @param str IML alignment name
	 * @return corresponding XML alignment name
	 */
	private String get_alignment(String str){
		return XML.ALIGNMENT[Arrays.asList(IML.ALIGNMENT).indexOf(str)];
	}
	
	/**
	 * wrapper for creating a HashMap
	 * @return new HashMap<String,String>
	 */
	private HashMap<String,String> new_map(){
		return new HashMap<String, String>();
	}
	/**
	 * Wrapper for putting 2 strings into a HashMap<String,String>
	 * @param hm HashMap
	 * @param s1 first string
	 * @param s2 second string
	 * @return HashMap (@hm)
	 */
	private HashMap<String,String> map_put(HashMap<String,String> hm, String s1, String s2){
		hm.put(s1,s2);
		return hm;
	}
	
	/**
	 * Sets the attributes of @node to @e
	 * @param node node with source attributes
	 * @param e destination element
	 * @return Element (@e)
	 */
	private Element set_attributes(TagNode node,Element e){
		return set_attributes(node,e,null,null,null);
	}
	
	/**
	 * Applies attributes to a given Element @e
	 * All attributes in @node are added to @e with the following caveats:
	 * 		Attributes in @node with a name that is a key to @changes have their
	 * 			names changed to the name in @changes corresponding to that name key
	 * 		Attributes in @node with a name in @ignore are ignored entirely
	 * All attributes in @add are also added to @e
	 * @param node node to copy attributes from
	 * @param e	destination Element to which attributes are added
	 * @param changes HashMap of attributes whose values are to be changed
	 * @param ignore Attribute names to be ignored
	 * @param add extra Attributes to be added to @e
	 * @return Element (@e)
	 */
	private Element set_attributes(TagNode node,Element e, HashMap<String, String> changes, String[] ignore, Attribute[] add){
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

	@Override
	public void render(DOM dom, Annotation ann)
			throws TransformerConfigurationException, TransformerException,
			IOException, Exception {
		System.err.println("invalid renderer");
	}

}

