/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.schema.Schema;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
abstract public class ValidatorTestBase extends TestBase {

  protected final Schema schema;

  public ValidatorTestBase() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    File f = new File("/resources/data/test-schema.xml");
    schema = new Schema(f);
  }

}
