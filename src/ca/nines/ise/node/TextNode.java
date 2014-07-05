/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class TextNode extends Node {

  public TextNode() {
    super();
  }

  public TextNode(Node n) {
    super(n);
  }

  @Override
  public Fragment expanded() {
    Fragment f = new Fragment();
    TextNode node = new TextNode(this);
    f.add(node);
    return f;
  }

  @Override
  public String plain() {
    return text;
  }

  @Override
  public NodeType type() {
    return NodeType.TEXT;
  }

  @Override
  public String unicode() {
    return text;
  }

}
