/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Log;
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

}
