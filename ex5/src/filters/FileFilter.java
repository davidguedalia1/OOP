package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * File filter class - if value equals to the name of a file.
 * @author David Guedalia
 */
public class FileFilter extends Filter {

    /**
     * The value to check if equal to the file.
     */
    private final String value;

    /**
     * Constructor of File filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public FileFilter(String[] filterCommand) throws TypeFirstError {
        super(filterCommand);
        validFilter(TWO_ARGUMENT);
        value = filterCommand[ONE_ARGUMENT];
    }

    /**
     * Check if the file should be filtered.
     * @param file file to check
     * @return True if the file is filtered, false otherwise.
     */
    @Override
    public boolean isFileFilter(File file) {
        if (negativeFilter){
            return !file.getName().equals(value);
        }
        else {
            return file.getName().equals(value);
        }
    }
}
