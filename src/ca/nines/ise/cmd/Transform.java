/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.log.Log;
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
    return "Transform an ISE SGML document to XML.";
  }

  @Override
  public void execute(CommandLine cmd) {
    PrintStream out = null;
    try {
      Log log = Log.getInstance();
      Locale.setDefault(Locale.ENGLISH);
      out = new PrintStream(System.out, true, "UTF-8");
      if (cmd.hasOption("l")) {
        out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
      }
      
      String[] files = getArgList(cmd);
      if(files.length > 1) {
        System.err.println("Can only transform one file at a time.");
        help();
        System.exit(1);
      }
      
      if(files.length < 1) {
        System.err.println("Must include a file path to transform.");
        help();
        System.exit(2);
      }
      
      DOM dom = new DOMBuilder(new File(files[0])).build();
      XMLOutput xmlOut = new XMLOutput(out);
      xmlOut.render(dom);
      
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
    opts.addOption("l", true, "Send error messages to log file");
    return opts;
  }

  @Override
  public String getUsage() {
    return "[options] file";
  }

}
