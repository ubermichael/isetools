/*
 * Copyright (C) 2016 Malcolm Moran
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
package ca.nines.ise.transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

/**
 * Converts deprecated tags to correct tags
 *
 */
public class DeprecatedTransformer extends IdentityTransform {
  
  String last_tag;
  
  public DOM transform(DOM dom) throws IOException {
    last_tag = "";
    return super.transform(dom);
  }
  
  public void text(TextNode n) {
    //if the last tag was removed entirely, 
    //don't add the following whitespace
    if (n.getText().trim().equals("") && 
        (last_tag.toLowerCase().equals("link") || 
        last_tag.toLowerCase().equals("meta") || 
        last_tag.toLowerCase().equals("iseheader")))
      return;
    else
        dom.add(n);
  }
  
  @Override
  public void defaultTransform(Node n) {
    last_tag = n.getName();
    dom.add(n);
  }
  
  public void empty_link(EmptyNode n) {
    last_tag = n.getName();
    //dom.add(n);
  }
  
  public void empty_meta(EmptyNode n) {
    last_tag = n.getName();
    //dom.add(n);
  }

 
  public void empty_space(EmptyNode n) {
    last_tag = n.getName();
    String l = n.getAttribute("n");
	  n.deleteAttribute("n");
	  n.setAttribute("l",l);
    dom.add(n);
  }

 
  public void end_blockquote(EndNode n) {
    last_tag = n.getName();
    n.setName("QUOTE");  
    dom.add(n);
  }
  
  
  public void end_fontgroup(EndNode n) {
    last_tag = n.getName();
    n.setName("FONT");
    dom.add(n);
  }

  
  public void end_h1(EndNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }

  
  public void end_h2(EndNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }

  
  public void end_h3(EndNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }

  
  public void end_h4(EndNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }

  
  public void end_h5(EndNode n) {
    last_tag = n.getName();
    n.setName("LD");
	  dom.add(n);
  }

  
  public void end_h6(EndNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }
  
  
  public void end_iseheader(EndNode n) {
    last_tag = n.getName();
    //dom.add(n);
  }
  
  
  public void end_poem(EndNode n) {
    last_tag = n.getName();
    n.setName("DIV");
    dom.add(n);
  }

  
  public void end_prosequote(EndNode n) {
    last_tag = n.getName();
    n.setName("QUOTE");  
    dom.add(n);
  }

  
  public void end_section(EndNode n) {
    last_tag = n.getName();
    n.setName("DIV");
    dom.add(n);
  }

  
  public void end_titlehead(EndNode n) {
    last_tag = n.getName();
    n.setName("TITLE");
    dom.add(n);
  }

  
  public void end_versequote(EndNode n) {
    last_tag = n.getName();
    n.setName("QUOTE");  
    dom.add(n);
  }

  
  public void start_blockquote(StartNode n) {
    last_tag = n.getName();
    n.setName("QUOTE");  		
    dom.add(n);
  }
  
  
  public void start_fontgroup(StartNode n) {
    last_tag = n.getName();
    n.setName("FONT");
    String size = n.getAttribute("n");
    n.deleteAttribute("n");
    n.setAttribute("size", size);
    dom.add(n);
  }
  
  
  public void start_h1(StartNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }

  
  public void start_h2(StartNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }

  
  public void start_h3(StartNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }

  
  public void start_h4(StartNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }

  
  public void start_h5(StartNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }

  
  public void start_h6(StartNode n) {
    last_tag = n.getName();
    n.setName("LD");
    dom.add(n);
  }
  
  
  public void start_indent(StartNode n) {
    last_tag = n.getName();
    String l = n.getAttribute("n");
    n.deleteAttribute("n");
    n.setAttribute("l",l);
    dom.add(n);
  }

  
  public void start_iseheader(StartNode n) {
    last_tag = n.getName();
    //dom.add(n);
  }

  
  public void start_linegroup(StartNode n) {
    last_tag = n.getName();
    String form = n.getAttribute("form");
    n.deleteAttribute("form");
    n.setAttribute("t", form);
    dom.add(n);
  }

  
  public void start_poem(StartNode n) {
    last_tag = n.getName();
    n.setName("DIV");
    dom.add(n);
  }
  
  
  public void start_prosequote(StartNode n) {
    last_tag = n.getName();
    EmptyNode en = new EmptyNode();
  	en.setName("MODE");
  	en.setAttribute("t", "prose");
  	dom.add(en);
  	n.setName("QUOTE");  
  	dom.add(n);
  }

  
  public void start_section(StartNode n) {
    last_tag = n.getName();
  	n.setName("DIV");
  	String name = n.getAttribute("n");
  	n.deleteAttribute("n");
  	n.setAttribute("name", name);
  	dom.add(n);
  }

  
  public void start_titlehead(StartNode n) {
    last_tag = n.getName();
    n.setName("TITLE");
    dom.add(n);
  }

  
  public void start_titlepage(StartNode n) {
    last_tag = n.getName();
  	n.setName("DIV");
  	n.setAttribute("name", "Title page");
    dom.add(n);
  }

  
  public void start_versequote(StartNode n) {
    last_tag = n.getName();
  	EmptyNode en = new EmptyNode();
  	en.setName("MODE");
  	en.setAttribute("t", "verse");
  	dom.add(en);
  	n.setName("QUOTE");  
    dom.add(n);
  }

}
