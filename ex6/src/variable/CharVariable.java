package variable;

public class CharVariable extends Variable{

	public CharVariable(boolean isFinal, String name) {super(isFinal,name);}

	/**
	 * @param value
	 */
	@Override
	public boolean isAssignmentValid(String value) {

	}

	@Override
	public Type getType() {
		return Type.CHAR;
	}
}
