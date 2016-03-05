package ca.nines.ise.validator;

import java.util.ArrayList;
import java.util.LinkedList;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;

public class SectionCoverageValidator {

  ValidatorStack<StartNode> nodeStack;
  ArrayList<StartNode> coverAll;
  ArrayList<StartNode> acts;
  ArrayList<StartNode> scenes;
  ArrayList<StartNode> pages;
  
  private void process_init(StartNode n){
    //if we're not in either frontmatter or backmatter, coverAll should cover everything
    if (n.getName().equals("div") && !(in_tag("frontmatter") || in_tag("backmatter")))
      coverAll.add(n);
    else if (n.getName().toLowerCase().equals("scene"))
      scenes.add(n);
    else if (n.getName().toLowerCase().equals("act"))
      acts.add(n);
    else if (n.getName().toLowerCase().equals("page"))
      pages.add(n);
  }
  
  private void process_start(StartNode n){
    nodeStack.push(n);
  }
  
  private void process_end(EndNode n){
    //remove node from stack, we don't care about splitting here
    Node end = nodeStack.get_first(n);
    if (end != null)
      nodeStack.remove(end);
  }
  
  private Boolean in_tag(String name){
    for (Node s : nodeStack) {
      if (s.getName().toLowerCase().equals(name.toLowerCase()))
        return true;
    }
    return false;
  }
  
  @ErrorCode(code = {
      "validator.coverage.required",
      "validator.coverage.outside_div",
      "validator.coverage.outside_body",
      "validator.coverage.outside_page"
  })
  private void process_text(TextNode n){
    if (!(in_tag("frontmatter") || in_tag("backmatter")) &&
        coverAll.isEmpty() && scenes.isEmpty() && acts.isEmpty() && pages.isEmpty()){
      Message m = Message.builder("validator.coverage.required")
          .fromNode(n)
          .addNote("Text in the document must be within at least one type of sectioning element (ex. DIV, ACT, SCENE, etc.)")
          .build();
      Log.addMessage(m); 
    }
    if ((in_tag("frontmatter") || in_tag("backmatter")) && !in_tag("div")){
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
        if (in_tag(c.getName()))
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
    
    //if outside frontmatter and backmatter
    if (!(in_tag("frontmatter") || in_tag("backmatter"))){
      //if not in a scene or act
      if (!in_tag("act") && !in_tag("scene")){
        //if acts or scenes exist
        if (!scenes.isEmpty() || !acts.isEmpty()){
          Message m = Message.builder("validator.coverage.outside_body")
            .fromNode(n)
            .addNote("All text outside FRONTMATTER and BACKMATTER must be within an ACT and/or SCENE if ACT and/or SCENE tags exist in the document")
            .build();
          Log.addMessage(m);
        }
      }
    }
    
    //if not in a page but pages exist, error
    if (!pages.isEmpty() && !in_tag("page")){
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
    acts = new ArrayList<StartNode>();
    scenes = new ArrayList<StartNode>();
    pages = new ArrayList<StartNode>();
    
    //first pass
    for (Node n : dom) {
      switch (n.type()) {
        case START:
          process_init((StartNode) n);
          break;
      }
    }
    
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
      }
    }
    
  }
}
