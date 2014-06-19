/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.node;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class StartNode extends TagNode {

  public StartNode() {
    super();
  }
  
  public StartNode(EmptyNode n) {
    super(n);
  }

  public StartNode(String tagname) {
    super(tagname);
  }

  @Override
  public NodeType type() {
    return NodeType.START;
  }

}
