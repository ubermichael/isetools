// Generated from src/ca/nines/ise/grammar/ISEParser.g4 by ANTLR 4.2.2
package ca.nines.ise.grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ISEParser}.
 */
public interface ISEParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ISEParser#endTag}.
	 * @param ctx the parse tree
	 */
	void enterEndTag(@NotNull ISEParser.EndTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#endTag}.
	 * @param ctx the parse tree
	 */
	void exitEndTag(@NotNull ISEParser.EndTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#charComplexLigature}.
	 * @param ctx the parse tree
	 */
	void enterCharComplexLigature(@NotNull ISEParser.CharComplexLigatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#charComplexLigature}.
	 * @param ctx the parse tree
	 */
	void exitCharComplexLigature(@NotNull ISEParser.CharComplexLigatureContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#startTag}.
	 * @param ctx the parse tree
	 */
	void enterStartTag(@NotNull ISEParser.StartTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#startTag}.
	 * @param ctx the parse tree
	 */
	void exitStartTag(@NotNull ISEParser.StartTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(@NotNull ISEParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(@NotNull ISEParser.ElementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#charSpace}.
	 * @param ctx the parse tree
	 */
	void enterCharSpace(@NotNull ISEParser.CharSpaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#charSpace}.
	 * @param ctx the parse tree
	 */
	void exitCharSpace(@NotNull ISEParser.CharSpaceContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#charDigraph}.
	 * @param ctx the parse tree
	 */
	void enterCharDigraph(@NotNull ISEParser.CharDigraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#charDigraph}.
	 * @param ctx the parse tree
	 */
	void exitCharDigraph(@NotNull ISEParser.CharDigraphContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#charTypographic}.
	 * @param ctx the parse tree
	 */
	void enterCharTypographic(@NotNull ISEParser.CharTypographicContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#charTypographic}.
	 * @param ctx the parse tree
	 */
	void exitCharTypographic(@NotNull ISEParser.CharTypographicContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#charAccent}.
	 * @param ctx the parse tree
	 */
	void enterCharAccent(@NotNull ISEParser.CharAccentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#charAccent}.
	 * @param ctx the parse tree
	 */
	void exitCharAccent(@NotNull ISEParser.CharAccentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#tag}.
	 * @param ctx the parse tree
	 */
	void enterTag(@NotNull ISEParser.TagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#tag}.
	 * @param ctx the parse tree
	 */
	void exitTag(@NotNull ISEParser.TagContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(@NotNull ISEParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(@NotNull ISEParser.AttributeContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#abbr}.
	 * @param ctx the parse tree
	 */
	void enterAbbr(@NotNull ISEParser.AbbrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#abbr}.
	 * @param ctx the parse tree
	 */
	void exitAbbr(@NotNull ISEParser.AbbrContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#charSimpleLigature}.
	 * @param ctx the parse tree
	 */
	void enterCharSimpleLigature(@NotNull ISEParser.CharSimpleLigatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#charSimpleLigature}.
	 * @param ctx the parse tree
	 */
	void exitCharSimpleLigature(@NotNull ISEParser.CharSimpleLigatureContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#charUnicode}.
	 * @param ctx the parse tree
	 */
	void enterCharUnicode(@NotNull ISEParser.CharUnicodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#charUnicode}.
	 * @param ctx the parse tree
	 */
	void exitCharUnicode(@NotNull ISEParser.CharUnicodeContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#character}.
	 * @param ctx the parse tree
	 */
	void enterCharacter(@NotNull ISEParser.CharacterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#character}.
	 * @param ctx the parse tree
	 */
	void exitCharacter(@NotNull ISEParser.CharacterContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(@NotNull ISEParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(@NotNull ISEParser.ContentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void enterAttributeName(@NotNull ISEParser.AttributeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void exitAttributeName(@NotNull ISEParser.AttributeNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#emptyTag}.
	 * @param ctx the parse tree
	 */
	void enterEmptyTag(@NotNull ISEParser.EmptyTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#emptyTag}.
	 * @param ctx the parse tree
	 */
	void exitEmptyTag(@NotNull ISEParser.EmptyTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(@NotNull ISEParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(@NotNull ISEParser.DocumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#attributeValue}.
	 * @param ctx the parse tree
	 */
	void enterAttributeValue(@NotNull ISEParser.AttributeValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#attributeValue}.
	 * @param ctx the parse tree
	 */
	void exitAttributeValue(@NotNull ISEParser.AttributeValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(@NotNull ISEParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(@NotNull ISEParser.TagNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(@NotNull ISEParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(@NotNull ISEParser.CommentContext ctx);
}