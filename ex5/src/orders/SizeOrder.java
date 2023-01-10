package orders;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Size order class - Sort files by size of the file.
 * @author David Guedalia
 */
public class SizeOrder extends Order {

    /**
     * Constructor of Size order.
     * @param order array of the command of the order.
     * @throws TypeFirstError First type of exception.
     */
    public SizeOrder(String[] order) throws TypeFirstError {
        super(order);
    }

    /**
     * This method compare two files according the order.
     * @param firstFile First file
     * @param secondFile Second file
     * @return 0 if they are equal, 1 if first file is bigger, -1 otherwise.
     */
    @Override
    public int compare(File firstFile, File secondFile) {
        if (firstFile.length() == secondFile.length()) {
            if (reverseOrder) {
                return defaultOrderCompare(secondFile, firstFile);
            }
            return defaultOrderCompare(firstFile, secondFile);
        }
        if (reverseOrder) {
            return firstFile.length() < secondFile.length() ? 1 : -1;
        }
        else {
            return firstFile.length() > secondFile.length() ? 1 : -1;
        }
    }
}
