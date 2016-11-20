package de.ws1617.pccl.search;

import java.util.ArrayList;

import de.ws1617.pccl.grammar.Terminal;

public class Hypothesis
{
	private int state;
	//private int inputIndex; not necessary , we're using last token
	//private ArrayList<Edge> edges; // shouldn't be necessary
	private ArrayList<Terminal> terminals;
	
	
	/**
	 * Constructor 
	 * @param s
	 * @param i
	 */
	public Hypothesis(int s, int i/* for spec compliance */)
	{
		state = s;
		terminals = new ArrayList<>();
		
	}

	public void setTerminals(ArrayList<Terminal> t)
	{
		terminals = t;
	}
	public int getState()
	{
		return state;
	}
	
	
	/**
	 * clones the hypothesis adding all index we had so far
	 */
	public Hypothesis clone()
	{
		Hypothesis h = new Hypothesis(state, 0);
		ArrayList<Terminal> arrayTerminals = new ArrayList<>();
		for(Terminal t: terminals)
		{
			arrayTerminals.add(t.clone());
		}
		h.setTerminals(arrayTerminals);
		return h;
	}
	
}
