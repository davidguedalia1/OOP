/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 * @author David Guedalia
 */
public abstract class SimpleHashSet implements SimpleSet {
    /**
     * Describes the capacity of a newly created hash set.
     */
    protected static final int MULTIPLY_HASH = 2;

    /**
     * Describes the higher load factor of a newly created hash set.
     */
    protected static final float HIGHER_CAPACITY_HASH = 0.75f;

    /**
     * Describes the lower load factor of a newly created hash set.
     */
    protected static final float LOWER_CAPACITY_HASH = 0.25f;

    /**
     * Describes the capacity of a newly created hash set.
     */
    protected static final int START_CAPACITY = 16;

    /**
     * upperLoadFactor - the upper load factor before rehashing.
     */
    protected float upperLoadFactor;

    /**
     * lowerLoadFactor - the lower load factor before rehashing.
     */
    protected float lowerLoadFactor;

    /**
     * size - the size of the hash set..
     */
    protected int sizeHash;

    /**
     * capacity - the capacity og the hash set.
     */
    protected int capacityHash;

    /**
     * Constructs a new hash set with the default capacities given in
     * DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY.
     */
    protected SimpleHashSet() {
        this.lowerLoadFactor = LOWER_CAPACITY_HASH;
        this.upperLoadFactor = HIGHER_CAPACITY_HASH;
        capacityHash = START_CAPACITY;
        sizeHash = 0;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY.
     * @param upperLoadFactor - the upper load factor before rehashing
     * @param lowerLoadFactor - the lower load factor before rehashing
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
        capacityHash = START_CAPACITY;
        sizeHash = 0;
    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    /**
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor(){
        return this.lowerLoadFactor;
    }

    /**+
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor(){
        return this.upperLoadFactor;
    }

    /**
     * Clamps hashing indices to fit within the current table capacity.
     * @param index - the index before clamping.
     * @return An index properly clamped.
     */
    protected int clamp(int index){
        return index & (capacity() - 1);
    }



}
