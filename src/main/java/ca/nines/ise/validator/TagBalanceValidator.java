package ca.nines.ise.validator;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.schema.Schema;
import java.util.ArrayDeque;

public class TagBalanceValidator {
  private final Schema schema;

  public TagBalanceValidator(Schema schema) {
      this.schema = schema;
  }
  
  ArrayDeque<StartNode> nodeStack;
  
  @ErrorCode(code = {
    "validator.tagBalance.missing_start_tag"
  })
  private void process_end(EndNode n) {
    if (nodeStack.peekFirst().getName().toLowerCase().equals(n.getName().toLowerCase())) {
      nodeStack.pop();
      return;
    }
  
    // this is a split tag.
    Boolean hasStart = false;
    ArrayDeque<StartNode> splitStack = new ArrayDeque<>();
    while (nodeStack.size() >= 1) {
      StartNode start = nodeStack.pop();
      if (start.getName().toLowerCase().equals(n.getName().toLowerCase())) {
        hasStart = true;
        break; // while.
      }
      splitStack.push(start);
    }
  
    if (!hasStart) {
      Message m = Message.builder("validator.tagBalance.missing_start_tag")
              .fromNode(n)
              .addNote("Cannot find start tag that corresponds to end tag " + n.getName())
              .build();
      Log.addMessage(m);
    }
  
    while (splitStack.size() >= 1) {
      nodeStack.push(splitStack.pop());
    }
  
  }
  
  private void process_start(StartNode n) {
    nodeStack.push(n);
  }

  public void validate(DOM dom) {
    nodeStack = new ArrayDeque<>();
  
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
  
    for (StartNode n : nodeStack) {
      Message m = Message.builder("validator.tagBalance.unclosed")
              .fromNode(n)
              .addNote("Start tag " + n.getName() + " has no matching end tag")
              .build();
      Log.addMessage(m);
    }
  
  }
}
