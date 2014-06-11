/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.validator;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMIterator;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.node.TextNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

import java.io.IOException;
import javax.lang.model.type.UnknownTypeException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class TagValidator {

  private final Schema schema;
  private final Log log;

  public TagValidator() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    log = Log.getInstance();
    schema = new Schema();
  }

  public void validate(AbbrNode n) {
    Message m = error("validator.abbr.depreciated", n);    
    if(n.getText().length() > 7) {
      m = error("validator.abbr.long", n);
      m.addNote("The long abbreviation starts with " + n.getText().substring(0, 7));
      m.addNote("The abbreviation cannot be corrected automatically.");
    }
  }

  public void validate(CharNode n) {

  }

  public void validate(CommentNode n) {
    Message m = null;
    String text = n.getText();
    if( ! text.startsWith("<!--")) {
      m = error("validator.comment.badstart", n);
      m.addNote("The comment started with " + text.substring(0,4));
    }
    if( ! text.endsWith("-->")) {
      m = error("validator.comment.badend", n);
      m.addNote("The comment ended with " + text.substring(text.length() - 3));
    }
  }
  
  public void validate(EndNode n) {
    Tag t = schema.getTag(n.getName());    
    if(t == null) {
      Message m = error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if(t.getEmpty() == "empty") {
      Message m = error("validator.tag.endempty", n);
      m.addNote("End tag " + n.getName() + " should not occur.");
    }
  }

  public void validate(EmptyNode n) {
    Tag t = schema.getTag(n.getName());    
    if(t == null) {
      Message m = error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if( t.getEmpty() == "") {
      Message m = error("validator.tag.emptystart", n);
      m.addNote("Tag " + n.getName() + " should not be self-closing.");
    }
    if( t.isDepreciated()) {
      Message m = error("validator.tag.depreciated", n);
      m.addNote(t.getDepreciated());
    }
  }

  public void validate(StartNode n) {
    Tag t = schema.getTag(n.getName());
    if(t == null) {
      Message m = error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if(t.getEmpty() == "empty") {
      Message m = error("validator.tag.startempty", n);
      m.addNote("Start tag " + n.getName() + " should be self-closing.");
    }
    if( t.isDepreciated()) {
      Message m = error("validator.tag.depreciated", n);
      m.addNote(t.getDepreciated());
    }
  }

  public void validate(TextNode n) {
    if(n.getText().contains("#")) {
      error("validator.text.depreciatedhash", n);
    }
  }

  private Message error(String code, Node n) {
    Message m = log.add(code);
    m.setComponent(this.getClass().getCanonicalName());
    m.setSource(n.getSource());
    m.setColumn(n.getColumn());
    m.setLine(n.getLine());
    m.setTLN(n.getTln());
    return m;
  }

  public void validate(DOM dom) throws Exception {
    DOMIterator iterator = dom.getIterator();
    while(iterator.hasNext()) {
      Node n = iterator.next();
      switch(n.type()) {
        case "#ABBR":
          validate((AbbrNode) n);
          break;
        case "#CHAR":
          validate((CharNode) n);
          break;
        case "#COMMENT":
          validate((CommentNode) n);
          break;
        case "#EMPTY":
          validate((EmptyNode) n);
          break;
        case "#END":
          validate((EndNode) n);
          break;
        case "#START":
          validate((StartNode) n);
          break;
        case "#TEXT":
          validate((TextNode) n);
          break;
        default:
          throw new Exception("Unknown node type " + n.type());
      }
    }
  }

}
