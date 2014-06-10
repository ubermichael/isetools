/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.dom;

import java.awt.Color;
import java.awt.Container;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author michael
 */
public class ParserErrorListener extends BaseErrorListener {

  private String source = "";
  
  @Override
  public void syntaxError(
          Recognizer<?, ?> recognizer,
          Object offendingSymbol,
          int line,
          int charPositionInLine,
          String msg,
          RecognitionException e) {
    
    List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
    Collections.reverse(stack);

    StringBuilder buf = new StringBuilder();
    Formatter f = new Formatter(buf);
    f.format("%s:%s:%s:%s%n", source, line, charPositionInLine, msg);
    buf.append("rule stack: ").append(stack).append(" ");
    buf.append(e);
    
    System.out.println(buf.toString());
  }

  /**
   * @param source the source to set
   */
  public void setSource(String source) {
    this.source = source;
  }

}
