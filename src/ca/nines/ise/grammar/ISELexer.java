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
		COMMENT_GOOD=1, COMMENT_BAD=2, ABBREVIATION=3, CHAR_UNICODE=4, CHAR_CODEPOINT=5, 
		CHAR_DIGRAPH=6, CHAR_SPACE=7, CHAR_ACCENT=8, CHAR_TYPOGRAPHIC=9, CHAR_LIGATURE=10, 
		CHAR_NESTED=11, TAG_START=12, TEXT=13, TAG_END=14, TAG_SLASH_END=15, TAG_SLASH=16, 
		TAG_EQ=17, TAG_NAME=18, TAG_WS=19, ATTRIBUTE_VALUE=20, ATTRIBUTE=21;
	public static final int TAG = 1;
	public static final int ATTR = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "TAG", "ATTR"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT_GOOD", "COMMENT_BAD", "ABBREVIATION", "CHAR_UNICODE", "CHAR_CODEPOINT", 
		"CHAR_DIGRAPH", "CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", 
		"CHAR_NESTED", "'<'", "TEXT", "'>'", "'/>'", "'/'", "'='", "TAG_NAME", 
		"TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE"
	};
	public static final String[] ruleNames = {
		"COMMENT_GOOD", "COMMENT_BAD", "ABBREVIATION", "CHAR_UNICODE", "CHAR_CODEPOINT", 
		"CHAR_DIGRAPH", "CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", 
		"CHAR_NESTED", "TAG_START", "TEXT", "TAG_END", "TAG_SLASH_END", "TAG_SLASH", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u0138\b\1\b\1"+
		"\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4"+
		"\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t"+
		"\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t"+
		"\30\4\31\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\7\2>\n\2\f\2\16\2A\13"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\3\3\3\3\3\4"+
		"\3\4\7\4T\n\4\f\4\16\4W\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\5\5u\n\5\3\6\3\6\3\6\3\6\3\6\6\6|\n\6\r\6\16\6}\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7\u009a\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a5"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c5\n\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\n\u00cd\n\n\3\13\3\13\3\13\3\13\5\13\u00d3\n"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\5\f\u00dc\n\f\3\f\3\f\3\f\3\f\6\f\u00e2"+
		"\n\f\r\f\16\f\u00e3\3\f\3\f\3\r\3\r\3\r\3\r\3\16\6\16\u00ed\n\16\r\16"+
		"\16\16\u00ee\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\7\23\u0102\n\23\f\23\16\23\u0105\13\23\3"+
		"\24\6\24\u0108\n\24\r\24\16\24\u0109\3\24\3\24\3\25\7\25\u010f\n\25\f"+
		"\25\16\25\u0112\13\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\5\26\u011b\n"+
		"\26\3\27\3\27\3\30\6\30\u0120\n\30\r\30\16\30\u0121\3\30\5\30\u0125\n"+
		"\30\3\31\3\31\7\31\u0129\n\31\f\31\16\31\u012c\13\31\3\31\3\31\3\32\3"+
		"\32\7\32\u0132\n\32\f\32\16\32\u0135\13\32\3\32\3\32\4?L\2\33\5\3\7\4"+
		"\t\5\13\6\r\7\17\b\21\t\23\n\25\13\27\f\31\r\33\16\35\17\37\20!\21#\22"+
		"%\23\'\24)\25+\26-\27/\2\61\2\63\2\65\2\5\2\3\4\f\3\2~~\5\2\62;CHch\4"+
		"\2C\\c|\4\2>>}~\5\2\62;C\\c|\5\2\13\f\17\17\"\"\3\2\"\"\b\2$$))>>@@}}"+
		"\177\177\4\2$$>>\4\2))>>\u015b\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\3"+
		"!\3\2\2\2\3#\3\2\2\2\3%\3\2\2\2\3\'\3\2\2\2\3)\3\2\2\2\4+\3\2\2\2\4-\3"+
		"\2\2\2\5\67\3\2\2\2\7F\3\2\2\2\tQ\3\2\2\2\13t\3\2\2\2\rv\3\2\2\2\17\u0099"+
		"\3\2\2\2\21\u00a4\3\2\2\2\23\u00c4\3\2\2\2\25\u00cc\3\2\2\2\27\u00ce\3"+
		"\2\2\2\31\u00d6\3\2\2\2\33\u00e7\3\2\2\2\35\u00ec\3\2\2\2\37\u00f0\3\2"+
		"\2\2!\u00f4\3\2\2\2#\u00f9\3\2\2\2%\u00fb\3\2\2\2\'\u00ff\3\2\2\2)\u0107"+
		"\3\2\2\2+\u0110\3\2\2\2-\u011a\3\2\2\2/\u011c\3\2\2\2\61\u011f\3\2\2\2"+
		"\63\u0126\3\2\2\2\65\u012f\3\2\2\2\678\7>\2\289\7#\2\29:\7/\2\2:;\7/\2"+
		"\2;?\3\2\2\2<>\13\2\2\2=<\3\2\2\2>A\3\2\2\2?@\3\2\2\2?=\3\2\2\2@B\3\2"+
		"\2\2A?\3\2\2\2BC\7/\2\2CD\7/\2\2DE\7@\2\2E\6\3\2\2\2FG\7>\2\2GH\7#\2\2"+
		"HL\3\2\2\2IK\13\2\2\2JI\3\2\2\2KN\3\2\2\2LM\3\2\2\2LJ\3\2\2\2MO\3\2\2"+
		"\2NL\3\2\2\2OP\7@\2\2P\b\3\2\2\2QU\7~\2\2RT\n\2\2\2SR\3\2\2\2TW\3\2\2"+
		"\2US\3\2\2\2UV\3\2\2\2VX\3\2\2\2WU\3\2\2\2XY\7~\2\2Y\n\3\2\2\2Z[\7}\2"+
		"\2[\\\7u\2\2\\u\7\177\2\2]^\7}\2\2^_\7R\2\2_u\7\177\2\2`a\7}\2\2ab\7t"+
		"\2\2bu\7\177\2\2cd\7}\2\2de\7T\2\2eu\7\177\2\2fg\7}\2\2gh\7e\2\2hu\7\177"+
		"\2\2ij\7}\2\2jk\7E\2\2ku\7\177\2\2lm\7}\2\2mn\7v\2\2no\7j\2\2ou\7\177"+
		"\2\2pq\7}\2\2qr\7V\2\2rs\7J\2\2su\7\177\2\2tZ\3\2\2\2t]\3\2\2\2t`\3\2"+
		"\2\2tc\3\2\2\2tf\3\2\2\2ti\3\2\2\2tl\3\2\2\2tp\3\2\2\2u\f\3\2\2\2vw\7"+
		"}\2\2wx\7^\2\2xy\7w\2\2y{\3\2\2\2z|\t\3\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2"+
		"\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\7\177\2\2\u0080\16\3\2\2\2\u0081"+
		"\u0082\7}\2\2\u0082\u0083\7c\2\2\u0083\u0084\7g\2\2\u0084\u009a\7\177"+
		"\2\2\u0085\u0086\7}\2\2\u0086\u0087\7C\2\2\u0087\u0088\7G\2\2\u0088\u009a"+
		"\7\177\2\2\u0089\u008a\7}\2\2\u008a\u008b\7q\2\2\u008b\u008c\7g\2\2\u008c"+
		"\u009a\7\177\2\2\u008d\u008e\7}\2\2\u008e\u008f\7Q\2\2\u008f\u0090\7G"+
		"\2\2\u0090\u009a\7\177\2\2\u0091\u0092\7}\2\2\u0092\u0093\7s\2\2\u0093"+
		"\u0094\7r\2\2\u0094\u009a\7\177\2\2\u0095\u0096\7}\2\2\u0096\u0097\7f"+
		"\2\2\u0097\u0098\7d\2\2\u0098\u009a\7\177\2\2\u0099\u0081\3\2\2\2\u0099"+
		"\u0085\3\2\2\2\u0099\u0089\3\2\2\2\u0099\u008d\3\2\2\2\u0099\u0091\3\2"+
		"\2\2\u0099\u0095\3\2\2\2\u009a\20\3\2\2\2\u009b\u009c\7}\2\2\u009c\u009d"+
		"\7\"\2\2\u009d\u00a5\7\177\2\2\u009e\u009f\7}\2\2\u009f\u00a0\7/\2\2\u00a0"+
		"\u00a5\7\177\2\2\u00a1\u00a2\7}\2\2\u00a2\u00a3\7%\2\2\u00a3\u00a5\7\177"+
		"\2\2\u00a4\u009b\3\2\2\2\u00a4\u009e\3\2\2\2\u00a4\u00a1\3\2\2\2\u00a5"+
		"\22\3\2\2\2\u00a6\u00a7\7}\2\2\u00a7\u00a8\7`\2\2\u00a8\u00a9\3\2\2\2"+
		"\u00a9\u00aa\t\4\2\2\u00aa\u00c5\7\177\2\2\u00ab\u00ac\7}\2\2\u00ac\u00ad"+
		"\7$\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\t\4\2\2\u00af\u00c5\7\177\2\2"+
		"\u00b0\u00b1\7}\2\2\u00b1\u00b2\7)\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4"+
		"\t\4\2\2\u00b4\u00c5\7\177\2\2\u00b5\u00b6\7}\2\2\u00b6\u00b7\7b\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\u00b9\t\4\2\2\u00b9\u00c5\7\177\2\2\u00ba\u00bb\7"+
		"}\2\2\u00bb\u00bc\7a\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\t\4\2\2\u00be"+
		"\u00c5\7\177\2\2\u00bf\u00c0\7}\2\2\u00c0\u00c1\7\u0080\2\2\u00c1\u00c2"+
		"\3\2\2\2\u00c2\u00c3\t\4\2\2\u00c3\u00c5\7\177\2\2\u00c4\u00a6\3\2\2\2"+
		"\u00c4\u00ab\3\2\2\2\u00c4\u00b0\3\2\2\2\u00c4\u00b5\3\2\2\2\u00c4\u00ba"+
		"\3\2\2\2\u00c4\u00bf\3\2\2\2\u00c5\24\3\2\2\2\u00c6\u00c7\7}\2\2\u00c7"+
		"\u00c8\7y\2\2\u00c8\u00cd\7\177\2\2\u00c9\u00ca\7}\2\2\u00ca\u00cb\7Y"+
		"\2\2\u00cb\u00cd\7\177\2\2\u00cc\u00c6\3\2\2\2\u00cc\u00c9\3\2\2\2\u00cd"+
		"\26\3\2\2\2\u00ce\u00cf\7}\2\2\u00cf\u00d0\t\4\2\2\u00d0\u00d2\t\4\2\2"+
		"\u00d1\u00d3\t\4\2\2\u00d2\u00d1\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4"+
		"\3\2\2\2\u00d4\u00d5\7\177\2\2\u00d5\30\3\2\2\2\u00d6\u00db\7}\2\2\u00d7"+
		"\u00dc\t\4\2\2\u00d8\u00dc\5\13\5\2\u00d9\u00dc\5\23\t\2\u00da\u00dc\5"+
		"\25\n\2\u00db\u00d7\3\2\2\2\u00db\u00d8\3\2\2\2\u00db\u00d9\3\2\2\2\u00db"+
		"\u00da\3\2\2\2\u00dc\u00e1\3\2\2\2\u00dd\u00e2\t\4\2\2\u00de\u00e2\5\13"+
		"\5\2\u00df\u00e2\5\23\t\2\u00e0\u00e2\5\25\n\2\u00e1\u00dd\3\2\2\2\u00e1"+
		"\u00de\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e6\7\177\2\2\u00e6\32\3\2\2\2\u00e7\u00e8\7>\2\2\u00e8\u00e9\3\2\2"+
		"\2\u00e9\u00ea\b\r\2\2\u00ea\34\3\2\2\2\u00eb\u00ed\n\5\2\2\u00ec\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef"+
		"\36\3\2\2\2\u00f0\u00f1\7@\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\b\17\3"+
		"\2\u00f3 \3\2\2\2\u00f4\u00f5\7\61\2\2\u00f5\u00f6\7@\2\2\u00f6\u00f7"+
		"\3\2\2\2\u00f7\u00f8\b\20\3\2\u00f8\"\3\2\2\2\u00f9\u00fa\7\61\2\2\u00fa"+
		"$\3\2\2\2\u00fb\u00fc\7?\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\b\22\4\2"+
		"\u00fe&\3\2\2\2\u00ff\u0103\t\4\2\2\u0100\u0102\t\6\2\2\u0101\u0100\3"+
		"\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104"+
		"(\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0108\t\7\2\2\u0107\u0106\3\2\2\2"+
		"\u0108\u0109\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b"+
		"\3\2\2\2\u010b\u010c\b\24\5\2\u010c*\3\2\2\2\u010d\u010f\t\b\2\2\u010e"+
		"\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2"+
		"\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0114\5-\26\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0116\b\25\3\2\u0116,\3\2\2\2\u0117\u011b\5\63\31"+
		"\2\u0118\u011b\5\65\32\2\u0119\u011b\5\61\30\2\u011a\u0117\3\2\2\2\u011a"+
		"\u0118\3\2\2\2\u011a\u0119\3\2\2\2\u011b.\3\2\2\2\u011c\u011d\n\t\2\2"+
		"\u011d\60\3\2\2\2\u011e\u0120\5/\27\2\u011f\u011e\3\2\2\2\u0120\u0121"+
		"\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123"+
		"\u0125\7\"\2\2\u0124\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125\62\3\2\2"+
		"\2\u0126\u012a\7$\2\2\u0127\u0129\n\n\2\2\u0128\u0127\3\2\2\2\u0129\u012c"+
		"\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012d\3\2\2\2\u012c"+
		"\u012a\3\2\2\2\u012d\u012e\7$\2\2\u012e\64\3\2\2\2\u012f\u0133\7)\2\2"+
		"\u0130\u0132\n\13\2\2\u0131\u0130\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131"+
		"\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0136\3\2\2\2\u0135\u0133\3\2\2\2\u0136"+
		"\u0137\7)\2\2\u0137\66\3\2\2\2\33\2\3\4?LUt}\u0099\u00a4\u00c4\u00cc\u00d2"+
		"\u00db\u00e1\u00e3\u00ee\u0103\u0109\u0110\u011a\u0121\u0124\u012a\u0133"+
		"\6\7\3\2\6\2\2\7\4\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}