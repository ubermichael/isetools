/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
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

package ca.nines.ise.document;

import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.DOM;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class Edition extends Document implements Comparable<Edition> {

  /**
   * Edition code: M or F1 or Q1 or FM or Q1M or EM.
   */
  private final String editionCode;
  
  /**
   * Full path to the file on the file system.
   */
  private final File file;

  /**
   * Parent directory.
   */
  private final String parentDir;
  
  /**
   * Play code (Rom, Ham, 1H4).
   */
  private final String playCode;

  /**
   * Contruct an edition from a file.
   * 
   * @param file full path to the file.
   * @throws IOException 
   */
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

  /**
   * Compare edition codes case-insensitively.
   * 
   * @param o
   * @return int
   */
  @Override
  public int compareTo(Edition o) {
    return editionCode.toLowerCase().compareTo(o.editionCode.toLowerCase());
  }
  
  /**
   * Return the path where annotations are expected to be found, if they exist.
   * 
   * @return String
   */
  public File expectedAnnotationFile() {
    return new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_annotation.xml");
  }

  /**
   * Return the path where collations are expected to be found.
   * 
   * @return String
   */
  public File expectedCollationFile() {
    return new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_collation.xml");
  }

  /**
   * Build an Annotations collection for the edition.
   * 
   * @return Annotation
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   * @throws XPathExpressionException
   * @throws TransformerException 
   */
  public Annotation getAnnotation() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
    return Annotation.builder().from(expectedAnnotationFile()).build();
  }

  /**
   * Get the Collations collection for the edition.
   * 
   * @return Collation
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   * @throws XPathExpressionException 
   */
  public Collation getCollation() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * Build and return a DOM for the edition.
   * 
   * @return DOM
   * @throws IOException 
   */
  public DOM getDOM() throws IOException {
    return new DOMBuilder(file).build();
  }

  /**
   * Check if an edition has annotations. Only checks if the annotations file
   * exists, don't check its content.
   * 
   * @return boolean
   */
  public boolean hasAnnotations() {
    return expectedAnnotationFile().exists();
  }

  /**
   * Check if the edition has collations. Only checks if the collations file
   * exists, doesn't check its contents.
   * 
   * @return boolean
   */
  public boolean hasCollations() {
    return expectedCollationFile().exists();
  }

  /**
   * Return a human readable string describing the edition.
   * 
   * @return String
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%4s %12s %12s%n", editionCode, hasAnnotations() ? "annotations" : "", hasCollations() ? "collations" : "");
    return formatter.toString();
  }
}
