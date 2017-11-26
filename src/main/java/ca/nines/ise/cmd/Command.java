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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.atteo.classindex.ClassIndex;
import org.atteo.classindex.IndexSubclasses;

/**
 * Superclass for all executable commands.
 */
@IndexSubclasses
abstract public class Command {

    private static Map<String, Command> commandList = null;
    
    public static Map<String, Command> getCommands() {
        if(commandList == null) {
            commandList = new TreeMap<>();
            for(Class<? extends Command> cls : ClassIndex.getSubclasses(Command.class)) {
                try {
                    Command c = cls.newInstance();
                    commandList.put(c.getName(), c);
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return commandList;
    }

  /**
   * Return a description of the command.
   *
   * @return String
   */
  abstract public String description();
  
  /**
   * Get the name of the command, which the user types at the commandline to
   * execute the command.
   * 
   * @return the command name.
   */
  abstract public String getName();

  /**
   * Execute a command.
   *
   * @param cmd Parsed command line options.
   * @throws Exception if an exception happens.
   */
  abstract public void execute(CommandLine cmd) throws Exception;

  /**
   * Generates the Options for the command.
   *
   * @return Options A description of the options expected on the command line.
   */
  public abstract Options getOptions();

  /**
   * Parse the command line args[] array and return the result.
   *
   * @param opts Description of the options expected
   * @param args Raw arguments from teh command line.
   * @throws ParseException if the command line cannot be parsed.
   * 
   * @return CommandLine
   */
  public CommandLine getCommandLine(Options opts, String[] args) throws ParseException {
    CommandLine cmd;
    CommandLineParser parser = new BasicParser();
    cmd = parser.parse(opts, args);
    return cmd;
  }

  /**
   * Get a list of file paths from the command line arguments.
   *
   * @param cmd Parsed command line arguments.
   * @return File[]
   */
  public File[] getFilePaths(CommandLine cmd) {
    Collection<File> fileList = new ArrayList<>();

    List<?> argList = cmd.getArgList();
    argList = argList.subList(1, argList.size());
    String[] args = argList.toArray(new String[argList.size()]);

    if (argList.isEmpty()) {
      File dir = new File("input");
      SuffixFileFilter sfx = new SuffixFileFilter(".txt");
      fileList = FileUtils.listFiles(dir, sfx, TrueFileFilter.INSTANCE);
    } else {
      for (String name : args) {
        fileList.add(new File(name));
      }
    }

    File[] files = fileList.toArray(new File[fileList.size()]);
    Arrays.sort(files);
    return files;
  }

  /**
   * Get a list of arguments for the command.
   *
   * @param cmd Get the list of arguments (not options) passed from the command line.
   * @return String[]
   */
  public String[] getArgList(CommandLine cmd) {
    List<String> argList = cmd.getArgList();
    argList = argList.subList(1, argList.size());
    String[] args = argList.toArray(new String[argList.size()]);
    return args;
  }

  /**
   * Turn the description and arguments list into nicely formatted help and
   * usage documentation.
   */
  public void help() {
    HelpFormatter formatter = new HelpFormatter();
    Options opts = getOptions();
    System.out.println(description());
    if (opts.getOptions().size() > 0) {
      formatter.printHelp(this.getClass().getSimpleName().toLowerCase() + " " + getUsage(), opts);
    } else {
      System.out.println(this.getClass().getSimpleName().toLowerCase() + " " + getUsage());
    }
  }

  /**
   * Generate a brief usage of the command.
   *
   * @return String
   */
  public String getUsage() {
    return "[options] [files]";
  }
}
