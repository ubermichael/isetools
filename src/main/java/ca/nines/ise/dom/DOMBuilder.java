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
package ca.nines.ise.dom;

import ca.nines.ise.dom.DOM.DOMStatus;
import ca.nines.ise.node.chr.UnicodeCharNode;
import ca.nines.ise.node.chr.LigatureCharNode;
import ca.nines.ise.node.chr.TypographicCharNode;
import ca.nines.ise.node.chr.DigraphCharNode;
import ca.nines.ise.node.chr.NestedCharNode;
import ca.nines.ise.node.chr.SpaceCharNode;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.grammar.ISELexer;
import ca.nines.ise.grammar.ISEParser;
import ca.nines.ise.grammar.ISEParser.*;

import ca.nines.ise.grammar.ISEParserBaseListener;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.*;
import ca.nines.ise.node.chr.CodePointCharNode;
import ca.nines.ise.node.Attribute;
import ca.nines.ise.util.BuilderInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * DOMBuilderInterface creates a DOM from from either a File or a String. The
 * class itself extends ISEParserBaseListener, which is automatically generated
 * by Antlr. Errors are handled by ParserErrorListener.
 * <p>
 * Sample usage:
 * <p>
 * <blockquote><pre><code>
 * File file = new File("/path/to/file");
 * DOMBuilderInterface builder = new DOMBuilderInterface(file);
 * DOM dom = builder.build();
 * </code></pre></blockquote></p>
 * <p>
 * Or
 * <p>
 * <blockquote><pre><code>
 * String input = "<WORK>Hello world.</WORK>";
 * DOMBuilderInterface builder = new DOMBuilderInterface(input);
 * DOM dom = builder.build();
 * </code></pre></blockquote></p>
 * <p>
 * <p>
 * @see ParserErrorListener
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class DOMBuilder extends ISEParserBaseListener implements BuilderInterface<DOM> {

  /**
   * Input Stream for the Antlr parser.
   */
  private final ANTLRInputStream ais;

  /**
   * Attribute being processed.
   */
  private Attribute currentAttr;

  /**
   * The tag currently being processed.
   */
  private TagNode currentTag;

  /**
   * The DOM being constructed.
   */
  private final DOM dom = new DOM();

  /**
   * A stream of tokens, as found by Antlr's lexer.
   */
  private TokenStream tokens;

  /**
   * Constructs a DOMBuilder from an InputStream, recording the source of the
   * input. The input will be run through a DOMStream before parsing.
   *
   * @param in
   * @param source
   * <p>
   * @throws java.io.IOException
   */
  public DOMBuilder(InputStream in, String source) throws IOException {
    DOMStream domStream = new DOMStream(in, source);
    dom.setSource(source);
    dom.setLines(domStream.getLines());
    ais = new ANTLRInputStream(domStream.getContent());
  }

  /**
   * Constructs a DOMBuilder from a string. The resulting DOM source will be
   * "#STRING". The input will be run through a DOMStream before parsing.
   * <p>
   * @param in The string to parse.
   * <p>
   * @throws java.io.IOException
   */
  public DOMBuilder(String in) throws IOException {
    DOMStream domStream = new DOMStream(in);
    dom.setSource("#STRING");
    dom.setLines(domStream.getLines());
    ais = new ANTLRInputStream(domStream.getContent());
  }

  /**
   * Constructs a BuilderInterface from a File. The resulting DOM source will
   * return the absolute path to the file. The input will be run through a
   * DOMStream before parsing.
   * <p>
   * @param in The file to read and parse.
   * <p>
   * @throws FileNotFoundException if the file cannot be found.
   * @throws IOException if the file cannot be read.
   */
  public DOMBuilder(File in) throws FileNotFoundException, IOException {
    DOMStream domStream = new DOMStream(in);
    dom.setSource(in.getCanonicalPath());
    dom.setLines(domStream.getLines());
    ais = new ANTLRInputStream(domStream.getContent());
  }

  /**
   * Fire off the full parse of the input and return the resulting DOM object.
   * Any errors encountered during the parse will be logged in the Log class.
   * <p>
   * @see Log
   * <p>
   * @return the DOM built by parsing the string or file.
   */
  @Override
  public DOM build() {
    ParserErrorListener parserListener = new ParserErrorListener(dom);
    ISELexer lexer = new ISELexer(ais);
    lexer.removeErrorListeners();
    lexer.addErrorListener(parserListener);

    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    ISEParser parser = new ISEParser(tokenStream);
    parser.removeErrorListeners();
    parser.addErrorListener(parserListener);

    ParseTreeWalker ptw = new ParseTreeWalker();
    tokens = parser.getTokenStream();
    ParseTree pt = parser.document();
    ptw.walk(this, pt);
    dom.index();
    return dom;
  }

  /**
   * Enter an abbreviation node. Create a new Abbr node from the context.
   *
   * @param ctx
   */
  @Override
  public void enterAbbr(AbbrContext ctx) {
    AbbrNode n = (AbbrNode) setupNode(new AbbrNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter the attribute name state. Saves the attribute name for later.
   *
   * @param ctx
   */
  @Override
  public void enterAttributeName(AttributeNameContext ctx) {
	  currentAttr = (Attribute) setupNode(new Attribute(), ctx);
	  String name = ctx.TAG_NAME().getText();
	  currentAttr.setName(name);
  }

  /**
   * Enter the attribute value state. Sets the attribute in the currentTag.
   *
   * @param ctx
   */
  @Override
  public void enterAttributeValue(AttributeValueContext ctx) {
    String value = ctx.ATTRIBUTE_VALUE().getText();
    value = value.replaceAll("^['\"]|['\"]$", "");
    currentAttr.setValue(value);
	currentTag.setAttribute(currentAttr);
	
  }

  /**
   * Enters the character accent state. Creates an AccentCharNode from the
   * context.
   *
   * @param ctx
   */
  @Override
  public void enterCharAccent(CharAccentContext ctx) {
    CharNode n = (CharNode) setupNode(CharNode.accentChar(), ctx);
    dom.add(n);
  }

  /**
   * Enter the code point state. Builds a CodePointCharNode from the context.
   *
   * @param ctx
   */
  @Override
  public void enterCharCodePoint(CharCodePointContext ctx) {
    CodePointCharNode n = (CodePointCharNode) setupNode(new CodePointCharNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter the digraph state. Constructs a DigraphCharNode node from the
   * context.
   *
   * @param ctx
   */
  @Override
  public void enterCharDigraph(CharDigraphContext ctx) {
    DigraphCharNode n = (DigraphCharNode) setupNode(new DigraphCharNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter the ligature character state. Constructs a LigatureCharNode from the
   * the parser context.
   *
   * @param ctx
   */
  @Override
  public void enterCharLigature(CharLigatureContext ctx) {
    LigatureCharNode n = (LigatureCharNode) setupNode(new LigatureCharNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter the nested character node. Constructs a NestedCharNode from the
   * context.
   *
   * @param ctx
   */
  @Override
  public void enterCharNested(CharNestedContext ctx) {
    NestedCharNode n = (NestedCharNode) setupNode(new NestedCharNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter the character space mode. Constructs a node from the context.
   *
   * @param ctx
   */
  @Override
  public void enterCharSpace(CharSpaceContext ctx) {
    SpaceCharNode n = (SpaceCharNode) setupNode(new SpaceCharNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter a typographic character node. Constructs a node from the context.
   *
   * @param ctx
   */
  @Override
  public void enterCharTypographic(CharTypographicContext ctx) {
    TypographicCharNode n = (TypographicCharNode) setupNode(new TypographicCharNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter a unicode character node. Creates a node from the context.
   *
   * @param ctx
   */
  @Override
  public void enterCharUnicode(CharUnicodeContext ctx) {
    UnicodeCharNode n = (UnicodeCharNode) setupNode(new UnicodeCharNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter a comment markup. Creates a new node from the context.
   *
   * @param ctx
   */
  @Override
  public void enterComment(CommentContext ctx) {
    CommentNode n = (CommentNode) setupNode(new CommentNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter text content. Creates a new node from the context.
   *
   * @param ctx
   */
  @Override
  public void enterContent(ContentContext ctx) {
    TextNode n = (TextNode) setupNode(new TextNode(), ctx);
    dom.add(n);
  }

  /**
   * Enter the empty tag state. Creates a new node from the context.
   *
   * @param ctx
   */
  @Override
  public void enterEmptyTag(EmptyTagContext ctx) {
    EmptyNode n = (EmptyNode) setupNode(new EmptyNode(), ctx);
    currentTag = n;
  }

  /**
   * Enter the end tag state. Creates a new node from the context.
   *
   * @param ctx
   */
  @Override
  public void enterEndTag(EndTagContext ctx) {
    EndNode n = (EndNode) setupNode(new EndNode(), ctx);
    currentTag = n;
  }

  /**
   * Enter the start tag state. Creates a new node from the context.
   *
   * @param ctx
   */
  @Override
  public void enterStartTag(StartTagContext ctx) {
    StartNode n = (StartNode) setupNode(new StartNode(), ctx);
    currentTag = n;
  }

  /**
   * Enter the tag name state. Sets the current tag's name.
   *
   * @param ctx
   */
  @Override
  public void enterTagName(TagNameContext ctx) {
    String name = ctx.TAG_NAME().getText();
    currentTag.setName(name);
  }

  /**
   * Leaves any tag state. Adds the current tag to the dom, if there is one.
   * Sets the currentTag and currentAttrName to null.
   *
   * @param ctx
   */
  @Override
  public void exitTag(TagContext ctx) {
    if (currentTag == null) {
      dom.setStatus(DOMStatus.ERROR);
    } else {
      dom.add(currentTag);
    }
    currentTag = null;
    currentAttr = null;
  }

  /**
   * Set up a newly created node with information from the context.
   *
   * @param n
   * @param ctx
   * @return Node
   */
  // @TODO turn this into Node.Builder and provide a Node.builder()
  // etc.
  private Node setupNode(Node n, ParserRuleContext ctx) {
    Token t = ctx.getStart();
    n.setOwner(dom);
    n.setLine(t.getLine());
    n.setColumn(t.getCharPositionInLine());
    n.setText(tokens.getText(ctx.getSourceInterval()));
    return n;
  }
}
