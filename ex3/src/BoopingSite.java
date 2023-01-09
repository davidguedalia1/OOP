import java.util.Arrays;

import oop.ex3.searchengine.*;


/**
 * This class is BoopingSite, Booping.com is a new hotel booking site, that allows for personalized search
 * methodologies. Implement a portion of the website's actions. Specifically, this class provide the users
 * with the ability to get a list of hotels based on different parameters.
 * @author David Guedalia
 */
public class BoopingSite {

    /**
     *  The maximal latitude.
     */
    private static final double MAXIMUM_LATITUDE = 90;

    /**
     * The maximal longitude.
     */
    private static final double MAXIMUM_LONGITUDE = 180;

    /**
     * Array of the hotels.
     */
    private final Hotel[] hotelsArray;

    /**
     * A empty array type Hotel.
     */
    private final Hotel[] emptyHotelArray = new Hotel[0];

    /**
     * This is the constructor of the class, This constructor receives as parameter a string, which is
     * the name of the dataset.
     * @param name name of the dataset.
     */
    public BoopingSite(String name){
        hotelsArray = HotelDataset.getHotels(name);
    }

    /**
     * This function get city as a input and compare the all hotels have
     * that city, comparing by the rating and sort them.
     * @param city - The city of the hotel
     * @return - Array of hotel from the city.
     */
    public Hotel[] getHotelsInCityByRating(String city) {
        CompareByStarRating compareByRating = new CompareByStarRating();
        int numberHotel = numberHotelInCity(city);
        if (numberHotel == 0){
            return emptyHotelArray;
        }
        Hotel [] cityArray = new Hotel[numberHotel];
        buildArrayCity(cityArray, city);
        Arrays.sort(cityArray, compareByRating);
        return cityArray;
    }

    /**
     * This method returns an array of hotels, sorted according to their Euclidean distance from the
     * given geographic location, in ascending order.
     * Hotels that are at the same distance from the given location are organized
     * according to the number of points-of-interest for which they are close to, in a decreasing order.
     * @param latitude latitude position of hotel.
     * @param longitude longitude position of hotel.
     * @return An array of hotels, sorted according to their Euclidean distance.
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude){
        if (isNotValidLatitudeLongitude(latitude, longitude)){
            return emptyHotelArray;
        }
        return sortByProximity(hotelsArray, latitude, longitude);
    }

    /**
     * This method returns an array of hotels from the given city, sorted according to
     * their Euclidean distance from the given geographic location, in ascending order.
     * Hotels that are at the same distance from the given location are organized
     * according to the number of points-of-interest for which they are close to, in a decreasing order.
     * @param city  The city of the hotel.
     * @param latitude latitude position of hotel.
     * @param longitude longitude position of hotel.
     * @return An array of hotels, sorted according to their proximity, only hotels from city.
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude){
        if (isNotValidLatitudeLongitude(latitude, longitude) || city == null){
            return emptyHotelArray;
        }
        int numberHotel = numberHotelInCity(city);
        Hotel [] cityArray = new Hotel[numberHotel];
        buildArrayCity(cityArray, city);
        return sortByProximity(cityArray, latitude, longitude);
    }

    /**
     * This function checks if the latitude and the longitude is on the terms.
     * @param latitude latitude position of hotel.
     * @param longitude longitude position of hotel.
     * @return true if is not valid, false otherwise.
     */
    private boolean isNotValidLatitudeLongitude(double latitude, double longitude) {
        return !(latitude <= MAXIMUM_LATITUDE) || !(latitude >= -MAXIMUM_LATITUDE)
                || !(longitude <= MAXIMUM_LONGITUDE) || !(longitude >= -MAXIMUM_LONGITUDE);
    }

    /**
     * This function responsibility to sort the array gets as input and return
     * array sorted.
     * @param arrayToSort array to sort by proximity.
     * @param latitude latitude position of hotel.
     * @param longitude longitude position of hotel.
     * @return An array sorted by proximity.
     */
    private Hotel[] sortByProximity(Hotel[] arrayToSort, double latitude, double longitude){
        CompareByProximity compareByProximity = new CompareByProximity(latitude, longitude);
        Hotel [] proximityArray = new Hotel[arrayToSort.length];
        System.arraycopy(arrayToSort, 0, proximityArray, 0, arrayToSort.length);
        Arrays.sort(proximityArray, compareByProximity);
        return proximityArray;
    }

    /**
     * This function gets a hotel array and build a new array from
     * the given city.
     * @param cityArray - array of type Hotel
     * @param city  The city of the hotel.
     */
    private void buildArrayCity(Hotel[] cityArray, String city) {
        int i = 0;
        for (Hotel hotel : hotelsArray) {
                if (hotel.getCity().equals(city)) {
                    cityArray[i] = hotel;
                    i++;
                }
        }
    }

    /**
     * This function counting how many hotels in the current city.
     * @param city  The city of the hotel.
     * @return return the number of hotels in the given city.
     */
    private int numberHotelInCity(String city) {
        int counterHotel = 0;
        for (Hotel hotel : hotelsArray){
            if (hotel.getCity().equals(city)){
                counterHotel ++;
            }
        }
        return counterHotel;
    }
}


