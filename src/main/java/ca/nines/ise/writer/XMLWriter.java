/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
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
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParentNode;
import nu.xom.Text;
import nu.xom.Serializer;

public class XMLWriter extends Writer{
  public static final String DOC_NS = "http://internetshakespeare.uvic.ca/exist/rest/db/apps/iseapp/content/schema/text/documentClass/dramaticWork.rng";
  public static final String LINK_NS = "http://internetshakespeare.uvic.ca/#internal-linking";
  public static final String HTML_NS = "http://www.w3.org/1999/xhtml";
    
  static final Map<String , String> XML_MAP = new HashMap<String , String>() {{
   put("RA", "right");
   put("C", "center");
   put("J", "justify");
  }};
    
	protected class XMLStack extends LinkedList<Element> {
	  private Schema schema;
	  private List<String> INLINE_TAGS;
    private List<String> FLATTEN;
    private List<String> TYPEFACES;
    private List<String> DONT_PARSE_TEXT;
    private List<String> LINE_PARENTS;
		private List<String> renewable;
		private List<String> VALID_TAGS;
		private LinkedList<Element> in_next_line;
		private Document xml;
		private LinkedList<LinkedList<Element>> renewing;
		private List<String> page_children;
		private Boolean endSplitLine;
		private Boolean endSplitLineOnNext;
		private String align;
		private List<Element> real_lines;

		public XMLStack(Document xml) throws IOException, SAXException, ParserConfigurationException, TransformerException {
		  schema = Schema.defaultSchema();
		  VALID_TAGS = array_to_lower(Arrays.asList(schema.getTagNames()));
			INLINE_TAGS = schema.get_Inline_tags();
			FLATTEN = array_to_lower(schema.get_flatten_tags());
			TYPEFACES = array_to_lower(schema.get_typeface_tags());
			DONT_PARSE_TEXT = array_to_lower(schema.get_unparsed_text_tags());
			LINE_PARENTS = array_to_lower(schema.get_line_parent_tags());
		  renewable = schema.get_Inline_tags();
			this.xml = xml;
			renewing = new LinkedList<LinkedList<Element>>();
			in_next_line = new LinkedList<Element>();
			renewing.push(new LinkedList<Element>());
			page_children = new ArrayList<String>();
			endSplitLine = false;
			endSplitLineOnNext = false;
			align = null;
			real_lines = new ArrayList<Element>();
		}
		
		private List<String> array_to_lower(List<String> list){
		  for (int i=0; i<list.size(); i++){
		    list.set(i, list.get(i).toLowerCase());
		  }
		  return list;
		}

		/* renew methods */
		
		private LinkedList<Element> get_renew(){
		  if (renewing.isEmpty())
	      renewing.push(new LinkedList<Element>());
			return renewing.peek();
		}

		/**
		 * Sets a tag to be renewed on next line if it's in RENEWABLE
		 * 
		 * @param name
		 *            name of tag to be renewed
		 */
		private void renew(String name, List<Attribute> atts) {
		  Element e = new_element(name);
			if (renewable.contains(name.toUpperCase()))
				if (atts == null)
					get_renew().push(e);
				else{
					for(Attribute a : atts)
						e.addAttribute(a);
					get_renew().push(e);
				}
		}
		
		private Boolean renewing_has(String name){
			for (Element e : get_renew()) {
				if (e.getLocalName().equals(name))
					return true;
			}
			return false;
		}
		
		/**
		 * Stops a renewing tag from being renewed (used when end tags are met)
		 * 
		 * @param name
		 *            name of tag
		 */
		private void end_renew(String name) {
			Element er = null;
			for (Element e : get_renew()) {
				if (e.getLocalName().equals(name)) {
					er = e;
					break;
				}
			}
			if (er != null)
				get_renew().remove(er);
		}

		/**
		 * renews the elements being renewed (in renewing)
		 */
		private void renew_elements() {
		  Element s = null;
			while (!get_renew().isEmpty()) {
				Element e = get_renew().pop();
				if (INLINE_TAGS.contains(e.getLocalName().toUpperCase()))
          ensure_in_line();
				if (e.getLocalName().equals("s")){
				  Boolean created = new_speech(false);
				  if (!created)
				    s = e;
				}else
				  start_element(e);
			}
			if (s != null)
			  renew(s.getLocalName(),null);
		}

		/* align methods */

		/**
		 * Adds an alignment attribute to @e (a line element)
		 * 
		 * @param e
		 *            element to which the attribute is added
		 */
		private void set_align(Element e) {
			if (align != null)
				e.addAttribute(new Attribute("align", align));
		}

		/**
		 * Changes the current alignment being used
		 */
		private void change_align() {
			for (int i = 0; i < size(); i++) {
				if (get(i).getLocalName().equals("l")) {
					set_align(get(i));
					return;
				}
			}
		}

		/* line methods */
		
		public void append_to_line(String text){
			Element last_line = get_tag_at("l", -1);
			Element this_line = get_last_tag("l");
			if (last_line != null){
				/* if there was a shy on last line and this is the first child of current line */
				if (!check_whitespace(last_line) && this_line.getChildCount() == 0)
					/* trim start of line */
					text = text.replaceAll("^\\s+", "");
			}
			peekFirst().appendChild(new Text(text));
		}
		
