/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 *
 * @author michael
 */
public class Validate extends Command {

  @Override
  public String description() {
    return "Validate an ISE SGMLish file";
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", false, "Send error messages to file");
    return opts;
  }

  @Override
  public void execute(CommandLine cmd) {
    
    System.out.println("validate.");
    
    if (cmd.hasOption("l")) {
      System.out.println("found an L.");
    }
  }

}
