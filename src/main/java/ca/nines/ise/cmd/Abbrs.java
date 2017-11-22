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
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Formatter;
import java.util.Locale;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Find and report old-stye abbreviation markup in one or more documents.
 *

 */
public class Abbrs extends Command {

  /**
   * {@inheritDoc}
   */
  @Override
  public String description() {
    return "Report depreciated abbrs in one or more ISE documents.";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(CommandLine cmd) throws Exception {
    File[] files;

    Locale.setDefault(Locale.ENGLISH);
    PrintStream out = new PrintStream(System.out, true, "UTF-8");

    if (cmd.hasOption("l")) {
      out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
    }

    files = getFilePaths(cmd);
    Formatter formatter = new Formatter(out);

    if (files != null) {
      out.println("Found " + files.length + " files to check.");
      for (File in : files) {
        DOM dom = new DOMBuilder(in).build();
        for (Node n : dom) {
          if (n.type() == NodeType.ABBR) {
            formatter.format("%s:%d:%d%n", n.getSource(), n.getLine(), n.getColumn());
            formatter.format("  near TLN %s%n", n.getTLN());
            formatter.format("  %s%n", n.getText().substring(0, Math.min(64, n.getText().length())));
            formatter.format("  %s%n", dom.getLine(n.getLine() - 1));
            formatter.format("%n");
          }
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", true, "Send output to log file");
    return opts;
  }

}
