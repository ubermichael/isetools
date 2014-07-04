/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Syntax {

  public static void main(String[] args) {
    Collection<File> fileList = null;
    Log log = Log.getInstance();
    Locale.setDefault(Locale.ENGLISH);
    PrintStream out;

    try {
      out = new PrintStream(System.out, true, "UTF-8");

      if (args.length == 0) {
        File dir = new File("data/sgml");
        SuffixFileFilter sfx = new SuffixFileFilter(".txt");
        fileList = FileUtils.listFiles(dir, sfx, TrueFileFilter.INSTANCE);
      } else {
        for (String name : args) {
          fileList = new ArrayList<>();
          fileList.add(new File(name));
        }
      }
      if (fileList != null) {
        out.println("Found " + fileList.size() + " files to check.");
        for (File in : fileList) {
          DOM dom = new Builder(in).getDOM();
          if (log.count() > 0) {
            out.println(log);
            log.clear();
          }
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(Syntax.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
