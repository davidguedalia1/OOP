import oop.ex3.spaceship.*;
import org.junit.*;

import static org.junit.Assert.*;

import java.util.Random;

/**
 * This class is LongTerm Test, This class checks the all methods of LongTermStorage, and runs the test
 * of this class.
 * @author David Guedalia
 */
public class LongTermTest {

    /**
     * This is the capacity of the long term storage is 1000.
     */
    private static final int CAPACITY_LONG_TERM = 1000;

    /**
     * Baseball item of volume 2.
     */
    private static Item baseballItem;

    /**
     * Helmet item of volume 3.
     */
    private static Item helmetItem;

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
     * Null item.
     */
    private static Item nullItem;

    /**
     * Item with negative volume.
     */
    private static Item negativeItem;

    /**
     * Item with 0 volume.
     */
    private static Item zeroItem;

    /**
     * the long term storage
     */
    private static LongTermStorage longTermStorage;

    /**
     * This function run once, in the beginning of the test, this function
     * creates the object of the test.
     */
    @BeforeClass
    public static void createTest() {
        longTermStorage = new LongTermStorage();
        sporesItem = ItemFactory.createSingleItem("spores engine");
        footballItem = ItemFactory.createSingleItem("football");
        baseballItem = ItemFactory.createSingleItem("baseball bat");
        helmetItem = ItemFactory.createSingleItem("helmet, size 1");
        helmetBigItem = ItemFactory.createSingleItem("helmet, size 3");
    }

    /**
     * This function running before each test and reset the objects for the next test.
     */
    @Before
    public void resetTest() {
        longTermStorage.resetInventory();
    }

    /**
     * Testing the capacity of the long term storage after adding and resetting.
     */
    @Test
    public void testCapacity() {
        assertEquals("Test Capacity failed, wrong capacity.",
                CAPACITY_LONG_TERM, longTermStorage.getCapacity());
        longTermStorage.addItem(sporesItem, 1);
        assertEquals("Test Capacity failed, after adding, wrong capacity.",
                CAPACITY_LONG_TERM, longTermStorage.getCapacity());
        longTermStorage.resetInventory();
        assertEquals("Test Capacity failed, after reset the storage, wrong capacity.",
                CAPACITY_LONG_TERM, longTermStorage.getCapacity());
    }

