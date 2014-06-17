/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.Node;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Abbrs {

  public static void main(String[] args) {

    Collection<File> fileList = null;
    try {
      if (args.length == 0) {
        FileUtils fu = new FileUtils();
        File dir = new File("data/sgml");
        SuffixFileFilter sfx = new SuffixFileFilter(".txt");
        fileList = FileUtils.listFiles(dir, sfx, TrueFileFilter.INSTANCE);
      } else {
        for (String name : args) {
          fileList = new ArrayList<>();
          fileList.add(new File(name));
        }
      }

      System.out.println("Found " + fileList.size() + " files to check.");

      HashMap<String, String> m = new HashMap<>();

      Iterator fi = fileList.iterator();
      while (fi.hasNext()) {
        File in = (File) fi.next();
        DOM dom = new Builder(in).getDOM();
        Iterator<Node> i = dom.iterator();
        while (i.hasNext()) {
          Node n = i.next();
          if (n.type().equals("#ABBR")) {
            if (n.getText().length() < 12) {
              m.put(n.getText(), "");
            }
          }
        }
      }
      System.out.println(m.keySet());
    } catch (Exception ex) {
      Logger.getLogger(Abbrs.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
