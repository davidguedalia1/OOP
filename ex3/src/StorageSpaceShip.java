import oop.ex3.spaceship.*;
import java.util.*;

/**
 * This class is StorageSpaceShip, This is an abstract class of storage in spaceShip.
 * This class contains the methods common to Locker and Long Term Storage.
 * the function addItem is an abstract function.
 * @author David Guedalia
 */
public abstract class StorageSpaceShip {

    /**
     *  If the action successful return 0.
     */
    protected static final int SUCCESSFUL = 0;
    /**
     *  If the action failure return -1.
     */
    protected static final int FAILURE = -1;

    /**
     *  If the action failure return 0.
     */
    private static final String ERROR_MESSAGE_CAPACITY = "Error: Your request cannot be completed at this time." +
            " Problem: no room for %d items of type %s";

    /**
     * Error message for a general issue.
     */
    protected static final String GENERAL_ERROR =  "Error: Your request cannot be completed at this time.";


    /**
     * This is the data structure of the long ter, storage.
     */
    protected Map <String,Integer> hashStorage;

    /**
     * The available capacity in the long term storage.
     */
    protected int availableCapacity;

    /**
     * The maximum capacity in the long term storage.
     */
    private final int maxCapacity;

    /**
     * This constructor initializes a Long-Term Storage object.
     * @param capacity The capacity of the spaceShip.
     */
    protected StorageSpaceShip(int capacity){
        hashStorage = new HashMap <String, Integer>();
        availableCapacity = capacity;
        maxCapacity = capacity;
    }

    /**
     * This method adds n Items. If the action is successful, this method should return 0.
     * this is an abstract method, each class have extension of this class.
     * If n Items cannot be added to the locker at this time, no Items should be added,
     * the method should return -1.
     * @param item - The item to store in long term storage.
     * @param n - Items of the given type to the long term storage unit.
     * @return - 0 - successful, -1 if failure.
     */
    public abstract int addItem(Item item, int n);

    /**
     * This method returns the number of Items of type
     * the storage contains.
     * @param type - the type to check how many contains.
     * @return - int,  numbers of items
     */
    protected int getItemCount(String type){
        if (type == null){
            System.out.println(GENERAL_ERROR);
            return FAILURE;
        }
        else if (hashStorage.containsKey(type)){
            return hashStorage.get(type);
        }
        return SUCCESSFUL;
    }

    /**
     * This method returns a map of all the Items
     * contained in the long-term storage unit, and their respective quantities.
     * @return - a map of all the Items.
     */
    public Map <String, Integer> getInventory(){
        return hashStorage;
    }

    /**
     * This method returns the long-term storage’s total capacity.
     * @return - total capacity.
     */
    public int getCapacity(){
        return maxCapacity;
    }

    /**
     * This method returns the long-term storage’s available capacity.
     * @return - Available capacity.
     */
    public int getAvailableCapacity(){
        return availableCapacity;
    }
    /**
     * This function checks if there's place to store the item.
     * @param item - The item to store in long term storage.
     * @param n - Items of the given type to the long term storage unit.
     * @return - false if there isn't place, true otherwise.
     */
    protected boolean checkForPlace(Item item, int n) {
        int itemVolume = item.getVolume();
        return itemVolume * n <= availableCapacity;
    }

    /**
     * Prints the error if there's no room.
     * @param item - The item to store in long term storage.
     * @param n - Items of the given type to the long term storage unit.
     */
    protected void printErrorMessageCapacity(Item item, int n) {
        System.out.printf((ERROR_MESSAGE_CAPACITY) + "%n", n, item.getType());
    }
}
