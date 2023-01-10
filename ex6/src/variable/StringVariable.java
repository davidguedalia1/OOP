package variable;

public class StringVariable extends Variable{
	/**
	 * constructor for the class.
	 * @param isFinal is the Variable final
	 * @param name the name of the Variable
	 */
	StringVariable(Boolean isFinal, String name) {
		super(isFinal, name);
	}

	/**
	 * @param value
	 */
	@Override
	public boolean isAssignmentValid(String value) {

	}



	@Override
	public Type getType() {
		return Type.STRING;
	}
}
