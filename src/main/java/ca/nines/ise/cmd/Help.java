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
package ca.nines.ise.cmd;

import ca.nines.ise.Main;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.atteo.classindex.ClassIndex;

/**
 * Describes the commands available and how they can be used.
 *

 */
public class Help extends Command {

  /**
   * {@inheritDoc}
   */
  @Override
  public String description() {
    return "Display useful help messages.";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(CommandLine cmd) throws Exception {

    System.out.println("iTools " + Main.version());
    System.out.println("General usage: java -jar path/to/isetools.jar [command] [options]");
    System.out.println("For a specific command: java -jar path/to/isetools.jar [command] -h\n");
    System.out.println("[command] is one of the following: ");
    listCommands();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Options getOptions() {
    Options opts = new Options();
    return opts;
  }

  /**
   * Format a list of commands and send it to System.out.
   *
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public void listCommands() throws InstantiationException, IllegalAccessException {
    Formatter formatter = new Formatter(System.out);
    formatter.format("%n%12s   %s%n%n", "command", "description");

    Map<String, String> descriptions = new HashMap<>();
    for (Class<?> cls : ClassIndex.getSubclasses(Command.class)) {
      Command cmd = (Command) cls.newInstance();
      descriptions.put(cls.getSimpleName().toLowerCase(), cmd.description());
    }
    String names[] = descriptions.keySet().toArray(new String[descriptions.size()]);
    Arrays.sort(names);
    for (String name : names) {
      formatter.format("%12s   %s%n", name, descriptions.get(name));
    }
  }
}
