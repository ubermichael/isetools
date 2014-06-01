// Generated from grammar/ISEParser.g4 by ANTLR 4.2.2
package ca.nines.ise.grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ISEParser}.
 */
public interface ISEParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ISEParser#char_ligature}.
	 * @param ctx the parse tree
	 */
	void enterChar_ligature(@NotNull ISEParser.Char_ligatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#char_ligature}.
	 * @param ctx the parse tree
	 */
	void exitChar_ligature(@NotNull ISEParser.Char_ligatureContext ctx);

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
	 * Enter a parse tree produced by {@link ISEParser#char_typographic}.
	 * @param ctx the parse tree
	 */
	void enterChar_typographic(@NotNull ISEParser.Char_typographicContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#char_typographic}.
	 * @param ctx the parse tree
	 */
	void exitChar_typographic(@NotNull ISEParser.Char_typographicContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#char_accented}.
	 * @param ctx the parse tree
	 */
	void enterChar_accented(@NotNull ISEParser.Char_accentedContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#char_accented}.
	 * @param ctx the parse tree
	 */
	void exitChar_accented(@NotNull ISEParser.Char_accentedContext ctx);

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
	 * Enter a parse tree produced by {@link ISEParser#char_spaced}.
	 * @param ctx the parse tree
	 */
	void enterChar_spaced(@NotNull ISEParser.Char_spacedContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#char_spaced}.
	 * @param ctx the parse tree
	 */
	void exitChar_spaced(@NotNull ISEParser.Char_spacedContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#start_tag}.
	 * @param ctx the parse tree
	 */
	void enterStart_tag(@NotNull ISEParser.Start_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#start_tag}.
	 * @param ctx the parse tree
	 */
	void exitStart_tag(@NotNull ISEParser.Start_tagContext ctx);

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
	 * Enter a parse tree produced by {@link ISEParser#attribute_name}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_name(@NotNull ISEParser.Attribute_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#attribute_name}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_name(@NotNull ISEParser.Attribute_nameContext ctx);

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
	 * Enter a parse tree produced by {@link ISEParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_value(@NotNull ISEParser.Attribute_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_value(@NotNull ISEParser.Attribute_valueContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#end_tag}.
	 * @param ctx the parse tree
	 */
	void enterEnd_tag(@NotNull ISEParser.End_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#end_tag}.
	 * @param ctx the parse tree
	 */
	void exitEnd_tag(@NotNull ISEParser.End_tagContext ctx);

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
	 * Enter a parse tree produced by {@link ISEParser#short_tag}.
	 * @param ctx the parse tree
	 */
	void enterShort_tag(@NotNull ISEParser.Short_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#short_tag}.
	 * @param ctx the parse tree
	 */
	void exitShort_tag(@NotNull ISEParser.Short_tagContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#char_named}.
	 * @param ctx the parse tree
	 */
	void enterChar_named(@NotNull ISEParser.Char_namedContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#char_named}.
	 * @param ctx the parse tree
	 */
	void exitChar_named(@NotNull ISEParser.Char_namedContext ctx);

	/**
	 * Enter a parse tree produced by {@link ISEParser#empty_tag}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_tag(@NotNull ISEParser.Empty_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ISEParser#empty_tag}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_tag(@NotNull ISEParser.Empty_tagContext ctx);
}