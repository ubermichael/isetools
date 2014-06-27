/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.CharNode.CharType;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.chr.NestedCharNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.validator.DOMValidator;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Chars {

  public static void main(String[] args) throws IOException, Exception {
    Collection<File> fileList = null;
    Log log = Log.getInstance();
    Locale.setDefault(Locale.ENGLISH);
    PrintStream out = null;

    try {
      out = new PrintStream(System.out, true, "UTF-8");
      Schema schema = new Schema();
      DOMValidator tv = new DOMValidator(schema);

      if (args.length == 0) {
        File dir = new File("data/sgml");
        SuffixFileFilter sfx = new SuffixFileFilter(".txt");
        fileList = FileUtils.listFiles(dir, sfx, TrueFileFilter.INSTANCE);
      } else {
        for (String name : args) {
          fileList = new ArrayList<>();
          fileList.add(new File(name));
        }
      }

      out.println("Found " + fileList.size() + " files to check.");

      HashMap<String, Integer> m = new HashMap<>();
      HashMap<CharNode.CharType, HashMap<String, Integer>> types = new HashMap<>();
      
      for(CharType type : CharNode.CharType.values()) {
        types.put(type, new HashMap<String, Integer>());
      }
      
      Iterator fi = fileList.iterator();
      while (fi.hasNext()) {
        File in = (File) fi.next();
        DOM dom = new Builder(in).getDOM();
        Iterator<Node> i = dom.iterator();
        while (i.hasNext()) {
          Node n = i.next();
          if (n instanceof CharNode) {
            CharNode c = (CharNode)n;            
            m.put(n.getText(), 1);
            
            types.get(c.getCharType()).put(c.getText(), 1);
            
          }
        }
      }
      
      System.out.println("EXISTING CHARS BY TYPE:");
      for(CharType type : CharNode.CharType.values()) {
        System.out.println(type.name());
        for(String str : types.get(type).keySet()) {
          System.out.println(str);
        }
      }
      
      System.out.println("EXISTING CHARS: ");
      for(String k : m.keySet()) {
        System.out.println(k);
      }
      
      System.out.println("LOG: ");
      System.out.println(log);
      
    } catch (IOException ex) {
      Logger.getLogger(Abbrs.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(Abbrs.class.getName()).log(Level.SEVERE, null, ex);
    } catch (XPathExpressionException ex) {
      Logger.getLogger(Abbrs.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SAXException ex) {
      Logger.getLogger(Abbrs.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
