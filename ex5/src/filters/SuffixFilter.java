package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Suffix filter class - if value is suffix of file.
 * @author David Guedalia
 */
public class SuffixFilter extends Filter {

    /**
     * The value to check if value is suffix of the file
     */
    private final String value;

    /**
     * Constructor of Suffix filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public SuffixFilter(String[] filterCommand) throws TypeFirstError {
        super(filterCommand);
        this.value = filterCommand[ONE_ARGUMENT];
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
            return !file.getName().endsWith(value);
        }
        else {
            return file.getName().endsWith(value);
        }
    }
}
