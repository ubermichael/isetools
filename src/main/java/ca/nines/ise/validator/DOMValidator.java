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
import ca.nines.ise.node.NodeType;
import ca.nines.ise.schema.Schema;
import java.util.HashMap;
import java.util.Map;

/**
 * DOMValidator validates all the nodes in a DOM against a schema. All
 * validation messages are saved in Log.
 * <p>
 * Usage example:
 * <p>
 * <pre> {@code
 * DOM dom = new DOM();
 * Schema schema = new Schema(); // optional
 * DOMValidator validator = new DOMValidator(schema);
 * validator.validate(dom);
 * System.out.println(Log.getInstance());
 * }
 * </pre>
 * <p>

 */
public class DOMValidator {

  /**
   * Mapping of node types to node validators.
   */
  private static final Map<NodeType, NodeValidator<? extends Node>> validators;

  static {
    validators = new HashMap<>();
    validators.put(NodeType.ABBR, new AbbrNodeValidator());
    validators.put(NodeType.CHAR, new CharNodeValidator());
    validators.put(NodeType.COMMENT, new CommentNodeValidator());
    validators.put(NodeType.EMPTY, new EmptyNodeValidator());
    validators.put(NodeType.END, new EndNodeValidator());
    validators.put(NodeType.START, new StartNodeValidator());
    validators.put(NodeType.TEXT, new TextNodeValidator());
  }

  /**
   * Perform all the schema and node validations.
   * <p>
   * @param dom
   * <p>
   * @param schema
   * @throws Exception
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void validate(DOM dom, Schema schema) throws Exception {
    NodeValidator validator;
    for (Node n : dom) {
      validator = validators.get(n.type());
      if (validator == null) {
        throw new Exception("Unknown node type: " + n.type());
      }
      validator.validate(n, schema);
    }
  }

}
