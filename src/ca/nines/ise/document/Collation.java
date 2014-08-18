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

import ca.nines.ise.node.lemma.Coll;
import ca.nines.ise.util.BuilderInterface;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Collation is a thin wrapper around a list of Coll objects.
 *
 * @author michael
 */
public class Collation extends Apparatus<Coll> {

  /**
   * CollationBuilder constructs a Collation in a builder pattern.
   */
  public static class CollationBuilder extends ApparatusBuilder<Coll> implements BuilderInterface<Collation> {

    /**
     * Use Collation.builder() to get a CollationBuilder object.
     */
    private CollationBuilder() {
    }
    
    /**
     * Once all the data is collected, build() will create the Collation.
     * 
     * @return Collation constructed from the data
     */
    @Override
    public Collation build() {
      return new Collation(source, lemmas);
    }

    /**
     * Parse the data from a node.
     * 
     * @TODO actually do something here.
     * 
     * @param in Node to parse
     * @return CollationBuilder to enable method chaining
     * @throws ParserConfigurationException
     * @throws XPathExpressionException 
     */
    public CollationBuilder from(Node in) throws ParserConfigurationException, XPathExpressionException {
      return this;
    }

    /**
     * Parse a string into an XML document and get the collations from it.
     * 
     * @TODO actually do something here.
     * 
     * @param in String to parse.
     * 
     * @return CollationBuilder to enable method chaining
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException 
     */
    public CollationBuilder from(String in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      return this;
    }

    /**
     * Parse a file for collations.
     * 
     * @param in File to parse.
     * 
     * @return CollationBuilder to enable method chaining
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException 
     */
    public CollationBuilder from(File in) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
      return this;
    }

  }

  /**
   * Create a new CollationBuilder object and return it.
   * 
   * @return CollationBUilder object
   */
  public static CollationBuilder builder() {
    return new CollationBuilder();
  }
  
  /**
   * Construct a collation.
   * 
   * @param source
   * @param lemmas 
   */
  private Collation(String source, List<Coll> lemmas) {
    super(source, lemmas);
  }

}
