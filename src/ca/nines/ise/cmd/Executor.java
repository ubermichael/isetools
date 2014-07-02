/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author michael
 */
public class Executor {

  public Command getCommand(String commandName) {
    String className = "ca.nines.ise.cmd." + commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
    Command cmd;

    try {
      cmd = (Command) Class.forName(commandName).newInstance();
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
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
      System.arraycopy(args, 0, args, 1, args.length - 1);
    }
    String commandPkg = "ca.nines.ise.cmd." + commandName.substring(0,1).toUpperCase() + commandName.substring(1);
    try {
      cmd = (Command) Class.forName(commandPkg).newInstance();
    } catch (ClassNotFoundException e) {
      System.err.println("Cannot find command " + commandName);
      System.err.println("Should be " + commandPkg);
      cmd = new Error();
    } catch (InstantiationException e) {
      System.err.println("Cannot instantiate command " + commandName);
      cmd = new Error();
    } catch (IllegalAccessException e) {
      System.err.println("Cannot access command " + commandName);
      cmd = new Error();
    }

    opts = cmd.getOptions();
    cmdline = null;
    try {
      cmdline = cmd.getCommandLine(opts, args);
    } catch (ParseException ex) {
      Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
    }

    if (cmdline == null) {
      System.exit(-1);
    }

    cmd.execute(cmdline);
  }
}
