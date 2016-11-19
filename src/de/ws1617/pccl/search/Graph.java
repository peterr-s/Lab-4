package de.ws1617.pccl.search;

import java.util.ArrayList;
import java.util.HashSet;

import de.ws1617.pccl.fsa.Edge;
import de.ws1617.pccl.grammar.Terminal;

public class Graph {

	private ArrayList<HashSet<Edge>> adj;
	// for each state indicate whether it is final
	private boolean[] finalStates;
	
	/**
	 * Initialize the adjacency and final state array.
	 * 
	 * @param v
	 *            the number of vertices in the graph.
	 */
	public Graph(int v) {
		adj = new ArrayList<HashSet<Edge>>();
		finalStates = new boolean[v];
	}

	public void addEdge(int from, Edge edge)
	{
		// TODO implement me !
	}

	public HashSet<Edge> getAdjacent(int from)
	{
		// not sure if this is what he wants
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
			if(candidate.getConsumed().equals(toConsume))
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