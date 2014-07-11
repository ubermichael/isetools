/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.node.EmptyNode;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class EmptyNodeValidatorTest extends ValidatorTestBase {

  public EmptyNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super();
  }

  /**
   * Test of validate method, of class EmptyNodeValidator.
   */
  @Test
  public void testValidate() throws Exception {
    EmptyNode n = new EmptyNode();
    EmptyNodeValidator validator = new EmptyNodeValidator();

    n.setName("EMPTY");
    validator.validate(n, schema);
    checkLog(new String[]{});

    n.setName("OPT");
    validator.validate(n, schema);
    checkLog(new String[]{});

    n.setName("NOATTR");
    validator.validate(n, schema);
    checkLog(new String[]{"validator.tag.emptystart"});

    n.setName("FOO");
    validator.validate(n, schema);
    checkLog(new String[]{"validator.tag.unknown"});

    n.setName("DEPTAG");
    validator.validate(n, schema);
    checkLog(new String[]{"validator.tag.depreciated"});

  }

}
