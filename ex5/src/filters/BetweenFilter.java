package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Between filter class - files is between two given numbers.
 * @author David Guedalia
 */
public class BetweenFilter extends Filter {

    /**
     * The small value, the file should be grader then this number.
     */
    private double smallValue;

    /**
     * The big value, the file should be smaller then this number.
     */
    private double bigValue;

    /**
     * Constructor of between filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public BetweenFilter(String[] filterCommand) throws TypeFirstError {
        super(filterCommand);
        checkInput();
    }

    /**
     * Check that the command file is valid.
     * @throws TypeFirstError First type of exception.
     */
    private void checkInput() throws TypeFirstError {
        smallValue = isValidNumber(filterCommand[ONE_ARGUMENT]);
        bigValue = isValidNumber(filterCommand[TWO_ARGUMENT]);
        validFilter(THREE_ARGUMENT);
        if (smallValue > bigValue){
            throw new TypeFirstError();
        }
    }

    /**
     * Check if the file should be filtered.
     * @param file file to check
     * @return True if the file is filtered, false otherwise.
     */
    @Override
    public boolean isFileFilter(File file) {
        if (negativeFilter){
            return file.length() >= 0 && file.length() <= smallValue * KILO_BYTES
                    || file.length() > bigValue * KILO_BYTES;
        }
        else {
            return file.length() >= smallValue * KILO_BYTES && file.length() <= bigValue * KILO_BYTES;
        }
    }
}
