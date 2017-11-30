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
import ca.nines.ise.log.Message;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.validator.DOMValidator;
import ca.nines.ise.validator.NestingValidator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Validate a document or documents.
 *

 */
public class Validate extends Command {

  /**
   * {@inheritDoc}
   */
  @Override
  public String description() {
    return "Validate one or more ISE SGML documents.";
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
      return "validate";
  }

  @Override
  public void execute(CommandLine cmd) throws Exception {
    File[] files;

    Log log = Log.getInstance();
    Locale.setDefault(Locale.ENGLISH);
    Schema schema = Schema.defaultSchema();
    DOMValidator dv = new DOMValidator();
    NestingValidator nv = new NestingValidator(schema);
    PrintStream out = new PrintStream(System.out, true, "UTF-8");

    if (cmd.hasOption("l")) {
      out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
    }

    files = getFilePaths(cmd);
    if (files != null) {
      out.println("Found " + files.length + " files to check.");
      for (File in : files) {
        DOM dom = new DOMBuilder(in).build();
        if (dom.getStatus() != DOM.DOMStatus.ERROR) {
          dv.validate(dom, schema);
          nv.validate(dom);
        } else {
          Message m = Message.builder("dom.errors")
                  .setSource(dom.getSource())
                  .build();
          log.add(m);
        }
        if (log.count() > 0) {
          out.println(log);
        }
        log.clear();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", true, "Send error messages to log file");
    return opts;
  }

}
