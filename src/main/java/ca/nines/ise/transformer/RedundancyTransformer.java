package ca.nines.ise.transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.collections4.iterators.PeekingIterator;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

public class RedundancyTransformer extends IdentityTransform {
  LinkedList<StartNode> nodeStack;
  ArrayList<String> nestedTags;
  
  public DOM transform(DOM dom) throws IOException {
    nodeStack = new LinkedList<StartNode>();
    nestedTags = new ArrayList<String>();
    return super.transform(dom);
  }
  
  private void start_node(StartNode n){
    StartNode temp = null;
    
    switch(n.getName().toLowerCase()){
      //basic no redundancies
      case "c":
      case "cl":
      case "cw":
      case "em":
      case "i":
      case "j":
      case "ld":
      case "ls":
      case "pn":
      case "ra":
      case "rt":
      case "sc":
      //have to come back to this one
      //case "sd":
      case "sig":
      case "sp":
      case "title":
      case "work":
        if (is_nested(n) != null)
          nestedTags.add(n.getName());    
        else{
          dom.add(n);
          nodeStack.push(n);
         }
        break;
      //special cases
      case "font":
        if ((temp = is_nested(n)) != null &&
            temp.hasAttribute("size") &&
            n.hasAttribute("size") &&
            temp.getAttribute("size").equals(n.getAttribute("size"))){
          nestedTags.add(n.getName());
        }else{
          dom.add(n);
          nodeStack.push(n);
        }
        break;
      case "foreign":
        if ((temp = is_nested(n)) != null && 
            temp.hasAttribute("lang") &&
            n.hasAttribute("lang") &&
            temp.getAttribute("lang").equals(n.getAttribute("lang"))){
          nestedTags.add(n.getName());
        }else{
          dom.add(n);
          nodeStack.push(n);
        }
        break;
    }

  }
  
  private void end_node(EndNode n){
    for (String s : nestedTags){
      if (s.toLowerCase().equals(n.getName().toLowerCase())){
        nestedTags.remove(s);
        return;
      }
    }
    for (StartNode s : nodeStack){
      if (s.getName().toLowerCase().equals(n.getName().toLowerCase())){
        nodeStack.remove(s);
        break;
      }
    }
    dom.add(n);
  }
  
  private StartNode is_nested(StartNode n){
    for (StartNode s : nodeStack)
      if (s.getName().toLowerCase().equals(n.getName().toLowerCase()))
        return s;
    return null;
  }
  
  
  public void start_c(StartNode n) {
    start_node(n);
  }
  
  public void start_cl(StartNode n) {
    start_node(n);
  }
  
  public void start_cw(StartNode n) {
    start_node(n);
  }
  
  public void start_em(StartNode n) {
    start_node(n);
  }
  
  public void start_i(StartNode n) {
    start_node(n);
  }
  
  public void start_j(StartNode n) {
    start_node(n);
  }
  
  public void start_ld(StartNode n) {
    start_node(n);
  }
  
  public void start_ls(StartNode n) {
    start_node(n);
  }
  
  public void start_pn(StartNode n) {
    start_node(n);
  }
  
  public void start_ra(StartNode n) {
    start_node(n);
  }
  
  public void start_rt(StartNode n) {
    start_node(n);
  }
  
  public void start_sc(StartNode n) {
    start_node(n);
  }
  
  public void start_sig(StartNode n) {
    start_node(n);
  }
  
  public void start_sp(StartNode n) {
    start_node(n);
  }
  
  public void start_title(StartNode n) {
    start_node(n);
  }
  
  public void start_work(StartNode n) {
    start_node(n);
  }
  
  public void start_foreign(StartNode n) {
    start_node(n);
  }
  
  public void start_font(StartNode n) {
    start_node(n);
  }
  
  public void end_c(EndNode n) {
    end_node(n);
  }
  
  public void end_cl(EndNode n) {
    end_node(n);
  }
  
  public void end_cw(EndNode n) {
    end_node(n);
  }
  
  public void end_em(EndNode n) {
    end_node(n);
  }
  
  public void end_i(EndNode n) {
    end_node(n);
  }
  
  public void end_j(EndNode n) {
    end_node(n);
  }
  
  public void end_ld(EndNode n) {
    end_node(n);
  }
  
  public void end_ls(EndNode n) {
    end_node(n);
  }
  
  public void end_pn(EndNode n) {
    end_node(n);
  }
  
  public void end_ra(EndNode n) {
    end_node(n);
  }
  
  public void end_rt(EndNode n) {
    end_node(n);
  }
  
  public void end_sc(EndNode n) {
    end_node(n);
  }
  
  public void end_sig(EndNode n) {
    end_node(n);
  }
  
  public void end_sp(EndNode n) {
    end_node(n);
  }
  
  public void end_title(EndNode n) {
    end_node(n);
  }
  
  public void end_work(EndNode n) {
    end_node(n);
  }
  
  public void end_foreign(EndNode n) {
    end_node(n);
  }
  
  public void end_font(EndNode n) {
    end_node(n);
  }

}
