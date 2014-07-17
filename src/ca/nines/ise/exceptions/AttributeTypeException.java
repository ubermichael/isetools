/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.exceptions;

/**
 * A class to encapsulate Attribute type exceptions, thrown for unknown 
 * attribute types.
 *
 * @author michael
 */
@SuppressWarnings("serial")
public class AttributeTypeException extends Exception {

  public AttributeTypeException(String msg) {
    super(msg);
  }
  
}
