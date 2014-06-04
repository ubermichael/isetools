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
		TAG_NAME=18, TAG_EQ=17, NESTED_END=12, CHAR_CONTENT=10, TAG_END=14, NESTED_CONTENT=13, 
		TAG_SLASH=16, TAG_START=5, TAG_WS=19, ATTRIBUTE=21, COMMENT_BAD=2, ABBR_START=3, 
		TAG_SLASH_END=15, ATTRIBUTE_VALUE=20, TEXT=6, COMMENT=1, ABBR_END=7, CHAR_START=4, 
		ABBR_CONTENT=8, CHAR_END=9, NESTED_START=11;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT", "COMMENT_BAD", "ABBR_START", "CHAR_START", "'<'", 
		"TEXT", "ABBR_END", "ABBR_CONTENT", "CHAR_END", "CHAR_CONTENT", "NESTED_START", 
		"NESTED_END", "NESTED_CONTENT", "'>'", "'/>'", "'/'", "'='", "TAG_NAME", 
		"TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE"
	};
	public static final int
		RULE_document = 0, RULE_element = 1, RULE_abbr = 2, RULE_comment = 3, 
		RULE_character = 4, RULE_nested_char = 5, RULE_attribute = 6, RULE_attribute_name = 7, 
		RULE_attribute_value = 8, RULE_tag = 9, RULE_empty_tag = 10, RULE_end_tag = 11, 
		RULE_start_tag = 12, RULE_tag_name = 13, RULE_content = 14;
	public static final String[] ruleNames = {
		"document", "element", "abbr", "comment", "character", "nested_char", 
		"attribute", "attribute_name", "attribute_value", "tag", "empty_tag", 
		"end_tag", "start_tag", "tag_name", "content"
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
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << COMMENT_BAD) | (1L << ABBR_START) | (1L << CHAR_START) | (1L << TAG_START) | (1L << TEXT))) != 0)) {
				{
				{
				setState(30); element();
				}
				}
				setState(35);
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
			setState(41);
			switch (_input.LA(1)) {
			case ABBR_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(36); abbr();
				}
				break;
			case COMMENT:
			case COMMENT_BAD:
				enterOuterAlt(_localctx, 2);
				{
				setState(37); comment();
				}
				break;
			case CHAR_START:
				enterOuterAlt(_localctx, 3);
				{
				setState(38); character();
				}
				break;
			case TAG_START:
				enterOuterAlt(_localctx, 4);
				{
				setState(39); tag();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 5);
				{
				setState(40); content();
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
		public List<TerminalNode> ABBR_CONTENT() { return getTokens(ISEParser.ABBR_CONTENT); }
		public TerminalNode ABBR_START() { return getToken(ISEParser.ABBR_START, 0); }
		public TerminalNode ABBR_CONTENT(int i) {
			return getToken(ISEParser.ABBR_CONTENT, i);
		}
		public TerminalNode ABBR_END() { return getToken(ISEParser.ABBR_END, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); match(ABBR_START);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ABBR_CONTENT) {
				{
				{
				setState(44); match(ABBR_CONTENT);
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50); match(ABBR_END);
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
			setState(52);
			_la = _input.LA(1);
			if ( !(_la==COMMENT || _la==COMMENT_BAD) ) {
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

	public static class CharacterContext extends ParserRuleContext {
		public Nested_charContext nested_char(int i) {
			return getRuleContext(Nested_charContext.class,i);
		}
		public TerminalNode CHAR_CONTENT(int i) {
			return getToken(ISEParser.CHAR_CONTENT, i);
		}
		public List<TerminalNode> CHAR_CONTENT() { return getTokens(ISEParser.CHAR_CONTENT); }
		public List<Nested_charContext> nested_char() {
			return getRuleContexts(Nested_charContext.class);
		}
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
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
		enterRule(_localctx, 8, RULE_character);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); match(CHAR_START);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHAR_CONTENT || _la==NESTED_START) {
				{
				setState(57);
				switch (_input.LA(1)) {
				case CHAR_CONTENT:
					{
					setState(55); match(CHAR_CONTENT);
					}
					break;
				case NESTED_START:
					{
					setState(56); nested_char();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62); match(CHAR_END);
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
		public TerminalNode NESTED_END() { return getToken(ISEParser.NESTED_END, 0); }
		public TerminalNode NESTED_START() { return getToken(ISEParser.NESTED_START, 0); }
		public TerminalNode NESTED_CONTENT() { return getToken(ISEParser.NESTED_CONTENT, 0); }
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
		enterRule(_localctx, 10, RULE_nested_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); match(NESTED_START);
			setState(65); match(NESTED_CONTENT);
			setState(66); match(NESTED_END);
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
		enterRule(_localctx, 12, RULE_attribute);
		try {
			setState(73);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68); attribute_name();
				setState(69); match(TAG_EQ);
				setState(70); attribute_value();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72); attribute_name();
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
		enterRule(_localctx, 14, RULE_attribute_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(TAG_NAME);
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
		public TerminalNode ATTRIBUTE_VALUE() { return getToken(ISEParser.ATTRIBUTE_VALUE, 0); }
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
		enterRule(_localctx, 16, RULE_attribute_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77); match(ATTRIBUTE_VALUE);
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
		enterRule(_localctx, 18, RULE_tag);
		try {
			setState(82);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79); empty_tag();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80); end_tag();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(81); start_tag();
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

	public static class Empty_tagContext extends ParserRuleContext {
		public TerminalNode TAG_SLASH_END() { return getToken(ISEParser.TAG_SLASH_END, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public Tag_nameContext tag_name() {
			return getRuleContext(Tag_nameContext.class,0);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode TAG_START() { return getToken(ISEParser.TAG_START, 0); }
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
		enterRule(_localctx, 20, RULE_empty_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); match(TAG_START);
			setState(85); tag_name();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TAG_NAME) {
				{
				{
				setState(86); attribute();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92); match(TAG_SLASH_END);
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
		public Tag_nameContext tag_name() {
			return getRuleContext(Tag_nameContext.class,0);
		}
		public TerminalNode TAG_END() { return getToken(ISEParser.TAG_END, 0); }
		public TerminalNode TAG_START() { return getToken(ISEParser.TAG_START, 0); }
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
		enterRule(_localctx, 22, RULE_end_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); match(TAG_START);
			setState(95); match(TAG_SLASH);
			setState(96); tag_name();
			setState(97); match(TAG_END);
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
		public Tag_nameContext tag_name() {
			return getRuleContext(Tag_nameContext.class,0);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode TAG_END() { return getToken(ISEParser.TAG_END, 0); }
		public TerminalNode TAG_START() { return getToken(ISEParser.TAG_START, 0); }
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
		enterRule(_localctx, 24, RULE_start_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); match(TAG_START);
			setState(100); tag_name();
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TAG_NAME) {
				{
				{
				setState(101); attribute();
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(107); match(TAG_END);
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
		enterRule(_localctx, 26, RULE_tag_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109); match(TAG_NAME);
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
		enterRule(_localctx, 28, RULE_content);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); match(TEXT);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\27t\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\7\2\"\n\2\f\2\16\2%\13"+
		"\2\3\3\3\3\3\3\3\3\3\3\5\3,\n\3\3\4\3\4\7\4\60\n\4\f\4\16\4\63\13\4\3"+
		"\4\3\4\3\5\3\5\3\6\3\6\3\6\7\6<\n\6\f\6\16\6?\13\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\5\bL\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\5\13"+
		"U\n\13\3\f\3\f\3\f\7\fZ\n\f\f\f\16\f]\13\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\7\16i\n\16\f\16\16\16l\13\16\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\20\2\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\3\3\2\3\4q"+
		"\2#\3\2\2\2\4+\3\2\2\2\6-\3\2\2\2\b\66\3\2\2\2\n8\3\2\2\2\fB\3\2\2\2\16"+
		"K\3\2\2\2\20M\3\2\2\2\22O\3\2\2\2\24T\3\2\2\2\26V\3\2\2\2\30`\3\2\2\2"+
		"\32e\3\2\2\2\34o\3\2\2\2\36q\3\2\2\2 \"\5\4\3\2! \3\2\2\2\"%\3\2\2\2#"+
		"!\3\2\2\2#$\3\2\2\2$\3\3\2\2\2%#\3\2\2\2&,\5\6\4\2\',\5\b\5\2(,\5\n\6"+
		"\2),\5\24\13\2*,\5\36\20\2+&\3\2\2\2+\'\3\2\2\2+(\3\2\2\2+)\3\2\2\2+*"+
		"\3\2\2\2,\5\3\2\2\2-\61\7\5\2\2.\60\7\n\2\2/.\3\2\2\2\60\63\3\2\2\2\61"+
		"/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\7\t\2\2\65\7"+
		"\3\2\2\2\66\67\t\2\2\2\67\t\3\2\2\28=\7\6\2\29<\7\f\2\2:<\5\f\7\2;9\3"+
		"\2\2\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7"+
		"\13\2\2A\13\3\2\2\2BC\7\r\2\2CD\7\17\2\2DE\7\16\2\2E\r\3\2\2\2FG\5\20"+
		"\t\2GH\7\23\2\2HI\5\22\n\2IL\3\2\2\2JL\5\20\t\2KF\3\2\2\2KJ\3\2\2\2L\17"+
		"\3\2\2\2MN\7\24\2\2N\21\3\2\2\2OP\7\26\2\2P\23\3\2\2\2QU\5\26\f\2RU\5"+
		"\30\r\2SU\5\32\16\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2U\25\3\2\2\2VW\7\7\2"+
		"\2W[\5\34\17\2XZ\5\16\b\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^"+
		"\3\2\2\2][\3\2\2\2^_\7\21\2\2_\27\3\2\2\2`a\7\7\2\2ab\7\22\2\2bc\5\34"+
		"\17\2cd\7\20\2\2d\31\3\2\2\2ef\7\7\2\2fj\5\34\17\2gi\5\16\b\2hg\3\2\2"+
		"\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7\20\2\2n\33\3"+
		"\2\2\2op\7\24\2\2p\35\3\2\2\2qr\7\b\2\2r\37\3\2\2\2\13#+\61;=KT[j";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}