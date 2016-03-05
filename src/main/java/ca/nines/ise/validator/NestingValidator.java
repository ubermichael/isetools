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
package ca.nines.ise.validator;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class NestingValidator {
    private final Schema schema;

    public NestingValidator(Schema schema) {
        this.schema = schema;
    }
    
  ValidatorStack<StartNode> nodeStack;
  
  @ErrorCode(code = {
      "validator.nesting.redundant"
  })
  private void check_redundant_nesting(StartNode n){
    String message = null;
    StartNode temp = null;
    
    switch(n.getName().toLowerCase()){
      //basic no redundancies
      case "col":
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
        if (is_nested(n) != null){
          message = "Tag " + n.getName() + " cannot be nested within a " + n.getName() + " tag.";
        }
        break;
      //special cases
      case "font":
        if ((temp = is_nested(n)) != null &&
            temp.hasAttribute("size") &&
            n.hasAttribute("size") &&
            temp.getAttribute("size").equals(n.getAttribute("size"))){
            message = "Tag FONT cannot be nested within a FONT tag of the same size";
        }
        break;
      case "foreign":
        if ((temp = is_nested(n)) != null && 
            temp.hasAttribute("lang") &&
            n.hasAttribute("lang") &&
            temp.getAttribute("lang").equals(n.getAttribute("lang"))){
            message = "Tag FOREIGN cannot be nested within a FOREIGN tag of the same language (lang)";
        }
        break;
    }
    if (message != null){
      Message m = Message.builder("validator.nesting.redundant")
          .fromNode(n)
          .addNote(message)
          .build();
      Log.addMessage(m);
    }
  }
  
  private StartNode is_nested(Node n){
    return nodeStack.get_first(n);
  }

  @ErrorCode(code = {
    "validator.nesting.split",
  })
  private void process_end(EndNode n) {
    if (nodeStack.is_head_equal(n.getName())){
      nodeStack.pop();
      return;
    }
    
    Tag t = schema.getTag(n.getName());
    List<String> noSplit = null;
    if (t != null)
      noSplit = Arrays.asList(t.getNoSplit().split(",[ ]*"));
    
    if (noSplit != null && !noSplit.isEmpty()){
      String splitTags = "";
      for (StartNode s : nodeStack){
        for (String ns : noSplit){
          if (s.getName().toLowerCase().equals(ns.toLowerCase()))
            splitTags += ns + " ";
        }
        if (s.getName().toLowerCase().equals(n.getName().toLowerCase())){
          nodeStack.remove(s);
          break;
        }
      }
      
      if (!splitTags.equals("")){
        Message m = Message.builder("validator.nesting.split")
            .fromNode(n)
            .addNote("Tag " + n.getName() + " cannot split these tags: "+splitTags)
            .build();
        Log.addMessage(m);
      }
    }else{
      nodeStack.remove_first(n);
    }
  }

  private void process_start(StartNode n) {
    check_redundant_nesting(n);
    Tag t = schema.getTag(n.getName());
    if (t != null && !t.getAncestor().equals(""))
      is_descendant_of(n, t.getAncestor());
    nodeStack.push(n);
  }
  
  @ErrorCode(code = {
      "validator.nesting.required"
  })
  private void is_descendant_of(Node n, String parent){
    for (StartNode s : nodeStack) {
      if (s.getName().toLowerCase().equals(parent)) {
        //it's a descendant
        return;
      }
    }
    //else, log error message
    Message m = Message.builder("validator.nesting.required")
        .fromNode(n)
        .addNote("Tag " + n.getName() + " must be the descendant of a "+parent.toUpperCase()+" tag")
        .build();
    Log.addMessage(m);
  }

  public void validate(DOM dom) {
    nodeStack = new ValidatorStack<StartNode>();

    for (Node n : dom) {
      switch (n.type()) {
        case END:
          process_end((EndNode) n);
          break;
        case START:
          process_start((StartNode) n);
          break;
      }
    }

  }

}
