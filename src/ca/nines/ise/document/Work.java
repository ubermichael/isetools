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
package ca.nines.ise.document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Work models the abstract concept of a work of literature, for example Hamlet
 * or Othello. It collects the editions together, grouped by the directory
 * containing them.
 *
 * @author michael
 */
public class Work extends Document implements Comparable<Work> {

  /**
   * A collection of editions.
   */
  private final Map<String, Edition> editions;

  /**
   * The MLA code for the play.
   */
  private final String playCode;

  /**
   * The root directory of the work.
   */
  private final File root;

  /**
   * Construct a work based on the directory.
   *
   * @param root The root directory for the work
   *
   * @throws IOException
   */
  public Work(File root) throws IOException {
    this.editions = new HashMap<>();
    this.root = root;
    this.playCode = root.getName();
  }

  /**
   * Add an edition for a work.
   *
   * @param file containing the edition
   * @throws IOException
   */
  public void addEdition(File file) throws IOException {
    String filename = file.getName();
    if (validName(filename)) {
      String edition = extractEdition(filename);
      editions.put(edition, new Edition(file));
    }
  }

  /**
   * Compare works lexicographically, based on the MLA play code.
   *
   * @param o the work to compare to
   * @return 0 if the works have the same play code, a value less than 0 if this
   * work's play code is lexicographically less than the argument tag's name;
   * and a value greater than 0 otherwise.
   */
  @Override
  public int compareTo(Work o) {
    return playCode.toLowerCase().compareTo(o.playCode.toLowerCase());
  }

  /**
   * Get an edition for a work.
   *
   * @param code The edition to get (M, F1, etc)
   * @return Edition the edition expected
   * @throws IOException if the edition does not exist.
   */
  public Edition getEdition(String code) throws IOException {
    if (editions.containsKey(code)) {
      return editions.get(code);
    }
    String editionPath = playCode + "_" + code + ".txt";
    String path = root.getCanonicalPath() + "/" + editionPath;
    File file = new File(path);
    if (file.exists()) {
      return new Edition(file);
    }
    throw new FileNotFoundException("Cannot find " + editionPath + " in " + root.getCanonicalPath());
  }

  /**
   * Get a list of editions, sorted by play code.
   * 
   * @return Edition[]
   * 
   * @throws IOException 
   */
  public Edition[] getEditions() throws IOException {
    File files[] = root.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return Document.validName(name);
      }
    });

    if (files.length == 0) {
      return new Edition[0];
    }

    for (File f : files) {
      addEdition(f);
    }
    Edition e[] = editions.values().toArray(new Edition[editions.size()]);
    Arrays.sort(e);
    return e;
  }

  /**
   * Gets the canonical path of the root directory containing the work.
   * 
   * @return String the root
   */
  public String getPath() throws IOException {
    return root.getCanonicalPath();
  }

  /**
   * Get the work's play code.
   * 
   * @return String the playCode
   */
  public String getPlayCode() {
    return playCode;
  }

  /**
   * Returns true if the work has a title page.
   * 
   * @return true for works with a title page.
   * 
   * @throws IOException 
   */
  public boolean hasTitlePage() throws IOException {
    return root.getCanonicalPath().contains("withTitlePage");
  }

  /**
   * Return a string representation of a work. Really only useful for 
   * debugging.
   * 
   * @return a string representation of the work.
   */
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
