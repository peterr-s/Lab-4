package de.ws1617.pccl.grammar;

public class Terminal extends Symbol {

	public Terminal(String value) {
		super(value);
	}

	public Terminal clone(){
		return new Terminal(String.valueOf(getValue()));
	}
	
}
