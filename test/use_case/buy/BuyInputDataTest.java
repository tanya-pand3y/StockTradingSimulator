package use_case.buy;

import data_access.StockQuantityDataAccessInterface;
import data_access.StockQuantityDataAccessObject;
import entity.Portfolio;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BuyInputDataTest {

    @Test
    void getTicker() {
        // Arrange
        String ticker = "AAPL";
        int quantity = 10;
        Portfolio portfolio = new Portfolio("test", new StockQuantityDataAccessInterface() {
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
        }); // Assume Portfolio has a default constructor

        // Act
        BuyInputData inputData = new BuyInputData(ticker, quantity, portfolio);

        // Assert
        assertEquals(ticker, inputData.getTicker());
    }

    @Test
    void getQuantity() {
        // Arrange
        String ticker = "AAPL";
        int quantity = 10;
        Portfolio portfolio = new Portfolio("test", new StockQuantityDataAccessObject()); // Assume Portfolio has a default constructor

        // Act
        BuyInputData inputData = new BuyInputData(ticker, quantity, portfolio);

        // Assert
        assertEquals(quantity, inputData.getQuantity());
    }

    @Test
    void getPortfolio() {
        // Arrange
        String ticker = "AAPL";
        int quantity = 10;
        Portfolio portfolio = new Portfolio("test", new StockQuantityDataAccessObject()); // Assume Portfolio has a default constructor

        // Act
        BuyInputData inputData = new BuyInputData(ticker, quantity, portfolio);

        // Assert
        assertEquals(portfolio, inputData.getPortfolio());
    }
}
