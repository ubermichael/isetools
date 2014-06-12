// Generated from src/ca/nines/ise/grammar/ISEParser.g4 by ANTLR 4.2.2
package ca.nines.ise.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ISEParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TAG_NAME=17, TAG_EQ=16, CHAR_TYPOGRAPHIC=8, TAG_END=13, COMMENT_GOOD=1, 
		CHAR_SPACE=6, TAG_SLASH=15, ABBREVIATION=3, TAG_START=11, TAG_WS=18, ATTRIBUTE=20, 
		CHAR_COMPLEX_LIGATURE=10, COMMENT_BAD=2, TAG_SLASH_END=14, CHAR_UNICODE=4, 
		ATTRIBUTE_VALUE=19, CHAR_SIMPLE_LIGATURE=9, TEXT=12, CHAR_DIGRAPH=5, CHAR_ACCENT=7;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT_GOOD", "COMMENT_BAD", "ABBREVIATION", "CHAR_UNICODE", 
		"CHAR_DIGRAPH", "CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", "CHAR_SIMPLE_LIGATURE", 
		"CHAR_COMPLEX_LIGATURE", "'<'", "TEXT", "'>'", "'/>'", "'/'", "'='", "TAG_NAME", 
		"TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE"
	};
	public static final int
		RULE_document = 0, RULE_element = 1, RULE_abbr = 2, RULE_comment = 3, 
		RULE_charUnicode = 4, RULE_charDigraph = 5, RULE_charSpace = 6, RULE_charAccent = 7, 
		RULE_charTypographic = 8, RULE_charSimpleLigature = 9, RULE_charComplexLigature = 10, 
		RULE_charLigature = 11, RULE_character = 12, RULE_attribute = 13, RULE_attributeName = 14, 
		RULE_attributeValue = 15, RULE_emptyTag = 16, RULE_endTag = 17, RULE_startTag = 18, 
		RULE_tag = 19, RULE_tagName = 20, RULE_content = 21;
	public static final String[] ruleNames = {
		"document", "element", "abbr", "comment", "charUnicode", "charDigraph", 
		"charSpace", "charAccent", "charTypographic", "charSimpleLigature", "charComplexLigature", 
		"charLigature", "character", "attribute", "attributeName", "attributeValue", 
		"emptyTag", "endTag", "startTag", "tag", "tagName", "content"
	};

	@Override
	public String getGrammarFileName() { return "ISEParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ISEParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DocumentContext extends ParserRuleContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitDocument(this);
		}
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_document);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(44); element();
				}
				}
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT_GOOD) | (1L << COMMENT_BAD) | (1L << ABBREVIATION) | (1L << CHAR_UNICODE) | (1L << CHAR_DIGRAPH) | (1L << CHAR_SPACE) | (1L << CHAR_ACCENT) | (1L << CHAR_TYPOGRAPHIC) | (1L << CHAR_SIMPLE_LIGATURE) | (1L << CHAR_COMPLEX_LIGATURE) | (1L << TAG_START) | (1L << TEXT))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public AbbrContext abbr() {
			return getRuleContext(AbbrContext.class,0);
		}
		public TagContext tag() {
			return getRuleContext(TagContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public CharacterContext character() {
			return getRuleContext(CharacterContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitElement(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_element);
		try {
			setState(54);
			switch (_input.LA(1)) {
			case ABBREVIATION:
				enterOuterAlt(_localctx, 1);
				{
				setState(49); abbr();
				}
				break;
			case COMMENT_GOOD:
			case COMMENT_BAD:
				enterOuterAlt(_localctx, 2);
				{
				setState(50); comment();
				}
				break;
			case CHAR_UNICODE:
			case CHAR_DIGRAPH:
			case CHAR_SPACE:
			case CHAR_ACCENT:
			case CHAR_TYPOGRAPHIC:
			case CHAR_SIMPLE_LIGATURE:
			case CHAR_COMPLEX_LIGATURE:
				enterOuterAlt(_localctx, 3);
				{
				setState(51); character();
				}
				break;
			case TAG_START:
				enterOuterAlt(_localctx, 4);
				{
				setState(52); tag();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 5);
				{
				setState(53); content();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbbrContext extends ParserRuleContext {
		public TerminalNode ABBREVIATION() { return getToken(ISEParser.ABBREVIATION, 0); }
		public AbbrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abbr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterAbbr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitAbbr(this);
		}
	}

	public final AbbrContext abbr() throws RecognitionException {
		AbbrContext _localctx = new AbbrContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_abbr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56); match(ABBREVIATION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT_BAD() { return getToken(ISEParser.COMMENT_BAD, 0); }
		public TerminalNode COMMENT_GOOD() { return getToken(ISEParser.COMMENT_GOOD, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_comment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_la = _input.LA(1);
			if ( !(_la==COMMENT_GOOD || _la==COMMENT_BAD) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharUnicodeContext extends ParserRuleContext {
		public TerminalNode CHAR_UNICODE() { return getToken(ISEParser.CHAR_UNICODE, 0); }
		public CharUnicodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charUnicode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterCharUnicode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitCharUnicode(this);
		}
	}

	public final CharUnicodeContext charUnicode() throws RecognitionException {
		CharUnicodeContext _localctx = new CharUnicodeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_charUnicode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); match(CHAR_UNICODE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharDigraphContext extends ParserRuleContext {
		public TerminalNode CHAR_DIGRAPH() { return getToken(ISEParser.CHAR_DIGRAPH, 0); }
		public CharDigraphContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charDigraph; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterCharDigraph(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitCharDigraph(this);
		}
	}

	public final CharDigraphContext charDigraph() throws RecognitionException {
		CharDigraphContext _localctx = new CharDigraphContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_charDigraph);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(CHAR_DIGRAPH);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharSpaceContext extends ParserRuleContext {
		public TerminalNode CHAR_SPACE() { return getToken(ISEParser.CHAR_SPACE, 0); }
		public CharSpaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charSpace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterCharSpace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitCharSpace(this);
		}
	}

	public final CharSpaceContext charSpace() throws RecognitionException {
		CharSpaceContext _localctx = new CharSpaceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_charSpace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); match(CHAR_SPACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharAccentContext extends ParserRuleContext {
		public TerminalNode CHAR_ACCENT() { return getToken(ISEParser.CHAR_ACCENT, 0); }
		public CharAccentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charAccent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterCharAccent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitCharAccent(this);
		}
	}

	public final CharAccentContext charAccent() throws RecognitionException {
		CharAccentContext _localctx = new CharAccentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_charAccent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(CHAR_ACCENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharTypographicContext extends ParserRuleContext {
		public TerminalNode CHAR_TYPOGRAPHIC() { return getToken(ISEParser.CHAR_TYPOGRAPHIC, 0); }
		public CharTypographicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charTypographic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterCharTypographic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitCharTypographic(this);
		}
	}

	public final CharTypographicContext charTypographic() throws RecognitionException {
		CharTypographicContext _localctx = new CharTypographicContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_charTypographic);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); match(CHAR_TYPOGRAPHIC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharSimpleLigatureContext extends ParserRuleContext {
		public TerminalNode CHAR_SIMPLE_LIGATURE() { return getToken(ISEParser.CHAR_SIMPLE_LIGATURE, 0); }
		public CharSimpleLigatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charSimpleLigature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterCharSimpleLigature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitCharSimpleLigature(this);
		}
	}

	public final CharSimpleLigatureContext charSimpleLigature() throws RecognitionException {
		CharSimpleLigatureContext _localctx = new CharSimpleLigatureContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_charSimpleLigature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); match(CHAR_SIMPLE_LIGATURE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharComplexLigatureContext extends ParserRuleContext {
		public TerminalNode CHAR_COMPLEX_LIGATURE() { return getToken(ISEParser.CHAR_COMPLEX_LIGATURE, 0); }
		public CharComplexLigatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charComplexLigature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterCharComplexLigature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitCharComplexLigature(this);
		}
	}

	public final CharComplexLigatureContext charComplexLigature() throws RecognitionException {
		CharComplexLigatureContext _localctx = new CharComplexLigatureContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_charComplexLigature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(CHAR_COMPLEX_LIGATURE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharLigatureContext extends ParserRuleContext {
		public CharSimpleLigatureContext charSimpleLigature() {
			return getRuleContext(CharSimpleLigatureContext.class,0);
		}
		public CharComplexLigatureContext charComplexLigature() {
			return getRuleContext(CharComplexLigatureContext.class,0);
		}
		public CharLigatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charLigature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterCharLigature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitCharLigature(this);
		}
	}

	public final CharLigatureContext charLigature() throws RecognitionException {
		CharLigatureContext _localctx = new CharLigatureContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_charLigature);
		try {
			setState(76);
			switch (_input.LA(1)) {
			case CHAR_SIMPLE_LIGATURE:
				enterOuterAlt(_localctx, 1);
				{
				setState(74); charSimpleLigature();
				}
				break;
			case CHAR_COMPLEX_LIGATURE:
				enterOuterAlt(_localctx, 2);
				{
				setState(75); charComplexLigature();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharacterContext extends ParserRuleContext {
		public CharTypographicContext charTypographic() {
			return getRuleContext(CharTypographicContext.class,0);
		}
		public CharLigatureContext charLigature() {
			return getRuleContext(CharLigatureContext.class,0);
		}
		public CharAccentContext charAccent() {
			return getRuleContext(CharAccentContext.class,0);
		}
		public CharUnicodeContext charUnicode() {
			return getRuleContext(CharUnicodeContext.class,0);
		}
		public CharDigraphContext charDigraph() {
			return getRuleContext(CharDigraphContext.class,0);
		}
		public CharSpaceContext charSpace() {
			return getRuleContext(CharSpaceContext.class,0);
		}
		public CharacterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterCharacter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitCharacter(this);
		}
	}

	public final CharacterContext character() throws RecognitionException {
		CharacterContext _localctx = new CharacterContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_character);
		try {
			setState(84);
			switch (_input.LA(1)) {
			case CHAR_UNICODE:
				enterOuterAlt(_localctx, 1);
				{
				setState(78); charUnicode();
				}
				break;
			case CHAR_DIGRAPH:
				enterOuterAlt(_localctx, 2);
				{
				setState(79); charDigraph();
				}
				break;
			case CHAR_SPACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(80); charSpace();
				}
				break;
			case CHAR_ACCENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(81); charAccent();
				}
				break;
			case CHAR_TYPOGRAPHIC:
				enterOuterAlt(_localctx, 5);
				{
				setState(82); charTypographic();
				}
				break;
			case CHAR_SIMPLE_LIGATURE:
			case CHAR_COMPLEX_LIGATURE:
				enterOuterAlt(_localctx, 6);
				{
				setState(83); charLigature();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode TAG_EQ() { return getToken(ISEParser.TAG_EQ, 0); }
		public AttributeValueContext attributeValue() {
			return getRuleContext(AttributeValueContext.class,0);
		}
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_attribute);
		try {
			setState(91);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86); attributeName();
				setState(87); match(TAG_EQ);
				setState(88); attributeValue();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90); attributeName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeNameContext extends ParserRuleContext {
		public TerminalNode TAG_NAME() { return getToken(ISEParser.TAG_NAME, 0); }
		public AttributeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterAttributeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitAttributeName(this);
		}
	}

	public final AttributeNameContext attributeName() throws RecognitionException {
		AttributeNameContext _localctx = new AttributeNameContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_attributeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); match(TAG_NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeValueContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTE_VALUE() { return getToken(ISEParser.ATTRIBUTE_VALUE, 0); }
		public AttributeValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterAttributeValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitAttributeValue(this);
		}
	}

	public final AttributeValueContext attributeValue() throws RecognitionException {
		AttributeValueContext _localctx = new AttributeValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_attributeValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); match(ATTRIBUTE_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyTagContext extends ParserRuleContext {
		public TagNameContext tagName() {
			return getRuleContext(TagNameContext.class,0);
		}
		public TerminalNode TAG_SLASH_END() { return getToken(ISEParser.TAG_SLASH_END, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode TAG_START() { return getToken(ISEParser.TAG_START, 0); }
		public EmptyTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterEmptyTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitEmptyTag(this);
		}
	}

	public final EmptyTagContext emptyTag() throws RecognitionException {
		EmptyTagContext _localctx = new EmptyTagContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_emptyTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); match(TAG_START);
			setState(98); tagName();
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TAG_NAME) {
				{
				{
				setState(99); attribute();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105); match(TAG_SLASH_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EndTagContext extends ParserRuleContext {
		public TagNameContext tagName() {
			return getRuleContext(TagNameContext.class,0);
		}
		public TerminalNode TAG_SLASH() { return getToken(ISEParser.TAG_SLASH, 0); }
		public TerminalNode TAG_END() { return getToken(ISEParser.TAG_END, 0); }
		public TerminalNode TAG_START() { return getToken(ISEParser.TAG_START, 0); }
		public EndTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterEndTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitEndTag(this);
		}
	}

	public final EndTagContext endTag() throws RecognitionException {
		EndTagContext _localctx = new EndTagContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_endTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); match(TAG_START);
			setState(108); match(TAG_SLASH);
			setState(109); tagName();
			setState(110); match(TAG_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StartTagContext extends ParserRuleContext {
		public TagNameContext tagName() {
			return getRuleContext(TagNameContext.class,0);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode TAG_END() { return getToken(ISEParser.TAG_END, 0); }
		public TerminalNode TAG_START() { return getToken(ISEParser.TAG_START, 0); }
		public StartTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterStartTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitStartTag(this);
		}
	}

	public final StartTagContext startTag() throws RecognitionException {
		StartTagContext _localctx = new StartTagContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_startTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(TAG_START);
			setState(113); tagName();
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TAG_NAME) {
				{
				{
				setState(114); attribute();
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(120); match(TAG_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagContext extends ParserRuleContext {
		public StartTagContext startTag() {
			return getRuleContext(StartTagContext.class,0);
		}
		public EndTagContext endTag() {
			return getRuleContext(EndTagContext.class,0);
		}
		public EmptyTagContext emptyTag() {
			return getRuleContext(EmptyTagContext.class,0);
		}
		public TagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitTag(this);
		}
	}

	public final TagContext tag() throws RecognitionException {
		TagContext _localctx = new TagContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_tag);
		try {
			setState(125);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122); emptyTag();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123); endTag();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(124); startTag();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagNameContext extends ParserRuleContext {
		public TerminalNode TAG_NAME() { return getToken(ISEParser.TAG_NAME, 0); }
		public TagNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterTagName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitTagName(this);
		}
	}

	public final TagNameContext tagName() throws RecognitionException {
		TagNameContext _localctx = new TagNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_tagName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); match(TAG_NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContentContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(ISEParser.TEXT, 0); }
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitContent(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_content);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); match(TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\26\u0086\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\6\2\60\n\2\r\2"+
		"\16\2\61\3\3\3\3\3\3\3\3\3\3\5\39\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\5\rO\n\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16W\n\16\3\17\3\17\3\17\3\17\3\17\5\17^\n\17\3"+
		"\20\3\20\3\21\3\21\3\22\3\22\3\22\7\22g\n\22\f\22\16\22j\13\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\7\24v\n\24\f\24\16\24y\13"+
		"\24\3\24\3\24\3\25\3\25\3\25\5\25\u0080\n\25\3\26\3\26\3\27\3\27\3\27"+
		"\2\2\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\3\3\2\3\4\177"+
		"\2/\3\2\2\2\48\3\2\2\2\6:\3\2\2\2\b<\3\2\2\2\n>\3\2\2\2\f@\3\2\2\2\16"+
		"B\3\2\2\2\20D\3\2\2\2\22F\3\2\2\2\24H\3\2\2\2\26J\3\2\2\2\30N\3\2\2\2"+
		"\32V\3\2\2\2\34]\3\2\2\2\36_\3\2\2\2 a\3\2\2\2\"c\3\2\2\2$m\3\2\2\2&r"+
		"\3\2\2\2(\177\3\2\2\2*\u0081\3\2\2\2,\u0083\3\2\2\2.\60\5\4\3\2/.\3\2"+
		"\2\2\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\3\3\2\2\2\639\5\6\4\2"+
		"\649\5\b\5\2\659\5\32\16\2\669\5(\25\2\679\5,\27\28\63\3\2\2\28\64\3\2"+
		"\2\28\65\3\2\2\28\66\3\2\2\28\67\3\2\2\29\5\3\2\2\2:;\7\5\2\2;\7\3\2\2"+
		"\2<=\t\2\2\2=\t\3\2\2\2>?\7\6\2\2?\13\3\2\2\2@A\7\7\2\2A\r\3\2\2\2BC\7"+
		"\b\2\2C\17\3\2\2\2DE\7\t\2\2E\21\3\2\2\2FG\7\n\2\2G\23\3\2\2\2HI\7\13"+
		"\2\2I\25\3\2\2\2JK\7\f\2\2K\27\3\2\2\2LO\5\24\13\2MO\5\26\f\2NL\3\2\2"+
		"\2NM\3\2\2\2O\31\3\2\2\2PW\5\n\6\2QW\5\f\7\2RW\5\16\b\2SW\5\20\t\2TW\5"+
		"\22\n\2UW\5\30\r\2VP\3\2\2\2VQ\3\2\2\2VR\3\2\2\2VS\3\2\2\2VT\3\2\2\2V"+
		"U\3\2\2\2W\33\3\2\2\2XY\5\36\20\2YZ\7\22\2\2Z[\5 \21\2[^\3\2\2\2\\^\5"+
		"\36\20\2]X\3\2\2\2]\\\3\2\2\2^\35\3\2\2\2_`\7\23\2\2`\37\3\2\2\2ab\7\25"+
		"\2\2b!\3\2\2\2cd\7\r\2\2dh\5*\26\2eg\5\34\17\2fe\3\2\2\2gj\3\2\2\2hf\3"+
		"\2\2\2hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2kl\7\20\2\2l#\3\2\2\2mn\7\r\2\2no"+
		"\7\21\2\2op\5*\26\2pq\7\17\2\2q%\3\2\2\2rs\7\r\2\2sw\5*\26\2tv\5\34\17"+
		"\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7\17"+
		"\2\2{\'\3\2\2\2|\u0080\5\"\22\2}\u0080\5$\23\2~\u0080\5&\24\2\177|\3\2"+
		"\2\2\177}\3\2\2\2\177~\3\2\2\2\u0080)\3\2\2\2\u0081\u0082\7\23\2\2\u0082"+
		"+\3\2\2\2\u0083\u0084\7\16\2\2\u0084-\3\2\2\2\n\618NV]hw\177";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}