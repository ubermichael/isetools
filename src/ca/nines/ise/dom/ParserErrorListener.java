/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package ca.nines.ise.dom;

import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * ParserErrorListener intercepts parser errors and generates error messages in
 * the log. Used by MessageBuilder.
 */
public class ParserErrorListener extends BaseErrorListener {

  private final String[] lines;
  private final String source;

  ParserErrorListener(String source, String[] lines) {
    this.source = source;
    this.lines = lines;    
  }

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
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
    Message m = Message.builder("lexer.syntax")
            .setLineNumber(line)
            .setColumnNumber(charPositionInLine)
            .setSource(source)
            .setLine(lines[line - 1])
            .addNote(msg.substring(0, Math.min(64, msg.length())))
            .build();
    Log.addMessage(m);
  }

}
