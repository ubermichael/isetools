// Generated from grammar/ISELexer.g4 by ANTLR 4.2.2
package ca.nines.ise.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ISELexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ABBR_START=1, CHAR_START=2, ABBR_END=3, ABBR_CONTENT=4, CHAR_END=5, CHAR_NAMED_SINGLE=6, 
		CHAR_NAMED_DOUBLE=7, CHAR_TYPOGRAPHIC=8, CHAR_SPACE=9, CHAR_ACCENT=10, 
		CHAR_ALPHA=11;
	public static final int ABBR = 1;
	public static final int CHAR = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "ABBR", "CHAR"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"ABBR_START", "'{'", "ABBR_END", "ABBR_CONTENT", "'}'", "CHAR_NAMED_SINGLE", 
		"CHAR_NAMED_DOUBLE", "CHAR_TYPOGRAPHIC", "CHAR_SPACE", "CHAR_ACCENT", 
		"CHAR_ALPHA"
	};
	public static final String[] ruleNames = {
		"ABBR_START", "CHAR_START", "ABBR_END", "ABBR_CONTENT", "CHAR_END", "CHAR_NAMED_SINGLE", 
		"CHAR_NAMED_DOUBLE", "CHAR_TYPOGRAPHIC", "CHAR_SPACE", "CHAR_ACCENT", 
		"CHAR_ALPHA"
	};


	public ISELexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ISELexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\rE\b\1\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\5\b<\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\2\2\r\5\3\7\4\t\5\13"+
		"\6\r\7\17\b\21\t\23\n\25\13\27\f\31\r\5\2\3\4\b\3\2~~\4\2RRee\5\2YYtu"+
		"yy\4\2\"\"%^\6\2$$))`b\u0080\u0080\4\2C\\c|G\2\5\3\2\2\2\2\7\3\2\2\2\3"+
		"\t\3\2\2\2\3\13\3\2\2\2\4\r\3\2\2\2\4\17\3\2\2\2\4\21\3\2\2\2\4\23\3\2"+
		"\2\2\4\25\3\2\2\2\4\27\3\2\2\2\4\31\3\2\2\2\5\33\3\2\2\2\7\37\3\2\2\2"+
		"\t#\3\2\2\2\13\'\3\2\2\2\r)\3\2\2\2\17-\3\2\2\2\21;\3\2\2\2\23=\3\2\2"+
		"\2\25?\3\2\2\2\27A\3\2\2\2\31C\3\2\2\2\33\34\7~\2\2\34\35\3\2\2\2\35\36"+
		"\b\2\2\2\36\6\3\2\2\2\37 \7}\2\2 !\3\2\2\2!\"\b\3\3\2\"\b\3\2\2\2#$\7"+
		"~\2\2$%\3\2\2\2%&\b\4\4\2&\n\3\2\2\2\'(\n\2\2\2(\f\3\2\2\2)*\7\177\2\2"+
		"*+\3\2\2\2+,\b\6\4\2,\16\3\2\2\2-.\t\3\2\2.\20\3\2\2\2/\60\7u\2\2\60<"+
		"\7o\2\2\61\62\7r\2\2\62<\7k\2\2\63\64\7c\2\2\64<\7g\2\2\65\66\7C\2\2\66"+
		"<\7G\2\2\678\7q\2\28<\7g\2\29:\7Q\2\2:<\7G\2\2;/\3\2\2\2;\61\3\2\2\2;"+
		"\63\3\2\2\2;\65\3\2\2\2;\67\3\2\2\2;9\3\2\2\2<\22\3\2\2\2=>\t\4\2\2>\24"+
		"\3\2\2\2?@\t\5\2\2@\26\3\2\2\2AB\t\6\2\2B\30\3\2\2\2CD\t\7\2\2D\32\3\2"+
		"\2\2\6\2\3\4;\5\7\3\2\7\4\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}