import oop.ex3.searchengine.*;
import java.util.Comparator;

/**
 * This class comparing hotels by the proximity of them, according to their Euclidean distance
 * from the given geographic location, in ascending order.
 * Hotels that are at the same distance from the given location are organized according to the number of
 * points-of-interest for which they are close to, (in a decreasing order).
 * @author David Guedalia
 */
public class CompareByProximity implements Comparator<Hotel>{

    /**
     * The latitude of the position of hotel.
     */
    private final double latitude;

    /**
     * The longitude of the position of hotel.
     */
    private final double longitude;

    /**
     * This is the constructor of the comparator
     * @param latitude latitude position of hotel.
     * @param longitude longitude position of hotel.
     */
    public CompareByProximity(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    /**
     * Tis function comparing between to hotels by their names.
     * @param first - first hotel.
     * @param second - second hotel
     * @return - 0 if the hotel are equal, -1 if first hotel is bigger, 1 otherwise.
     */
    @Override
    public int compare(Hotel first, Hotel second) {
        if (distanceFrom(first) == distanceFrom(second)){
            return compareByPoi(first, second);
        }
        return (distanceFrom(first) > distanceFrom(second) ? 1 : -1);
    }

    /**
     * This function compare between two hotels by the points-of-interest.
     * @param first - first hotel.
     * @param second - second hotel
     * @return - 0 if the hotel are equal, -1 if first hotel is bigger, 1 otherwise.
     */
    private int compareByPoi(Hotel first, Hotel second) {
        if (first.getNumPOI() == second.getNumPOI()){
            return 0;
        }
        return (first.getNumPOI() > second.getNumPOI() ? -1 : 1);
    }

    /**
     * This function calculate the distance between the two hotels.
     * @param hotel Object of Hotel.
     * @return Euclidean distance from the given location (double).
     */
    private double distanceFrom(Hotel hotel) {
        double x = Math.abs(hotel.getLatitude() - latitude);
        double y = Math.abs(hotel.getLongitude() - longitude);
        return Math.sqrt((x * x) + (y * y));
    }
}