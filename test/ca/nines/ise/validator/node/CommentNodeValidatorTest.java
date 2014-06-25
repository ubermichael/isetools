/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.node.CommentNode;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class CommentNodeValidatorTest extends ValidatorTestBase {

  public CommentNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    super();
  }

  /**
   * Test of validate method, of class CommentNodeValidator.
   */
  @Test
  public void testValidate() {
    CommentNodeValidator validator = new CommentNodeValidator(schema);
    CommentNode n;

    n = new CommentNode();
    validator.validate(n);
    checkLog(new String[]{"validator.comment.badstart", "validator.comment.badend"});

    n.setText("<! bad start, good end. -->");
    validator.validate(n);
    checkLog(new String[]{"validator.comment.badstart"});

    n.setText("<!-- good start, bad end. >");
    validator.validate(n);
    checkLog(new String[]{"validator.comment.badend"});

    n.setText("<!-- dashes -- dashes -->");
    validator.validate(n);
    checkLog(new String[]{"validator.comment.dashes"});

    n.setText("<!-- good comment. -->");
    validator.validate(n);
    checkLog(new String[]{});
  }

}
