/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
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

package ca.nines.ise.validator.node;

import ca.nines.ise.node.TagNodeImpl;
import ca.nines.ise.node.TagNode;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class TagNodeValidatorTest extends ValidatorTestBase {

  public TagNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
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
