package de.ws1617.pccl.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * A lexicon for a generative formal grammar.
 * 
 * @author bjoern
 *
 */
public class Lexicon {

	private HashSet<Terminal> terminals;
	
	// key: pre-terminal, value: terminal
	private HashMap<NonTerminal, HashSet<ArrayList<Terminal>>> lexMap;

	public Lexicon() {
		lexMap = new HashMap<>();
		terminals = new HashSet<>();
	}

	public void addKey(NonTerminal nt) {
		if (!lexMap.containsKey(nt)) {
			lexMap.put(nt, new HashSet<>());
		}
	}

	public void addRule(NonTerminal nt, ArrayList<Terminal> rhs) {
		addKey(nt);
		lexMap.get(nt).add(rhs);
		terminals.addAll(rhs);
	}

	public HashSet<ArrayList<Terminal>> getRules(NonTerminal lhs) {
		if (lexMap.containsKey(lhs)) {
			return lexMap.get(lhs);
		}
		return new HashSet<>();
	}

	public HashSet<Terminal> getTerminals() {
		return terminals;
	}

	public Set<NonTerminal> getNonTerminals(){
		return lexMap.keySet();
	}
}
