package data_access;

import java.util.ArrayList;

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
    ArrayList<Integer> getQuantities();

    /**
     * Returns a list of purchase prices that matches the list of owned tickers
     * @return a list of purchase prices that matches the list of owned tickers
     */
    ArrayList<Double> getPurchasePrices();

    /**
     * Fetches the data given a username
     * @param username the username of the User to fetch data for
     */
    void fetchData(String username);

    /**
     * Removes shares (or whole stocks) from user data
     * @param username the username
     * @param ticker the ticker of the stock
     * @param quantity the quantity of stock
     */
    void deleteStocks(String username, String ticker, Integer quantity);

    /**
     * Adds stocks (or shares) to user data
     * @param username the username
     * @param ticker the ticker of the stock
     * @param quantity the quantity of stock
     */

    /**
     * Creates a user csv file
     * @param username the username of that user
     */
    void createUserCSV(String username);
}
