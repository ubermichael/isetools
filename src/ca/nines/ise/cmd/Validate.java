/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import ca.nines.ise.dom.Builder;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class Validate extends Command {

  @Override
  public String description() {
    return "Validate an ISE SGMLish file";
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("l", true, "Send error messages to log file");
    return opts;
  }

  @Override
  public void execute(CommandLine cmd) {
    try {
      Collection<File> fileList = new ArrayList<>();

      Log log = Log.getInstance();
      Locale.setDefault(Locale.ENGLISH);
      Schema schema = new Schema();
      DOMValidator validator = new DOMValidator(schema);
      PrintStream out = new PrintStream(System.out, true, "UTF-8");

      if (cmd.hasOption("l")) {
        out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
      }

      List<String> argList = cmd.getArgList();
      argList = argList.subList(1, argList.size());
      
      if (argList.isEmpty()) {
        File dir = new File("data/sgml");
        SuffixFileFilter sfx = new SuffixFileFilter(".txt");
        fileList = FileUtils.listFiles(dir, sfx, TrueFileFilter.INSTANCE);
      } else {
        for (String name : argList) {
          fileList.add(new File(name));
        }
      }
      if (fileList != null) {
        out.println("Found " + fileList.size() + " files to check.");
        for (File in : fileList) {
          DOM dom = new Builder(in).getDOM();
          validator.validate(dom);
          out.println(log);
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
}
