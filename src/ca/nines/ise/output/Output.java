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
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author michael
 */
abstract public class Output {

  protected final PrintStream out;

  public Output() throws UnsupportedEncodingException {
    this(System.out);
  }

  public Output(File file) throws FileNotFoundException, UnsupportedEncodingException {
    this(new FileOutputStream(file));
  }

  public Output(OutputStream out) throws UnsupportedEncodingException {
    this.out = new PrintStream(out, true, "UTF-8");
  }

  abstract public void render(DOM dom);

  abstract public void render(Node n);

  abstract public void render(Log log);

  abstract public void render(Message m);

}
