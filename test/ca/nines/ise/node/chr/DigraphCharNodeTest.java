/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.chr;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.validator.node.TestBase;
import java.text.Normalizer;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class DigraphCharNodeTest extends TestBase {

  /**
   * Test of expanded method, of class DigraphCharNode.
   */
  @Test
  public void testExpanded() {
    DigraphCharNode instance = new DigraphCharNode();
    instance.setText("{ae}");
    Fragment dom = instance.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node n;
    
    assertEquals(3, dom.size());
    n = iterator.next();
    assertEquals("START", n.type().name());
    assertEquals("DIGRAPH", n.getName());
    assertEquals("{ae}", ((StartNode)n).getAttribute("setting"));
    
    n = iterator.next();
    assertEquals("TEXT", n.type().name());
    assertEquals(Normalizer.normalize("æ", Normalizer.Form.NFC), n.getText());
    
    n = iterator.next();
    assertEquals("END", n.type().name());
    assertEquals("DIGRAPH", n.getName());
    checkLog();
  }

}
