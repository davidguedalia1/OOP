import oop.ex3.spaceship.*;
import org . junit .*;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * This class is Locker Test, This class checks the all methods of Locker, and runs the test
 * of this class.
 * @author David Guedalia
 */
public class LockerTest {

    /**
     * Locker capacity of normal locker.
     */
    private final static int CAPACITY_NORMAL_LOCKER = 100;

    /**
     * Locker capacity of big locker.
     */
    private final static int CAPACITY_BIG_LOCKER = 1000;

    /**
     * Locker capacity of empty locker.
     */
    private final static int CAPACITY_ZERO_LOCKER = 0;

    /**
     * Object of normal locker.
     */
    private static Locker lockerNormal;

    /**
     * Object of big locker.
     */
    private static Locker lockerBig;

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
     * Very big item, volume 600.
     */
    private static Item bigItem;

    /**
     * Locker with 0 capacity.
     */
    private static Locker lockerZero;

    /**
     * Array od the constraints.
     */
    private static Item[][] constraintsArray;

    /**
     * the long term storage
     */
    private static LongTermStorage longTermStorage;

    /**
     * This function create the objects to run the test.
     */
    @BeforeClass
    public static void createTest() {
        constraintsArray = ItemFactory.getConstraintPairs();
        longTermStorage = new LongTermStorage();
        nullItem = null;
        negativeItem = new Item("Negative", -3);
        zeroItem = new Item("Zero", 0);
        bigItem = new Item("Big Item", 600);
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
    public void emptyLockers() {
        lockerNormal = new Locker(longTermStorage, CAPACITY_NORMAL_LOCKER, constraintsArray);
        lockerBig = new Locker(longTermStorage, CAPACITY_BIG_LOCKER, constraintsArray);
        lockerZero = new Locker(longTermStorage, CAPACITY_ZERO_LOCKER, constraintsArray);
        longTermStorage.resetInventory();
    }

    /**
     * Test that checks the capacity of the locker.
     */
    @Test
    public void testCapacity() {
        assertEquals("Capacity test failed, normal locker", CAPACITY_NORMAL_LOCKER,
                lockerNormal.getCapacity());
        assertEquals("Capacity test failed, big locker", CAPACITY_BIG_LOCKER, lockerBig.getCapacity());
        assertEquals("Capacity test failed, locker with no space", CAPACITY_ZERO_LOCKER,
                lockerZero.getCapacity());
    }

    /**
     * Test that checks the available capacity of the locker.
     */
    @Test
    public void testAvailableCapacity() {
        assertEquals("Available capacity size test, normal locker",
                lockerNormal.getAvailableCapacity(), lockerNormal.getCapacity());
        assertEquals("Available capacity test, big locker",
                lockerBig.getAvailableCapacity(), lockerBig.getCapacity());
        assertEquals("Available capacity test, locker with no space",
                lockerZero.getAvailableCapacity(), lockerZero.getCapacity());
    }

    /**
     * Test that checks the capacity after add item.
     */
    @Test
    public void testCapacityAfterAddItem() {
        lockerNormal.addItem(sporesItem, 5);
        assertEquals("Failed test add item and changing the capacity",
                100, lockerNormal.getCapacity());
        lockerNormal.removeItem(sporesItem, 3);
        assertEquals("Failed test remove item and changing the capacity",
                100, lockerNormal.getCapacity());
        lockerNormal.removeItem(sporesItem, 2);
        assertEquals("Failed test remove all the items and changing the capacity",
                100, lockerNormal.getCapacity());
        lockerZero.addItem(sporesItem, 2);
        assertEquals("Failed test add item and changing the capacity, locker- zero capacity",
                0, lockerZero.getCapacity());
        lockerZero.removeItem(sporesItem, 1);
        assertEquals("Failed test remove item and changing the capacity, locker- zero capacity",
                0, lockerZero.getCapacity());
    }

    /**
     * Test that checks the available capacity after adding item in the locker.
     */
    @Test
    public void testAvailableCapacityAfterAddItem() {
        lockerNormal.addItem(sporesItem, 2);
        assertEquals("Test available capacity failed after adding two spores item",
                80, lockerNormal.getAvailableCapacity());
        lockerNormal.removeItem(sporesItem, 1);
        assertEquals("Test available capacity failed after remove item", 90,
                lockerNormal.getAvailableCapacity());
        lockerNormal.removeItem(sporesItem, 1);
        assertEquals("Test available capacity failed after remove all items", 100,
                lockerNormal.getAvailableCapacity());

        lockerZero.addItem(sporesItem, 2);
        assertEquals("Test available capacity failed after adding two spores item in zero locker",
                0, lockerZero.getAvailableCapacity());
        lockerZero.removeItem(sporesItem, 1);
        assertEquals("Test available capacity failed after removing two spores item in zero locker",
                0, lockerZero.getAvailableCapacity());
    }

    /**
     * Test that checks the GetItemCount function that counts how many from each item.
     */
    @Test
    public void testGetItemCount() {
        assertEquals("Test GetItemCount failed when calling item is not in the locker",
                0, lockerNormal.getItemCount("NoBody"));

        assertEquals("Test GetItemCount failed when calling item is null",
                -1, lockerZero.getItemCount(null));

        lockerBig.addItem(helmetItem, 3);
        assertEquals("Test GetItemCount failed after add item", 3,
                lockerBig.getItemCount(helmetItem.getType()));

        lockerBig.addItem(helmetItem, 5);
        assertEquals("Test GetItemCount failed after add from the same item.",
                8, lockerBig.getItemCount(helmetItem.getType()));

        lockerBig.addItem(baseballItem, 2);
        assertEquals("Test GetItemCount failed after add from the different item.",
                2, lockerBig.getItemCount(baseballItem.getType()));

        lockerBig.removeItem(helmetItem, 3);
        assertEquals("Test GetItemCount failed after remove part of the item.", 5,
                lockerBig.getItemCount(helmetItem.getType()));

        lockerBig.removeItem(baseballItem, 2);
        assertEquals("Test GetItemCount failed after remove full of the item.", 0,
                lockerBig.getItemCount(baseballItem.getType()));

        /* Test getItemCount for locker with zero*/
        assertEquals("Test GetItemCount failed when calling item is not in the locker, zero locker",
                0, lockerZero.getItemCount("NoBody"));
        lockerZero.addItem(baseballItem, 1);
        assertEquals("Test GetItemCount failed after add item, zero locker",
                0, lockerZero.getItemCount(baseballItem.getType()));
        lockerBig.removeItem(baseballItem, 1);
        assertEquals("Test GetItemCount failed after remove part of the item, zero locker.",
                0, lockerZero.getItemCount(baseballItem.getType()));

    }

    /**
     * Test that checks the GetInventory function that counts how many from each item.
     */
    @Test
    public void testGetInventory() {
        assertNotNull("Test GetInventory failed starts return null", lockerNormal.getInventory());
        assertTrue("Test GetInventory failed starts with not empty locker",
                lockerNormal.getInventory().isEmpty());

        lockerBig.addItem(sporesItem, 10);
        assertTrue("Test GetInventory failed add item and its not appear in the inventory",
                lockerBig.getInventory().containsKey(sporesItem.getType()));
        assertEquals("Test GetInventory failed add item the number is incorrect",
                10, lockerBig.getItemCount(sporesItem.getType()));

        lockerBig.addItem(sporesItem, 3);
        assertTrue("Test GetInventory failed add item to exist item and its not appear in the inventory",
                lockerBig.getInventory().containsKey(sporesItem.getType()));
        assertEquals("Test GetInventory failed add item to exist item, the number is incorrect",
                13, lockerBig.getItemCount(sporesItem.getType()));

        lockerBig.addItem(footballItem, 5);
        assertTrue("Test GetInventory failed add different item and its not appear in the inventory",
                lockerBig.getInventory().containsKey(footballItem.getType()));
        assertEquals("Test GetInventory failed add different item and the number is incorrect",
                5, lockerBig.getItemCount(footballItem.getType()));

        lockerBig.removeItem(sporesItem, 7);
        assertEquals("Test GetInventory failed removing part item and the number is incorrect",
                6, lockerBig.getItemCount(sporesItem.getType()));

        lockerBig.removeItem(footballItem, 5);
        assertEquals("Test GetInventory failed removing full item and the number is incorrect",
                0, lockerBig.getItemCount(footballItem.getType()));

        lockerBig.removeItem(sporesItem, 7);
        assertTrue("Test GetInventory failed after removing all items needs to be empty",
                lockerNormal.getInventory().isEmpty());
    }

    /**
     * This test checks for invalid input to function addItem.
     */
    @Test
    public void testInvalidAddItem() {
        assertEquals("Test AddItem failed adding a negative amount of items",
                -1, lockerNormal.addItem(helmetItem, -2));

        assertEquals("Test AddItem failed adding a 0 amount of items",
                0, lockerNormal.addItem(baseballItem, 0));

        assertEquals("Test AddItem adding a null item", -1, lockerNormal.addItem(nullItem, 1));

        assertEquals("Test AddItem failed adding a negative volume of item",
                -1, lockerNormal.addItem(negativeItem, 2));

        assertEquals("Test AddItem failed adding a to locker zero normal item.",
                -1, lockerZero.addItem(baseballItem, 1));
    }


    @Test
    public void tesAddItemZero() {
        assertEquals("Test AddItem failed adding a zero of a item",
                0, lockerNormal.addItem(footballItem, 0));
        assertEquals("Test AddItem failed adding zero of item and change the inventory",
                0, lockerNormal.getItemCount(footballItem.getType()));

        lockerNormal.addItem(sporesItem, 1);
        assertEquals("Test AddItem failed adding a item with volume zero",
                0, lockerNormal.addItem(zeroItem, 2));
        assertEquals("Test AddItem failed adding a item with volume zero, didn't add the item",
                2, lockerNormal.getItemCount(zeroItem.getType()));
        assertEquals("Test AddItem failed adding a item with volume zero, and change the available",
                90, lockerNormal.getAvailableCapacity());
    }

    /**
     * After store 50% of one specific item in the locker try to store more (excepted to get -1 in this case)
     * then there's needs to be 20% only in the locker now try to get 50% and checks if its valid.
     * now trying to add one more - needs to get  -1.
     */
    @Test
    public void testValidAddItem() {
        assertEquals("Test AddItem failed adding a normal item",
                0, lockerNormal.addItem(sporesItem, 2));
        assertEquals("Test AddItem failed after adding additional normal item",
                0, lockerNormal.addItem(sporesItem, 3));
        assertEquals("Test AddItem failed expected 50 to be available", 50,
                lockerNormal.getAvailableCapacity());

        assertEquals("Test AddItem failed after adding item that don't have capacity in the locker",
                -1, lockerNormal.addItem(sporesItem, 6));
        assertEquals("Test AddItem failed expected 50 to be available after invalid input",
                50, lockerNormal.getAvailableCapacity());

        assertEquals("Test AddItem failed after add item needs to be transfer to LTS",
                1, lockerNormal.addItem(sporesItem, 1));
        assertEquals("Test AddItem failed after add item needs to be transfer to LTS," +
                " and the capacity of the locker is not 20% of the item",
                80, lockerNormal.getAvailableCapacity());
        assertEquals("Test AddItem failed after add item needs to be transfer to LTS," +
                " and the capacity of the locker is not 20% of the item",
                960, longTermStorage.getAvailableCapacity());

        assertEquals("Test AddItem failed after adding second time additional normal item",
                0, lockerNormal.addItem(sporesItem, 3));
        assertEquals("Test AddItem failed after adding second time item needs to be transfer to LTS",
                1, lockerNormal.addItem(sporesItem, 1));
        assertEquals("Test AddItem failed after adding second time item needs to be transfer to LTS," +
                " and the capacity of the locker is not 20% of the item",
                80, lockerNormal.getAvailableCapacity());
        assertEquals("Test AddItem failed after add item needs to be transfer to LTS," +
                " and the capacity of the locker is not 20% of the item",
                920, longTermStorage.getAvailableCapacity());

    }

    /**
     * After store 50% of one specific item in the locker try to store more (excepted to get -1 in this case)
     * then there's needs to be 20% only in the locker now try to get 40% and checks if its valid.
     * now trying to add one and gets 50% needs to be valid.
     */
    @Test
    public void testValidAddItemFiftyPercent() {
        /* There is 80 in the locker available and 960 available in long term. */
        assertEquals("Test AddItem failed adding a item is more then 50 percent",
                1, lockerNormal.addItem(sporesItem, 6));
        assertEquals("Test AddItem failed adding a item is more then 50 percent, the amount is not valid",
                80, lockerNormal.getAvailableCapacity());
        assertEquals("Test AddItem failed adding a item is less then 50%", 0,
                lockerNormal.addItem(sporesItem, 3));
    }

    /**
     * Testing the big locker and adding to the long term storage till the available capacity in the
     * long term storage is full, and checking many cases along of this operation.
     */
    @Test
    public void testValidAddItemBigLocker() {
        /* There is 800 in the locker available and 400 available in long term. */
        assertEquals("Test AddItem failed adding a item is more then 50 percent, in the big locker",
                1, lockerBig.addItem(sporesItem, 80));
        assertEquals("Test AddItem failed adding a item is more then 50 percent, in the big locker," +
                " the amount is incorrect", 800, lockerBig.getAvailableCapacity());
        /* no space in locker for 70 sporesItem */
        assertEquals("Test AddItem failed adding a item there is no capacity in the locker and no" +
                " capacity in long term storage", -1, lockerBig.addItem(sporesItem, 70));

        /* There is 800 in the locker available and 400 available in long term. */
        assertEquals("Test AddItem failed adding a item is exactly 50 percent, in the big locker",
                0, lockerBig.addItem(sporesItem, 30));

        /* There is 800 in the locker available and 0 available in long term. */
        assertEquals("Test AddItem failed adding a item is more then 50 percent, in the big locker",
                1, lockerBig.addItem(sporesItem, 10));
        assertEquals("Test AddItem failed adding a item is more then 50 percent, in the big locker," +
                " the amount of the locker isn't correct", 800, lockerBig.getAvailableCapacity());

        /* There is 496 in the locker available and 0 available in long term. */
        assertEquals("Test AddItem failed adding a new item is more then 50 percent, in the big locker",
                0, lockerBig.addItem(footballItem, 76));

        /* There is 300 in the locker available. */
        assertEquals("Test AddItem failed adding a new item is exactly then 50 percent, in the big locker",
                0, lockerBig.addItem(footballItem, 49));
        assertEquals("Test AddItem failed adding a new item is exactly then 50 percent," +
                " the amount un the locker is not correct", 300, lockerBig.getAvailableCapacity());

        /* 0 available in long term. */
        assertEquals("Test AddItem failed adding a new item is more then 50 percent," +
                " no space in long term storage", -1, lockerBig.addItem(footballItem, 1));

        /* There is 300 in the locker available.*/
        assertEquals("Test AddItem failed adding a new item is making full the locker",
                0, lockerBig.addItem(helmetItem, 100));
        assertEquals("Test AddItem failed adding a new item is making full the locker, the capacity is not 0",
                0, lockerBig.getAvailableCapacity());

        assertEquals("Test AddItem failed adding a item with volume zero," +
                " there is not space in locker and LTS", 0, lockerNormal.addItem(zeroItem, 2));
        assertEquals("Test AddItem failed adding a new item there is no space.",
                -1, lockerBig.addItem(helmetBigItem, 1));
    }

    @Test
    public void testValidAddItemTwoLockerToLTS() {
        /* There is 80 in the locker available and 960 available in long term. */
        assertEquals("Test AddItem failed adding a item is more then 50 percent",
                1, lockerNormal.addItem(sporesItem, 6));

        /* There is 60 in the locker available and 925 available in long term. */
        assertEquals("Test AddItem failed adding new item is more then 50 percent.",
                1, lockerNormal.addItem(helmetBigItem, 11));
        assertEquals("Test AddItem failed adding new item is more then 50 percent, the amount is incorrect",
                60, lockerNormal.getAvailableCapacity());

        /* There is 42 in the locker available and 892 available in long term. */
        assertEquals("Test AddItem failed adding third item is more then 50 percent.",
                1, lockerNormal.addItem(helmetItem,  17));
        assertEquals("Test AddItem failed adding third item is more then 50 percent, the amount is incorrect",
                42, lockerNormal.getAvailableCapacity());

        /* There is 42 in the locker available and 492 available in long term. */
        assertEquals("Test AddItem failed adding new item is more then 50 percent to the big locker",
                1, lockerBig.addItem(sporesItem, 60));

        /* There is 42 in the locker available and 492 available in long term. */
        assertEquals("Test AddItem failed adding new item there's no space in long term.", -1,
                lockerBig.addItem(sporesItem, 60));

        /* There is 42 in the locker available and 42 available in long term. */
        assertEquals("Test AddItem failed adding new item is more then 50," +
                " because interior adding of same item. ", 1, lockerBig.addItem(sporesItem, 40));

        assertEquals("Test AddItem failed adding items, the amount of long term is incorrect",
                92, longTermStorage.getAvailableCapacity());
        assertEquals("Test AddItem failed adding items, the amount of big locker is incorrect",
                800, lockerBig.getAvailableCapacity());

        /* There is 42 in the locker available and 42 available in long term. */
        assertEquals("Test AddItem failed adding item there is no space in the locker",
                -1, lockerNormal.addItem(baseballItem, 22));

        /* There is 0 in the locker available and 42 available in long term. */
        assertEquals("Test AddItem failed adding a item with volume zero when there is not space" +
                " in the locker should success", 0, lockerNormal.addItem(zeroItem, 2));

        assertEquals("Test AddItem failed adding item there is no space in the locker",
                0, lockerNormal.addItem(baseballItem, 21));
        assertEquals("Test AddItem failed adding the capacity needs to be 0",
                0, lockerNormal.getAvailableCapacity());
        assertEquals("Test AddItem failed adding the capacity of the long term  needs to be 92",
                92, longTermStorage.getAvailableCapacity());
    }

    /**
     * This test checks invalid inputs of RemoveItem
     */
    @Test
    public void testAddBigItem(){
        assertEquals("Test AddItem failed adding item there is no space in the locker",
                1, lockerBig.addItem(bigItem, 1));
        assertEquals("Test AddItem failed adding item there is no space in the locker",
                -1, lockerBig.addItem(bigItem, 1));
        assertEquals("Test AddItem failed adding item there is no space in the locker",
                400, longTermStorage.getAvailableCapacity());
    }

    /**
     * This test checks invalid inputs of RemoveItem
     */
    @Test
    public void testRemoveItemInvalid(){
        int result;
        lockerNormal.addItem(helmetItem, 2);
        result = lockerNormal.removeItem(helmetItem, -2);
        assertEquals("Test RemoveItem failed adding a negative amount of items", -1, result);
        assertEquals("Test RemoveItem failed remove item shouldn't removed.",
                2, lockerNormal.getItemCount(helmetItem.getType()));

        result = lockerNormal.removeItem(baseballItem, 0);
        assertEquals("Test RemoveItem failed adding a 0 amount of items", 0, result);

        result = lockerNormal.removeItem(nullItem, 1);
        assertEquals("Test RemoveItem adding a null item", -1, result);

        result = lockerNormal.removeItem(negativeItem, 2);
        assertEquals("Test RemoveItem failed removing a negative volume of item", -1, result);

        lockerZero.addItem(baseballItem, 2);
        result = lockerZero.removeItem(baseballItem, 1);
        assertEquals("Test RemoveItem failed removing to locker zero normal item.", -1, result);
    }

    /**
     * This test checks RemoveItem try to remove more then capacity of that item.
     */
    @Test
    public void testRemoveItemTooMany(){
        lockerNormal.addItem(helmetBigItem, 5);
        assertEquals("Test RemoveItem failed try to remove more then capacity.",
                -1, lockerNormal.removeItem(helmetBigItem, 6));
    }

    @Test
    public void testRemoveItemSuccess(){
        lockerNormal.addItem(helmetBigItem, 5);
        assertEquals("Test RemoveItem failed removed one unit from 5.",
                0, lockerNormal.removeItem(helmetBigItem, 1));
        assertEquals("Test RemoveItem failed didn't removed the item",
                4, lockerNormal.getItemCount(helmetBigItem.getType()));
        assertEquals("Test RemoveItem failed removed 4 unit from 4.",
                0, lockerNormal.removeItem(helmetBigItem, 4));
        assertTrue("Test RemoveItem failed didn't removed the item",lockerNormal.getInventory().isEmpty());

        lockerNormal.addItem(zeroItem, 2);
        assertEquals("Test RemoveItem failed removing a item with volume zero",
                0, lockerNormal.removeItem(zeroItem, 2));
        assertEquals("Test RemoveItem failed didn't removed the item",
                0, lockerNormal.getItemCount(zeroItem.getType()));
        assertEquals("Test RemoveItem failed removing a item with volume zero, and change the available",
                100, lockerNormal.getAvailableCapacity());
    }

    @Test
    public void testRemoveItemSuccessManyItem(){
        lockerNormal.addItem(helmetBigItem, 5);
        lockerNormal.addItem(helmetItem, 3);

        assertEquals("Test RemoveItem failed, removed 4 unit from 5.",
                0, lockerNormal.removeItem(helmetBigItem, 4));
        assertEquals("Test RemoveItem failed, removed 2 unit from 3.",
                0, lockerNormal.removeItem(helmetItem, 2));

        assertEquals("Test RemoveItem failed, didn't removed the item",
                1, lockerNormal.getItemCount(helmetBigItem.getType()));
        assertEquals("Test RemoveItem failed, didn't removed the item",
                1, lockerNormal.getItemCount(helmetItem.getType()));
    }

    @Test
    public void testConstrain(){
        Item football = ItemFactory.createSingleItem("football");
        Item baseballBat = ItemFactory.createSingleItem("baseball bat");
        lockerNormal.addItem(football, 5);
        assertEquals("Test Constrain failed add item have a constrain",
                -2, lockerNormal.addItem(baseballBat, 1));
        assertEquals("Test Constrain failed add item to inventory and shouldn't because item have a constrain",
                0, lockerNormal.getItemCount(baseballBat.getType()));
        lockerNormal.removeItem(football, 5);
        assertEquals("Test Constrain failed no constrain in the locker",
                0, lockerNormal.addItem(baseballBat, 1));
        assertEquals("Test Constrain failed add item to inventory and shouldn't because item have a constrain",
                -2, lockerNormal.addItem(football, 1));
    }

    @Test
    public void testConstrainZero(){
        Item football = ItemFactory.createSingleItem("football");
        Item baseballBat = ItemFactory.createSingleItem("baseball bat");
        lockerNormal.addItem(football, 0);
        assertEquals("Test Constrain failed add item have no constrain, because football adds 0 units",
                0, lockerNormal.addItem(baseballBat, 1));
        assertEquals("Test Constrain failed add item to inventory and shouldn't because item have a constrain",
                1, lockerNormal.getItemCount(baseballBat.getType()));
        lockerNormal.removeItem(baseballBat, 1);
        lockerNormal.addItem(baseballBat, 0);
        assertEquals("Test Constrain failed add item have no constrain, because football adds 0 units",
                0, lockerNormal.addItem(football, 1));

        assertEquals("Test Constrain failed add item have constrain, even if 0 units",
                -2, lockerNormal.addItem(baseballBat, 0));
        lockerNormal.removeItem(football, 1);
        lockerNormal.addItem(baseballBat, 1);
        assertEquals("Test Constrain failed add item have constrain, even if 0 units",
                -2, lockerNormal.addItem(football, 0));

    }
}