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
public class Error extends Command {

  @Override
  public String description() {
    return "Command error.";
  }

  @Override
  public void execute(CommandLine cmd) {
    System.out.println("Error");
    
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    return opts;
  }
}
