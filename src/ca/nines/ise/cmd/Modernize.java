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

import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOM.DOMStatus;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.writer.Writer;
import ca.nines.ise.writer.RTFWriter;
import ca.nines.ise.writer.TextWriter;
import ca.nines.ise.writer.XMLWriter;
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
public class Modernize extends Command {

  @Override
  public String description() {
    return "Transform an ISE SGML document another format.";
  }

  @Override
  public void execute(CommandLine cmd) throws Exception {
    PrintStream out;
    Writer renderer = null;
    Locale.setDefault(Locale.ENGLISH);
    out = new PrintStream(System.out, true, "UTF-8");
    if (cmd.hasOption("o")) {
      out = new PrintStream(new FileOutputStream(cmd.getOptionValue("o")), true, "UTF-8");
    }

    String[] files = getArgList(cmd);
    DOM dom = new DOMBuilder(new File(files[0])).build();
    if (dom.getStatus() != DOMStatus.ERROR) {
      renderer.render(dom);
    }
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("o", true, "Send output to file.");
    return opts;
  }

  @Override
  public String getUsage() {
    return "[options] file";
  }

}
