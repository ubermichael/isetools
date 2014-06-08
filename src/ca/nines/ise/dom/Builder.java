/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.dom;

import ca.nines.ise.grammar.ISEParser;

import ca.nines.ise.grammar.ISEParser.AbbrContext;
import ca.nines.ise.grammar.ISEParser.AttributeNameContext;
import ca.nines.ise.grammar.ISEParser.AttributeValueContext;
import ca.nines.ise.grammar.ISEParser.CharacterContext;
import ca.nines.ise.grammar.ISEParser.CommentContext;
import ca.nines.ise.grammar.ISEParser.ContentContext;
import ca.nines.ise.grammar.ISEParser.EmptyTagContext;
import ca.nines.ise.grammar.ISEParser.EndTagContext;
import ca.nines.ise.grammar.ISEParser.StartTagContext;
import ca.nines.ise.grammar.ISEParser.TagContext;
import ca.nines.ise.grammar.ISEParser.TagNameContext;
import ca.nines.ise.grammar.ISEParserBaseListener;

import ca.nines.ise.node.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Builder extends ISEParserBaseListener {

  private final TokenStream tokens;
  private final DOM dom = new DOM();
  private TagNode current_tag;
  private String currentAttrName;
  private String currentAttrValue;

  public Builder(ISEParser iseparser) {
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

  public void enterCharacter(CharacterContext ctx) {
    CharNode n = (CharNode) setupNode(new CharNode(), ctx);
    dom.add(n);
  }

  public void enterContent(ContentContext ctx) {
    TextNode n = (TextNode) setupNode(new TextNode(), ctx);
    dom.add(n);
  }

  public void enterComment(CommentContext ctx) {
    CommentNode n = (CommentNode) setupNode(new CommentNode(), ctx);
    dom.add(n);
  }

  public void enterEndTag(EndTagContext ctx) {
    EndNode n = (EndNode) setupNode(new EndNode(), ctx);
    current_tag = n;
  }

  public void enterEmptyTag(EmptyTagContext ctx) {
    EmptyNode n = (EmptyNode) setupNode(new EmptyNode(), ctx);
    current_tag = n;
  }

  public void enterStartTag(StartTagContext ctx) {
    StartNode n = (StartNode) setupNode(new StartNode(), ctx);
    current_tag = n;
  }

  public void enterTagName(TagNameContext ctx) {
    String name = ctx.TAG_NAME().getText();
    current_tag.setName(name);
  }

  public void enterAttributeName(AttributeNameContext ctx) {
    currentAttrName =ctx.TAG_NAME().getText();
  }

  public void enterAttributeValue(AttributeValueContext ctx) {
    String value = ctx.ATTRIBUTE_VALUE().getText();
    current_tag.setAttribute(currentAttrName, value);
    currentAttrName = null;
  }

  public void exitTag(TagContext ctx) {
    dom.add(current_tag);
    current_tag = null;
    currentAttrName = null;
    currentAttrValue = null;
  }
}
