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
  private final Schema schema;
  //these stacks are parallel: each list in childStack correlates to the node in parentStack at that position
  LinkedList<StartNode> parentStack;
  LinkedList<List<StartNode>> childStack;
  
  //<parent,<child,count>>
  static final Map<String , HashMap<String,String>> SIBLING_MAP = new HashMap<String , HashMap<String,String>>() {{
    put("s", new HashMap<String , String>() {{put("sp","1");}});
   }};

  public SiblingCountValidator(Schema schema) {
    this.schema = schema;
  }
  
  @ErrorCode(code = {
      "validator.siblingCount.overCount"
    })
  private void process_start(StartNode n){
    String count = SIBLING_MAP.get(parentStack.peekFirst().getName().toLowerCase()).get(n.getName().toLowerCase());
    
    if (!count.equals("")){
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
              .addNote("Tag " + n.getName() + " cannot have more than " + count + " " + n.getName() + " siblings.")
              .build();
          Log.addMessage(m);
        }
      }
      
    }
    
  }
  
  public void validate(DOM dom){
    parentStack = new LinkedList<>();
    childStack = new LinkedList<List<StartNode>>();
    
    for (Node n : dom) {
      switch (n.type()) {
        case START:
          process_start((StartNode) n);
          parentStack.push((StartNode) n);
          childStack.push(new ArrayList<StartNode>());
          break;
        case END:
          if (n.getName().toLowerCase().equals(parentStack.peekFirst().getName().toLowerCase())){
            parentStack.pop();
            childStack.pop();
          }
          break;
      }
    }
  }
    
}