		/**
		 * Determines if currently in a line tag or one of its children
		 * 
		 * @return true if in line, false otherwise
		 */
		public boolean in_line() {
			if (in_tag("l"))
			  return true;
			return false;
		}
		
		/**
		 * Ensures that a line tag is open: if not in a line, one is started
		 * otherwise, nothing happens
		 */
		public void ensure_in_line() {
			if (!in_line() && !in_page_child())
				new_line(new EmptyNode(), false);
		}
		
		/**
		 * Ends the current line and all of its children (in order). Used to
		 * break out of a current line. Saves closed children in RENEWABLE,
		 * which will be renewed on next line.
		 */
		public void end_line() {
			if (!in_line())
				return;
			// pop out of all till and including line
			Element e = super.pop();
			while (!e.getLocalName().equals("l")) {
				renew(e.getLocalName(),get_attributes(e));
				e = super.pop();
			}
			// if ending split on next line, skip this one
			if (endSplitLineOnNext){
			  endSplitLine = true;
			  endSplitLineOnNext = false;
			} 
			// if the l tag we just closed contains only iembeds
			if (contains_only(e,"iembed") && !real_lines.contains(e)){
			  ParentNode parent = e.getParent();
			  // attach the iembeds to l's parent
			  Elements children = e.getChildElements();
			  for(int i=0; i<children.size(); i++){
			    children.get(i).detach();
			    parent.appendChild(children.get(i));
			  }
			  //detach l
			  e.detach();
			}
			
      // if ending splitline, pop that as well
			else if (endSplitLine)
				end_element("splitline");
		}
		
		private Boolean contains_only(Element e, String child){
		  /* empty line does not contain given child */
		  if (e.getChildCount() == 0)
		    return false;
      for(int i=0; i<e.getChildCount(); i++)
        /* false if non-empty text exists */
        if (e.getChild(i) instanceof Text){
          if (e.getChild(i).getValue().trim().length() > 0)
            return false;
        }
        else if (!((Element)e.getChild(i)).getLocalName().equals(child))
          return false;
      return true;
		}

		/**
		 * Ends all children until the current line, but does not end the line.
		 * Saves closed children in RENEWABLE
		 */
		private void end_till_line() {
			if (!in_line())
				return;
			// pop out of all till line
			Element e = super.pop();
			while (!e.getLocalName().equals("l")) {
				renew(e.getLocalName(),get_attributes(e));
				e = super.pop();
			}
			super.push(e);
		}

		/**
		 * Ends all children until the current line and starts a new element
		 * 
		 * @param e
		 *            new element to be started
		 */
		public void end_till_line_and_start(Element e) {
			end_till_line();
			start_element(e);
		}
		
		private Boolean check_whitespace(Element last_line){
			if (last_line == null)
				return false;
			Elements children = last_line.getChildElements();
			if (children.size() > 0 && 
					children.get(children.size()-1).getLocalName().equals("shy"))
				return false;
			return true;
		}

		/**
		 * Starts a new line tag. Creates splitline tag is @node has such
		 * attributes. Renews any tags that were renewed on a previous
		 * end_line().
		 * 
		 * @node the node from which to create the line element
		 */
		public void new_line(TagNode node, Boolean real) {
			if (in_page())
				end_page();
			if (in_page_child())
				pop();
			Boolean needs_whitespace = check_whitespace(get_last_tag("l"));
			endSplitLine = false;
			Boolean new_ms = false;
			for (String name : node.getAttributeNames()) {
				if (name.equals("n"))
					new_ms = true;
				else if (name.equals("part")) {
					if (node.getAttribute(name).equals("i")) {
						// new splitline element if not already in a splitline
					  if (!in_tag("splitline")){
					    Element e = new_element("splitline");
						  super.peekFirst().appendChild(e);
						  super.push(e);
					  }
					} else if (node.getAttribute(name).equals("f")) {
						// close splitline element
						// flag that splitline is closing on next line close
						endSplitLine = true;
					}
				}
			}
			Element e = new_element("l");
			set_align(e);
			start_element(e);
			/* insert " " at beginning if last didn't end in <shy/> */
			if (needs_whitespace)
				peekFirst().appendChild(new Text(" "));
      /* insert in_next_line elements first */
      while (!in_next_line.isEmpty())
        peekFirst().appendChild(in_next_line.pop());
			if (new_ms)
				new_ms_element("l", node.getAttribute("n"));
			renew_elements();
			/* if it's a real line, add it to real_lines */
			if (real)
			  real_lines.add(e);
		}

		/* page methods */

		/**
		 * Determines if currently in a page tag or one of its children
		 * 
		 * @return true if in line, false otherwise
		 */
		public boolean in_page() {
			return in_tag("page");
		}

		/**
		 * Ends the current page tag. All children currently open are closed.
		 */
		public void end_page() {
			if (!in_page())
				return;
			// pop out of all till and including page
			Element e = super.pop();
			while (!e.getLocalName().equals("page"))
				e = super.pop();
			// reset page children
			page_children = new ArrayList<String>();
		}

