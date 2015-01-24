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

import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

/**
 * Base class for transformers which drops everything - subclasses can override
 * methods as needed for output.
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class NullTransform extends Transformer {

  @Override
  public void abbreviation(AbbrNode n) {

  }

  @Override
  public void character(CharNode n) {

  }

  @Override
  public void comment(CommentNode n) {

  }

  @Override
  public void empty_bl(EmptyNode n) {

  }

  @Override
  public void empty_br(EmptyNode n) {

  }

  @Override
  public void empty_l(EmptyNode n) {

  }

  @Override
  public void empty_lb(EmptyNode n) {

  }

  @Override
  public void empty_link(EmptyNode n) {

  }

  @Override
  public void empty_meta(EmptyNode n) {

  }

  @Override
  public void empty_ornament(EmptyNode n) {

  }

  @Override
  public void empty_prop(EmptyNode n) {

  }

  @Override
  public void empty_qln(EmptyNode n) {

  }

  @Override
  public void empty_rule(EmptyNode n) {

  }

  @Override
  public void empty_space(EmptyNode n) {

  }

  @Override
  public void empty_tln(EmptyNode n) {

  }

  @Override
  public void empty_wln(EmptyNode n) {

  }

  @Override
  public void end_abbr(EndNode n) {

  }

  @Override
  public void end_act(EndNode n) {

  }

  @Override
  public void end_add(EndNode n) {

  }

  @Override
  public void end_ambig(EndNode n) {

  }

  @Override
  public void end_backmatter(EndNode n) {

  }

  @Override
  public void end_bll(EndNode n) {

  }

  @Override
  public void end_blockquote(EndNode n) {

  }

  @Override
  public void end_c(EndNode n) {

  }

  @Override
  public void end_cl(EndNode n) {

  }

  @Override
  public void end_col(EndNode n) {

  }

  @Override
  public void end_cw(EndNode n) {

  }

  @Override
  public void end_div(EndNode n) {

  }

  @Override
  public void end_em(EndNode n) {

  }

  @Override
  public void end_epilogue(EndNode n) {

  }

  @Override
  public void end_fontgroup(EndNode n) {

  }

  @Override
  public void end_foreign(EndNode n) {

  }

  @Override
  public void end_frontmatter(EndNode n) {

  }

  @Override
  public void end_h1(EndNode n) {

  }

  @Override
  public void end_h2(EndNode n) {

  }

  @Override
  public void end_h3(EndNode n) {

  }

  @Override
  public void end_h4(EndNode n) {

  }

  @Override
  public void end_h5(EndNode n) {

  }

  @Override
  public void end_h6(EndNode n) {

  }

  @Override
  public void end_hw(EndNode n) {

  }

  @Override
  public void end_i(EndNode n) {

  }

  @Override
  public void end_iembed(EndNode n) {

  }

  @Override
  public void end_indent(EndNode n) {

  }

  @Override
  public void end_iseheader(EndNode n) {

  }

  @Override
  public void end_j(EndNode n) {

  }

  @Override
  public void end_ld(EndNode n) {

  }

  @Override
  public void end_lem(EndNode n) {

  }

  @Override
  public void end_linegroup(EndNode n) {

  }

  @Override
  public void end_link(EndNode n) {

  }

  @Override
  public void end_ls(EndNode n) {

  }

  @Override
  public void end_marg(EndNode n) {

  }

  @Override
  public void end_mode(EndNode n) {

  }

  @Override
  public void end_ornament(EndNode n) {

  }

  @Override
  public void end_page(EndNode n) {

  }

  @Override
  public void end_pn(EndNode n) {
    
  }

  @Override
  public void end_poem(EndNode n) {

  }

  @Override
  public void end_prologue(EndNode n) {

  }

  @Override
  public void end_prop(EndNode n) {

  }

  @Override
  public void end_prosequote(EndNode n) {

  }

  @Override
  public void end_quote(EndNode n) {

  }

  @Override
  public void end_r(EndNode n) {

  }

  @Override
  public void end_ra(EndNode n) {

  }

  @Override
  public void end_rdg(EndNode n) {

  }

  @Override
  public void end_rt(EndNode n) {

  }

  @Override
  public void end_s(EndNode n) {

  }

  @Override
  public void end_sc(EndNode n) {

  }

  @Override
  public void end_scene(EndNode n) {

  }

  @Override
  public void end_sd(EndNode n) {

  }

  @Override
  public void end_section(EndNode n) {

  }

  @Override
  public void end_sig(EndNode n) {

  }

  @Override
  public void end_sp(EndNode n) {

  }

  @Override
  public void end_stanza(EndNode n) {

  }

  @Override
  public void end_sub(EndNode n) {

  }

  @Override
  public void end_sup(EndNode n) {

  }

  @Override
  public void end_swash(EndNode n) {

  }

  @Override
  public void end_titlehead(EndNode n) {

  }

  @Override
  public void end_titlepage(EndNode n) {

  }

  @Override
  public void end_versequote(EndNode n) {

  }

  @Override
  public void end_work(EndNode n) {

  }

  @Override
  public void start_abbr(StartNode n) {

  }

  @Override
  public void start_act(StartNode n) {

  }

  @Override
  public void start_add(StartNode n) {

  }

  @Override
  public void start_ambig(StartNode n) {

  }

  @Override
  public void start_backmatter(StartNode n) {

  }

  @Override
  public void start_bll(StartNode n) {

  }

  @Override
  public void start_blockquote(StartNode n) {

  }

  @Override
  public void start_c(StartNode n) {

  }

  @Override
  public void start_cl(StartNode n) {

  }

  @Override
  public void start_col(StartNode n) {

  }

  @Override
  public void start_cw(StartNode n) {

  }

  @Override
  public void start_div(StartNode n) {

  }

  @Override
  public void start_em(StartNode n) {

  }

  @Override
  public void start_epilogue(StartNode n) {

  }

  @Override
  public void start_fontgroup(StartNode n) {

  }

  @Override
  public void start_foreign(StartNode n) {

  }

  @Override
  public void start_frontmatter(StartNode n) {

  }

  @Override
  public void start_h1(StartNode n) {

  }

  @Override
  public void start_h2(StartNode n) {

  }

  @Override
  public void start_h3(StartNode n) {

  }

  @Override
  public void start_h4(StartNode n) {

  }

  @Override
  public void start_h5(StartNode n) {

  }

  @Override
  public void start_h6(StartNode n) {

  }

  @Override
  public void start_hw(StartNode n) {

  }

  @Override
  public void start_i(StartNode n) {

  }

  @Override
  public void start_iembed(StartNode n) {

  }

  @Override
  public void start_indent(StartNode n) {

  }

  @Override
  public void start_iseheader(StartNode n) {

  }

  @Override
  public void start_j(StartNode n) {

  }

  @Override
  public void start_ld(StartNode n) {

  }

  @Override
  public void start_lem(StartNode n) {

  }

  @Override
  public void start_linegroup(StartNode n) {

  }

  @Override
  public void start_ilink(StartNode n) {

  }

  @Override
  public void start_ls(StartNode n) {

  }

  @Override
  public void start_marg(StartNode n) {

  }

  @Override
  public void start_mode(StartNode n) {

  }

  @Override
  public void start_ornament(StartNode n) {

  }

  @Override
  public void start_page(StartNode n) {

  }

  @Override
  public void start_poem(StartNode n) {

  }

  @Override
  public void start_prologue(StartNode n) {

  }
  
  @Override
  public void start_pn(StartNode n) {
    
  }

  @Override
  public void start_prop(StartNode n) {

  }

  @Override
  public void start_prosequote(StartNode n) {

  }

  @Override
  public void start_quote(StartNode n) {

  }

  @Override
  public void start_r(StartNode n) {

  }

  @Override
  public void start_ra(StartNode n) {

  }

  @Override
  public void start_rdg(StartNode n) {

  }

  @Override
  public void start_rt(StartNode n) {

  }

  @Override
  public void start_s(StartNode n) {

  }

  @Override
  public void start_sc(StartNode n) {

  }

  @Override
  public void start_scene(StartNode n) {

  }

  @Override
  public void start_sd(StartNode n) {

  }

  @Override
  public void start_section(StartNode n) {

  }

  @Override
  public void start_sig(StartNode n) {

  }

  @Override
  public void start_sp(StartNode n) {

  }

  @Override
  public void start_stanza(StartNode n) {

  }

  @Override
  public void start_sub(StartNode n) {

  }

  @Override
  public void start_sup(StartNode n) {

  }

  @Override
  public void start_swash(StartNode n) {

  }

  @Override
  public void start_titlehead(StartNode n) {

  }

  @Override
  public void start_titlepage(StartNode n) {

  }

  @Override
  public void start_versequote(StartNode n) {

  }

  @Override
  public void start_work(StartNode n) {

  }

  @Override
  public void text(TextNode n) {

  }

}
