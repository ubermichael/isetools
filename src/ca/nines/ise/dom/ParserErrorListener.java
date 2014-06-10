/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.dom;

import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
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
  private Log log = Log.getInstance();
  
  @Override
  public void syntaxError(
          Recognizer<?, ?> recognizer,
          Object offendingSymbol,
          int line,
          int charPositionInLine,
          String msg,
          RecognitionException e) {
    
    Message m = log.add("parser.grammar");
    m.setLine(line);
    m.setColumn(charPositionInLine);
    m.setSource(source);
    m.addNote(msg);
  }

  /**
   * @param source the source to set
   */
  public void setSource(String source) {
    this.source = source;
  }

}