		/**
		 * Ends all children of the current page tag.
		 */
		public void end_till_page() {
			if (!in_page())
				return;
			// pop out of all till line
			Element e = super.pop();
			while (!e.getLocalName().equals("page"))
				e = super.pop();
			super.push(e);
		}

		/**
		 * Determines if the current page has a child with tag name @name
		 * 
		 * @param name
		 *            name of tag to check
		 * @return true if page has child of @name, false otherwise
		 */
		private Boolean page_child_exists(String name) {
			return page_children.contains(name);
		}

		/**
		 * Current page now has child of name @name
		 * 
		 * @param name
		 *            name of new page child
		 */
		private void add_page_child(String name) {
			page_children.add(name);
		}

		/**
		 * Starts a new page child with name of given @name. Does nothing if
		 * current page already has a child of name @name
		 * 
		 * @param name
		 *            of the page child
		 */
		public void start_page_child(String name) {
			Element e = new_element(name);
			Element page = get_last_tag("page");
			if (page == null)
				return; // no page has been started... (error ?)
			// can't have more than one of any in a single page
			if (page_child_exists(name))
				return;
			// if in a page, close current child
			end_till_page();
			// append child to last page
			page.appendChild(e);
			super.push(e);
			// notify that this page child can't occur again
			add_page_child(name);
		}

		/**
		 * Determines if currently in page tag's child
		 * 
		 * @return true/false
		 */
		public Boolean in_page_child() {
			for (String name : page_children)
				if (in_tag(name))
					return true;
			return false;
		}

		/* speech methods */

		/**
		 * Ensures that a speech tag is currently open. If one is not, it starts
		 * one. (Also ensures a line is started) Otherwise, nothing happens
		 */
		public void ensure_in_speech() {
			if (!in_tag("s")) {
				ensure_in_line();
				start_element(new_element("s"));
			}
		}
		
		/**
		 * Returns true if currently in a speech with a speaker (sp) child already
		 * false otherise
		 */
		public Boolean speech_has_speaker(){
		  /* if not in a speech, obviously false */
		  if (!in_tag("s"))
		    return false;
		  Element last_speaker = get_last_tag("sp");
		  /* if the last sp is a child of the last (current) speech*/
		  if (last_speaker != null && get_last_tag("s").equals(last_speaker.getParent()))
		    return true;
		  return false;
		}

		/**
		 * Gets the speech number of the last speech
		 * 
		 * @return speech number
		 */
		private int get_last_speech_index() {
			Element s = get_last_tag("s");
			if (s == null)
				return 0;
			if (s.getAttribute("k") != null)
				return Integer.valueOf(s.getAttributeValue("k"));
			else
				return 0;
		}

		/* element methods */
		
		public void append_before_line(){
			if (in_line()){
				Element line_parent = get_nearest_of(LINE_PARENTS);
				line_parent.insertChild(pop(), line_parent.getChildCount()-1);
			} else {
				Element e = pop();
				peekFirst().appendChild(e);
			}
		}
		
		/**
		 * Starts a new ms element. If not in a line or if the current line has
		 * an ms element of the same type, a new line is started with this ms
		 * element.
		 * 
		 * @param ln
		 *            type of ms (tln/qln/wln)
		 * @param n
		 *            number of the ms element
		 */
		public void new_ms_element(String ln, String n) {
			ensure_in_line();
			Element e = new_element("ms");
			if (ln != null)
			  e.addAttribute(new Attribute("t", ln));
			if (n != null)
			  e.addAttribute(new Attribute("n", n));
			empty_element(e);
		}

		/**
		 * If starting an element which should be the child of a line, ensures a
		 * line element is started if not in one
		 * 
		 * @param node
		 */
		private void start_element(StartNode node) {
			if (INLINE_TAGS.contains(node.getName()))
				ensure_in_line();
		}
		
		private boolean is_typeface(String str){
			return TYPEFACES.contains(str);
		}
		private Boolean is_lineParent(String str){
			return str.equals("linegroup") || LINE_PARENTS.contains(str);
		}
		private Boolean is_inline(String str){
			return INLINE_TAGS.contains(str.toUpperCase());
		}
		private Boolean is_flatten(String str){
			return FLATTEN.contains(str);
		}
		public void start_element_dont_append(Element e){
			push(e);
		}
		
		private List<Attribute> get_attributes(Element e){
			List<Attribute> atts = new ArrayList<Attribute>();
			for(int i=0 ;i<e.getAttributeCount(); i++){
			  Attribute a = e.getAttribute(i);
				atts.add(new Attribute(a.getLocalName(),a.getValue()));
			}
			return atts;
		}
		
		/**
		 * starts a new start element (<tag>)
		 * 
		 * @param e
		 *            element to start
		 */
		public void start_element(Element e) {
			if (is_typeface(e.getLocalName()) && is_typeface(peekFirst().getLocalName())){
			  renew(peekFirst().getLocalName(),get_attributes(peekFirst()));
				pop();
			} else if (is_lineParent(e.getLocalName()) && is_inline(peekFirst().getLocalName())){
			  renew(peekFirst().getLocalName(),get_attributes(peekFirst()));
				pop();
			} else if (is_flatten(e.getLocalName()) && is_flatten(peekFirst().getLocalName())){
				renew(peekFirst().getLocalName(),get_attributes(peekFirst()));
				pop();
			} 
			if (e.getLocalName().equals("quote")){
			  peekFirst().appendChild(e);
			  push(e);
			  renew_elements();
			  return;
			}
			if (is_lineParent(e.getLocalName()))
				renewing.push(new LinkedList<Element>());
			peekFirst().appendChild(e);
			push(e);
		}
		
