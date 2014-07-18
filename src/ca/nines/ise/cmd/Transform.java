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

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.log.Log;
import ca.nines.ise.output.Output;
import ca.nines.ise.output.RTFOutput;
import ca.nines.ise.output.TextOutput;
import ca.nines.ise.output.XMLOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 *
 * @author michael
 */
public class Transform extends Command {

  @Override
  public String description() {
    return "Transform an ISE SGML document another format.";
  }

  @Override
  public void execute(CommandLine cmd) {
    PrintStream out;
    Output renderer = null;
    try {
      Log log = Log.getInstance();
      Locale.setDefault(Locale.ENGLISH);
      
      out = new PrintStream(System.out, true, "UTF-8");
      if (cmd.hasOption("o")) {
        out = new PrintStream(new FileOutputStream(cmd.getOptionValue("o")), true, "UTF-8");
      }

      String[] files = getArgList(cmd);
      if (files.length > 1) {
        System.err.println("Can only transform one file at a time.");
        help();
        System.exit(1);
      }

      if (files.length < 1) {
        System.err.println("Must include a file path to transform.");
        help();
        System.exit(2);
      }

      if (cmd.hasOption("text")) {
        renderer = new TextOutput(out);
      }
      if (cmd.hasOption("xml")) {
        renderer = new XMLOutput(out);
      }
      if(cmd.hasOption("rtf")) {
        renderer = new RTFOutput(out);
      }

      if (renderer != null) {

        DOM dom = new DOMBuilder(new File(files[0])).build();
        renderer.render(dom);

      }
    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(Transform.class.getName()).log(Level.SEVERE, null, ex);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Transform.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Transform.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(Transform.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(Transform.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("o", true, "Send output to file.");
    opts.addOption("xml", false, "Transform output to XML.");
    opts.addOption("text", false, "Transform output to UTF-8 (unicode) text.");
    opts.addOption("rtf", false, "Transform output to an RTF document.");
    return opts;
  }

  @Override
  public String getUsage() {
    return "[options] file";
  }

}
