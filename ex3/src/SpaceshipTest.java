import oop.ex3.spaceship.*;
import org . junit .*;
import static org.junit.Assert.*;

/**
 * This class is SpaceShip Test, This class checks the all methods of SpaceShip, and runs the test
 * of this class.
 * @author David Guedalia
 */
public class SpaceshipTest {
    /**
     * The length of the array ID.
     */
    private static final int AMOUNT_OF_LOCKER = 6;
    /**
     * the long term storage
     */
    private static LongTermStorage longTermStorage;

    /**
     * Baseball item of volume 2.
     */
    private static Item baseballItem;

    /**
     * Helmet Big item of volume 5.
     */
    private static Item helmetBigItem;

    /**
     * Helmet Big item of volume 4.
     */
    private static Item footballItem;

    /**
     * Spores item of volume 10.
     */
    private static Item sporesItem;

    /**
     * Item with negative volume.
     */
    private static Item negativeItem;

    /**
     * Item with 0 volume.
     */
    private static Item zeroItem;

    /**
     * spaceShip normal.
     */
    private static Spaceship spaceShip;

    /**
     * SpaceShip have 0 numbers of lockers.
     */
    private static Spaceship spaceShipZeroLocker;

    /**
     * SpaceShip have 0 ID numbers.
     */
    private static Spaceship spaceShipNoID;

    /**
     * Array of the constraints.
     */
    private static Item[][] constraintsArray;

    /**
     * Array of the id in the spaceShip.
     */
    private static int[] crewIdArray;

    /**
     * Array odf the id in the spaceShip - empty array.
     */
    private static int[] crewIdArrayEmpty;
    /**
     * locker array of the SpaceShip.
     */
    private static Locker[] lockerArray;

    /**
     * This function create the objects to run the test.
     */
    @BeforeClass
    public static void createTest() {
        longTermStorage = new LongTermStorage();
        constraintsArray = ItemFactory.getConstraintPairs();
        crewIdArray = new int[]{-1, 0, 1, 2, 3, 4};
        crewIdArrayEmpty = new int[]{};
        sporesItem = ItemFactory.createSingleItem("spores engine");
        footballItem = ItemFactory.createSingleItem("football");
        baseballItem = ItemFactory.createSingleItem("baseball bat");
        helmetBigItem = ItemFactory.createSingleItem("helmet, size 3");
    }

    /**
     * This function running before each test and reset the objects for the next test.
     */
    @Before
    public void emptyLockers() {
        longTermStorage.resetInventory();
        spaceShip = new Spaceship("Space", crewIdArray, AMOUNT_OF_LOCKER,constraintsArray);
        spaceShipZeroLocker = new Spaceship("SpaceZeroLocker", crewIdArray, 0,constraintsArray);
        spaceShipNoID = new Spaceship("SpaceNoID", crewIdArrayEmpty, AMOUNT_OF_LOCKER,constraintsArray);
    }

    /**
     * Testing the GetLongTermStorage testing the long term storage of spaceship.
     */
    @Test
    public void testGetLongTermAfterCreateLocker() {
        spaceShip.createLocker(2, 200);
        spaceShip.createLocker(3, 50);
        spaceShip.createLocker(4, 40);
        lockerArray = spaceShip.getLockers();
        assertEquals("Test GetLongTermStorage the capacity of long term storage is not correct",
                1000, spaceShip.getLongTermStorage().getCapacity());
        lockerArray[0].addItem(sporesItem, 11);
        assertEquals("Test GetLongTermStorage the available capacity of long term storage is not" +
                " correct after adding locker", 930, spaceShip.getLongTermStorage().getAvailableCapacity());
        lockerArray[1].addItem(helmetBigItem, 5);
        assertEquals("Test GetLongTermStorage the available capacity of long term storage is not correct" +
                " after adding locker", 930, spaceShip.getLongTermStorage().getAvailableCapacity());
        lockerArray[2].addItem(footballItem, 6);
        assertEquals("Test GetLongTermStorage the available capacity of long term storage is not" +
                " correct after adding locker", 914, spaceShip.getLongTermStorage().getAvailableCapacity());
    }
    /**
     * Testing the GetLongTermStorage testing the long term storage of spaceship.
     */
    @Test
    public void testGetLongTermStorage() {
        assertNotNull("Test GetLongTermStorage failed return null",
                spaceShip.getLongTermStorage());
        assertEquals("Test GetLongTermStorage the capacity of long term storage is not correct",
                1000, spaceShip.getLongTermStorage().getCapacity());
    }

    /**
     * Testing the CreateLocker of invalid inputs.
     */
    @Test
    public void testCreateLockerInvalid() {
        assertEquals("Test CreateLockerInvalid failed, the id doesn't exist",
                -1, spaceShip.createLocker(5, 40));
        assertEquals("Test CreateLockerInvalid failed, the id doesn't exist",
                -1, spaceShip.createLocker('4', 40));
        assertEquals("Test CreateLockerInvalid failed, the id doesn't exist",
                -2, spaceShip.createLocker(1, -1));

        assertEquals("Test CreateLocker failed, number of lockers is not correct",
                -3, spaceShipZeroLocker.createLocker(1, 10));

        assertEquals("Test CreateLocker failed, number of lockers is not correct",
                -1, spaceShipNoID.createLocker(1, 10));
        assertEquals("Test CreateLocker failed, number of lockers is not correct",
                -1, spaceShipNoID.createLocker(1, -10));

        for (int i = 0; i < AMOUNT_OF_LOCKER; i++){
            spaceShip.createLocker(1, 40);
        }
        assertEquals("Test CreateLockerInvalid failed, there is no space in spaceShip.",
                -3, spaceShip.createLocker(2, 10));
    }

