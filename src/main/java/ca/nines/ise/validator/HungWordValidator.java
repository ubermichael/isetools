/*
 * Copyright (C) 2015 Malcolm Moran <malcolm.moran@outlook.com>
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

import java.util.ArrayDeque;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;
/**
 * 
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 * 
 * Ensures HW are always used at the end of a line.
 */
public class HungWordValidator {
  Boolean in_hw, after_hw;
  StartNode last_hw;
  
  @ErrorCode(code = {
      "validator.hungWord.endOfLine"
  })
  private void process_start(StartNode n){
    if (n.getName().toLowerCase().equals("hw")){
      in_hw = true;
      last_hw = n;
    }
    if (after_hw){
      Message m = Message.builder("validator.hungWord.endOfLine")
          .fromNode(last_hw)
          .addNote("HW tags must be at the end of a line")
          .build();
      Log.addMessage(m);
    }
  }

  @ErrorCode(code = {
      "validator.hungWord.endOfLine"
  })
  private void process_empty(EmptyNode n){
    if (after_hw){
      Message m = Message.builder("validator.hungWord.endOfLine")
          .fromNode(last_hw)
          .addNote("HW tags must be at the end of a line")
          .build();
      Log.addMessage(m);
    }
  }
  
  @ErrorCode(code = {
      "validator.hungWord.endOfLine"
  })
  private void process_end(EndNode n) {
    // if last_hw is null (ie. there was no start tag, we'll let the tag_balance_validator attach an error
    if (n.getName().toLowerCase().equals("hw") && last_hw != null){
      in_hw = false;
      after_hw = true;
    }
  }
  
  private void process_text(TextNode n) {
    if (n.getText().contains("\n")){
      in_hw = false;
      after_hw = false;
    } else if (after_hw){
      Message m = Message.builder("validator.hungWord.endOfLine")
          .fromNode(last_hw)
          .addNote("HW tags must be at the end of a line")
          .build();
      Log.addMessage(m);
    }
  }

  public void validate(DOM dom) {
    in_hw = false;
    after_hw = false;
    last_hw = null;
    
    for (Node n : dom) {
      switch (n.type()) {
        case END:
          process_end((EndNode) n);
          break;
        case START:
          process_start((StartNode) n);
          break;        
        case EMPTY:
          process_empty((EmptyNode) n);
          break;
        case TEXT:
          process_text((TextNode) n);
          break;
      }
    }
  }

}