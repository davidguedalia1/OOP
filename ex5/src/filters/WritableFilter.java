package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Writable filter class - if file is writable.
 * @author David Guedalia
 */
public class WritableFilter extends Filter {

    /**
     * Constructor of Writable filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public WritableFilter(String[] filterCommand) throws TypeFirstError {
        super(filterCommand);
        validFilterPermission();
    }

    /**
     * Check if the file should be filtered.
     * @param file file to check
     * @return True if the file is filtered, false otherwise.
     */
    @Override
    public boolean isFileFilter(File file) {
        if (negativeFilter){
            return !file.canWrite();
        }
        else {
            return file.canWrite();
        }
    }
}
