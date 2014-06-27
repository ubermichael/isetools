/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.node.TextNode;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class TextNodeValidatorTest extends ValidatorTestBase {

  public TextNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super();
  }

  /**
   * Test of validate method, of class TextNodeValidator.
   */
  @Test
  public void testValidate() {
    TextNodeValidator validator = new TextNodeValidator(schema);
    TextNode n = new TextNode();
    validator.validate(n);
    checkLog(new String[]{});

    n.setText("foo bar yes please.");
    validator.validate(n);
    checkLog(new String[]{});

    n.setText("foo \uFFFD yes please.");
    validator.validate(n);
    checkLog(new String[]{"validator.text.badunicode"});

    n.setText("foo # yes please.");
    validator.validate(n);
    checkLog(new String[]{"validator.text.depreciatedhash"});
  }

}
