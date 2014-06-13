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
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class SW {

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

  public static void attrInfo(Tag tag) {
    if (tag.countAttributes() == 0) {
      return;
    }
    StringBuilder sb = new StringBuilder();
    Formatter fmt = new Formatter(sb);
    System.out.println("|| Name || Type || Optional || Empty || Match || Renumberable || Depreciated || Options ||");
    for (String attrName : tag.getAttributeNames()) {
      Attribute attr = tag.getAttribute(attrName);
      fmt.format("|| ''%s'' || %s || %s || %s || %s || %s || %s || %s ||%n", 
              attr.getName(), 
              attr.getType(), 
              attr.getOptional() == null ? "no" : "yes",
              attr.getEmpty() == null ? "no" : "yes", 
              attr.getMatch() == null ? " " : attr.getMatch(),  
              attr.getRenumber() == null ? "no" : "yes",
              attr.getDepreciated() == null ? " " : attr.getDepreciated(),
              attrOptions(attr)       
      );
      fmt.format("{{{#!td%n}}}%n{{{#!td colspan=7%n%s%n}}}%n|-------------------%n", attr.getDescription());
    }
    System.out.println(sb);
  }

  public static void tagInfo(Tag tag) {
    System.out.println("== " + tag.getName() + "==\n");
    System.out.println(tag.getDescription() + "\n");
    System.out.println(" Empty::");
    System.out.println("  " + tag.getEmpty());
    System.out.println(" Context::");
    System.out.println("  " + (tag.getWhere() == "" ? "anywhere" : tag.getWhere()));
    System.out.println(" Depreciated::");
    System.out.println("  " + (tag.getDepreciated() == "" ? "no" : tag.getDepreciated()));
    System.out.println("\n");
  }

  public static void main(String[] args) {
    try {
      Schema schema = new Schema();
      StringBuilder sb = new StringBuilder();
      Formatter fmt = new Formatter(sb);

      for (String tagName : schema.getTagNames()) {
        Tag tag = schema.getTag(tagName);
        tagInfo(tag);
        attrInfo(tag);
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
