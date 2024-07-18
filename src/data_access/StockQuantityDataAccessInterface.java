package data_access;

import java.util.ArrayList;

public interface StockQuantityDataAccessInterface {
    ArrayList<String> getTicker();
    ArrayList<Integer> getQuantities();
}
