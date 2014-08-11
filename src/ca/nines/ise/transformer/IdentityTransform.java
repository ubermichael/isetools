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

import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class IdentityTransform extends Transformer {

  @Override
  public void abbreviation(AbbrNode n) {
    dom.add(n);
  }

  @Override
  public void character(CharNode n) {
    dom.add(n);
  }

  @Override
  public void comment(CommentNode n) {
    dom.add(n);
  }

  @Override
  public void empty_bl(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_br(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_l(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_lb(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_link(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_meta(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_ornament(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_prop(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_qln(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_rule(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_space(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_tln(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void empty_wln(EmptyNode n) {
    dom.add(n);
  }

  @Override
  public void end_abbr(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_act(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_add(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_ambig(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_backmatter(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_bll(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_blockquote(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_c(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_cl(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_col(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_cw(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_div(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_em(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_epilogue(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_fontgroup(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_foreign(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_frontmatter(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_h1(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_h2(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_h3(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_h4(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_h5(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_h6(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_hw(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_i(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_iembed(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_indent(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_iseheader(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_j(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_ld(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_lem(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_linegroup(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_link(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_ls(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_marg(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_mode(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_ornament(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_page(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_pn(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_poem(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_prologue(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_prop(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_prosequote(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_quote(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_r(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_ra(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_rdg(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_rt(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_s(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_sc(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_scene(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_sd(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_section(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_sig(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_sp(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_stanza(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_sub(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_sup(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_swash(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_titlehead(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_titlepage(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_versequote(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_work(EndNode n) {
    dom.add(n);
  }

  @Override
  public void start_abbr(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_act(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_add(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_ambig(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_backmatter(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_bll(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_blockquote(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_c(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_cl(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_col(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_cw(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_div(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_em(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_epilogue(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_fontgroup(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_foreign(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_frontmatter(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_h1(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_h2(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_h3(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_h4(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_h5(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_h6(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_hw(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_i(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_iembed(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_ilink(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_indent(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_iseheader(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_j(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_ld(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_lem(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_linegroup(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_ls(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_marg(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_mode(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_ornament(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_page(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_pn(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_poem(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_prologue(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_prop(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_prosequote(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_quote(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_r(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_ra(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_rdg(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_rt(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_s(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_sc(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_scene(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_sd(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_section(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_sig(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_sp(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_stanza(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_sub(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_sup(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_swash(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_titlehead(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_titlepage(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_versequote(StartNode n) {
    dom.add(n);
  }

  @Override
  public void start_work(StartNode n) {
    dom.add(n);
  }

  @Override
  public void text(TextNode n) {
    dom.add(n);
  }
  
}
