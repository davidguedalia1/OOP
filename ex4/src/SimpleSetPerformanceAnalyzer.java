import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * This class has a main method that measures the run-times requested in the "Performance Analysis" section.
 * And running basic data methods and comparing between them.
 * @author David Guedalia
 */
public class SimpleSetPerformanceAnalyzer {
    /**
     * First data that contains different words with the same hash.
     */
    private static final String FIRST_DATA = "data1.txt";

    /**
     * Second data that contains different words but natural mixture.
     */
    private static final String SECOND_DATA = "data2.txt";

    /**
     * hi string.
     */
    private static final String HI_STRING = "hi";

    /**
     * -13170890158 string.
     */
    private static final String LONG_NUMBER = "-13170890158";

    /**
     * 23 string.
     */
    private static final String TWENTY_THREE_STRING = "23";

    /**
     * Not printing the results.
     */
    private static final int NOT_PRINT = 0;

    /**
     * Printing the results.
     */
    private static final int PRINT = 1;

    /**
     * 70,000 iterations as  warm up before checks the contains.
     */
    private static final int WARMUP = 70000;

    /**
     * 7,000 iterations as for linked list.
     */
    private static final int WARMUP_LINKED_LIST = 7000;

    /**
     * Divide the result by million
     */
    private static final int DIVIDE_RESULT = 1000000;

    /**
     * Tree data structure of tree.
     */
    private static SimpleSet treeSet;

    /**
     * Tree data structure od linked list.
     */
    private static SimpleSet linkedList;

    /**
     * Tree data structure of hash set.
     */
    private static SimpleSet hashSet;

    /**
     * Tree data structure of open hash.
     */
    private static SimpleSet openHash;

    /**
     * Tree data structure of closed hash set.
     */
    private static SimpleSet closedHash;

    /**
     * Array that contains the all data structure to compare.
     */
    private static SimpleSet[] dataSet;

    /**
     * array of String that contains the data1.txt
     */
    private final String[] data1;

    /**
     * array of String that contains the data2.txt
     */
    private final String[] data2;

    /**
     * The strings to check the contains method.
     */
    private final String[] strToCheck = new String[]{"hi", "23", "-13170890158"};

    /**
     * The strings to check the contains method.
     */
    private final String[] namesOfDataSet = new String[]{"Linked List", "Tree Set",
            "Hash Set", "Open Hash", "Closed Hash"};

    /**
     * This is the constructor of the class, init the data.
     */
    public SimpleSetPerformanceAnalyzer() {
        data1 = Ex4Utils.file2array(FIRST_DATA);
        data2 = Ex4Utils.file2array(SECOND_DATA);
        arrayReset();
    }

    /**
     * This function reset the arrays.
     */
    private void arrayReset() {
        treeSet = new CollectionFacadeSet(new TreeSet<String>());
        linkedList = new CollectionFacadeSet(new LinkedList<String>());
        hashSet = new CollectionFacadeSet(new HashSet<String>());
        openHash = new OpenHashSet();
        closedHash = new ClosedHashSet();
        dataSet = new SimpleSet[]{linkedList, treeSet, hashSet, openHash, closedHash};
    }

    /**
     * This function gets data as a input and add the data
     * to each data structure.
     * @param data to check the data structure
     */
    public void addToSimpleSet(String[] data){
        int indexOfData = 0;
        for (SimpleSet simpleSet : dataSet){
            addAllData(data, simpleSet, PRINT, indexOfData);
            indexOfData++;
        }
    }

    /**
     * This method add the data to the data structure.
     * @param data to check the data structure.
     * @param simpleSet data structure to add the data.
     */
    private void addAllData(String[] data, SimpleSet simpleSet, int flagPrint, int indexOfData) {
        long timeBefore = System.nanoTime();
        for (String str : data){
            simpleSet.add(str);
        }
        long difference = System.nanoTime() - timeBefore;
        if (flagPrint == PRINT)
            System.out.println(namesOfDataSet[indexOfData] + " " + difference / DIVIDE_RESULT);
    }

    /**
     * To each data set check if given string is there.
     * @param data to check the data structure.
     * @param str String to check if it is contains in the data structure.
     */
    public void containsToSimpleSet(String[] data, String str) {
        int indexOfData = 0;
        for (SimpleSet simpleSet : dataSet){
            addAllData(data, simpleSet, NOT_PRINT, NOT_PRINT);
            containsInSimpleHash(str, simpleSet, indexOfData);
            indexOfData++;

        }
    }

    /**
     * check if string is in specific data set
     * @param str String to check if it is contains in the data structure.
     * @param simpleSet data structure to check if string is in the data.
     */
    private void containsInSimpleHash(String str, SimpleSet simpleSet, int indexOfData) {
        int numberIteration = WARMUP_LINKED_LIST;
        if (simpleSet != linkedList){
            makeWarmup(str, simpleSet);
            numberIteration = WARMUP;
        }
        long timeBefore = System.nanoTime();
        for (int i = 0; i < numberIteration; i++) {
            simpleSet.contains(str);
        }
        long difference = System.nanoTime() - timeBefore;
        System.out.println(namesOfDataSet[indexOfData] + " " + difference / numberIteration);
    }

    /**
     * Do warm up to get better results.
     * @param str String to check if it is contains in the data structure.
     * @param simpleSet data structure to check if string is in the data.
     */
    private void makeWarmup(String str, SimpleSet simpleSet) {
        for (int i = 0; i < WARMUP; i++) {
            simpleSet.contains(str);
        }
    }

    public static void main(String[] args) {
        SimpleSetPerformanceAnalyzer sim = new SimpleSetPerformanceAnalyzer();
        /*test 1*/
        addData1(sim);
        /*test 2*/
        addData2(sim);
        /*test 3*/
        containsHiData1(sim);
        /*test 4*/
        containsLongNumberData1(sim);
        /*test 5*/
        contains23Data2(sim);
        /*test 6*/
        containsHiData2(sim);
    }

    private static void containsHiData2(SimpleSetPerformanceAnalyzer sim) {
        sim.arrayReset();
        System.out.println("test 6");
        sim.containsToSimpleSet(sim.data2, HI_STRING);
    }

    private static void contains23Data2(SimpleSetPerformanceAnalyzer sim) {
        sim.arrayReset();
        System.out.println("test 5");
        sim.containsToSimpleSet(sim.data2, TWENTY_THREE_STRING);
    }

    private static void containsLongNumberData1(SimpleSetPerformanceAnalyzer sim) {
        sim.arrayReset();
        System.out.println("test 4");
        sim.containsToSimpleSet(sim.data1, LONG_NUMBER);
    }

    private static void containsHiData1(SimpleSetPerformanceAnalyzer sim) {
        sim.arrayReset();
        System.out.println("test 3");
        sim.containsToSimpleSet(sim.data1, HI_STRING);
    }

    private static void addData2(SimpleSetPerformanceAnalyzer sim) {
        sim.arrayReset();
        System.out.println("test 2");
        sim.addToSimpleSet(sim.data2);
    }

    private static void addData1(SimpleSetPerformanceAnalyzer sim) {
        sim.arrayReset();
        System.out.println("test 1");
        sim.addToSimpleSet(sim.data1);
    }
}