    /**
     * Testing CreateLocker function checks that the order of the errors are good.
     */
    @Test
    public void testCreateLockerOrder() {
        assertEquals("Test CreateLockerInvalidOrder failed, the issue id check first.",
                -1, spaceShip.createLocker(5, -40));
        for (int i = 0; i < AMOUNT_OF_LOCKER; i++){
            spaceShip.createLocker(1, 40);
        }
        assertEquals("Test CreateLockerInvalidOrder failed, the issue id check first, in zero spaceship",
                -1, spaceShipZeroLocker.createLocker(5, 10));
        assertEquals("Test CreateLockerInvalidOrder failed, the validity of capacity check second.",
                -2, spaceShipZeroLocker.createLocker(1, -10));
        assertEquals("Test CreateLockerInvalidOrder failed, theres no available in the locker.",
                -3, spaceShipZeroLocker.createLocker(1, 10));

        assertEquals("Test CreateLockerInvalidOrder failed, the validity of id check first.",
                -1, spaceShip.createLocker(5, 10));
        assertEquals("Test CreateLockerInvalidOrder failed, the validity of capacity check second.",
                -2, spaceShip.createLocker(1, -10));
    }

    /**
     * Testing the CreateLocker of valid inputs, and checks if the lockers have the same constrain.
     */
    @Test
    public void testCreateLocker() {
        assertEquals("Test GetCreateLocker failed, try to add locker valid.",
                0, spaceShip.createLocker(1, 100));
        assertEquals("Test GetCreateLocker failed, try to add another locker valid.",
                0, spaceShip.createLocker(-1, 100));
        assertEquals("Test GetCreateLocker failed, try to add locker to same Id.",
                0, spaceShip.createLocker(-1, 100));
        assertEquals("Test GetCreateLocker failed, try to add locker to same Id with zero capacity.",
                0, spaceShip.createLocker(-1, 0));
        spaceShip.getLockers()[0].addItem(baseballItem, 2);
        assertEquals("Test GetCreateLocker failed, check if the locker have the same constrain.",
                -2, spaceShip.getLockers()[0].addItem(footballItem, 2));
        spaceShip.getLockers()[1].addItem(footballItem, 2);
        assertEquals("Test GetCreateLocker failed, check if the locker have the same constrain when" +
                " id have more then one locker.", -2, spaceShip.getLockers()[1].addItem(baseballItem, 2));
    }

    /**
     * Testing the GetLockers, checks that the function return the lockers.
     */
    @Test
    public void testGetLockers() {
        assertNotNull("Test GetCreateLocker failed, the array shouldn't be null",
                spaceShip.getLockers());
        assertEquals("Test GetCreateLocker failed, number of lockers is not correct",
                6, spaceShip.getLockers().length);
        assertEquals("Test GetCreateLocker failed, number of lockers is not correct",
                6, spaceShipNoID.getLockers().length);

        assertEquals("Test GetCreateLocker failed, number of lockers is not correct," +
                " this spaceShip not have lockers.", 0, spaceShipZeroLocker.getLockers().length);
        for (int i = 0; i < AMOUNT_OF_LOCKER; i++) {
            assertNull("Test GetCreateLocker failed, the array should be null before adding locker",
                    spaceShip.getLockers()[i]);
            spaceShip.createLocker(1, 100);
            assertNotNull("Test GetCreateLocker failed, not null after adding locker",
                    spaceShip.getLockers()[i]);
            assertEquals("Test GetCreateLocker failed, the capacity of the locker should be 100",
                    100 ,spaceShip.getLockers()[i].getCapacity());
        }
    }

    /**
     * Testing the GetCrewIDs, checks that the crew id is valid and contains the all parameters.
     */
    @Test
    public void testGetCrewIDs() {
        assertNotNull("Test GetCrewIDs failed, the array shouldn't be null", spaceShip.getCrewIDs());
        assertEquals("Test GetCrewIDs failed, number of lockers is not correct", 6,
                spaceShip.getCrewIDs().length);
        assertEquals("Test GetCrewIDs failed, number of lockers is not correct, no ID in the spaceShip.",
                0, spaceShipNoID.getCrewIDs().length);
        assertEquals("Test GetCrewIDs failed, number of lockers is not correct, SpaceShip with no " +
                "lockers but with ID.", 6, spaceShipZeroLocker.getCrewIDs().length);
        assertEquals("Test GetCrewIDs failed, there is ID suppose to be in the array.",
                0, spaceShip.createLocker(-1, 1));
        assertEquals("Test GetCrewIDs failed, there is ID suppose to be in the array.",
                0, spaceShip.createLocker(0, 1));
        assertEquals("Test GetCrewIDs failed, there is ID suppose to be in the array.",
                0, spaceShip.createLocker(1, 1));
        assertEquals("Test GetCrewIDs failed, there is ID suppose to be in the array.",
                0, spaceShip.createLocker(2, 1));
        assertEquals("Test GetCrewIDs failed, there is ID suppose to be in the array.",
                0, spaceShip.createLocker(3, 1));
        assertEquals("Test GetCrewIDs failed, there is ID suppose to be in the array.",
                0, spaceShip.createLocker(4, 1));
    }
}
