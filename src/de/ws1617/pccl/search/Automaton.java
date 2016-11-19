package de.ws1617.pccl.search;

import java.util.List;
import java.util.ArrayList;
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
		//super(); // not meaningful; inherits from Object

		// set start symbol
		this.startSymbol = startSymbol;
		
		// make set of nodes
		nonTerminals = new ArrayList<NonTerminal>();
		nonTerminals.add(startSymbol); // add first so that it can be defined as final state
		nonTerminals.addAll(grammar.getNonTerminals());
		nonTerminals.addAll(lexicon.getNonTerminals()); // already checks if elements are present; no need to worry about that

		// graph should hold all nonterminals
		graph = new Graph(nonTerminals.size());
		graph.setFinalState(0);
	}

	/**
	 * Returns whether the give input is licensed by the grammar or not.
	 * 
	 * @param input
	 * @return
	 */
	public boolean recognize(String input)
	{
		
		
		return true;
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
	 * @param s - the input string to be processed.
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
	public boolean isFinalState(Hypothesis h, List<Terminal> input)
	{
		// make sure the hypothesis is in a final state and the input has been entirely processed
		return (graph.isFinalState(h.getState())) && (input.size() == 0);
	}

	/**
	 * Adds edges for the rules to the automaton based on the grammar and
	 * lexicon.
	 * 
	 * @param gr - a Grammar.
	 * @param lex - a Lexicon.
	 */
	public void addRules(Grammar gr, Lexicon lex)
	{
		// for each possible nonterminal
		for(NonTerminal symbol : nonTerminals)
		{
			// find all grammatical rules
			for(ArrayList<Symbol> parse : gr.getRuleForLHS(symbol));
				// do stuff
			
			// find all lexical rules
		}
	}
}
