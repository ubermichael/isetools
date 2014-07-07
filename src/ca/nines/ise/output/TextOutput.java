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
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michael
 */
public class TextOutput extends Output {

  public TextOutput() throws UnsupportedEncodingException {
    super();
  }

  public TextOutput(File file) throws FileNotFoundException, UnsupportedEncodingException {
    super(file);
  }

  public TextOutput(OutputStream out) throws UnsupportedEncodingException {
    super(out);
  }

  @Override
  public void render(DOM dom) {
    try {
      out.print(dom.plain());
    } catch (IOException ex) {
      Logger.getLogger(TextOutput.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void render(Node n) {
    try {
      out.print(n.plain());
    } catch (IOException ex) {
      Logger.getLogger(TextOutput.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void render(Log log) {
    out.print(log);
  }

  @Override
  public void render(Message m) {
    out.print(m);
  }

  public void print(String string) {
    out.print(string);
  }

}
