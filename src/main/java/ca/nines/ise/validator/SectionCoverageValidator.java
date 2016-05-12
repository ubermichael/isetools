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

import java.util.ArrayList;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

/**
 * Checks to see if all text in the given DOM is inside at least one sectioning element.
 * The rules are as follows,
 *    If a PAGE tag exists in the document, all text must be within a PAGE tag.
 *    For text outside of FRONTMATTER and BACKMATTER:
 *      If an ACT or SCENE tag exist, all text must be within an ACT or SCENE tag.
 *      If a DIV tag exists, all text must be within a DIV tag.
 *    For text within FRONTMATTER or BACKMATTER:
 *      All text within matter must be within a DIV tag.
 *    If no sectioning exists, all text will be flagged with required.
 *    
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 */
public class SectionCoverageValidator {

  ValidatorStack<StartNode> nodeStack;
  ArrayList<StartNode> coverAll;
  boolean acts;
  boolean scenes;
  boolean pages;
  
  private void process_init(StartNode n){
    //if we're not in either frontmatter or backmatter, coverAll should cover everything
    if (n.getName().toLowerCase().equals("div") && !nodeStack.in_tag("frontmatter") && !nodeStack.in_tag("backmatter"))
      coverAll.add(n);
    else if (n.getName().toLowerCase().equals("scene"))
      scenes = true;
    else if (n.getName().toLowerCase().equals("act"))
      acts = true;
    else if (n.getName().toLowerCase().equals("page"))
      pages = true;
  }
  
  private void process_start(StartNode n){
    nodeStack.push(n);
  }
  
  private void process_end(EndNode n){
    nodeStack.remove_first(n);
  }
  
  @ErrorCode(code = {
      "validator.coverage.required",
      "validator.coverage.outside_div",
      "validator.coverage.outside_body",
      "validator.coverage.outside_page"
  })
  private void process_text(TextNode n){
    //if text is empty, return
    if (n.getText().trim().equals(""))
      return;
    //if not in matter
    if (!nodeStack.in_tag("frontmatter") && !nodeStack.in_tag("backmatter")){
      //if no sectioning exists
      if (coverAll.isEmpty() && !scenes && !acts && !pages){
        Message m = Message.builder("validator.coverage.required")
          .fromNode(n)
          .addNote("Text in the document must be within at least one type of sectioning element (ex. DIV, ACT, SCENE, etc.)")
          .build();
        Log.addMessage(m); 
      }
      //if not in a scene or act
      if (!nodeStack.in_tag("act") && !nodeStack.in_tag("scene")){
        //if acts or scenes exist
        if (scenes || acts){
          Message m = Message.builder("validator.coverage.outside_body")
            .fromNode(n)
            .addNote("All text outside FRONTMATTER and BACKMATTER must be within an ACT and/or SCENE if ACT and/or SCENE tags exist in the document")
            .build();
          Log.addMessage(m);
        }
      }
    }else if (!nodeStack.in_tag("div")){
      Message m = Message.builder("validator.coverage.outside_div")
          .fromNode(n)
          .addNote("Text in FRONTMATTER or BACKMATTER must be within a DIV")
          .build();
      Log.addMessage(m);
    }
    
    Boolean outside = true;
    if (coverAll.isEmpty())
      outside = false;
    else{
      for (Node c : coverAll){
        if (nodeStack.in_tag(c.getName()))
           outside = false;
      }
    }
    if (outside){
      Message m = Message.builder("validator.coverage.outside_body")
          .fromNode(n)
          .addNote("All text in the document must be contained within DIV tags if a DIV exists outside FRONTMATTER and BACKMATTER")
          .build();
      Log.addMessage(m);
    }
    
    //if not in a page but pages exist, error
    if (pages && !nodeStack.in_tag("page")){
      Message m = Message.builder("validator.coverage.outside_page")
          .fromNode(n)
          .addNote("if PAGE tags exists, all text must be within a PAGE")
          .build();
      Log.addMessage(m);
    }
    
  }
  
  public void validate(DOM dom) {
    nodeStack = new ValidatorStack<StartNode>();
    coverAll = new ArrayList<StartNode>();
    acts = false;
    scenes = false;
    pages = false;
    
    //first pass
    for (Node n : dom) {
      switch (n.type()) {
        case START:
          process_start((StartNode) n);
          process_init((StartNode) n);
          break;
        case END:
          process_end((EndNode) n);
          break;
      }
    }
    
    nodeStack = new ValidatorStack<StartNode>();
    
    //second pass
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
