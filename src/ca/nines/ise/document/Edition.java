/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.dom.Builder;
import ca.nines.ise.dom.DOM;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class Edition implements Comparable<Edition> {

  private final String parentDir;
  private final File file;
  private final String playCode;
  private final String editionCode;

  public Edition(File file) throws IOException {
    this.file = file;
    parentDir = file.getParentFile().getCanonicalPath();
    Pattern p = Pattern.compile("(\\p{Alnum}+)_(\\p{Alnum}+)\\.txt");
    Matcher m = p.matcher(file.getName());
    if (m.matches()) {
      playCode = m.group(1);
      editionCode = m.group(2);
    } else {
      playCode = "";
      editionCode = "";
    }
  }

  @Override
  public int compareTo(Edition o) {
    return editionCode.toLowerCase().compareTo(o.editionCode.toLowerCase());
  }

  public boolean hasCollations() throws IOException {
    File annotationsFile = new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_collation.xml");
    return annotationsFile.exists();
  }

  public Collations getCollations() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    File collationsFile = new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_collation.xml");
    return new Collations(collationsFile);
  }

  public boolean hasAnnotations() {
    File annotationsFile = new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_annotation.xml");
    return annotationsFile.exists();
  }

  public Annotations getAnnotations() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    File collationsFile = new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_annotation.xml");
    return new Annotations(collationsFile);
  }

  public DOM getDOM() throws IOException {
    return new Builder(file).getDOM();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);
    try {
      formatter.format("%4s %12s %12s%n", editionCode, hasAnnotations() ? "annotations" : "", hasCollations() ? "collations" : "");
    } catch (IOException ex) {
      Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
    }
    return sb.toString();
  }
}
