/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author michael
 */
public class Validate extends Command {

  static {
    Command.commandList.put("validate", Command.class);
  }

  public String description() {
    return "Validate an ISE SGMLish file";
  }

  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", false, "Set error messages to file");
    return opts;
  }

  public CommandLine getCommandLine(Options opts, String[] args) throws ParseException {
    CommandLine cmd;
    CommandLineParser parser = new BasicParser();
    cmd = parser.parse(opts, args);
    return cmd;
  }

  public void execute(String[] args) throws ParseException {
    Options opts = getOptions();
    CommandLine cmd = getCommandLine(opts, args);

    if (cmd.hasOption("l")) {
      System.out.println("found an L.");
    }
  }

  public static void main(String[] args) throws ParseException {
    Validate v = new Validate();
    v.execute(args);
  }

}
