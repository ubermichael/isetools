package ca.nines.ise.writer;


import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;

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

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Text;

public class XMLWriterNew extends Writer{
	
	  /**
	   * Construct an XMLWriter and send output to System.out.
	   *
	   * @throws ParserConfigurationException
	   * @throws UnsupportedEncodingException
	   */
	  public XMLWriterNew() throws ParserConfigurationException, UnsupportedEncodingException {
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
	  public XMLWriterNew(PrintStream out) throws ParserConfigurationException, UnsupportedEncodingException {
	    super(out);
	  }

	  private static final String[] LINE_CHILDREN = {"ABBR","AMBIG","BLL","CL","EM","FOREIGN","HW",
		  					"LD","LS","PROP","R","S","SC","SD","SWASH","TITLEHEAD","TLN","QLN","WLN"};
	  private static final String[] ALIGNMENT = {"RA","C","J"};
	  private static final String[] ALIGNMENT_NEW = {"right","center","justify"};
	  
	  private ArrayDeque<Element> xmlStack;
	  private Document xml;
	  private Stack<Integer> line_children;
	  private Boolean endSplitLine;
	  private String align;
	  
	@Override
	public void render(DOM dom) throws TransformerConfigurationException, TransformerException, IOException, Exception {
	    // @TODO check if the DOM is expanded, and expand if necessary.
		xmlStack = new ArrayDeque<>();
		xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		line_children = new Stack<Integer>();
		endSplitLine = false;
		align = null;
		
	    Element e = xml.createElement("root");
	    xml.appendChild(e);
	    xmlStack.push(e);
	
	    for (Node n : dom.expanded()) {
	    	switch (n.type()) {
	        case ABBR:
	        	throw new UnsupportedOperationException("Cannot serialize depreciated abbreviation markup.");
	        case COMMENT:
	        	Comment c = xml.createComment(n.getText());
	        	xmlStack.peekFirst().appendChild(c);
	        	break;
	        case EMPTY:
	        	EmptyNode emptyNode = (EmptyNode) n;
	        	Element endElement = xml.createElement(emptyNode.getName().toLowerCase());
	        	if (parse_empty(emptyNode))
	        		break;
	        	for (String name : emptyNode.getAttributeNames()) {
	        		endElement.setAttribute(name, emptyNode.getAttribute(name));
	        	}
	        	xmlStack.peekFirst().appendChild(endElement);
	        	break;
	        case END:
	        	EndNode endNode = (EndNode) n;
	        	if (parse_end(endNode))
	        		break;
	        	if (endNode.getName().toLowerCase().equals(xmlStack.peekFirst().getNodeName())) {
	        		xmlStack.pop();
	        		break;
	        	}
	      		ArrayDeque<Element> splitStack = new ArrayDeque<>();
	       		while (xmlStack.size() > 1) {
	       			Element split = xmlStack.pop();
	       			if (split.getNodeName().equals(endNode.getName().toLowerCase())) {
	       				break; // while 
	       			}
	       			splitStack.push(split);
	       		}
		        while (splitStack.size() > 0) {
	       			Element popped = splitStack.pop();
	       			Element clone = xml.createElement(popped.getNodeName());
	       			NamedNodeMap attrs = popped.getAttributes();
	       			int numAttrs = attrs.getLength();
	       			for (int i = 0; i < numAttrs; i++) {
	       				clone.setAttribute(attrs.item(i).getNodeName(), attrs.item(i).getNodeValue());
	       			}
	       			xmlStack.peekFirst().appendChild(clone);
	       			xmlStack.push(clone);
	       		}
	       		break;
	      	case START:
	       		StartNode startNode = (StartNode) n;
	       		if (parse_start(startNode))
	       			break;
	       		Element startElement = xml.createElement(startNode.getName().toLowerCase());
	       		//if work, add namespace
	       		if (n.getName().toUpperCase().equals("WORK"))
	       			startElement.setAttribute("xmlns", "http://internetshakespeare.uvic.ca/exist/rest/db/apps/iseapp/content/schema/text/documentClass");
	       		else
		       		for (String name : startNode.getAttributeNames()) {
		       			startElement.setAttribute(name, startNode.getAttribute(name));
		       		}
	       		xmlStack.peekFirst().appendChild(startElement);
	       		xmlStack.push(startElement);
	       		break;
	       	case TEXT:
	       		Text t = xml.createTextNode(n.getText());
	       		xmlStack.peekFirst().appendChild(t);
	       		break;
	       	default:
	       		throw new Exception("Cannot convert " + n.getName() + " to XML");
	    	}
	    }    
	    output(xml);
  }
	
	private Boolean parse_start(StartNode node){
   		//if not in a line, but should be, or is an alignment, push new line
    	if (Arrays.asList(LINE_CHILDREN).contains(node.getName()) || 
    		Arrays.asList(ALIGNMENT).contains(node.getName())){
        	//line can't be a child of a line, so close if not in a child of line
        	if (!line_children.empty() && line_children.peek() == 0)
        		end_line();
    		int index;
    		//if aligning, set alignment
    		if ((index = Arrays.asList(ALIGNMENT).indexOf(node.getName())) >= 0){
    			align = ALIGNMENT_NEW[index];
    			new_line(new EmptyNode());	        			
    			return true;
    		}
   			new_line(new EmptyNode());
    	}
  		//if child of line, increment head of line_children	        	
  		if (!line_children.empty())
  			line_children.push(line_children.pop()+1);
  		return false;
	}
	
	private Boolean parse_end(EndNode node){
    	//if closing alignment, close line and null alignment
    	if (Arrays.asList(ALIGNMENT).contains(node.getName())){
    		end_line();	
    		return true;
    	}
    	//if closing node that's not a child of current line, close line
    	if (!line_children.empty() && line_children.peek() == 0)
    		end_line();
  		//if child of line, decrement head of line_children
    	if (!line_children.empty())
  			line_children.push(line_children.pop()-1);
    	return false;
	}
	
	private boolean parse_empty(EmptyNode node){
   		//if line, handle it differently
   		if (node.getName().toUpperCase().equals("L")){
        	//line can't be a child of a line, so close if not in a child of line
        	if (!line_children.empty() && line_children.peek() == 0)
        		end_line();
        	new_line(node);
        	return true;
   		}
   		return false;
	}
	
	private void new_line(EmptyNode node){
		line_children.push(0);
		endSplitLine = false;
    	for (String name : node.getAttributeNames()) {
    		//only attribute we care about
    		if (name.equals("part")){
    			if (node.getAttribute(name).equals("i")){
    				//new splitline element
    				Element e = xml.createElement("splitline");
        			xmlStack.peekFirst().appendChild(e);
        			xmlStack.push(e);
    			}else if (node.getAttribute(name).equals("f")){
    				//close splitline element
    				//flag that splitline is closing on next line close
    				endSplitLine = true;
    			}
    		}
    	}
    	Element e = xml.createElement("l");
    	if (align != null)
    		e.setAttribute("align", align);
    	xmlStack.peekFirst().appendChild(e);
    	xmlStack.push(e);
	}
	
	private void end_line(){
		line_children.pop();
		xmlStack.pop();
		//if ending splitline, pop that as well
		if (endSplitLine)
			xmlStack.pop();		    
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
		return str.replaceAll("\\<root>|</root>", "");
	}

	@Override
	public void render(DOM dom, Annotation ann)
			throws TransformerConfigurationException, TransformerException,
			IOException, Exception {
		// TODO Auto-generated method stub
		
	}

}
