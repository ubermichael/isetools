/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.log;

import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * @author michael
 */
public class Log {
  private final ArrayList<Message> messages = new ArrayList<>();

  private static final Log instance = new Log();

  private Log() {}

  public static Log getInstance() {
    return instance;
  }

  public Message add(String code) {
    Message m = new Message(code);
    messages.add(m);
    return m;
  }

  public void clear() {
    messages.clear();
  }
  
  public void add(Message m) {
    messages.add(m);
  }

  public Message[] messages() {
    return (Message[]) messages.toArray();
  }

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
