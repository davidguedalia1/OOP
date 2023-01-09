import oop.ex3.searchengine.Hotel;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * This class is BoopingSite Test, This class checks the all methods of BoopingSite, and runs the test
 * of this class.
 * @author David Guedalia
 */
public class BoopingSiteTest {

    /**
     *  The maximal latitude.
     */
    private static final double MAXIMUM_LATITUDE = 90;

    /**
     * The maximal longitude.
     */
    private static final double MAXIMUM_LONGITUDE = 180;

    /**
     * This is the file contains the all hotels.
     */
    private static final String HOTELS_BIG_DATASET = "hotels_dataset.txt";

    /**
     *  This is the file contains hotels, short dataset.
     */
    private static final String HOTELS_SHORT_DATASET = "hotels_tst1.txt";

    /**
     *  This is the file contains the empty dataset.
     */
    private static final String HOTELS_EMPTY_DATASET = "hotels_tst2.txt";

    /**
     * Object of BoopingSite contains the big dataset.
     */
    private static BoopingSite boopingSiteBig;

    /**
     * Object of BoopingSite contains the short dataset.
     */
    private static BoopingSite boopingSiteShort;

    /**
     * Object of BoopingSite contains the empty dataset.
     */
    private static BoopingSite boopingSiteEmpty;

    /**
     * City Goa.
     */
    private static final String CITY_GOA = "goa";

    /**
     * City Croog.
     */
    private static final String CITY_CROOG= "croog";

    /**
     * City Srinagar.
     */
    private static final String CITY_SRINAGAR = "srinagar";

    /**
     * City Manali.
     */
    private static final String CITY_MANALI = "manali";

    /**
     * Array of city to check.
     */
    private static final String[] arrayCity = {"croog", "srinagar", "goa"};

    /**
     * This function running before each test and reset the objects for the next test.
     */
    @Before
    public void resetTest() {
        boopingSiteBig = new BoopingSite(HOTELS_BIG_DATASET);
        boopingSiteShort = new BoopingSite(HOTELS_SHORT_DATASET);
        boopingSiteEmpty = new BoopingSite(HOTELS_EMPTY_DATASET);
    }

    /**
     * Testing the function GetHotelsInCityByRating valid inputs, this test is
     * calling function to check the validity.
     */
    @Test
    public void testGetHotelsInCityByRating() {
        Hotel[] cityToCheck;
        cityToCheck = boopingSiteBig.getHotelsInCityByRating(CITY_SRINAGAR);
        assertTrue("Test GetHotelsInCityByRating failed, because the hotels is not in the city",
                checkIfCityHotel(CITY_SRINAGAR, cityToCheck));
        assertTrue("Test GetHotelsInCityByRating failed, because the rating is not sorted by rating",
                checkIfSortByRating(cityToCheck));
        assertTrue("Test GetHotelsInCityByRating failed, because the rating is not sorted by name",
                checkIfSortByName(cityToCheck));

        cityToCheck = boopingSiteBig.getHotelsInCityByRating(CITY_MANALI);
        assertTrue("Test GetHotelsInCityByRating failed, because the hotels is not in the city"
                , checkIfCityHotel(CITY_MANALI, cityToCheck));
        assertTrue("Test GetHotelsInCityByRating failed, because the rating is not sorted by rating",
                checkIfSortByRating(cityToCheck));
        assertTrue("Test GetHotelsInCityByRating failed, because the rating is not sorted by name",
                checkIfSortByName(cityToCheck));
    }

    /**
     * Testing the function GetHotelsInCityByRating testing the big data set
     * this test checks invalid inputs, calling function to check the validity.
     */
    @Test
    public void testGetHotelsInCityByRatingInvalidBig() {
        Hotel[] cityToCheck;
        cityToCheck = boopingSiteBig.getHotelsInCityByRating(null);
        assertEquals("Test GetHotelsInCityByRating failed, city is null the array need to be empty.",
                0, cityToCheck.length);
        cityToCheck = boopingSiteBig.getHotelsInCityByRating("CityNotExist");
        assertEquals("Test GetHotelsInCityByRating failed, city is doesn't exist the array need to be empty.",
                0, cityToCheck.length);
    }

    /**
     * Testing the function GetHotelsInCityByRating testing the short data set
     * this test checks invalid inputs, calling function to check the validity.
     * */
    @Test
    public void testGetHotelsInCityByRatingInvalidShort() {
        Hotel[] cityToCheck;
        cityToCheck = boopingSiteShort.getHotelsInCityByRating(null);
        assertEquals("Test GetHotelsInCityByRating failed, city is null the array need to be empty.",
                0, cityToCheck.length);
        cityToCheck = boopingSiteShort.getHotelsInCityByRating(CITY_SRINAGAR);
        assertEquals("Test GetHotelsInCityByRating failed, city is doesn't exist the array need to be empty.",
                0, cityToCheck.length);
    }

