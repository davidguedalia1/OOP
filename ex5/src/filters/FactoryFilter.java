package filters;
import filesprocessing.TypeFirstError;

/**
 * This is the factory of Filter, According design factory.
 * @author David Guedalia.
 */
public class FactoryFilter {

    /**
     * The location of the filter name.
     */
    private static final int FILTER_NAME = 0;

    /**
     * Greater then.
     */
    private static final String GREATER_THAN = "greater_than";

    /**
     * Between.
     */
    private static final String BETWEEN = "between";

    /**
     * Smaller then.
     */
    private static final String SMALLER_THAN = "smaller_than";

    /**
     * File.
     */
    private static final String FILE = "file";

    /**
     * Contains.
     */
    private static final String CONTAINS = "contains";

    /**
     * Prefix.
     */
    private static final String PREFIX = "prefix";

    /**
     * Suffix.
     */
    private static final String SUFFIX = "suffix";

    /**
     * Writable.
     */
    private static final String WRITABLE = "writable";

    /**
     * Executable
     */
    private static final String EXECUTABLE = "executable";

    /**
     * Hidden.
     */
    private static final String HIDDEN = "hidden";

    /**
     * All.
     */
    private static final String ALL = "all";

    /**
     * This method return the Filter.
     * @param filterCommand - Array of strings of the filter command.
     * @return The Filter.
     * @throws TypeFirstError First type of exception.
     */
    public Filter makeFilter(String[] filterCommand) throws TypeFirstError {
        switch (filterCommand[FILTER_NAME]) {
            case GREATER_THAN:
                return new GreaterThanFilter(filterCommand);
            case BETWEEN:
                return new BetweenFilter(filterCommand);
            case SMALLER_THAN:
                return new SmallerThanFilter(filterCommand);
            case FILE:
                return new FileFilter(filterCommand);
            case CONTAINS:
                return new ContainsFilter(filterCommand);
            case PREFIX:
                return new PrefixFilter(filterCommand);
            case SUFFIX:
                return new SuffixFilter(filterCommand);
            case WRITABLE:
                return new WritableFilter(filterCommand);
            case EXECUTABLE:
                return new ExecutableFilter(filterCommand);
            case HIDDEN:
                return new HiddenFilter(filterCommand);
            case ALL:
                return new AllFilter(filterCommand);
            default:
                throw new TypeFirstError();

        }
    }
}
