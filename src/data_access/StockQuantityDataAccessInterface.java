package data_access;

import java.util.ArrayList;
import java.util.Map;

public interface StockQuantityDataAccessInterface {
    /**
     * Returns a list of owned tickers
     * @return a list of owned tickers
     */
    ArrayList<String> getTicker();

    /**
     * Returns a map of quantities that matches the list of owned tickers
     * @return a map of quantities that matches the list of owned tickers
     */
    Map<String, Map<String, Map<String, Object>>> getPriceQuantityMap();

    /**
     * Fetches the data given a username
     * @param username the username of the User to fetch data for
     */
    void fetchData(String username);

    /**
     * Adds a transaction entry to the data
     * @param username the username
     * @param ticker the ticker
     * @param date the date
     * @param price the price
     * @param quantity the quantity
     */
    void addEntry(String username, String ticker, String date, Double price, int quantity);

    /**
     * Creates a user csv file
     * @param username the username of that user
     */
    void createUserInFirebase(String username);
}
