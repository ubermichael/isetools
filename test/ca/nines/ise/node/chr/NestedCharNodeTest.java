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
public class NestedCharNodeTest extends CharNodeTestBase {

  @Test
  public void testUnicodify() throws IOException {
	testUnicodify("{{^e}t}", "e\u0302t", new NestedCharNode());
	testUnicodify("{{\"e}t}", "e\u0308t", new NestedCharNode());
	testUnicodify("{{'e}t}", "e\u0301t", new NestedCharNode());
	testUnicodify("{{`e}t}", "e\u0300t", new NestedCharNode());
	testUnicodify("{{_e}t}", "e\u0304t", new NestedCharNode());
	testUnicodify("{{~e}t}", "e\u0303t", new NestedCharNode());
	testUnicodify("{a{^e}}", "ae\u0302", new NestedCharNode());
	testUnicodify("{a{\"e}}", "ae\u0308", new NestedCharNode());
	testUnicodify("{a{'e}}", "ae\u0301", new NestedCharNode());
	testUnicodify("{a{`e}}", "ae\u0300", new NestedCharNode());
	testUnicodify("{a{_e}}", "ae\u0304", new NestedCharNode());
	testUnicodify("{a{~e}}", "ae\u0303", new NestedCharNode());
	testUnicodify("{{^a}{^e}}", "a\u0302e\u0302", new NestedCharNode());
	
	//@TODO test code points, digraphs, and unicode char nodes.
	
  }

}
