/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 *
 * @author michael
 */
abstract public class Command {

  protected static final HashMap<String, Class<? extends Command>> commandList = new HashMap<>();

  abstract public String description();

  abstract public void execute(CommandLine cmd);

  public CommandLine getCommandLine(Options opts, String[] args) throws ParseException {
    CommandLine cmd;
    CommandLineParser parser = new BasicParser();
    cmd = parser.parse(opts, args);
    return cmd;
  }

  public File[] getFilePaths(CommandLine cmd) {
    Collection<File> fileList = new ArrayList<>();

    List<String> argList = cmd.getArgList();
    argList = argList.subList(1, argList.size());

    if (argList.isEmpty()) {
      File dir = new File("input");
      SuffixFileFilter sfx = new SuffixFileFilter(".txt");
      fileList = FileUtils.listFiles(dir, sfx, TrueFileFilter.INSTANCE);
    } else {
      for (String name : argList) {
        fileList.add(new File(name));
      }
    }

    File[] files = fileList.toArray(new File[fileList.size()]);
    Arrays.sort(files);
    return files;

  }

  public abstract Options getOptions();

}
