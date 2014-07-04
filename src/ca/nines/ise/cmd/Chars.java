/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Chars extends Command {

  @Override
  public String description() {
    return "Report on the special characters used in one or more documents.";
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", true, "Send output to log file");
    return opts;
  }

  @Override
  public void execute(CommandLine cmd) {
    try {
      File[] files;
      Locale.setDefault(Locale.ENGLISH);
      PrintStream out = new PrintStream(System.out, true, "UTF-8");

      if (cmd.hasOption("l")) {
        out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
      }

      HashMap<String, Integer> m = new HashMap<>();
      HashMap<CharNode.CharType, HashMap<String, Integer>> types = new HashMap<>();
      files = getFilePaths(cmd);

      for(CharNode.CharType type : CharNode.CharType.values()) {
        types.put(type, new HashMap<String, Integer>());
      }

      out.println("Found " + files.length + " files to check.");
      for (File file : files) {
        DOM dom = new Builder(file).getDOM();
        Iterator<Node> i = dom.iterator();
        while (i.hasNext()) {
          Node n = i.next();
          if (n instanceof CharNode) {
            CharNode c = (CharNode) n;
            m.put(n.getText(), 1);
            types.get(c.getCharType()).put(c.getText(), 1);
          }
        }
      }

      out.println("EXISTING CHARS BY TYPE:");
      for (CharNode.CharType type : CharNode.CharType.values()) {
        out.println(type.name());
        for (String str : types.get(type).keySet()) {
          out.println(str);
        }
      }

      out.println("EXISTING CHARS: ");
      for (String k : m.keySet()) {
        out.println(k);
      }

    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
