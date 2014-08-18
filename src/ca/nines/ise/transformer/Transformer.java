/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
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

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;
import java.io.IOException;
import org.apache.commons.collections4.iterators.PeekingIterator;

/**
 * Transformers change on DOM into another DOM. 
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
abstract public class Transformer {
  
  /**
   * Iterator which can look ahead by one node.
   */
  private PeekingIterator<Node> iterator;

  /**
   * DOM being constructed.
   */
  DOM dom;

  /**
   * Visit an abbreviation node.
   * 
   * @param n Node to visit 
   */
  abstract public void abbreviation(AbbrNode n);

  /**
   * Visit a character node.
   * @param n Node to visit 
   */
  abstract public void character(CharNode n);

  /**
   * Visit a comment node.
   * 
   * @param n Node to visit 
   */
  abstract public void comment(CommentNode n);

  /**
   * Visit a bl tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_bl(EmptyNode n);

  /**
   * Visit an empty br tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_br(EmptyNode n);

  /**
   * Visit an empty l tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_l(EmptyNode n);

  /**
   * Visit an empty lb tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_lb(EmptyNode n);

  /**
   * Visit an empty link tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_link(EmptyNode n);

  /**
   * Visit an empty meta tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_meta(EmptyNode n);

  /**
   * Visit an empty ornament tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_ornament(EmptyNode n);

  /**
   * Visit an empty prop tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_prop(EmptyNode n);

  /**
   * Visit an empty qln tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_qln(EmptyNode n);

  /**
   * Visit an empty rule tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_rule(EmptyNode n);

  /**
   * Visit an empty space tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_space(EmptyNode n);

  /**
   * Visit an empty tln tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_tln(EmptyNode n);

  /**
   * Visit an empty wln tag node.
   * 
   * @param n Node to visit 
   */
  abstract public void empty_wln(EmptyNode n);

  /**
   * Visit an end abbr node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_abbr(EndNode n);

  /**
   * Visit an end act node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_act(EndNode n);

  /**
   * Visit an end add node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_add(EndNode n);

  /**
   * Visit an end ambig node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_ambig(EndNode n);

  /**
   * Visit an end backmatter node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_backmatter(EndNode n);

  /**
   * Visit an end bll node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_bll(EndNode n);

  /**
   * Visit an end blockquote node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_blockquote(EndNode n);

  /**
   * Visit an end c node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_c(EndNode n);

  /**
   * Visit an end cl node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_cl(EndNode n);

  /**
   * Visit an end col node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_col(EndNode n);

  /**
   * Visit an end cw node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_cw(EndNode n);

  /**
   * Visit an end div node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_div(EndNode n);

  /**
   * Visit an end em node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_em(EndNode n);

  /**
   * Visit an end epilogue node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_epilogue(EndNode n);

  /**
   * Visit an end fontgroup node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_fontgroup(EndNode n);

  /**
   * Visit an end foreign node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_foreign(EndNode n);

  /**
   * Visit an end frontmatter node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_frontmatter(EndNode n);

  /**
   * Visit an end h1 node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_h1(EndNode n);

  /**
   * Visit an end h2 node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_h2(EndNode n);

  /**
   * Visit an end h3 node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_h3(EndNode n);

  /**
   * Visit an end h4 node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_h4(EndNode n);

  /**
   * Visit an end h5 node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_h5(EndNode n);

  /**
   * Visit an end h6 node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_h6(EndNode n);

  /**
   * Visit an end hw node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_hw(EndNode n);

  /**
   * Visit an end i node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_i(EndNode n);

  /**
   * Visit an end iembed node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_iembed(EndNode n);

  /**
   * Visit an end indent node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_indent(EndNode n);

  /**
   * Visit an end iseheader node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_iseheader(EndNode n);

  /**
   * Visit an end j node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_j(EndNode n);

  /**
   * Visit an end ld node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_ld(EndNode n);

  /**
   * Visit an end lem node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_lem(EndNode n);

  /**
   * Visit an end linegroup node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_linegroup(EndNode n);

  /**
   * Visit an end link node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_link(EndNode n);

  /**
   * Visit an end ls node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_ls(EndNode n);

  /**
   * Visit an end marg node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_marg(EndNode n);

  /**
   * Visit an end mode node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_mode(EndNode n);

  /**
   * Visit an end ornament node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_ornament(EndNode n);

  /**
   * Visit an end page node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_page(EndNode n);

  /**
   * Visit an end pn node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_pn(EndNode n);

  /**
   * Visit an end poem node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_poem(EndNode n);

  /**
   * Visit an end prologue node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_prologue(EndNode n);

  /**
   * Visit an end prop node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_prop(EndNode n);

  /**
   * Visit an end prosequote node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_prosequote(EndNode n);

  /**
   * Visit an end quote node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_quote(EndNode n);

  /**
   * Visit an end r node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_r(EndNode n);

  /**
   * Visit an end ra node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_ra(EndNode n);

  /**
   * Visit an end rdg node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_rdg(EndNode n);

  /**
   * Visit an end rt node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_rt(EndNode n);

  /**
   * Visit an end s node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_s(EndNode n);

  /**
   * Visit an end sc node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_sc(EndNode n);

  /**
   * Visit an end scene node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_scene(EndNode n);

  /**
   * Visit an end sd node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_sd(EndNode n);

  /**
   * Visit an end section node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_section(EndNode n);

  /**
   * Visit an end sig node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_sig(EndNode n);

  /**
   * Visit an end sp node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_sp(EndNode n);

  /**
   * Visit an end stanza node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_stanza(EndNode n);

  /**
   * Visit an end sub node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_sub(EndNode n);

  /**
   * Visit an end sup node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_sup(EndNode n);

  /**
   * Visit an end swash node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_swash(EndNode n);

  /**
   * Visit an end titlhead node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_titlehead(EndNode n);

  /**
   * Visit an end titlepage node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_titlepage(EndNode n);

  /**
   * Visit an end versequote node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_versequote(EndNode n);

  /**
   * Visit an end work node.
   * 
   * @param n Node to visit 
   */
  abstract public void end_work(EndNode n);

  /**
   * Visit an start abbr node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_abbr(StartNode n);

  /**
   * Visit an start act node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_act(StartNode n);

  /**
   * Visit an start add node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_add(StartNode n);

  /**
   * Visit an start ambig node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_ambig(StartNode n);

  /**
   * Visit an start backmatter node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_backmatter(StartNode n);

  /**
   * Visit an start bll node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_bll(StartNode n);

  /**
   * Visit an start blockquote node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_blockquote(StartNode n);

  /**
   * Visit an start c node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_c(StartNode n);

  /**
   * Visit an start cl node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_cl(StartNode n);

  /**
   * Visit an start col node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_col(StartNode n);

  /**
   * Visit an start cw node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_cw(StartNode n);

  /**
   * Visit an start div node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_div(StartNode n);

  /**
   * Visit an start em node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_em(StartNode n);

  /**
   * Visit an start epilogue node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_epilogue(StartNode n);

  /**
   * Visit an start fontgroup node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_fontgroup(StartNode n);

  /**
   * Visit an start foreign node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_foreign(StartNode n);

  /**
   * Visit an start frontmatter node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_frontmatter(StartNode n);

  /**
   * Visit an start h1 node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_h1(StartNode n);

  /**
   * Visit an start h2 node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_h2(StartNode n);

  /**
   * Visit an start h3 node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_h3(StartNode n);

  /**
   * Visit an start h4 node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_h4(StartNode n);

  /**
   * Visit an start h5 node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_h5(StartNode n);

  /**
   * Visit an start h6 node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_h6(StartNode n);

  /**
   * Visit an start hw node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_hw(StartNode n);

  /**
   * Visit an start i node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_i(StartNode n);

  /**
   * Visit an start iembed node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_iembed(StartNode n);

  /**
   * Visit an start indent node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_indent(StartNode n);

  /**
   * Visit an start iseheader node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_iseheader(StartNode n);

  /**
   * Visit an start j node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_j(StartNode n);

  /**
   * Visit an start ld node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_ld(StartNode n);

  /**
   * Visit an start lem node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_lem(StartNode n);

  /**
   * Visit an start linegroup node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_linegroup(StartNode n);

  /**
   * Visit an start link node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_link(StartNode n);

  /**
   * Visit an start ls node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_ls(StartNode n);

  /**
   * Visit an start marg node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_marg(StartNode n);

  /**
   * Visit an start mode node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_mode(StartNode n);

  /**
   * Visit an start ornament node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_ornament(StartNode n);

  /**
   * Visit an start page node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_page(StartNode n);

  /**
   * Visit an start pn node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_pn(StartNode n);

  /**
   * Visit an start poem node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_poem(StartNode n);

  /**
   * Visit an start prologue node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_prologue(StartNode n);

  /**
   * Visit an start prop node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_prop(StartNode n);

  /**
   * Visit an start prosequote node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_prosequote(StartNode n);

  /**
   * Visit an start quote node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_quote(StartNode n);

  /**
   * Visit an start r node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_r(StartNode n);

  /**
   * Visit an start ra node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_ra(StartNode n);

  /**
   * Visit an start rdg node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_rdg(StartNode n);

  /**
   * Visit an start rt node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_rt(StartNode n);

  /**
   * Visit an start s node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_s(StartNode n);

  /**
   * Visit an start sc node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_sc(StartNode n);

  /**
   * Visit an start scene node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_scene(StartNode n);

  /**
   * Visit an start sd node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_sd(StartNode n);

  /**
   * Visit an start section node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_section(StartNode n);

  /**
   * Visit an start sig node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_sig(StartNode n);

  /**
   * Visit an start sp node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_sp(StartNode n);

  /**
   * Visit an start stanza node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_stanza(StartNode n);

  /**
   * Visit an start sub node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_sub(StartNode n);

  /**
   * Visit an start sup node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_sup(StartNode n);

  /**
   * Visit an start swash node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_swash(StartNode n);

  /**
   * Visit an start titlhead node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_titlehead(StartNode n);

  /**
   * Visit an start titlepage node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_titlepage(StartNode n);

  /**
   * Visit an start versequote node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_versequote(StartNode n);

  /**
   * Visit an start work node.
   * 
   * @param n Node to visit 
   */
  abstract public void start_work(StartNode n);

  /**
   * Visit a text node.
   * 
   * @param n Node to visit 
   */
  public abstract void text(TextNode n);

  /**
   * Transform a DOM into another DOM.
   * 
   * @param dom
   * @return the new, transformed dom.
   * @throws IOException 
   */
  public DOM transform(DOM dom) throws IOException {
    this.dom = new DOM();
    this.dom.setLines(dom.getLines());
    this.dom.setSource(dom.getSource());
    iterator = new PeekingIterator(dom.iterator());
    
    while (iterator.hasNext()) {
      Node n = iterator.next();
      dispatch(n);
    }
    return this.dom;
  }

  /**
   * Figure out what to do, based on the node being visited.
   * 
   * @param n Node to visit Node being visited
   */
  private void dispatch(Node n) {
    switch (n.type()) {
      case ABBR:
        abbreviation((AbbrNode) n);
        break;
      case CHAR:
        character((CharNode) n);
        break;
      case COMMENT:
        comment((CommentNode) n);
        break;
      case END:
        dispatch_end((EndNode) n);
        break;
      case EMPTY:
        dispatch_empty((EmptyNode) n);
        break;
      case START:
        dispatch_start((StartNode) n);
        break;
      case TEXT:
        text((TextNode) n);
        break;
    }
  }

  /**
   * Call the right method for an empty node.
   * 
   * @param n Node to visit EmptyNode being visited
   */
  private void dispatch_empty(EmptyNode n) {
    switch (n.getName().toLowerCase()) {
      case "bl":
        empty_bl(n);
        break;
      case "br":
        empty_br(n);
        break;
      case "l":
        empty_l(n);
        break;
      case "lb":
        empty_lb(n);
        break;
      case "link":
        empty_link(n);
        break;
      case "meta":
        empty_meta(n);
        break;
      case "ornament":
        empty_ornament(n);
        break;
      case "prop":
        empty_prop(n);
        break;
      case "qln":
        empty_qln(n);
        break;
      case "rule":
        empty_rule(n);
        break;
      case "space":
        empty_space(n);
        break;
      case "tln":
        empty_tln(n);
        break;
      case "wln":
        empty_wln(n);
        break;
      default:
        // @TODO throw an error here.
        break;
    }
  }

  /**
   * Call the right method for an end node.
   * 
   * @param n Node to visit EndNode being visited.
   */
  private void dispatch_end(EndNode n) {
    switch (n.getName().toLowerCase()) {
      case "abbr":
        end_abbr(n);
        break;
      case "act":
        end_act(n);
        break;
      case "add":
        end_add(n);
        break;
      case "ambig":
        end_ambig(n);
        break;
      case "backmatter":
        end_backmatter(n);
        break;
      case "bll":
        end_bll(n);
        break;
      case "blockquote":
        end_blockquote(n);
        break;
      case "c":
        end_c(n);
        break;
      case "cl":
        end_cl(n);
        break;
      case "col":
        end_col(n);
        break;
      case "cw":
        end_cw(n);
        break;
      case "div":
        end_div(n);
        break;
      case "em":
        end_em(n);
        break;
      case "epilogue":
        end_epilogue(n);
        break;
      case "fontgroup":
        end_fontgroup(n);
        break;
      case "foreign":
        end_foreign(n);
        break;
      case "frontmatter":
        end_frontmatter(n);
        break;
      case "h1":
        end_h1(n);
        break;
      case "h2":
        end_h2(n);
        break;
      case "h3":
        end_h3(n);
        break;
      case "h4":
        end_h4(n);
        break;
      case "h5":
        end_h5(n);
        break;
      case "h6":
        end_h6(n);
        break;
      case "hw":
        end_hw(n);
        break;
      case "i":
        end_i(n);
        break;
      case "iembed":
        end_iembed(n);
        break;
      case "indent":
        end_indent(n);
        break;
      case "iseheader":
        end_iseheader(n);
        break;
      case "j":
        end_j(n);
        break;
      case "ld":
        end_ld(n);
        break;
      case "lem":
        end_lem(n);
        break;
      case "linegroup":
        end_linegroup(n);
        break;
      case "link":
        end_link(n);
        break;
      case "ls":
        end_ls(n);
        break;
      case "marg":
        end_marg(n);
        break;
      case "mode":
        end_mode(n);
        break;
      case "ornament":
        end_ornament(n);
        break;
      case "page":
        end_page(n);
        break;
      case "pn":
        end_pn(n);
        break;
      case "poem":
        end_poem(n);
        break;
      case "prologue":
        end_prologue(n);
        break;
      case "prop":
        end_prop(n);
        break;
      case "prosequote":
        end_prosequote(n);
        break;
      case "quote":
        end_quote(n);
        break;
      case "r":
        end_r(n);
        break;
      case "ra":
        end_ra(n);
        break;
      case "rdg":
        end_rdg(n);
        break;
      case "rt":
        end_rt(n);
        break;
      case "s":
        end_s(n);
        break;
      case "sc":
        end_sc(n);
        break;
      case "scene":
        end_scene(n);
        break;
      case "sd":
        end_sd(n);
        break;
      case "section":
        end_section(n);
        break;
      case "sig":
        end_sig(n);
        break;
      case "sp":
        end_sp(n);
        break;
      case "stanza":
        end_stanza(n);
        break;
      case "sub":
        end_sub(n);
        break;
      case "sup":
        end_sup(n);
        break;
      case "swash":
        end_swash(n);
        break;
      case "titlehead":
        end_titlehead(n);
        break;
      case "titlepage":
        end_titlepage(n);
        break;
      case "versequote":
        end_versequote(n);
        break;
      case "work":
        end_work(n);
        break;
      default:
        // @TODO throw an error here.
        break;
    }
  }

  /**
   * call the right method for a start node.
   * 
   * @param n Node to visit StartNode being visited.
   */
  private void dispatch_start(StartNode n) {
    switch (n.getName().toLowerCase()) {
      case "abbr":
        start_abbr(n);
        break;
      case "act":
        start_act(n);
        break;
      case "add":
        start_add(n);
        break;
      case "ambig":
        start_ambig(n);
        break;
      case "backmatter":
        start_backmatter(n);
        break;
      case "bll":
        start_bll(n);
        break;
      case "blockquote":
        start_blockquote(n);
        break;
      case "c":
        start_c(n);
        break;
      case "cl":
        start_cl(n);
        break;
      case "col":
        start_col(n);
        break;
      case "cw":
        start_cw(n);
        break;
      case "div":
        start_div(n);
        break;
      case "em":
        start_em(n);
        break;
      case "epilogue":
        start_epilogue(n);
        break;
      case "fontgroup":
        start_fontgroup(n);
        break;
      case "foreign":
        start_foreign(n);
        break;
      case "frontmatter":
        start_frontmatter(n);
        break;
      case "h1":
        start_h1(n);
        break;
      case "h2":
        start_h2(n);
        break;
      case "h3":
        start_h3(n);
        break;
      case "h4":
        start_h4(n);
        break;
      case "h5":
        start_h5(n);
        break;
      case "h6":
        start_h6(n);
        break;
      case "hw":
        start_hw(n);
        break;
      case "i":
        start_i(n);
        break;
      case "iembed":
        start_iembed(n);
        break;
      case "indent":
        start_indent(n);
        break;
      case "iseheader":
        start_iseheader(n);
        break;
      case "j":
        start_j(n);
        break;
      case "ld":
        start_ld(n);
        break;
      case "lem":
        start_lem(n);
        break;
      case "linegroup":
        start_linegroup(n);
        break;
      case "link":
        start_link(n);
        break;
      case "ls":
        start_ls(n);
        break;
      case "marg":
        start_marg(n);
        break;
      case "mode":
        start_mode(n);
        break;
      case "ornament":
        start_ornament(n);
        break;
      case "page":
        start_page(n);
        break;
      case "pn":
        start_pn(n);
        break;
      case "poem":
        start_poem(n);
        break;
      case "prologue":
        start_prologue(n);
        break;
      case "prop":
        start_prop(n);
        break;
      case "prosequote":
        start_prosequote(n);
        break;
      case "quote":
        start_quote(n);
        break;
      case "r":
        start_r(n);
        break;
      case "ra":
        start_ra(n);
        break;
      case "rdg":
        start_rdg(n);
        break;
      case "rt":
        start_rt(n);
        break;
      case "s":
        start_s(n);
        break;
      case "sc":
        start_sc(n);
        break;
      case "scene":
        start_scene(n);
        break;
      case "sd":
        start_sd(n);
        break;
      case "section":
        start_section(n);
        break;
      case "sig":
        start_sig(n);
        break;
      case "sp":
        start_sp(n);
        break;
      case "stanza":
        start_stanza(n);
        break;
      case "sub":
        start_sub(n);
        break;
      case "sup":
        start_sup(n);
        break;
      case "swash":
        start_swash(n);
        break;
      case "titlehead":
        start_titlehead(n);
        break;
      case "titlepage":
        start_titlepage(n);
        break;
      case "versequote":
        start_versequote(n);
        break;
      case "work":
        start_work(n);
        break;
      default:
        // @TODO throw an error here.
        break;
    }
  }

  protected Node peek() {
    return iterator.peek();
  }
  
  void skipTo(NodeType nt, String nodeName) {
    while (iterator.hasNext()) {
      Node n = iterator.next();
      if ((n.type() == nt) && (n.getName().toLowerCase().equals(nodeName.toLowerCase()))) {
        return;
      }
    }
  }

}
