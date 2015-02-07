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
 * A corpus is a collection of Works.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Corpus {

  /**
   * File system path to the root of the corpus.
   */
  private final File root;

  /**
   * Map work codes to works for easy access.
   */
  // @todo write a case-insensitive wrapper around Map<String, ?>
  private final Map<String, Work> works = new HashMap<>();

  /**
   * Construct a corpus for a file system path.
   *
   * @param root Path to the root of the corpus.
   */
  public Corpus(String root) {
    this(new File(root));
  }

  /**
   * Construct a corpus for a file system path.
   *
   * @param root Root of the corpus on the file system.
   */
  public Corpus(File root) {
    this.root = root;
  }

  /**
   * Get a work from the corpus
   *
   * @param code
   * @return Work
   * @throws IOException if the work does not exist.
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
   * Return a list of Works, sorted by play code.
   *
   * @return Work[]
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
   * Produce a human-readable description of the corpus.
   *
   * @return String
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
