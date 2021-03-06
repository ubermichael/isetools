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

import ca.nines.ise.node.EndNode;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *

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
