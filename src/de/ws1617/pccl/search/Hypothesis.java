package de.ws1617.pccl.search;

import java.util.ArrayList;

import de.ws1617.pccl.fsa.Edge;

public class Hypothesis
{
	private int state;
	private int inputIndex;
	private ArrayList<Edge> edges;//not sure about that but i think somehow it should
	//show all edges processed till now , nop?!
	private ArrayList<Integer> index;
	
	
	/**
	 * Constructor 
	 * @param s
	 * @param i
	 */
	public Hypothesis(int s, int i)
	{
		state = s;
		inputIndex = i;
		index = new ArrayList<>();
		
	}

	public void setIndex(ArrayList<Integer> in)
	{
		index = in;
	}
	public int getState()
	{
		return state;
	}
	
	public int getInputIndex()
	{
		return inputIndex;
	}
	
	/**
	 * clones the hypothesis adding all index we had so far
	 */
	public Hypothesis clone()
	{
		Hypothesis h = new Hypothesis(state, inputIndex);
		ArrayList<Integer> array = new ArrayList<>();
		for(Integer i : index)
		{
			array.add(i);
		}
		h.setIndex(array);
		return h;
	}
	
}
