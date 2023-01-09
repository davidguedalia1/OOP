import oop.ex3.spaceship.*;

/**
 * This class is SpaceShip, this class gets name, array of ID's, and a array of the
 * constrain that the lockers gets, and the common long term storage.
 * this class build the lockers in the spaceShip that associated with ID.
 * @author David Guedalia
 */
public class Spaceship {

    /**
     *  If the action successful return 0.
     */
    protected static final int SUCCESSFUL = 0;

    /**
     *  If the action failure because of the ID, return -1.
     */
    private static final int FIRST_ERROR = -1;

    /**
     *  If the action failure because of the capacity, return -2.
     */
    private static final int SECOND_ERROR = -2;

    /**
     *  If the action failure because of the no room, return -3.
     */
    private static final int THIRD_ERROR = -3;

    /**
     * Array contains the constrains.
     */
    private final Item[][] constraintsArray;

    /**
     * Array contains all the ID.
     */
    private final int[] crewIdArray;

    /**
     * The number of lockers is available.
     */
    private int numberOfLockersAvailable;

    /**
     * Array contains the all lockers in the space ship.
     */
    private final Locker[] lockerArray;

    /**
     * index of the array locker.
     */
    private int indexLocker;

    /**
     * The name of the spaceShip.
     */
    private String nameSpace;

    /**
     * lockerLts associate with the locker.
     */
    private final LongTermStorage lockerLts;

    /**
     * The constructor of SpaceShips.
     * @param name The name of the SpaceShip.
     * @param crewIDs List of crew ids contains unique ids and does not change through time.
     * @param numOfLockers The numbers of lockers in the spaceShip.
     * @param constraints Array of constraints contains valid Item arrays.
     */
    public Spaceship(String name, int[] crewIDs, int numOfLockers, Item[][] constraints){
        nameSpace = name;
        crewIdArray = crewIDs;
        constraintsArray = constraints;
        numberOfLockersAvailable = numOfLockers;
        indexLocker = 0;
        lockerLts = new LongTermStorage();
        lockerArray = new Locker[numOfLockers];
    }

    /**
     * Get function of array long term storage.
     * @return The long term storage array of the Space Ship.
     */
    public LongTermStorage getLongTermStorage(){
        return lockerLts;
    }

    /**
     * This method creates a Locker object,and adds it as part of the Spaceship’s storage.
     * @param crewID Associated with a crew member with the given id.
     * @param capacity The capacity of the locker.
     * @return 0 if success, -1 if id isn't valid, -2 if capacity does not meet the Locker
     *         class requirements, -3 no space for another locker.
     */
    public int createLocker(int crewID, int capacity){
        if (!checkIfContains(crewID)){
            return FIRST_ERROR;
        }
        else if (capacity < 0){
            return SECOND_ERROR;
        }
        else if (numberOfLockersAvailable <= 0){
            return THIRD_ERROR;
        }
        Locker locker = new Locker(lockerLts, capacity, constraintsArray);
        lockerArray[indexLocker] = locker;
        indexLocker ++;
        numberOfLockersAvailable --;
        return SUCCESSFUL;
    }

    /**
     * This function checks if the id is in the Id array.
     * @param crewId Associated with a crew member with the given id.
     * @return true if found, false otherwise.
     */
    private boolean checkIfContains(int crewId) {
        for (int id : crewIdArray) {
            if (id == crewId){
                return true;
            }
        }
        return false;
    }
    /**
     * Get function of array CrewID
     * @return The crew Id of the Space Ship.
     */
    public int[] getCrewIDs(){
        return crewIdArray;
    }

    /**
     * Get function of array locker
     * @return The locker array of the Space Ship.
     */
    public Locker[] getLockers(){
       return lockerArray;
    }
}
