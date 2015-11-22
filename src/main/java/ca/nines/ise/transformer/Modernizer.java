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
 * Turn an old-spelling ISE document into a modern one, by modernizing the tags
 * only. Actual spelling changes are the responsibility of the editors.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Modernizer extends IdentityTransform {

    boolean inHW = false;

    public void abbr(AbbrNode n) {
        TextNode t = new TextNode(n);
        t.setText(n.plain());
        dom.add(t);
    }

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

    public void comment(CommentNode n) {
        // do nothing.
    }

    public void empty_l(EmptyNode n) {

    }

    public void empty_ornament(EmptyNode n) {

    }

    public void empty_rule(EmptyNode n) {

    }

    public void empty_space(EmptyNode n) {

    }

    public void empty_tln(EmptyNode n) {
        EmptyNode l = new EmptyNode(n);
        l.setName("L");
        l.setAttribute("n", "");
        dom.add(l);
        dom.add(n);
    }

    public void end_bll(EndNode n) {

    }

    public void end_c(EndNode n) {

    }

    public void end_col(EndNode n) {

    }

    public void end_cw(EndNode n) {
        skipTo(NodeType.END, "cw");
    }

    public void end_fontgroup(EndNode n) {

    }

    public void end_hw(EndNode n) {
        inHW = true;
    }

    public void end_i(EndNode n) {

    }

    public void end_j(EndNode n) {

    }

    public void end_ls(EndNode n) {

    }

    public void end_marg(EndNode n) {

    }

    public void end_ornament(EndNode n) {

    }

    public void end_page(EndNode n) {

    }

    public void end_pn(EndNode n) {
        skipTo(NodeType.END, "pn");
    }

    public void end_r(EndNode n) {

    }

    public void end_ra(EndNode n) {

    }

    public void end_rt(EndNode n) {

    }

    public void end_sc(EndNode n) {

    }

    public void start_bll(StartNode n) {

    }

    public void start_c(StartNode n) {

    }

    public void start_col(StartNode n) {

    }

    public void start_cw(StartNode n) {
        skipTo(NodeType.END, "cw");
    }

    public void start_fontgroup(StartNode n) {

    }

    public void start_hw(StartNode n) {
        inHW = true;
    }

    public void start_i(StartNode n) {

    }

    public void start_j(StartNode n) {

    }

    public void start_ls(StartNode n) {

    }

    public void start_marg(StartNode n) {

    }

    public void start_ornament(StartNode n) {

    }

    public void start_page(StartNode n) {

    }

    public void start_pn(StartNode n) {
        skipTo(NodeType.END, "pn");
    }

    public void start_r(StartNode n) {

    }

    public void start_ra(StartNode n) {

    }

    public void start_rt(StartNode n) {
        skipTo(NodeType.END, "rt");
    }

    public void start_sc(StartNode n) {

    }

    public void start_sig(StartNode n) {
        skipTo(NodeType.END, "SIG");
    }

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

    public void text(TextNode n) {
        TextNode txt = new TextNode(n);
        if (inHW) {
            txt.setText(n.getText().replaceFirst("[(]", ""));
            inHW = false;
        }
        dom.add(txt);
    }

}
