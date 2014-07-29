package ca.nines.ise.writer;

import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.Node;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
/**
 *
 * @author michael
 */
public class SGMLWriter extends Writer{

  private boolean expandAbbrs;
  
  public SGMLWriter() throws ParserConfigurationException, UnsupportedEncodingException {
    this(new PrintStream(System.out, true, "UTF-8"));
  }

  public SGMLWriter(PrintStream out) {
    super(out);
    expandAbbrs = false;
  }

  @Override
  public void render(DOM dom) {
    for(Node n : dom) {
      out.print(n.sgml());
    }
  }

  @Override
  public void render(DOM dom, Annotation ann) throws TransformerConfigurationException, TransformerException, IOException, Exception {
    throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
  }

}
