/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.node.AbbrNode;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class AbbrNodeValidatorTest extends ValidatorTestBase {

  public AbbrNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super();
  }

  /**
   * Test of validate method, of class AbbrNodeValidator.
   */
  @Test
  public void testValidate() {
    AbbrNodeValidator validator = new AbbrNodeValidator();
    AbbrNode n = new AbbrNode();
    n.setText("|a^b|");

    validator.validate(n, schema);
    checkLog(new String[]{"validator.abbr.depreciated"});

    n.setText("|supercalafraglistic|");
    validator.validate(n, schema);
    checkLog(new String[]{"validator.abbr.depreciated", "validator.abbr.long"});
  }

}
