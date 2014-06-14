/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.node;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class EmptyNode extends TagNode {

  public EmptyNode() {
    super();
  }
  
  public EmptyNode(StartNode n) {
    super(n);
  }

  public EmptyNode(String tagname) {
    super(tagname);
  }

  @Override
  public String type() {
    return "#EMPTY";
  }

}
