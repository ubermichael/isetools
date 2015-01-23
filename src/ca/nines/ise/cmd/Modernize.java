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
import ca.nines.ise.node.Node;
import ca.nines.ise.node.TextNode;
import ca.nines.ise.transformer.Formatter;
import ca.nines.ise.transformer.Modernizer;
import ca.nines.ise.transformer.Normalizer;
import ca.nines.ise.writer.Writer;
import ca.nines.ise.writer.SGMLWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Modernize an old-spelling edition.
 *
 * @author michael
 */
public class Modernize extends Command {

  /**
   * {@inheritDoc}
   */
  @Override
  public String description() {
    return "Modernize the markup of an old-spelling edition.";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(CommandLine cmd) throws Exception {
    PrintStream out;
    Writer renderer;
    Locale.setDefault(Locale.ENGLISH);
    out = new PrintStream(System.out, true, "UTF-8");
    if (cmd.hasOption("o")) {
      out = new PrintStream(new FileOutputStream(cmd.getOptionValue("o")), true, "UTF-8");
    }

    renderer = new SGMLWriter(out);
    String[] files = getArgList(cmd);
    DOM dom = new DOMBuilder(new File(files[0])).build();
    if (dom.getStatus() == DOMStatus.ERROR) {
      System.err.println("Document contains errors. Cannot continue.");
      System.exit(-1);
    }

    Modernizer m = new Modernizer();
    Normalizer n = new Normalizer();
    Formatter f = new Formatter();
    
    dom = m.transform(dom);
    dom = n.transform(dom);
    dom = f.transform(dom);
    
    renderer.render(dom);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("o", true, "Send output to file.");
    return opts;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUsage() {
    return "[options] file";
  }

}
