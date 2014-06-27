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
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author michael
 */
public class Corpus {

  private final HashMap<String, Work> works;

  public Corpus(File root) throws IOException {
    this.works = new HashMap<>();
    File[] dirs = ArrayUtils.addAll(
            new File(root.getCanonicalFile() + "/noTitlePage").listFiles(),
            new File(root.getCanonicalFile() + "/withTitlePage").listFiles()
    );
    for (File d : dirs) {
      File editions[] = d.listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          return name.endsWith(".txt");
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
  }

  public Work getWork(String code) {
    return works.get(code.toLowerCase());
  }

  public Work[] getWorks() {
    Work[] list = works.values().toArray(new Work[works.size()]);
    Arrays.sort(list);
    return list;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Work w : getWorks()) {
      sb.append(w.toString());
    }
    return sb.toString();
  }

}
