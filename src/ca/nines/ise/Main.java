/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.validator.TagValidator;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Main {

  public static void main(String[] args) {
    try {
      String filepath = "data/sgml/withTitlePage/Oth/Oth_F1.txt";

      if(args.length == 1) {
        filepath = args[0];
      }
      File in = new File(filepath);
      DOM dom = new Builder(in).getDOM();
      TagValidator tv = new TagValidator();
      Log log = Log.getInstance();
      tv.validate(dom);
      System.out.println(log);
    } catch (Exception ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
