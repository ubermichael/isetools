/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.validator.TagValidator;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Main {

  public static void main(String[] args) {
    Collection<File> fileList = null;
    Log log = Log.getInstance();
    Locale.setDefault(Locale.ENGLISH);
    PrintStream out = null;

    try {
      out = new PrintStream(System.out, true, "UTF-8");
      Schema schema = new Schema();
      TagValidator tv = new TagValidator(schema);
      
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

      out.println("Found " + fileList.size() + " files to check.");

      Iterator fi = fileList.iterator();
      while (fi.hasNext()) {
        File in = (File) fi.next();
        DOM dom = new Builder(in).getDOM();
        tv.validate(dom);
        out.println(log);
        log.clear();
      }
    } catch (Exception ex) {
      ex.printStackTrace(out);
    }
  }
}
