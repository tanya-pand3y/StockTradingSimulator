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
     * Returns a list of quantities that matches the list of owned tickers
     * @return a list of quantities that matches the list of owned tickers
     */
    public Map<String, Map<String, Map<String, Object>>> getPriceQuantityMap();


    /**
     * Fetches the data given a username
     * @param username the username of the User to fetch data for
     */
    void fetchData(String username);

    void addEntry(String username, String ticker, String date, Double price, int quantity);


    /**
     * Creates a user csv file
     * @param username the username of that user
     */
    void createUserInFirebase(String username);
}
