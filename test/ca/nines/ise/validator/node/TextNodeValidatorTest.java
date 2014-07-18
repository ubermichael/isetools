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

import ca.nines.ise.node.TextNode;
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
public class TextNodeValidatorTest extends ValidatorTestBase {

  public TextNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
    super();
  }

  /**
   * Test of validate method, of class TextNodeValidator.
   */
  @Test
  public void testValidate() {
    TextNodeValidator validator = new TextNodeValidator();
    TextNode n = new TextNode();
    validator.validate(n, schema);
    checkLog(new String[]{});

    n.setText("foo bar yes please.");
    validator.validate(n, schema);
    checkLog(new String[]{});

    n.setText("foo \uFFFD yes please.");
    validator.validate(n, schema);
    checkLog(new String[]{"validator.text.badunicode"});

    n.setText("foo # yes please.");
    validator.validate(n, schema);
    checkLog(new String[]{"validator.text.depreciatedhash"});
  }

}
