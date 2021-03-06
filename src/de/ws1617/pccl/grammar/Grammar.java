package de.ws1617.pccl.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Grammar {
	
	private HashSet<NonTerminal> nts; // should just use rules.keySet()

	// key: NT (LHS), value: Set<List<Symbols>> (RHS)
	private HashMap<NonTerminal, HashSet<ArrayList<Symbol>>> rules;

	/*public*/ protected Grammar() // protected so it isn't invoked outside of GrammarUtils
	{
		//super(); // this is not meaningful; Grammar inherits directly from Object
		rules = new HashMap<>();
		nts = new HashSet<>();
	}

	public void addKey(NonTerminal nt) {
		if (!rules.containsKey(nt)) {
			rules.put(nt, new HashSet<>());
		}
		nts.add(nt);
	}

	public void addRule(NonTerminal lhs, ArrayList<Symbol> rhs) {
		addKey(lhs);
		rules.get(lhs).add(rhs);
		for(int i = 0; i < rhs.size(); i++)
		{
			if(rhs.get(i) instanceof NonTerminal)
			{
				nts.add((NonTerminal) rhs.get(i));
			}
		}
	}

	public HashSet<ArrayList<Symbol>> getRuleForLHS(NonTerminal lhs) {
		if (rules.containsKey(lhs)) {
			return rules.get(lhs);
		}
		return new HashSet<ArrayList<Symbol>>();
	}

	public String prettyPrint(){
		StringBuilder sb = new StringBuilder();
		for(NonTerminal lhs : rules.keySet())
		{
			for(ArrayList<Symbol> rhs : rules.get(lhs))
			{
				sb.append(lhs);
				sb.append(" --> ");
				for(int i = 0; i < rhs.size(); i++)
				{
					sb.append(rhs.get(i));
					sb.append(" ");
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public HashSet<NonTerminal> getNonTerminals() {
		//return nts; // may not be up to date
		HashSet<NonTerminal> keys = new HashSet<NonTerminal>();
		keys.addAll(rules.keySet()); // for some reason Set can't be safely cast to HashSet (???)
		return keys;
	}

}
