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
public class ListAttributeValidatorTest extends ValidatorTestBase {

  public ListAttributeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
    super();
  }

  /**
   * Test of validate method, of class ListAttributeValidator.
   */
  @Test
  public void testValidate() {
    TagNode n = new TagNodeImpl();
    n.setName("LIST");
    Attribute attr = schema.getTag(n.getName()).getAttribute("items");
    ListAttributeValidator instance = new ListAttributeValidator();

    n.setAttribute("items", "earth");
    instance.validate(n, attr);
    checkLog(new String[]{});

    n.setAttribute("items", "air, wind, basil");
    instance.validate(n, attr);
    checkLog(new String[]{});

    n.setAttribute("items", "basil, wind, air");
    instance.validate(n, attr);
    checkLog(new String[]{});

    n.setAttribute("items", "minty");
    instance.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badlist"});

    n.setAttribute("items", "fire, minty, smiles");
    instance.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badlist"});

    n.setAttribute("items", "minty, smiles");
    instance.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badlist"});

    n.setAttribute("items", "");
    instance.validate(n, attr);
    checkLog(new String[]{"validator.attribute.badlist"});
  }

}
