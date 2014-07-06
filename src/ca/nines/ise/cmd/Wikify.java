/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import ca.nines.ise.schema.Attribute;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Iterator;
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
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Wikify extends Command {

  @Override
  public String description() {
    return "Create a wikified version of the schema.";
  }

  @Override
  public void execute(CommandLine cmd) {
    try {
      Locale.setDefault(Locale.ENGLISH);
      PrintStream out = new PrintStream(System.out, true, "UTF-8");

      if (cmd.hasOption("l")) {
        out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
      }

      if(cmd.hasOption("c")) {
        wikifyCharacters(out);
      }
      
      if(cmd.hasOption("s")) {
        wikifySchema(out);
      }
      
      System.out.println("not implemented.");

    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Wikify.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(Wikify.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SAXException ex) {
      Logger.getLogger(Wikify.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Wikify.class.getName()).log(Level.SEVERE, null, ex);
    } catch (XPathExpressionException ex) {
      Logger.getLogger(Wikify.class.getName()).log(Level.SEVERE, null, ex);
    } 
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("o", true, "Send output to file");
    opts.addOption("c", false, "Generate output for special characters");
    opts.addOption("s", false, "Generate output for default schema");
    return opts;
  }

  @Override
  public String getUsage() {
    return "[options]";
  }

  private void wikifyCharacters(PrintStream out) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  private void wikifySchema(PrintStream out) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      Schema schema = new Schema();

      for (Tag tag : schema.getTags()) {
        wikifyTagInfo(out, tag);
        wikifySchemaAttrInfo(out, tag);
      }
  }

  public static void wikifySchemaAttrInfo(PrintStream out, Tag tag) {
    if (tag.countAttributes() == 0) {
      return;
    }
    Formatter fmt = new Formatter(out);
    fmt.format("|| Name || Type || Optional || Empty || Renumberable || Depreciated || Options ||%n");
    for (Attribute attr : tag.getAttributes()) {
      fmt.format("|| ''%s'' || %s || %s || %s || %s || %s || %s ||%n",
                 attr.getName(),
                 attr.getType(),
                 attr.isOptional(),
                 attr.isEmpty(),
                 attr.isRenumberable(),
                 attr.getDepreciated(),
                 wikifySchemaAttrOptions(out, attr)
      );
      fmt.format("{{{#!td%n}}}%n{{{#!td colspan=7%n%s%n}}}%n|-------------------%n", attr.getDescription());
    }
    out.println();
  }

  public static String wikifySchemaAttrOptions(PrintStream out, Attribute attr) {
    StringBuilder sb = new StringBuilder();
    String[] options = attr.getOptions();
    if (options.length == 0) {
      return "";
    }
    Iterator<String> i = Arrays.asList(options).iterator();
    while (i.hasNext()) {
      String s = i.next();
      sb.append(s);
      if (i.hasNext()) {
        sb.append(", ");
      }
    }
    return sb.toString();
  }

  public static void wikifyTagInfo(PrintStream out, Tag tag) {
    System.out.println("== " + tag.getName() + "==\n");
    System.out.println(tag.getDescription() + "\n");
    System.out.println(" Empty::");
    System.out.println("  " + tag.getEmpty());
    System.out.println(" Context::");
    System.out.println("  " + tag.getWhere());
    System.out.println(" Depreciated::");
    System.out.println("  " + (tag.isDepreciated() ? tag.getDepreciated() : "no"));
    System.out.println("\n");
  }

}
