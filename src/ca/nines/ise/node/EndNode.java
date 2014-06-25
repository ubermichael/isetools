/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.node.Node;
import ca.nines.ise.node.TagNode;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class EndNode extends TagNode {

  public EndNode() {
    super();
  }

  public EndNode(Node n) {
    super(n);
  }
  
  public EndNode(String tagname) {
    super(tagname);
  }

  @Override
  public NodeType type() {
    return NodeType.END;
  }

}
