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
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;
/**
 * 
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 * 
 * Ensures HW tags do not span more than one line, 
 * and are always used at the end of a line.
 */
public class HungWordValidator {

  ArrayDeque<StartNode> nodeStack;
  Boolean in_hm, after_hm;
  
  @ErrorCode(code = {
      "validator.hungWord.endOfLine"
  })
  private void process_start(StartNode n){
    if (n.getName().toLowerCase().equals("hm"))
      in_hm = true;
    if (after_hm){
      Message m = Message.builder("validator.hungWord.endOfLine")
          .fromNode(n)
          .addNote("Tag " + n.getName() + " is following an HW tag on the same line")
          .build();
      Log.addMessage(m);
    }
    nodeStack.push(n);
  }
  
  private void process_end(EndNode n) {
    if (n.getName().toLowerCase().equals("hm")){
      in_hm = false;
      after_hm = true;
    }
    nodeStack.pop();
  }
  
  @ErrorCode(code = {
      "validator.hungWord.spannedLines"
  })
  private void process_text(TextNode n) {
    if (n.getText().contains("\n")){
      if (in_hm){
          Message m = Message.builder("validator.hungWord.spannedLines")
              .fromNode(n)
              .addNote("Tag " + n.getName() + " spans more than one line")
              .build();
          Log.addMessage(m);
      }else if (after_hm){
          after_hm = false;
      }
    }
  }

  public void validate(DOM dom) {
    nodeStack = new ArrayDeque<>();
    in_hm = false;
    after_hm = false;
    
    for (Node n : dom) {
      switch (n.type()) {
        case END:
          process_end((EndNode) n);
          break;
        case START:
          process_start((StartNode) n);
          break;        
        case TEXT:
          process_text((TextNode) n);
          break;
      }
    }
  }

}