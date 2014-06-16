/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import ca.nines.ise.grammar.ISELexer;
import ca.nines.ise.grammar.ISEParser;
import ca.nines.ise.grammar.ISEParserBaseListener;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class GrammarTest {
  
  public GrammarTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  private ISEParser buildParser(String in) {
    ANTLRInputStream ais = new ANTLRInputStream(in);
    ISELexer lexer = new ISELexer(ais);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    ISEParser parser = new ISEParser(tokens);
    return parser;
  }
  
  private class TestWalker extends ISEParserBaseListener {
    
    public class TokenList extends ArrayList<Token> {
      
    }
    
    public TokenList tl = new TokenList();
    
    public void enterEveryRule(ParserRuleContext ctx) {
      tl.add(ctx.getStart());
    }
    
  }
  
  /**
   * Test of toString method, of class DOM.
   */
  @Test
  public void testContent() {
    String in = "this is some text.";
    ISEParser parser = buildParser(in);
    ParseTree tree = parser.content();
    ParseTreeWalker w = new ParseTreeWalker();
    TestWalker tw = new TestWalker();
    w.walk(tw,tree);
    assertEquals(1, tw.tl.size());
    Token t = tw.tl.get(0);
    assertEquals("this is some text.", t.getText());
    
    // how do i check that the token tw.tl[0] is a 
    // content token?
  }
  
}
