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
		TEXT=1, ALPHA=2, DIGIT=3, ALPHANUM=4, S=5, ACCENT=6, COMMENT=7, BAD_COMMENT=8, 
		ABBR=9, CHAR_START=10, CHAR_END=11, TYPOGRAPHIC_CHAR=12, SPACE_CHAR=13, 
		NAMED_CHAR=14, SQ=15, DQ=16, FQ=17, DOUBLE_QUOTED_STR=18, SINGLE_QUOTED_STR=19, 
		UNQUOTED_STR=20, FANCY_QUOTED_STR=21, STRING=22, TAG_OPEN=23, DOC_TEXT=24, 
		TAG_CLOSE=25, TAG_SLASH_CLOSE=26, TAG_SLASH=27, TAG_EQ=28, TAG_NAME=29, 
		TAG_NameStartChar=30, TAG_NameChar=31, ATTVALUE_VALUE=32, ATTRIBUTE=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"TEXT", "ALPHA", "DIGIT", "ALPHANUM", "S", "ACCENT", "COMMENT", "BAD_COMMENT", 
		"ABBR", "'{'", "'}'", "TYPOGRAPHIC_CHAR", "SPACE_CHAR", "NAMED_CHAR", 
		"'''", "'\"'", "FQ", "DOUBLE_QUOTED_STR", "SINGLE_QUOTED_STR", "UNQUOTED_STR", 
		"FANCY_QUOTED_STR", "STRING", "'<'", "DOC_TEXT", "'>'", "'/>'", "'/'", 
		"'='", "TAG_NAME", "TAG_NameStartChar", "TAG_NameChar", "ATTVALUE_VALUE", 
		"ATTRIBUTE"
	};
	public static final String[] ruleNames = {
		"TEXT", "ALPHA", "DIGIT", "ALPHANUM", "S", "ACCENT", "COMMENT", "BAD_COMMENT", 
		"ABBR", "CHAR_START", "CHAR_END", "TYPOGRAPHIC_CHAR", "SPACE_CHAR", "NAMED_CHAR", 
		"SQ", "DQ", "FQ", "DOUBLE_QUOTED_STR", "SINGLE_QUOTED_STR", "UNQUOTED_STR", 
		"FANCY_QUOTED_STR", "STRING", "TAG_OPEN", "DOC_TEXT", "TAG_CLOSE", "TAG_SLASH_CLOSE", 
		"TAG_SLASH", "TAG_EQ", "TAG_NAME", "TAG_NameStartChar", "TAG_NameChar", 
		"ATTVALUE_VALUE", "ATTRIBUTE"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u00e8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\6\2G\n\2\r\2\16\2H\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\b[\n\b\f\b\16\b^\13\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\7\th\n\t\f\t\16\tk\13\t\3\t\3\t\3\n\3\n\7\nq\n\n\f\n\16"+
		"\nt\13\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u008d\n\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0099\n\22\3\23\3\23"+
		"\7\23\u009d\n\23\f\23\16\23\u00a0\13\23\3\23\3\23\3\24\3\24\7\24\u00a6"+
		"\n\24\f\24\16\24\u00a9\13\24\3\24\3\24\3\25\6\25\u00ae\n\25\r\25\16\25"+
		"\u00af\3\26\3\26\7\26\u00b4\n\26\f\26\16\26\u00b7\13\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\5\27\u00bf\n\27\3\30\3\30\3\31\6\31\u00c4\n\31\r\31\16"+
		"\31\u00c5\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\7\36"+
		"\u00d3\n\36\f\36\16\36\u00d6\13\36\3\37\3\37\3 \3 \3 \5 \u00dd\n \3!\7"+
		"!\u00e0\n!\f!\16!\u00e3\13!\3!\3!\3\"\3\"\5\\ir\2#\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#\3\2\20\5"+
		"\2>>@@}\177\4\2C\\c|\3\2\62;\5\2\62;C\\c|\5\2\13\f\17\17\"\"\6\2$$))`"+
		"b\u0080\u0080\5\2YYtuyy\5\2\"\"%%//\4\2eerr\4\2$$>>\4\2))>>\3\2\"\"\5"+
		"\2$$>>\uffff\uffff\4\2//aa\u00fe\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\3F\3\2\2\2\5J\3\2\2\2\7L\3\2\2\2\tN\3\2\2\2\13P\3\2\2\2\r"+
		"R\3\2\2\2\17T\3\2\2\2\21c\3\2\2\2\23n\3\2\2\2\25w\3\2\2\2\27y\3\2\2\2"+
		"\31{\3\2\2\2\33}\3\2\2\2\35\u008c\3\2\2\2\37\u008e\3\2\2\2!\u0090\3\2"+
		"\2\2#\u0098\3\2\2\2%\u009a\3\2\2\2\'\u00a3\3\2\2\2)\u00ad\3\2\2\2+\u00b1"+
		"\3\2\2\2-\u00be\3\2\2\2/\u00c0\3\2\2\2\61\u00c3\3\2\2\2\63\u00c7\3\2\2"+
		"\2\65\u00c9\3\2\2\2\67\u00cc\3\2\2\29\u00ce\3\2\2\2;\u00d0\3\2\2\2=\u00d7"+
		"\3\2\2\2?\u00dc\3\2\2\2A\u00e1\3\2\2\2C\u00e6\3\2\2\2EG\n\2\2\2FE\3\2"+
		"\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\4\3\2\2\2JK\t\3\2\2K\6\3\2\2\2LM\t"+
		"\4\2\2M\b\3\2\2\2NO\t\5\2\2O\n\3\2\2\2PQ\t\6\2\2Q\f\3\2\2\2RS\t\7\2\2"+
		"S\16\3\2\2\2TU\7>\2\2UV\7#\2\2VW\7/\2\2WX\7/\2\2X\\\3\2\2\2Y[\13\2\2\2"+
		"ZY\3\2\2\2[^\3\2\2\2\\]\3\2\2\2\\Z\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_`\7/\2"+
		"\2`a\7/\2\2ab\7@\2\2b\20\3\2\2\2cd\7>\2\2de\7#\2\2ei\3\2\2\2fh\13\2\2"+
		"\2gf\3\2\2\2hk\3\2\2\2ij\3\2\2\2ig\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm\7@\2"+
		"\2m\22\3\2\2\2nr\7~\2\2oq\13\2\2\2po\3\2\2\2qt\3\2\2\2rs\3\2\2\2rp\3\2"+
		"\2\2su\3\2\2\2tr\3\2\2\2uv\7~\2\2v\24\3\2\2\2wx\7}\2\2x\26\3\2\2\2yz\7"+
		"\177\2\2z\30\3\2\2\2{|\t\b\2\2|\32\3\2\2\2}~\t\t\2\2~\34\3\2\2\2\177\u008d"+
		"\t\n\2\2\u0080\u0081\7u\2\2\u0081\u008d\7o\2\2\u0082\u0083\7r\2\2\u0083"+
		"\u008d\7k\2\2\u0084\u0085\7c\2\2\u0085\u008d\7g\2\2\u0086\u0087\7C\2\2"+
		"\u0087\u008d\7G\2\2\u0088\u0089\7q\2\2\u0089\u008d\7g\2\2\u008a\u008b"+
		"\7Q\2\2\u008b\u008d\7G\2\2\u008c\177\3\2\2\2\u008c\u0080\3\2\2\2\u008c"+
		"\u0082\3\2\2\2\u008c\u0084\3\2\2\2\u008c\u0086\3\2\2\2\u008c\u0088\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008d\36\3\2\2\2\u008e\u008f\7)\2\2\u008f \3"+
		"\2\2\2\u0090\u0091\7$\2\2\u0091\"\3\2\2\2\u0092\u0093\7\uffff\2\2\u0093"+
		"\u0094\7\uffff\2\2\u0094\u0099\7\uffff\2\2\u0095\u0096\7\uffff\2\2\u0096"+
		"\u0097\7\uffff\2\2\u0097\u0099\7\uffff\2\2\u0098\u0092\3\2\2\2\u0098\u0095"+
		"\3\2\2\2\u0099$\3\2\2\2\u009a\u009e\5!\21\2\u009b\u009d\n\13\2\2\u009c"+
		"\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2\5!\21\2\u00a2"+
		"&\3\2\2\2\u00a3\u00a7\5\37\20\2\u00a4\u00a6\n\f\2\2\u00a5\u00a4\3\2\2"+
		"\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa"+
		"\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ab\5\37\20\2\u00ab(\3\2\2\2\u00ac"+
		"\u00ae\n\r\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad\3\2"+
		"\2\2\u00af\u00b0\3\2\2\2\u00b0*\3\2\2\2\u00b1\u00b5\5#\22\2\u00b2\u00b4"+
		"\n\16\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2"+
		"\u00b5\u00b6\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9"+
		"\5#\22\2\u00b9,\3\2\2\2\u00ba\u00bf\5%\23\2\u00bb\u00bf\5\'\24\2\u00bc"+
		"\u00bf\5)\25\2\u00bd\u00bf\5+\26\2\u00be\u00ba\3\2\2\2\u00be\u00bb\3\2"+
		"\2\2\u00be\u00bc\3\2\2\2\u00be\u00bd\3\2\2\2\u00bf.\3\2\2\2\u00c0\u00c1"+
		"\7>\2\2\u00c1\60\3\2\2\2\u00c2\u00c4\n\2\2\2\u00c3\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\62\3\2\2"+
		"\2\u00c7\u00c8\7@\2\2\u00c8\64\3\2\2\2\u00c9\u00ca\7\61\2\2\u00ca\u00cb"+
		"\7@\2\2\u00cb\66\3\2\2\2\u00cc\u00cd\7\61\2\2\u00cd8\3\2\2\2\u00ce\u00cf"+
		"\7?\2\2\u00cf:\3\2\2\2\u00d0\u00d4\5=\37\2\u00d1\u00d3\5? \2\u00d2\u00d1"+
		"\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"<\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d8\5\5\3\2\u00d8>\3\2\2\2\u00d9"+
		"\u00dd\5\5\3\2\u00da\u00dd\5\7\4\2\u00db\u00dd\t\17\2\2\u00dc\u00d9\3"+
		"\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd@\3\2\2\2\u00de\u00e0"+
		"\5\13\6\2\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2"+
		"\u00e1\u00e2\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5"+
		"\5C\"\2\u00e5B\3\2\2\2\u00e6\u00e7\5-\27\2\u00e7D\3\2\2\2\22\2H\\ir\u008c"+
		"\u0098\u009e\u00a7\u00af\u00b5\u00be\u00c5\u00d4\u00dc\u00e1\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}