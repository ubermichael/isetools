/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

/**
 * Log collects error messages generated during parsing 
 * and validation.
 * 
 * Log is a singleton: use getInstance() to get an object, 
 * rather than new().
 *
 * @author michael
 */
public class Log {
  private final ArrayList<Message> messages = new ArrayList<>();

  private static final Log instance = new Log();

  private Log() {}

  /**
   * Get an instance of the log.
   * 
   * @return log instance.
   */
  public static Log getInstance() {
    return instance;
  }

  /**
   * Create and add a new error message to the log.
   * 
   * @param code 
   * @return Message, so that notes can be added.
   */
  public Message add(String code) {
    Message m = new Message(code);
    messages.add(m);
    return m;
  }
  
  /**
   * Add a message to the log.
   * 
   * @param m the message to add.
   */
  public void add(Message m) {
    messages.add(m);
  }


  /**
   * Empty the message log.
   */
  public void clear() {
    messages.clear();
  }
  
  /**
   * Return am array of error messages, sorted by 
   * source and line number.
   * 
   * @return sorted message array.
   */
  public Message[] messages() {
    Message[] m = messages.toArray(new Message[messages.size()]);
    Arrays.sort(m);
    return m;
  }

  /**
   * Serialize the messages into a string.
   * 
   * @return string of messages.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);
    
    for(Message message : messages) {
      formatter.format("%s%n", message);
    }
    return sb.toString();
  }

}
