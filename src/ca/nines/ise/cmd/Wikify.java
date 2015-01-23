/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
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

import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.chr.AccentCharNode;
import ca.nines.ise.writer.Writer;
import ca.nines.ise.writer.XMLWriter;
import ca.nines.ise.schema.Attribute;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;
import ca.nines.ise.util.CodePoint;
import ca.nines.ise.util.CodePointTable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.xml.sax.SAXException;

/**
 * Create trac-friendly wiki pages describing some aspect of the toolset, based
 * on the code and annotations.
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Wikify extends Command {

  /**
   * {@inheritDoc}
   */
  @Override
  public String description() {
    return "Create a wikified version of the schema.";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(CommandLine cmd) throws Exception {
    Locale.setDefault(Locale.ENGLISH);
    PrintStream out = new PrintStream(System.out, true, "UTF-8");

    if (cmd.hasOption("o")) {
      out = new PrintStream(new FileOutputStream(cmd.getOptionValue("l")), true, "UTF-8");
    }

    if (cmd.hasOption("chars")) {
      wikifyCharacters(out);
    }

    if (cmd.hasOption("schema")) {
      wikifySchema(out);
    }
	
	if(cmd.hasOption("codepoints")) {
	  wikifyCodepoints(out);
	}
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Options getOptions() {
    Options opts = new Options();
    opts.addOption("o", true, "Send output to file");
    opts.addOption("chars", false, "Generate output for special characters");
    opts.addOption("schema", false, "Generate output for default schema");
	opts.addOption("codepoints", false, "Generate output for named codepoints");
    return opts;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUsage() {
    return "[options]";
  }

  private void wikifyCharacters(PrintStream out) {
    // accented
    try {
      Map<String, String> cm = AccentCharNode.mapping();
      Writer xmlOut = new XMLWriter(out);
      CharNode cn = new AccentCharNode();
      Formatter formatter = new Formatter(out);

      for (String c : cm.keySet()) {
        cn.setText("{" + c + "a}");
        formatter.format("%s %s %s %s%n",
                         c,
                         Character.getName(cm.get(c).charAt(0)),
                         cn.getText(),
                         cn.unicode()
        );
        xmlOut.render(cn.expanded());
        out.println();
      }

    } catch (Exception ex) {
      Logger.getLogger(Wikify.class.getName()).log(Level.SEVERE, null, ex);
    }

    // code point
    // digraph
    // ligature
    // space
    // typographic
    // unicode
    // nested
  }

  /**
   * Produce a wikified version of the schema.
   * 
   * @param out destination
   * 
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   * @throws XPathExpressionException
   * @throws TransformerException 
   */
  private void wikifySchema(PrintStream out) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
    Schema schema = Schema.defaultSchema();

    for (Tag tag : schema.getTags()) {
      wikifyTagInfo(out, tag);
      wikifySchemaAttrInfo(out, tag);
    }
  }

  /**
   * Wikify a tag.
   * 
   * @param out
   * @param tag 
   */
  private static void wikifySchemaAttrInfo(PrintStream out, Tag tag) {
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
                 wikifySchemaAttrOptions(attr)
      );
      fmt.format("{{{#!td%n}}}%n{{{#!td colspan=7%n%s%n}}}%n|-------------------%n", attr.getDescription());
    }
    out.println();
  }

  /**
   * Wikify a schema attribute's options.
   * 
   * @param out
   * @param attr
   * @return String
   */
  private static String wikifySchemaAttrOptions(Attribute attr) {
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

  /**
   * Wikify a tag and its attributes.
   * 
   * @param out
   * @param tag 
   */
  private static void wikifyTagInfo(PrintStream out, Tag tag) {
    System.out.println("== " + tag.getName() + "==\n");
    System.out.println(tag.getDescription() + "\n");
    System.out.println(" Empty::");
    System.out.println("  " + tag.getEmpty());
    System.out.println(" Depreciated::");
    System.out.println("  " + (tag.isDepreciated() ? tag.getDepreciated() : "no"));
    System.out.println("\n");
  }

  /**
   * Turn the list of entity names into a wiki table.
   * 
   * @param out
   * @throws IOException 
   */
  private void wikifyCodepoints(PrintStream out) throws IOException {
	CodePointTable tbl = CodePointTable.defaultCodePointTable();
	Formatter fmt = new Formatter(out);
	fmt.format("|| Name || Char || Decimal || Hex || Code Point || Description ||\n");
	for(String name : tbl.getCodePoints()) {
		CodePoint cp = tbl.getCodePoint(name);
		fmt.format("|| %s || %s || %s || %s || %s || %s ||\n", 
				cp.getName(), cp.getValue(), cp.dec(), cp.hex(), 
				cp.unicodePoint(), cp.description().toLowerCase());
	  }
	out.println();
  }

}
