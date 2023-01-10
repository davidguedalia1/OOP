package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * GreaterThan filter class - if size file is bigger then given value.
 * @author David Guedalia
 */
public class GreaterThanFilter extends Filter{

    /**
     * The number that the file should be bigger then.
     */
    private final double number;

    /**
     * Constructor of GreaterThan filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    GreaterThanFilter(String[] filterCommand) throws TypeFirstError {
        super(filterCommand);
        number = isValidNumber(filterCommand[ONE_ARGUMENT]);
        validFilter(TWO_ARGUMENT);
    }

    /**
     * Check if the file should be filtered.
     * @param file file to check
     * @return True if the file is filtered, false otherwise.
     */
    @Override
    public boolean isFileFilter(File file) {
    if (negativeFilter){
        return file.length() >= 0 && file.length() <= number * KILO_BYTES;
    }
    else {
        return file.length() > number * KILO_BYTES;
    }
    }
}
