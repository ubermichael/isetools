/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.cmd.Command;
import ca.nines.ise.cmd.Error;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author michael
 */
public class Main {

  public Command getCommand(String commandName) {
    String className = "ca.nines.ise.cmd." + commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
    Command cmd;

    try {
      cmd = (Command) Class.forName(commandName).newInstance();
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public static void main(String[] args) {

    String commandName = "help";
    Command cmd;
    Options opts;
    CommandLine cmdline;

    if (args.length != 0) {
      commandName = args[0];
    }
    String commandPkg = "ca.nines.ise.cmd." + commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
    cmd = new Error();

    try {
      cmd = (Command) Class.forName(commandPkg).newInstance();
    } catch (ClassNotFoundException e) {
      System.err.println("Cannot find command " + commandName + " in " + commandPkg);
    } catch (InstantiationException e) {
      System.err.println("Cannot instantiate command " + commandName);
    } catch (IllegalAccessException e) {
      System.err.println("Cannot access command " + commandName);
    }

    opts = cmd.getOptions();
    cmdline = null;
    try {
      cmdline = cmd.getCommandLine(opts, args);
    } catch (ParseException ex) {
      System.err.println(ex.getMessage());
    }

    if (cmdline == null) {
      System.exit(-1);
    }

    cmd.execute(cmdline);
  }
}
