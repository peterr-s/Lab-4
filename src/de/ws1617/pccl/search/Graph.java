package de.ws1617.pccl.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import de.ws1617.pccl.fsa.Edge;
import de.ws1617.pccl.grammar.NonTerminal;
import de.ws1617.pccl.grammar.Terminal;

public class Graph
{
	// list of nodes, each of which has a set of references to other nodes
	private ArrayList<HashSet<Edge>> adj;
	private ArrayList<NonTerminal> nonterminalMapper; // used to associate a nonterminal with an index in the other array (Map can't be used here because of the need for index support)
	
	// for each state indicate whether it is final
	private boolean[] finalStates;
	
	/**
	 * Initialize the adjacency and final state array.
	 * 
	 * @param v - the number of vertices in the graph.
	 */
	public Graph(int v)
	{
		// initialize containers
		adj = new ArrayList<HashSet<Edge>>();
		finalStates = new boolean[v];
		
		// initialize elements
		for(int i = 0; i < v; i ++)
		{
			adj.add(new HashSet<Edge>());
			finalStates[i] = false;
		}
	}
	
	public Graph(Collection<NonTerminal> nodes)
	{
		this(nodes.size()); // this is why it needs to be Collection, not Iterable (constructor call at beginning, therefore need size())
		
		nonterminalMapper = new ArrayList<NonTerminal>();
		for(NonTerminal node : nodes)
			nonterminalMapper.add(node);
	}

	public void addEdge(int from, Edge edge)
	{
		if(from < 0 || from >= adj.size()) // prevent indices out of bounds
			return;
		
		adj.get(from).add(edge);
	}

	public HashSet<Edge> getAdjacent(int from)
	{
		return adj.get(from);
	}

	/**
	 * Returns all edges that point from a certain state to adjacent states and can be reached when consuimg the given {@link Terminal}.
	 * 
	 * @param from the current state.
	 * @param toConsume the next terminal to consume.
	 * @return a set of edges adjacent to the from state reachable via the terminal toConsume.
	 */
	public HashSet<Edge> getAdjacent(int from, Terminal toConsume)
	{
		HashSet<Edge> adjacentAndConsuming = new HashSet<Edge>();
		for(Edge candidate : adj.get(from))
		{
			if(candidate.getToConsume().equals(toConsume))
				adjacentAndConsuming.add(candidate);
		}
		return adjacentAndConsuming;
	}

	/**
	 * Make a certain state at the given index a final state.
	 * @param index 
	 */
	public void setFinalState(int index) {
		finalStates[index] = true;
	}

	/**
	 * Checks whether the state at the given index is a final state.
	 * @param index
	 * @return whether or not the state is final
	 */
	public boolean isFinalState(int index) {
		return finalStates[index];
	}
}