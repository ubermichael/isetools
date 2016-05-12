/*
 * Copyright (C) 2016 Malcolm Moran <malcolm.moran@outlook.com>
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
package ca.nines.ise.validator;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.StartNode;

/**
 * Checks to make sure that split lines (lines with @part attributes) are properly formed.
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 */

public class SplitLineValidator {
    
  EmptyNode current;
  ValidatorStack<Node> nodeStack;
  /**
   * array to hold recursive split lines so that they don't generate
   * multiple errors (one for recursive and one for a part="f" missing part="i"
   * even though one exists
   * */
  ValidatorStack<Node> extraSplits;
  
  @ErrorCode(code = {
      "validator.splitLine.invalid"
  })
  private void process_start(EmptyNode n) {
      if (current != null) {
          Message m = Message.builder("validator.splitLine.invalid")
                 .fromNode(n)
                 .addNote(get_name(n) + " cannot start a new split line (@part=\"i\") before the current one ("+get_name(current)+" @ TLN="+current.getTLN()+") is finished")
                 .build();
          Log.addMessage(m);
          extraSplits.push(n);
      }
      else{
        current = n;
        nodeStack.push(current);
      }
  }
  
  @ErrorCode(code = {
      "validator.splitLine.notStartedM"
  })
  private void process_middle(EmptyNode n) {
    if (current == null){
      Message m = Message.builder("validator.splitLine.notStartedM")
          .fromNode(n)
          .addNote(get_name(n) + " has a @part=\"m\" but no matching \"i\" L tag")
          .build();
      Log.addMessage(m);
    }
  }
  
  @ErrorCode(code = {
    "validator.splitLine.notStartedF",
    "validator.partLine.crossing"
  })
  private void process_finish(EmptyNode n) {
    if (current == null){
      Message m = Message.builder("validator.splitLine.notStartedF")
              .fromNode(n)
              .addNote(get_name(n) + " has a @part=\"f\" but no matching \"i\" L tag")
              .build();
      Log.addMessage(m);
    //if there's a sectioning open in the split line, it's crossing
    }else if (!nodeStack.is_head_equal("l")){
      Message m = Message.builder("validator.partLine.crossing")
          .fromNode(n)
          .addNote("Tag " + nodeStack.get_head_name() + " crosses a split-line ("+get_name(current)+" @ TLN="+current.getTLN()+")")
          .build();
      Log.addMessage(m);
      nodeStack.remove(current);
      current = null;
    }else if (!extraSplits.isEmpty())
      extraSplits.pop();
    else{
      nodeStack.remove(current);
      current = null;
    }
  }
  
  private String get_name(EmptyNode n){
    if (n.hasAttribute("n"))
      return "L n="+n.getAttribute("n")+"";
    else
      return "";
  }
  
  @ErrorCode(code = {
      "validator.partLine.crossing"
  })
  private void process_sectioning_end(EndNode n) {
    //if closing itself with nothing in between, we're good
    if (nodeStack.is_head_equal(n.getName())) {
      nodeStack.pop();
      return;
    }
    //if closing with splitline open, error
    if (current != null) {
      Message m = Message.builder("validator.partLine.crossing")
        .fromNode(n)
        .addNote("Tag " + n.getName() + " crosses a split-line ("+get_name(current)+" @ TLN="+current.getTLN()+")")
        .build();
      Log.addMessage(m);
    }
    //remove n tag from stack
    Node end = null;
    for (Node s : nodeStack) {
      if (s.getName().toLowerCase().equals(n.getName().toLowerCase()))
        end = s;
    }
    if (end != null)
      nodeStack.remove(end);
  }
  
  private void process_sectioning_start(StartNode n){
    nodeStack.push(n);
  }
  
  @ErrorCode(code = {
    "validator.splitLine.unclosed",
    "validator.splitLine.missingPart"
  })
  public void validate(DOM dom) {
    current = null;
    nodeStack = new ValidatorStack<Node>();
    extraSplits = new ValidatorStack<Node>();
  
    for (Node n : dom) {      
      switch (n.getName().toLowerCase()){
        case "act":
        case "backmatter":
        case "div":
        case "frontmatter":
        case "marg":
        case "page":
        case "scene":
        case "stanza":
          switch (n.type()) {
            case END:
              process_sectioning_end((EndNode) n);
              break;
            case START:
              process_sectioning_start((StartNode) n);
              break;
          }
          break;
        case "l":
          if (n.type().equals(NodeType.EMPTY)){
            EmptyNode en = (EmptyNode) n;
            if (en.hasAttribute("part")){
              switch (en.getAttribute("part")) {
                case "i":
                  process_start(en);
                  break;
                case "m":
                  process_middle(en);
                  break;
                case "f":
                  process_finish(en);
              }
            }/*else if (current != null){
              // line in a splitline but doesn't have a part attribute
              Message m = Message.builder("validator.splitLine.missingPart")
                  .fromNode(n)
                  .addNote(get_name(en) + " doesn't have a @part attribute but is within a split line ("+get_name(current)+" @ TLN="+current.getTLN()+")")
                  .build();
              Log.addMessage(m);
          ` }*/
          }
        }
    }
  
    if (current != null) {
      Message m = Message.builder("validator.splitLine.unclosed")
              .fromNode(current)
              .addNote(get_name(current) + " has a @part=\"i\" but no matching \"f\" L tag")
              .build();
      Log.addMessage(m);
    }
  
  }
}
