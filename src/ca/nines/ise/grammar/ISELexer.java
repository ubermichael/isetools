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
		TAG_START=11, TEXT=12, TAG_END=13, TAG_SLASH_END=14, TAG_SLASH=15, TAG_EQ=16, 
		TAG_NAME=17, TAG_WS=18, ATTRIBUTE_VALUE=19, ATTRIBUTE=20;
	public static final int TAG = 1;
	public static final int ATTR = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "TAG", "ATTR"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT_GOOD", "COMMENT_BAD", "ABBREVIATION", "CHAR_UNICODE", "CHAR_DIGRAPH", 
		"CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", "CHAR_NESTED", 
		"'<'", "TEXT", "'>'", "'/>'", "'/'", "'='", "TAG_NAME", "TAG_WS", "ATTRIBUTE_VALUE", 
		"ATTRIBUTE"
	};
	public static final String[] ruleNames = {
		"COMMENT_GOOD", "COMMENT_BAD", "ABBREVIATION", "CHAR_UNICODE", "CHAR_DIGRAPH", 
		"CHAR_SPACE", "CHAR_ACCENT", "CHAR_TYPOGRAPHIC", "CHAR_LIGATURE", "CHAR_NESTED", 
		"TAG_START", "TEXT", "TAG_END", "TAG_SLASH_END", "TAG_SLASH", "TAG_EQ", 
		"TAG_NAME", "TAG_WS", "ATTRIBUTE_VALUE", "ATTRIBUTE", "ATTRIBUTE_CHAR", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\26\u012b\b\1\b\1"+
		"\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4"+
		"\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t"+
		"\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t"+
		"\30\4\31\t\31\3\2\3\2\3\2\3\2\3\2\3\2\7\2<\n\2\f\2\16\2?\13\2\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\7\3I\n\3\f\3\16\3L\13\3\3\3\3\3\3\4\3\4\7\4R"+
		"\n\4\f\4\16\4U\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5s\n"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u008d\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\5\7\u0098\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\5\b\u00b8\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c0\n\t\3\n\3\n\3\n\3\n"+
		"\5\n\u00c6\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\5\13\u00cf\n\13\3\13\3"+
		"\13\3\13\3\13\6\13\u00d5\n\13\r\13\16\13\u00d6\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\r\6\r\u00e0\n\r\r\r\16\r\u00e1\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\7\22\u00f5\n\22\f\22"+
		"\16\22\u00f8\13\22\3\23\6\23\u00fb\n\23\r\23\16\23\u00fc\3\23\3\23\3\24"+
		"\7\24\u0102\n\24\f\24\16\24\u0105\13\24\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\5\25\u010e\n\25\3\26\3\26\3\27\6\27\u0113\n\27\r\27\16\27\u0114"+
		"\3\27\5\27\u0118\n\27\3\30\3\30\7\30\u011c\n\30\f\30\16\30\u011f\13\30"+
		"\3\30\3\30\3\31\3\31\7\31\u0125\n\31\f\31\16\31\u0128\13\31\3\31\3\31"+
		"\4=J\2\32\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23\n\25\13\27\f\31\r\33\16\35"+
		"\17\37\20!\21#\22%\23\'\24)\25+\26-\2/\2\61\2\63\2\5\2\3\4\13\3\2~~\4"+
		"\2C\\c|\4\2>>}~\5\2\62;C\\c|\5\2\13\f\17\17\"\"\3\2\"\"\b\2$$))>>@@}}"+
		"\177\177\4\2$$>>\4\2))>>\u014d\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\3\37\3\2\2\2\3"+
		"!\3\2\2\2\3#\3\2\2\2\3%\3\2\2\2\3\'\3\2\2\2\4)\3\2\2\2\4+\3\2\2\2\5\65"+
		"\3\2\2\2\7D\3\2\2\2\tO\3\2\2\2\13r\3\2\2\2\r\u008c\3\2\2\2\17\u0097\3"+
		"\2\2\2\21\u00b7\3\2\2\2\23\u00bf\3\2\2\2\25\u00c1\3\2\2\2\27\u00c9\3\2"+
		"\2\2\31\u00da\3\2\2\2\33\u00df\3\2\2\2\35\u00e3\3\2\2\2\37\u00e7\3\2\2"+
		"\2!\u00ec\3\2\2\2#\u00ee\3\2\2\2%\u00f2\3\2\2\2\'\u00fa\3\2\2\2)\u0103"+
		"\3\2\2\2+\u010d\3\2\2\2-\u010f\3\2\2\2/\u0112\3\2\2\2\61\u0119\3\2\2\2"+
		"\63\u0122\3\2\2\2\65\66\7>\2\2\66\67\7#\2\2\678\7/\2\289\7/\2\29=\3\2"+
		"\2\2:<\13\2\2\2;:\3\2\2\2<?\3\2\2\2=>\3\2\2\2=;\3\2\2\2>@\3\2\2\2?=\3"+
		"\2\2\2@A\7/\2\2AB\7/\2\2BC\7@\2\2C\6\3\2\2\2DE\7>\2\2EF\7#\2\2FJ\3\2\2"+
		"\2GI\13\2\2\2HG\3\2\2\2IL\3\2\2\2JK\3\2\2\2JH\3\2\2\2KM\3\2\2\2LJ\3\2"+
		"\2\2MN\7@\2\2N\b\3\2\2\2OS\7~\2\2PR\n\2\2\2QP\3\2\2\2RU\3\2\2\2SQ\3\2"+
		"\2\2ST\3\2\2\2TV\3\2\2\2US\3\2\2\2VW\7~\2\2W\n\3\2\2\2XY\7}\2\2YZ\7u\2"+
		"\2Zs\7\177\2\2[\\\7}\2\2\\]\7R\2\2]s\7\177\2\2^_\7}\2\2_`\7t\2\2`s\7\177"+
		"\2\2ab\7}\2\2bc\7T\2\2cs\7\177\2\2de\7}\2\2ef\7e\2\2fs\7\177\2\2gh\7}"+
		"\2\2hi\7E\2\2is\7\177\2\2jk\7}\2\2kl\7v\2\2lm\7j\2\2ms\7\177\2\2no\7}"+
		"\2\2op\7V\2\2pq\7J\2\2qs\7\177\2\2rX\3\2\2\2r[\3\2\2\2r^\3\2\2\2ra\3\2"+
		"\2\2rd\3\2\2\2rg\3\2\2\2rj\3\2\2\2rn\3\2\2\2s\f\3\2\2\2tu\7}\2\2uv\7c"+
		"\2\2vw\7g\2\2w\u008d\7\177\2\2xy\7}\2\2yz\7C\2\2z{\7G\2\2{\u008d\7\177"+
		"\2\2|}\7}\2\2}~\7q\2\2~\177\7g\2\2\177\u008d\7\177\2\2\u0080\u0081\7}"+
		"\2\2\u0081\u0082\7Q\2\2\u0082\u0083\7G\2\2\u0083\u008d\7\177\2\2\u0084"+
		"\u0085\7}\2\2\u0085\u0086\7s\2\2\u0086\u0087\7r\2\2\u0087\u008d\7\177"+
		"\2\2\u0088\u0089\7}\2\2\u0089\u008a\7f\2\2\u008a\u008b\7d\2\2\u008b\u008d"+
		"\7\177\2\2\u008ct\3\2\2\2\u008cx\3\2\2\2\u008c|\3\2\2\2\u008c\u0080\3"+
		"\2\2\2\u008c\u0084\3\2\2\2\u008c\u0088\3\2\2\2\u008d\16\3\2\2\2\u008e"+
		"\u008f\7}\2\2\u008f\u0090\7\"\2\2\u0090\u0098\7\177\2\2\u0091\u0092\7"+
		"}\2\2\u0092\u0093\7/\2\2\u0093\u0098\7\177\2\2\u0094\u0095\7}\2\2\u0095"+
		"\u0096\7%\2\2\u0096\u0098\7\177\2\2\u0097\u008e\3\2\2\2\u0097\u0091\3"+
		"\2\2\2\u0097\u0094\3\2\2\2\u0098\20\3\2\2\2\u0099\u009a\7}\2\2\u009a\u009b"+
		"\7`\2\2\u009b\u009c\3\2\2\2\u009c\u009d\t\3\2\2\u009d\u00b8\7\177\2\2"+
		"\u009e\u009f\7}\2\2\u009f\u00a0\7$\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2"+
		"\t\3\2\2\u00a2\u00b8\7\177\2\2\u00a3\u00a4\7}\2\2\u00a4\u00a5\7)\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a7\t\3\2\2\u00a7\u00b8\7\177\2\2\u00a8\u00a9\7"+
		"}\2\2\u00a9\u00aa\7b\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\t\3\2\2\u00ac"+
		"\u00b8\7\177\2\2\u00ad\u00ae\7}\2\2\u00ae\u00af\7a\2\2\u00af\u00b0\3\2"+
		"\2\2\u00b0\u00b1\t\3\2\2\u00b1\u00b8\7\177\2\2\u00b2\u00b3\7}\2\2\u00b3"+
		"\u00b4\7\u0080\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\t\3\2\2\u00b6\u00b8"+
		"\7\177\2\2\u00b7\u0099\3\2\2\2\u00b7\u009e\3\2\2\2\u00b7\u00a3\3\2\2\2"+
		"\u00b7\u00a8\3\2\2\2\u00b7\u00ad\3\2\2\2\u00b7\u00b2\3\2\2\2\u00b8\22"+
		"\3\2\2\2\u00b9\u00ba\7}\2\2\u00ba\u00bb\7y\2\2\u00bb\u00c0\7\177\2\2\u00bc"+
		"\u00bd\7}\2\2\u00bd\u00be\7Y\2\2\u00be\u00c0\7\177\2\2\u00bf\u00b9\3\2"+
		"\2\2\u00bf\u00bc\3\2\2\2\u00c0\24\3\2\2\2\u00c1\u00c2\7}\2\2\u00c2\u00c3"+
		"\t\3\2\2\u00c3\u00c5\t\3\2\2\u00c4\u00c6\t\3\2\2\u00c5\u00c4\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\7\177\2\2\u00c8\26\3\2"+
		"\2\2\u00c9\u00ce\7}\2\2\u00ca\u00cf\t\3\2\2\u00cb\u00cf\5\13\5\2\u00cc"+
		"\u00cf\5\21\b\2\u00cd\u00cf\5\23\t\2\u00ce\u00ca\3\2\2\2\u00ce\u00cb\3"+
		"\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d4\3\2\2\2\u00d0"+
		"\u00d5\t\3\2\2\u00d1\u00d5\5\13\5\2\u00d2\u00d5\5\21\b\2\u00d3\u00d5\5"+
		"\23\t\2\u00d4\u00d0\3\2\2\2\u00d4\u00d1\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4"+
		"\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\7\177\2\2\u00d9\30\3\2\2\2\u00da"+
		"\u00db\7>\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\b\f\2\2\u00dd\32\3\2\2\2"+
		"\u00de\u00e0\n\4\2\2\u00df\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00df"+
		"\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\34\3\2\2\2\u00e3\u00e4\7@\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5\u00e6\b\16\3\2\u00e6\36\3\2\2\2\u00e7\u00e8\7\61"+
		"\2\2\u00e8\u00e9\7@\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb\b\17\3\2\u00eb"+
		" \3\2\2\2\u00ec\u00ed\7\61\2\2\u00ed\"\3\2\2\2\u00ee\u00ef\7?\2\2\u00ef"+
		"\u00f0\3\2\2\2\u00f0\u00f1\b\21\4\2\u00f1$\3\2\2\2\u00f2\u00f6\t\3\2\2"+
		"\u00f3\u00f5\t\5\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4"+
		"\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7&\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9"+
		"\u00fb\t\6\2\2\u00fa\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fa\3\2"+
		"\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\b\23\5\2\u00ff"+
		"(\3\2\2\2\u0100\u0102\t\7\2\2\u0101\u0100\3\2\2\2\u0102\u0105\3\2\2\2"+
		"\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u0103"+
		"\3\2\2\2\u0106\u0107\5+\25\2\u0107\u0108\3\2\2\2\u0108\u0109\b\24\3\2"+
		"\u0109*\3\2\2\2\u010a\u010e\5\61\30\2\u010b\u010e\5\63\31\2\u010c\u010e"+
		"\5/\27\2\u010d\u010a\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010c\3\2\2\2\u010e"+
		",\3\2\2\2\u010f\u0110\n\b\2\2\u0110.\3\2\2\2\u0111\u0113\5-\26\2\u0112"+
		"\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2"+
		"\2\2\u0115\u0117\3\2\2\2\u0116\u0118\7\"\2\2\u0117\u0116\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118\60\3\2\2\2\u0119\u011d\7$\2\2\u011a\u011c\n\t\2\2"+
		"\u011b\u011a\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e"+
		"\3\2\2\2\u011e\u0120\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0121\7$\2\2\u0121"+
		"\62\3\2\2\2\u0122\u0126\7)\2\2\u0123\u0125\n\n\2\2\u0124\u0123\3\2\2\2"+
		"\u0125\u0128\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0129"+
		"\3\2\2\2\u0128\u0126\3\2\2\2\u0129\u012a\7)\2\2\u012a\64\3\2\2\2\32\2"+
		"\3\4=JSr\u008c\u0097\u00b7\u00bf\u00c5\u00ce\u00d4\u00d6\u00e1\u00f6\u00fc"+
		"\u0103\u010d\u0114\u0117\u011d\u0126\6\7\3\2\6\2\2\7\4\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}