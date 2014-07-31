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

import ca.nines.ise.node.StartNode;
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
public class StartNodeValidatorTest extends ValidatorTestBase {

  public StartNodeValidatorTest() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
    super();
  }

  /**
   * Test of validate method, of class StartNodeValidator.
   */
  @Test
  public void testValidate() throws Exception {
    StartNode n = new StartNode();
    StartNodeValidator validator = new StartNodeValidator();

    n.setName("EMPTY");
    validator.validate(n, schema);
    checkLog(new String[]{"validator.tag.startempty"});

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
    checkLog(new String[]{"validator.tag.depreciated"});
  }

}
