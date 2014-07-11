/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.node.Node;
import ca.nines.ise.schema.Schema;

/**
 * NodeValidator is defines all the functionality for the individual node
 * validators. Each node validator is responsible for validating a single node,
 * via the validate() method.
 * <p>
 * @author michael
 * @param <T> extends Node
 */
abstract public class NodeValidator<T extends Node> {

  /**
   * Perform the text node validation, as described above.
   * <p>
   * @param n <p>
   * @param schema
   * @throws java.lang.Exception
   */
  abstract public void validate(T n, Schema schema) throws Exception;

}
