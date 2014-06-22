/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator;

import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.Node;
import ca.nines.ise.schema.Schema;

/**
 *
 * @author michael
 * @param <T>
 */
abstract public class NodeValidator<T extends Node> {

  protected final Schema schema;
  protected final Log log;

  public NodeValidator(Schema schema) {
    this.schema = schema;
    this.log = Log.getInstance();
  }

  abstract public void validate(T n);

  protected Message error(String code, T n) {
    Message m = log.add(code);
    m.setComponent(this.getClass().getCanonicalName());
    m.setSource(n.getSource());
    m.setColumnNumber(n.getColumn());
    m.setLineNumber(n.getLine());
    m.setTLN(n.getTln());
    return m;
  }

}