		/**
		 * If currently in an element of name @name, ends the element and all of
		 * its children.
		 * 
		 * @param name
		 *            name of element to end
		 */
		public void end_element(String name) {
			// if being renewed and not currently in it, end renew
			// this if statement is to allow recursion (font within font for example)
			if (!renewing_has(peekFirst().getLocalName()))
					end_renew(name);
			// if not in this tag or its descendants, return (happens if being
			// renewed)
			if (!in_tag(name))
				return;
			Element e = super.pop();
			while (!e.getLocalName().equals(name))
				e = super.pop();
			// renew anything being renewed if closing element in
			// end_till_line_and_start
			if (is_typeface(name))
				renew_elements();
			else if (is_lineParent(name)){
			  // splitline doesn't get its own renewing stack
			  if (!name.equals("splitline"))
			    renewing.pop();
				renew_elements();
			}
			else if (is_flatten(name))
				renew_elements();
		}

		/**
		 * creates a new empty element (<tag/>)
		 * 
		 * @param e
		 *            element to create
		 */
		public void empty_element(Element e) {
		  peekFirst().appendChild(e);
		}

		/**
		 * Creates a new Element object of name @name with given namespace
		 * 
		 * @param name
		 *            name of element
		 * @param namespace
		 *            namespace of element
		 * @return new Element
		 */
		public Element new_element(String name, String namespace) {
			return new Element(name, namespace);
		}

		/**
		 * Creates a new Element object of name @name with correct namespace
		 * 
		 * @param name
		 *            name of element
		 * @return new Element
		 */
		public Element new_element(String name) {
		  if (name.equals("ilink") || name.equals("iembed"))
		    return new Element(name, LINK_NS);
		  return new Element(name, DOC_NS);
		}

		/**
		 * Duplicates given element e
		 * 
		 * @param e
		 *            element to duplicate
		 * @return duplicate element
		 */
		public Element new_element(Element e) {
			return new Element(e);
		}

		/**
		 * Creates an Element of name @name and adds attributes to it from the @atts
		 * 
		 * @param name
		 *            the name of the new Element
		 * @param atts
		 *            HashMap of attributes (<name,value>)
		 * @return Element created
		 */
		public Element create_element(String name, HashMap<String, String> atts) {
			Element e = new_element(name);
			for (String key : atts.keySet())
				e.addAttribute(new Attribute(key, atts.get(key)));
			return e;
		}

		/* special case elements */
		
		public void new_column(Element e){
			String n = e.getAttributeValue("n");
			if (n != null && Integer.valueOf(n) == 0)
			  empty_element(new_element("col-reset"));
			else
				empty_element(new_element("col"));
		}
		
		public void new_stanza(Element e){
			start_element(new_element("linegroup"));
			String n = e.getAttributeValue("n");
			if (n != null){
	      Element ms = new_element("ms");
	      ms.addAttribute(new Attribute("t", "stanza"));
	      ms.addAttribute(new Attribute("n", n));
	      in_next_line.push(ms);
			}
		}

		/**
		 * Creates a new quote element. If in a line, returns an XML.QUOTE
		 * element (to quote lines), otherwise returns an XML.Q element
		 * 
		 * @return created quote element
		 */
		public Element new_quote() {
			if (!in_line())
				return new_element("quote");
			else
				return new_element("q");
		}

		/**
		 * Changes the current line alignment
		 * 
		 * @param name
		 *            new alignment value if null, alignment is set to default
		 *            (nothing)
		 */
		public void new_align(String name) {
			align = name;
			if (in_line())
				change_align();
		}

		/**
		 * Places a given mode element in the corrent place. If in a line, the
		 * mode is placed before the line Otherwise, placed at current position
		 * 
		 * @param e
		 *            mode element
		 */
		public void new_mode(Element e) {
			rm_last_child("mode");
			if (in_line())
				append_to_work(e, -1);
			else
				append_to_work(e, 0);
		}

		/**
		 * Must start a line before starting a new speech
		 * 
		 * Starts a new speech element Speech is automatically given an "n"
		 * attribute to differentiate speeches
		 */
		public Boolean new_speech(Boolean real) {
		  //never a case for recursive speeches
		  if (in_tag("s"))
		    return false;
		  /* if not in a line, don't start a speech */
		  if(!peekFirst().getLocalName().equals("l")){
		    return false;
		  }
			Element e = new_element("s");
  		if (real)
  			e.addAttribute(new Attribute("k", String
  					.valueOf(get_last_speech_index() + 1)));
  		else
        e.addAttribute(new Attribute("k", String
            .valueOf(get_last_speech_index())));
  		start_element(e);
  		return true;
		}

		/* other methods */

		public Boolean in_dont_parse(){
			return DONT_PARSE_TEXT.contains(peekFirst().getLocalName());
		}
		
