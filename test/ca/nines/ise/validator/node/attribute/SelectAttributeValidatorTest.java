/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node.attribute;

import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;
import ca.nines.ise.node.TagNodeImpl;
import ca.nines.ise.validator.node.ValidatorTestBase;
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
public class SelectAttributeValidatorTest extends ValidatorTestBase {

  public SelectAttributeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
    super();
  }

  /**
   * Test of validate method, of class SelectAttributeValidator.
   */
  @Test
  public void testValidate() {
    TagNode n = new TagNodeImpl();
    n.setName("SEL");
    Attribute attr = schema.getTag(n.getName()).getAttribute("opt");
    SelectAttributeValidator instance = new SelectAttributeValidator();

    n.setAttribute("opt", "thyme");
    instance.validate(n, attr);
    checkLog(new String[]{});

    n.setAttribute("opt", "mint");
    instance.validate(n, attr);
    checkLog(new String[]{});

    n.setAttribute("opt", "basil");
    instance.validate(n, attr);
    checkLog(new String[]{});

    n.setAttribute("opt", "minty");
    instance.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badselect"});

    n.setAttribute("opt", "");
    instance.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badselect"});
  }

}
