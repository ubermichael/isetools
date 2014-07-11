/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.node.TagNodeImpl;
import ca.nines.ise.node.TagNode;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class TagNodeValidatorTest extends ValidatorTestBase {

  public TagNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super();
  }

  /**
   * Test of validate_attributes method, of class TagNodeValidator. Tests
   * attributes present on a node, but doesn't test the attribute value matches
   * the type, etc.
   */
  @Test
  public void testValidate_attributes() throws Exception {
    TagNode n = new TagNodeImpl();
    TagNodeValidator<?> instance = new TagNodeValidatorImpl();
    instance.validate_attributes(n, schema);
    checkLog(new String[]{});

    n.setName("NONEXISTANT");
    instance.validate_attributes(n, schema);
    checkLog(new String[]{});

    n.setName("NOATTR");
    instance.validate_attributes(n, schema);
    checkLog(new String[]{});

    n.setAttribute("non", "yes");
    instance.validate_attributes(n, schema);
    checkLog(new String[]{"validator.attribute.unknown"});
    n.clearAttributes();

    n.setName("ONEATTR");
    instance.validate_attributes(n, schema);
    checkLog(new String[]{"validator.attribute.missing"});

    n.setAttribute("n", "");
    instance.validate_attributes(n, schema);
    checkLog(new String[]{"validator.attribute.nonempty"});

    n.setAttribute("n", "3");
    instance.validate_attributes(n, schema);
    checkLog(new String[]{});

    n.setName("TWOATTRS");
    instance.validate_attributes(n, schema);
    checkLog(new String[]{"validator.attribute.missing"});

    n.setAttribute("b", "3");
    instance.validate_attributes(n, schema);
    checkLog(new String[]{});

    n.setName("DEPAT");
    n.clearAttributes();
    n.setAttribute("foo", "yes");
    instance.validate_attributes(n, schema);
    checkLog(new String[]{"validator.attribute.depreciated"});
  }

}