		/**
		 * Places an Element @e at position @pos in work's children If @pos is
		 * positive, indexes start at 1 (first child is 1) If @pos is 0, @e will
		 * be work's last child If @pos is negative, indexes from the end
		 * (second to last child is -1)
		 * 
		 * @param e
		 * @param pos
		 */
		public void append_to_work(Element e, int pos) {
			Element t = xml.getRootElement();
			if (pos <= 0)
				t.insertChild(e, t.getChildCount() + pos);
			else
				t.insertChild(e, pos - 1);
		}

		/**
		 * Returns a List of all descendants of the given node, in order,
		 * including the given parent node
		 * 
		 * @param node
		 *            whose descendants to list
		 * @return list of descendants
		 */
		public List<Element> get_all_elements(Element current) {
			List<Element> elements = new ArrayList<Element>();
			elements.add(current);
			Elements list = current.getChildElements();
			for (int i = 0; i < list.size(); i++) {
				elements.addAll(get_all_elements(list.get(i)));
			}
			return elements;
		}
		
		/**
		 * Gets the Element of given name @name at place @index
		 * @index takes integers <= 0
		 * ex. -1 will give you the second last tag
		 * 
		 * @param name
		 * 				name of the tag
		 * @param index
		 * 				index of the tag
		 * @return
		 */
		private Element get_tag_at(String name, int index){
			if (index > 0)
				return null;
			List<Element> list = get_all_elements(xml.getRootElement());
			for (int i = list.size() - 1; i >= 0; i--){
				if (list.get(i).getLocalName().equals(name)){
					if (index == 0)
						return list.get(i);
					else
						index ++;
				}
			}
			return null;
		}
		
		/**
		 * Gets the Element of the last tag with name @name
		 * 
		 * @param name
		 *            name of the Element
		 * @return the Element
		 */
		private Element get_last_tag(String name) {
			return get_tag_at(name, 0);
		}

		/**
		 * Determines if currently in a tag or one of its descendants
		 * 
		 * @param tag
		 *            name of tag
		 * @return true if in tag, false otherwise
		 */
		private boolean in_tag(String tag) {
			for (int i = 0; i < size(); i++) {
				if (get(i).getLocalName().equals(tag))
					return true;
			}
			return false;
		}
		
		/**
		 * Returns the nearest ancestor of given tag name If no ancestor of that
		 * name exists, returns null
		 * 
		 * @param tag
		 *            name of tag
		 * @return that tag's element, null if there wasn't one
		 */
		private Element get_nearest_tag(String tag){
			for (int i = 0; i < size(); i++) {
				if (get(i).getLocalName().equals(tag))
					return get(i);
			}
			return null;
		}
		
		private Element get_nearest_of(List<String> tags){
			for (int i = 0; i < size(); i++) {
				if (tags.contains(get(i).getLocalName()))
					return get(i);
			}
			return null;
		}

		/**
		 * Determines if a tag of name @name is the last child of work
		 * 
		 * @param name
		 *            @name of tag
		 * @return true if last child, otherwise
		 */
		private Boolean is_last_child(String name) {
			Element work = xml.getRootElement();
			Elements children = work.getChildElements();
			return (children.size() > 0 && children.get(children.size() - 1)
					.getLocalName().equals(name));
		}

		/**
		 * Removes the last child of work of given name @name
		 * 
		 * @param name
		 *            name of work's child to remove
		 */
		public void rm_last_child(String name) {
			Element work = xml.getRootElement();
			if (is_last_child(name))
				work.removeChild(work.getChildElements().size() - 1);
		}
		
		public void new_rdg(Element e){
		  if (in_tag("ambig"))
		    start_element(e);
		}
	}

	/**
	 * Construct an XMLWriter and send output to System.out.
	 *
	 * @throws ParserConfigurationException
	 * @throws UnsupportedEncodingException
	 */
	public XMLWriter() throws ParserConfigurationException,
			UnsupportedEncodingException {
		this(new PrintStream(System.out, true, "UTF-8"));
	}

	/**
	 * Construct an XMLWriter and send the output to the print stream.
	 *
	 * @param out
	 *            the output destination.
	 *
	 * @throws ParserConfigurationException
	 * @throws UnsupportedEncodingException
	 */
	public XMLWriter(PrintStream out) throws ParserConfigurationException,
			UnsupportedEncodingException {
		super(out);
	}

	/**
	 * Renders the given IML, and serializes to the configured PrintStream
	 */
	@Override
	public void render(DOM dom) throws TransformerConfigurationException,
			TransformerException, IOException, Exception {
		Document doc = renderToXOM(dom);
		Serializer ser = new Serializer(out);
		ser.write(doc);
	}

	/**
	 * Render the given IML
	 *
	 * @return the rendered DOM (as a XOM Document)
	 * @throws TransformerException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 */
	public Document renderToXOM(DOM dom) throws IOException, SAXException, ParserConfigurationException, TransformerException {
		// First tag must be work; will now simply ignore start work tags
		Element e = new Element("work", DOC_NS);
		Document doc = new Document(e);
		XMLStack xmlStack = new XMLStack(doc);
		xmlStack.push(e);
    DOM expanded_dom = dom.expanded();
    Node skip_till = null;

		for (Node n : expanded_dom) {
		  if (skip_till == null){
  		  if (n.getName().toUpperCase().equals("ADD"))
  		    skip_till = handle_add(n,xmlStack, expanded_dom);
  		  else
  		    parse_node(n,xmlStack);
		  }
		  else if (skip_till == n)
		    skip_till = null;
		}
		return doc;
	}
	
