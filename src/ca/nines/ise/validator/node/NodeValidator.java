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
 * NodeValidator is defines all the functionality
 * for the individual node validators. Each node validator
 * is responsible for validating a single node, via the 
 * validate() method.
 *
 * @author michael
 * @param <T> extends Node
 */
abstract public class NodeValidator<T extends Node> {

  /**
   * The schema to validate against. Not used by all 
   * node validators.
   */
  protected final Schema schema;
  
  /**
   * The log to store the validation messages.
   */
  protected final Log log;

  /**
   * Construct a node validator. Should be called as
   * {@code super(schema) } in constructors.
   * 
   * @param schema The schema for validation.
   */
  public NodeValidator(Schema schema) {
    this.schema = schema;
    this.log = Log.getInstance();
  }

  /**
   * Perform the text node validation, as described above.
   * 
   * @param n 
   */
  abstract public void validate(T n);

}
