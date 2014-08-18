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
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

/**
 * Modernizer takes an old-spelling transcription and creates a modernized
 * version.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Modernizer extends IdentityTransform {

  /**
   * Text in HW tags gets special processing.
   */
  boolean inHW = false;

  /**
   * Visit an abbreviation node.
   *
   * @param n AbbrNode to visit.
   */
  @Override
  public void abbreviation(AbbrNode n) {
    TextNode t = new TextNode(n);
    t.setText(n.plain());
    dom.add(t);
  }

  /**
   * Visit a character node, and insert the modern-spelling equivalent.
   *
   * @param n CharNode to visit.
   */
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

  /**
   * Comments get dropped.
   *
   * @param n
   */
  @Override
  public void comment(CommentNode n) {
    // do nothing.
  }

  /**
   * L tags get dropped. More are inserted elsewhere.
   *
   * @param n
   */
  @Override
  public void empty_l(EmptyNode n) {

  }

  /**
   * Ornament tags are dropped.
   *
   * @param n
   */
  @Override
  public void empty_ornament(EmptyNode n) {

  }

  /**
   * Rule tags are dropped.
   *
   * @param n
   */
  @Override
  public void empty_rule(EmptyNode n) {

  }

  /**
   * Space tags are dropped.
   *
   * @param n
   */
  @Override
  public void empty_space(EmptyNode n) {

  }

  /**
   * TLN tags are copied, and new L tags are inserted BEFORE them.
   *
   * @param n
   */
  @Override
  public void empty_tln(EmptyNode n) {
    EmptyNode l = new EmptyNode(n);
    l.setName("L");
    l.setAttribute("n", "");
    dom.add(l);
    dom.add(n);
  }

  /**
   * BLL tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_bll(EndNode n) {

  }

  /**
   * C tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_c(EndNode n) {

  }

  /**
   * COL tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_col(EndNode n) {

  }

  /**
   * CW tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_cw(EndNode n) {
    skipTo(NodeType.END, "cw");
  }

  /**
   * FONTGROUP tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_fontgroup(EndNode n) {

  }

  /**
   * HW tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_hw(EndNode n) {
    inHW = true;
  }

  /**
   * I tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_i(EndNode n) {

  }

  /**
   * J tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_j(EndNode n) {

  }

  /**
   * LS tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_ls(EndNode n) {

  }

  /**
   * MARG tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_marg(EndNode n) {

  }

  /**
   * ORNAMENT tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_ornament(EndNode n) {

  }

  /**
   * PAGE tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_page(EndNode n) {

  }

  /**
   * PN tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_pn(EndNode n) {
    skipTo(NodeType.END, "pn");
  }

  /**
   * R tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_r(EndNode n) {

  }

  /**
   * RA tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_ra(EndNode n) {

  }

  /**
   * RT tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_rt(EndNode n) {

  }

  /**
   * SC tags are dropped.
   *
   * @param n
   */
  @Override
  public void end_sc(EndNode n) {

  }

  /**
   * BLL tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_bll(StartNode n) {

  }

  /**
   * C tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_c(StartNode n) {

  }

  /**
   * COL tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_col(StartNode n) {

  }

  /**
   * CW tags and their contents are dropped.
   *
   * @param n
   */
  @Override
  public void start_cw(StartNode n) {
    skipTo(NodeType.END, "cw");
  }

  /**
   * FONTGROUP tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_fontgroup(StartNode n) {

  }

  /**
   * HW tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_hw(StartNode n) {
    inHW = true;
  }

  /**
   * I tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_i(StartNode n) {

  }

  /**
   * J tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_j(StartNode n) {

  }

  /**
   * LS tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_ls(StartNode n) {

  }

  /**
   * MARG tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_marg(StartNode n) {

  }

  /**
   * ORNAMENT tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_ornament(StartNode n) {

  }

  /**
   * PAGE tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_page(StartNode n) {

  }

  /**
   * PN tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_pn(StartNode n) {
    skipTo(NodeType.END, "pn");
  }

  /**
   * R tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_r(StartNode n) {

  }

  /**
   * RA tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_ra(StartNode n) {

  }

  /**
   * RT tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_rt(StartNode n) {
    skipTo(NodeType.END, "rt");
  }

  /**
   * SC tags are dropped.
   *
   * @param n
   */
  @Override
  public void start_sc(StartNode n) {

  }

  /**
   * SIG tags are dropped with their contents.
   *
   * @param n
   */
  @Override
  public void start_sig(StartNode n) {
    skipTo(NodeType.END, "SIG");
  }

  /**
   * SP tags are kept: their content is replaced with the content of the norm
   * attribute.
   *
   * @param n
   */
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

  /**
   * Visit a text node. Content in HW tags is stripped of a leading left
   * parenthesis if present.
   *
   * @param n
   */
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