	private void parse_node(Node node, XMLStack xmlStack){
    /*if tag is unknown (not in schema), pass through contents without tag*/
    if (is_valid_tag(node,xmlStack)){
      switch (node.type()) {
      case COMMENT:
        break;
      case ABBR:
        throw new UnsupportedOperationException(
            "Cannot serialize depreciated abbreviation markup.");
      case EMPTY:
        parse_empty((EmptyNode) node,
            xmlStack.new_element(node.getName().toLowerCase()),
            xmlStack);
        break;
      case END:
        parse_end((EndNode) node, xmlStack);
        break;
      case START:
        parse_start((StartNode) node,
            xmlStack.new_element(node.getName().toLowerCase()),
            xmlStack);
        break;
      case TEXT:
        parse_text(node.getText(), xmlStack);
        break;
      default:
        throw new UnsupportedOperationException("Cannot convert "
            + node.getName() + " to XML");
      }
    }
	}

	private void parse_start(StartNode node, Element e, XMLStack xmlStack) {
		xmlStack.start_element(node);
		String xml_name = node.getName().toLowerCase();
		switch (node.getName().toUpperCase()) {
    case "MARG":
      if (xmlStack.in_line())
        xmlStack.end_till_line_and_start(set_attributes(node, e));
      else
        xmlStack.start_element(set_attributes(node, e));
    break;    
		case "SECTION":
      xmlStack.end_line();
      xmlStack.empty_element(set_attributes(node,
          xmlStack.new_element("div"),map_put(new_map(), "n", "name"),null,null));
      break;
		case "INDENT":
			xmlStack.ensure_in_line();
			xmlStack.empty_element(set_attributes(node,
					xmlStack.new_element("space"),map_put(new_map(), "n", "l"),null,
					new Attribute[] { new Attribute("t", "formatting") }));
			break;
		case "IEMBED":
		  xmlStack.ensure_in_line();
			xmlStack.start_element(set_attributes(node,
					xmlStack.new_element(xml_name, LINK_NS)));
			break;
		case "ILINK":
			xmlStack.ensure_in_line();
			xmlStack.start_element(set_attributes(node,
					xmlStack.new_element(xml_name, LINK_NS)));
			break;
		case "I":
		case "SUP":
		case "SUB":
			xmlStack.ensure_in_line();
			xmlStack.start_element(set_attributes(node,
					xmlStack.new_element(xml_name)));
			break;
		case "CW":
		case "SIG":
		case "RT":
		case "PN":
			xmlStack.start_page_child(xml_name);
			break;
		case "DIV":
		case "BACKMATTER":
		case "FRONTMATTER":
		case "ACT":
		case "SCENE":
			xmlStack.end_line();
			node.deleteAttribute("link"); /* deprecated */
			xmlStack.empty_element(set_attributes(node, e));
			break;
		case "COL":
			xmlStack.end_line();
			xmlStack.new_column(set_attributes(node,e));
			break;
		case "SD":
			xmlStack.end_till_line_and_start(set_attributes(node, e));
			break;
		case "S":
			xmlStack.new_speech(true);
			break;
		case "MODE":
			xmlStack.new_mode(set_attributes(node, e));
			break;
		case "LABEL":
			xmlStack.start_element_dont_append(set_attributes(node, e));
			break;
		case "WORK":
			break;
		// alignments
		case "RA":
		case "C":
		case "J":
			xmlStack.new_align(XML_MAP.get(node.getName()));
			break;
		case "SP":
			if (xmlStack.speech_has_speaker())
			  xmlStack.end_element("s");
      xmlStack.ensure_in_speech();
			xmlStack.start_element(set_attributes(node, e));
			break;
		case "PAGE":
		case "LINEGROUP":
		  xmlStack.end_line();
			xmlStack.start_element(set_attributes(node, e));
			xmlStack.end_page();
			break;
    case "VERSEQUOTE":
		case "PROSEQUOTE":
		case "QUOTE":
		  xmlStack.end_line();
			xmlStack.start_element(set_attributes(node, xmlStack.new_quote()));
			break;
		case "ORNAMENT":
			xmlStack.start_element(set_attributes(node, e, null,
					new String[] { "letter" }, null));
			break;
		case "STANZA":
		  xmlStack.end_line();
			xmlStack.new_stanza(set_attributes(node, e));
			break;
		case "ISEHEADER":
		  break;
		case "BRACEGROUP":
		  xmlStack.end_line();
      xmlStack.start_element(set_attributes(node, e));
      break;
		case "RDG":
		  xmlStack.new_rdg(set_attributes(node, e));
		  break;
		  /*
		case "ADD":
		  xmlStack.new_add(node, set_attributes(node, e));
		  break;*/
	  //no tags for now; content goes straight through
		case "ACCENT":
		case "UNICODE":
		case "LIG":
		case "DIGRAPH":
		case "VAR":
		case "TYPEFORM":
    case "TITLEHEAD":
		  break;
		default:
			xmlStack.start_element(set_attributes(node, e));
			break;
		}
	}

