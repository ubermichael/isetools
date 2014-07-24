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

  public static String version() throws IOException {
    InputStream stream = Main.class.getResourceAsStream("version.properties");
    Properties prop = new Properties();
    prop.load(stream);
    return prop.getProperty("buildVersion");
  }

  public static void execute(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException, Exception {
    String commandName = "help";
    Command cmd;
    Options opts;
    CommandLine cmdline;

    if (args.length != 0) {
      commandName = args[0];
    }
    String commandPkg = "ca.nines.ise.cmd." + commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
    cmd = (Command) Class.forName(commandPkg).newInstance();

    opts = cmd.getOptions();
    opts.addOption("h", false, "Show command options");
    cmdline = cmd.getCommandLine(opts, args);

    if (cmdline.hasOption("h")) {
      cmd.help();
      return;
    }

    cmd.execute(cmdline);
  }

  public static void main(String[] args) {
    String version = "unknown";
    try {
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
