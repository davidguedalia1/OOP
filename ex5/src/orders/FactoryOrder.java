package orders;
import filesprocessing.TypeFirstError;

/**
 * This is the factory of order, According design factory.
 * @author David Guedalia.
 */
public class FactoryOrder {

    /**
     * The location of the filter name.
     */
    private static final int ORDER_NAME = 0;

    /**
     * Abs.
     */
    private static final String ABS = "abs";

    /**
     * Type.
     */
    private static final String TYPE = "type";

    /**
     * Size.
     */
    private static final String SIZE = "size";

    /**
     * This method return the Order.
     * @param order - Array of strings of the order command.
     * @return The Order.
     * @throws TypeFirstError First type of exception.
     */
    public Order makeOrder(String[] order) throws TypeFirstError {
        switch (order[ORDER_NAME]) {
            case ABS:
                return new AbsOrder(order);
            case TYPE:
                return new TypeOrder(order);
            case SIZE:
                return new SizeOrder(order);
            default:

                throw new TypeFirstError();

        }
    }

}
