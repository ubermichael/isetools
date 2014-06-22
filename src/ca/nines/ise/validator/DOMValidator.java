/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator;

import ca.nines.ise.validator.node.EmptyNodeValidator;
import ca.nines.ise.validator.node.CommentNodeValidator;
import ca.nines.ise.validator.node.AbbrNodeValidator;
import ca.nines.ise.validator.node.StartNodeValidator;
import ca.nines.ise.validator.node.CharNodeValidator;
import ca.nines.ise.validator.node.NodeValidator;
import ca.nines.ise.validator.node.EndNodeValidator;
import ca.nines.ise.validator.node.TextNodeValidator;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.Node.NodeType;
import ca.nines.ise.schema.Schema;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class DOMValidator {

  private final HashMap<NodeType, NodeValidator> validators;

  public DOMValidator() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new Schema());
  }

  public DOMValidator(Schema schema) {   
    this.validators = new HashMap<>();
    validators.put(NodeType.ABBR, new AbbrNodeValidator(schema));
    validators.put(NodeType.CHAR, new CharNodeValidator(schema));
    validators.put(NodeType.COMMENT, new CommentNodeValidator(schema));
    validators.put(NodeType.EMPTY, new EmptyNodeValidator(schema));
    validators.put(NodeType.END, new EndNodeValidator(schema));
    validators.put(NodeType.START, new StartNodeValidator(schema));
    validators.put(NodeType.TEXT, new TextNodeValidator(schema));
  }

  @SuppressWarnings("unchecked")
  public void validate(DOM dom) throws Exception {
    Iterator<Node> i = dom.iterator();
    while (i.hasNext()) {
      Node n = i.next();
      NodeValidator validator = validators.get(n.type());
      if (validator == null) {
        throw new Exception("Unknown node type: " + n.type());
      }
      validator.validate(n);
    }

  }

}
