package filesprocessing;
import filters.Filter;
import orders.Order;
import java.util.LinkedList;

/**
 * Class Section that to store in array the all the sections in linked list.
 * @author David Guedalia
 */
public class Section {

    /**
     * this linked list contains the warning messages of each section.
     */
    private LinkedList<String> warningsMessages = new LinkedList<>();

    /**
     * filter
     */
    private Filter filter;

    /**
     * order
     */
    private Order order;

    /**
     * Setting Order and Filter - Section.
     * @param filter - Filter.
     * @param order - Order.
     */
    public void setSection(Filter filter, Order order){
        this.filter = filter;
        this.order = order;
    }

    /**
     * @return The filter.
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * @return The Order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Adding new message to the section.
     * @param newMessage - Warning message.
     */
    public void addToWarnings(String newMessage){
        warningsMessages.add(newMessage);
    }

    /**
     * @return LinkedList of the warning messages.
     */
    public LinkedList<String> getWarningsMessages() {
        return warningsMessages;
    }
}
