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
 * Edition models a conceptual edition of a work, for example Folio 1, Quarto 1,
 * Modern. Editions are also called transcriptions.
 * 
 * Related editions are collected together in a work, sometimes referred to as
 * a parent.
 *
 * @author michael
 */
public class Edition extends Document implements Comparable<Edition> {

  /**
   * The short edition code, F1 = folio 1, etc.
   */
  private final String editionCode;
  
  /**
   * The file containing the edition. May be null.
   */
  private final File file;

  /**
   * The directory containing the edition. Probably the same as the parent work's
   * directory.
   */
  private final String parentDir;
  
  /**
   * The play code for the edition (Oth_F1, Ham_Q1, etc).
   */
  private final String playCode;

  /**
   * Construct an edition from a file.
   * 
   * @param file to construct the edition from
   * 
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
   * Compare editions lexicographically and case insensitively, based on the play code.
   * 
   * @param o the edition to compare to
   * @return 0 if the editions have the same play code, a value less than 0 if this
   * edition's play code is lexicographically less than the argument's play code;
   * and a value greater than 0 otherwise.
   */
  @Override
  public int compareTo(Edition o) {
    return editionCode.toLowerCase().compareTo(o.editionCode.toLowerCase());
  }
  
  /**
   * Fetch the expected annotations file, which may not exist.
   * 
   * @return a File representing the expected annotations location.
   */
  public File expectedAnnotationFile() {
    return new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_annotation.xml");
  }
  
  /**
   * Fetch the expected collations file, which may not exist.
   * 
   * @return a File representing the expected collations location.
   */
  public File expectedCollationFile() {
    return new File(parentDir + "/apparatus/" + playCode + "_" + editionCode + "_collation.xml");
  }

  /**
   * Get the Annotation object for the edition, if it exists, or throw
   * exceptions if the file doesn't exist. Use hasAnnotation() to check for
   * existence.
   * 
   * @return Annotation object
   * 
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
   * Get the Collation object for the edition if it exists or throw exceptions
   * if the file doesn't exist. Use hasCollations() to check for existence.
   * 
   * @return
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
   * @return DOM representing the edition
   * 
   * @throws IOException if the file cannot be read.
   */
  public DOM getDOM() throws IOException {
    return new DOMBuilder(file).build();
  }

  /**
   * Check if the annotations file exists
   * 
   * @return true if the edition is annotated
   */
  public boolean hasAnnotations() {
    return expectedAnnotationFile().exists();
  }

  /**
   * Check if the edition has been collated.
   * 
   * @return true if the edition has collations
   */
  public boolean hasCollations() {
    return expectedCollationFile().exists();
  }

  /**
   * Return a string representation of the edition. Really only useful for
   * debugging.
   * 
   * @return 
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%4s %12s %12s%n", editionCode, hasAnnotations() ? "annotations" : "", hasCollations() ? "collations" : "");
    return formatter.toString();
  }
}
