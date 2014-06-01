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
		ABBR_DELIM=1, CHAR_START=2, TAG_START=3, ALPHA=4, ALPHANUM=5, S=6, EQ=7, 
		TEXT=8, CHAR_END=9, CHAR_NAMED=10, CHAR_SPACE=11, CHAR_ACCENT=12, CHAR_ALPHA=13, 
		CHAR_TYPOGRAPHIC=14, CHAR_LIGATURE=15, TAG_END=16, TAG_SLASH=17, TAG_SLASH_END=18, 
		TAG_NAME=19, ATTR_NAME=20, ATTR_VALUE=21, SQ=22, DQ=23, FQ=24, SQ_STRING=25, 
		DQ_STRING=26, FQ_STRING=27, UQ_STRING=28, STRING=29;
	public static final int CHAR = 1;
	public static final int TAG = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "CHAR", "TAG"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'|'", "'{'", "'<'", "ALPHA", "ALPHANUM", "S", "'='", "TEXT", "'}'", "CHAR_NAMED", 
		"CHAR_SPACE", "CHAR_ACCENT", "CHAR_ALPHA", "CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", 
		"'>'", "'/'", "'/>'", "TAG_NAME", "ATTR_NAME", "ATTR_VALUE", "'''", "'\"'", 
		"FQ", "SQ_STRING", "DQ_STRING", "FQ_STRING", "UQ_STRING", "STRING"
	};
	public static final String[] ruleNames = {
		"ABBR_DELIM", "CHAR_START", "TAG_START", "ALPHA", "ALPHANUM", "S", "EQ", 
		"TEXT", "CHAR_END", "CHAR_NAMED", "CHAR_SPACE", "CHAR_ACCENT", "CHAR_ALPHA", 
		"CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", "TAG_END", "TAG_SLASH", "TAG_SLASH_END", 
		"TAG_NAME", "ATTR_NAME", "ATTR_VALUE", "SQ", "DQ", "FQ", "SQ_STRING", 
		"DQ_STRING", "FQ_STRING", "UQ_STRING", "STRING"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\37\u00bf\b\1\b\1"+
		"\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4"+
		"\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t"+
		"\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t"+
		"\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\6\tS\n\t\r\t\16\tT\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13h\n\13\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\17\3\17\3\20\3\20\3\20\5\20u\n\20\3\21\3\21\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\6\24\u0083\n\24\r\24\16\24\u0084\3\25\6\25\u0088"+
		"\n\25\r\25\16\25\u0089\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\5\31\u0098\n\31\3\32\3\32\7\32\u009c\n\32\f\32\16\32\u009f"+
		"\13\32\3\32\3\32\3\33\3\33\7\33\u00a5\n\33\f\33\16\33\u00a8\13\33\3\33"+
		"\3\33\3\34\3\34\7\34\u00ae\n\34\f\34\16\34\u00b1\13\34\3\34\3\34\3\35"+
		"\6\35\u00b6\n\35\r\35\16\35\u00b7\3\36\3\36\3\36\3\36\5\36\u00be\n\36"+
		"\2\2\37\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23\n\25\13\27\f\31\r\33\16\35\17"+
		"\37\20!\21#\22%\23\'\24)\25+\26-\27/\30\61\31\63\32\65\33\67\349\35;\36"+
		"=\37\5\2\3\4\r\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\4\2>>}~\4\2RR"+
		"ee\4\2\"\"%^\6\2$$))`b\u0080\u0080\5\2YYtuyy\3\2))\3\2$$\5\2$$))\uffff"+
		"\uffff\u00ce\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3\25\3\2\2\2\3\27\3\2\2\2"+
		"\3\31\3\2\2\2\3\33\3\2\2\2\3\35\3\2\2\2\3\37\3\2\2\2\3!\3\2\2\2\4#\3\2"+
		"\2\2\4%\3\2\2\2\4\'\3\2\2\2\4)\3\2\2\2\4+\3\2\2\2\4-\3\2\2\2\4/\3\2\2"+
		"\2\4\61\3\2\2\2\4\63\3\2\2\2\4\65\3\2\2\2\4\67\3\2\2\2\49\3\2\2\2\4;\3"+
		"\2\2\2\4=\3\2\2\2\5?\3\2\2\2\7A\3\2\2\2\tE\3\2\2\2\13I\3\2\2\2\rK\3\2"+
		"\2\2\17M\3\2\2\2\21O\3\2\2\2\23R\3\2\2\2\25V\3\2\2\2\27g\3\2\2\2\31i\3"+
		"\2\2\2\33k\3\2\2\2\35m\3\2\2\2\37o\3\2\2\2!q\3\2\2\2#v\3\2\2\2%z\3\2\2"+
		"\2\'|\3\2\2\2)\u0082\3\2\2\2+\u0087\3\2\2\2-\u008b\3\2\2\2/\u008d\3\2"+
		"\2\2\61\u008f\3\2\2\2\63\u0097\3\2\2\2\65\u0099\3\2\2\2\67\u00a2\3\2\2"+
		"\29\u00ab\3\2\2\2;\u00b5\3\2\2\2=\u00bd\3\2\2\2?@\7~\2\2@\6\3\2\2\2AB"+
		"\7}\2\2BC\3\2\2\2CD\b\3\2\2D\b\3\2\2\2EF\7>\2\2FG\3\2\2\2GH\b\4\3\2H\n"+
		"\3\2\2\2IJ\t\2\2\2J\f\3\2\2\2KL\t\3\2\2L\16\3\2\2\2MN\t\4\2\2N\20\3\2"+
		"\2\2OP\7?\2\2P\22\3\2\2\2QS\n\5\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3"+
		"\2\2\2U\24\3\2\2\2VW\7\177\2\2WX\3\2\2\2XY\b\n\4\2Y\26\3\2\2\2Zh\t\6\2"+
		"\2[\\\7u\2\2\\h\7o\2\2]^\7r\2\2^h\7k\2\2_`\7c\2\2`h\7g\2\2ab\7C\2\2bh"+
		"\7G\2\2cd\7q\2\2dh\7g\2\2ef\7Q\2\2fh\7G\2\2gZ\3\2\2\2g[\3\2\2\2g]\3\2"+
		"\2\2g_\3\2\2\2ga\3\2\2\2gc\3\2\2\2ge\3\2\2\2h\30\3\2\2\2ij\t\7\2\2j\32"+
		"\3\2\2\2kl\t\b\2\2l\34\3\2\2\2mn\t\2\2\2n\36\3\2\2\2op\t\t\2\2p \3\2\2"+
		"\2qr\5\13\5\2rt\5\13\5\2su\5\13\5\2ts\3\2\2\2tu\3\2\2\2u\"\3\2\2\2vw\7"+
		"@\2\2wx\3\2\2\2xy\b\21\4\2y$\3\2\2\2z{\7\61\2\2{&\3\2\2\2|}\7\61\2\2}"+
		"~\7@\2\2~\177\3\2\2\2\177\u0080\b\23\4\2\u0080(\3\2\2\2\u0081\u0083\5"+
		"\13\5\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085*\3\2\2\2\u0086\u0088\5\13\5\2\u0087\u0086\3\2\2\2"+
		"\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a,\3"+
		"\2\2\2\u008b\u008c\5=\36\2\u008c.\3\2\2\2\u008d\u008e\7)\2\2\u008e\60"+
		"\3\2\2\2\u008f\u0090\7$\2\2\u0090\62\3\2\2\2\u0091\u0092\7\uffff\2\2\u0092"+
		"\u0093\7\uffff\2\2\u0093\u0098\7\uffff\2\2\u0094\u0095\7\uffff\2\2\u0095"+
		"\u0096\7\uffff\2\2\u0096\u0098\7\uffff\2\2\u0097\u0091\3\2\2\2\u0097\u0094"+
		"\3\2\2\2\u0098\64\3\2\2\2\u0099\u009d\5/\27\2\u009a\u009c\n\n\2\2\u009b"+
		"\u009a\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\u00a0\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a1\5/\27\2\u00a1"+
		"\66\3\2\2\2\u00a2\u00a6\5\61\30\2\u00a3\u00a5\n\13\2\2\u00a4\u00a3\3\2"+
		"\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\5\61\30\2\u00aa8\3\2\2"+
		"\2\u00ab\u00af\5\63\31\2\u00ac\u00ae\n\f\2\2\u00ad\u00ac\3\2\2\2\u00ae"+
		"\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2"+
		"\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\5\63\31\2\u00b3:\3\2\2\2\u00b4\u00b6"+
		"\n\4\2\2\u00b5\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8<\3\2\2\2\u00b9\u00be\5\65\32\2\u00ba\u00be\5\67\33"+
		"\2\u00bb\u00be\59\34\2\u00bc\u00be\5;\35\2\u00bd\u00b9\3\2\2\2\u00bd\u00ba"+
		"\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be>\3\2\2\2\20\2\3"+
		"\4Tgt\u0084\u0089\u0097\u009d\u00a6\u00af\u00b7\u00bd\5\7\3\2\7\4\2\6"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}