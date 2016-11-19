package de.ws1617.pccl.grammar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GrammarUtils {

	/**
	 * Reads a grammar from a file into a grammar data structure.
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static Grammar readGrammar(String path) throws IOException, FileNotFoundException {

		Grammar gr = new Grammar();

		String line = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));

		while ((line = br.readLine()) != null) {
			if (!line.trim().equals("")) {
				String[] splt = line.split("-->");
				String lhs = splt[0].trim();
				String rhs = splt[1].trim();

				// the rule left hand side
				NonTerminal lhsSymb = new NonTerminal(lhs);

				// iterate over rhs
				String[] rhsSplit = rhs.split("\\s+");
				ArrayList<Symbol> rhsList = new ArrayList<>();
				for (int i = 0; i < rhsSplit.length; i++) {
					String symb = rhsSplit[i];
					Symbol s = null;
					if (NonTerminal.isNonTerminal(symb)) {
						s = new NonTerminal(symb);
					} else {
						s = new Terminal(symb);
					}
					rhsList.add(s);
				}
				gr.addRule(lhsSymb, rhsList);
			}
		}
		br.close();

		return gr;
	}

	/**
	 * Reads lexical rules into a lexicon data structure.
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static Lexicon readLexicon(String path) throws IOException, FileNotFoundException {
		Lexicon lex = new Lexicon();

		String line = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));

		while ((line = br.readLine()) != null) {
			if (!line.trim().equals("")) {
				String[] splt = line.split("-->");
				String lhs = splt[0].trim();
				String rhs = splt[1].trim();

				// the rule left hand side
				NonTerminal lhsSymb = new NonTerminal(lhs);

				String[] rhsSplit = rhs.split("\\s+");
				ArrayList<Terminal> rhsList = new ArrayList<>();

				for (int i = 0; i < rhsSplit.length; i++) {
					rhsList.add(new Terminal(rhsSplit[i]));
				}
				lex.addRule(lhsSymb, rhsList);
			}
		}
		br.close();
		return lex;
	}
}
