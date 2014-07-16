/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.node.EndNode;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class EndNodeValidatorTest extends ValidatorTestBase {

  public EndNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
    super();
  }

  /**
   * Test of validate method, of class EndNodeValidator.
   */
  @Test
  public void testValidate() throws Exception {
    EndNode n = new EndNode();
    EndNodeValidator validator = new EndNodeValidator();

    n.setName("EMPTY");
    validator.validate(n, schema);
    checkLog(new String[]{"validator.tag.endempty"});

    n.setName("OPT");
    validator.validate(n, schema);
    checkLog(new String[]{});

    n.setName("NOATTR");
    validator.validate(n, schema);
    checkLog(new String[]{});

    n.setName("FOO");
    validator.validate(n, schema);
    checkLog(new String[]{"validator.tag.unknown"});

    n.setName("DEPTAG");
    validator.validate(n, schema);
    checkLog(new String[]{});
  }

}
