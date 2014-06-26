/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node.attribute;

import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;
import ca.nines.ise.validator.node.TagNodeImpl;
import ca.nines.ise.validator.node.ValidatorTestBase;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class StringAttributeValidatorTest extends ValidatorTestBase {

  public StringAttributeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super();
  }

  /**
   * Test of validate method, of class StringAttributeValidator.
   */
  @Test
  public void testValidate() {
    TagNode n = new TagNodeImpl();
    Attribute attr;
    StringAttributeValidator validator = new StringAttributeValidator();
    n.setName("TWOATTRS");
    
    n.setAttribute("n", "foo");
    attr = schema.getTag(n.getName()).getAttribute("n");

    validator.validate(n, attr);
    checkLog(new String[]{});

    n.setAttribute("n", "");
    validator.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badstring"});

    n.setAttribute("n", "     ");
    validator.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badstring"});
  }

}
