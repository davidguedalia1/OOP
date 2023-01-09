import oop.ex3.spaceship.*;
import java.util.*;

/**
 * This class is LongTermStorage, In addition to the aforementioned lockers, a spaceship also
 * has a single centralized long-term storage, which has a capacity of 1000 storage units.
 * If items of a specific type take up more than 50% of the storage
 * units of a specific locker, some of them are automatically moved to the long-term storage.
 *
 * @author David Guedalia
 */
public class LongTermStorage extends StorageSpaceShip  {

    /**
     *  The capacity of the long term storage is 1000.
     */
    private static final int CAPACITY_LONG_TERM = 1000;

    /**
     * This constructor initializes a Long-Term Storage object with capacity of 1000.
     */
    public LongTermStorage(){
        super(CAPACITY_LONG_TERM);
        availableCapacity = CAPACITY_LONG_TERM;
    }

    /**
     * This method adds n Items. If the action is successful, this method should return 0. If n Items cannot be added
     * to the locker at this time, no Items should be added, the method should return -1.
     * @param item - The item to store in long term storage.
     * @param n - Items of the given type to the long term storage unit.
     * @return - 0 - successful, -1 if failure.
     * "Error: Your request cannot be completed at this
     * time. Problem: no room for n items of type type"
     */
    @Override
    public int addItem(Item item, int n){
        if (n < 0 || item == null){
            System.out.println(GENERAL_ERROR);
            return FAILURE;
        }
        if (checkForPlace(item, n)){
            hashStorage.put(item.getType(), n + getItemCount(item.getType()));
            availableCapacity -= n * item.getVolume();
            return SUCCESSFUL;
        }
        printErrorMessageCapacity(item, n);
        return FAILURE;
    }

    /**
     * This method resets the long-term storage’s inventory.
     * so its clears the data and reset the capacity.
     */
    public void resetInventory(){
        availableCapacity = CAPACITY_LONG_TERM;
        hashStorage.clear();
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
        return CAPACITY_LONG_TERM;
    }

    /**
     * This method returns the long-term storage’s available capacity.
     * @return - Available capacity.
     */
    public int getAvailableCapacity(){
        return availableCapacity;
    }
}
