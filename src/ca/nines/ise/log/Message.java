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
 * @author michael
 */
public class Message implements Comparable<Message> {

  /**
   * A collection of error codes and error messages
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
   * The TLN where the message was generated
   */
  private final String TLN;

  /**
   * The code of the message
   */
  private final String code;

  /**
   * The column number where the message occurred.
   */
  private final int columnNumber;

  /**
   * The text content of the line where the error occurred.
   */
  private final String line;

  /**
   * The line number causing the message
   */
  private final int lineNumber;

  /**
   * A list of notes with the message
   */
  private final List<String> notes;

  /**
   * The source of the data causing the message
   */
  private final String source;

  /**
   * Construct a MessageBuilder for the message code
   *
   * @param code for the message
   * @return MessageBuilder
   */
  public static MessageBuilder builder(String code) {
    return new MessageBuilder(code);
  }

  /**
   * MessageBuilder constructs messages for the Log, in the Builder pattern.
   */
  public static class MessageBuilder implements BuilderInterface<Message> {

    /**
     * The TLN where the Message was built.
     */
    private String TLN = "unknown";

    /**
     * The code for the message.
     */
    private String code = "unknown";

    /**
     * The column number where the message was generated.
     */
    private int columnNumber = 0;

    /**
     * The text line where the message was generated.
     */
    private String line = "";

    /**
     * The line number where the message was generated.
     */
    private int lineNumber = 0;

    /**
     * A list of notes with the message.
     */
    private final List<String> notes = new ArrayList<>();

    /**
     * The source of the data which generated the message.
     */
    private String source = "unknown";

    /**
     * Construct a MessageBuilder. Use Message.builder(code) to get one.
     *
     * @param code
     */
    private MessageBuilder(String code) {
      this.code = code;
    }

    /**
     * Add a note to a message.
     *
     * @param note to add
     * @return MessageBuilder to enable method chaining
     */
    public MessageBuilder addNote(String note) {
      notes.add(note);
      return this;
    }

    /**
     * Build a message from the collected data.
     *
     * @return Message as constructed.
     */
    @Override
    public Message build() {
      Message m = new Message(code, TLN, lineNumber, columnNumber, line, source, notes);
      return m;
    }

    /**
     * Build a message from a Node
     *
     * @param n from which to build the message
     * @return MessageBuilder to enable method chaining
     */
    public MessageBuilder fromNode(Node n) {
      setColumnNumber(n.getColumn());
      setLineNumber(n.getLine());
      setSource(n.getSource());
      setTLN(n.getTLN());
      return this;
    }

    /**
     * Build a message from a Lemma
     *
     * @param lem from which to build the message
     * @return MessageBuilder to enable method chaining
     */
    public MessageBuilder fromLemma(Lemma lem) {
      setLineNumber(lem.getLineNumber());
      setSource(lem.getSource());
      setTLN(lem.getTln());
      return this;
    }

    /**
     * Set the column number for the message.
     *
     * @param columnNumber
     * @return MessageBuilder to enable method chaining
     */
    public MessageBuilder setColumnNumber(int columnNumber) {
      this.columnNumber = columnNumber;
      return this;
    }

    /**
     * Set the content line for the message
     *
     * @param line the Content causing the line
     * @return MessageBuilder to enable method chaining
     */
    public MessageBuilder setLine(String line) {
      this.line = line;
      return this;
    }

    /**
     * Set the line number for the message.
     *
     * @param lineNumber of the message
     * @return MessageBuilder to enable method chaining
     */
    public MessageBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * Set the source of the message.
     *
     * @param source of the message
     * @return MessageBuilder to enable method chaining
     */
    public MessageBuilder setSource(String source) {
      this.source = source;
      return this;
    }

    /**
     * TLN where the message was generated.
     *
     * @param TLN of the message
     * @return MessageBuilder to enable method chaining
     */
    public MessageBuilder setTLN(String TLN) {
      this.TLN = TLN;
      return this;
    }
  }

  /**
   * Construct a message. Use Message.builder() to get a builder and use that to
   * do stuff.
   *
   * @param code Message code
   * @param TLN Message TLN
   * @param lineNumber Message line number
   * @param columnNumber Message column number
   * @param line Message line (the textual content of the line)
   * @param source Source of the message data
   * @param notes Notes associated with the message
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
   * Compare two messages based on
   * <ol>
   * <li>Source</li>
   * <li>Line number</li>
   * <li>Column number</li>
   * </ol>
   *
   * @param o the message to compare to
   * @return an int consistent with the compareTo contract.
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

  /**
   * Find the text equivalent of the message, as defined in the error code.
   *
   * @return a string describing the error message.
   */
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
   * Return a string representation of the message. Mostly useful for debugging.
   *
   * @return
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
