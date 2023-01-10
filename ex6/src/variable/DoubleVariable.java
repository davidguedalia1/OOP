package variable;

public class DoubleVariable extends Variable {

	public DoubleVariable(boolean isFinal, String name) {super(isFinal,name);}

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
		return Type.DOUBLE;
	}
}
