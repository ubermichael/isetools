/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import java.io.File;
import java.io.FilenameFilter;
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

  private File root;
  private String playCode;
  private final HashMap<String, Edition> editions;

  public Work(File root) throws IOException {
    this.editions = new HashMap<>();
    this.root = root;
    this.playCode = root.getName();
  }

  @Override
  public int compareTo(Work o) {
    return playCode.toLowerCase().compareTo(o.playCode.toLowerCase());
  }

  public void addEdition(File f) throws IOException {
    Pattern p = Pattern.compile("_([a-zA-Z0-9]+)\\.txt$");
    Matcher m = p.matcher(f.getName());
    if (m.find()) {
      editions.put(m.group(1), new Edition(f));
    }
  }

  public Edition[] getEditions() throws IOException {

    File files[] = root.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.matches("^[a-zA-Z0-9]+_[a-zA-Z0-9]+\\.txt$");
      }
    });

    if (files.length == 0) {
      return new Edition[0];
    }

    Pattern p = Pattern.compile("[a-zA-Z0-9]+_([a-zA-Z0-9]+)\\.txt");
    for (File f : files) {
      addEdition(f);
    }
    Edition e[] = editions.values().toArray(new Edition[editions.size()]);
    Arrays.sort(e);
    return e;
  }

  public String[] listEditions() {
    return null;
  }

  public Edition getEdition(String code) {
    return editions.get(code);
  }

  public boolean hasTitlePage() throws IOException {
    return root.getCanonicalPath().contains("withTitlePage");
  }

  /**
   * @return the root
   */
  public String getPath() throws IOException {
    return root.getCanonicalPath();
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
    try {
      for (Edition edition : getEditions()) {
        sb.append(edition);
      }
    } catch (IOException ex) {
      Logger.getLogger(Work.class.getName()).log(Level.SEVERE, null, ex);
    }
    return sb.toString();
  }

}
