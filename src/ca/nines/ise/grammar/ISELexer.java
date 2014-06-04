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
		COMMENT=1, COMMENT_BAD=2, ABBR_START=3, CHAR_START=4, TAG_START=5, TEXT=6, 
		ABBR_END=7, ABBR_CONTENT=8, CHAR_END=9, CHAR_CONTENT=10, NESTED_START=11, 
		NESTED_END=12, NESTED_CONTENT=13, TAG_END=14, TAG_SLASH_END=15, TAG_SLASH=16, 
		TAG_EQ=17, TAG_NAME=18, TAG_WS=19, ATTRIBUTE_VALUE=20, ATTRIBUTE=21;
	public static final int ABBR = 1;
	public static final int CHAR = 2;
	public static final int NESTED = 3;
	public static final int TAG = 4;
	public static final int ATTR = 5;
	public static String[] modeNames = {
		"DEFAULT_MODE", "ABBR", "CHAR", "NESTED", "TAG", "ATTR"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "COMMENT_BAD", "ABBR_START", "CHAR_START", "'<'", "TEXT", "ABBR_END", 
		"ABBR_CONTENT", "CHAR_END", "CHAR_CONTENT", "NESTED_START", "NESTED_END", 
		"NESTED_CONTENT", "'>'", "'/>'", "'/'", "'='", "TAG_NAME", "TAG_WS", "ATTRIBUTE_VALUE", 
		"ATTRIBUTE"
	};
	public static final String[] ruleNames = {
		"COMMENT", "COMMENT_BAD", "ABBR_START", "CHAR_START", "TAG_START", "TEXT", 
		"ABBR_END", "ABBR_CONTENT", "CHAR_END", "CHAR_CONTENT", "NESTED_START", 
		"NESTED_END", "NESTED_CONTENT", "TAG_END", "TAG_SLASH_END", "TAG_SLASH", 
		"TAG_EQ", "TAG_NAME", "TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE", "ATTRIBUTE_CHAR", 
		"ATTRIBUTE_CHARS", "DOUBLE_QUOTE_STRING", "SINGLE_QUOTE_STRING"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u00c4\b\1\b\1"+
		"\b\1\b\1\b\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\7\2A\n\2\f"+
		"\2\16\2D\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3N\n\3\f\3\16\3Q\13\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\6\7b\n\7"+
		"\r\7\16\7c\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\6\13q\n\13\r\13"+
		"\16\13r\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\6\16~\n\16\r\16\16\16\177"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\23\6\23\u0092\n\23\r\23\16\23\u0093\3\24\3\24\3\24\3\24\3\25\7"+
		"\25\u009b\n\25\f\25\16\25\u009e\13\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\5\26\u00a7\n\26\3\27\3\27\3\30\6\30\u00ac\n\30\r\30\16\30\u00ad\3"+
		"\30\5\30\u00b1\n\30\3\31\3\31\7\31\u00b5\n\31\f\31\16\31\u00b8\13\31\3"+
		"\31\3\31\3\32\3\32\7\32\u00be\n\32\f\32\16\32\u00c1\13\32\3\32\3\32\4"+
		"BO\2\33\b\3\n\4\f\5\16\6\20\7\22\b\24\t\26\n\30\13\32\f\34\r\36\16 \17"+
		"\"\20$\21&\22(\23*\24,\25.\26\60\27\62\2\64\2\66\28\2\b\2\3\4\5\6\7\13"+
		"\5\2>>@@}\177\3\2~~\4\2}}\177\177\4\2C\\c|\5\2\13\f\17\17\"\"\3\2\"\""+
		"\b\2$$))>>@@}}\177\177\4\2$$>>\4\2))>>\u00c7\2\b\3\2\2\2\2\n\3\2\2\2\2"+
		"\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\3\24\3\2\2\2\3\26\3"+
		"\2\2\2\4\30\3\2\2\2\4\32\3\2\2\2\4\34\3\2\2\2\5\36\3\2\2\2\5 \3\2\2\2"+
		"\6\"\3\2\2\2\6$\3\2\2\2\6&\3\2\2\2\6(\3\2\2\2\6*\3\2\2\2\6,\3\2\2\2\7"+
		".\3\2\2\2\7\60\3\2\2\2\b:\3\2\2\2\nI\3\2\2\2\fT\3\2\2\2\16X\3\2\2\2\20"+
		"\\\3\2\2\2\22a\3\2\2\2\24e\3\2\2\2\26i\3\2\2\2\30k\3\2\2\2\32p\3\2\2\2"+
		"\34t\3\2\2\2\36x\3\2\2\2 }\3\2\2\2\"\u0081\3\2\2\2$\u0085\3\2\2\2&\u008a"+
		"\3\2\2\2(\u008c\3\2\2\2*\u0091\3\2\2\2,\u0095\3\2\2\2.\u009c\3\2\2\2\60"+
		"\u00a6\3\2\2\2\62\u00a8\3\2\2\2\64\u00ab\3\2\2\2\66\u00b2\3\2\2\28\u00bb"+
		"\3\2\2\2:;\7>\2\2;<\7#\2\2<=\7/\2\2=>\7/\2\2>B\3\2\2\2?A\13\2\2\2@?\3"+
		"\2\2\2AD\3\2\2\2BC\3\2\2\2B@\3\2\2\2CE\3\2\2\2DB\3\2\2\2EF\7/\2\2FG\7"+
		"/\2\2GH\7@\2\2H\t\3\2\2\2IJ\7>\2\2JK\7#\2\2KO\3\2\2\2LN\13\2\2\2ML\3\2"+
		"\2\2NQ\3\2\2\2OP\3\2\2\2OM\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\7@\2\2S\13\3"+
		"\2\2\2TU\7~\2\2UV\3\2\2\2VW\b\4\2\2W\r\3\2\2\2XY\7}\2\2YZ\3\2\2\2Z[\b"+
		"\5\3\2[\17\3\2\2\2\\]\7>\2\2]^\3\2\2\2^_\b\6\4\2_\21\3\2\2\2`b\n\2\2\2"+
		"a`\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\23\3\2\2\2ef\7~\2\2fg\3\2\2"+
		"\2gh\b\b\5\2h\25\3\2\2\2ij\n\3\2\2j\27\3\2\2\2kl\7\177\2\2lm\3\2\2\2m"+
		"n\b\n\5\2n\31\3\2\2\2oq\n\4\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2"+
		"\2s\33\3\2\2\2tu\7}\2\2uv\3\2\2\2vw\b\f\6\2w\35\3\2\2\2xy\7\177\2\2yz"+
		"\3\2\2\2z{\b\r\5\2{\37\3\2\2\2|~\n\4\2\2}|\3\2\2\2~\177\3\2\2\2\177}\3"+
		"\2\2\2\177\u0080\3\2\2\2\u0080!\3\2\2\2\u0081\u0082\7@\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0084\b\17\5\2\u0084#\3\2\2\2\u0085\u0086\7\61\2\2\u0086"+
		"\u0087\7@\2\2\u0087\u0088\3\2\2\2\u0088\u0089\b\20\5\2\u0089%\3\2\2\2"+
		"\u008a\u008b\7\61\2\2\u008b\'\3\2\2\2\u008c\u008d\7?\2\2\u008d\u008e\3"+
		"\2\2\2\u008e\u008f\b\22\7\2\u008f)\3\2\2\2\u0090\u0092\t\5\2\2\u0091\u0090"+
		"\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"+\3\2\2\2\u0095\u0096\t\6\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\24\b\2"+
		"\u0098-\3\2\2\2\u0099\u009b\t\7\2\2\u009a\u0099\3\2\2\2\u009b\u009e\3"+
		"\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009f\u00a0\5\60\26\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\b"+
		"\25\5\2\u00a2/\3\2\2\2\u00a3\u00a7\5\66\31\2\u00a4\u00a7\58\32\2\u00a5"+
		"\u00a7\5\64\30\2\u00a6\u00a3\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a5\3"+
		"\2\2\2\u00a7\61\3\2\2\2\u00a8\u00a9\n\b\2\2\u00a9\63\3\2\2\2\u00aa\u00ac"+
		"\5\62\27\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2"+
		"\u00ad\u00ae\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af\u00b1\7\"\2\2\u00b0\u00af"+
		"\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\65\3\2\2\2\u00b2\u00b6\7$\2\2\u00b3"+
		"\u00b5\n\t\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9"+
		"\u00ba\7$\2\2\u00ba\67\3\2\2\2\u00bb\u00bf\7)\2\2\u00bc\u00be\n\n\2\2"+
		"\u00bd\u00bc\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0"+
		"\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c3\7)\2\2\u00c3"+
		"9\3\2\2\2\24\2\3\4\5\6\7BOcr\177\u0093\u009c\u00a6\u00ad\u00b0\u00b6\u00bf"+
		"\t\7\3\2\7\4\2\7\6\2\6\2\2\7\5\2\7\7\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}