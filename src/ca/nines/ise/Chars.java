/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.grammar.ISELexer;
import ca.nines.ise.grammar.ISEParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Chars {

  public static void main(String[] args) {
    String str = "{abc}{{s}f}{{s}{s}h}";
    ANTLRInputStream ais = new ANTLRInputStream(str);
    ISELexer lexer = new ISELexer(ais);
    CommonTokenStream ts = new CommonTokenStream(lexer);    
    ISEParser parser = new ISEParser(ts);
    ParseTree pt = parser.document();
    System.out.println(pt.toStringTree(parser));
  }

}
