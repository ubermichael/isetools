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
 * Transformer base class, to transform an ISE DOM into another ISE DOM.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class Transformer {

  private PeekingIterator<Node> iterator;

  DOM dom;
  
  abstract public void abbreviation(AbbrNode n);

  abstract public void character(CharNode n);

  abstract public void comment(CommentNode n);

  abstract public void empty_iembed(EmptyNode n);
  abstract public void empty_ilink(EmptyNode n);
  
  abstract public void empty_bl(EmptyNode n);

  abstract public void empty_br(EmptyNode n);

  abstract public void empty_l(EmptyNode n);

  abstract public void empty_lb(EmptyNode n);

  abstract public void empty_link(EmptyNode n);

  abstract public void empty_meta(EmptyNode n);

  abstract public void empty_ornament(EmptyNode n);

  abstract public void empty_prop(EmptyNode n);

  abstract public void empty_qln(EmptyNode n);

  abstract public void empty_rule(EmptyNode n);

  abstract public void empty_space(EmptyNode n);

  abstract public void empty_tln(EmptyNode n);

  abstract public void empty_wln(EmptyNode n);

  abstract public void end_abbr(EndNode n);

  abstract public void end_act(EndNode n);

  abstract public void end_add(EndNode n);

  abstract public void end_ambig(EndNode n);

  abstract public void end_backmatter(EndNode n);

  abstract public void end_bll(EndNode n);

  abstract public void end_blockquote(EndNode n);

  abstract public void end_c(EndNode n);

  abstract public void end_cl(EndNode n);

  abstract public void end_col(EndNode n);

  abstract public void end_cw(EndNode n);

  abstract public void end_div(EndNode n);

  abstract public void end_em(EndNode n);

  abstract public void end_epilogue(EndNode n);

  abstract public void end_fontgroup(EndNode n);

  abstract public void end_foreign(EndNode n);

  abstract public void end_frontmatter(EndNode n);

  abstract public void end_h1(EndNode n);

  abstract public void end_h2(EndNode n);

  abstract public void end_h3(EndNode n);

  abstract public void end_h4(EndNode n);

  abstract public void end_h5(EndNode n);

  abstract public void end_h6(EndNode n);

  abstract public void end_hw(EndNode n);

  abstract public void end_i(EndNode n);

  abstract public void end_iembed(EndNode n);

  abstract public void end_indent(EndNode n);

  abstract public void end_iseheader(EndNode n);

  abstract public void end_j(EndNode n);

  abstract public void end_ld(EndNode n);

  abstract public void end_lem(EndNode n);

  abstract public void end_linegroup(EndNode n);

  abstract public void end_link(EndNode n);

  abstract public void end_ls(EndNode n);

  abstract public void end_marg(EndNode n);

  abstract public void end_mode(EndNode n);

  abstract public void end_ornament(EndNode n);

  abstract public void end_page(EndNode n);

  abstract public void end_pn(EndNode n);

  abstract public void end_poem(EndNode n);

  abstract public void end_prologue(EndNode n);

  abstract public void end_prop(EndNode n);

  abstract public void end_prosequote(EndNode n);

  abstract public void end_quote(EndNode n);

  abstract public void end_r(EndNode n);

  abstract public void end_ra(EndNode n);

  abstract public void end_rdg(EndNode n);

  abstract public void end_rt(EndNode n);

  abstract public void end_s(EndNode n);

  abstract public void end_sc(EndNode n);

  abstract public void end_scene(EndNode n);

  abstract public void end_sd(EndNode n);

  abstract public void end_section(EndNode n);

  abstract public void end_sig(EndNode n);

  abstract public void end_sp(EndNode n);

  abstract public void end_stanza(EndNode n);

  abstract public void end_sub(EndNode n);

  abstract public void end_sup(EndNode n);

  abstract public void end_swash(EndNode n);

  abstract public void end_titlehead(EndNode n);

  abstract public void end_titlepage(EndNode n);

  abstract public void end_versequote(EndNode n);

  abstract public void end_work(EndNode n);

  abstract public void start_abbr(StartNode n);

  abstract public void start_act(StartNode n);

  abstract public void start_add(StartNode n);

  abstract public void start_ambig(StartNode n);

  abstract public void start_backmatter(StartNode n);

  abstract public void start_bll(StartNode n);

  abstract public void start_blockquote(StartNode n);

  abstract public void start_c(StartNode n);

  abstract public void start_cl(StartNode n);

  abstract public void start_col(StartNode n);

  abstract public void start_cw(StartNode n);

  abstract public void start_div(StartNode n);

  abstract public void start_em(StartNode n);

  abstract public void start_epilogue(StartNode n);

  abstract public void start_fontgroup(StartNode n);

  abstract public void start_foreign(StartNode n);

  abstract public void start_frontmatter(StartNode n);

  abstract public void start_h1(StartNode n);

  abstract public void start_h2(StartNode n);

  abstract public void start_h3(StartNode n);

  abstract public void start_h4(StartNode n);

  abstract public void start_h5(StartNode n);

  abstract public void start_h6(StartNode n);

  abstract public void start_hw(StartNode n);

  abstract public void start_i(StartNode n);

  abstract public void start_iembed(StartNode n);

  abstract public void start_ilink(StartNode n);

  abstract public void start_indent(StartNode n);

  abstract public void start_iseheader(StartNode n);

  abstract public void start_j(StartNode n);

  abstract public void start_ld(StartNode n);

  abstract public void start_lem(StartNode n);

  abstract public void start_linegroup(StartNode n);

  abstract public void start_ls(StartNode n);

  abstract public void start_marg(StartNode n);

  abstract public void start_mode(StartNode n);

  abstract public void start_ornament(StartNode n);

  abstract public void start_page(StartNode n);

  abstract public void start_pn(StartNode n);

  abstract public void start_poem(StartNode n);

  abstract public void start_prologue(StartNode n);

  abstract public void start_prop(StartNode n);

  abstract public void start_prosequote(StartNode n);

  abstract public void start_quote(StartNode n);

  abstract public void start_r(StartNode n);

  abstract public void start_ra(StartNode n);

  abstract public void start_rdg(StartNode n);

  abstract public void start_rt(StartNode n);

  abstract public void start_s(StartNode n);

  abstract public void start_sc(StartNode n);

  abstract public void start_scene(StartNode n);

  abstract public void start_sd(StartNode n);

  abstract public void start_section(StartNode n);

  abstract public void start_sig(StartNode n);

  abstract public void start_sp(StartNode n);

  abstract public void start_stanza(StartNode n);

  abstract public void start_sub(StartNode n);

  abstract public void start_sup(StartNode n);

  abstract public void start_swash(StartNode n);

  abstract public void start_titlehead(StartNode n);

  abstract public void start_titlepage(StartNode n);

  abstract public void start_versequote(StartNode n);

  public abstract void start_work(StartNode n);

  public abstract void text(TextNode n);

  public DOM transform(DOM dom) throws IOException {
    this.dom = new DOM();
    this.dom.setLines(dom.getLines());
    this.dom.setSource(dom.getSource());
    iterator = new PeekingIterator<>(dom.iterator());

    while (iterator.hasNext()) {
      Node n = iterator.next();
      dispatch(n);
    }
    return this.dom;
  }

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

  private void dispatch_empty(EmptyNode n) {
    switch (n.getName().toLowerCase()) {
      case "iembed":
        empty_iembed(n);
      case "ilink":
        empty_ilink(n);
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
      case "ilink":
        start_ilink(n);
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
