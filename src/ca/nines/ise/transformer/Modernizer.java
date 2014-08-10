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
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Modernizer extends IdentityTransform {

  boolean inHW = false;

  @Override
  public void abbreviation(AbbrNode n) {
    TextNode t = new TextNode(n);
    t.setText(n.plain());
    dom.add(t);
  }

  @Override
  public void character(CharNode n) {
    String txt;
    switch (n.getText()) {
      case "{#}":
        txt = " ";
        break;
      case "{ }":
        txt = "";
        break;
      default:
        txt = n.plain();
        break;
    }
    TextNode t = new TextNode(n);
    t.setText(txt);
    dom.add(t);
  }

  @Override
  public void comment(CommentNode n) {
    // do nothing.
  }

  @Override
  public void empty_l(EmptyNode n) {
    
  }

  @Override
  public void empty_ornament(EmptyNode n) {
    
  }

  @Override
  public void empty_rule(EmptyNode n) {
    
  }

  @Override
  public void empty_space(EmptyNode n) {
    
  }

  @Override
  public void empty_tln(EmptyNode n) {
    EmptyNode l = new EmptyNode(n);
    l.setName("L");
    l.setAttribute("n", "");
    dom.add(l);
    dom.add(n);
  }

  @Override
  public void end_bll(EndNode n) {

  }

  @Override
  public void end_c(EndNode n) {

  }

  @Override
  public void end_col(EndNode n) {

  }

  @Override
  public void end_cw(EndNode n) {
    skipTo(NodeType.END, "cw");
  }

  @Override
  public void end_fontgroup(EndNode n) {

  }

  @Override
  public void end_hw(EndNode n) {
    inHW = true;
  }

  @Override
  public void end_i(EndNode n) {

  }

  @Override
  public void end_j(EndNode n) {

  }

  @Override
  public void end_ls(EndNode n) {

  }

  @Override
  public void end_marg(EndNode n) {

  }

  @Override
  public void end_ornament(EndNode n) {

  }

  @Override
  public void end_page(EndNode n) {

  }

  @Override
  public void end_pn(EndNode n) {
    skipTo(NodeType.END, "pn");
  }

  @Override
  public void end_r(EndNode n) {

  }

  @Override
  public void end_ra(EndNode n) {

  }

  @Override
  public void end_rt(EndNode n) {

  }

  @Override
  public void end_sc(EndNode n) {

  }

  @Override
  public void start_bll(StartNode n) {

  }

  @Override
  public void start_c(StartNode n) {

  }

  @Override
  public void start_col(StartNode n) {

  }

  @Override
  public void start_cw(StartNode n) {
    skipTo(NodeType.END, "cw");
  }

  @Override
  public void start_fontgroup(StartNode n) {

  }

  @Override
  public void start_hw(StartNode n) {
    inHW = true;
  }

  @Override
  public void start_i(StartNode n) {

  }

  @Override
  public void start_j(StartNode n) {

  }

  @Override
  public void start_ls(StartNode n) {

  }

  @Override
  public void start_marg(StartNode n) {

  }

  @Override
  public void start_ornament(StartNode n) {

  }

  @Override
  public void start_page(StartNode n) {

  }

  @Override
  public void start_pn(StartNode n) {
    skipTo(NodeType.END, "pn");
  }

  @Override
  public void start_r(StartNode n) {

  }

  @Override
  public void start_ra(StartNode n) {

  }

  @Override
  public void start_rt(StartNode n) {
    skipTo(NodeType.END, "rt");
  }

  @Override
  public void start_sc(StartNode n) {

  }

  @Override
  public void start_sig(StartNode n) {
    skipTo(NodeType.END, "SIG");
  }

  @Override
  public void start_sp(StartNode n) {
    dom.add(n);
    Node txt = new TextNode(n);
    txt.setText(n.getAttribute("norm"));
    dom.add(txt);
    EndNode end = new EndNode(n);
    end.setName("SP");
    dom.add(end);
    skipTo(NodeType.END, "sp");
  }

  @Override
  public void text(TextNode n) {
    TextNode txt = new TextNode(n);
    if (inHW) {
      txt.setText(n.getText().replaceFirst("[(]", ""));
      inHW = false;
    }
    dom.add(txt);
  }

}
