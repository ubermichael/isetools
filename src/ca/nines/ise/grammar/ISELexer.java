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
		CHAR_SPACE=6, CHAR_ACCENT=7, CHAR_TYPOGRAPHIC=8, CHAR_LIGATURE=9, CHAR_NESTED=10, 
		TAG_START=11, TEXT=12, ALPHA=13, LB=14, RB=15, TAG_END=16, TAG_SLASH_END=17, 
		TAG_SLASH=18, TAG_EQ=19, TAG_NAME=20, TAG_WS=21, ATTRIBUTE_VALUE=22, ATTRIBUTE=23;
	public static final int TAG = 1;
	public static final int ATTR = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "TAG", "ATTR"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT_GOOD", "COMMENT_BAD", "ABBREVIATION", "CHAR_UNICODE", "CHAR_DIGRAPH", 
		"CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", "CHAR_NESTED", 
		"'<'", "TEXT", "ALPHA", "'{'", "'}'", "'>'", "'/>'", "'/'", "'='", "TAG_NAME", 
		"TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE"
	};
	public static final String[] ruleNames = {
		"COMMENT_GOOD", "COMMENT_BAD", "ABBREVIATION", "CHAR_UNICODE", "CHAR_DIGRAPH", 
		"CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", "CHAR_NESTED", 
		"TAG_START", "TEXT", "ALPHA", "LB", "RB", "TAG_END", "TAG_SLASH_END", 
		"TAG_SLASH", "TAG_EQ", "TAG_NAME", "TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE", 
		"ATTRIBUTE_CHAR", "ATTRIBUTE_CHARS", "DOUBLE_QUOTE_STRING", "SINGLE_QUOTE_STRING"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31\u012a\b\1\b\1"+
		"\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4"+
		"\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t"+
		"\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t"+
		"\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\7"+
		"\2B\n\2\f\2\16\2E\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3O\n\3\f\3\16"+
		"\3R\13\3\3\3\3\3\3\4\3\4\7\4X\n\4\f\4\16\4[\13\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5m\n\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0083"+
		"\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u008e\n\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00ae\n\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00bf\n\t\3\n\3\n\3\n\3\n\5"+
		"\n\u00c5\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\6\13\u00ce\n\13\r\13\16"+
		"\13\u00cf\3\13\3\13\3\f\3\f\3\f\3\f\3\r\6\r\u00d9\n\r\r\r\16\r\u00da\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\22\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\7\25\u00f4\n\25\f\25\16\25"+
		"\u00f7\13\25\3\26\6\26\u00fa\n\26\r\26\16\26\u00fb\3\26\3\26\3\27\7\27"+
		"\u0101\n\27\f\27\16\27\u0104\13\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\5\30\u010d\n\30\3\31\3\31\3\32\6\32\u0112\n\32\r\32\16\32\u0113\3\32"+
		"\5\32\u0117\n\32\3\33\3\33\7\33\u011b\n\33\f\33\16\33\u011e\13\33\3\33"+
		"\3\33\3\34\3\34\7\34\u0124\n\34\f\34\16\34\u0127\13\34\3\34\3\34\4CP\2"+
		"\35\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23\n\25\13\27\f\31\r\33\16\35\17\37"+
		"\20!\21#\22%\23\'\24)\25+\26-\27/\30\61\31\63\2\65\2\67\29\2\5\2\3\4\13"+
		"\3\2~~\4\2C\\c|\5\2>>@@}\177\5\2\62;C\\c|\5\2\13\f\17\17\"\"\3\2\"\"\b"+
		"\2$$))>>@@}}\177\177\4\2$$>>\4\2))>>\u0147\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\3#\3\2\2\2\3%\3\2\2\2\3\'\3\2\2\2\3)\3\2\2\2\3"+
		"+\3\2\2\2\3-\3\2\2\2\4/\3\2\2\2\4\61\3\2\2\2\5;\3\2\2\2\7J\3\2\2\2\tU"+
		"\3\2\2\2\13l\3\2\2\2\r\u0082\3\2\2\2\17\u008d\3\2\2\2\21\u00ad\3\2\2\2"+
		"\23\u00be\3\2\2\2\25\u00c0\3\2\2\2\27\u00c8\3\2\2\2\31\u00d3\3\2\2\2\33"+
		"\u00d8\3\2\2\2\35\u00dc\3\2\2\2\37\u00de\3\2\2\2!\u00e0\3\2\2\2#\u00e2"+
		"\3\2\2\2%\u00e6\3\2\2\2\'\u00eb\3\2\2\2)\u00ed\3\2\2\2+\u00f1\3\2\2\2"+
		"-\u00f9\3\2\2\2/\u0102\3\2\2\2\61\u010c\3\2\2\2\63\u010e\3\2\2\2\65\u0111"+
		"\3\2\2\2\67\u0118\3\2\2\29\u0121\3\2\2\2;<\7>\2\2<=\7#\2\2=>\7/\2\2>?"+
		"\7/\2\2?C\3\2\2\2@B\13\2\2\2A@\3\2\2\2BE\3\2\2\2CD\3\2\2\2CA\3\2\2\2D"+
		"F\3\2\2\2EC\3\2\2\2FG\7/\2\2GH\7/\2\2HI\7@\2\2I\6\3\2\2\2JK\7>\2\2KL\7"+
		"#\2\2LP\3\2\2\2MO\13\2\2\2NM\3\2\2\2OR\3\2\2\2PQ\3\2\2\2PN\3\2\2\2QS\3"+
		"\2\2\2RP\3\2\2\2ST\7@\2\2T\b\3\2\2\2UY\7~\2\2VX\n\2\2\2WV\3\2\2\2X[\3"+
		"\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\]\7~\2\2]\n\3\2\2\2^"+
		"_\7}\2\2_`\7e\2\2`m\7\177\2\2ab\7}\2\2bc\7E\2\2cm\7\177\2\2de\7}\2\2e"+
		"f\7v\2\2fg\7j\2\2gm\7\177\2\2hi\7}\2\2ij\7V\2\2jk\7J\2\2km\7\177\2\2l"+
		"^\3\2\2\2la\3\2\2\2ld\3\2\2\2lh\3\2\2\2m\f\3\2\2\2no\7}\2\2op\7c\2\2p"+
		"q\7g\2\2q\u0083\7\177\2\2rs\7}\2\2st\7C\2\2tu\7G\2\2u\u0083\7\177\2\2"+
		"vw\7}\2\2wx\7q\2\2xy\7g\2\2y\u0083\7\177\2\2z{\7}\2\2{|\7Q\2\2|}\7G\2"+
		"\2}\u0083\7\177\2\2~\177\7}\2\2\177\u0080\7s\2\2\u0080\u0081\7r\2\2\u0081"+
		"\u0083\7\177\2\2\u0082n\3\2\2\2\u0082r\3\2\2\2\u0082v\3\2\2\2\u0082z\3"+
		"\2\2\2\u0082~\3\2\2\2\u0083\16\3\2\2\2\u0084\u0085\7}\2\2\u0085\u0086"+
		"\7\"\2\2\u0086\u008e\7\177\2\2\u0087\u0088\7}\2\2\u0088\u0089\7/\2\2\u0089"+
		"\u008e\7\177\2\2\u008a\u008b\7}\2\2\u008b\u008c\7%\2\2\u008c\u008e\7\177"+
		"\2\2\u008d\u0084\3\2\2\2\u008d\u0087\3\2\2\2\u008d\u008a\3\2\2\2\u008e"+
		"\20\3\2\2\2\u008f\u0090\7}\2\2\u0090\u0091\7`\2\2\u0091\u0092\3\2\2\2"+
		"\u0092\u0093\t\3\2\2\u0093\u00ae\7\177\2\2\u0094\u0095\7}\2\2\u0095\u0096"+
		"\7$\2\2\u0096\u0097\3\2\2\2\u0097\u0098\t\3\2\2\u0098\u00ae\7\177\2\2"+
		"\u0099\u009a\7}\2\2\u009a\u009b\7)\2\2\u009b\u009c\3\2\2\2\u009c\u009d"+
		"\t\3\2\2\u009d\u00ae\7\177\2\2\u009e\u009f\7}\2\2\u009f\u00a0\7b\2\2\u00a0"+
		"\u00a1\3\2\2\2\u00a1\u00a2\t\3\2\2\u00a2\u00ae\7\177\2\2\u00a3\u00a4\7"+
		"}\2\2\u00a4\u00a5\7a\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\t\3\2\2\u00a7"+
		"\u00ae\7\177\2\2\u00a8\u00a9\7}\2\2\u00a9\u00aa\7\u0080\2\2\u00aa\u00ab"+
		"\3\2\2\2\u00ab\u00ac\t\3\2\2\u00ac\u00ae\7\177\2\2\u00ad\u008f\3\2\2\2"+
		"\u00ad\u0094\3\2\2\2\u00ad\u0099\3\2\2\2\u00ad\u009e\3\2\2\2\u00ad\u00a3"+
		"\3\2\2\2\u00ad\u00a8\3\2\2\2\u00ae\22\3\2\2\2\u00af\u00b0\7}\2\2\u00b0"+
		"\u00b1\7u\2\2\u00b1\u00bf\7\177\2\2\u00b2\u00b3\7}\2\2\u00b3\u00b4\7t"+
		"\2\2\u00b4\u00bf\7\177\2\2\u00b5\u00b6\7}\2\2\u00b6\u00b7\7T\2\2\u00b7"+
		"\u00bf\7\177\2\2\u00b8\u00b9\7}\2\2\u00b9\u00ba\7y\2\2\u00ba\u00bf\7\177"+
		"\2\2\u00bb\u00bc\7}\2\2\u00bc\u00bd\7Y\2\2\u00bd\u00bf\7\177\2\2\u00be"+
		"\u00af\3\2\2\2\u00be\u00b2\3\2\2\2\u00be\u00b5\3\2\2\2\u00be\u00b8\3\2"+
		"\2\2\u00be\u00bb\3\2\2\2\u00bf\24\3\2\2\2\u00c0\u00c1\7}\2\2\u00c1\u00c2"+
		"\t\3\2\2\u00c2\u00c4\t\3\2\2\u00c3\u00c5\t\3\2\2\u00c4\u00c3\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\7\177\2\2\u00c7\26\3\2"+
		"\2\2\u00c8\u00cd\7}\2\2\u00c9\u00ce\5\35\16\2\u00ca\u00ce\5\13\5\2\u00cb"+
		"\u00ce\5\21\b\2\u00cc\u00ce\5\23\t\2\u00cd\u00c9\3\2\2\2\u00cd\u00ca\3"+
		"\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\7\177"+
		"\2\2\u00d2\30\3\2\2\2\u00d3\u00d4\7>\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6"+
		"\b\f\2\2\u00d6\32\3\2\2\2\u00d7\u00d9\n\4\2\2\u00d8\u00d7\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\34\3\2\2"+
		"\2\u00dc\u00dd\t\3\2\2\u00dd\36\3\2\2\2\u00de\u00df\7}\2\2\u00df \3\2"+
		"\2\2\u00e0\u00e1\7\177\2\2\u00e1\"\3\2\2\2\u00e2\u00e3\7@\2\2\u00e3\u00e4"+
		"\3\2\2\2\u00e4\u00e5\b\21\3\2\u00e5$\3\2\2\2\u00e6\u00e7\7\61\2\2\u00e7"+
		"\u00e8\7@\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\b\22\3\2\u00ea&\3\2\2\2"+
		"\u00eb\u00ec\7\61\2\2\u00ec(\3\2\2\2\u00ed\u00ee\7?\2\2\u00ee\u00ef\3"+
		"\2\2\2\u00ef\u00f0\b\24\4\2\u00f0*\3\2\2\2\u00f1\u00f5\t\3\2\2\u00f2\u00f4"+
		"\t\5\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6,\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fa\t\6\2\2"+
		"\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc"+
		"\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\b\26\5\2\u00fe.\3\2\2\2\u00ff"+
		"\u0101\t\7\2\2\u0100\u00ff\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u0105\3\2\2\2\u0104\u0102\3\2\2\2\u0105"+
		"\u0106\5\61\30\2\u0106\u0107\3\2\2\2\u0107\u0108\b\27\3\2\u0108\60\3\2"+
		"\2\2\u0109\u010d\5\67\33\2\u010a\u010d\59\34\2\u010b\u010d\5\65\32\2\u010c"+
		"\u0109\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010b\3\2\2\2\u010d\62\3\2\2"+
		"\2\u010e\u010f\n\b\2\2\u010f\64\3\2\2\2\u0110\u0112\5\63\31\2\u0111\u0110"+
		"\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114"+
		"\u0116\3\2\2\2\u0115\u0117\7\"\2\2\u0116\u0115\3\2\2\2\u0116\u0117\3\2"+
		"\2\2\u0117\66\3\2\2\2\u0118\u011c\7$\2\2\u0119\u011b\n\t\2\2\u011a\u0119"+
		"\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\u011f\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0120\7$\2\2\u01208\3\2\2\2\u0121"+
		"\u0125\7)\2\2\u0122\u0124\n\n\2\2\u0123\u0122\3\2\2\2\u0124\u0127\3\2"+
		"\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0128\3\2\2\2\u0127"+
		"\u0125\3\2\2\2\u0128\u0129\7)\2\2\u0129:\3\2\2\2\31\2\3\4CPYl\u0082\u008d"+
		"\u00ad\u00be\u00c4\u00cd\u00cf\u00da\u00f5\u00fb\u0102\u010c\u0113\u0116"+
		"\u011c\u0125\6\7\3\2\6\2\2\7\4\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}