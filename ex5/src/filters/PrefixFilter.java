package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Prefix filter class - if given value is prefix of file.
 * @author David Guedalia
 */
public class PrefixFilter extends Filter {

    /**
     * The value to check if value is prefix of the file
     */
    private final String value;

    /**
     * Constructor of Prefix filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public PrefixFilter(String[] filterCommand) throws TypeFirstError {
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
            return !file.getName().startsWith(value);
        }
        else {
            return file.getName().startsWith(value);
        }
    }
}
