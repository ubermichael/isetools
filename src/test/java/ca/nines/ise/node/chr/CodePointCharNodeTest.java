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
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.validator.node.TestBase;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *

 */
public class CodePointCharNodeTest extends TestBase {

  /**
   * Test of expanded method, of class CodePointChar.
   */
  @Test
  public void testExpandedDec() throws IOException {
    // leading zeros
    testExpansion("{&#0122}", "z"); // extended latin
    testExpansion("{&#0262}", "\u0106"); // extended latin
    testExpansion("{&#0934}", "\u03A6"); // greek PHI
    testExpansion("{&#1071}", "\u042F"); // cyrillic YA.

    // no leading zeros
    testExpansion("{&#122}", "z"); // extended latin
    testExpansion("{&#262}", "\u0106"); // extended latin
    testExpansion("{&#934}", "\u03A6"); // greek PHI
    testExpansion("{&#1071}", "\u042F"); // cyrillic YA.

    // higher characters
    testExpansion("{&#27700}", "\u6C34"); // CJK ideograph water

    // now this is just silly. MUSICAL SYMBOL G CLEF.
    testExpansion("{&#119070}", new String(Character.toChars(0x1d11e)));
  }

  /**
   * Test of expanded method, of class CodePointChar.
   */
  @Test
  public void testExpandedNamed() throws IOException {
    // leading zeros
    testExpansion("{&amp}", "&"); // extended latin
    testExpansion("{&lt}", "<"); // extended latin
    testExpansion("{&gt}", ">"); // greek PHI
    testExpansion("{&copy}", "\u00A9"); // cyrillic YA.

    // no leading zeros
    testExpansion("{&Delta}", "\u0394"); // extended latin
    testExpansion("{&delta}", "\u03b4"); // extended latin

    // higher characters
    testExpansion("{&sdot}", "\u22c5"); // CJK ideograph water

    // now this is just silly. MUSICAL SYMBOL G CLEF.
    testExpansion("{&notachar}", "\uFFFD", new String[]{"char.codepoint.unknown"});
  }

  /**
   * Test of expanded method, of class CodePointChar.
   */
  @Test
  public void testExpandedHex() throws IOException {
    // leading zeros
    testExpansion("{&#x007A}", "z"); // extended latin
    testExpansion("{&#x0106}", "\u0106"); // extended latin
    testExpansion("{&#x03A6}", "\u03A6"); // greek PHI
    testExpansion("{&#x042F}", "\u042F"); // cyrillic YA.

    // no leading zeros
    testExpansion("{&#x7A}", "z"); // extended latin
    testExpansion("{&#x106}", "\u0106"); // extended latin
    testExpansion("{&#x3A6}", "\u03A6"); // greek PHI
    testExpansion("{&#x42F}", "\u042F"); // cyrillic YA.

    // lower case
    testExpansion("{&#x7a}", "z"); // extended latin
    testExpansion("{&#x3a6}", "\u03A6"); // greek PHI
    testExpansion("{&#x42f}", "\u042F"); // cyrillic YA.

    // higher characters
    testExpansion("{&#x6c34}", "\u6C34"); // CJK ideograph water

    // now this is just silly. MUSICAL SYMBOL G CLEF.
    testExpansion("{&#x1D11E}", new String(Character.toChars(0x1d11e)));
  }

  /**
   * Test of expanded method, of class CodePointChar.
   */
  @Test
  public void testUnicodeDec() throws IOException {
    // leading zeros
    testUnicodify("{&#0122}", "z"); // extended latin
    testUnicodify("{&#0262}", "\u0106"); // extended latin
    testUnicodify("{&#0934}", "\u03A6"); // greek PHI
    testUnicodify("{&#1071}", "\u042F"); // cyrillic YA.

    // no leading zeros
    testUnicodify("{&#122}", "z"); // extended latin
    testUnicodify("{&#262}", "\u0106"); // extended latin
    testUnicodify("{&#934}", "\u03A6"); // greek PHI
    testUnicodify("{&#1071}", "\u042F"); // cyrillic YA.

    // higher characters
    testUnicodify("{&#27700}", "\u6C34"); // CJK ideograph water

    // now this is just silly. MUSICAL SYMBOL G CLEF.
    testUnicodify("{&#119070}", new String(Character.toChars(0x1d11e)));
  }

  /**
   * Test of expanded method, of class CodePointChar.
   */
  @Test
  public void testUnicodeNamed() throws IOException {
    // leading zeros
    testUnicodify("{&amp}", "&"); // extended latin
    testUnicodify("{&lt}", "<"); // extended latin
    testUnicodify("{&gt}", ">"); // greek PHI
    testUnicodify("{&copy}", "\u00A9"); // cyrillic YA.

    // no leading zeros
    testUnicodify("{&Delta}", "\u0394"); // extended latin
    testUnicodify("{&delta}", "\u03b4"); // extended latin

    // higher characters
    testUnicodify("{&sdot}", "\u22c5"); // CJK ideograph water

    // now this is just silly. MUSICAL SYMBOL G CLEF.
    testUnicodify("{&notachar}", "\uFFFD", new String[]{"char.codepoint.unknown"});
  }

  /**
   * Test of expanded method, of class CodePointChar.
   */
  @Test
  public void testUnicodeHex() throws IOException {
    // leading zeros
    testUnicodify("{&#x007A}", "z"); // extended latin
    testUnicodify("{&#x0106}", "\u0106"); // extended latin
    testUnicodify("{&#x03A6}", "\u03A6"); // greek PHI
    testUnicodify("{&#x042F}", "\u042F"); // cyrillic YA.

    // no leading zeros
    testUnicodify("{&#x7A}", "z"); // extended latin
    testUnicodify("{&#x106}", "\u0106"); // extended latin
    testUnicodify("{&#x3A6}", "\u03A6"); // greek PHI
    testUnicodify("{&#x42F}", "\u042F"); // cyrillic YA.

    // lower case
    testUnicodify("{&#x7a}", "z"); // extended latin
    testUnicodify("{&#x3a6}", "\u03A6"); // greek PHI
    testUnicodify("{&#x42f}", "\u042F"); // cyrillic YA.

    // higher characters
    testUnicodify("{&#x6c34}", "\u6C34"); // CJK ideograph water

    // now this is just silly. MUSICAL SYMBOL G CLEF.
    testUnicodify("{&#x1D11E}", new String(Character.toChars(0x1d11e)));
  }

  public void testUnicodify(String text, String unicode) throws IOException {
    testUnicodify(text, unicode, new String[]{});
  }

  public void testUnicodify(String text, String unicode, String[] errors) throws IOException {
    CharNode c = new CodePointCharNode();
    c.setText(text);
    String u = c.unicode();
    assertEquals(u, Normalizer.normalize(unicode, Normalizer.Form.NFC));
    checkLog(errors);
  }

  private void testExpansion(String text, String unicode) throws IOException {
    testExpansion(text, unicode, new String[]{});
  }

  private void testExpansion(String text, String unicode, String[] errors) throws IOException {
    CharNode charNode = new CodePointCharNode();
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
    assertEquals("CODEPOINT", node.getName());
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
    assertEquals("CODEPOINT", node.getName());
    checkLog(errors);
  }

}
