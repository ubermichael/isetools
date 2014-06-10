/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.dom;

import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author michael
 */
public class LexerErrorListener extends BaseErrorListener {

  private String source = "";
  
  @Override
  public void syntaxError(
          Recognizer<?, ?> recognizer,
          Object offendingSymbol,
          int line,
          int charPositionInLine,
          String msg,
          RecognitionException e) {

    StringBuilder sb = new StringBuilder();
    Formatter f = new Formatter(sb);
    f.format("%s:%s:%s:%s%n", source, line, charPositionInLine, msg);
    f.format("  %s", e);
    
    System.out.println(sb);
  }

  /**
   * @param source the source to set
   */
  public void setSource(String source) {
    this.source = source;
  }

}
