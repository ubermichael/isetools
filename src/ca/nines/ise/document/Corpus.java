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
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;

/**
 * A corpus is a collection of works.
 *
 * @author michael
 */
public class Corpus {

  /**
   * The directory containing the works.
   */
  private final File root;

  /**
   * A list of works, keyed on play code.
   */
  // @todo write a case-insensitive wrapper around Map<String, ?>
  private final Map<String, Work> works = new HashMap<>();

  /**
   * Construct a corpus object from a file path
   * 
   * @param root of the corpus
   */
  public Corpus(String root) {
    this(new File(root));
  }

  /**
   * Construct a corpus object from a directory.
   * 
   * @param root of the corpus
   */
  public Corpus(File root) {
    this.root = root;
  }

  /**
   * Fetch a Work from the corpus.
   * 
   * @param code the play code to search for
   * 
   * @return Work found
   * 
   * @throws IOException if something goes boom.
   */
  public Work getWork(String code) throws IOException {
    File w;

    if (works.containsKey(code.toLowerCase())) {
      return works.get(code.toLowerCase());
    }

    w = new File(root.getCanonicalPath() + "/noTitlePage/" + code);
    if (w.exists()) {
      works.put(code, new Work(w));
    }

    w = new File(root.getCanonicalPath() + "/withTitlePage/" + code);
    if (w.exists()) {
      works.put(code, new Work(w));
    }
    return works.get(code);
  }

  /**
   * Get a list of all the works available
   * 
   * @return Work[] list of works
   * 
   * @throws IOException 
   */
  public Work[] getWorks() throws IOException {
    File[] dirs = ArrayUtils.addAll(
            new File(root.getCanonicalFile() + "/noTitlePage").listFiles(),
            new File(root.getCanonicalFile() + "/withTitlePage").listFiles()
    );
    for (File d : dirs) {
      File editions[] = d.listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          return Document.validName(name);
        }
      });
      if (editions.length == 0) {
        continue;
      }
      Work w = new Work(d);
      for (File edition : editions) {
        w.addEdition(edition);
      }
      works.put(d.getName(), w);
    }
    Work[] list = works.values().toArray(new Work[works.size()]);
    Arrays.sort(list);
    return list;
  }

  /**
   * Create a string representation of a corpus. Mostly useful for debugging.
   * 
   * @return Stringified corpus
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    try {
      for (Work w : getWorks()) {
        sb.append(w.toString());
      }
    } catch (IOException ex) {
      Logger.getLogger(Corpus.class.getName()).log(Level.SEVERE, null, ex);
    }
    return sb.toString();
  }

}
