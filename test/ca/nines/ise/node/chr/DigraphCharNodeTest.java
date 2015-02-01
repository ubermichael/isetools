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

import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class DigraphCharNodeTest extends CharNodeTestBase {

  /**
   * Test of expanded method, of class DigraphCharNode.
   * <p>
   * @throws java.io.IOException
   */
  @Test
  public void testExpanded() throws IOException {
    testExpansion("{ae}", "\u00e6", new DigraphCharNode());
    testExpansion("{AE}", "\u00c6", new DigraphCharNode());
    testExpansion("{oe}", "\u0153", new DigraphCharNode());
    testExpansion("{OE}", "\u0152", new DigraphCharNode());
    testExpansion("{qp}", "\u0239", new DigraphCharNode());
    testExpansion("{db}", "\u0238", new DigraphCharNode());
    testExpansion("{pd}", "\uFFFD", new DigraphCharNode(), new String[]{"char.digraph.unknown"});
  }
  
  @Test
  public void textUnicodify() throws IOException {
    testUnicodify("{ae}", "\u00e6", new DigraphCharNode());
    testUnicodify("{AE}", "\u00c6", new DigraphCharNode());
    testUnicodify("{oe}", "\u0153", new DigraphCharNode());
    testUnicodify("{OE}", "\u0152", new DigraphCharNode());
    testUnicodify("{qp}", "\u0239", new DigraphCharNode());
    testUnicodify("{db}", "\u0238", new DigraphCharNode());
    testUnicodify("{pd}", "\uFFFD", new DigraphCharNode(), new String[]{"char.digraph.unknown"});
  }
  
}
