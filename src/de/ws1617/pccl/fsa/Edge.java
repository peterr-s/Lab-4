package de.ws1617.pccl.fsa;

import de.ws1617.pccl.grammar.Terminal;

/**
 * A directed edge (transition) between states.
 *
 */
public class Edge {
	
	private int goal;
	private Terminal toConsume;
	
	/**
	 * Constructor creates the Edge giving it an index
	 *  and the content of terminal 
	 * @param g the goal index of Terminal
	 * @param t the terminal stored in this edge 
	 */
	public Edge(int g, Terminal t)
	{
		goal = g;
		toConsume = t;
	}
	
	public Terminal getToConsume()
	{
		return toConsume;
	}
	
	public int getGoal()
	{
		return goal;
	}
}
