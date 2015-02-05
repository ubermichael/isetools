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
 * @author Michael Joyce <ubermichael@gmail.com>
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
