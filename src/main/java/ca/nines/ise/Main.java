/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package ca.nines.ise;

import ca.nines.ise.cmd.Command;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Main entry point into the ISE tools. Figures out what command to run, what 
 * options go to that command, and then runs it.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Main {

  /**
   * Gets version information from the version.properties file and returns it.
   * version.properties is maintained automatically by ant in the build.xml
   * file.
   * 
   * @return String
   * @throws IOException 
   */
  public static String version() throws IOException {
//    InputStream stream = Main.class.getResourceAsStream("version.properties");
//    Properties prop = new Properties();
//    prop.load(stream);
//    return prop.getProperty("buildVersion")+ " " + prop.getProperty("buildBranch");
      return "unknown";
  }

  /**
   * Execute a command, defaulting to the help command.
   * 
   * @param args
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ParseException
   * @throws Exception 
   */
  public static void execute(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException, Exception {
    String commandName = "help";
    Command cmd;
    Options opts;
    CommandLine cmdline;

    if (args.length != 0) {
      commandName = args[0];
    }
    String commandPkg = "ca.nines.ise.cmd." + commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
	
	cmd = new ca.nines.ise.cmd.Help();
	try {
      cmd = (Command) Class.forName(commandPkg).newInstance();
	} catch(ClassNotFoundException e) {
	  System.err.println("Uknown command: " + commandName);
	  cmd.execute(null);
	  System.exit(-1);
	}
	
    opts = cmd.getOptions();
    opts.addOption("h", false, "Show command options");
	opts.addOption("help", false, "Show command options");
    cmdline = cmd.getCommandLine(opts, args);

    if (cmdline.hasOption("h") || cmdline.hasOption("help")) {
      cmd.help();
      return;
    }

    cmd.execute(cmdline);
  }

  /**
   * Entry point for the application. Catches all exceptions.
   * 
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    String version = "unknown";    
    try {
      if(args.length == 0) {
          args = new String[]{"help"};
      }
      version = version();
      execute(args);
    } catch (ClassNotFoundException ex) {
      System.err.println("Cannot find command class " + args[0]);
      System.err.println("iTools version "+ version);
      ex.printStackTrace(System.err);
    } catch (IllegalAccessException ex) {
      System.err.println("Cannot access command class " + args[0]);
      System.err.println("iTools version "+ version);
      ex.printStackTrace(System.err);
    } catch (InstantiationException ex) {
      System.err.println("Cannot create command class " + args[0]);
      System.err.println("iTools version "+ version);
      ex.printStackTrace(System.err);
    } catch (ParseException ex) {
      System.err.println("Cannot parse command line arguments " + args[0]);
      System.err.println(ex.getMessage());
      System.err.println("iTools version "+ version);
      ex.printStackTrace(System.err);
    } catch (IOException ex) {
      System.err.println("I/O error " + args[0]);
      System.err.println("iTools version "+ version);
      ex.printStackTrace(System.err);
    } catch (Exception ex) {
      System.err.println("Internal error " + args[0]);
      System.err.println("iTools version "+ version);
      ex.printStackTrace(System.err);
    } 
  }
}
