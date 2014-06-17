/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import ca.nines.ise.util.XMLReader;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;

/**
 *
 * @author michael
 */
public class Tester {
  public static void main(String[] args) {
    try {
      File file = new File("/resources/schemas/default.xml");
      XMLReader xmlIn = new XMLReader(file);
      Node[] nl = xmlIn.xpathList("//tag");
      System.out.println("found " + nl.length);
      for(Node n : nl) {
        System.out.println(n.getNodeName());
      }
    } catch (Exception ex) {
      Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
