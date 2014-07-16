/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.schema.Schema;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
abstract public class ValidatorTestBase extends TestBase {

  protected final Schema schema;

  public ValidatorTestBase() throws SAXException, IOException, XPathExpressionException, ParserConfigurationException, TransformerException {
    String loc = "/resources/data/test-schema.xml";
    InputStream stream = ValidatorTestBase.class.getResourceAsStream(loc);
    schema = Schema.builder().from(loc, stream).build();
  }

}
