/**
 * This class is hash-set based on closed-hashing with quadratic probing. Extends
 * SimpleHashSet.
 * @author David Guedalia
 */
public class ClosedHashSet extends SimpleHashSet {

    /**
     * The constant multiply to calculate the hash.
     */
    protected static final int CONSTANT_HASH = 2;

    /**
     * Didn't found the value in the closed hash.
     */
    protected static final int NOT_FOUND = -1;

    /**
     * Describes deleted field in the hash.
     */
    protected static final String EMPTY = new String("");

    /**
     * This is the close hash, array of strings.
     */
    private String[] closeHash;
    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        super();
        closeHash = new String[START_CAPACITY];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        closeHash = new String[START_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data){
        super();
        closeHash = new String[START_CAPACITY];
        dataToHashClose(data);
    }

    /**
     * This function gets array of data an add it to the hash.
     * @param data - Values to add to the set.
     */
    private void dataToHashClose(String[] data) {
        if (data != null) {
            for (String str : data) {
                if (str != null){
                    add(str);
                }
            }
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set.
     */
    @Override
    public boolean add(String newValue) {
        if (newValue == null){
            return false;
        }
        if (contains(newValue)){
            return false;
        }
        else if (getUpperLoadFactor() < calculateCapacity(1)) {
            capacityHash *= MULTIPLY_HASH;
            closeHash = changeHashSize(capacityHash);
        }
        insertValueToHash(newValue, closeHash);
        sizeHash ++;
        return true;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        if (toDelete == null){
            return false;
        }
        int indexToDelete = findValueClosedHash(toDelete);
        if (indexToDelete == NOT_FOUND){
            return false;
        }
        sizeHash --;
        closeHash[indexToDelete] = EMPTY;
        if (getLowerLoadFactor() > calculateCapacity(0)) {
            capacityHash /= MULTIPLY_HASH;
            closeHash = changeHashSize(capacityHash);
        }
        return true;
    }

    /**
     * This function gets as input value to find and return the index of the value.
     * @param value Value to find in the hash.
     * @return -1 iff value is not in the hash, index of the value otherwise
     */
    private int findValueClosedHash(String value){
        int j;
        for (int i = 0; i < capacity(); i++) {
            j = calculateIndex(i, value);
            if (closeHash[j] == null){
                return NOT_FOUND;
            }
            if (closeHash[j].equals(value)){
                if (closeHash[j] == EMPTY){
                    break;
                }
                return j;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This function insert the value to the closed hash.
     * @param newValue New value to add to the set
     */
    private void insertValueToHash(String newValue, String[] hash) {
        int j;
        for (int i = 0; i < capacity(); i++) {
            j = calculateIndex(i, newValue);
            if (validityIndex(hash[j])){
                hash[j] = newValue;
                break;
            }
        }
    }

    /**
     * @param str String to check if is validity.
     * @return true id it is valid to insert, false otherwise.
     */
    private boolean validityIndex(String str) {
        return str == null || str == EMPTY;
    }

    /**
     * This function calculate the closed hash calculator.
     * @param i index between 0 to size().
     * @param newValue valuse to find his index
     * @return index that suit the value.
     */
    private int calculateIndex(int i, String newValue) {
        return clamp(newValue.hashCode() + (i + (i * i) )/ CONSTANT_HASH);
    }

     /**
     * This function change the has to the current size according adding/ removing.
     * @param newCapacity The capacity of the new open hash set.
     */
    private String[] changeHashSize(int newCapacity) {
        String[] closeHashNew = new String[newCapacity];
        for (String hash : closeHash) {
            if (!validityIndex(hash)) {
                insertValueToHash(hash, closeHashNew);
            }
        }
        return closeHashNew;
    }

    /**
     * @return Capacity, calculating the capacity.
     */
    private float calculateCapacity(int deleteOrAdd) {
        return (float) (size() + deleteOrAdd) / capacity();
    }

    /**
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        if (searchVal != null){
            return findValueClosedHash(searchVal) >= 0;
        }
        return false;
    }

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return sizeHash;
    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    @Override
    public int capacity() {
        return capacityHash;
    }
}