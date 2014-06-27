/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.validator.node.TestBase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class NestedCharNodeTest extends TestBase {

  /**
   * Test of expanded method, of class NestedCharNode.
   */
  @Test
  public void testExpanded() throws Exception {
    System.out.println("expanded");
    NestedCharNode instance = new NestedCharNode();
    Fragment expResult = null;
    Fragment result = instance.expanded();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
