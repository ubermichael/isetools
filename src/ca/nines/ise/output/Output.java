/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.output;

import ca.nines.ise.dom.DOM;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author michael
 */
abstract public class Output {

  protected final PrintStream out;

  // @TODO make this an interface by dropping out and the constructors.
  
  public Output() throws UnsupportedEncodingException, ParserConfigurationException {
    this(System.out);
  }

  public Output(PrintStream out) throws ParserConfigurationException, UnsupportedEncodingException {
    this.out = out;
  }

  abstract public void render(DOM dom) throws TransformerConfigurationException, TransformerException, IOException, Exception;

}
