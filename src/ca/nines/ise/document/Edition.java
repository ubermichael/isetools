/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.DOM;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class Edition extends Document implements Comparable<Edition> {

  private final String editionCode;
  private final File file;

  private final String parentDir;
  private final String playCode;

  public Edition(File file) throws IOException {
    this.file = file;
    String filename = file.getName();
    if (validName(filename)) {
      parentDir = file.getParentFile().getCanonicalPath();
      playCode = extractName(filename);
      editionCode = extractEdition(filename);
    } else {
      parentDir = "";
      playCode = "";
      editionCode = "";
    }
  }

  @Override
  public int compareTo(Edition o) {
    return editionCode.toLowerCase().compareTo(o.editionCode.toLowerCase());
  }
  
  public File expectedAnnotationsFile() {
    return new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_annotation.xml");
  }
  
  public File expectedCollationsFile() {
    return new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_collation.xml");
  }

  public Annotations getAnnotations() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    return new Annotations(expectedAnnotationsFile());
  }

  public Collations getCollations() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    return new Collations(expectedCollationsFile());
  }

  public DOM getDOM() throws IOException {
    return new DOMBuilder(file).build();
  }

  public boolean hasAnnotations() {
    return expectedAnnotationsFile().exists();
  }

  public boolean hasCollations() {
    return expectedCollationsFile().exists();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);
    formatter.format("%4s %12s %12s%n", editionCode, hasAnnotations() ? "annotations" : "", hasCollations() ? "collations" : "");
    return sb.toString();
  }
}