    /**
     * Testing the function GetHotelsInCityByRating testing the empty data set
     * this test checks invalid inputs, calling function to check the validity.
     * */
    @Test
    public void testGetHotelsInCityByRatingInvalidEmpty() {
        Hotel[] cityToCheck;
        cityToCheck = boopingSiteEmpty.getHotelsInCityByRating(null);
        assertEquals("Test GetHotelsInCityByRating failed, city is null the array need to be empty.",
                0, cityToCheck.length);
        cityToCheck = boopingSiteEmpty.getHotelsInCityByRating(CITY_MANALI);
        assertEquals("Test GetHotelsInCityByRating failed, city is doesn't exist the array (empty array)" +
                        " need to be empty.", 0, cityToCheck.length);
    }

    /**
     * Testing the function GetHotelsByProximity testing the big data set and short data set.
     * this test checks valid inputs, calling function to check the validity.
     */
    @Test
    public void testGetHotelsByProximity() {
        Hotel[] arrayToCheck;
        arrayToCheck = boopingSiteBig.getHotelsByProximity(50, 50);
        assertTrue("Test GetHotelsByProximity failed, because the hotels is not sorted via proximity",
                checkIfSortByProximity(arrayToCheck, 50, 50));
        assertTrue("Test GetHotelsByProximityBig failed, because the hotels is not sorted if the" +
                        "proximity is equal.", checkIfSortByPoi(arrayToCheck, 50, 50));

        arrayToCheck = boopingSiteShort.getHotelsByProximity(0, 0);
        assertTrue("Test GetHotelsByProximity failed, because the hotels is not sorted via proximity",
                checkIfSortByProximity(arrayToCheck, 0, 0));
        assertTrue("Test GetHotelsByProximityBig failed, because the hotels is not sorted if the" +
                        "proximity is equal.", checkIfSortByPoi(arrayToCheck, 0, 0));
    }

