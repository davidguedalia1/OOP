package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * All filter class - all files are matched.
 * @author David Guedalia
 */
public class AllFilter extends Filter {

    /**
     * Constructor of all filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public AllFilter(String[] filterCommand) throws TypeFirstError {
        super(filterCommand);
        validFilter(ONE_ARGUMENT);
    }

    /**
     * Check if the file should be filtered.
     * @param file file to check
     * @return True if the file is filtered, false otherwise.
     */
    @Override
    public boolean isFileFilter(File file) {
        return !negativeFilter;
    }
}
