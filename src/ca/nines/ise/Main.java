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
import ca.nines.ise.cmd.Error;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author michael
 */
public class Main {

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
    opts.addOption("h", false, "Show command options");
    cmdline = null;
    try {
      cmdline = cmd.getCommandLine(opts, args);
    } catch (ParseException ex) {
      System.err.println(ex.getMessage());
    }

    if (cmdline == null) {
      System.exit(-1);
      return;
    }
    
    if(cmdline.hasOption("h")) {
      cmd.help();
      return;
    }

    cmd.execute(cmdline);
  }
}
