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

 */
public class AccentCharNodeTest extends CharNodeTestBase {

  /**
   * Test of expanded method, of class AccentCharNode.
   *
   * Check that all the new nodes have the correct line, column, etc. here. It
   * isn't really necessary to repeat those tests elsewhere.
   *
   * @throws java.io.IOException
   */
  @Test
  public void testExpanded() throws IOException {
    testExpansion("{^e}", "e\u0302", new AccentCharNode());
    testExpansion("{\"e}", "e\u0308", new AccentCharNode());
    testExpansion("{,e", "e\u0327", new AccentCharNode());
    testExpansion("{'e}", "e\u0301", new AccentCharNode());
    testExpansion("{`e}", "e\u0300", new AccentCharNode());
    testExpansion("{_e}", "e\u0304", new AccentCharNode());
    testExpansion("{~e}", "e\u0303", new AccentCharNode());
    testExpansion("{!e}", "e\uFFFD", new AccentCharNode(), new String[]{"char.accent.unknown"});
  }

  @Test
  public void testUnicode() throws IOException {
    testUnicodify("{^e}", "e\u0302", new AccentCharNode());
    testUnicodify("{\"e}", "e\u0308", new AccentCharNode());
    testUnicodify("{,e", "e\u0327", new AccentCharNode());
    testUnicodify("{'e}", "e\u0301", new AccentCharNode());
    testUnicodify("{`e}", "e\u0300", new AccentCharNode());
    testUnicodify("{_e}", "e\u0304", new AccentCharNode());
    testUnicodify("{~e}", "e\u0303", new AccentCharNode());
    testUnicodify("{!e}", "e\uFFFD", new AccentCharNode(), new String[]{"char.accent.unknown"});
  }

}
