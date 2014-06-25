/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Schema;
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

//  /**
//   * Test of validate_attribute_string method, of class TagNodeValidator.
//   */
//  @Test
//  public void testValidate_attribute_string() {
//    System.out.println("validate_attribute_string");
//    TagNode n = null;
//    Attribute attr = null;
//    TagNodeValidator instance = null;
//    instance.validate_attribute_string(n, attr);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of validate_attribute_number method, of class TagNodeValidator.
//   */
//  @Test
//  public void testValidate_attribute_number() {
//    System.out.println("validate_attribute_number");
//    TagNode n = null;
//    Attribute attr = null;
//    TagNodeValidator instance = null;
//    instance.validate_attribute_number(n, attr);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of validate_attribute_list method, of class TagNodeValidator.
//   */
//  @Test
//  public void testValidate_attribute_list() {
//    System.out.println("validate_attribute_list");
//    TagNode n = null;
//    Attribute attr = null;
//    TagNodeValidator instance = null;
//    instance.validate_attribute_list(n, attr);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of validate_attribute_select method, of class TagNodeValidator.
//   */
//  @Test
//  public void testValidate_attribute_select() {
//    System.out.println("validate_attribute_select");
//    TagNode n = null;
//    Attribute attr = null;
//    TagNodeValidator instance = null;
//    instance.validate_attribute_select(n, attr);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of validate_attribute_value method, of class TagNodeValidator.
   */
//  @Test
//  public void testValidate_attribute() {
//    System.out.println("validate_attribute_value");
//    TagNode n = null;
//    Attribute attr = null;
//    TagNodeValidator instance = null;
//    instance.validate_attribute(n, attr);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of validate_attributes method, of class TagNodeValidator.
   */
  @Test
  public void testValidate_attributes() throws Exception {
    TagNode n = new TagNodeImpl();
    TagNodeValidator instance = new TagNodeValidatorImpl(schema);
    instance.validate_attributes(n);
    checkLog(new String[]{});

    n.setName("NONEXISTANT");
    instance.validate_attributes(n);
    checkLog(new String[]{});

    n.setName("NOATTR");
    instance.validate_attributes(n);
    checkLog(new String[]{});

    n.setAttribute("non", "yes");
    instance.validate_attributes(n);
    checkLog(new String[]{"validator.attribute.unknown"});
    n.clearAttributes();

    n.setName("ONEATTR");
    instance.validate_attributes(n);
    checkLog(new String[]{"validator.attribute.missing"});

    n.setAttribute("n", "");
    instance.validate_attributes(n);
    checkLog(new String[]{"validator.attribute.nonempty"});

    n.setAttribute("n", "3");
    instance.validate_attributes(n);
    checkLog(new String[]{});

    n.setName("TWOATTRS");
    instance.validate_attributes(n);
    checkLog(new String[]{"validator.attribute.missing"});

    n.setAttribute("b", "3");
    instance.validate_attributes(n);
    checkLog(new String[]{});
    
    n.setName("DEPAT");
    n.clearAttributes();
    n.setAttribute("foo", "yes");
    instance.validate_attributes(n);
    checkLog(new String[]{"validator.attribute.depreciated"});
  }

  public class TagNodeValidatorImpl extends TagNodeValidator {

    public TagNodeValidatorImpl(Schema schema) {
      super(schema);
    }

    @Override
    public void validate(TagNode node) throws Exception {
      validate_attributes(node);
    }
  }
  
  public class TagNodeImpl extends TagNode {

    @Override
    public NodeType type() {
      return null;
    }
    
  }

}
