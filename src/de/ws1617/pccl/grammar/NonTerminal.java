package de.ws1617.pccl.grammar;

public class NonTerminal extends Symbol {

	public NonTerminal(String value) {

		// don't alter input argument
		String toSet = String.valueOf(value);
		if (toSet.startsWith("[") && toSet.endsWith("]")) {
			toSet = toSet.substring(1, toSet.length() - 1);
		}
		setValue(toSet);
	}

	public static boolean isNonTerminal(String symb) {
		return symb.startsWith("[") && symb.endsWith("]");
	}

	public NonTerminal clone() {
		return new NonTerminal(String.valueOf(getValue()));
	}

}
