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
	private NonTerminal startSymbol, // not needed; kept for spec compliance
		entryPt;
	private Graph graph;

	public Automaton(Grammar grammar, Lexicon lexicon, NonTerminal startSymbol) {
		//super(); // not meaningful; inherits from Object

		// set start symbol
		this.startSymbol = startSymbol;
		
		// make set of nodes
		nonTerminals = new ArrayList<NonTerminal>();
		nonTerminals.add(startSymbol); // add first so that it can be defined as final state
		
		// this is not, strictly speaking, a nonterminal, but the entry point of the FSA
		entryPt = new NonTerminal("EntryPt");
		nonTerminals.add(entryPt);
		
		// DEBUG: show start symbol and entry point indices
		//System.out.println("start symbol:\t" + nonTerminals.indexOf(startSymbol));
		//System.out.println("entry point:\t" + nonTerminals.indexOf(entryPt));
		
		for(NonTerminal n: grammar.getNonTerminals())
		{
			if(!nonTerminals.contains(n))
				nonTerminals.add(n);
		}
		
		for(NonTerminal n: lexicon.getNonTerminals())
		{
			if(!nonTerminals.contains(n))
				nonTerminals.add(n);
		}
		
		// graph should hold all nonterminals
		graph = new Graph(nonTerminals); // use Collection constructor for nonterminal mapping to work properly
		graph.setFinalState(0);
		addRules(grammar, lexicon);
		
		// initialize agenda
		agenda = new Stack<Hypothesis>();
	}

	/**
	 * Returns whether the give input is licensed by the grammar or not.
	 * 
	 * @param input
	 * @return
	 */
	public boolean recognize(String input)
	{
		// DEBUG: show input
		//System.out.println("evaluating \"" + input + "\"");
		
		// 1 for the graph entry point
		Hypothesis init = new Hypothesis(1);
		init.setTerminals(initialize(input));
		
		// DEBUG: show starting hypothesis
		//System.out.println("h0: " + init.toString());
		
		// make agenda
		agenda.addAll(successors(init));
		for(Hypothesis h : agenda)
		{
			// DEBUG: print hypotheses
			//System.out.println("\th: " + h.toString());
			
			if(isFinalState(h)) // if there's a final state the sentence must be valid
				return true;
		}
		return false; // if not it isn't
	}

	private ArrayList<Hypothesis> successors(Hypothesis h)
	{
		return successors(h, h.getTerminals());
	}

	/**
	 * Generates all successors for a given hypothesis and input.
	 * 
	 * @param h
	 * @param input
	 * @return
	 */
	private ArrayList<Hypothesis> successors(Hypothesis h, ArrayList<Terminal> input)
	{
		// use a set so that duplicates don't add up
		HashSet<Hypothesis> hSuccessors = new HashSet<>();
		
		// make sure there are terminals left
		ArrayList<Terminal> terminals = h.getTerminals();
		if(!(terminals == null || terminals.size() == 0))
		{
			// DEBUG: getTerminals
			//System.out.println("\t\thypothesis has terminals");
			
			// take last terminal, check if it can be consumed
			Terminal lTerm = terminals.get(terminals.size() - 1);
			// DEBUG: lTerm
			//System.out.println("\t\t\tevaluating " + lTerm.toString());
			for(Edge e : graph.getAdjacent(h.getState(), lTerm))
			{
				// DEBUG: getAdjacent
				//System.out.println("\t\t\tthere are adjacent nodes");
				
				Hypothesis successor = new Hypothesis(e.getGoal());
				ArrayList<Terminal> clonedList = new ArrayList<>(); // use a cloned list to keep Hypotheses from interacting
				for(Terminal t : terminals)
					clonedList.add(t.clone());
				
				// last element is being parsed; remove it
				clonedList.remove(clonedList.size() - 1); // this is safe since clonedList is a copy of terminals, which has been checked
				successor.setTerminals(clonedList);
				
				hSuccessors.add(successor);
				hSuccessors.addAll(successors(successor));
				
			}
		}
		
		// returns it in list form
		ArrayList<Hypothesis> hSuccessorList = new ArrayList<>();
		hSuccessorList.addAll(hSuccessors);
		return hSuccessorList;
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

	private boolean isFinalState(Hypothesis h)
	{
		return isFinalState(h, h.getTerminals());
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
			int symbolIndex = graph.getIndex(symbol);
			if(symbolIndex >= 0) // >=, not > !
			{
				// find all grammatical rules
				for(ArrayList<Symbol> parse : gr.getRuleForLHS(symbol))
				{
					// check bounds
					if(parse.size() >= 2)
					{
						// grammar rule will always link one nonterminal to one terminal and one nonterminal
						Terminal t = (Terminal)parse.get(0);
						NonTerminal nT = (NonTerminal)parse.get(1);
						
						// make sure the index is found
						int index = graph.getIndex(nT);
						if(index >= 0) // this mistake? twice?! for shame Peter, you should know better
							graph.addEdge(index, new Edge(symbolIndex, t)); // rule direction is reversed to parse instead of generate
					}
				}
				
				// find all lexical rules
				// why is this an ArrayList? there's only ever one token on the left of the 
				for(ArrayList<Terminal> parse : lex.getRules(symbol))
				{
					// check bounds
					if(parse.size() >= 1)
					{
						Terminal t = (Terminal)parse.get(0);
						graph.addEdge(1, new Edge(symbolIndex, t));
					}
				}
			}
			// DEBUG: make sure all symbols are found
			//else System.out.println("error while reading grammar: " + symbol.toString() + " not found in graph");
		}
		
		// DEBUG: graph
		System.out.println(graph.toString());
	}
}
