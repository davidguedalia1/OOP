package variable;

/**
 * this class represents a Variable in the program
 */
public abstract class Variable {
	/*
	the name of the Variable
	 */
	protected String name;
	/*
	is the Variable final
	 */
	protected boolean isFinal;

	/**
	 *constructor for the class.
	 * @param isFinal is the Variable final
	 * @param name the name of the Variable
	 */
	Variable(Boolean isFinal,String name){
		this.name = name;
		this.isFinal = isFinal;
	}

	public String getName() {
		return name;
	}

	/**
	 * checks whether a string can be interpreted as value with a type that is compatible with the type if
	 * this variable
	 * @param value
	 */
	public abstract boolean isAssignmentValid(String value);


	/**
	 * checks whether the Variable can get the value of another Variable based on their type
	 * @param variable the variable we want to check
	 */

	public void isCompatibleWith(Variable variable) throws AssignmentException {
		if(variable.getType()!=this.getType()){
			throw new AssignmentException();
		}

	}

	/**
	 * return the type of this Variable
	 * @return the type of this Variable
	 */
	public abstract Type getType();

}
