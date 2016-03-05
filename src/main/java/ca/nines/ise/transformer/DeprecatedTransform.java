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

import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

/**
 * Converts deprecated tags to correct tags
 *
 */
public class DeprecatedTransform extends IdentityTransform {
  @Override
  public void empty_link(EmptyNode n) {
    //dom.add(n);
  }

  @Override
  public void empty_meta(EmptyNode n) {
    //dom.add(n);
  }

  @Override
  public void empty_space(EmptyNode n) {
    String l = n.getAttribute("n");
	  n.deleteAttribute("n");
	  n.setAttribute("l",l);
    dom.add(n);
  }

  @Override
  public void end_blockquote(EndNode n) {
    n.setName("QUOTE");  
    dom.add(n);
  }
  
  @Override
  public void end_fontgroup(EndNode n) {
    n.setName("FONT");
    dom.add(n);
  }

  @Override
  public void end_h1(EndNode n) {
    n.setName("LD");
    dom.add(n);
  }

  @Override
  public void end_h2(EndNode n) {
    n.setName("LD");
    dom.add(n);
  }

  @Override
  public void end_h3(EndNode n) {
    n.setName("LD");
    dom.add(n);
  }

  @Override
  public void end_h4(EndNode n) {
    n.setName("LD");
    dom.add(n);
  }

  @Override
  public void end_h5(EndNode n) {
    n.setName("LD");
	  dom.add(n);
  }

  @Override
  public void end_h6(EndNode n) {
    n.setName("LD");
    dom.add(n);
  }
  
  @Override
  public void end_iseheader(EndNode n) {
    //dom.add(n);
  }
  
  @Override
  public void end_poem(EndNode n) {
    n.setName("DIV");
    dom.add(n);
  }

  @Override
  public void end_prosequote(EndNode n) {
    n.setName("QUOTE");  
    dom.add(n);
  }

  @Override
  public void end_section(EndNode n) {
    n.setName("DIV");
    dom.add(n);
  }

  @Override
  public void end_titlehead(EndNode n) {
    n.setName("TITLE");
    dom.add(n);
  }

  @Override
  public void end_versequote(EndNode n) {
    n.setName("QUOTE");  
    dom.add(n);
  }

  @Override
  public void start_blockquote(StartNode n) {
    n.setName("QUOTE");  		
    dom.add(n);
  }
  
  @Override
  public void start_fontgroup(StartNode n) {
    n.setName("FONT");
    String size = n.getAttribute("n");
    n.deleteAttribute("n");
    n.setAttribute("size", size);
    dom.add(n);
  }
  
  @Override
  public void start_h1(StartNode n) {
    n.setName("LD");
    dom.add(n);
  }

  @Override
  public void start_h2(StartNode n) {
    n.setName("LD");
    dom.add(n);
  }

  @Override
  public void start_h3(StartNode n) {
    n.setName("LD");
    dom.add(n);
  }

  @Override
  public void start_h4(StartNode n) {
    n.setName("LD");
    dom.add(n);
  }

  @Override
  public void start_h5(StartNode n) {
    n.setName("LD");
    dom.add(n);
  }

  @Override
  public void start_h6(StartNode n) {
    n.setName("LD");
    dom.add(n);
  }
  
  @Override
  public void start_indent(StartNode n) {
    String l = n.getAttribute("n");
    n.deleteAttribute("n");
    n.setAttribute("l",l);
    dom.add(n);
  }

  @Override
  public void start_iseheader(StartNode n) {
    //dom.add(n);
  }

  @Override
  public void start_linegroup(StartNode n) {
    String form = n.getAttribute("form");
    n.deleteAttribute("form");
    n.setAttribute("t", form);
    dom.add(n);
  }

  @Override
  public void start_poem(StartNode n) {
    n.setName("DIV");
    dom.add(n);
  }
  
  @Override
  public void start_prosequote(StartNode n) {
    EmptyNode en = new EmptyNode();
  	en.setName("MODE");
  	en.setAttribute("t", "prose");
  	dom.add(en);
  	n.setName("QUOTE");  
  	dom.add(n);
  }

  @Override
  public void start_section(StartNode n) {
  	n.setName("DIV");
  	String name = n.getAttribute("n");
  	n.deleteAttribute("n");
  	n.setAttribute("name", name);
  	dom.add(n);
  }

  @Override
  public void start_titlehead(StartNode n) {
    n.setName("TITLE");
    dom.add(n);
  }

  @Override
  public void start_titlepage(StartNode n) {
  	n.setName("DIV");
  	n.setAttribute("name", "Title page");
    dom.add(n);
  }

  @Override
  public void start_versequote(StartNode n) {
  	EmptyNode en = new EmptyNode();
  	en.setName("MODE");
  	en.setAttribute("t", "verse");
  	dom.add(en);
  	n.setName("QUOTE");  
    dom.add(n);
  }

}