	private void parse_end(EndNode node, XMLStack xmlStack){
		String xml_name = node.getName().toLowerCase();
   		switch (node.getName().toUpperCase()){
   		case "VERSEQUOTE":
   		case "PROSEQUOTE":
        xmlStack.end_element("quote");
        break;
   		case "QUOTE":
   			xmlStack.end_element(xml_name);
   			break;
   		case "MODE":
        	xmlStack.append_to_work(xmlStack.create_element(xml_name,map_put(new_map(),"t","uncertain")), 0);
        	break;
   		case "FRONTMATTER":
   			xmlStack.end_element(xml_name);
   			xmlStack.end_line();
   			xmlStack.empty_element(xmlStack.new_element("main"));
   			break;
   		case "RA":
   		case "C":
   		case "J":
   			xmlStack.align = null;
   			break;
   		case "PAGE":
   			xmlStack.end_page();
   			break;
   		case "WORK":
   			xmlStack.rm_last_child(xml_name);
   			break;
   		case "LABEL":
   			xmlStack.append_before_line();
   			break;
      case "STANZA":
        xmlStack.end_element("linegroup");
        break;
   		default:
   			xmlStack.end_element(xml_name);
   			break;
   		}
	}

	private void parse_empty(EmptyNode node, Element e, XMLStack xmlStack) {
		String xml_name = node.getName().toLowerCase();
		switch (node.getName().toUpperCase()) {
    case "IEMBED":
      xmlStack.ensure_in_line();
      xmlStack.empty_element(set_attributes(node,
          xmlStack.new_element(xml_name, LINK_NS)));
      break;
    case "ILINK":
      xmlStack.ensure_in_line();
      xmlStack.empty_element(set_attributes(node,
          xmlStack.new_element(xml_name, LINK_NS)));
      break;
		case "SHY":
			xmlStack.empty_element(xmlStack.new_element(xml_name));
			break;
		case "L":
			xmlStack.end_line();
			xmlStack.new_line(node, true);
			break;
    case "MS":
      xmlStack.ensure_in_line();
      xmlStack.empty_element(set_attributes(node,e));
      break;
		case "QLN":
		case "WLN":
		case "TLN":
			xmlStack.new_ms_element(xml_name, node.getAttribute("n"));
			break;
		case "RULE":
			xmlStack.end_line();
			xmlStack.empty_element(set_attributes(node, e,
					map_put(new_map(), "n", "l"), null, null));
			break;
		case "SPACE":
			xmlStack.ensure_in_line();
			Element sp_e = set_attributes(node, e,
					map_put(new_map(), "n", "l"), new String[] {"setting"}, null);
			xmlStack.empty_element(sp_e);
			/* add extra space */
			sp_e.appendChild(new Text(" "));
			break;
		case "BR":
			xmlStack.end_line();
			xmlStack.new_line(new EmptyNode(), false);
			break;
		}
	}
	
	private static String trim_whitespace(String str){
		str.replace(" ", "");
		str.replace("\t", "");
		str.replace("\r", "");
		return str;
	}

	/**
	 * Parses a text node for newline characters and splits splits the text node
	 * into separate nodes placed in their own lines. First node is added to the
	 * current line (if in one) Last line is not ended
	 * 
	 * @param t
	 *            text node
	 * @param xmlStack
	 *            XML stack
	 * @return true if parsed, false otherwise (in page/no newline characters)
	 */
	private static void parse_text(String text, XMLStack xmlStack) {
		/*
		 * if text is all whitespace or in an element which should not be parsed
		 * element, don't parse further
		 */
		if (trim_whitespace(text).isEmpty() || xmlStack.in_dont_parse()){
			xmlStack.append_to_line(text);
			return;
		}
		String[] lines = null;
		lines = text.split("\\n+");
		/* if the text only contains a newline */
		if (lines.length == 0){
			xmlStack.end_line();
			return;
		}
		if (lines[0].equals(""))
			xmlStack.end_line();
		/* if not in a line, start one; text can not be the child of work */
		xmlStack.ensure_in_line();
		/* if there is no newline character, don't parse further */
		if (!text.contains("\n")){
			xmlStack.append_to_line(text);
			return;
		}
		for (int i = 0; i < lines.length; i++) {
			/* end empty lines */
			if (normalizeBoundary(lines[i]).isEmpty()){
				continue;
			}
			/* add each line as its own line */
			xmlStack.ensure_in_line();
			xmlStack.append_to_line(lines[i]);
			/* don't end the last line*/
			if (i < (lines.length - 1))
				xmlStack.end_line();
		}
		/* if the text ends in a newline character, end the last line */
		if (text.charAt(text.length()-1) == '\n')
		  xmlStack.end_line();
	}
	
