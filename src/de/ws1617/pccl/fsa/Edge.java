package de.ws1617.pccl.fsa;

import de.ws1617.pccl.grammar.Terminal;

/**
 * A directed edge (transition) between states.
 *
 */
public class Edge {
	
	int goal;
	Terminal toConsume;
	
	public Edge(int g , Terminal t)
	{
		goal=g;
		toConsume=t;
	}
	
}
