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
		ABBR_START=1, CHAR_TYPOGRAPHIC=7, CHAR_NAMED=6, CHAR_ALPHA=10, CHAR_SPACE=8, 
		CHAR_ACCENT=9, ABBR_END=3, CHAR_END=5, ABBR_CONTENT=4, CHAR_START=2;
	public static final String[] tokenNames = {
		"<INVALID>", "ABBR_START", "'{'", "ABBR_END", "ABBR_CONTENT", "'}'", "CHAR_NAMED", 
		"CHAR_TYPOGRAPHIC", "CHAR_SPACE", "CHAR_ACCENT", "CHAR_ALPHA"
	};
	public static final int
		RULE_document = 0, RULE_element = 1, RULE_abbr = 2, RULE_char_accented = 3, 
		RULE_char_named = 4, RULE_char_spaced = 5, RULE_char_typographic = 6, 
		RULE_char_ligature = 7, RULE_character = 8;
	public static final String[] ruleNames = {
		"document", "element", "abbr", "char_accented", "char_named", "char_spaced", 
		"char_typographic", "char_ligature", "character"
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
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ABBR_START || _la==CHAR_START) {
				{
				{
				setState(18); element();
				}
				}
				setState(23);
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
			setState(26);
			switch (_input.LA(1)) {
			case ABBR_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(24); abbr();
				}
				break;
			case CHAR_START:
				enterOuterAlt(_localctx, 2);
				{
				setState(25); character();
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
			setState(28); match(ABBR_START);
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ABBR_CONTENT) {
				{
				{
				setState(29); match(ABBR_CONTENT);
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(35); match(ABBR_END);
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
		enterRule(_localctx, 6, RULE_char_accented);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); match(CHAR_ACCENT);
			setState(38); match(CHAR_ALPHA);
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
		enterRule(_localctx, 8, RULE_char_named);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); match(CHAR_NAMED);
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
		enterRule(_localctx, 10, RULE_char_spaced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42); match(CHAR_SPACE);
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
			setState(44); match(CHAR_TYPOGRAPHIC);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); match(CHAR_ALPHA);
			setState(47); match(CHAR_ALPHA);
			setState(48); match(CHAR_ALPHA);
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
		public TerminalNode CHAR_END() { return getToken(ISEParser.CHAR_END, 0); }
		public TerminalNode CHAR_START() { return getToken(ISEParser.CHAR_START, 0); }
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
			enterOuterAlt(_localctx, 1);
			{
			setState(50); match(CHAR_START);
			setState(56);
			switch (_input.LA(1)) {
			case CHAR_NAMED:
				{
				setState(51); char_named();
				}
				break;
			case CHAR_SPACE:
				{
				setState(52); char_spaced();
				}
				break;
			case CHAR_ACCENT:
				{
				setState(53); char_accented();
				}
				break;
			case CHAR_TYPOGRAPHIC:
				{
				setState(54); char_typographic();
				}
				break;
			case CHAR_ALPHA:
				{
				setState(55); char_ligature();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\f?\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\7\2\26"+
		"\n\2\f\2\16\2\31\13\2\3\3\3\3\5\3\35\n\3\3\4\3\4\7\4!\n\4\f\4\16\4$\13"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\5\n;\n\n\3\n\3\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\2"+
		"<\2\27\3\2\2\2\4\34\3\2\2\2\6\36\3\2\2\2\b\'\3\2\2\2\n*\3\2\2\2\f,\3\2"+
		"\2\2\16.\3\2\2\2\20\60\3\2\2\2\22\64\3\2\2\2\24\26\5\4\3\2\25\24\3\2\2"+
		"\2\26\31\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30\3\3\2\2\2\31\27\3\2\2"+
		"\2\32\35\5\6\4\2\33\35\5\22\n\2\34\32\3\2\2\2\34\33\3\2\2\2\35\5\3\2\2"+
		"\2\36\"\7\3\2\2\37!\7\6\2\2 \37\3\2\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2"+
		"\2#%\3\2\2\2$\"\3\2\2\2%&\7\5\2\2&\7\3\2\2\2\'(\7\13\2\2()\7\f\2\2)\t"+
		"\3\2\2\2*+\7\b\2\2+\13\3\2\2\2,-\7\n\2\2-\r\3\2\2\2./\7\t\2\2/\17\3\2"+
		"\2\2\60\61\7\f\2\2\61\62\7\f\2\2\62\63\7\f\2\2\63\21\3\2\2\2\64:\7\4\2"+
		"\2\65;\5\n\6\2\66;\5\f\7\2\67;\5\b\5\28;\5\16\b\29;\5\20\t\2:\65\3\2\2"+
		"\2:\66\3\2\2\2:\67\3\2\2\2:8\3\2\2\2:9\3\2\2\2;<\3\2\2\2<=\7\7\2\2=\23"+
		"\3\2\2\2\6\27\34\":";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}