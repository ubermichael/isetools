/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XMLWriter extends Writer{
	
	  public class  Lines extends Stack<ImmutablePair<String,Integer>>{
		  
		  private String peek_type(){
			  return (String)super.peek().getLeft();
		  }
		  
		  private int peek_count(){
			  return (int)super.peek().getRight();
		  }
		  
		  private void dec(){
			  change_line_head(-1);
		  }
		  
		  private void inc(){
			  change_line_head(1);
		  }
		  private void change_line_head(int n){
			  if (super.isEmpty())
				  return;
			  String left = peek_type();
			  int right = peek_count();
			  super.pop();
			  super.push(new ImmutablePair<String, Integer>(left,right+n));
		  }
		  
		  public void new_element(){
			  if (!super.empty())
				  inc();
		  }
		  
		  public void rm_element(){
			  if (!super.empty())
				  dec();
		  }
	  }
	  
	  public class XMLStack extends ArrayDeque<Element>{
		  	private Boolean endSplitLine;
		  	private Boolean inSpeech;
		  	private String align;
		  	Document xml;
		  	Lines line;
		  	
		  	public XMLStack() throws ParserConfigurationException{
		  		endSplitLine = false;
		  		inSpeech = false;
		  		align = null;
		  		xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		  		line = new Lines();
		  	}
		  	
		  	public void start_speech(){
		  		inSpeech = true;
		  	}
		  	
		  	public void end_speech(){
		  		inSpeech = false;
		  	}
		  	
		  	private void push_speech(){
		    	Element e = xml.createElement(XML.S);
		    	super.peekFirst().appendChild(e);
		    	super.push(e);
		  	}
		  
			public void new_line(TagNode node){
				new_line(IML.LINE);
				endSplitLine = false;
		    	for (String name : node.getAttributeNames()) {
		    		//only attribute we care about
		    		if (name.equals("part")){
		    			if (node.getAttribute(name).equals("i")){
		    				//new splitline element
		    				Element e = xml.createElement("splitline");
		        			super.peekFirst().appendChild(e);
		        			super.push(e);
		    			}else if (node.getAttribute(name).equals("f")){
		    				//close splitline element
		    				//flag that splitline is closing on next line close
		    				endSplitLine = true;
		    			}
		    		}
		    	}
		    	Element e = xml.createElement(XML.LINE);
		    	if (align != null)
		    		e.setAttribute("align", align);
		    	super.peekFirst().appendChild(e);
		    	super.push(e);
				if (inSpeech)
					push_speech();
			}
			
			public void end_line(){
				if (!in_line())
					return;
				line.pop();
				//pop out of all till and including line
				Element e = super.pop();
				while (!e.getNodeName().equals(XML.LINE))
					e = super.pop();
				//if ending splitline, pop that as well
				if (endSplitLine)
					super.pop();		    
			}
			
			public void end_till_line(){
				if (!in_line())
					return;
				//pop out of all till line
				Element e = super.pop();
				while (!e.getNodeName().equals(XML.LINE))
					e = super.pop();
				super.push(e);
			}
			
			public void end_page(){
				if (in_page())
					line.pop();
				super.pop();	    
			}
			
			private void new_ms_element(String ln, String n){
				if (!in_line())
					new_line(new EmptyNode());
		    	Element e = xml.createElement(XML.MS);
		    	e.setAttribute("t", ln);
		    	e.setAttribute("n", n);
		    	super.peekFirst().appendChild(e);
			}
	   		
			//if not in a line, but should be, push new line
	    	private void new_element(StartNode node){
				if (Arrays.asList(IML.LINE_CHILDREN).contains(node.getName()) && !in_line())
		   			new_line(new EmptyNode());
				line.new_element();
	    	}    	
	    	//if closing node that's not a child of current line, close line
	    	private void rm_element(EndNode node){    		
		    	if (peekFirst().getNodeName().equals(IML.LINE))
		    		end_line();
		    	line.rm_element();
	    	}
	    	public boolean in_line(){
	    		if (!line.empty())
	    			return line.peek_type().equals(IML.LINE);
				return false;
	    	}
			  
	    	public boolean in_line_element(){
	    		return !line.empty();
	    	}
			  
	    	public boolean in_page(){
	    		if (!line.empty())
	    			return line.peek_type().equals(IML.PAGE);
	    		return false;
	    	}
				
	    	public boolean in_line_child(){
	    		if (!line.empty())
	    			return (int)line.peek().getRight() != 0;	
	    		return false;
	    	}
	    	private void new_line(String type){
	    		line.push(new ImmutablePair<String, Integer>(type, 0));
	    	}
	    	public void pop_element(String name){
	    		if (peekFirst().getNodeName().equals(name))
	    			pop();
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
	  
	@Override
	public void render(DOM dom) throws TransformerConfigurationException, TransformerException, IOException, Exception {
	    // @TODO check if the DOM is expanded, and expand if necessary.
		XMLStack xmlStack = new XMLStack();
		
	    Element e = xmlStack.xml.createElement(IML.ROOT);
	    xmlStack.xml.appendChild(e);
	    xmlStack.push(e);
	
	    for (Node n : dom.expanded()) {
	    	switch (n.type()) {
	        case ABBR:
	        	throw new UnsupportedOperationException("Cannot serialize depreciated abbreviation markup.");
	        case COMMENT:
	        	Comment c = xmlStack.xml.createComment(n.getText());
	        	xmlStack.peekFirst().appendChild(c);
	        	break;
	        case EMPTY:
	        	EmptyNode emptyNode = (EmptyNode) n;
	        	Element emptyElement = xmlStack.xml.createElement(emptyNode.getName().toLowerCase());
	        	if (parse_empty(emptyNode, emptyElement, xmlStack))
	        		break;
	       		set_attributes(emptyNode, emptyElement,null,null);
	        	xmlStack.peekFirst().appendChild(emptyElement);
	        	break;
	        case END:
	        	EndNode endNode = (EndNode) n;
	        	parse_end(endNode, xmlStack);
	       		break;
	      	case START:
	       		StartNode startNode = (StartNode) n;
	       		Element startElement = xmlStack.xml.createElement(startNode.getName().toLowerCase());	       		
	       		if (parse_start(startNode, startElement, xmlStack))
	       			break;
	       		set_attributes(startNode, startElement, null,null);
	       		xmlStack.peekFirst().appendChild(startElement);
	       		xmlStack.push(startElement);
	       		break;
	       	case TEXT:
	       		Text t = xmlStack.xml.createTextNode(n.getText());
	       		if (parse_text(t, xmlStack))
	       			break;
	       		xmlStack.peekFirst().appendChild(t);
	       		break;
	       	default:
	       		throw new Exception("Cannot convert " + n.getName() + " to XML");
	    	}
	    }    
	    output(xmlStack.xml);
	}
	
	private Boolean parse_text(Text t, XMLStack xmlStack){
		String text = t.getTextContent();
		//replace all -- with dash
		text = text.replaceAll("--","\u2014");
		t.setTextContent(text);
		if (text.trim().isEmpty() || xmlStack.in_page())
			return false;
		if (!xmlStack.in_line())
   			xmlStack.new_line(new EmptyNode());
		String[] lines = null;
		//if there is no newline character, return false and handle normally
		if ((lines = text.split("\n")).length == text.length())
			return false;
		for(int i=0; i<lines.length; i++){
			if (lines[i].isEmpty())
				continue;
			//if not in a line, start one
			if (!xmlStack.in_line())
				xmlStack.new_line(new EmptyNode());
			xmlStack.peekFirst().appendChild(xmlStack.xml.createTextNode(lines[i]));
			//if in a line and it is not the last line of this text node, end line
			if (xmlStack.in_line_element() && i < (lines.length -1))
				xmlStack.end_line();
		}
		return true;
	}
	
	private String get_alignment(String str){
		return XML.ALIGNMENT[Arrays.asList(IML.ALIGNMENT).indexOf(str)];
	}
	
	private Boolean parse_start(StartNode node, Element e, XMLStack xmlStack){
    	xmlStack.new_element(node);
  		
   		switch (node.getName().toUpperCase()){
   		//found an IML document with an unclosed line...
   		case IML.LINE:
        	if (xmlStack.in_line())
        		xmlStack.end_line();
        	xmlStack.new_line(node);
        	return true;
   		case IML.ACCENT:
   			return true;
   		case IML.DIV:
   		case IML.BACKMATTER:
   			if (xmlStack.in_line())
   				xmlStack.end_line();
   			set_attributes(node,e,null,null);
        	xmlStack.peekFirst().appendChild(e);
   			return true;
   		case IML.SD:
   			if (xmlStack.in_line())
   				xmlStack.end_till_line();
   			return false;
   		case IML.S:
   			xmlStack.start_speech();
   			return false;
   		case IML.MODE:
   			if (xmlStack.in_line_element())
   				xmlStack.end_line();
       		set_attributes(node, e,null,null);
        	xmlStack.peekFirst().appendChild(e);
        	return true;
   		case IML.ACT:
   		case IML.SCENE:
   			if (xmlStack.in_line())
   				xmlStack.end_line();
       		set_attributes(node, e,null,null);
        	xmlStack.peekFirst().appendChild(e);
        	return true;
   		case IML.WORK:
   			e.setAttribute("xmlns", "http://internetshakespeare.uvic.ca/exist/rest/db/apps/iseapp/content/schema/text/documentClass");
       		xmlStack.peekFirst().appendChild(e);
       		xmlStack.push(e);
       		return true;
   		//alignments
   		case IML.RA:
   		case IML.C:
   		case IML.J:
			xmlStack.align = get_alignment(node.getName());
			if (xmlStack.in_line())
				xmlStack.end_line();
			xmlStack.new_line(new EmptyNode());	        			
			return true;
   		case IML.COL: //column
   	    	Element e_col = xmlStack.xml.createElement(XML.COL);
   	    	xmlStack.peekFirst().appendChild(e_col);
   	    	return true;
   		case IML.ISEHEADER:
   			return true;
   		case IML.SP:
   			if (!xmlStack.peekFirst().getTagName().equals(XML.S)){
   				if (!xmlStack.peekFirst().getTagName().equals(XML.LINE))
   					xmlStack.new_line(new EmptyNode());
	   	   		Element e_s = xmlStack.xml.createElement(XML.S);
	   	       	xmlStack.peekFirst().appendChild(e_s);
	   	       	xmlStack.push(e_s);
   			}
   			return false;
   		case IML.TITLEHEAD:
   			if (!xmlStack.peekFirst().getTagName().equals(XML.LINE))
   				xmlStack.new_line(new EmptyNode());
   	    	Element e_title = xmlStack.xml.createElement(XML.TITLE);
   	    	xmlStack.peekFirst().appendChild(e_title);
   	    	xmlStack.push(e_title);
   			return true;
   		case IML.PAGE:
   			xmlStack.new_line(IML.PAGE);
   			return false;
   		case IML.LINEGROUP:
       		set_attributes(node,e, new String[][] {{"form","metric"}},null);
   			xmlStack.peekFirst().appendChild(e);
   			xmlStack.push(e);
        	return true;
   		case IML.QUOTE:
   			//if not currently in a line element, assume quoting a line element
   			Element e_quote;
   			if (!xmlStack.in_line_element())
   				e_quote = xmlStack.xml.createElement(XML.QUOTE);
   			else
   				e_quote = xmlStack.xml.createElement(XML.Q);
   			set_attributes(node, e_quote,null,null);
   			xmlStack.peekFirst().appendChild(e_quote);
   			xmlStack.push(e_quote); 
   			return true;
   		case IML.VERSEQUOTE:
   			if (xmlStack.in_line_element())
   				xmlStack.end_line();
   			e_quote = xmlStack.xml.createElement(XML.QUOTE);
   			set_attributes(node, e_quote,null,null);
   			xmlStack.peekFirst().appendChild(e_quote);
   			xmlStack.push(e_quote); 
   			return true;
   		}
   		return false;
	}
	
	private void parse_end(EndNode node, XMLStack xmlStack){
    	xmlStack.rm_element(node);
    	
   		switch (node.getName().toUpperCase()){
   		case IML.ACCENT:
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
   			xmlStack.end_speech();
   			break;
   		case IML.QUOTE:
   			xmlStack.pop_element(XML.Q);
   			xmlStack.pop_element(XML.QUOTE);
   			break;
   		case IML.BACKMATTER:
   		case IML.DIV:
   		case IML.COL:
   		case IML.ISEHEADER:
   		case IML.MODE:
   		case IML.ACT:
   		case IML.SCENE:
   			break;
   		//alignments
   		case IML.RA:
   		case IML.C:
   		case IML.J:
   			if (xmlStack.in_line())
   				xmlStack.end_line();
   			break;
   		case IML.SP:
   			xmlStack.pop_element(XML.SP);
   			break;
   		case IML.PAGE:
   			if (xmlStack.in_page())
   				xmlStack.end_page();
   			break;
   		case IML.LINEGROUP:
   			//linegroup can't be empty; default insert an empty line if so
   			if (!xmlStack.peekFirst().equals(IML.LINEGROUP) && !xmlStack.peekFirst().hasChildNodes()){
   				xmlStack.new_line(new EmptyNode());
   				xmlStack.end_line();
   			}
   			xmlStack.pop_element(XML.LINEGROUP);
   			break;
   		case IML.MARG:
   			//marg can't be empty; default insert an empty line if so
   			if (!xmlStack.peekFirst().equals(IML.MARG) && !xmlStack.peekFirst().hasChildNodes()){
   				xmlStack.new_line(new EmptyNode());
   				xmlStack.end_line();
   			}
   			xmlStack.pop_element(XML.MARG);
   			break;
   		case IML.VERSEQUOTE:
   			if (xmlStack.in_line())
   				xmlStack.end_line();
   			xmlStack.pop_element(XML.QUOTE);
   			break;
   		}
	}
	
	private boolean parse_empty(EmptyNode node, Element e, XMLStack xmlStack){
   		switch (node.getName().toUpperCase()){
   		case IML.LINE:
        	if (xmlStack.in_line())
        		xmlStack.end_line();
        	xmlStack.new_line(node);
        	return true;
   		case IML.QLN:
   			xmlStack.new_ms_element(XML.QLN, node.getAttribute("n"));
   			return true;
   		case IML.WLN:
   			xmlStack.new_ms_element(XML.WLN, node.getAttribute("n"));
   			return true;
   		case IML.TLN:
   			xmlStack.new_ms_element(XML.TLN, node.getAttribute("n"));
   			return true;
   		case IML.LINK:
   			return true;
   		case IML.META:
   			return true;
   		case IML.RULE:
       		set_attributes(node,e, new String[][] {{"n","l"}},null);
   			xmlStack.peekFirst().appendChild(e);
        	return true;
		case IML.SPACE:
   			if (!xmlStack.in_line())
   				xmlStack.new_line(new EmptyNode());
   			set_attributes(node, e, new String[][] {{"n","l"}}, new String[] {"setting"});
   			xmlStack.peekFirst().appendChild(e);
        	return true;
		case IML.BR:
   			if (xmlStack.in_line())
   				xmlStack.end_line();
   			xmlStack.new_line(new EmptyNode());
   			return true;
   		}
   		return false;
	}
	
	public Element set_attributes(TagNode node,Element e, String[][] changes, String[] ignore){
   		atts: for (String name : node.getAttributeNames()){
   			if (changes != null){
	   			for (String[] pair : changes)
	   				if (pair[0].equals(name)){
	   		       		e.setAttribute(pair[1], node.getAttribute(pair[0]));
	   		       		continue atts;
	   				}
   			}
	   		if (ignore != null){
	   			for (String n : ignore)
	   				if (n.equals(name))
	   		       		continue atts;
	   		}
       		e.setAttribute(name, node.getAttribute(name));
   		}
       	return e;
	}
	
	private void output(Document xml){
	    try {    	
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "no");
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        DOMSource source = new DOMSource(xml);
	        
	        //temporary stream
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PrintStream ps = new PrintStream(baos);
	        StreamResult stream = new StreamResult(ps);
	        transformer.transform(source, stream);
	        
	        //remove root from ps
	        String str = remove_root(baos.toString());
	        //System.out.println(str);
	        //add version & encoding to out stream
	        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");      
	        //print rest without root node
	        out.print(str);
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }		
	}
	
	private String remove_root(String str){
		return str.replaceAll("\\<"+IML.ROOT+">|</"+IML.ROOT+">", "");
	}

	@Override
	public void render(DOM dom, Annotation ann)
			throws TransformerConfigurationException, TransformerException,
			IOException, Exception {
		// TODO Auto-generated method stub
		
	}

}

