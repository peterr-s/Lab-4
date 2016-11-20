package de.ws1617.pccl.app;

import java.io.IOException;

import de.ws1617.pccl.grammar.Grammar;
import de.ws1617.pccl.grammar.GrammarUtils;
import de.ws1617.pccl.grammar.Lexicon;
import de.ws1617.pccl.grammar.NonTerminal;
import de.ws1617.pccl.search.Automaton;

public class Main {

	public static void main(String[] args) {

		// read runtime arguments
		try {
			Grammar grammar = GrammarUtils.readGrammar(args[0]);
			Lexicon lexicon = GrammarUtils.readLexicon(args[1]);
			NonTerminal startSymbol = new NonTerminal(args[2]);
			String input = args[3]; // alone, this would just get the first word
			for(int i = 4; i < args.length; i ++)
				input += args[i];

			Automaton auto = new Automaton(grammar,lexicon,startSymbol);
			if(auto.recognize(input)==true)
				System.out.println("This is sentence is in the language : "+input);
			else
				System.out.println("This sentence was not recognized in the language ! " );

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(IndexOutOfBoundsException e) // thrown if there's an issue reading args, i.e. if there aren't enough arguments
		{
			System.err.println("Not enough arguments");
			System.err.println("Usage: " + new java.io.File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName() + " grammar lexicon start input");
		}
	}
}
