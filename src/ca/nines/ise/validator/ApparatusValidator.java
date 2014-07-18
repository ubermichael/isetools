/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.validator;

import ca.nines.ise.document.Apparatus;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.lemma.Lemma;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author michael
 * @param <T>
 */
abstract public class ApparatusValidator<T extends Apparatus>{
  
  private static final Pattern singleTlnPattern = Pattern.compile("\\p{Digit}+(?:\\.\\p{Digit}+)?");
  private static final Pattern splitTlnPattern = Pattern.compile("\\p{Digit}+-\\p{Digit}+");
  private static final Pattern splitLemPattern = Pattern.compile("(.*?) ?. . . ?(.*?)");
  
  public void validate(Lemma lem) {
    Matcher matcher;
    String cName = lem.getClass().getSimpleName().toLowerCase();

    matcher = singleTlnPattern.matcher(lem.getTln());
    if(matcher.matches()) {
      return;
    }
    
    matcher = splitTlnPattern.matcher(lem.getTln());
    if(matcher.matches()) {
      return;
    }
    
    Message m = Message.builder("validator." + cName + ".bad_tln")
            .fromLemma(lem)
            .addNote("The invalid TLN looks like " + lem.getTln())
            .build();
    Log.addMessage(m);
  }
  
  public void validate(DOM dom, Lemma lem) {
    
  }
}
