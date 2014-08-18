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
 * NullTransform visits each node and does nothing. It is intended as a base
 * for other transforms.
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class NullTransform extends Transformer {

  /**
   * {@inheritDoc}
   */
  @Override
  public void abbreviation(AbbrNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void character(CharNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void comment(CommentNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_bl(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_br(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_l(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_lb(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_link(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_meta(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_ornament(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_prop(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_qln(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_rule(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_space(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_tln(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void empty_wln(EmptyNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_abbr(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_act(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_add(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_ambig(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_backmatter(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_bll(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_blockquote(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_c(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_cl(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_col(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_cw(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_div(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_em(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_epilogue(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_fontgroup(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_foreign(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_frontmatter(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_h1(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_h2(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_h3(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_h4(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_h5(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_h6(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_hw(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_i(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_iembed(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_indent(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_iseheader(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_j(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_ld(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_lem(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_linegroup(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_link(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_ls(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_marg(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_mode(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_ornament(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_page(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_pn(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_poem(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_prologue(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_prop(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_prosequote(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_quote(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_r(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_ra(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_rdg(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_rt(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_s(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_sc(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_scene(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_sd(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_section(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_sig(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_sp(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_stanza(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_sub(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_sup(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_swash(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_titlehead(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_titlepage(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_versequote(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void end_work(EndNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_abbr(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_act(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_add(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_ambig(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_backmatter(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_bll(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_blockquote(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_c(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_cl(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_col(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_cw(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_div(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_em(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_epilogue(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_fontgroup(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_foreign(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_frontmatter(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_h1(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_h2(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_h3(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_h4(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_h5(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_h6(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_hw(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_i(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_iembed(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_indent(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_iseheader(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_j(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_ld(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_lem(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_linegroup(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_link(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_ls(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_marg(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_mode(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_ornament(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_page(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_poem(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_prologue(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_pn(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_prop(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_prosequote(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_quote(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_r(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_ra(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_rdg(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_rt(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_s(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_sc(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_scene(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_sd(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_section(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_sig(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_sp(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_stanza(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_sub(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_sup(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_swash(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_titlehead(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_titlepage(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_versequote(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start_work(StartNode n) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void text(TextNode n) {

  }

}
