/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.output;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author michael
 */
public class TextOutput extends Output{
  
  public TextOutput() throws UnsupportedEncodingException {
    this(System.out);
  }
  
  public TextOutput(File file) throws FileNotFoundException, UnsupportedEncodingException {
    this(new FileOutputStream(file));
  }
  
  public TextOutput(OutputStream out) throws UnsupportedEncodingException {
    super(out);
  }
  
  @Override
  public void print(DOM dom) {}
  
  @Override
  public void print(Node n) {}
  
  @Override
  public void print(Log log) {}
  
  @Override
  public void print(Message m) {}
  
}
