package data_access;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StockQuantityDataAccessInterfaceTest implements StockQuantityDataAccessInterface {

    @Override
    public ArrayList<String> getTicker() {
        return null;
    }

    @Override
    public Map<String, Map<String, Map<String, Object>>> getPriceQuantityMap() {
        return Map.of();
    }

    @Override
    public void fetchData(String username) {

    }

    @Override
    public void addEntry(String username, String ticker, String date, Double price, int quantity) {

    }

    @Override
    public void createUserInFirebase(String username) {

    }
}