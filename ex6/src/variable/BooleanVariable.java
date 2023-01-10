package variable;

public class BooleanVariable extends Variable{
	public BooleanVariable(boolean isFinal, String name) {super(isFinal,name);}

	/**
	 * checks whether a string can be interpreted as a bool
	 * @param value a string containing the value that we want to check
	 */
	@Override
	public boolean isAssignmentValid(String value) {

	}

	/**
	 * return the type of this Variable
	 * @return the type of this Variable
	 */
	@Override
	public Type getType() {
		return Type.BOOLEAN;
	}


}
