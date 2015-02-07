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
 * Reformat an ISE DOM by inserting newlines in smart spots.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Formatter extends IdentityTransform {

  private void newline() {
    TextNode nl = new TextNode();
    nl.setText("\n");
    dom.add(nl);
  }

  @Override
  public void end_act(EndNode n) {
    dom.add(n);
    newline();
  }

  @Override
  public void end_iseheader(EndNode n) {
    dom.add(n);
    newline();
  }

  @Override
  public void end_ld(EndNode n) {
    dom.add(n);
    newline();
  }

  @Override
  public void end_mode(EndNode n) {
    dom.add(n);
    newline();
  }

  @Override
  public void end_page(EndNode n) {
    dom.add(n);
    newline();
  }

  @Override
  public void end_s(EndNode n) {
    dom.add(n);
    newline();
  }

  @Override
  public void end_scene(EndNode n) {
    dom.add(n);
    newline();
  }

  @Override
  public void end_sd(EndNode n) {
    dom.add(n);
  }

  @Override
  public void end_sp(EndNode n) {
    dom.add(n);
    newline();
  }

  @Override
  public void start_act(StartNode n) {
    newline();
    dom.add(n);
  }

  @Override
  public void start_mode(StartNode n) {
    newline();
    dom.add(n);
  }

  @Override
  public void start_page(StartNode n) {
    newline();
    dom.add(n);
  }

  @Override
  public void start_scene(StartNode n) {
    newline();
    dom.add(n);
  }

}
