/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.dom;

import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.misc.ErrorCode;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * ParserErrorListener intercepts parser errors and generates error messages in
 * the log. Used by Builder.
 */
public class ParserErrorListener extends BaseErrorListener {

  private String source = "";
  private final Log log = Log.getInstance();

  /**
   * Called automatically when the parser encounters an error.
   * <p>
   * @param recognizer
   * @param offendingSymbol
   * @param line
   * @param charPositionInLine
   * @param msg
   * @param e
   */
  @ErrorCode(code = {
    "lexer.syntax"
  })
  @Override
  public void syntaxError(
          Recognizer<?, ?> recognizer,
          Object offendingSymbol,
          int line,
          int charPositionInLine,
          String msg,
          RecognitionException e) {

    Message m = log.error("parser.grammar");
    m.setComponent(this.getClass().getSimpleName());
    m.setLineNumber(line);
    m.setColumnNumber(charPositionInLine);
    m.setSource(source);
    m.addNote(msg);
  }

  /**
   * Sets the source of the parsed data. Either #STRING or the path to the file.
   * <p>
   * @param source the source to set
   */
  public void setSource(String source) {
    this.source = source;
  }

}
