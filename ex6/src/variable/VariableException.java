package variable;

/**
 * this is a Exception class that deals with errors concerning variables
 */
public class VariableException extends Exception{
	/**
	 * constructor for the class gets the error message
	 * @param message error message
	 */
	VariableException(String message){
		super(message);
	}
}
