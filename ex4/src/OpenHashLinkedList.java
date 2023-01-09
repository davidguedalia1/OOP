import java.util.LinkedList;

/**
 * This class is a wrapper-class that has a LinkedList and delegates methods to it,
 * @author David Guedalia
 */
public class OpenHashLinkedList implements SimpleSet {

    /**
     * This is the open hash linked list.
     */
    private LinkedList<String> hashLinked;

    /**
     * This is the constructor of the open hash linked list.
     */
    public OpenHashLinkedList(){
        hashLinked = new LinkedList<String>();
    }

    /**
     * @param newValue New value to add to the set
     * @return true if succeed, false otherwise.
     */
    @Override
    public boolean add(String newValue) {
        return hashLinked.add(newValue);
    }

    /**
     * @param searchVal Value to search for
     * @return true if succeed, false otherwise.
     */
    @Override
    public boolean contains(String searchVal) {
        return hashLinked.contains(searchVal);
    }


    /**
     * @param toDelete Value to delete
     * @return true if succeed, false otherwise.
     */
    @Override
    public boolean delete(String toDelete) {
        return hashLinked.remove(toDelete);
    }

    /**
     * @return the size of the linked list.
     */
    @Override
    public int size() {
        return hashLinked.size();
    }

    /**
     * @return the linked list.
     */
    public LinkedList<String> getHashLinked(){
        return hashLinked;
    }
}
