/**
 * This class id a hash-set based on chaining. Extends SimpleHashSet.
 * @author David Guedalia
 */
public class OpenHashSet  extends SimpleHashSet {

    /**
     * This is the open hash table.
     */
    private OpenHashLinkedList[] openHash;

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        super();
        this.openHash = new OpenHashLinkedList[START_CAPACITY];
        buildOpenHash(openHash);
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        this.openHash = new OpenHashLinkedList[START_CAPACITY];
        buildOpenHash(openHash);

    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data) {
        super();
        this.openHash = new OpenHashLinkedList[START_CAPACITY];
        buildOpenHash(openHash);
        dataToHashOpen(data);
    }

    /**
     * This function gets array of data an add it to the hash.
     * @param data - Values to add to the set.
     */
    private void dataToHashOpen(String[] data) {
        if (data != null) {
            for (String str : data) {
                if (str != null){
                    add(str);
                }
            }
        }
    }

    /**
     * This method initialize the linked list in the hash table.
     * @param openHash - The hash table to initialize.
     */
    private void buildOpenHash(OpenHashLinkedList[] openHash) {
        for (int i = 0; i < openHash.length; i++) {
            openHash[i] = new OpenHashLinkedList();
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set.
     */
    public boolean add(java.lang.String newValue) {
        if (contains(newValue)) {
            return false;
        }
        else if (getUpperLoadFactor() < calculateCapacity(1)) {
            capacityHash *= MULTIPLY_HASH;
            changeHashSize(capacityHash);
        }
        openHash[clamp(newValue.hashCode())].getHashLinked().add(newValue);
        sizeHash++;
        return true;
    }

    /**
     * Delete a specified element to the set if it's already in it.
     * @param toDelete Value to delete.
     * @return True iff toDelete is found and deleted.
     */
    public boolean delete(java.lang.String toDelete) {
        if (!contains(toDelete)) {
            return false;
        }
        openHash[clamp(toDelete.hashCode())].getHashLinked().remove(toDelete);
        sizeHash--;
        if (capacity() <= 1) {
            return true;
        }
        if (getLowerLoadFactor() > calculateCapacity(0)) {
            capacityHash /= MULTIPLY_HASH;
            changeHashSize(capacityHash);
        }
        return true;
    }

    /**
     * This function change the has to the current size according adding/ removing.
     * @param newCapacity The capacity of the new open hash set.
     */
    private void changeHashSize(int newCapacity) {
        OpenHashLinkedList[] openHashNew = new OpenHashLinkedList[newCapacity];
        buildOpenHash(openHashNew);
        for (OpenHashLinkedList list : openHash) {
            if (list != null) {
                for (String str : list.getHashLinked()) {
                    openHashNew[clamp(str.hashCode())].getHashLinked().add(str);
                }
            }
        }
        openHash = openHashNew;
    }

    /**
     * @return Capacity, calculating the capacity.
     */
    private float calculateCapacity(int deleteOrAdd) {
        return (float) (size() + deleteOrAdd) / capacity();
    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    @Override
    public int capacity() {
        return capacityHash;
    }

    /**
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        return openHash[clamp(searchVal.hashCode())].getHashLinked().contains(searchVal);
    }

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return sizeHash;
    }
}