/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.schema.Attribute;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class SW {

  public static void row(StringBuilder sb) {
    sb.append("|------------\n");
  }

  public static void tagRow(Formatter fmt, Tag tag) {
    fmt.format("|| %s || %s || %s || %s ||%n",
            tag.getName(),
            tag.getEmpty(),
            tag.getWhere(),
            tag.getDepreciated());
  }

  public static void attrHeader(StringBuilder sb) {
    sb.append("{{{#!td\n"
            + "}}}\n"
            + "{{{#!td colspan=3\n"
            + "|| Name || Type || Optional || Emtpy || Match || Renmberable || Options ||\n"
    );
  }

  public static String attrOptions(Attribute attr) {
    StringBuilder sb = new StringBuilder();
    String[] options = attr.getOptions();
    if (options.length == 0) {
      return "";
    }
    Iterator i = Arrays.asList(options).iterator();
    while (i.hasNext()) {
      String s = (String) i.next();
      sb.append(s);
      if(i.hasNext()) {
        sb.append(", ");
      }
    }
    return sb.toString();
  }

  public static void attrRow(Formatter fmt, Attribute attr) {
    fmt.format("|| @''%s'' || %s || %s || %s || %s || %s || %s ||%n",
            attr.getName(),
            attr.getType(),
            attr.getOptional(),
            attr.getEmpty(),
            attr.getMatch(),
            attr.getRenumber(),
            attrOptions(attr));
  }

  public static void main(String[] args) {
    try {
      Schema schema = new Schema();
      StringBuilder sb = new StringBuilder();
      Formatter fmt = new Formatter(sb);

      sb.append("||= Name =||= Empty =||= Context =||= Depreciated =||\n");
      row(sb);

      for (String tagName : schema.getTagNames()) {
        Tag tag = schema.getTag(tagName);
        tagRow(fmt, tag);
        row(sb);

        if (tag.countAttributes() > 0) {
          attrHeader(sb);
          row(sb);
          for (String attrName : tag.getAttributeNames()) {
            Attribute attr = tag.getAttribute(attrName);
            attrRow(fmt, attr);
          }
          sb.append("}}}\n");
          row(sb);
        }
      }
      System.out.println(sb);
    } catch (Exception ex) {
      Logger.getLogger(SW.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
}

/*

 ||= Name =||= Empty =||= Context =||= Depreciated =||
 |------
 || '''ABBR''' || no ||  ||  ||
 |------
 {{{#!th
 }}}
 {{{#!th colspan=3
 || Name || Type || Optional || Emtpy || Match || Renmberable || Options ||
 }}}
 |------
 {{{#!td
 }}}
 {{{#!td colspan=3
 || @''expan'' || string || null || null || null || null ||  ||
 || @''expan'' || string || null || null || null || null ||  ||
 }}}
 |------
 || '''ACT''' || no ||  ||  ||
 |------
 {{{#!th
 }}}
 {{{#!th colspan=3
 || Name || Type || Optional || Emtpy || Match || Renmberable || Options ||
 || @''expan'' || string || null || null || null || null ||  ||
 || @''expan'' || string || null || null || null || null ||  ||
 }}}

 */