    /**
     * Test that checks the available capacity of the long term storage.
     */
    @Test
    public void testAvailableCapacity() {
        assertEquals("Test AvailableCapacity failed after initial, wrong capacity.",
                CAPACITY_LONG_TERM, longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(sporesItem, 10);
        assertEquals("Test AvailableCapacity failed, after adding, wrong capacity.",
                CAPACITY_LONG_TERM - (sporesItem.getVolume() * 10), longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(sporesItem, 80);
        assertEquals("Test AvailableCapacity failed, after adding another item same, wrong capacity.",
                100, longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(helmetBigItem, 20);
        assertEquals("Test AvailableCapacity failed,  after adding another different item," +
                " the storage is full, wrong capacity.", 0, longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(baseballItem, 1);
        assertEquals("Test AvailableCapacity failed, the storage is full, trying to add another item," +
                " wrong capacity.", 0, longTermStorage.getAvailableCapacity());
    }

    /**
     * Test that checks the GetInventory function that counts how many from each item.
     */
    @Test
    public void testGetInventory() {
        assertTrue("Test GetInventory failed after initial, not empty.",
                longTermStorage.getInventory().isEmpty());
        longTermStorage.addItem(sporesItem, 10);
        assertTrue("Test GetInventory failed add item and its not appear in the inventory",
                longTermStorage.getInventory().containsKey(sporesItem.getType()));
        assertEquals("Test GetInventory failed add item the number is incorrect",
                10, longTermStorage.getItemCount(sporesItem.getType()));

        longTermStorage.addItem(sporesItem, 3);
        assertTrue("Test GetInventory failed add item to exist item and its not appear in the inventory",
                longTermStorage.getInventory().containsKey(sporesItem.getType()));
        assertEquals("Test GetInventory failed add item to exist item, the number is incorrect",
                13, longTermStorage.getItemCount(sporesItem.getType()));

        longTermStorage.addItem(footballItem, 5);
        assertTrue("Test GetInventory failed add different item and its not appear in the inventory",
                longTermStorage.getInventory().containsKey(footballItem.getType()));
        assertEquals("Test GetInventory failed add different item and the number is incorrect",
                5, longTermStorage.getItemCount(footballItem.getType()));

        longTermStorage.resetInventory();
        assertTrue("Test GetInventory failed after removing all items needs to be empty",
                longTermStorage.getInventory().isEmpty());
    }

    /**
     * Testing ResetInventory that is actually resting the inventory.
     */
    @Test
    public void testResetInventory() {
        longTermStorage.addItem(helmetBigItem, 5);
        longTermStorage.resetInventory();
        assertEquals("Test ResetInventory failed after add item and reset the inventory needs to be empty",
                "{}", longTermStorage.getInventory().toString());
        assertEquals("Test ResetInventory failed after add item and reset the inventory," +
                " capacity should be full.", CAPACITY_LONG_TERM, longTermStorage.getAvailableCapacity());
    }

    /**
     * Test that checks the GetItemCount function that counts how many from each item.
     */
    @Test
    public void testGetItemCount() {
        assertEquals("Test GetItemCount failed when calling item is not in the locker",
                0, longTermStorage.getItemCount("NoBody"));

        assertEquals("Test GetItemCount failed when calling item is null", -1,
                longTermStorage.getItemCount(null));

        longTermStorage.addItem(helmetItem, 3);
        assertEquals("Test GetItemCount failed after add item", 3,
                longTermStorage.getItemCount(helmetItem.getType()));

        longTermStorage.addItem(helmetItem, 5);
        assertEquals("Test GetItemCount failed after add from the same item.",
                8, longTermStorage.getItemCount(helmetItem.getType()));

        longTermStorage.addItem(baseballItem, 2);
        assertEquals("Test GetItemCount failed after add from the different item.",
                2, longTermStorage.getItemCount(baseballItem.getType()));

        longTermStorage.resetInventory();
        assertEquals("Test GetItemCount failed after remove full of the items.",
                0, longTermStorage.getItemCount(baseballItem.getType()));
    }

    /**
     * This test checks for invalid input to function addItem.
     */
    @Test
    public void testAddItemInvalid() {
        assertEquals("Test AddItem failed adding a negative amount of items",
                -1, longTermStorage.addItem(helmetItem, -2));

        assertEquals("Test AddItem failed adding a 0 amount of items",
                0, longTermStorage.addItem(baseballItem, 0));

        assertEquals("Test AddItem adding a null item",
                -1, longTermStorage.addItem(nullItem, 1));

        assertEquals("Test AddItem failed adding a negative volume of item",
                -1, longTermStorage.addItem(negativeItem, 2));

    }

    /**
     * This test checks for valid input to function addItem.
     */
    @Test
    public void testAddItemValid() {
        assertEquals("Test AddItem failed adding a item.",
                0, longTermStorage.addItem(helmetBigItem, 40));
        assertEquals("Test AddItem failed adding a item, wrong capacity",
                800, longTermStorage.getAvailableCapacity());

        assertEquals("Test AddItem failed adding a different item",
                0, longTermStorage.addItem(sporesItem, 20));
        assertEquals("Test AddItem failed adding a different item item, wrong capacity",
                600, longTermStorage.getAvailableCapacity());

        assertEquals("Test AddItem failed adding a same item already exist",
                0, longTermStorage.addItem(sporesItem, 60));
        assertEquals("Test AddItem failed adding a same item item, wrong capacity",
                0, longTermStorage.getAvailableCapacity());

        assertEquals("Test AddItem failed adding a zero units of item, should be possible",
                0, longTermStorage.addItem(baseballItem, 0));
        assertEquals("Test AddItem failed adding a zero units of item, wrong capacity",
                0, longTermStorage.getAvailableCapacity());

        assertEquals("Test AddItem failed adding a item with no space in long term storage",
                -1, longTermStorage.addItem(baseballItem, 1));
        assertEquals("Test AddItem failed adding a item with no space in long term storage, wrong capacity",
                0, longTermStorage.getAvailableCapacity());

    }
}