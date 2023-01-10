package filesprocessing;
import filters.AllFilter;
import filters.FactoryFilter;
import filters.Filter;
import orders.AbsOrder;
import orders.FactoryOrder;
import orders.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * The class is the processor
 * @author David Guedalia
 */
public class Processor
{
    /**
     * Filter name.
     */
    private static final String FILTER = "FILTER";

    /**
     * Order name.
     */
    private static final String ORDER = "ORDER";

    /**
     * Warning message.
     */
    private static final String WARNING_MESSAGE = "Warning in line ";

    /**
     * Error in accessing file
     */
    private static final String ERROR_ACCESSING_FILE = "ERROR: Occurring while accessing the Commands File.";

    /**
     * Error in accessing file
     */
    private static final String ERROR_FILTER_ORDER = "ERROR: Bad sub section name.";

    /**
     * Error in accessing file
     */
    private static final String ERROR_BAD_FORMAT = "ERROR: Bad format of the command file";

    /**
     * The sign of the split - Hashtag.
     */
    private static final String SPLIT_SIGN = "#";
    /**
     * Minus one (-1)
     */
    private static final int MINUS_ONE = -1;

    /**
     * default order is Abs.
     */
    private static final String[] defaultOrder = new String[]{"abs"};

    /**
     * default filter is All.
     */
    private static final String[] defaultFilter = new String[]{"all"};

    /**
     * The command path.
     */
    private final String commandPath;

    /**
     * Counter of lines.
     */
    private int countLine = 1;

    /**
     * Constructor of parser.
     * @param commandPath The command path.
     */
    Processor(String commandPath) {
        this.commandPath = commandPath;
    }

    /**
     * This is the main method that go over rhe command file.
     * @return Linked list of sections.
     * @throws TypeSecondError Second type of exception.
     * @throws TypeFirstError First type of exception.
     */
    public LinkedList<Section> run() throws TypeSecondError, TypeFirstError {
        BufferedReader reader;
        Order order= null;
        Filter filter = null;
        try {
            reader = new BufferedReader(new FileReader(commandPath));
        }
        catch (IOException e){
            throw new TypeSecondError(ERROR_ACCESSING_FILE);
        }
        LinkedList<Section> sectionArray = new LinkedList<>();
        try {
            String line = reader.readLine();
            while (line != null) {
                Section section = new Section();
                try {
                    filter = filterCommand(line, reader.readLine());
                    line = reader.readLine();
                    countLine ++;
                }
                catch (TypeFirstError firstError){
                    filter = new AllFilter(defaultFilter);
                    section.addToWarnings(WARNING_MESSAGE + countLine);
                    line = reader.readLine();
                    countLine ++;
                }
                try {
                    String commandOrder = reader.readLine();
                    order = orderCommand(line, commandOrder);
                    line = commandOrder == null || commandOrder.equals(FILTER) ? commandOrder : reader.readLine();
                    countLine ++;
                }
                catch (TypeFirstError firstError){
                    order = new AbsOrder(defaultOrder);
                    section.addToWarnings(WARNING_MESSAGE + countLine);
                    line = reader.readLine();
                    countLine ++;
                }
                catch (Exception e){
                    throw new TypeSecondError(ERROR_BAD_FORMAT);
                }
                section.setSection(filter, order);
                sectionArray.add(section);
            }
        }
        catch (IOException e) {
            throw new TypeSecondError(ERROR_ACCESSING_FILE);
        }
        return sectionArray;
    }

    /**
     * This method responsible of the filter command.
     * @param line - FILTER string.
     * @param filterCommandString - the command of the string.
     * @return type of the filter.
     * @throws TypeSecondError Second type of exception.
     * @throws TypeFirstError First type of exception.
     */
    private Filter filterCommand(String line, String filterCommandString) throws TypeSecondError, TypeFirstError {
    if (line.equals(FILTER)){
        if (filterCommandString == null){
            throw new TypeSecondError(ERROR_BAD_FORMAT);
        }
    }
    else {
        throw new TypeSecondError(ERROR_FILTER_ORDER);
    }
        countLine ++;
        FactoryFilter filter = new FactoryFilter();
        return  filter.makeFilter(filterCommandString.split(SPLIT_SIGN, MINUS_ONE));
    }

    /**
     * This method responsible of the order command.
     * @param line - ORDER string.
     * @param orderCommand - the command of the string.
     * @return type of the order.
     * @throws TypeSecondError Second type of exception.
     * @throws TypeFirstError First type of exception.
     */
    private Order orderCommand(String line, String orderCommand) throws TypeSecondError, TypeFirstError {
        if (line.equals(ORDER)){
            if (orderCommand == null || orderCommand.equals(FILTER)){
                return new AbsOrder(defaultOrder);
            }
        }
        else {
            throw new TypeSecondError(ERROR_FILTER_ORDER);
        }
        countLine ++;
        FactoryOrder order = new FactoryOrder();
        return  order.makeOrder(orderCommand.split(SPLIT_SIGN));
    }
}
