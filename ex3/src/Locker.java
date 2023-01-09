import oop.ex3.spaceship.*;

/**
 * This class is Locker, A locker can contain different types of items.
 * Each of these items have a unique identifying type.
 * All items of the same type take up the same amount of storage units in the locker.
 * Storage units are positive integers.
 * Each locker has a capacity, which is the total amount of storage units it can hold.
 * @author David Guedalia
 */
public class Locker extends StorageSpaceShip {

    /**
     * This is warning message if capacity transferred to Long term storage.
     */
    private static final String WARNING_MESSAGE = "Warning: Action successful," +
            " but has caused items to be moved to storage";

    /**
     * This is error message if try to remove negative number.
     */
    private static final String ERROR_NEGATIVE_NUMBER_MESSAGE =  "Error: Your request cannot be completed at" +
            " this time. Problem: cannot remove a negative number of items of type ";

    /**
     * This is error message because trying to add item that have constrain.
     */
    private static final String ERROR_CONSTRAIN_MESSAGE = "Error: Your request cannot be completed at this time." +
            " Problem: the locker cannot contain items of type %s, as it contains a contradicting item";

    /**
     * The maximal percent item can take from locker.
     */
    private static final double MAXIMAL_PERCENT_LOCKER = 0.5;

    /**
     * The remaining amount should only take up to 20% of the storage units of that locker.
     */
    private static final double MAXIMAL_REMAINING_LOCKER = 0.2;

    /**
     *  If the action failure because a constrain return -2.
     */
    private static final int FAILURE_CONSTRAIN = -2;

    /**
     * If the item succeed to add, but there was a transfer return 1.
     */
    private static final int SUCCESS_TRANSFER = 1;

    /**
     *  The capacity of the locker.
     */
    private final int capacityLocker;

    /**
     * This is array of constrains each element is array of two length.
     */
    private final Item[][] constraintsLocker;

    /**
     * lockerLts associate with the locker.
     */
    private final LongTermStorage lockerLts;

    /**
     * This constructor initializes a Locker object that is associated with the given long-term storage.
     * with the given capacity and Item constraints.
     * @param lts - a Locker associated with the given long-term storage.
     * @param capacity - The capacity of the Locker.
     * @param constraints - Item constraints.
     */
    public Locker(LongTermStorage lts, int capacity, Item[][] constraints){
        super(capacity);
        lockerLts = lts;
        capacityLocker = capacity;
        constraintsLocker = constraints;
    }

    /**
     This method adds n Items. If the action is successful, this method should return 0. If n Items cannot be added
     * to the locker at this time, no Items should be added, the method should return -1.
     * @param item - The item to store in long term storage.
     * @param n - Items of the given type to the long term storage unit.
     * @return - 0 - successful, -1 if failure.
     */
    @Override
    public int addItem(Item item, int n) {
        if (item != null && checkConstraints(item.getType())){
            printErrorMessageConstrain(item);
            return FAILURE_CONSTRAIN;
        }
        if (n < 0 || item == null){
            System.out.println(GENERAL_ERROR);
            return FAILURE;
        }
        if (n == 0){
            return SUCCESSFUL;
        }
        if (item.getVolume() < 0){
            System.out.println(GENERAL_ERROR);
            return FAILURE;
        }
        if (checkForPlace(item, n)){
            return storeItem(item, n);
        }
        else {
            printErrorMessageCapacity(item, n);
            return FAILURE;
        }
    }

    /**
     * This function check if there is constrain in the array.
     * @param type String, the type of the item.
     * @return true if there is a constrain, false otherwise.
     */
    private boolean checkConstraints(String type) {
        for (Item[] tuple : constraintsLocker) {
            if (tuple[0].getType().equals(type)) {
                if (hashStorage.containsKey(tuple[1].getType())) {
                    return true;
                }
            }
            else if (tuple[1].getType().equals(type)) {
                if (hashStorage.containsKey(tuple[0].getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method removes n Items of the type type from
     * the locker. If the action is successful, this method should return 0. In case there are less than n Items
     * of this type in the locker, no Items should be removed, the method should return -1
     * @param item - The item to store in long term storage.
     * @param n - Items of the given type to the long term storage unit.
     * @return -
     */
    public int removeItem(Item item, int n){
        if (item == null){
            System.out.println(GENERAL_ERROR);
            return FAILURE;
        }
        if (n < 0){
            System.out.println(ERROR_NEGATIVE_NUMBER_MESSAGE);
            return FAILURE;
        }
        if (item.getVolume() < 0){
            System.out.println(GENERAL_ERROR);
            return FAILURE;
        }
        int numberItem = getItemCount(item.getType());
        if (numberItem < n){
            printErrorMessageCapacity(item, n);
            return FAILURE;
        }
        else if (numberItem == n){
            hashStorage.remove(item.getType());
        }
        else {
            hashStorage.put(item.getType(), numberItem - n);
        }
        availableCapacity += n * item.getVolume();
        return SUCCESSFUL;
    }

    /**
     * This function store's the item in the relevant storage.
     * @param item - The item to store in long term storage.
     * @param n - Items of the given type to the long term storage unit.
     * @return 1 if store in both, 0 store only in Locker, -1 if failure.
     */
    private Integer storeItem(Item item, int n) {
        int amountOfItem = n + getItemCount(item.getType());
        int volumeTotalItem = item.getVolume() * amountOfItem;
        if (volumeTotalItem <= MAXIMAL_PERCENT_LOCKER * capacityLocker){
            hashStorage.put(item.getType(), amountOfItem);
            availableCapacity -= (n * item.getVolume());
            return SUCCESSFUL;
        }
        else if (transferToLongTerm(item, n)){
            System.out.println(WARNING_MESSAGE);
            return SUCCESS_TRANSFER;
        }
        else {
            return FAILURE;
        }
    }

    /**
     * This function transfer from locker to long term.
     * @param item - The item to store in long term storage.
     * @param n - Items of the given type to the long term storage unit.
     * @return - true if success, false otherwise.
     */
    private boolean transferToLongTerm(Item item, int n) {
        int amountOfItem = n + getItemCount(item.getType());
        int storeToLocker = (int) (MAXIMAL_REMAINING_LOCKER * capacityLocker) / item.getVolume();
        int storeToLongTerm = (amountOfItem) - storeToLocker;
        if (lockerLts.addItem(item, storeToLongTerm) == FAILURE){
            return false;
        }
        else {
            availableCapacity += item.getVolume() * (getItemCount(item.getType()) - storeToLocker);
            hashStorage.put(item.getType(), storeToLocker);
            return true;
        }
    }

    /**
     * Prints the error if there's a constrain.
     * @param item - The item to store in long term storage.
     */
    private void printErrorMessageConstrain(Item item) {
        System.out.printf((ERROR_CONSTRAIN_MESSAGE) + "%n", item.getType());
    }
}
