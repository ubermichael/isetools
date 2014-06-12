// Generated from src/ca/nines/ise/grammar/ISELexer.g4 by ANTLR 4.2.2
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
		COMMENT_GOOD=1, COMMENT_BAD=2, ABBREVIATION=3, CHAR_UNICODE=4, CHAR_DIGRAPH=5, 
		CHAR_SPACE=6, CHAR_ACCENT=7, CHAR_TYPOGRAPHIC=8, CHAR_LIGATURE=9, TAG_START=10, 
		TEXT=11, TAG_END=12, TAG_SLASH_END=13, TAG_SLASH=14, TAG_EQ=15, TAG_NAME=16, 
		TAG_WS=17, ATTRIBUTE_VALUE=18, ATTRIBUTE=19;
	public static final int TAG = 1;
	public static final int ATTR = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "TAG", "ATTR"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT_GOOD", "COMMENT_BAD", "ABBREVIATION", "CHAR_UNICODE", "CHAR_DIGRAPH", 
		"CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", "'<'", 
		"TEXT", "'>'", "'/>'", "'/'", "'='", "TAG_NAME", "TAG_WS", "ATTRIBUTE_VALUE", 
		"ATTRIBUTE"
	};
	public static final String[] ruleNames = {
		"COMMENT_GOOD", "COMMENT_BAD", "ABBREVIATION", "CHAR_UNICODE", "CHAR_DIGRAPH", 
		"CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", "TAG_START", 
		"TEXT", "TAG_END", "TAG_SLASH_END", "TAG_SLASH", "TAG_EQ", "TAG_NAME", 
		"TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE", "ATTRIBUTE_CHAR", "ATTRIBUTE_CHARS", 
		"DOUBLE_QUOTE_STRING", "SINGLE_QUOTE_STRING"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\25\u011d\b\1\b\1"+
		"\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4"+
		"\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t"+
		"\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t"+
		"\30\3\2\3\2\3\2\3\2\3\2\3\2\7\2:\n\2\f\2\16\2=\13\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\7\3G\n\3\f\3\16\3J\13\3\3\3\3\3\3\4\3\4\7\4P\n\4\f\4\16"+
		"\4S\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\5e\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\5\6{\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5"+
		"\7\u0086\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a6"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00b7"+
		"\n\t\3\n\3\n\3\n\3\n\6\n\u00bd\n\n\r\n\16\n\u00be\3\n\3\n\3\n\3\n\3\n"+
		"\6\n\u00c6\n\n\r\n\16\n\u00c7\3\n\5\n\u00cb\n\n\3\13\3\13\3\13\3\13\3"+
		"\f\6\f\u00d2\n\f\r\f\16\f\u00d3\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\7\21\u00e7\n\21\f\21\16\21"+
		"\u00ea\13\21\3\22\6\22\u00ed\n\22\r\22\16\22\u00ee\3\22\3\22\3\23\7\23"+
		"\u00f4\n\23\f\23\16\23\u00f7\13\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\5\24\u0100\n\24\3\25\3\25\3\26\6\26\u0105\n\26\r\26\16\26\u0106\3\26"+
		"\5\26\u010a\n\26\3\27\3\27\7\27\u010e\n\27\f\27\16\27\u0111\13\27\3\27"+
		"\3\27\3\30\3\30\7\30\u0117\n\30\f\30\16\30\u011a\13\30\3\30\3\30\4;H\2"+
		"\31\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23\n\25\13\27\f\31\r\33\16\35\17\37"+
		"\20!\21#\22%\23\'\24)\25+\2-\2/\2\61\2\5\2\3\4\13\3\2~~\4\2C\\c|\4\2>"+
		">}~\5\2\62;C\\c|\5\2\13\f\17\17\"\"\3\2\"\"\b\2$$))>>@@}}\177\177\4\2"+
		"$$>>\4\2))>>\u013a\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\3\33\3\2\2\2\3\35\3\2\2\2\3\37\3\2\2\2\3!\3\2\2\2"+
		"\3#\3\2\2\2\3%\3\2\2\2\4\'\3\2\2\2\4)\3\2\2\2\5\63\3\2\2\2\7B\3\2\2\2"+
		"\tM\3\2\2\2\13d\3\2\2\2\rz\3\2\2\2\17\u0085\3\2\2\2\21\u00a5\3\2\2\2\23"+
		"\u00b6\3\2\2\2\25\u00ca\3\2\2\2\27\u00cc\3\2\2\2\31\u00d1\3\2\2\2\33\u00d5"+
		"\3\2\2\2\35\u00d9\3\2\2\2\37\u00de\3\2\2\2!\u00e0\3\2\2\2#\u00e4\3\2\2"+
		"\2%\u00ec\3\2\2\2\'\u00f5\3\2\2\2)\u00ff\3\2\2\2+\u0101\3\2\2\2-\u0104"+
		"\3\2\2\2/\u010b\3\2\2\2\61\u0114\3\2\2\2\63\64\7>\2\2\64\65\7#\2\2\65"+
		"\66\7/\2\2\66\67\7/\2\2\67;\3\2\2\28:\13\2\2\298\3\2\2\2:=\3\2\2\2;<\3"+
		"\2\2\2;9\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7/\2\2?@\7/\2\2@A\7@\2\2A\6\3\2"+
		"\2\2BC\7>\2\2CD\7#\2\2DH\3\2\2\2EG\13\2\2\2FE\3\2\2\2GJ\3\2\2\2HI\3\2"+
		"\2\2HF\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\7@\2\2L\b\3\2\2\2MQ\7~\2\2NP\n\2"+
		"\2\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TU\7~"+
		"\2\2U\n\3\2\2\2VW\7}\2\2WX\7e\2\2Xe\7\177\2\2YZ\7}\2\2Z[\7E\2\2[e\7\177"+
		"\2\2\\]\7}\2\2]^\7v\2\2^_\7j\2\2_e\7\177\2\2`a\7}\2\2ab\7V\2\2bc\7J\2"+
		"\2ce\7\177\2\2dV\3\2\2\2dY\3\2\2\2d\\\3\2\2\2d`\3\2\2\2e\f\3\2\2\2fg\7"+
		"}\2\2gh\7c\2\2hi\7g\2\2i{\7\177\2\2jk\7}\2\2kl\7C\2\2lm\7G\2\2m{\7\177"+
		"\2\2no\7}\2\2op\7q\2\2pq\7g\2\2q{\7\177\2\2rs\7}\2\2st\7Q\2\2tu\7G\2\2"+
		"u{\7\177\2\2vw\7}\2\2wx\7s\2\2xy\7r\2\2y{\7\177\2\2zf\3\2\2\2zj\3\2\2"+
		"\2zn\3\2\2\2zr\3\2\2\2zv\3\2\2\2{\16\3\2\2\2|}\7}\2\2}~\7\"\2\2~\u0086"+
		"\7\177\2\2\177\u0080\7}\2\2\u0080\u0081\7/\2\2\u0081\u0086\7\177\2\2\u0082"+
		"\u0083\7}\2\2\u0083\u0084\7%\2\2\u0084\u0086\7\177\2\2\u0085|\3\2\2\2"+
		"\u0085\177\3\2\2\2\u0085\u0082\3\2\2\2\u0086\20\3\2\2\2\u0087\u0088\7"+
		"}\2\2\u0088\u0089\7`\2\2\u0089\u008a\3\2\2\2\u008a\u008b\t\3\2\2\u008b"+
		"\u00a6\7\177\2\2\u008c\u008d\7}\2\2\u008d\u008e\7$\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u0090\t\3\2\2\u0090\u00a6\7\177\2\2\u0091\u0092\7}\2\2\u0092"+
		"\u0093\7)\2\2\u0093\u0094\3\2\2\2\u0094\u0095\t\3\2\2\u0095\u00a6\7\177"+
		"\2\2\u0096\u0097\7}\2\2\u0097\u0098\7b\2\2\u0098\u0099\3\2\2\2\u0099\u009a"+
		"\t\3\2\2\u009a\u00a6\7\177\2\2\u009b\u009c\7}\2\2\u009c\u009d\7a\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u009f\t\3\2\2\u009f\u00a6\7\177\2\2\u00a0\u00a1\7"+
		"}\2\2\u00a1\u00a2\7\u0080\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\t\3\2\2"+
		"\u00a4\u00a6\7\177\2\2\u00a5\u0087\3\2\2\2\u00a5\u008c\3\2\2\2\u00a5\u0091"+
		"\3\2\2\2\u00a5\u0096\3\2\2\2\u00a5\u009b\3\2\2\2\u00a5\u00a0\3\2\2\2\u00a6"+
		"\22\3\2\2\2\u00a7\u00a8\7}\2\2\u00a8\u00a9\7u\2\2\u00a9\u00b7\7\177\2"+
		"\2\u00aa\u00ab\7}\2\2\u00ab\u00ac\7t\2\2\u00ac\u00b7\7\177\2\2\u00ad\u00ae"+
		"\7}\2\2\u00ae\u00af\7T\2\2\u00af\u00b7\7\177\2\2\u00b0\u00b1\7}\2\2\u00b1"+
		"\u00b2\7y\2\2\u00b2\u00b7\7\177\2\2\u00b3\u00b4\7}\2\2\u00b4\u00b5\7Y"+
		"\2\2\u00b5\u00b7\7\177\2\2\u00b6\u00a7\3\2\2\2\u00b6\u00aa\3\2\2\2\u00b6"+
		"\u00ad\3\2\2\2\u00b6\u00b0\3\2\2\2\u00b6\u00b3\3\2\2\2\u00b7\24\3\2\2"+
		"\2\u00b8\u00b9\7}\2\2\u00b9\u00ba\t\3\2\2\u00ba\u00bc\t\3\2\2\u00bb\u00bd"+
		"\t\3\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00cb\7\177\2\2\u00c1\u00c5\7"+
		"}\2\2\u00c2\u00c6\t\3\2\2\u00c3\u00c6\5\21\b\2\u00c4\u00c6\5\23\t\2\u00c5"+
		"\u00c2\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c7\3\2"+
		"\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00cb\7\177\2\2\u00ca\u00b8\3\2\2\2\u00ca\u00c1\3\2\2\2\u00cb\26\3\2"+
		"\2\2\u00cc\u00cd\7>\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\b\13\2\2\u00cf"+
		"\30\3\2\2\2\u00d0\u00d2\n\4\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d3\3\2\2"+
		"\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\32\3\2\2\2\u00d5\u00d6"+
		"\7@\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\b\r\3\2\u00d8\34\3\2\2\2\u00d9"+
		"\u00da\7\61\2\2\u00da\u00db\7@\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\b\16"+
		"\3\2\u00dd\36\3\2\2\2\u00de\u00df\7\61\2\2\u00df \3\2\2\2\u00e0\u00e1"+
		"\7?\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\b\20\4\2\u00e3\"\3\2\2\2\u00e4"+
		"\u00e8\t\3\2\2\u00e5\u00e7\t\5\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2"+
		"\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9$\3\2\2\2\u00ea\u00e8"+
		"\3\2\2\2\u00eb\u00ed\t\6\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\b\22"+
		"\5\2\u00f1&\3\2\2\2\u00f2\u00f4\t\7\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f7"+
		"\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f8\3\2\2\2\u00f7"+
		"\u00f5\3\2\2\2\u00f8\u00f9\5)\24\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\b\23"+
		"\3\2\u00fb(\3\2\2\2\u00fc\u0100\5/\27\2\u00fd\u0100\5\61\30\2\u00fe\u0100"+
		"\5-\26\2\u00ff\u00fc\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u00fe\3\2\2\2\u0100"+
		"*\3\2\2\2\u0101\u0102\n\b\2\2\u0102,\3\2\2\2\u0103\u0105\5+\25\2\u0104"+
		"\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2"+
		"\2\2\u0107\u0109\3\2\2\2\u0108\u010a\7\"\2\2\u0109\u0108\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a.\3\2\2\2\u010b\u010f\7$\2\2\u010c\u010e\n\t\2\2\u010d"+
		"\u010c\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2"+
		"\2\2\u0110\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7$\2\2\u0113"+
		"\60\3\2\2\2\u0114\u0118\7)\2\2\u0115\u0117\n\n\2\2\u0116\u0115\3\2\2\2"+
		"\u0117\u011a\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b"+
		"\3\2\2\2\u011a\u0118\3\2\2\2\u011b\u011c\7)\2\2\u011c\62\3\2\2\2\32\2"+
		"\3\4;HQdz\u0085\u00a5\u00b6\u00be\u00c5\u00c7\u00ca\u00d3\u00e8\u00ee"+
		"\u00f5\u00ff\u0106\u0109\u010f\u0118\6\7\3\2\6\2\2\7\4\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}