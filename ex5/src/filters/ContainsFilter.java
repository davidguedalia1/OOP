package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Between filter class - files that contains some given string.
 * @author David Guedalia
 */
public class ContainsFilter extends Filter {

    /**
     * The value should appear in the file
     */
    private final String value;

    /**
     * Constructor of Contains filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public ContainsFilter(String[] filterCommand) throws TypeFirstError {
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
            return !file.getName().contains(value);
        }
        else {
            return file.getName().contains(value);
        }
    }
}
