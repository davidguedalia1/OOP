package variable;

public class NameException extends VariableException{
	/**
	 * constructor for the class
	 */
	NameException() {
		super(MESSAGE);
	}
	private static final String MESSAGE = "bad name";
}
