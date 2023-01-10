package variable;

public class IntVariable extends Variable{
	/**
	 * constructor for the class.
	 * @param isFinal is the Variable final
	 * @param name the name of the Variable
	 */
	IntVariable(Boolean isFinal, String name) {
		super(isFinal, name);
	}

	/**
	 * @param value
	 */
	@Override
	public boolean isAssignmentValid(String value) {

	}

	/**
	 * @param variable
	 */
	@Override
	public void isCompatibleWith(Variable variable) throws AssignmentException{
		if(variable.getType()!=Type.INT&&variable.getType()!=Type.DOUBLE){
			throw new AssignmentException();
		}


	}

	@Override
	public Type getType() {
		return Type.INT;
	}
}
