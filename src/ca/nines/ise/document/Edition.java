/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.document;

import ca.nines.ise.dom.DOM;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author michael
 */
public class Edition {
  private final String parentDir;
  private final String fileName;
  private final String playCode;
  private final String editionCode;
  
  public Edition(File file) throws IOException {
    parentDir = file.getParentFile().getCanonicalPath();
    fileName = file.getName();    
    Pattern p = Pattern.compile("(\\p{Alnum}+)_(\\p{Alnum}+)\\.txt");
    Matcher m = p.matcher(fileName);
    if(m.matches()) {
      playCode = m.group(1);
      editionCode = m.group(2);
    } else {
      playCode = "";
      editionCode = "";
    }
  }
  
  public boolean hasCollations() throws IOException {
    File annotationsFile = new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_collation.xml");
    return annotationsFile.exists();
  }
  
  public boolean hasAnnotations() {
    File annotationsFile = new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_annotation.xml");
    return annotationsFile.exists();
  }
  
  public DOM getDOM() {
    return null;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);
    try {
      formatter.format("%4s %12s %12s%n", editionCode, hasAnnotations() ? "annotations" : "", hasCollations()? "collations" : "");
    } catch (IOException ex) {
      Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
    }
    return sb.toString();
  }
}
