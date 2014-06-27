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
import java.text.Normalizer.Form;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class AccentCharNodeTest extends TestBase {

  /**
   * Test of expanded method, of class AccentCharNode.
   */
  @Test
  public void testExpandedCircumflex() {
    AccentCharNode instance = new AccentCharNode();
    instance.setText("{^e}");
    Fragment dom = instance.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node n;
    
    assertEquals(3, dom.size());
    n = iterator.next();
    assertEquals("START", n.type().name());
    assertEquals("ACCENT", n.getName());
    assertEquals("{^e}", ((StartNode)n).getAttribute("setting"));
    
    n = iterator.next();
    assertEquals("TEXT", n.type().name());
    assertEquals(Normalizer.normalize("e\u0302", Form.NFC), n.getText());
    
    n = iterator.next();
    assertEquals("END", n.type().name());
    assertEquals("ACCENT", n.getName());
    checkLog();
  }

  /**
   * Test of expanded method, of class AccentCharNode.
   */
  @Test
  public void testExpandedUmlat() {
    AccentCharNode instance = new AccentCharNode();
    instance.setText("{\"u}");
    Fragment dom = instance.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node n;
    
    assertEquals(3, dom.size());
    n = iterator.next();
    assertEquals("START", n.type().name());
    assertEquals("ACCENT", n.getName());
    assertEquals("{\"u}", ((StartNode)n).getAttribute("setting"));
    
    n = iterator.next();
    assertEquals("TEXT", n.type().name());
    assertEquals(Normalizer.normalize("ü", Form.NFC), n.getText());
    
    n = iterator.next();
    assertEquals("END", n.type().name());
    assertEquals("ACCENT", n.getName());
    checkLog();
  }

  /**
   * Test of expanded method, of class AccentCharNode.
   */
  @Test
  public void testExpandedAcute() {
    AccentCharNode instance = new AccentCharNode();
    instance.setText("{'a}");
    Fragment dom = instance.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node n;
    
    assertEquals(3, dom.size());
    n = iterator.next();
    assertEquals("START", n.type().name());
    assertEquals("ACCENT", n.getName());
    assertEquals("{'a}", ((StartNode)n).getAttribute("setting"));
    
    n = iterator.next();
    assertEquals("TEXT", n.type().name());
    assertEquals(Normalizer.normalize("á", Form.NFC), n.getText());
    
    n = iterator.next();
    assertEquals("END", n.type().name());
    assertEquals("ACCENT", n.getName());
    checkLog();
  }

  /**
   * Test of expanded method, of class AccentCharNode.
   */
  @Test
  public void testExpandedGrave() {
    AccentCharNode instance = new AccentCharNode();
    instance.setText("{`o}");
    Fragment dom = instance.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node n;
    
    assertEquals(3, dom.size());
    n = iterator.next();
    assertEquals("START", n.type().name());
    assertEquals("ACCENT", n.getName());
    assertEquals("{`o}", ((StartNode)n).getAttribute("setting"));
    
    n = iterator.next();
    assertEquals("TEXT", n.type().name());
    assertEquals(Normalizer.normalize("ò", Form.NFC), n.getText());
    
    n = iterator.next();
    assertEquals("END", n.type().name());
    assertEquals("ACCENT", n.getName());
    checkLog();
  }

  /**
   * Test of expanded method, of class AccentCharNode.
   */
  @Test
  public void testExpandedMacron() {
    AccentCharNode instance = new AccentCharNode();
    instance.setText("{_o}");
    Fragment dom = instance.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node n;
    
    assertEquals(3, dom.size());
    n = iterator.next();
    assertEquals("START", n.type().name());
    assertEquals("ACCENT", n.getName());
    assertEquals("{_o}", ((StartNode)n).getAttribute("setting"));
    
    n = iterator.next();
    assertEquals("TEXT", n.type().name());
    assertEquals(Normalizer.normalize("ō", Form.NFC), n.getText());
    
    n = iterator.next();
    assertEquals("END", n.type().name());
    assertEquals("ACCENT", n.getName());
    checkLog();
  }

  /**
   * Test of expanded method, of class AccentCharNode.
   */
  @Test
  public void testExpandedTilde() {
    AccentCharNode instance = new AccentCharNode();
    instance.setText("{~n}");
    Fragment dom = instance.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node n;
    
    assertEquals(3, dom.size());
    n = iterator.next();
    assertEquals("START", n.type().name());
    assertEquals("ACCENT", n.getName());
    assertEquals("{~n}", ((StartNode)n).getAttribute("setting"));
    
    n = iterator.next();
    assertEquals("TEXT", n.type().name());
    assertEquals(Normalizer.normalize("ñ", Form.NFC), n.getText());
    
    n = iterator.next();
    assertEquals("END", n.type().name());
    assertEquals("ACCENT", n.getName());
    checkLog();
  }

  /**
   * Test of expanded method, of class AccentCharNode.
   */
  @Test
  public void testExpandedBad() {
    AccentCharNode instance = new AccentCharNode();
    instance.setText("{!e}");
    Fragment dom = instance.expanded();
    Iterator<Node> iterator = dom.iterator();
    Node n;
    
    assertEquals(3, dom.size());
    n = iterator.next();
    assertEquals("START", n.type().name());
    assertEquals("ACCENT", n.getName());
    assertEquals("{!e}", ((StartNode)n).getAttribute("setting"));
    
    n = iterator.next();
    assertEquals("TEXT", n.type().name());
    assertEquals(Normalizer.normalize("e\uFFFD", Form.NFC), n.getText());
    
    n = iterator.next();
    assertEquals("END", n.type().name());
    assertEquals("ACCENT", n.getName());
    checkLog(new String[]{"char.accent.unknown"});
  }
}
