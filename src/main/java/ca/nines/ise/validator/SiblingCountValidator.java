package ca.nines.ise.validator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 * Validates the siblingCount attribute in the schema
 *
 */

public class SiblingCountValidator {
  //these stacks are parallel: each list in childStack correlates to the node in parentStack at that position
  ValidatorStack<StartNode> parentStack;
  LinkedList<ValidatorStack<StartNode>> childStack;
  
  //<parent,<child,count>>
  static final Map<String , HashMap<String,String>> SIBLING_MAP = new HashMap<String , HashMap<String,String>>() {{
    put("s", new HashMap<String , String>() {{put("sp","0");}});
    put("bracegroup", new HashMap<String , String>() {{put("label","0");}});
   }};
  
  @ErrorCode(code = {
      "validator.siblingCount.overCount"
    })
  private void process_start(StartNode n){
    StartNode parent = parentStack.peekFirst();
    
    if (parent != null){
      String parent_name = parent.getName();
      HashMap<String, String> children = SIBLING_MAP.get(parent_name.toLowerCase());
      String count = null;
      if (children != null)
        count = children.get(n.getName().toLowerCase());
      
      if (count != null){
        int sibCount = Integer.parseInt(count);
        int childCount = 0;
        
        if (childStack.peekFirst().isEmpty()){
          childStack.peekFirst().add(n);
        }else{
          for(StartNode c : childStack.peekFirst()){
            if (c.getName().toLowerCase().equals(n.getName().toLowerCase()))
              childCount ++;
          }
          if (childCount > sibCount){
            Message m = Message.builder("validator.siblingCount.overCount")
                .fromNode(n)
                .addNote("Tag " + parent_name + " cannot have more than " + count + " " + n.getName() + " children.")
                .build();
            Log.addMessage(m);
          }
        }
      }
    }
    
    parentStack.push(n);
    childStack.push(new ValidatorStack<StartNode>());
  }
  
  public void validate(DOM dom){
    parentStack = new ValidatorStack<StartNode>();
    childStack = new LinkedList<ValidatorStack<StartNode>>();
    
    for (Node n : dom) {
      switch (n.type()) {
        case START:
          process_start((StartNode) n);
          break;
        case END:
          StartNode parent = parentStack.peekFirst();
          if (parent != null && n.getName().toLowerCase().equals(parent.getName().toLowerCase())){
            parentStack.pop();
            childStack.pop();
          }
          break;
      }
    }
  }
    
}
