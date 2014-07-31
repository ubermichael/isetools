/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
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
public class StringAttributeValidatorTest extends ValidatorTestBase {

  public StringAttributeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
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
