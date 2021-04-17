// Generated from com\github\feltavoni\offensive\playbook\compiler\PlaybookGrammar.g4 by ANTLR 4.9.1
package com.github.feltavoni.offensive.playbook.compiler;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PlaybookGrammarParser}.
 */
public interface PlaybookGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PlaybookGrammarParser#playbook}.
	 * @param ctx the parse tree
	 */
	void enterPlaybook(PlaybookGrammarParser.PlaybookContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlaybookGrammarParser#playbook}.
	 * @param ctx the parse tree
	 */
	void exitPlaybook(PlaybookGrammarParser.PlaybookContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlaybookGrammarParser#roster}.
	 * @param ctx the parse tree
	 */
	void enterRoster(PlaybookGrammarParser.RosterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlaybookGrammarParser#roster}.
	 * @param ctx the parse tree
	 */
	void exitRoster(PlaybookGrammarParser.RosterContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlaybookGrammarParser#player}.
	 * @param ctx the parse tree
	 */
	void enterPlayer(PlaybookGrammarParser.PlayerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlaybookGrammarParser#player}.
	 * @param ctx the parse tree
	 */
	void exitPlayer(PlaybookGrammarParser.PlayerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlaybookGrammarParser#yard_line}.
	 * @param ctx the parse tree
	 */
	void enterYard_line(PlaybookGrammarParser.Yard_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlaybookGrammarParser#yard_line}.
	 * @param ctx the parse tree
	 */
	void exitYard_line(PlaybookGrammarParser.Yard_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlaybookGrammarParser#strategy}.
	 * @param ctx the parse tree
	 */
	void enterStrategy(PlaybookGrammarParser.StrategyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlaybookGrammarParser#strategy}.
	 * @param ctx the parse tree
	 */
	void exitStrategy(PlaybookGrammarParser.StrategyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlaybookGrammarParser#formation}.
	 * @param ctx the parse tree
	 */
	void enterFormation(PlaybookGrammarParser.FormationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlaybookGrammarParser#formation}.
	 * @param ctx the parse tree
	 */
	void exitFormation(PlaybookGrammarParser.FormationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlaybookGrammarParser#position}.
	 * @param ctx the parse tree
	 */
	void enterPosition(PlaybookGrammarParser.PositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlaybookGrammarParser#position}.
	 * @param ctx the parse tree
	 */
	void exitPosition(PlaybookGrammarParser.PositionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlaybookGrammarParser#route}.
	 * @param ctx the parse tree
	 */
	void enterRoute(PlaybookGrammarParser.RouteContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlaybookGrammarParser#route}.
	 * @param ctx the parse tree
	 */
	void exitRoute(PlaybookGrammarParser.RouteContext ctx);
}