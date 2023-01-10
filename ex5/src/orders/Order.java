package orders;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Abstract Class Order - This is the class Order.
 * @author David Guedalia
 */
public abstract class Order {

    /**
     * The number of kylo bytes - 1024.
     */
    protected double KILO_BYTES = 1024;

    /**
     * Reverse.
     */
    protected static final String REVERSE = "REVERSE";

    /**
     * The length of the order command.
     */
    protected static final int LENGTH_ORDER = 2;

    /**
     * Reverse - default to be false.
     */
    boolean reverseOrder = false;

    /**
     * Array of strings of the order command.
     */
    protected final String[] typeOrder;

    /**
     * Constructor of order
     * @param order - Array of strings of the order command.
     */
    Order(String[] order) throws TypeFirstError {
        this.typeOrder = order;
        validOrder();
    }

    /**
     * Set the reverse.
     */
    protected void setReverse(){
        reverseOrder = !reverseOrder;
    }

    /**
     * This method compare two files according the order.
     * @param firstFile First file
     * @param secondFile Second file
     * @return 0 if they are equal, 1 if first file is bigger, -1 otherwise.
     * @throws TypeFirstError First type of exception.
     */
    public abstract int compare(File firstFile, File secondFile) throws TypeFirstError;

    /**
     * Check if the command order is valid.
     * @throws TypeFirstError First type of exception.
     */
    protected void validOrder() throws TypeFirstError {
        if (typeOrder.length ==  LENGTH_ORDER){
            if (typeOrder[1].equals(REVERSE)){
                    reverseOrder = true;
            }
        }
        else if (typeOrder.length != 1){
            throw new TypeFirstError();
        }
    }

    /**
     * The default comparator is Abs.
     * @param firstFile First file
     * @param secondFile Second file
     * @return 0 if they are equal, 1 if first file is bigger, -1 otherwise.
     */
    protected int defaultOrderCompare(File firstFile, File secondFile) {
            return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
        }

}
