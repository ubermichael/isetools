/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author michael
 */
abstract public class Document {
  
    protected static final Pattern editionPattern = 
            Pattern.compile("^(?<name>[a-zA-Z0-9]+)_(?<edition>[a-zA-Z0-9]+)\\.txt$");

    public static final boolean validName(String filename) {
      Matcher m = editionPattern.matcher(filename);
      return m.matches();
    }
    
    public static final String extractName(String filename) {
      Matcher m = editionPattern.matcher(filename);
      if(m.matches()) {
        return m.group("name");
      }
      if(m.find()) {
        return m.group("name");
      }
      return null;
    }
  
    public static final String extractEdition(String filename) {
      Matcher m = editionPattern.matcher(filename);
      if(m.matches()) {
        return m.group("edition");
      }
      if(m.find()) {
        return m.group("edition");
      }
      return null;
    }
}
