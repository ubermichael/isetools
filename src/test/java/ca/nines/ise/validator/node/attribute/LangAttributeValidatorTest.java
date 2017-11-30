/*
 * Copyright (C) 2017 michael
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
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

import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
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
public class LangAttributeValidatorTest extends ValidatorTestBase {
    
    public LangAttributeValidatorTest() throws SAXException, IOException, XPathExpressionException, ParserConfigurationException, TransformerException {
        super();        
    }
    
    /**
     * Test of validate method, of class LangAttributeValidator.
     */
    @Test
    public void testValidate() {
        TagNode n = new StartNode();
        n.setName("FOREIGN");
        LangAttributeValidator instance = new LangAttributeValidator();        
        
        n.setAttribute("lang", "fr");
        instance.validate(n, null);
        checkLog(new String[]{});    

        n.setAttribute("lang", "frx");
        instance.validate(n, null);
        checkLog(new String[]{"validator.attribute.badlanguage"});    

    }
    
}
