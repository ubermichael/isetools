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
		TAG_NAME=20, TAG_EQ=19, CHAR_TYPOGRAPHIC=12, TAG_END=16, SEA_WS=2, CHAR_NAMED_DOUBLE=11, 
		CHAR_SPACE=13, TAG_SLASH=18, CHAR_NAMED_SINGLE=10, TAG_START=5, TAG_WS=21, 
		ATTRIBUTE=23, ABBR_START=3, TAG_SLASH_END=17, ATTRIBUTE_VALUE=22, CHAR_ALPHA=15, 
		TEXT=6, COMMENT=1, CHAR_ACCENT=14, ABBR_END=7, CHAR_START=4, ABBR_CONTENT=8, 
		CHAR_END=9;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT", "SEA_WS", "ABBR_START", "'{'", "'<'", "TEXT", 
		"ABBR_END", "ABBR_CONTENT", "'}'", "CHAR_NAMED_SINGLE", "CHAR_NAMED_DOUBLE", 
		"CHAR_TYPOGRAPHIC", "CHAR_SPACE", "CHAR_ACCENT", "CHAR_ALPHA", "'>'", 
		"'/>'", "'/'", "'='", "TAG_NAME", "TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE"
	};
	public static final int
		RULE_document = 0, RULE_element = 1, RULE_abbr = 2, RULE_char_named = 3, 
		RULE_char_spaced = 4, RULE_char_typographic = 5, RULE_char_accented = 6, 
		RULE_char_ligature = 7, RULE_character = 8, RULE_attribute = 9, RULE_attribute_name = 10, 
		RULE_attribute_value = 11, RULE_tag = 12, RULE_empty_tag = 13, RULE_end_tag = 14, 
		RULE_start_tag = 15, RULE_tag_name = 16, RULE_content = 17;
	public static final String[] ruleNames = {
		"document", "element", "abbr", "char_named", "char_spaced", "char_typographic", 
		"char_accented", "char_ligature", "character", "attribute", "attribute_name", 
		"attribute_value", "tag", "empty_tag", "end_tag", "start_tag", "tag_name", 
		"content"
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
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABBR_START) | (1L << CHAR_START) | (1L << TAG_START) | (1L << TEXT))) != 0)) {
				{
				{
				setState(36); element();
				}
				}
				setState(41);
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
			setState(46);
			switch (_input.LA(1)) {
			case ABBR_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(42); abbr();
				}
				break;
			case CHAR_START:
				enterOuterAlt(_localctx, 2);
				{
				setState(43); character();
				}
				break;
			case TAG_START:
				enterOuterAlt(_localctx, 3);
				{
				setState(44); tag();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(45); content();
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
			setState(48); match(ABBR_START);
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ABBR_CONTENT) {
				{
				{
				setState(49); match(ABBR_CONTENT);
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55); match(ABBR_END);
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

	public static class Char_namedContext extends ParserRuleContext {
		public TerminalNode CHAR_NAMED_DOUBLE() { return getToken(ISEParser.CHAR_NAMED_DOUBLE, 0); }
		public TerminalNode CHAR_NAMED_SINGLE() { return getToken(ISEParser.CHAR_NAMED_SINGLE, 0); }
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public Char_namedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_char_named; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterChar_named(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitChar_named(this);
		}
	}

	public final Char_namedContext char_named() throws RecognitionException {
		Char_namedContext _localctx = new Char_namedContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_char_named);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); match(CHAR_START);
			setState(58);
			_la = _input.LA(1);
			if ( !(_la==CHAR_NAMED_SINGLE || _la==CHAR_NAMED_DOUBLE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(59); match(CHAR_END);
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

	public static class Char_spacedContext extends ParserRuleContext {
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public TerminalNode CHAR_SPACE() { return getToken(ISEParser.CHAR_SPACE, 0); }
		public Char_spacedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_char_spaced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterChar_spaced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitChar_spaced(this);
		}
	}

	public final Char_spacedContext char_spaced() throws RecognitionException {
		Char_spacedContext _localctx = new Char_spacedContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_char_spaced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); match(CHAR_START);
			setState(62); match(CHAR_SPACE);
			setState(63); match(CHAR_END);
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

	public static class Char_typographicContext extends ParserRuleContext {
		public TerminalNode CHAR_TYPOGRAPHIC() { return getToken(ISEParser.CHAR_TYPOGRAPHIC, 0); }
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public Char_typographicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_char_typographic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterChar_typographic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitChar_typographic(this);
		}
	}

	public final Char_typographicContext char_typographic() throws RecognitionException {
		Char_typographicContext _localctx = new Char_typographicContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_char_typographic);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); match(CHAR_START);
			setState(66); match(CHAR_TYPOGRAPHIC);
			setState(67); match(CHAR_END);
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

	public static class Char_accentedContext extends ParserRuleContext {
		public TerminalNode CHAR_ACCENT() { return getToken(ISEParser.CHAR_ACCENT, 0); }
		public TerminalNode CHAR_TYPOGRAPHIC() { return getToken(ISEParser.CHAR_TYPOGRAPHIC, 0); }
		public TerminalNode CHAR_NAMED_SINGLE() { return getToken(ISEParser.CHAR_NAMED_SINGLE, 0); }
		public TerminalNode CHAR_ALPHA() { return getToken(ISEParser.CHAR_ALPHA, 0); }
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public Char_accentedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_char_accented; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterChar_accented(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitChar_accented(this);
		}
	}

	public final Char_accentedContext char_accented() throws RecognitionException {
		Char_accentedContext _localctx = new Char_accentedContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_char_accented);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); match(CHAR_START);
			setState(70); match(CHAR_ACCENT);
			setState(71);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CHAR_NAMED_SINGLE) | (1L << CHAR_TYPOGRAPHIC) | (1L << CHAR_ALPHA))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(72); match(CHAR_END);
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

	public static class Char_ligatureContext extends ParserRuleContext {
		public List<TerminalNode> CHAR_ALPHA() { return getTokens(ISEParser.CHAR_ALPHA); }
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public TerminalNode CHAR_ALPHA(int i) {
			return getToken(ISEParser.CHAR_ALPHA, i);
		}
		public Char_ligatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_char_ligature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterChar_ligature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitChar_ligature(this);
		}
	}

	public final Char_ligatureContext char_ligature() throws RecognitionException {
		Char_ligatureContext _localctx = new Char_ligatureContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_char_ligature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74); match(CHAR_START);
			setState(76); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(75); match(CHAR_ALPHA);
				}
				}
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CHAR_ALPHA );
			setState(80); match(CHAR_END);
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
		public Char_accentedContext char_accented() {
			return getRuleContext(Char_accentedContext.class,0);
		}
		public Char_spacedContext char_spaced() {
			return getRuleContext(Char_spacedContext.class,0);
		}
		public Char_ligatureContext char_ligature() {
			return getRuleContext(Char_ligatureContext.class,0);
		}
		public Char_typographicContext char_typographic() {
			return getRuleContext(Char_typographicContext.class,0);
		}
		public Char_namedContext char_named() {
			return getRuleContext(Char_namedContext.class,0);
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
		enterRule(_localctx, 16, RULE_character);
		try {
			setState(87);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(82); char_named();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(83); char_spaced();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(84); char_accented();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(85); char_typographic();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(86); char_ligature();
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
		enterRule(_localctx, 18, RULE_attribute);
		try {
			setState(94);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(89); attribute_name();
				setState(90); match(TAG_EQ);
				setState(91); attribute_value();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93); attribute_name();
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
		enterRule(_localctx, 20, RULE_attribute_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(TAG_NAME);
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
		enterRule(_localctx, 22, RULE_attribute_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); match(ATTRIBUTE_VALUE);
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
		enterRule(_localctx, 24, RULE_tag);
		try {
			setState(103);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100); empty_tag();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(101); end_tag();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(102); start_tag();
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
		enterRule(_localctx, 26, RULE_empty_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); match(TAG_START);
			setState(106); tag_name();
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TAG_NAME) {
				{
				{
				setState(107); attribute();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(113); match(TAG_SLASH_END);
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
		enterRule(_localctx, 28, RULE_end_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(TAG_START);
			setState(116); match(TAG_SLASH);
			setState(117); tag_name();
			setState(118); match(TAG_END);
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
		enterRule(_localctx, 30, RULE_start_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(TAG_START);
			setState(121); tag_name();
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TAG_NAME) {
				{
				{
				setState(122); attribute();
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128); match(TAG_END);
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
		enterRule(_localctx, 32, RULE_tag_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(TAG_NAME);
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
		enterRule(_localctx, 34, RULE_content);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132); match(TEXT);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\31\u0089\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\7\2(\n\2\f\2\16\2+\13\2\3\3\3\3\3\3\3\3\5\3\61\n\3\3\4"+
		"\3\4\7\4\65\n\4\f\4\16\48\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\6\tO\n\t\r\t\16\tP\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\5\nZ\n\n\3\13\3\13\3\13\3\13\3\13\5\13a\n\13\3"+
		"\f\3\f\3\r\3\r\3\16\3\16\3\16\5\16j\n\16\3\17\3\17\3\17\7\17o\n\17\f\17"+
		"\16\17r\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\7\21~"+
		"\n\21\f\21\16\21\u0081\13\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\2\2\24"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\4\3\2\f\r\5\2\f\f\16\16"+
		"\21\21\u0085\2)\3\2\2\2\4\60\3\2\2\2\6\62\3\2\2\2\b;\3\2\2\2\n?\3\2\2"+
		"\2\fC\3\2\2\2\16G\3\2\2\2\20L\3\2\2\2\22Y\3\2\2\2\24`\3\2\2\2\26b\3\2"+
		"\2\2\30d\3\2\2\2\32i\3\2\2\2\34k\3\2\2\2\36u\3\2\2\2 z\3\2\2\2\"\u0084"+
		"\3\2\2\2$\u0086\3\2\2\2&(\5\4\3\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3"+
		"\2\2\2*\3\3\2\2\2+)\3\2\2\2,\61\5\6\4\2-\61\5\22\n\2.\61\5\32\16\2/\61"+
		"\5$\23\2\60,\3\2\2\2\60-\3\2\2\2\60.\3\2\2\2\60/\3\2\2\2\61\5\3\2\2\2"+
		"\62\66\7\5\2\2\63\65\7\n\2\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66"+
		"\67\3\2\2\2\679\3\2\2\28\66\3\2\2\29:\7\t\2\2:\7\3\2\2\2;<\7\6\2\2<=\t"+
		"\2\2\2=>\7\13\2\2>\t\3\2\2\2?@\7\6\2\2@A\7\17\2\2AB\7\13\2\2B\13\3\2\2"+
		"\2CD\7\6\2\2DE\7\16\2\2EF\7\13\2\2F\r\3\2\2\2GH\7\6\2\2HI\7\20\2\2IJ\t"+
		"\3\2\2JK\7\13\2\2K\17\3\2\2\2LN\7\6\2\2MO\7\21\2\2NM\3\2\2\2OP\3\2\2\2"+
		"PN\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\13\2\2S\21\3\2\2\2TZ\5\b\5\2UZ\5\n"+
		"\6\2VZ\5\16\b\2WZ\5\f\7\2XZ\5\20\t\2YT\3\2\2\2YU\3\2\2\2YV\3\2\2\2YW\3"+
		"\2\2\2YX\3\2\2\2Z\23\3\2\2\2[\\\5\26\f\2\\]\7\25\2\2]^\5\30\r\2^a\3\2"+
		"\2\2_a\5\26\f\2`[\3\2\2\2`_\3\2\2\2a\25\3\2\2\2bc\7\26\2\2c\27\3\2\2\2"+
		"de\7\30\2\2e\31\3\2\2\2fj\5\34\17\2gj\5\36\20\2hj\5 \21\2if\3\2\2\2ig"+
		"\3\2\2\2ih\3\2\2\2j\33\3\2\2\2kl\7\7\2\2lp\5\"\22\2mo\5\24\13\2nm\3\2"+
		"\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2\2\2rp\3\2\2\2st\7\23\2\2t\35"+
		"\3\2\2\2uv\7\7\2\2vw\7\24\2\2wx\5\"\22\2xy\7\22\2\2y\37\3\2\2\2z{\7\7"+
		"\2\2{\177\5\"\22\2|~\5\24\13\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177"+
		"\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7\22"+
		"\2\2\u0083!\3\2\2\2\u0084\u0085\7\26\2\2\u0085#\3\2\2\2\u0086\u0087\7"+
		"\b\2\2\u0087%\3\2\2\2\13)\60\66PY`ip\177";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}