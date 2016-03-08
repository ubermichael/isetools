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
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.transformer.DeprecatedTransformer;
import ca.nines.ise.validator.DOMValidator;
import ca.nines.ise.validator.NestingValidator;
import ca.nines.ise.writer.Writer;
import ca.nines.ise.writer.RTFWriter;
import ca.nines.ise.writer.SGMLWriter;
import ca.nines.ise.writer.TextWriter;
import ca.nines.ise.writer.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Transform a document into a new document.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
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
    Locale.setDefault(Locale.ENGLISH);
    out = new PrintStream(System.out, true, "UTF-8");
    if (cmd.hasOption("o")) {
      out = new PrintStream(new FileOutputStream(cmd.getOptionValue("o")), true, "UTF-8");
    }

    Log log = Log.getInstance();
    Writer renderer = null;

    if (cmd.hasOption("text")) {
      renderer = new TextWriter(out);
    }
    if (cmd.hasOption("xml")) {
      renderer = new XMLWriter(out);
    }
    if (cmd.hasOption("rtf")) {
      renderer = new RTFWriter(out);
    }
    if(cmd.hasOption("sgml")) {
        renderer = new SGMLWriter(out);
    }

    if (renderer == null) {
      System.err.println("You must specify a transformation");
      System.exit(1);
      return;
    }

    String[] files = getArgList(cmd);
    DOM dom = new DOMBuilder(new File(files[0])).build();
    
    Schema schema = Schema.defaultSchema();    
    DOMValidator dv = new DOMValidator();
    NestingValidator nv = new NestingValidator(schema);
    dv.validate(dom, schema);
    nv.validate(dom);    
    
    if (dom.getStatus() != DOMStatus.ERROR) {
      DeprecatedTransformer dt = new DeprecatedTransformer();
      dom = dt.transform(dom);
      if (files.length == 2) {
        Annotation ann = Annotation.builder().from(new File(files[1])).build();
        renderer.render(dom, ann);
      } else {
        renderer.render(dom);
      }
    } else {
      Message m = Message.builder("dom.errors")
              .setSource(dom.getSource())
              .build();
      log.add(m);
    }
    if (log.count() > 0) {
      System.err.println(log.count() + " errors or warnings.");
      System.err.println(log);
    }
    log.clear();
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
    opts.addOption("sgml", false, "Transform output to an SGML document, after validating.");
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
