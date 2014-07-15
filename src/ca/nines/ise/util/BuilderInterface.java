/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.util;

/**
 * BuilderInterface defines a way to build complex objects.
 *
 * @author michael
 * @param <T>
 */
public interface BuilderInterface<T> {
  
  /**
   * Build an object.
   * 
   * @return T
   */
  public T build();
}
