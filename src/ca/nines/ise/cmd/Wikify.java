/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Wikify extends Command {

  @Override
  public String description() {
    return "Create a wikified version of the schema.";
  }

  @Override
  public void execute(CommandLine cmd) {
    try {
      Locale.setDefault(Locale.ENGLISH);
      PrintStream out = new PrintStream(System.out, true, "UTF-8");

      if (cmd.hasOption("l")) {
        out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
      }

      System.out.println("not implemented.");

    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Wikify.class.getName()).log(Level.SEVERE, null, ex);
    } 
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("o", true, "Send output to file");
    opts.addOption("L", false, "Show a list of locations");
    opts.addOption("l", true, "Generate output for specific location");
    return opts;
  }

  @Override
  public String getUsage() {
    return "[options]";
  }
}
