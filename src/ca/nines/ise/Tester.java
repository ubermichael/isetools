/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.document.Collations;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.log.Log;
import ca.nines.ise.output.Output;
import ca.nines.ise.output.RTFOutput;
import ca.nines.ise.schema.Attribute;
import ca.nines.ise.util.XMLDriver;

import com.lowagie.text.*;
import com.lowagie.text.rtf.*;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      String data = ""
              + "<attribute name=\"foo\" type=\"number\" optional=\"yes\">\n"
              + "  <desc>optional attribute</desc>\n"
              + "</attribute>";

      org.w3c.dom.Document doc = new XMLDriver().drive(data);
      Attribute a = Attribute.builder().from(doc.getElementsByTagName("attribute").item(0)).build();
      System.out.println("optional " + a.isOptional());
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    } finally {
      System.out.println(log);
    }
  }
}
