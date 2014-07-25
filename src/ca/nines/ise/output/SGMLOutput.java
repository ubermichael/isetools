package ca.nines.ise.output;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.Node;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
/**
 *
 * @author michael
 */
public class SGMLOutput extends Output{

  private boolean expandAbbrs;
  
  public SGMLOutput() throws ParserConfigurationException, UnsupportedEncodingException {
    this(new PrintStream(System.out, true, "UTF-8"));
  }

  public SGMLOutput(PrintStream out) {
    super(out);
    expandAbbrs = false;
  }

  @Override
  public void render(DOM dom) {
    for(Node n : dom) {
      out.print(n.sgml());
    }
  }

}
