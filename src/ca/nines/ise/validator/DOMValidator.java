/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
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
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class DOMValidator {

  private final HashMap<Node.NodeType, NodeValidator> validators;

  public DOMValidator() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    this(new Schema());
  }

  public DOMValidator(Schema schema) {    
    validators = new HashMap<>();
    validators.put(NodeType.ABBR, new AbbrValidator(schema));
    validators.put(NodeType.CHAR, new CharValidator(schema));
    validators.put(NodeType.COMMENT, new CommentValidator(schema));
    validators.put(NodeType.EMPTY, new EmptyValidator(schema));
    validators.put(NodeType.END, new EndValidator(schema));
    validators.put(NodeType.START, new StartValidator(schema));
    validators.put(NodeType.TEXT, new TextValidator(schema));
  }

  public void validate(DOM dom) throws Exception {
    
    Iterator<Node> i = dom.iterator();
    while(i.hasNext()) {
      Node n = i.next();
      NodeValidator validator = validators.get(n.type());
      if(validator == null) {
        throw new Exception("Unknown node type: " + n.type());
      }
      validator.validate(n);
    }
    
  }

}
