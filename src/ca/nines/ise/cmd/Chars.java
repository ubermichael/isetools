/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
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
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.Node;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Report the special characters used in one or more documents.
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Chars extends Command {

  /**
   * {@inheritDoc}
   */
  @Override
  public String description() {
    return "Report on the special characters used in one or more documents.";
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

    Map<String, Integer> m = new HashMap<>();
    Map<CharNode.CharType, HashMap<String, Integer>> types = new HashMap<>();
    files = getFilePaths(cmd);

    for (CharNode.CharType type : CharNode.CharType.values()) {
      types.put(type, new HashMap<String, Integer>());
    }

    out.println("Found " + files.length + " files to check.");
    for (File file : files) {
      DOM dom = new DOMBuilder(file).build();
      for (Node n : dom) {
        if (n instanceof CharNode) {
          CharNode c = (CharNode) n;
          m.put(n.getText(), 1);
          types.get(c.getCharType()).put(c.getText(), 1);
        }
      }
    }

    out.println("EXISTING CHARS BY TYPE:");
    for (CharNode.CharType type : CharNode.CharType.values()) {
      out.println(type.name());
      for (String str : types.get(type).keySet()) {
        out.println(str);
      }
    }

    out.println("EXISTING CHARS: ");
    for (String k : m.keySet()) {
      out.println(k);
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
