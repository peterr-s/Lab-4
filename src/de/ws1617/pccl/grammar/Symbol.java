package de.ws1617.pccl.grammar;

/**
 * Generic symbol class in formal grammars.
 * 
 * @author bjoern
 *
 */
public abstract class Symbol {

	private String value;

	public Symbol(){
		//super(); // meaningless; inherits from Object
	}
	
	public Symbol(String value) {
		//super();
		this.value = value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		/*if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Symbol other = (Symbol) obj;
		if (value == null) {
			if (other.value != null) // this wouldn't work; value is private
				return false;
		} else if (!value.equals(other.value)) // another access violation
			return false;
		return true;*/
		
		// more efficient/elegant solution
		return (this == obj) // same object
				|| ((obj != null) // or both not null
						&& (getClass() == obj.getClass()) // and same class
						&& ((value == null && ((Symbol)obj).getValue() == null) // and both have a null String value
								|| value.equals(((Symbol)obj).getValue()))); // or both have the same String value
	}
	
	
}
