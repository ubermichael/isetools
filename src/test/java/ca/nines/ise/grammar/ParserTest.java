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

package ca.nines.ise.grammar;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.TagNode;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class ParserTest {
    
    @Test
    public void testAttributesWithSpaces() {
        try {
            String s = "<L n=\"31\" part= \"f\"/>";            
            DOM dom = new DOMBuilder(s).build();
            TagNode n = (TagNode)dom.get(0);
            String[] a = n.getAttributeNames();
            assertEquals(a[0], "n");
            assertEquals(a[1], "part");
            assertEquals(n.getAttribute("n"), "31");
            assertEquals(n.getAttribute("part"), "f");
        } catch (IOException ex) {
            Logger.getLogger(ParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testNodeText() {
        try {
            String s = "<L n=\"31\" part= \"f\"/>";            
            DOM dom = new DOMBuilder(s).build();
            TagNode n = (TagNode)dom.get(0);
            assertEquals(s, n.getText());
        } catch(IOException ex) {
            Logger.getLogger(ParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
}
