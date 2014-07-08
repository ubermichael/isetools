/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.output;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author michael
 */
public class TextOutput extends Output {

  public TextOutput() throws UnsupportedEncodingException, ParserConfigurationException {
    super();
  }

  public TextOutput(PrintStream out) throws UnsupportedEncodingException, ParserConfigurationException {
    super(out);
  }

  @Override
  public void render(DOM dom) {
    try {
      out.print(dom.unicode());
    } catch (IOException ex) {
      Logger.getLogger(TextOutput.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
