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

import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Check the syntax of a document, without validating it.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Syntax extends Command {

  @Override
  public String description() {
    return "Syntax check one or more ISE SGML documents.";
  }

  @Override
  public void execute(CommandLine cmd) throws Exception {
    File[] files;

    Log log = Log.getInstance();
    Locale.setDefault(Locale.ENGLISH);
    PrintStream out = new PrintStream(System.out, true, "UTF-8");

    if (cmd.hasOption("l")) {
      out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
    }

    files = getFilePaths(cmd);
    out.println("Found " + files.length + " files to check.");
    for (File file : files) {
      DOM dom = new DOMBuilder(file).build();
      if (log.count() > 0) {
        out.println(log);
        log.clear();
      }
    }
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", true, "Send error messages to log file");
    return opts;
  }

}
