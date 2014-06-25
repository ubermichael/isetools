/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.validator.node;

import ca.nines.ise.node.StartNode;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class StartNodeValidatorTest extends ValidatorTestBase {
  
  public StartNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super();
  }

  /**
   * Test of validate method, of class StartNodeValidator.
   */
  @Test
  public void testValidate() throws Exception {
    StartNode n = new StartNode();
    StartNodeValidator validator = new StartNodeValidator(schema);

    n.setName("EMPTY");
    validator.validate(n);
    checkLog(new String[]{"validator.tag.startempty"});
    
    n.setName("OPT");
    validator.validate(n);
    checkLog(new String[]{});
    
    n.setName("NOATTR");
    validator.validate(n);
    checkLog(new String[]{});
    
    n.setName("FOO");
    validator.validate(n);
    checkLog(new String[]{"validator.tag.unknown"});
    
    n.setName("DEPTAG");
    validator.validate(n);
    checkLog(new String[]{"validator.tag.depreciated"});
  }
  
}
