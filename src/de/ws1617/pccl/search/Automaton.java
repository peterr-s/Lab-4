package de.ws1617.pccl.search;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

import de.ws1617.pccl.fsa.Edge;
import de.ws1617.pccl.grammar.Grammar;
import de.ws1617.pccl.grammar.Lexicon;
import de.ws1617.pccl.grammar.NonTerminal;
import de.ws1617.pccl.grammar.Symbol;
import de.ws1617.pccl.grammar.Terminal;

public class Automaton {

	private Stack<Hypothesis> agenda;

	private ArrayList<NonTerminal> nonTerminals;

	private NonTerminal startSymbol;

	private Graph graph;

	public Automaton(Grammar grammar, Lexicon lexicon, NonTerminal startSymbol) {
		super();

		// TODO create the union of the nonterminals from lexicon and grammar

		// TODO create a graph based on the grammar and lexicon
		// attention: how many states do you need ?
	}

	/**
	 * Returns whether the give input is licensed by the grammar or not.
	 * 
	 * @param input
	 * @return
	 */
	public boolean recognize(String input) {

		// TODO implement me !

		return false;
	}

	/**
	 * Generates all successors for a given hypothesis and input.
	 * 
	 * @param h
	 * @param input
	 * @return
	 */
	private ArrayList<Hypothesis> successors(Hypothesis h, ArrayList<Terminal> input) {

		// TODO implement me !
		return null;
	}

	/**
	 * Initializes the agenda and prepares the input by splitting it and making
	 * terminals from a string..
	 * 
	 * @param s
	 *            the input string to be processed.
	 * @return a list of terminals based on the input s split by whitespaces.
	 */
	private ArrayList<Terminal> initialize(String s)
	{
		// split on all whitespace
		String[] tokenArray = s.split("\\s+");
		
		// convert to ArrayList, make Terminal for each String
		ArrayList<Terminal> tokens = new ArrayList<Terminal>();
		for(int i = 0; i < tokenArray.length; i ++)
			tokens.add(new Terminal(tokenArray[i]));

		return tokens;
	}

	/**
	 * Checks whether for a given hypothesis and input the automaton is in a
	 * final state and licenses the string. Two conditions have to be met: (a)
	 * all symbols have been processed (b) the current state is final.
	 * 
	 * @param h
	 * @param input
	 * @return
	 */
	public boolean isFinalState(Hypothesis h, List<Terminal> input) {
		// TODO implement me !
		return false;
	}

	/**
	 * Adds edges for the rules to the automaton based on the grammar and
	 * lexicon.
	 * 
	 * @param gr
	 *            a Grammar.
	 * @param lex
	 *            a Lexicon.
	 */
	public void addRules(Grammar gr, Lexicon lex) {

		// TODO implement me !

	}

}
