package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Smaller filter class - if size file is smaller than given value.
 * @author David Guedalia
 */
public class SmallerThanFilter extends Filter {
    /**
     * The number that the file should be smaller than.
     **/
    private final double number;

    /**
     * Constructor of Smaller filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public SmallerThanFilter(String[] filterCommand) throws TypeFirstError {
        super(filterCommand);
        number = isValidNumber(filterCommand[1]);
        validFilter(2);
    }

    /**
     * Check if the file should be filtered.
     * @param file file to check
     * @return True if the file is filtered, false otherwise.
     */
    @Override
    public boolean isFileFilter(File file) {
        if (negativeFilter){
            return file.length() >= number * KILO_BYTES;
        }
        else {
            return file.length() < number * KILO_BYTES && file.length() >= 0;
        }
    }
}
