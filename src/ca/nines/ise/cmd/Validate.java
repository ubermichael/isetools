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
import ca.nines.ise.schema.Schema;
import ca.nines.ise.validator.DOMValidator;
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
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class Validate extends Command {

  @Override
  public String description() {
    return "Validate one or more ISE SGML documents.";
  }

  @Override
  public void execute(CommandLine cmd) {
    try {
      File[] files;

      Log log = Log.getInstance();
      Locale.setDefault(Locale.ENGLISH);
      Schema schema = Schema.defaultSchema();
      DOMValidator validator = new DOMValidator();
      PrintStream out = new PrintStream(System.out, true, "UTF-8");

      if (cmd.hasOption("l")) {
        out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
      }

      files = getFilePaths(cmd);
      if (files != null) {
        out.println("Found " + files.length + " files to check.");
        for (File in : files) {
          DOM dom = new DOMBuilder(in).build();
          validator.validate(dom, schema);
          if (log.count() > 0) {
            out.println(log);
            log.clear();
          }
          log.clear();
        }
      }

    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SAXException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (XPathExpressionException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", true, "Send error messages to log file");
    return opts;
  }

}
