/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import ca.nines.ise.document.Corpus;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Works extends Command {

  @Override
  public String description() {
    return "List the documents.";
  }

  @Override
  public void execute(CommandLine cmd) {
    try {
      Corpus corpus;
      String root = "input";
      
      Locale.setDefault(Locale.ENGLISH);
      PrintStream out = new PrintStream(System.out, true, "UTF-8");

      if (cmd.hasOption("l")) {
        out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
      }
      
      corpus = new Corpus(root);
      out.println(corpus);
      
    } catch (Exception ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", true, "Send output to log file");
    return opts;
  }

}
