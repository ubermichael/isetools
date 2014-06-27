/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author michael
 */
public class Work implements Comparable<Work> {

  private String path;
  private String playCode;
  private final HashMap<String, File> editions;

  public Work(File dir) throws IOException {    
    this.editions = new HashMap<>();
    path = dir.getCanonicalPath();
    playCode = dir.getName();
  }

  @Override
  public int compareTo(Work o) {
    return playCode.toLowerCase().compareTo(o.playCode.toLowerCase());
  }

  public void addEdition(File f) {
    Pattern p = Pattern.compile("_([a-zA-Z0-9]+)\\.txt$");
    Matcher m = p.matcher(f.getName());
    if(m.find()) {
      editions.put(m.group(1), f);
    } 
  }

  public String[] getEditions() {
    String list[] = editions.keySet().toArray(new String[editions.size()]);
    Arrays.sort(list);
    return list;
  }
  
  public Edition getEdition(String code) throws IOException {
    File filePath = editions.get(code);
    return new Edition(filePath);
  }
  
  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @param path the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * @return the playCode
   */
  public String getPlayCode() {
    return playCode;
  }

  /**
   * @param playCode the playCode to set
   */
  public void setPlayCode(String playCode) {
    this.playCode = playCode;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(playCode).append("\n");
    for(String s : getEditions()) {
      Edition edition;
      try {
        edition = new Edition(editions.get(s));
        sb.append(edition);
      } catch (IOException ex) {
        Logger.getLogger(Work.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return sb.toString();
  }

}
