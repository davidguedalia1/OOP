package filesprocessing;
import orders.Order;
import java.io.File;

/**
 * This class is Sorting the files according Quick Sort.
 * @author David Guedalia
 */
public class SortFiles {

    /**
     * order - type of sorting.
     */
    Order order;

    /**
     * The array to sort.
     */
    File[] filesArray;

    /**
     * The Constructor, call the method that sorting the files.
     * @param order - type of sorting.
     * @param filesArray The array to sort.
     * @throws TypeFirstError First type of exception.
     */
    SortFiles(Order order, File[] filesArray) throws TypeFirstError {
        this.order = order;
        this.filesArray = filesArray;
        sort(0, filesArray.length - 1);
    }

    /**
     * @return files array.
     */
    public File[] getFilesArray() {
        return filesArray;
    }

    /**
     * Sort the array according Quick Sort.
     * @param low - the low index.
     * @param high - the high index
     * @throws TypeFirstError First type of exception.
     */
    private void sort(int low, int high) throws TypeFirstError {
        if (low < high){
            int partitionIndex = partition(low, high);
            sort(low, partitionIndex-1);
            sort(partitionIndex+1, high);
        }
    }

    /**
     *  This is the partition of Quick Sort.
     * @param low - the low index.
     * @param high - the high index
     * @return i
     * @throws TypeFirstError First type of exception.
     */
    private int partition(int low, int high) throws TypeFirstError {
        int i = (low - 1);
        for (int j = low; j < high; j++){
            i = compareFiles(filesArray, high, i, j);
        }
        swap(high, i + 1);
        return i + 1;
    }

    /**
     * Compare two files
     * @param filesArray The array to sort.
     * @param pivot - first file
     * @param i - i index.
     * @param j - second file.
     * @return i - index
     * @throws TypeFirstError First type of exception.
     */
    private int compareFiles(File[] filesArray, int pivot, int i, int j) throws TypeFirstError {
        if (order.compare(filesArray[j], filesArray[pivot]) < 0){
            i ++;
            swap(j, i);
        }
        return i;
    }

    /**
     * Swap two files.
     * @param j - first file.
     * @param i - second file.
     */
    private void swap(int j, int i) {
        File temp = filesArray[i];
        filesArray[i] = filesArray[j];
        filesArray[j] = temp;
    }
}
