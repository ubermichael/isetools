/*
 * Copyright (C) 2015 Michael Joyce <ubermichael@gmail.com>
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
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.validator.node.TestBase;
import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Iterator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class CharNodeTestBase extends TestBase {

  protected void testUnicodify(String text, String unicode, CharNode charNode) throws IOException {
    testUnicodify(text, unicode, charNode, new String[]{});
  }

  protected void testUnicodify(String text, String unicode, CharNode charNode, String[] errors) throws IOException {
    charNode.setText(text);
    String u = Normalizer.normalize(charNode.unicode(), Form.NFC);
    String n = Normalizer.normalize(unicode, Form.NFC);
    assertEquals(u, n);
    checkLog(errors);
  }

  protected void testExpansion(String text, String unicode, CharNode charNode) throws IOException {
    testExpansion(text, unicode, charNode, new String[]{});
  }

  protected void testExpansion(String text, String unicode, CharNode charNode, String[] errors) throws IOException {
    charNode.setText(text);
    charNode.setAsl("3.2.1");
    charNode.setColumn(42);
    charNode.setLine(420);
    charNode.setTLN("11.3");
    Fragment dom = charNode.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node node;

    assertEquals(3, dom.size());
    node = iterator.next();
    assertEquals("START", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertArrayEquals(new String[]{"setting"}, ((TagNode) node).getAttributeNames());
    assertEquals(text, ((TagNode) node).getAttribute("setting"));

    node = iterator.next();
    assertEquals("TEXT", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    assertEquals(Normalizer.normalize(unicode, Normalizer.Form.NFC), node.getText());

    node = iterator.next();
    assertEquals("END", node.type().name());
    assertEquals("3.2.1", node.getAsl());
    assertEquals(42, node.getColumn());
    assertEquals(420, node.getLine());
    assertEquals("11.3", node.getTLN());
    checkLog(errors);
  }

}
