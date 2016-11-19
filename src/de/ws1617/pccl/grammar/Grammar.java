package de.ws1617.pccl.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Grammar {
	
	private HashSet<NonTerminal> nts;

	// key: NT (LHS), value: Set<List<Symbols>> (RHS)
	private HashMap<NonTerminal, HashSet<ArrayList<Symbol>>> rules;

	public Grammar() {
		super(); // this is not meaningful; Grammar inherits directly from Object
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
	
	public Set<NonTerminal> getNonTerminals() {
		return nts;
	}

}
