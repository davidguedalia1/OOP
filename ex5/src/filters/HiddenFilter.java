package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Hidden filter class - if the file is hidden.
 * @author David Guedalia
 */
public class HiddenFilter extends Filter {

    /**
     * Constructor of Hidden filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public HiddenFilter(String[] filterCommand) throws TypeFirstError {
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
            return !file.isHidden();
        }
        else {
            return file.isHidden();
        }
    }
}
