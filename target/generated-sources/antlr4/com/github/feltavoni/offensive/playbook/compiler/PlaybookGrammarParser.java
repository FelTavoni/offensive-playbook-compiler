// Generated from com\github\feltavoni\offensive\playbook\compiler\PlaybookGrammar.g4 by ANTLR 4.9.1
package com.github.feltavoni.offensive.playbook.compiler;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PlaybookGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		KEYWORDS=10, NUMBER=11, ROLE=12, PLAYMAKE=13, ROUTE=14, POSITION=15, IDENT=16, 
		DELIM=17, WS=18, ERROR=19;
	public static final int
		RULE_playbook = 0, RULE_roster = 1, RULE_player = 2, RULE_yard_line = 3, 
		RULE_strategy = 4, RULE_formation = 5, RULE_position = 6, RULE_route = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"playbook", "roster", "player", "yard_line", "strategy", "formation", 
			"position", "route"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Roster:'", "'Yard Line:'", "'Play Type:'", "'Formation:'", "'Positions:'", 
			"'Routes:'", "'('", "'Main'", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "KEYWORDS", 
			"NUMBER", "ROLE", "PLAYMAKE", "ROUTE", "POSITION", "IDENT", "DELIM", 
			"WS", "ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PlaybookGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PlaybookGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PlaybookContext extends ParserRuleContext {
		public RosterContext roster() {
			return getRuleContext(RosterContext.class,0);
		}
		public Yard_lineContext yard_line() {
			return getRuleContext(Yard_lineContext.class,0);
		}
		public StrategyContext strategy() {
			return getRuleContext(StrategyContext.class,0);
		}
		public PlaybookContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_playbook; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).enterPlaybook(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).exitPlaybook(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlaybookGrammarVisitor ) return ((PlaybookGrammarVisitor<? extends T>)visitor).visitPlaybook(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlaybookContext playbook() throws RecognitionException {
		PlaybookContext _localctx = new PlaybookContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_playbook);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			roster();
			setState(17);
			yard_line();
			setState(18);
			strategy();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RosterContext extends ParserRuleContext {
		public List<PlayerContext> player() {
			return getRuleContexts(PlayerContext.class);
		}
		public PlayerContext player(int i) {
			return getRuleContext(PlayerContext.class,i);
		}
		public RosterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roster; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).enterRoster(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).exitRoster(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlaybookGrammarVisitor ) return ((PlaybookGrammarVisitor<? extends T>)visitor).visitRoster(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RosterContext roster() throws RecognitionException {
		RosterContext _localctx = new RosterContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_roster);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(T__0);
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NUMBER) {
				{
				{
				setState(21);
				player();
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlayerContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(PlaybookGrammarParser.NUMBER, 0); }
		public TerminalNode ROLE() { return getToken(PlaybookGrammarParser.ROLE, 0); }
		public List<TerminalNode> IDENT() { return getTokens(PlaybookGrammarParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(PlaybookGrammarParser.IDENT, i);
		}
		public PlayerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_player; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).enterPlayer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).exitPlayer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlaybookGrammarVisitor ) return ((PlaybookGrammarVisitor<? extends T>)visitor).visitPlayer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlayerContext player() throws RecognitionException {
		PlayerContext _localctx = new PlayerContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_player);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(NUMBER);
			setState(28);
			match(ROLE);
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				match(IDENT);
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Yard_lineContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(PlaybookGrammarParser.NUMBER, 0); }
		public Yard_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yard_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).enterYard_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).exitYard_line(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlaybookGrammarVisitor ) return ((PlaybookGrammarVisitor<? extends T>)visitor).visitYard_line(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Yard_lineContext yard_line() throws RecognitionException {
		Yard_lineContext _localctx = new Yard_lineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_yard_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__1);
			setState(35);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrategyContext extends ParserRuleContext {
		public TerminalNode PLAYMAKE() { return getToken(PlaybookGrammarParser.PLAYMAKE, 0); }
		public List<FormationContext> formation() {
			return getRuleContexts(FormationContext.class);
		}
		public FormationContext formation(int i) {
			return getRuleContext(FormationContext.class,i);
		}
		public List<PositionContext> position() {
			return getRuleContexts(PositionContext.class);
		}
		public PositionContext position(int i) {
			return getRuleContext(PositionContext.class,i);
		}
		public List<RouteContext> route() {
			return getRuleContexts(RouteContext.class);
		}
		public RouteContext route(int i) {
			return getRuleContext(RouteContext.class,i);
		}
		public StrategyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strategy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).enterStrategy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).exitStrategy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlaybookGrammarVisitor ) return ((PlaybookGrammarVisitor<? extends T>)visitor).visitStrategy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrategyContext strategy() throws RecognitionException {
		StrategyContext _localctx = new StrategyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_strategy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(T__2);
			setState(38);
			match(PLAYMAKE);
			setState(39);
			match(T__3);
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				formation();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMBER );
			setState(45);
			match(T__4);
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46);
				position();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMBER );
			setState(51);
			match(T__5);
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52);
				route();
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormationContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(PlaybookGrammarParser.NUMBER, 0); }
		public TerminalNode ROLE() { return getToken(PlaybookGrammarParser.ROLE, 0); }
		public FormationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).enterFormation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).exitFormation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlaybookGrammarVisitor ) return ((PlaybookGrammarVisitor<? extends T>)visitor).visitFormation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormationContext formation() throws RecognitionException {
		FormationContext _localctx = new FormationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_formation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(NUMBER);
			setState(58);
			match(ROLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PositionContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(PlaybookGrammarParser.NUMBER, 0); }
		public TerminalNode POSITION() { return getToken(PlaybookGrammarParser.POSITION, 0); }
		public PositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_position; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).enterPosition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).exitPosition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlaybookGrammarVisitor ) return ((PlaybookGrammarVisitor<? extends T>)visitor).visitPosition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PositionContext position() throws RecognitionException {
		PositionContext _localctx = new PositionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_position);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(NUMBER);
			setState(61);
			match(POSITION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RouteContext extends ParserRuleContext {
		public Token main;
		public TerminalNode NUMBER() { return getToken(PlaybookGrammarParser.NUMBER, 0); }
		public TerminalNode ROUTE() { return getToken(PlaybookGrammarParser.ROUTE, 0); }
		public RouteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_route; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).enterRoute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlaybookGrammarListener ) ((PlaybookGrammarListener)listener).exitRoute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlaybookGrammarVisitor ) return ((PlaybookGrammarVisitor<? extends T>)visitor).visitRoute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RouteContext route() throws RecognitionException {
		RouteContext _localctx = new RouteContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_route);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(NUMBER);
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(64);
				match(T__6);
				setState(65);
				((RouteContext)_localctx).main = match(T__7);
				setState(66);
				match(T__8);
				}
			}

			setState(69);
			match(ROUTE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25J\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\7\3\31\n\3\f\3\16\3\34\13\3\3\4\3\4\3\4\6\4!\n\4\r\4\16\4\"\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\6\6,\n\6\r\6\16\6-\3\6\3\6\6\6\62\n\6\r\6\16\6"+
		"\63\3\6\3\6\6\68\n\6\r\6\16\69\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\5\tF\n\t\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2G\2\22\3\2\2\2\4\26"+
		"\3\2\2\2\6\35\3\2\2\2\b$\3\2\2\2\n\'\3\2\2\2\f;\3\2\2\2\16>\3\2\2\2\20"+
		"A\3\2\2\2\22\23\5\4\3\2\23\24\5\b\5\2\24\25\5\n\6\2\25\3\3\2\2\2\26\32"+
		"\7\3\2\2\27\31\5\6\4\2\30\27\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2\2\32\33"+
		"\3\2\2\2\33\5\3\2\2\2\34\32\3\2\2\2\35\36\7\r\2\2\36 \7\16\2\2\37!\7\22"+
		"\2\2 \37\3\2\2\2!\"\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\7\3\2\2\2$%\7\4\2\2"+
		"%&\7\r\2\2&\t\3\2\2\2\'(\7\5\2\2()\7\17\2\2)+\7\6\2\2*,\5\f\7\2+*\3\2"+
		"\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2./\3\2\2\2/\61\7\7\2\2\60\62\5\16\b"+
		"\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\65\3\2\2"+
		"\2\65\67\7\b\2\2\668\5\20\t\2\67\66\3\2\2\289\3\2\2\29\67\3\2\2\29:\3"+
		"\2\2\2:\13\3\2\2\2;<\7\r\2\2<=\7\16\2\2=\r\3\2\2\2>?\7\r\2\2?@\7\21\2"+
		"\2@\17\3\2\2\2AE\7\r\2\2BC\7\t\2\2CD\7\n\2\2DF\7\13\2\2EB\3\2\2\2EF\3"+
		"\2\2\2FG\3\2\2\2GH\7\20\2\2H\21\3\2\2\2\b\32\"-\639E";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}