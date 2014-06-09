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

import java.io.IOException;
import javax.lang.model.type.UnknownTypeException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class TagValidator {

  private final Schema schema;
  private final Log log;

  public TagValidator() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    log = Log.getInstance();
    schema = new Schema();
  }

  public void validate(AbbrNode n) {

  }

  public void validate(CharNode n) {

  }

  public void validate(CommentNode n) {

  }

  public void validate(EndNode n) {

  }

  public void validate(EmptyNode n) {

  }

  public void validate(StartNode n) {

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
