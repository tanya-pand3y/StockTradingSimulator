package data_access;

import java.util.ArrayList;

public interface StockQuantityDataAccessInterface {
    ArrayList<String> getTicker();
    ArrayList<Integer> getQuantities();
    ArrayList<Double> getPurchasePrices();
    void fetchData(String username);
}
