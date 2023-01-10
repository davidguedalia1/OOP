package orders;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Abs order class - Sort files by absolute name (from "a" to "z").
 * @author David Guedalia
 */
public class AbsOrder extends Order {

    /**
     * Constructor of Abs order.
     * @param order array of the command of the order.
     * @throws TypeFirstError First type of exception.
     */
    public AbsOrder(String[] order) throws TypeFirstError {
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
        if (reverseOrder){
            return -(firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath()));
        }
        else {
            return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
        }
    }
}
