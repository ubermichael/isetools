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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author michael
 */
public class Corpus {

  private final File root;

  // @todo write a case-insensitive wrapper around Map<String, ?>
  private final Map<String, Work> works = new HashMap<>();

  public Corpus(String root) {
    this(new File(root));
  }

  public Corpus(File root) {
    this.root = root;
  }

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
