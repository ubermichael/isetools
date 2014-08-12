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

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOM.DOMStatus;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.transformer.Renumberer;
import ca.nines.ise.writer.Writer;
import ca.nines.ise.writer.SGMLWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 *
 * @author michael
 */
public class Renumber extends Command {

  @Override
  public String description() {
    return "Transform an ISE SGML document another format.";
  }

  @Override
  public void execute(CommandLine cmd) throws Exception {
    PrintStream outputStream;
    Locale.setDefault(Locale.ENGLISH);
    outputStream = new PrintStream(System.out, true, "UTF-8");
    if (cmd.hasOption("o")) {
      outputStream = new PrintStream(new FileOutputStream(cmd.getOptionValue("o")), true, "UTF-8");
    }

    String[] files = getArgList(cmd);
    if (files.length > 1) {
      System.err.println("Can only renumber one file at a time.");
      help();
      System.exit(1);
    }
    DOM dom = new DOMBuilder(new File(files[0])).build();
    if (dom.getStatus() != DOMStatus.ERROR) {
      Renumberer r = new Renumberer();
      if (cmd.hasOption("l")) {
        r.setRenumberLine(true);
      }
      if (cmd.hasOption("qln")) {
        r.setRenumberQln(true);
      }
      if (cmd.hasOption("tln")) {
        r.setRenumberTln(true);
      }
      if (cmd.hasOption("wln")) {
        r.setRenumberWln(true);
      }
      if (cmd.hasOption("act")) {
        r.setRenumberAct(true);
      }
      if (cmd.hasOption("scene")) {
        r.setRenumberScene(true);
      }
      if (cmd.hasOption("stanza")) {
        r.setRenumberStanza(true);
      }
      if (cmd.hasOption("page")) {
        r.setRenumberPage(true);
      }
      
      dom = r.transform(dom);
      
    }
    Writer out = new SGMLWriter(outputStream);
    out.render(dom);
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("o", true, "Send output to file.");
    opts.addOption("l", false, "Renumber L tags.");
    opts.addOption("qln", false, "Renumber QLN tags.");
    opts.addOption("wln", false, "Renumber WLN tags.");
    opts.addOption("act", false, "Renumber ACT tags.");
    opts.addOption("scene", false, "Renumber SCENE tags.");
    opts.addOption("stanza", false, "Renumber STANZA tags.");
    opts.addOption("page", false, "Renumber PAGE tags.");

    return opts;
  }

  @Override
  public String getUsage() {
    return "[options] file";
  }

}
