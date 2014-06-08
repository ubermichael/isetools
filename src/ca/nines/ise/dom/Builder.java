/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.dom;

import ca.nines.ise.grammar.ISELexer;
import ca.nines.ise.grammar.ISEParser;

import ca.nines.ise.grammar.ISEParser.*;
import ca.nines.ise.grammar.ISEParserBaseListener;

import ca.nines.ise.node.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Builder extends ISEParserBaseListener {

  private final ANTLRInputStream ais;
  private TokenStream tokens;

  private final DOM dom = new DOM();
  private TagNode currentTag;
  private String currentAttrName;

  public Builder(String input) {
    dom.setSource("#STRING");
    ais = new ANTLRInputStream(input);
  }

  public Builder(File input) throws FileNotFoundException, IOException {
    dom.setSource(input.getName());
    FileReader fr = new FileReader(input);
    ais = new ANTLRInputStream(fr);
  }

  public DOM getDOM() {
    ISELexer lexer = new ISELexer(ais);
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    ISEParser parser = new ISEParser(tokenStream);
    ParseTreeWalker ptw = new ParseTreeWalker();
    tokens = parser.getTokenStream();
    ParseTree pt = parser.document();
    ptw.walk(this, pt);
    return dom;
  }

  private Node setupNode(Node n, ParserRuleContext ctx) {
    Token t = ctx.getStart();
    n.setLine(t.getLine());
    n.setColumn(t.getCharPositionInLine());
    n.setText(tokens.getText(ctx.getSourceInterval()));
    return n;
  }

  public void enterAbbr(AbbrContext ctx) {
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
    currentTag = n;
  }

  public void enterEmptyTag(EmptyTagContext ctx) {
    EmptyNode n = (EmptyNode) setupNode(new EmptyNode(), ctx);
    currentTag = n;
  }

  public void enterStartTag(StartTagContext ctx) {
    StartNode n = (StartNode) setupNode(new StartNode(), ctx);
    currentTag = n;
  }

  public void enterTagName(TagNameContext ctx) {
    String name = ctx.TAG_NAME().getText();
    currentTag.setName(name);
  }

  public void enterAttributeName(AttributeNameContext ctx) {
    currentAttrName = ctx.TAG_NAME().getText();
  }

  public void enterAttributeValue(AttributeValueContext ctx) {
    String value = ctx.ATTRIBUTE_VALUE().getText();
    currentTag.setAttribute(currentAttrName, value);
    currentAttrName = null;
  }

  public void exitTag(TagContext ctx) {
    dom.add(currentTag);
    currentTag = null;
    currentAttrName = null;
  }
}