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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;

/**
 * Checks to see if a certain parent does not have too many descendants of a certain type.
 * ex:
 *    <S> must only have one <SP> descendant
 *    <S><SP>x</SP><SP>y</SP><S> => too many descendants
 *    <S><SP>x</SP><z><SP>y</SP></z><S> => too many descendants
 * 
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 */

public class DescendantCountValidator {
  //these stacks are parallel: each list in childStack correlates to the node in parentStack at that position
  ValidatorStack<StartNode> parentStack;
  LinkedList<ValidatorStack<StartNode>> childStack;
  
  //<parent,<descendant,count>>
  static final Map<String , HashMap<String,String>> DESCENDANT_MAP = new HashMap<String , HashMap<String,String>>() {{
    put("s", new HashMap<String , String>() {{put("sp","0");}});
    put("bracegroup", new HashMap<String , String>() {{put("label","0");}});
   }};
  
  @ErrorCode(code = {
      "validator.descendantCount.overCount"
    })
  private void process_start(StartNode n){
    //if we're in a node(s)
    if (!parentStack.isEmpty()){
      //check to see if this parent has any rules for its descendants
      for (int i=0 ;i<parentStack.size(); i++){
        String parent_name = parentStack.get(i).getName();
        HashMap<String, String> children = DESCENDANT_MAP.get(parent_name.toLowerCase());
        String count = null;
        if (children != null)
          count = children.get(n.getName().toLowerCase());
        
        if (count != null){
          int sibCount = Integer.parseInt(count);
          int childCount = 0;
          
          if (childStack.get(i).isEmpty()){
            childStack.get(i).add(n);
          }else{
            for(StartNode c : childStack.get(i)){
              if (c.getName().toLowerCase().equals(n.getName().toLowerCase()))
                childCount ++;
            }
            if (childCount > sibCount){
              Message m = Message.builder("validator.descendantCount.overCount")
                  .fromNode(n)
                  .addNote("Tag " + parent_name + " cannot have more than " + count + " " + n.getName() + " children.")
                  .build();
              Log.addMessage(m);
            }
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