    /**
     * Testing the function GetHotelsByProximity testing the big data set
     * this test checks invalid inputs, calling function to check the validity.
     */
    @Test
    public void testGetHotelsByProximityInvalid() {
        Hotel[] arrayToCheck;
        arrayToCheck = boopingSiteBig.getHotelsByProximity(-91, 181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is not valid " +
                        "should return empty array", 0 , arrayToCheck.length);

        arrayToCheck = boopingSiteBig.getHotelsByProximity(10, 181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is not valid " +
                        "should return empty array", 0 , arrayToCheck.length);
        arrayToCheck = boopingSiteBig.getHotelsByProximity(10, -181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is not valid " +
                        "should return empty array", 0 , arrayToCheck.length);

        arrayToCheck = boopingSiteBig.getHotelsByProximity(91, 10);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is not valid " +
                        "should return empty array",
                0 , arrayToCheck.length);
        arrayToCheck = boopingSiteBig.getHotelsByProximity(-91, 10);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is not valid " +
                        "should return empty array", 0 , arrayToCheck.length);
    }

    /**
     /**
     * Testing the function GetHotelsByProximity testing the big data set and the short data set,
     * this test checks valid inputs, calling function to check the validity.
     */
    @Test
    public void testGetHotelsByProximityBorder() {
        Hotel[] arrayToCheck;
        arrayToCheck = boopingSiteBig.getHotelsByProximity(-MAXIMUM_LATITUDE, MAXIMUM_LONGITUDE);
        assertTrue("Test GetHotelsByProximity failed, because the hotels is not sorted via proximity",
                checkIfSortByProximity(arrayToCheck, -MAXIMUM_LATITUDE, MAXIMUM_LONGITUDE));
        assertTrue("Test GetHotelsByProximityBig failed, because the hotels is not sorted if the" +
                        "proximity is equal.", checkIfSortByPoi(arrayToCheck, -MAXIMUM_LATITUDE, MAXIMUM_LONGITUDE));

        arrayToCheck = boopingSiteShort.getHotelsByProximity(MAXIMUM_LATITUDE, -MAXIMUM_LONGITUDE);
        assertTrue("Test GetHotelsByProximity failed, because the hotels is not sorted via proximity",
                checkIfSortByProximity(arrayToCheck, -MAXIMUM_LATITUDE, MAXIMUM_LONGITUDE));
        assertTrue("Test GetHotelsByProximityBig failed, because the hotels is not sorted if the" +
                        "proximity is equal.", checkIfSortByPoi(arrayToCheck, -MAXIMUM_LATITUDE, MAXIMUM_LONGITUDE));
    }

    /**
     * Testing the function GetHotelsInCityByProximity testing the big data set and the short data set,
     * this test checks valid inputs, calling function to check the validity.
     */
    @Test
    public void testGetHotelsInCityByProximityBig() {
        Hotel[] cityToCheck;
        for (String city : arrayCity) {
            cityToCheck = boopingSiteBig.getHotelsInCityByProximity(city, 50, 50);
            assertTrue("Test GetHotelsByProximity failed, because the hotels is not sorted via proximity",
                    checkIfCityHotel(city, cityToCheck));
            assertTrue("Test GetHotelsByProximityBig failed, because the hotels is not sorted if the" +
                    "proximity is equal.", checkIfSortByProximity(cityToCheck, 50, 50));

            cityToCheck = boopingSiteBig.getHotelsInCityByProximity(city, 50, 50);
            assertTrue("Test GetHotelsByProximity failed, because the hotels is not sorted via proximity",
                    checkIfCityHotel(city, cityToCheck));
            assertTrue("Test GetHotelsByProximityBig failed, because the hotels is not sorted if the" +
                    "proximity is equal.", checkIfSortByProximity(cityToCheck, 50, 50));
        }
    }

    /**
     * Testing the function GetHotelsInCityByProximity testing the short data set,
     * this test checks valid inputs, calling function to check the validity.
     */
    @Test
    public void testGetHotelsInCityByProximityShort() {
        Hotel[] cityToCheck;
        cityToCheck = boopingSiteShort.getHotelsInCityByProximity(CITY_MANALI, 50, 50);
        assertTrue("Test GetHotelsByProximity failed, because the hotels is not sorted via proximity",
                checkIfCityHotel(CITY_MANALI, cityToCheck));
        assertTrue("Test GetHotelsByProximityBig failed, because the hotels is not sorted if the" +
                        "proximity is equal.", checkIfSortByProximity(cityToCheck, 50, 50));
    }

    /**
     * Testing the function GetHotelsInCityByProximity testing the big data set,
     * this test checks invalid inputs, calling function to check the validity.
     */
    @Test
    public void testGetHotelsInCityByProximityInvalidBig() {
        Hotel[] cityToCheck;
        cityToCheck = boopingSiteBig.getHotelsInCityByProximity("CityNotExist", 50, 50);
        assertEquals("Test GetHotelsInCityByRating failed, because the hotels is not in the city",
                0, cityToCheck.length);
        cityToCheck = boopingSiteBig.getHotelsInCityByProximity(CITY_MANALI, -91, 50);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is not valid " +
                        "should return empty array", 0, cityToCheck.length);
        cityToCheck = boopingSiteBig.getHotelsInCityByProximity(CITY_SRINAGAR, 91, 50);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is not valid " +
                        "should return empty array", 0, cityToCheck.length);
        cityToCheck = boopingSiteBig.getHotelsInCityByProximity(CITY_GOA, 50, 181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is not valid " +
                        "should return empty array", 0, cityToCheck.length);
        cityToCheck = boopingSiteBig.getHotelsInCityByProximity(CITY_MANALI, 50, -181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is not valid " +
                        "should return empty array", 0, cityToCheck.length);
        cityToCheck = boopingSiteBig.getHotelsInCityByProximity(null, 50, 181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is null " +
                        "should return empty array", 0, cityToCheck.length);
    }

    /**
     * Testing the function GetHotelsInCityByProximity testing the empty data set,
     * this test checks invalid inputs, calling function to check the validity.
     */
    @Test
    public void testGetHotelsInCityByProximityInvalidEmpty() {
        Hotel[] cityToCheck;
        cityToCheck = boopingSiteEmpty.getHotelsInCityByProximity(CITY_MANALI, 50, 50);
        assertEquals("Test GetHotelsInCityByRating failed, because the hotels is not in the city",
                0, cityToCheck.length);
        cityToCheck = boopingSiteEmpty.getHotelsInCityByProximity(CITY_GOA, -91, 50);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is null " +
                        "should return empty array", 0, cityToCheck.length);
        cityToCheck = boopingSiteEmpty.getHotelsInCityByProximity(CITY_CROOG, 91, 50);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is null " +
                        "should return empty array", 0, cityToCheck.length);
        cityToCheck = boopingSiteEmpty.getHotelsInCityByProximity(CITY_SRINAGAR, 50, 181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is null " +
                        "should return empty array", 0, cityToCheck.length);
        cityToCheck = boopingSiteEmpty.getHotelsInCityByProximity(CITY_MANALI, 50, -181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is null " +
                        "should return empty array", 0, cityToCheck.length);
        cityToCheck = boopingSiteEmpty.getHotelsInCityByProximity(CITY_GOA, 91, -181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is null " +
                        "should return empty array", 0, cityToCheck.length);
        cityToCheck = boopingSiteEmpty.getHotelsInCityByProximity(null, 50, 181);
        assertEquals("Test GetHotelsInCityByRating failed, because the input is null " +
                        "should return empty array", 0, cityToCheck.length);
    }


    /**
     * This function check if the array hotel is sorted by proximity or not.
     * @param arrayHotel array of Hotels.
     * @param latitude latitude position of hotel.
     * @param longitude longitude position of hotel.
     * @return true if sorted by POI, false otherwise.
     */
    private boolean checkIfSortByPoi(Hotel[] arrayHotel, double latitude, double longitude) {
        for (int i = 0; i < arrayHotel.length - 1; i++) {
            if (equalDistance(arrayHotel[i], arrayHotel[i + 1], latitude, longitude)) {
                if (arrayHotel[i].getNumPOI() < arrayHotel[i + 1].getNumPOI()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This function check if two hotels have the same distance from given location.
     * @param hotel1 array of Hotels.
     * @param hotel2 array of Hotels.
     * @param latitude latitude position of hotel.
     * @param longitude longitude position of hotel.
     * @return true if the distance is equal or not.
     */
    private boolean equalDistance(Hotel hotel1, Hotel hotel2, double latitude, double longitude) {
        double d1 = distance(hotel1, latitude, longitude);
        double d2 = distance(hotel2, latitude, longitude);
        return d1 == d2;
    }

    /**
     * This function check if the array hotel is sorted by distance from given location or not.
     * @param arrayHotel array of Hotels.
     * @param latitude latitude position of hotel.
     * @param longitude longitude position of hotel.
     * @return true if sorted by POI, false otherwise.
     */
    private boolean checkIfSortByProximity(Hotel[] arrayHotel, double latitude, double longitude) {
        for (int i = 0; i < arrayHotel.length - 1; i++) {
            if (distance(arrayHotel[i],latitude, longitude) > distance(arrayHotel[i],latitude, longitude)){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * This function calculate distance from given location.
     * @param hotel array of Hotels.
     * @param latitude latitude position of hotel.
     * @param longitude longitude position of hotel.
     * @return true if sorted by POI, false otherwise.
     */
    private double distance(Hotel hotel, double latitude, double longitude) {
        double x = Math.abs(hotel.getLatitude() - latitude);
        double y = Math.abs(hotel.getLongitude() - longitude);
        return Math.sqrt((x * x) + (y * y));
    }

    /**
     * This function gets a hotel array and build a new array from
     * the given city.
     * @param hotelArray array of Hotels.
     * @param city  The city of the hotel.
     * @return true if is equal, false otherwise.
     */
    private boolean checkIfCityHotel(String city, Hotel[] hotelArray){
        for (Hotel hotel : hotelArray){
            if (!hotel.getCity().equals(city)){
                return false;
            }
        }
        return true;
    }

    /**
     * This function check if the array hotel is sorted by rating.
     * @param hotelArray array of Hotels.
     * @return true if sorted by rating, false otherwise.
     */
    private boolean checkIfSortByRating(Hotel[] hotelArray) {
        for (int i = 0; i < hotelArray.length - 1; i ++) {
            if (hotelArray[i].getStarRating() < hotelArray[i + 1].getStarRating()){
                    return false;
                }
            }
        return true;
    }

    /**
     * This function check if the array hotel is sorted by name.
     * @param hotelArray array of Hotels.
     * @return true if sorted by name, false otherwise.
     */
    private boolean checkIfSortByName(Hotel[] hotelArray) {
        int isFirstBigger;
        for (int i = 0; i < hotelArray.length - 1; i ++) {
            isFirstBigger = hotelArray[i].getPropertyName().compareTo(hotelArray[i + 1].getPropertyName());
            if (hotelArray[i].getStarRating() == hotelArray[i + 1].getStarRating()){
                System.out.println(hotelArray[i].getCity());
                if (isFirstBigger > 0){
                    return false;
                }
            }
        }
        return true;
    }
}