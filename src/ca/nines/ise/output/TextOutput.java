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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

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
  public void print(DOM dom) {
    Iterator<Node> iterator = dom.iterator();
    while (iterator.hasNext()) {
      Node n = iterator.next();
      this.print(n); // no super.print() please.
    }
  }

  @Override
  public void print(Node n) {
    out.println(n);
  }

  @Override
  public void print(Log log) {
    out.print(log);
  }

  @Override
  public void print(Message m) {
    out.print(m);
  }

  public void print(String string) {
    out.print(string);
  }

}
