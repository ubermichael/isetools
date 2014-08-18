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
import java.util.HashMap;
import java.util.List;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.atteo.classindex.IndexSubclasses;

/**
 * Base class for all executable commands.
 *
 * @author michael
 */
@IndexSubclasses
abstract public class Command {

  /**
   * Mapping of command names to Command objects.
   */
  protected static final HashMap<String, Class<? extends Command>> commandList = new HashMap<>();

  /**
   * Get a description of the executable command.
   *
   * @return string description
   */
  abstract public String description();

  /**
   * Execute a command.
   *
   * @param cmd CommandLine with the parameters to execute.
   *
   * @throws Exception
   */
  abstract public void execute(CommandLine cmd) throws Exception;

  /**
   * Construct a CommandLine object for an executable command by parsing the
   * java command line parameters.
   *
   * @param opts
   * @param args
   * @return parsed CommandLine object
   * @throws ParseException
   */
  public CommandLine getCommandLine(Options opts, String[] args) throws ParseException {
    CommandLine cmd;
    CommandLineParser parser = new BasicParser();
    cmd = parser.parse(opts, args);
    return cmd;
  }

  /**
   * Any extra command line arguments (after options are parsed) are considered
   * file paths passed to the command. 
   *
   * @param cmd CommandLine with the arguments.
   * @return list of File objects.
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
   * Get a list of arguments available to the command.
   * 
   * @param cmd
   * @return list of arguments.
   */
  public String[] getArgList(CommandLine cmd) {
    List<?> argList = cmd.getArgList();
    argList = argList.subList(1, argList.size());
    String[] args = argList.toArray(new String[argList.size()]);
    return args;
  }

  /**
   * Show help information for a command.
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
   * Get a list of options for the command.
   * 
   * @return Options for the command.
   */
  public abstract Options getOptions();

  /**
   * Return a string describing the command usage.
   * 
   * @return string describing the command usage.
   */
  public String getUsage() {
    return "[options] [files]";
  }
}
