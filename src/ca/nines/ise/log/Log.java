/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.log;

import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class Log {
  private final ArrayList<Message> messages = new ArrayList<>();

  private static final Log log = new Log();
  
  private Log() {}
  
  public static Log getInstance() {
    return log;
  }
  
  public void add(Message m) {
    messages.add(m);
  }
  
  public Message[] messages() {
    return (Message[]) messages.toArray();
  }
  
  @Override
  public String toString() {
    String s = "";
    for(Message message : messages) {
      s += message;
      s += "\n";      
    }
    return s;
  }
  
}
