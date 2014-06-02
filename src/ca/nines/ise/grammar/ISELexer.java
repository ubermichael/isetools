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
		COMMENT=1, SEA_WS=2, ABBR_START=3, CHAR_START=4, TAG_START=5, TEXT=6, 
		ABBR_END=7, ABBR_CONTENT=8, CHAR_END=9, CHAR_NAMED_SINGLE=10, CHAR_NAMED_DOUBLE=11, 
		CHAR_TYPOGRAPHIC=12, CHAR_SPACE=13, CHAR_ACCENT=14, CHAR_ALPHA=15, TAG_END=16, 
		TAG_SLASH_END=17, TAG_SLASH=18, TAG_EQ=19, TAG_NAME=20, TAG_WS=21, ATTRIBUTE_VALUE=22, 
		ATTRIBUTE=23;
	public static final int ABBR = 1;
	public static final int CHAR = 2;
	public static final int TAG = 3;
	public static final int ATTR = 4;
	public static String[] modeNames = {
		"DEFAULT_MODE", "ABBR", "CHAR", "TAG", "ATTR"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "SEA_WS", "ABBR_START", "'{'", "'<'", "TEXT", "ABBR_END", "ABBR_CONTENT", 
		"'}'", "CHAR_NAMED_SINGLE", "CHAR_NAMED_DOUBLE", "CHAR_TYPOGRAPHIC", "CHAR_SPACE", 
		"CHAR_ACCENT", "CHAR_ALPHA", "'>'", "'/>'", "'/'", "'='", "TAG_NAME", 
		"TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE"
	};
	public static final String[] ruleNames = {
		"COMMENT", "SEA_WS", "ABBR_START", "CHAR_START", "TAG_START", "TEXT", 
		"ABBR_END", "ABBR_CONTENT", "CHAR_END", "CHAR_NAMED_SINGLE", "CHAR_NAMED_DOUBLE", 
		"CHAR_TYPOGRAPHIC", "CHAR_SPACE", "CHAR_ACCENT", "CHAR_ALPHA", "TAG_END", 
		"TAG_SLASH_END", "TAG_SLASH", "TAG_EQ", "TAG_NAME", "TAG_WS", "ATTRIBUTE_VALUE", 
		"ATTRIBUTE", "ATTRIBUTE_CHAR", "ATTRIBUTE_CHARS", "DOUBLE_QUOTE_STRING", 
		"SINGLE_QUOTE_STRING"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31\u00cb\b\1\b\1"+
		"\b\1\b\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\7\2D\n\2\f\2\16\2G\13\2\3\2\3\2\3\2\3\2\3\3\3\3\5\3O\n\3\3\3\6\3"+
		"R\n\3\r\3\16\3S\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\6"+
		"\7c\n\7\r\7\16\7d\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\177\n\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\25\6\25\u0099\n\25\r\25\16\25\u009a\3"+
		"\26\3\26\3\26\3\26\3\27\7\27\u00a2\n\27\f\27\16\27\u00a5\13\27\3\27\3"+
		"\27\3\27\3\27\3\30\3\30\3\30\5\30\u00ae\n\30\3\31\3\31\3\32\6\32\u00b3"+
		"\n\32\r\32\16\32\u00b4\3\32\5\32\u00b8\n\32\3\33\3\33\7\33\u00bc\n\33"+
		"\f\33\16\33\u00bf\13\33\3\33\3\33\3\34\3\34\7\34\u00c5\n\34\f\34\16\34"+
		"\u00c8\13\34\3\34\3\34\3E\2\35\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27"+
		"\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31"+
		"\65\2\67\29\2;\2\7\2\3\4\5\6\17\4\2\13\13\"\"\5\2>>@@}\177\3\2~~\4\2R"+
		"Ree\5\2YYtuyy\4\2\"\"%^\6\2$$))`b\u0080\u0080\4\2C\\c|\5\2\13\f\17\17"+
		"\"\"\3\2\"\"\b\2$$))>>@@}}\177\177\4\2$$>>\4\2))>>\u00d4\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\3\23\3\2"+
		"\2\2\3\25\3\2\2\2\4\27\3\2\2\2\4\31\3\2\2\2\4\33\3\2\2\2\4\35\3\2\2\2"+
		"\4\37\3\2\2\2\4!\3\2\2\2\4#\3\2\2\2\5%\3\2\2\2\5\'\3\2\2\2\5)\3\2\2\2"+
		"\5+\3\2\2\2\5-\3\2\2\2\5/\3\2\2\2\6\61\3\2\2\2\6\63\3\2\2\2\7=\3\2\2\2"+
		"\tQ\3\2\2\2\13U\3\2\2\2\rY\3\2\2\2\17]\3\2\2\2\21b\3\2\2\2\23f\3\2\2\2"+
		"\25j\3\2\2\2\27l\3\2\2\2\31p\3\2\2\2\33~\3\2\2\2\35\u0080\3\2\2\2\37\u0082"+
		"\3\2\2\2!\u0084\3\2\2\2#\u0086\3\2\2\2%\u0088\3\2\2\2\'\u008c\3\2\2\2"+
		")\u0091\3\2\2\2+\u0093\3\2\2\2-\u0098\3\2\2\2/\u009c\3\2\2\2\61\u00a3"+
		"\3\2\2\2\63\u00ad\3\2\2\2\65\u00af\3\2\2\2\67\u00b2\3\2\2\29\u00b9\3\2"+
		"\2\2;\u00c2\3\2\2\2=>\7>\2\2>?\7#\2\2?@\7/\2\2@A\7/\2\2AE\3\2\2\2BD\13"+
		"\2\2\2CB\3\2\2\2DG\3\2\2\2EF\3\2\2\2EC\3\2\2\2FH\3\2\2\2GE\3\2\2\2HI\7"+
		"/\2\2IJ\7/\2\2JK\7@\2\2K\b\3\2\2\2LR\t\2\2\2MO\7\17\2\2NM\3\2\2\2NO\3"+
		"\2\2\2OP\3\2\2\2PR\7\f\2\2QL\3\2\2\2QN\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3"+
		"\2\2\2T\n\3\2\2\2UV\7~\2\2VW\3\2\2\2WX\b\4\2\2X\f\3\2\2\2YZ\7}\2\2Z[\3"+
		"\2\2\2[\\\b\5\3\2\\\16\3\2\2\2]^\7>\2\2^_\3\2\2\2_`\b\6\4\2`\20\3\2\2"+
		"\2ac\n\3\2\2ba\3\2\2\2cd\3\2\2\2db\3\2\2\2de\3\2\2\2e\22\3\2\2\2fg\7~"+
		"\2\2gh\3\2\2\2hi\b\b\5\2i\24\3\2\2\2jk\n\4\2\2k\26\3\2\2\2lm\7\177\2\2"+
		"mn\3\2\2\2no\b\n\5\2o\30\3\2\2\2pq\t\5\2\2q\32\3\2\2\2rs\7u\2\2s\177\7"+
		"o\2\2tu\7r\2\2u\177\7k\2\2vw\7c\2\2w\177\7g\2\2xy\7C\2\2y\177\7G\2\2z"+
		"{\7q\2\2{\177\7g\2\2|}\7Q\2\2}\177\7G\2\2~r\3\2\2\2~t\3\2\2\2~v\3\2\2"+
		"\2~x\3\2\2\2~z\3\2\2\2~|\3\2\2\2\177\34\3\2\2\2\u0080\u0081\t\6\2\2\u0081"+
		"\36\3\2\2\2\u0082\u0083\t\7\2\2\u0083 \3\2\2\2\u0084\u0085\t\b\2\2\u0085"+
		"\"\3\2\2\2\u0086\u0087\t\t\2\2\u0087$\3\2\2\2\u0088\u0089\7@\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\u008b\b\21\5\2\u008b&\3\2\2\2\u008c\u008d\7\61\2"+
		"\2\u008d\u008e\7@\2\2\u008e\u008f\3\2\2\2\u008f\u0090\b\22\5\2\u0090("+
		"\3\2\2\2\u0091\u0092\7\61\2\2\u0092*\3\2\2\2\u0093\u0094\7?\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0096\b\24\6\2\u0096,\3\2\2\2\u0097\u0099\t\t\2\2"+
		"\u0098\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b"+
		"\3\2\2\2\u009b.\3\2\2\2\u009c\u009d\t\n\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u009f\b\26\7\2\u009f\60\3\2\2\2\u00a0\u00a2\t\13\2\2\u00a1\u00a0\3\2"+
		"\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\5\63\30\2\u00a7\u00a8\3"+
		"\2\2\2\u00a8\u00a9\b\27\5\2\u00a9\62\3\2\2\2\u00aa\u00ae\59\33\2\u00ab"+
		"\u00ae\5;\34\2\u00ac\u00ae\5\67\32\2\u00ad\u00aa\3\2\2\2\u00ad\u00ab\3"+
		"\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\64\3\2\2\2\u00af\u00b0\n\f\2\2\u00b0"+
		"\66\3\2\2\2\u00b1\u00b3\5\65\31\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2"+
		"\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6"+
		"\u00b8\7\"\2\2\u00b7\u00b6\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b88\3\2\2\2"+
		"\u00b9\u00bd\7$\2\2\u00ba\u00bc\n\r\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bf"+
		"\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00c0\u00c1\7$\2\2\u00c1:\3\2\2\2\u00c2\u00c6\7)\2\2\u00c3"+
		"\u00c5\n\16\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3"+
		"\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9"+
		"\u00ca\7)\2\2\u00ca<\3\2\2\2\24\2\3\4\5\6ENQSd~\u009a\u00a3\u00ad\u00b4"+
		"\u00b7\u00bd\u00c6\b\7\3\2\7\4\2\7\5\2\6\2\2\7\6\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}