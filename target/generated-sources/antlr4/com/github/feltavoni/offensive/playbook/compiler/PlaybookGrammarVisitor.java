// Generated from com\github\feltavoni\offensive\playbook\compiler\PlaybookGrammar.g4 by ANTLR 4.9.1
package com.github.feltavoni.offensive.playbook.compiler;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PlaybookGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PlaybookGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PlaybookGrammarParser#playbook}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlaybook(PlaybookGrammarParser.PlaybookContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlaybookGrammarParser#roster}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoster(PlaybookGrammarParser.RosterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlaybookGrammarParser#player}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlayer(PlaybookGrammarParser.PlayerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlaybookGrammarParser#yard_line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYard_line(PlaybookGrammarParser.Yard_lineContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlaybookGrammarParser#strategy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrategy(PlaybookGrammarParser.StrategyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlaybookGrammarParser#formation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormation(PlaybookGrammarParser.FormationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlaybookGrammarParser#position}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPosition(PlaybookGrammarParser.PositionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlaybookGrammarParser#route}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoute(PlaybookGrammarParser.RouteContext ctx);
}