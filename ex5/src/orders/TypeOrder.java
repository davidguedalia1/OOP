package orders;
import filesprocessing.TypeFirstError;
import java.io.File;

/**
 * Type order class - Sort files by type of the file.
 * @author David Guedalia
 */
public class TypeOrder extends Order {

    /**
     * Dot.
     */
    private static final String DOT = ".";

    /**
     * Empty string.
     */
    private static final String EMPTY = "";

    /**
     * Minus one.
     */
    protected static final int MINE_ONE = -1;

    /**
     * Constructor of Type order.
     * @param order array of the command of the order.
     * @throws TypeFirstError First type of exception.
     */
    public TypeOrder(String[] order) throws TypeFirstError {
        super(order);
    }

    /**
     * Checking the type of the file.
     * @param file File.
     * @return String - the type of the file.
     */
    private String type(File file){
        String output;
        String name = file.getName();
        int index = name.lastIndexOf(DOT);
        if (index <= 0){
            output =  EMPTY;
        }
        else if (name.substring(index).equals(EMPTY)){
            output = EMPTY;
        }
        else {
            output = name.substring(index + 1);
        }
        return output;
    }

    /**
     * This method compare two files according the order.
     * @param firstFile First file
     * @param secondFile Second file
     * @return 0 if they are equal, 1 if first file is bigger, -1 otherwise.
     */
    @Override
    public int compare(File firstFile, File secondFile) {
        if (type(firstFile).equals(type(secondFile))) {
            if (reverseOrder) {
                return defaultOrderCompare(secondFile, firstFile);
            } else {
                return defaultOrderCompare(firstFile, secondFile);
            }
        }
        else {
            if (reverseOrder) {
                return MINE_ONE * type(firstFile).compareTo(type(secondFile));
            }
            else {
                return type(firstFile).compareTo(type(secondFile));
            }
        }
    }
}
