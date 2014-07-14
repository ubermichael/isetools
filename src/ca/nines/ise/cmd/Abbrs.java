/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.Node;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.validator.DOMValidator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Abbrs extends Command {

  @Override
  public String description() {
    return "Report depreciated abbreviations used in one or more ISE SGMLdocuments.";
  }

  @Override
  public void execute(CommandLine cmd) {
    try {
      File[] files;

      Locale.setDefault(Locale.ENGLISH);
      PrintStream out = new PrintStream(System.out, true, "UTF-8");

      if (cmd.hasOption("l")) {
        out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
      }

      files = getFilePaths(cmd);
      Formatter formatter = new Formatter(out);

      if (files != null) {
        out.println("Found " + files.length + " files to check.");
        for (File in : files) {
          DOM dom = new DOMBuilder(in).build();
          dom.index();
          Iterator<Node> iterator = dom.iterator();
          while (iterator.hasNext()) {
            Node n = iterator.next();
            if (n.type() == Node.NodeType.ABBR) {
              formatter.format("%s:%d:%d%n", n.getSource(), n.getLine(), n.getColumn());
              formatter.format("  near TLN %s%n", n.getTLN());
              formatter.format("  %s%n", n.getText().substring(0, Math.min(64, n.getText().length())));
              formatter.format("  %s%n", dom.getLine(n.getLine() - 1));
              formatter.format("%n");
            }
          }
        }
      }
    } catch (Exception ex) {
      Logger.getLogger(Abbrs.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", true, "Send output to log file");
    return opts;
  }

}
