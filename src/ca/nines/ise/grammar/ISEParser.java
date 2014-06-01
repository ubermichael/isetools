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
		CHAR_TYPOGRAPHIC=14, ATTR_NAME=20, CHAR_SPACE=11, SQ_STRING=25, CHAR_NAMED=10, 
		FQ_STRING=27, TAG_SLASH_END=18, DQ=23, CHAR_ALPHA=13, S=6, FQ=24, TEXT=8, 
		ABBR_DELIM=1, CHAR_ACCENT=12, UQ_STRING=28, ALPHANUM=5, ALPHA=4, CHAR_START=2, 
		DQ_STRING=26, TAG_NAME=19, TAG_END=16, TAG_SLASH=17, TAG_START=3, CHAR_LIGATURE=15, 
		SQ=22, ATTR_VALUE=21, EQ=7, STRING=29, CHAR_END=9;
	public static final String[] tokenNames = {
		"<INVALID>", "'|'", "'{'", "'<'", "ALPHA", "ALPHANUM", "S", "'='", "TEXT", 
		"'}'", "CHAR_NAMED", "CHAR_SPACE", "CHAR_ACCENT", "CHAR_ALPHA", "CHAR_TYPOGRAPHIC", 
		"CHAR_LIGATURE", "'>'", "'/'", "'/>'", "TAG_NAME", "ATTR_NAME", "ATTR_VALUE", 
		"'''", "'\"'", "FQ", "SQ_STRING", "DQ_STRING", "FQ_STRING", "UQ_STRING", 
		"STRING"
	};
	public static final int
		RULE_document = 0, RULE_element = 1, RULE_abbr = 2, RULE_char_named = 3, 
		RULE_char_spaced = 4, RULE_char_accented = 5, RULE_char_typographic = 6, 
		RULE_char_ligature = 7, RULE_character = 8, RULE_start_tag = 9, RULE_empty_tag = 10, 
		RULE_end_tag = 11, RULE_short_tag = 12, RULE_tag = 13, RULE_attribute_name = 14, 
		RULE_attribute_value = 15, RULE_attribute = 16, RULE_content = 17;
	public static final String[] ruleNames = {
		"document", "element", "abbr", "char_named", "char_spaced", "char_accented", 
		"char_typographic", "char_ligature", "character", "start_tag", "empty_tag", 
		"end_tag", "short_tag", "tag", "attribute_name", "attribute_value", "attribute", 
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABBR_DELIM) | (1L << CHAR_START) | (1L << TEXT))) != 0)) {
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
			setState(45);
			switch (_input.LA(1)) {
			case ABBR_DELIM:
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
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(44); content();
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
		public TerminalNode ABBR_DELIM(int i) {
			return getToken(ISEParser.ABBR_DELIM, i);
		}
		public List<TerminalNode> ABBR_DELIM() { return getTokens(ISEParser.ABBR_DELIM); }
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(47); match(ABBR_DELIM);
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=1 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(48);
					matchWildcard();
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(54); match(ABBR_DELIM);
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
		public TerminalNode CHAR_NAMED() { return getToken(ISEParser.CHAR_NAMED, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56); match(CHAR_START);
			setState(57); match(CHAR_NAMED);
			setState(58); match(CHAR_END);
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
			setState(60); match(CHAR_START);
			setState(61); match(CHAR_SPACE);
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

	public static class Char_accentedContext extends ParserRuleContext {
		public TerminalNode CHAR_ACCENT() { return getToken(ISEParser.CHAR_ACCENT, 0); }
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
		enterRule(_localctx, 10, RULE_char_accented);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); match(CHAR_START);
			setState(65); match(CHAR_ACCENT);
			setState(66); match(CHAR_ALPHA);
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
		enterRule(_localctx, 12, RULE_char_typographic);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); match(CHAR_START);
			setState(70); match(CHAR_TYPOGRAPHIC);
			setState(71); match(CHAR_END);
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
		public CharacterContext character(int i) {
			return getRuleContext(CharacterContext.class,i);
		}
		public List<TerminalNode> CHAR_LIGATURE() { return getTokens(ISEParser.CHAR_LIGATURE); }
		public List<CharacterContext> character() {
			return getRuleContexts(CharacterContext.class);
		}
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
		public TerminalNode CHAR_LIGATURE(int i) {
			return getToken(ISEParser.CHAR_LIGATURE, i);
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
			setState(73); match(CHAR_START);
			setState(76); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(76);
				switch (_input.LA(1)) {
				case CHAR_LIGATURE:
					{
					setState(74); match(CHAR_LIGATURE);
					}
					break;
				case CHAR_START:
					{
					setState(75); character();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CHAR_START || _la==CHAR_LIGATURE );
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
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
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

	public static class Start_tagContext extends ParserRuleContext {
		public List<TerminalNode> S() { return getTokens(ISEParser.S); }
		public TerminalNode TAG_NAME() { return getToken(ISEParser.TAG_NAME, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode TAG_END() { return getToken(ISEParser.TAG_END, 0); }
		public TerminalNode TAG_START() { return getToken(ISEParser.TAG_START, 0); }
		public TerminalNode S(int i) {
			return getToken(ISEParser.S, i);
		}
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
		enterRule(_localctx, 18, RULE_start_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); match(TAG_START);
			setState(90); match(TAG_NAME);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==S) {
				{
				{
				setState(91); match(S);
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ATTR_NAME) {
				{
				{
				setState(97); attribute();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103); match(TAG_END);
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
		public List<TerminalNode> S() { return getTokens(ISEParser.S); }
		public TerminalNode TAG_SLASH_END() { return getToken(ISEParser.TAG_SLASH_END, 0); }
		public TerminalNode TAG_NAME() { return getToken(ISEParser.TAG_NAME, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode TAG_START() { return getToken(ISEParser.TAG_START, 0); }
		public TerminalNode S(int i) {
			return getToken(ISEParser.S, i);
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
		enterRule(_localctx, 20, RULE_empty_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); match(TAG_START);
			setState(106); match(TAG_NAME);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==S) {
				{
				{
				setState(107); match(S);
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ATTR_NAME) {
				{
				{
				setState(113); attribute();
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119); match(TAG_SLASH_END);
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
		public TerminalNode TAG_NAME() { return getToken(ISEParser.TAG_NAME, 0); }
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
			setState(121); match(TAG_START);
			setState(122); match(TAG_SLASH);
			setState(123); match(TAG_NAME);
			setState(124); match(TAG_END);
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

	public static class Short_tagContext extends ParserRuleContext {
		public List<TerminalNode> S() { return getTokens(ISEParser.S); }
		public TerminalNode TAG_NAME() { return getToken(ISEParser.TAG_NAME, 0); }
		public TerminalNode ATTR_VALUE() { return getToken(ISEParser.ATTR_VALUE, 0); }
		public TerminalNode TAG_END() { return getToken(ISEParser.TAG_END, 0); }
		public TerminalNode TAG_START() { return getToken(ISEParser.TAG_START, 0); }
		public TerminalNode S(int i) {
			return getToken(ISEParser.S, i);
		}
		public TerminalNode EQ() { return getToken(ISEParser.EQ, 0); }
		public Short_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).enterShort_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ISEParserListener ) ((ISEParserListener)listener).exitShort_tag(this);
		}
	}

	public final Short_tagContext short_tag() throws RecognitionException {
		Short_tagContext _localctx = new Short_tagContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_short_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); match(TAG_START);
			setState(127); match(TAG_NAME);
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==S) {
				{
				{
				setState(128); match(S);
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134); match(EQ);
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==S) {
				{
				{
				setState(135); match(S);
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(141); match(ATTR_VALUE);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==S) {
				{
				{
				setState(142); match(S);
				}
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148); match(TAG_END);
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
		public Short_tagContext short_tag() {
			return getRuleContext(Short_tagContext.class,0);
		}
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
		enterRule(_localctx, 26, RULE_tag);
		try {
			setState(154);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150); start_tag();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(151); empty_tag();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(152); end_tag();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(153); short_tag();
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
		public TerminalNode ATTR_NAME() { return getToken(ISEParser.ATTR_NAME, 0); }
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
		enterRule(_localctx, 28, RULE_attribute_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156); match(ATTR_NAME);
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
		public TerminalNode ATTR_VALUE() { return getToken(ISEParser.ATTR_VALUE, 0); }
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
		enterRule(_localctx, 30, RULE_attribute_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(ATTR_VALUE);
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
		public List<TerminalNode> S() { return getTokens(ISEParser.S); }
		public Attribute_valueContext attribute_value() {
			return getRuleContext(Attribute_valueContext.class,0);
		}
		public Attribute_nameContext attribute_name() {
			return getRuleContext(Attribute_nameContext.class,0);
		}
		public TerminalNode S(int i) {
			return getToken(ISEParser.S, i);
		}
		public TerminalNode EQ() { return getToken(ISEParser.EQ, 0); }
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
		enterRule(_localctx, 32, RULE_attribute);
		int _la;
		try {
			setState(177);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(160); attribute_name();
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==S) {
					{
					{
					setState(161); match(S);
					}
					}
					setState(166);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(167); match(EQ);
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==S) {
					{
					{
					setState(168); match(S);
					}
					}
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(174); attribute_value();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(176); attribute_name();
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
			setState(179); match(TEXT);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u00b8\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\7\2(\n\2\f\2\16\2+\13\2\3\3\3\3\3\3\5\3\60\n\3\3\4\3\4"+
		"\7\4\64\n\4\f\4\16\4\67\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\6\tO\n\t\r\t\16\tP\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\5\nZ\n\n\3\13\3\13\3\13\7\13_\n\13\f\13\16\13"+
		"b\13\13\3\13\7\13e\n\13\f\13\16\13h\13\13\3\13\3\13\3\f\3\f\3\f\7\fo\n"+
		"\f\f\f\16\fr\13\f\3\f\7\fu\n\f\f\f\16\fx\13\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\7\16\u0084\n\16\f\16\16\16\u0087\13\16\3\16\3\16\7"+
		"\16\u008b\n\16\f\16\16\16\u008e\13\16\3\16\3\16\7\16\u0092\n\16\f\16\16"+
		"\16\u0095\13\16\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u009d\n\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\7\22\u00a5\n\22\f\22\16\22\u00a8\13\22\3\22\3\22"+
		"\7\22\u00ac\n\22\f\22\16\22\u00af\13\22\3\22\3\22\3\22\5\22\u00b4\n\22"+
		"\3\23\3\23\3\23\3\65\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2"+
		"\2\u00bc\2)\3\2\2\2\4/\3\2\2\2\6\61\3\2\2\2\b:\3\2\2\2\n>\3\2\2\2\fB\3"+
		"\2\2\2\16G\3\2\2\2\20K\3\2\2\2\22Y\3\2\2\2\24[\3\2\2\2\26k\3\2\2\2\30"+
		"{\3\2\2\2\32\u0080\3\2\2\2\34\u009c\3\2\2\2\36\u009e\3\2\2\2 \u00a0\3"+
		"\2\2\2\"\u00b3\3\2\2\2$\u00b5\3\2\2\2&(\5\4\3\2\'&\3\2\2\2(+\3\2\2\2)"+
		"\'\3\2\2\2)*\3\2\2\2*\3\3\2\2\2+)\3\2\2\2,\60\5\6\4\2-\60\5\22\n\2.\60"+
		"\5$\23\2/,\3\2\2\2/-\3\2\2\2/.\3\2\2\2\60\5\3\2\2\2\61\65\7\3\2\2\62\64"+
		"\13\2\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\66\3\2\2\2\65\63\3\2\2\2\668"+
		"\3\2\2\2\67\65\3\2\2\289\7\3\2\29\7\3\2\2\2:;\7\4\2\2;<\7\f\2\2<=\7\13"+
		"\2\2=\t\3\2\2\2>?\7\4\2\2?@\7\r\2\2@A\7\13\2\2A\13\3\2\2\2BC\7\4\2\2C"+
		"D\7\16\2\2DE\7\17\2\2EF\7\13\2\2F\r\3\2\2\2GH\7\4\2\2HI\7\20\2\2IJ\7\13"+
		"\2\2J\17\3\2\2\2KN\7\4\2\2LO\7\21\2\2MO\5\22\n\2NL\3\2\2\2NM\3\2\2\2O"+
		"P\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\13\2\2S\21\3\2\2\2TZ\5\b\5"+
		"\2UZ\5\n\6\2VZ\5\f\7\2WZ\5\16\b\2XZ\5\20\t\2YT\3\2\2\2YU\3\2\2\2YV\3\2"+
		"\2\2YW\3\2\2\2YX\3\2\2\2Z\23\3\2\2\2[\\\7\5\2\2\\`\7\25\2\2]_\7\b\2\2"+
		"^]\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2af\3\2\2\2b`\3\2\2\2ce\5\"\22"+
		"\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\7\22"+
		"\2\2j\25\3\2\2\2kl\7\5\2\2lp\7\25\2\2mo\7\b\2\2nm\3\2\2\2or\3\2\2\2pn"+
		"\3\2\2\2pq\3\2\2\2qv\3\2\2\2rp\3\2\2\2su\5\"\22\2ts\3\2\2\2ux\3\2\2\2"+
		"vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7\24\2\2z\27\3\2\2\2{|\7\5"+
		"\2\2|}\7\23\2\2}~\7\25\2\2~\177\7\22\2\2\177\31\3\2\2\2\u0080\u0081\7"+
		"\5\2\2\u0081\u0085\7\25\2\2\u0082\u0084\7\b\2\2\u0083\u0082\3\2\2\2\u0084"+
		"\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2"+
		"\2\2\u0087\u0085\3\2\2\2\u0088\u008c\7\t\2\2\u0089\u008b\7\b\2\2\u008a"+
		"\u0089\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2"+
		"\2\2\u008d\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0093\7\27\2\2\u0090"+
		"\u0092\7\b\2\2\u0091\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096"+
		"\u0097\7\22\2\2\u0097\33\3\2\2\2\u0098\u009d\5\24\13\2\u0099\u009d\5\26"+
		"\f\2\u009a\u009d\5\30\r\2\u009b\u009d\5\32\16\2\u009c\u0098\3\2\2\2\u009c"+
		"\u0099\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d\35\3\2\2"+
		"\2\u009e\u009f\7\26\2\2\u009f\37\3\2\2\2\u00a0\u00a1\7\27\2\2\u00a1!\3"+
		"\2\2\2\u00a2\u00a6\5\36\20\2\u00a3\u00a5\7\b\2\2\u00a4\u00a3\3\2\2\2\u00a5"+
		"\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2"+
		"\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00ad\7\t\2\2\u00aa\u00ac\7\b\2\2\u00ab"+
		"\u00aa\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00b0\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\5 \21\2\u00b1"+
		"\u00b4\3\2\2\2\u00b2\u00b4\5\36\20\2\u00b3\u00a2\3\2\2\2\u00b3\u00b2\3"+
		"\2\2\2\u00b4#\3\2\2\2\u00b5\u00b6\7\n\2\2\u00b6%\3\2\2\2\23)/\65NPY`f"+
		"pv\u0085\u008c\u0093\u009c\u00a6\u00ad\u00b3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}