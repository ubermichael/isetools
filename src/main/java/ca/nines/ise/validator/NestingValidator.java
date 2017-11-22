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

import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.schema.Schema;
import java.util.ArrayDeque;

/**
 *

 */
public class NestingValidator {
    private final Schema schema;

    public NestingValidator(Schema schema) {
        this.schema = schema;
    }
    
  ArrayDeque<StartNode> nodeStack;

  private void process_end(EndNode n) {
    if (nodeStack.peekFirst().getName().toLowerCase().equals(n.getName().toLowerCase())) {
      nodeStack.pop();
      return;
    }

    Message m = Message.builder("validator.nesting.split_tag")
            .fromNode(n)
            .addNote("Tag " + n.getName() + " splits other tags.")
            .build();
    Log.addMessage(m);

    // this is a split tag.
    ArrayDeque<StartNode> splitStack = new ArrayDeque<>();
    while (nodeStack.size() >= 1) {
      StartNode start = nodeStack.pop();
      if (start.getName().toLowerCase().equals(n.getName().toLowerCase())) {
        break; // while.
      }
      splitStack.push(start);
    }

    if (nodeStack.isEmpty()) {
      m = Message.builder("validator.nesting.missing_start")
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
    for (StartNode s : nodeStack) {
      if (s.getName().toLowerCase().equals(n.getName().toLowerCase())) {
        Message m = Message.builder("validator.nesting.recursive")
                .fromNode(n)
                .addNote("Tag " + n.getName() + " cannot be recursive.")
                .addNote("Or the tag " + s.getName() + " at TLN " + s.getTLN() + " on line " + s.getLine() + " may be unclosed.")
                .build();
        Log.addMessage(m);
      }
    }
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
      Message m = Message.builder("validator.nesting.unclosed")
              .fromNode(n)
              .addNote("Start tag " + n.getName() + " has no matching end tag")
              .build();
      Log.addMessage(m);
    }

  }

}
