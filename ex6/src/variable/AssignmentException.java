package variable;

public class AssignmentException extends VariableException{
	/**
	 * constructor for the class
	 */
	AssignmentException() {
		super(MESSAGE);
	}
	private static final String MESSAGE = "bad assignment";
}
