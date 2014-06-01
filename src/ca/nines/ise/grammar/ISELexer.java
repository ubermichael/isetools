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
		ABBR_DELIM=1, CHAR_START=2, CHAR_END=3, CHAR_NAMED=4, CHAR_SPACE=5, CHAR_ACCENT=6, 
		CHAR_TYPOGRAPHIC=7, CHAR_LIGATURE=8, ALPHA=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'|'", "'{'", "'}'", "CHAR_NAMED", "CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", 
		"CHAR_LIGATURE", "ALPHA"
	};
	public static final String[] ruleNames = {
		"ABBR_DELIM", "CHAR_START", "CHAR_END", "CHAR_NAMED", "CHAR_SPACE", "CHAR_ACCENT", 
		"CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", "ALPHA"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\13:\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5)\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\5\t\64\n\t\3\n\6\n\67\n\n"+
		"\r\n\16\n8\2\2\13\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\3\2\7\4\2R"+
		"Ree\4\2\"\"%^\6\2$$))`b\u0080\u0080\5\2YYtuyy\4\2C\\c|A\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\3\25\3\2\2\2\5\27\3\2\2\2\7\31\3\2\2\2\t"+
		"(\3\2\2\2\13*\3\2\2\2\r,\3\2\2\2\17.\3\2\2\2\21\60\3\2\2\2\23\66\3\2\2"+
		"\2\25\26\7~\2\2\26\4\3\2\2\2\27\30\7}\2\2\30\6\3\2\2\2\31\32\7\177\2\2"+
		"\32\b\3\2\2\2\33)\t\2\2\2\34\35\7u\2\2\35)\7o\2\2\36\37\7r\2\2\37)\7k"+
		"\2\2 !\7c\2\2!)\7g\2\2\"#\7C\2\2#)\7G\2\2$%\7q\2\2%)\7g\2\2&\'\7Q\2\2"+
		"\')\7G\2\2(\33\3\2\2\2(\34\3\2\2\2(\36\3\2\2\2( \3\2\2\2(\"\3\2\2\2($"+
		"\3\2\2\2(&\3\2\2\2)\n\3\2\2\2*+\t\3\2\2+\f\3\2\2\2,-\t\4\2\2-\16\3\2\2"+
		"\2./\t\5\2\2/\20\3\2\2\2\60\61\5\23\n\2\61\63\5\23\n\2\62\64\5\23\n\2"+
		"\63\62\3\2\2\2\63\64\3\2\2\2\64\22\3\2\2\2\65\67\t\6\2\2\66\65\3\2\2\2"+
		"\678\3\2\2\28\66\3\2\2\289\3\2\2\29\24\3\2\2\2\6\2(\638\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}