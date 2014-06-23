/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class CommentNode extends Node {

  public CommentNode() {
    super();
  }

  public CommentNode(CommentNode node) {
    super(node);
  }

  @Override
  public NodeType type() {
    return NodeType.COMMENT;
  }

  @Override
  public Fragment expanded() {
    CommentNode node = new CommentNode(this);
    Fragment f = new Fragment();
    f.add(node);
    return f;
  }

  @Override
  public String plain() {
    return "";
  }

  @Override
  public String unicode() {
    return "";
  }

}
