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
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import java.util.ArrayDeque;

/**
 * Checks to see if every start tag in the  DOM has a matching end tag.
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 */

public class TagBalanceValidator {
  
  ValidatorStack<StartNode> nodeStack;
  
  @ErrorCode(code = {
    "validator.tagBalance.missing_start_tag"
  })
  private void process_end(EndNode n) {
    if (nodeStack.is_head_equal(n.getName())) {
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

  @ErrorCode(code = {
    "validator.tagBalance.unclosed"
  })
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
  
    for (StartNode n : nodeStack) {
      Message m = Message.builder("validator.tagBalance.unclosed")
              .fromNode(n)
              .addNote("Start tag " + n.getName() + " has no matching end tag")
              .build();
      Log.addMessage(m);
    }
  
  }
}
