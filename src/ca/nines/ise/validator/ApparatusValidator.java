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
