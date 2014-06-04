/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.dom;

import ca.nines.ise.DOM;
import ca.nines.ise.grammar.ISEParser;

import ca.nines.ise.grammar.ISEParser.AbbrContext;
import ca.nines.ise.grammar.ISEParser.ElementContext;
import ca.nines.ise.grammar.ISEParserBaseListener;

import ca.nines.ise.node.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class DOMBuilder extends ISEParserBaseListener {

  ISEParser parser;
  TokenStream tokens;

  private DOM dom = new DOM();

  public DOMBuilder(ISEParser iseparser) {
    this.parser = iseparser;
    tokens = iseparser.getTokenStream();
  }

  private Node setupNode(Node n, ParserRuleContext ctx) {
    Token t = ctx.getStart();
    n.setLine(t.getLine());
    n.setColumn(t.getCharPositionInLine());
    n.setText(tokens.getText(ctx.getSourceInterval()));
    return n;
  }

  public DOM getDOM() {
    return dom;
  }

  public void enterAbbr (AbbrContext ctx) {
    AbbrNode n = (AbbrNode) setupNode(new AbbrNode(), ctx);
    dom.add(n);
  }

  public void exitAbbr(AbbrContext ctx) {

  }
}
