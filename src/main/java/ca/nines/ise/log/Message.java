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
package ca.nines.ise.log;

import ca.nines.ise.node.Node;
import ca.nines.ise.node.lemma.Lemma;
import ca.nines.ise.util.BuilderInterface;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Message captures all the information about a parse, validation, or other
 * error.
 * <p>

 */
public class Message implements Comparable<Message> {

  /**
   * mapping from error code to human-readable string.
   */
  private static final ErrorCodes errorCodes;

  static {
    ErrorCodes tmp = null;
    try {
      tmp = ErrorCodes.defaultErrorCodes();
    } catch (Exception ex) {
      Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
    }
    errorCodes = tmp;
  }

  /**
   * TLN of the error, if applicable.
   */
  private final String TLN;

  /**
   * Message code.
   */
  private final String code;

  /**
   * Column number where the error occurred.
   */
  private final int columnNumber;

  /**
   * The line of text where the error occurred.
   */
  private final String line;

  /**
   * Line number where the error occurred.
   */
  private final int lineNumber;

  /**
   * Notes associated with the error.
   */
  private final List<String> notes;

  /**
   * Input source where the error occurred.
   */
  private final String source;

  /**
   * Create a message builder.
   *
   * @param code
   * @return MessageBuilder
   */
  public static MessageBuilder builder(String code) {
    return new MessageBuilder(code);
  }

  /**
   * Message constructor. Once a message is built it is final and unchangeable.
   */
  public static class MessageBuilder implements BuilderInterface<Message> {

    /**
     * TLN of the error, if applicable.
     */
    private String TLN = "unknown";

    /**
     * Message code.
     */
    private String code = "unknown";

    /**
     * Column number where the error occurred.
     */
    private int columnNumber = 0;

    /**
     * The line of text where the error occurred.
     */
    private String line = "";

    /**
     * Line number where the error occurred.
     */
    private int lineNumber = 0;

    /**
     * Notes associated with the error.
     */
    private final List<String> notes = new ArrayList<>();

    /**
     * Input source where the error occurred.
     */
    private String source = "unknown";

    /**
     * Use Message#builder() to create message builders.
     *
     * @param code
     */
    private MessageBuilder(String code) {
      this.code = code;
    }

    /**
     * Add a note to the messing being constructed.
     *
     * @param note
     * @return MessageBuilder
     */
    public MessageBuilder addNote(String note) {
      notes.add(note);
      return this;
    }

    /**
     * Build and return the message.
     *
     * @return Message
     */
    @Override
    public Message build() {
      Message m = new Message(code, TLN, lineNumber, columnNumber, line, source, notes);
      return m;
    }

    /**
     * Set message metadata based on a Node.
     *
     * @param n
     * @return MessageBuilder
     */
    public MessageBuilder fromNode(Node n) {
      setColumnNumber(n.getColumn());
      setLineNumber(n.getLine());
      setLine(n.getText());
      setSource(n.getSource());
      setTLN(n.getTLN());
      return this;
    }

    /**
     * Set message metadata from a lemma.
     *
     * @param lem
     * @return MessageBuilder
     */
    public MessageBuilder fromLemma(Lemma lem) {
      setLineNumber(lem.getLineNumber());
      setSource(lem.getSource());
      setTLN(lem.getTln());
      return this;
    }

    /**
     * Set a column number.
     *
     * @param columnNumber
     * @return MessageBuilder
     */
    public MessageBuilder setColumnNumber(int columnNumber) {
      this.columnNumber = columnNumber;
      return this;
    }

    /**
     * Set the line.
     *
     * @param line
     * @return MessageBuilder
     */
    public MessageBuilder setLine(String line) {
      this.line = line;
      return this;
    }

    /**
     * Set the line number.
     *
     * @param lineNumber
     * @return MessageBuilder
     */
    public MessageBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * Set the source.
     *
     * @param source
     * @return MessageBuilder
     */
    public MessageBuilder setSource(String source) {
      this.source = source;
      return this;
    }

    /**
     * Set the TLN.
     *
     * @param TLN
     * @return MessageBuilder
     */
    public MessageBuilder setTLN(String TLN) {
      this.TLN = TLN;
      return this;
    }
  }

  /**
   * Use MessageBuilder to create messages.
   *
   * @param code
   * @param TLN
   * @param lineNumber
   * @param columnNumber
   * @param line
   * @param source
   * @param notes
   */
  private Message(String code, String TLN, int lineNumber, int columnNumber, String line, String source, List<String> notes) {
    this.code = code;
    this.TLN = TLN;
    this.lineNumber = lineNumber;
    this.columnNumber = columnNumber;
    this.line = line;
    this.source = source;
    this.notes = notes;
  }

  /**
   * Compare two messages based on source, line number, and column number.
   *
   * @param o
   * @return int
   */
  @Override
  public int compareTo(Message o) {
    if (!this.source.equals(o.source)) {
      return this.source.compareTo(o.source);
    }
    if (this.lineNumber != o.lineNumber) {
      return this.lineNumber - o.lineNumber;
    }
    return this.columnNumber - o.columnNumber;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @return the column
   */
  public int getColumnNumber() {
    return columnNumber;
  }

  /**
   * @return the line
   */
  public String getLine() {
    return line;
  }

  /**
   * @return the line
   */
  public int getLineNumber() {
    return lineNumber;
  }

  public String getMessage() {
    if (errorCodes != null && errorCodes.hasErrorCode(code)) {
      return errorCodes.getErrorCode(code).getMessage();
    } else {
      return "unknown";
    }
  }

  /**
   * @return the notes
   */
  public String[] getNotes() {
    return notes.toArray(new String[notes.size()]);
  }

  /**
   * @return the severity
   */
  public String getSeverity() {
    if (errorCodes != null && errorCodes.hasErrorCode(code)) {
      return errorCodes.getErrorCode(code).getSeverity();
    } else {
      return "unknown";
    }
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * @return the TLN
   */
  public String getTLN() {
    return TLN;
  }

  /**
   * Return a nicely formatted, human readable string.
   *
   * @return debug string
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s:%d:%d:%s%n", source, lineNumber, columnNumber, code);
    formatter.format("  %s:%s%n", getSeverity(), getMessage());
    if (!TLN.equals("unknown")) {
      formatter.format("  near TLN %s%n", TLN);
    }
    if (!line.equals("")) {
      formatter.format("  %s%n", line);
    }
    for (String note : notes) {
      formatter.format("    * %s%n", note);
    }
    return formatter.toString();
  }
}
