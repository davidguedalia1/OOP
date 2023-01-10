package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Abstract class of filter
 * @author David Guedalia
 */
public abstract class Filter {

    /**
     * The number og kilo bytes.
     */
    protected double KILO_BYTES = 1024;

    /**
     * One argument should get.
     */
    protected static final int ONE_ARGUMENT = 1;

    /**
     * Two argument should get.
     */
    protected static final int TWO_ARGUMENT = 2;

    /**
     * Three argument should get.
     */
    protected static final int THREE_ARGUMENT = 3;

    /**
     * Yes.
     */
    protected static final String YES = "YES";

    /**
     * Not.
     */
    protected static final String NOT = "NOT";

    /**
     * No.
     */
    protected static final String NO = "NO";

    /**
     * If the filter is a negative.
     */
    boolean negativeFilter;
    /**
     * Array of strings of the filter command.
     */
    protected final String[] filterCommand;

    /**
     * Constructor of Filter
     * @param filterCommand - Array of strings of the filter command.
     */
    Filter(String[] filterCommand){
        this.filterCommand = filterCommand;
    }

    /**
     * Check if the file should be filtered.
     * @param file file to check
     * @return True if the file is filtered, false otherwise.
     */
    public abstract boolean isFileFilter(File file);

    /**
     * Check if the command filter is valid.
     * @param numberOfArguments Number of arguments the command filter should have.
     * @throws TypeFirstError First type of exception.
     */
    protected void validFilter(int numberOfArguments) throws TypeFirstError {
        if (filterCommand.length ==  numberOfArguments + ONE_ARGUMENT){
            if(filterCommand[numberOfArguments].equals(NOT)){
                negativeFilter = true;
            }
            else if (filterCommand[numberOfArguments].equals(YES)){
                negativeFilter = false;
            }
            else {
                throw new TypeFirstError();
            }
        }
        else if (filterCommand.length < numberOfArguments){
            throw new TypeFirstError();
        }
    }

    /**
     * Check if the command file of - Hidden, Writable, Executable.
     * @throws TypeFirstError First type of exception.
     */
    protected void validFilterPermission() throws TypeFirstError {
        if (filterCommand.length < TWO_ARGUMENT){
            throw new TypeFirstError();
            }
        else if (filterCommand[ONE_ARGUMENT].equals(YES)){
                negativeFilter = false;
            }
        else if (filterCommand[ONE_ARGUMENT].equals(NO)){
                negativeFilter = true;
            }
        else {
            throw new TypeFirstError();
        }
        if (filterCommand.length == THREE_ARGUMENT) {
            negativeFilter = filterCommand[TWO_ARGUMENT].equals(NOT) != negativeFilter;
        }
        else if (filterCommand.length > THREE_ARGUMENT){
            throw new TypeFirstError();
        }
    }

    /**
     *
     * @param numberString String represents number.
     * @return The number it represents.
     * @throws TypeFirstError First type of exception.
     */
    protected double isValidNumber(String numberString) throws TypeFirstError {
        double numberOutput;
        try {
            numberOutput = Double.parseDouble(numberString);
        }
        catch (Exception NumberFormatException) {
            throw new TypeFirstError();
        }
        if (numberOutput < 0){
            throw new TypeFirstError();
        }
        return numberOutput;
    }
}
