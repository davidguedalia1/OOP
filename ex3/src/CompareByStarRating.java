import oop.ex3.searchengine.*;
import java.util.Comparator;

/**
 * This class comparing hotels by the star rating of them.
 * this class implements from Comparator, and overriding compare function.
 * @author David Guedalia
 */
public class CompareByStarRating implements Comparator<Hotel>{

    /**
     * Tis function comparing between to hotels by the star rating.
     * @param first - first hotel.
     * @param second - second hotel
     * @return - 0 if the hotel are equal, -1 if first hotel is bigger, 1 otherwise.
     */
    @Override
    public int compare(Hotel first, Hotel second) {
        if (first.getStarRating() == second.getStarRating()){
            return compareByName(first, second);
        }
        return (first.getStarRating() > second.getStarRating() ? -1 : 1);
    }

    /**
     * This function compare between two objects of Hotel, and return the bigger, hotel
     * according the alphabetic.
     * @param first - first hotel.
     * @param second - second hotel
     * @return - 0 if the hotel are equal, -1 if first hotel is bigger, 1 otherwise.
     */
    private int compareByName(Hotel first, Hotel second) {
        if (first.getPropertyName().equals(second.getPropertyName())){
            return 0;
        }
        return (first.getPropertyName().compareTo(second.getPropertyName()));
    }
}