	/**
	 * Parses an add tag and handles its entire contents
	 * 
	 * @param node
	 * @param xmlStack
	 * @return the last node handled; caller should skip to there
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private Node handle_add(Node node, XMLStack xmlStack, DOM dom) throws IOException, SAXException, ParserConfigurationException, TransformerException{
	  Element e = new Element("work", DOC_NS);
	  Document doc = new Document(e);
    XMLStack addStack = new XMLStack(doc);
    addStack.push(e);
    //tags between the start and end tags of this ADD
    Node end_tag = dom.find_forward(node, node.getName());
    List<Node> nodes = dom.get_between(node,end_tag);
    for(Node n : nodes){
      parse_node(n,addStack);
    }
    Elements elements = doc.getRootElement().getChildElements();
    Element head = xmlStack.peekFirst();
    for (int i=0; i<elements.size(); i++){
      elements.get(i).detach();
      Element child = elements.get(i);
      if (child.getLocalName().equals("l")){
        /*  l becomes an add */
        child.setLocalName("add");
        /* create new l */
        Element line = addStack.new_element("l");
        /* move attributes to new line */
        for(int j=0; j<child.getAttributeCount(); j++){
          Attribute a = child.getAttribute(j);
          a.detach();
          line.addAttribute(a);
        }
        /* set add's attributes */
        child = set_attributes((StartNode)node,child);
        /* wrap new add in new l */
        line.appendChild(child);
        head.appendChild(line);
        /* if on the last item in the ADD and it's a line, push it on the stack (leave it open) */
        if (i == elements.size() - 1)
          xmlStack.push(line);
      }else{
        Element add = set_attributes((StartNode)node, addStack.new_element("add"));
        add.appendChild(child);
        head.appendChild(add);
      }
      /* if the head of xmlStack is a line, end it and make head its parent */
      if (head.getLocalName().equals("l"))
        xmlStack.pop();
        head = xmlStack.peekFirst();
    }
    return end_tag;
  }

	/**
	 * wrapper for creating a HashMap
	 * 
	 * @return new HashMap<String,String>
	 */
	private HashMap<String, String> new_map() {
		return new HashMap<String, String>();
	}

	/**
	 * Wrapper for putting 2 strings into a HashMap<String,String>
	 * 
	 * @param hm
	 *            HashMap
	 * @param s1
	 *            first string
	 * @param s2
	 *            second string
	 * @return HashMap (@hm)
	 */
	private HashMap<String, String> map_put(HashMap<String, String> hm,
			String s1, String s2) {
		hm.put(s1, s2);
		return hm;
	}

	/**
	 * Sets the attributes of @node to @e
	 * 
	 * @param node
	 *            node with source attributes
	 * @param e
	 *            destination element
	 * @return Element (@e)
	 */
	private Element set_attributes(TagNode node, Element e) {
		return set_attributes(node, e, null, null, null);
	}

	/**
	 * Applies attributes to a given Element @e All attributes in @node are
	 * added to @e with the following caveats: Attributes in @node with a name
	 * that is a key to @changes have their names changed to the name in @changes
	 * corresponding to that name key Attributes in @node with a name in @ignore
	 * are ignored entirely All attributes in @add are also added to @e
	 * 
	 * @param node
	 *            node to copy attributes from
	 * @param e
	 *            destination Element to which attributes are added
	 * @param changes
	 *            HashMap of attributes whose values are to be changed
	 * @param ignore
	 *            Attribute names to be ignored
	 * @param add
	 *            extra Attributes to be added to @e
	 * @return Element (@e)
	 */
	private Element set_attributes(TagNode node, Element e,
			HashMap<String, String> changes, String[] ignore, Attribute[] add) {
		for (String name : node.getAttributeNames()) {
			if (ignore != null && Arrays.asList(ignore).contains(name))
				continue;
			if (changes != null && changes.containsKey(name))
				e.addAttribute(new Attribute(changes.get(name), node
						.getAttribute(name).replace(',', ' ')));
			else
				e.addAttribute(new Attribute(name, node.getAttribute(name)
						.replace(',', ' ')));
		}
		if (add != null)
			for (Attribute attr : add)
				e.addAttribute(attr);
		return e;
	}
	
	/**
	 * determines whether or not the given node is valid with respect to the schema (recognized)
	 * @n the node to check
	 * @return validity
	 */
	private static Boolean is_valid_tag(Node n, XMLStack xmlStack){
	  if (n.type().equals(NodeType.TEXT))
	    return true;
	  String name = n.getName().toLowerCase();
	  /* if the tag is in the schema */
	  if (xmlStack.VALID_TAGS.contains(name)){
	    if (xmlStack.schema.getTag(name).isDepreciated())
	      return false;
	    if (xmlStack.schema.getTag(name).getEmpty().equals("optional"))
	      return true;
	    /* if the given tag and its tag in the schema have different types */
      if (xmlStack.schema.getTag(name).isEmpty() ^ n.type().equals(NodeType.EMPTY)){
        return false;
      }
	    return true;
	  }
	  return false;
	}

	/**
	 * removes bounding white space (strings which are entirely whitespace)
	 * 
	 * @param data
	 * @return " " if boundary, @data otherwise
	 */
	private static String normalizeBoundary(String data) {
		String temp = normalizeSpace(data);
		if (temp.equals(" ") || temp.equals(""))
			return temp;
		return data;
	}

	private static String normalizeSpace(String data) {
		return data.replaceAll("\\p{Space}+", " ").trim();
	}
	
	@Override
	public void render(DOM dom, Annotation ann) throws TransformerConfigurationException, TransformerException, IOException, Exception {
		throw new UnsupportedOperationException("Not supported.");
	}
}

