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

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.validator.AnnotationValidator;
import ca.nines.ise.validator.DOMValidator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Validate an ISE annotations xml file against an ISE SGMLish document,
 * checking that the lemmas match up.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Annotations extends Command {

  /**
   * {@inheritDoc}
   */
  @Override
  public String description() {
    return "Validate annotations for an ISE document.";
  }

  /**
   * {@inheritDoc}
   */
  @ErrorCode(code = {
    "dom.errors"
  })
  @Override
  public void execute(CommandLine cmd) throws Exception {
    Log log = Log.getInstance();
    Locale.setDefault(Locale.ENGLISH);
    PrintStream logOut = new PrintStream(System.out, true, "UTF-8");

    if (cmd.hasOption("l")) {
      logOut = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
    }

    String args[] = getArgList(cmd);

    File docFile = new File(args[0]);
    if (!docFile.exists()) {
      System.err.println("Cannot read document " + docFile.getCanonicalPath());
      System.exit(1);
    }

    File annFile = new File(args[1]);
    if (!annFile.exists()) {
      System.err.println("Cannot read annotations " + annFile.getCanonicalPath());
      System.exit(1);
    }

    DOM dom = new DOMBuilder(docFile).build();
    if (dom.getStatus() != DOM.DOMStatus.ERROR) {
      Annotation annotation = Annotation.builder().from(annFile).build();
      AnnotationValidator av = new AnnotationValidator();
      av.validate(dom, annotation);
    } else {
      Message m = Message.builder("dom.errors")
              .setSource(dom.getSource())
              .build();
      log.add(m);
    }
    if (log.count() > 0) {
      logOut.print(log);
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
