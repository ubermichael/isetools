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
 * Transform a document into HTML, XML, Text, or RTF.
 *
 * @author michael
 */
public class Transform extends Command {

  /**
   * {@inheritDoc}
   */
  @Override
  public String description() {
    return "Transform an ISE SGML document another format.";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(CommandLine cmd) throws Exception {
    PrintStream out;
    Writer renderer = null;
    Locale.setDefault(Locale.ENGLISH);
    out = new PrintStream(System.out, true, "UTF-8");
    if (cmd.hasOption("o")) {
      out = new PrintStream(new FileOutputStream(cmd.getOptionValue("o")), true, "UTF-8");
    }

    if (cmd.hasOption("text")) {
      renderer = new TextWriter(out);
    }
    if (cmd.hasOption("xml")) {
      renderer = new XMLWriter(out);
    }
    if (cmd.hasOption("rtf")) {
      renderer = new RTFWriter(out);
    }
    
    if(renderer == null) {
      System.err.println("You must specify a transformation");
      System.exit(1);
    }

    String[] files = getArgList(cmd);
    DOM dom = new DOMBuilder(new File(files[0])).build();
    Annotation ann = Annotation.builder().build();
    if (files.length > 1) {
      ann = Annotation.builder().from(new File(files[1])).build();
    }
    if (dom.getStatus() != DOMStatus.ERROR) {
      renderer.render(dom, ann);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("o", true, "Send output to file.");
    opts.addOption("xml", false, "Transform output to XML.");
    opts.addOption("text", false, "Transform output to UTF-8 (unicode) text.");
    opts.addOption("rtf", false, "Transform output to an RTF document.");
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
