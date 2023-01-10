package filters;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Executable filter class - if the files have execution permission.
 * @author David Guedalia
 */
public class ExecutableFilter extends Filter {

    /**
     * Constructor of Executable filter.
     * @param filterCommand array of the command of the filter.
     * @throws TypeFirstError First type of exception.
     */
    public ExecutableFilter(String[] filterCommand) throws TypeFirstError {
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
            return !file.canExecute();
        }
        else {
            return file.canExecute();
        }
    }
}
