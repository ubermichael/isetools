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
public class NumberAttributeValidatorTest extends ValidatorTestBase {
  
  public NumberAttributeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super();    
  }

  /**
   * Test of validate method, of class NumberAttributeValidator.
   */
  @Test
  public void testValidate() {
    TagNode n = new TagNodeImpl();
    Attribute attr;
    NumberAttributeValidator validator = new NumberAttributeValidator();
    
    n.setName("ONEATTR");
    attr = schema.getTag(n.getName()).getAttribute("n");

    n.setAttribute("n", "5");
    validator.validate(n, attr);
    checkLog(new String[]{});
    
    n.setAttribute("n", "5.5");
    validator.validate(n, attr);
    checkLog(new String[]{});

    n.setAttribute("n", "+5.5");
    validator.validate(n, attr);
    checkLog(new String[]{});

    n.setAttribute("n", "-5.533");
    validator.validate(n, attr);
    checkLog(new String[]{});
    
    n.setAttribute("n", "-.533");
    validator.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badnumber"});
    
    n.setAttribute("n", "Where's my elephant?");
    validator.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badnumber"});
  }
  
}
