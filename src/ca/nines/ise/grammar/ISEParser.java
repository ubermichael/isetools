// Generated from grammar/ISEParser.g4 by ANTLR 4.2.2
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
		TAG_EQ=28, TAG_CLOSE=25, ACCENT=6, FANCY_QUOTED_STR=21, BAD_COMMENT=8, 
		ATTRIBUTE=33, UNQUOTED_STR=20, TAG_SLASH_CLOSE=26, TAG_NameStartChar=30, 
		DQ=16, DOUBLE_QUOTED_STR=18, S=5, FQ=17, DOC_TEXT=24, TEXT=1, TAG_NameChar=31, 
		ALPHANUM=4, ALPHA=2, CHAR_START=10, ABBR=9, TAG_OPEN=23, TAG_NAME=29, 
		NAMED_CHAR=14, TAG_SLASH=27, SQ=15, SINGLE_QUOTED_STR=19, TYPOGRAPHIC_CHAR=12, 
		DIGIT=3, COMMENT=7, SPACE_CHAR=13, ATTVALUE_VALUE=32, CHAR_END=11, STRING=22;
	public static final String[] tokenNames = {
		"<INVALID>", "TEXT", "ALPHA", "DIGIT", "ALPHANUM", "S", "ACCENT", "COMMENT", 
		"BAD_COMMENT", "ABBR", "'{'", "'}'", "TYPOGRAPHIC_CHAR", "SPACE_CHAR", 
		"NAMED_CHAR", "'''", "'\"'", "FQ", "DOUBLE_QUOTED_STR", "SINGLE_QUOTED_STR", 
		"UNQUOTED_STR", "FANCY_QUOTED_STR", "STRING", "'<'", "DOC_TEXT", "'>'", 
		"'/>'", "'/'", "'='", "TAG_NAME", "TAG_NameStartChar", "TAG_NameChar", 
		"ATTVALUE_VALUE", "ATTRIBUTE"
	};
	public static final int
		RULE_document = 0, RULE_element = 1, RULE_content = 2, RULE_comment = 3, 
		RULE_abbr = 4, RULE_defined_char = 5, RULE_space_char = 6, RULE_accent_char = 7, 
		RULE_typographic_char = 8, RULE_ligature_char = 9, RULE_nested_char = 10, 
		RULE_special_char = 11, RULE_tag_name = 12, RULE_attribute_name = 13, 
		RULE_attribute_value = 14, RULE_attribute = 15, RULE_start_tag = 16, RULE_empty_tag = 17, 
		RULE_end_tag = 18, RULE_tag = 19;
	public static final String[] ruleNames = {
		"document", "element", "content", "comment", "abbr", "defined_char", "space_char", 
		"accent_char", "typographic_char", "ligature_char", "nested_char", "special_char", 
		"tag_name", "attribute_name", "attribute_value", "attribute", "start_tag", 
		"empty_tag", "end_tag", "tag"
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
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << COMMENT) | (1L << BAD_COMMENT) | (1L << ABBR) | (1L << CHAR_START) | (1L << TAG_OPEN))) != 0)) {
				{
				{
				setState(40); element();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
		public Special_charContext special_char() {
			return getRuleContext(Special_charContext.class,0);
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
			setState(51);
			switch (_input.LA(1)) {
			case ABBR:
				enterOuterAlt(_localctx, 1);
				{
				setState(46); abbr();
				}
				break;
			case CHAR_START:
				enterOuterAlt(_localctx, 2);
				{
				setState(47); special_char();
				}
				break;
			case TAG_OPEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(48); tag();
				}
				break;
			case COMMENT:
			case BAD_COMMENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(49); comment();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 5);
				{
				setState(50); content();
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
		enterRule(_localctx, 4, RULE_content);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); match(TEXT);
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
		public TerminalNode BAD_COMMENT() { return getToken(ISEParser.BAD_COMMENT, 0); }
		public TerminalNode COMMENT() { return getToken(ISEParser.COMMENT, 0); }
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
			setState(55);
			_la = _input.LA(1);
			if ( !(_la==COMMENT || _la==BAD_COMMENT) ) {
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

	public static class AbbrContext extends ParserRuleContext {
		public TerminalNode ABBR() { return getToken(ISEParser.ABBR, 0); }
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
		enterRule(_localctx, 8, RULE_abbr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); match(ABBR);
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

	public static class Defined_charContext extends ParserRuleContext {
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode NAMED_CHAR() { return getToken(ISEParser.NAMED_CHAR, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public Defined_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defined_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterDefined_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitDefined_char(this);
		}
	}

	public final Defined_charContext defined_char() throws RecognitionException {
		Defined_charContext _localctx = new Defined_charContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defined_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); match(CHAR_START);
			setState(60); match(NAMED_CHAR);
			setState(61); match(CHAR_END);
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

	public static class Space_charContext extends ParserRuleContext {
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public TerminalNode SPACE_CHAR() { return getToken(ISEParser.SPACE_CHAR, 0); }
		public Space_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_space_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterSpace_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitSpace_char(this);
		}
	}

	public final Space_charContext space_char() throws RecognitionException {
		Space_charContext _localctx = new Space_charContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_space_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); match(CHAR_START);
			setState(64); match(SPACE_CHAR);
			setState(65); match(CHAR_END);
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

	public static class Accent_charContext extends ParserRuleContext {
		public TerminalNode ALPHA() { return getToken(ISEParser.ALPHA, 0); }
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode ACCENT() { return getToken(ISEParser.ACCENT, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public Accent_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accent_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterAccent_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitAccent_char(this);
		}
	}

	public final Accent_charContext accent_char() throws RecognitionException {
		Accent_charContext _localctx = new Accent_charContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_accent_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); match(CHAR_START);
			setState(68); match(ACCENT);
			setState(69); match(ALPHA);
			setState(70); match(CHAR_END);
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

	public static class Typographic_charContext extends ParserRuleContext {
		public TerminalNode TYPOGRAPHIC_CHAR() { return getToken(ISEParser.TYPOGRAPHIC_CHAR, 0); }
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public Typographic_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typographic_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterTypographic_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitTypographic_char(this);
		}
	}

	public final Typographic_charContext typographic_char() throws RecognitionException {
		Typographic_charContext _localctx = new Typographic_charContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typographic_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(CHAR_START);
			setState(73); match(TYPOGRAPHIC_CHAR);
			setState(74); match(CHAR_END);
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

	public static class Ligature_charContext extends ParserRuleContext {
		public List<TerminalNode> ALPHA() { return getTokens(ISEParser.ALPHA); }
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public TerminalNode ALPHA(int i) {
			return getToken(ISEParser.ALPHA, i);
		}
		public Ligature_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ligature_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterLigature_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitLigature_char(this);
		}
	}

	public final Ligature_charContext ligature_char() throws RecognitionException {
		Ligature_charContext _localctx = new Ligature_charContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ligature_char);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(CHAR_START);
			setState(77); match(ALPHA);
			setState(78); match(ALPHA);
			setState(80);
			_la = _input.LA(1);
			if (_la==ALPHA) {
				{
				setState(79); match(ALPHA);
				}
			}

			setState(82); match(CHAR_END);
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

	public static class Nested_charContext extends ParserRuleContext {
		public List<Typographic_charContext> typographic_char() {
			return getRuleContexts(Typographic_charContext.class);
		}
		public List<Accent_charContext> accent_char() {
			return getRuleContexts(Accent_charContext.class);
		}
		public Typographic_charContext typographic_char(int i) {
			return getRuleContext(Typographic_charContext.class,i);
		}
		public List<TerminalNode> ALPHA() { return getTokens(ISEParser.ALPHA); }
		public Accent_charContext accent_char(int i) {
			return getRuleContext(Accent_charContext.class,i);
		}
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public List<Ligature_charContext> ligature_char() {
			return getRuleContexts(Ligature_charContext.class);
		}
		public Ligature_charContext ligature_char(int i) {
			return getRuleContext(Ligature_charContext.class,i);
		}
		public TerminalNode ALPHA(int i) {
			return getToken(ISEParser.ALPHA, i);
		}
		public Nested_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nested_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterNested_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitNested_char(this);
		}
	}

	public final Nested_charContext nested_char() throws RecognitionException {
		Nested_charContext _localctx = new Nested_charContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_nested_char);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); match(CHAR_START);
			setState(89); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(89);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(85); match(ALPHA);
					}
					break;

				case 2:
					{
					setState(86); accent_char();
					}
					break;

				case 3:
					{
					setState(87); typographic_char();
					}
					break;

				case 4:
					{
					setState(88); ligature_char();
					}
					break;
				}
				}
				setState(91); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ALPHA || _la==CHAR_START );
			setState(93); match(CHAR_END);
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

	public static class Special_charContext extends ParserRuleContext {
		public Typographic_charContext typographic_char() {
			return getRuleContext(Typographic_charContext.class,0);
		}
		public Accent_charContext accent_char() {
			return getRuleContext(Accent_charContext.class,0);
		}
		public Defined_charContext defined_char() {
			return getRuleContext(Defined_charContext.class,0);
		}
		public Nested_charContext nested_char() {
			return getRuleContext(Nested_charContext.class,0);
		}
		public Ligature_charContext ligature_char() {
			return getRuleContext(Ligature_charContext.class,0);
		}
		public Space_charContext space_char() {
			return getRuleContext(Space_charContext.class,0);
		}
		public Special_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_special_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterSpecial_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitSpecial_char(this);
		}
	}

	public final Special_charContext special_char() throws RecognitionException {
		Special_charContext _localctx = new Special_charContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_special_char);
		try {
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95); defined_char();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(96); space_char();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(97); accent_char();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(98); typographic_char();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(99); ligature_char();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(100); nested_char();
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

	public static class Tag_nameContext extends ParserRuleContext {
		public TerminalNode TAG_NAME() { return getToken(ISEParser.TAG_NAME, 0); }
		public Tag_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tag_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterTag_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitTag_name(this);
		}
	}

	public final Tag_nameContext tag_name() throws RecognitionException {
		Tag_nameContext _localctx = new Tag_nameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_tag_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(TAG_NAME);
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

	public static class Attribute_nameContext extends ParserRuleContext {
		public TerminalNode TAG_NAME() { return getToken(ISEParser.TAG_NAME, 0); }
		public Attribute_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterAttribute_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitAttribute_name(this);
		}
	}

	public final Attribute_nameContext attribute_name() throws RecognitionException {
		Attribute_nameContext _localctx = new Attribute_nameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_attribute_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); match(TAG_NAME);
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

	public static class Attribute_valueContext extends ParserRuleContext {
		public TerminalNode ATTVALUE_VALUE() { return getToken(ISEParser.ATTVALUE_VALUE, 0); }
		public Attribute_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterAttribute_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitAttribute_value(this);
		}
	}

	public final Attribute_valueContext attribute_value() throws RecognitionException {
		Attribute_valueContext _localctx = new Attribute_valueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_attribute_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); match(ATTVALUE_VALUE);
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
		public Attribute_valueContext attribute_value() {
			return getRuleContext(Attribute_valueContext.class,0);
		}
		public Attribute_nameContext attribute_name() {
			return getRuleContext(Attribute_nameContext.class,0);
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
		enterRule(_localctx, 30, RULE_attribute);
		try {
			setState(114);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109); attribute_name();
				setState(110); match(TAG_EQ);
				setState(111); attribute_value();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113); attribute_name();
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

	public static class Start_tagContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode TAG_OPEN() { return getToken(ISEParser.TAG_OPEN, 0); }
		public Tag_nameContext tag_name() {
			return getRuleContext(Tag_nameContext.class,0);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode TAG_CLOSE() { return getToken(ISEParser.TAG_CLOSE, 0); }
		public Start_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterStart_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitStart_tag(this);
		}
	}

	public final Start_tagContext start_tag() throws RecognitionException {
		Start_tagContext _localctx = new Start_tagContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_start_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); match(TAG_OPEN);
			setState(117); tag_name();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TAG_NAME) {
				{
				{
				setState(118); attribute();
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(124); match(TAG_CLOSE);
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

	public static class Empty_tagContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode TAG_OPEN() { return getToken(ISEParser.TAG_OPEN, 0); }
		public TerminalNode TAG_SLASH_CLOSE() { return getToken(ISEParser.TAG_SLASH_CLOSE, 0); }
		public Tag_nameContext tag_name() {
			return getRuleContext(Tag_nameContext.class,0);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public Empty_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_empty_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterEmpty_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitEmpty_tag(this);
		}
	}

	public final Empty_tagContext empty_tag() throws RecognitionException {
		Empty_tagContext _localctx = new Empty_tagContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_empty_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); match(TAG_OPEN);
			setState(127); tag_name();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TAG_NAME) {
				{
				{
				setState(128); attribute();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134); match(TAG_SLASH_CLOSE);
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

	public static class End_tagContext extends ParserRuleContext {
		public TerminalNode TAG_SLASH() { return getToken(ISEParser.TAG_SLASH, 0); }
		public TerminalNode TAG_OPEN() { return getToken(ISEParser.TAG_OPEN, 0); }
		public Tag_nameContext tag_name() {
			return getRuleContext(Tag_nameContext.class,0);
		}
		public TerminalNode TAG_CLOSE() { return getToken(ISEParser.TAG_CLOSE, 0); }
		public End_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterEnd_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitEnd_tag(this);
		}
	}

	public final End_tagContext end_tag() throws RecognitionException {
		End_tagContext _localctx = new End_tagContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_end_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); match(TAG_OPEN);
			setState(137); match(TAG_SLASH);
			setState(138); tag_name();
			setState(139); match(TAG_CLOSE);
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
		public Start_tagContext start_tag() {
			return getRuleContext(Start_tagContext.class,0);
		}
		public Empty_tagContext empty_tag() {
			return getRuleContext(Empty_tagContext.class,0);
		}
		public End_tagContext end_tag() {
			return getRuleContext(End_tagContext.class,0);
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
			setState(144);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141); start_tag();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142); empty_tag();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(143); end_tag();
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#\u0095\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2\16\2/\13\2\3\3\3\3\3\3"+
		"\3\3\3\3\5\3\66\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\5\13S\n"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\6\f\\\n\f\r\f\16\f]\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\rh\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\5\21u\n\21\3\22\3\22\3\22\7\22z\n\22\f\22\16\22}\13\22\3\22"+
		"\3\22\3\23\3\23\3\23\7\23\u0084\n\23\f\23\16\23\u0087\13\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\5\25\u0093\n\25\3\25\2\2\26\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\3\3\2\t\n\u0094\2-\3\2\2"+
		"\2\4\65\3\2\2\2\6\67\3\2\2\2\b9\3\2\2\2\n;\3\2\2\2\f=\3\2\2\2\16A\3\2"+
		"\2\2\20E\3\2\2\2\22J\3\2\2\2\24N\3\2\2\2\26V\3\2\2\2\30g\3\2\2\2\32i\3"+
		"\2\2\2\34k\3\2\2\2\36m\3\2\2\2 t\3\2\2\2\"v\3\2\2\2$\u0080\3\2\2\2&\u008a"+
		"\3\2\2\2(\u0092\3\2\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2"+
		"\2\2.\3\3\2\2\2/-\3\2\2\2\60\66\5\n\6\2\61\66\5\30\r\2\62\66\5(\25\2\63"+
		"\66\5\b\5\2\64\66\5\6\4\2\65\60\3\2\2\2\65\61\3\2\2\2\65\62\3\2\2\2\65"+
		"\63\3\2\2\2\65\64\3\2\2\2\66\5\3\2\2\2\678\7\3\2\28\7\3\2\2\29:\t\2\2"+
		"\2:\t\3\2\2\2;<\7\13\2\2<\13\3\2\2\2=>\7\f\2\2>?\7\20\2\2?@\7\r\2\2@\r"+
		"\3\2\2\2AB\7\f\2\2BC\7\17\2\2CD\7\r\2\2D\17\3\2\2\2EF\7\f\2\2FG\7\b\2"+
		"\2GH\7\4\2\2HI\7\r\2\2I\21\3\2\2\2JK\7\f\2\2KL\7\16\2\2LM\7\r\2\2M\23"+
		"\3\2\2\2NO\7\f\2\2OP\7\4\2\2PR\7\4\2\2QS\7\4\2\2RQ\3\2\2\2RS\3\2\2\2S"+
		"T\3\2\2\2TU\7\r\2\2U\25\3\2\2\2V[\7\f\2\2W\\\7\4\2\2X\\\5\20\t\2Y\\\5"+
		"\22\n\2Z\\\5\24\13\2[W\3\2\2\2[X\3\2\2\2[Y\3\2\2\2[Z\3\2\2\2\\]\3\2\2"+
		"\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7\r\2\2`\27\3\2\2\2ah\5\f\7\2bh\5\16"+
		"\b\2ch\5\20\t\2dh\5\22\n\2eh\5\24\13\2fh\5\26\f\2ga\3\2\2\2gb\3\2\2\2"+
		"gc\3\2\2\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2h\31\3\2\2\2ij\7\37\2\2j\33\3"+
		"\2\2\2kl\7\37\2\2l\35\3\2\2\2mn\7\"\2\2n\37\3\2\2\2op\5\34\17\2pq\7\36"+
		"\2\2qr\5\36\20\2ru\3\2\2\2su\5\34\17\2to\3\2\2\2ts\3\2\2\2u!\3\2\2\2v"+
		"w\7\31\2\2w{\5\32\16\2xz\5 \21\2yx\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2"+
		"\2|~\3\2\2\2}{\3\2\2\2~\177\7\33\2\2\177#\3\2\2\2\u0080\u0081\7\31\2\2"+
		"\u0081\u0085\5\32\16\2\u0082\u0084\5 \21\2\u0083\u0082\3\2\2\2\u0084\u0087"+
		"\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0089\7\34\2\2\u0089%\3\2\2\2\u008a\u008b\7\31\2"+
		"\2\u008b\u008c\7\35\2\2\u008c\u008d\5\32\16\2\u008d\u008e\7\33\2\2\u008e"+
		"\'\3\2\2\2\u008f\u0093\5\"\22\2\u0090\u0093\5$\23\2\u0091\u0093\5&\24"+
		"\2\u0092\u008f\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093)"+
		"\3\2\2\2\f-\65R[]gt{\u0085\u0092";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}