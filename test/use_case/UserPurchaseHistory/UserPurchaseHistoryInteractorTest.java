package use_case.UserPurchaseHistory;

import data_access.StockQuantityDataAccessInterface;
import entity.Portfolio;
import entity.StockTransactionHistory;
import entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInteractor;
import use_case.UserPurchaseHistory.UserPurchaseHistoryOutputBoundary;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class UserPurchaseHistoryInteractorTest {

    private UserPurchaseHistoryInteractor interactor;
    private Portfolio portfolio;
    private UserPurchaseHistoryInputData inputData;
    private TestOutputBoundary outputBoundary;

    @BeforeEach
    void setUp() {
        outputBoundary = new TestOutputBoundary();
        interactor = new UserPurchaseHistoryInteractor(outputBoundary);

        // Create a mock data access object with the required data
        StockQuantityDataAccessInterface mockDataAccess = new StockQuantityDataAccessInterface() {
            @Override
            public ArrayList<String> getTicker() {
                ArrayList<String> tickers = new ArrayList<>();
                tickers.add("AAPL");
                return tickers;
            }

            @Override
            public Map<String, Map<String, Map<String, Object>>> getPriceQuantityMap() {
                Map<String, Map<String, Map<String, Object>>> priceQuantityMap = new HashMap<>();
                Map<String, Map<String, Object>> transactions = new HashMap<>();

                Map<String, Object> transaction1 = new HashMap<>();
                transaction1.put("Price", 100.0);
                transaction1.put("Quantity", 10L);
                transactions.put("2023-01-01", transaction1);

                Map<String, Object> transaction2 = new HashMap<>();
                transaction2.put("Price", 150.0);
                transaction2.put("Quantity", 5L);
                transactions.put("2023-02-01", transaction2);

                priceQuantityMap.put("AAPL", transactions);
                return priceQuantityMap;
            }

            @Override
            public void fetchData(String username) {
                // No action needed for this test
            }

            @Override
            public void addEntry(String username, String ticker, String date, Double price, int quantity) {
                // No action needed for this test
            }

            @Override
            public void createUserInFirebase(String username) {
                // No action needed for this test
            }
        };

        // Initialize the portfolio with the mock data access object
        portfolio = new Portfolio("testUser", mockDataAccess);

        // Manually add transactions to the portfolio
        StockTransactionHistory transactionHistory = new StockTransactionHistory();
        transactionHistory.setTicker("AAPL");
        transactionHistory.addTransaction(new Transaction("2023-01-01", 100.0, 10));
        transactionHistory.addTransaction(new Transaction("2023-02-01", 150.0, 5));
        portfolio.getHoldings().add(new entity.Holding(new entity.Stock("AAPL"), transactionHistory));

        inputData = new UserPurchaseHistoryInputData("AAPL", portfolio);
    }

    @Test
    void testGetUserHistoryArrays() {
        // Execute the method
        HashMap<String, String[]> result = interactor.getUserHistoryArrays(inputData);

        // Print the actual arrays for debugging
        System.out.println("Dates: " + String.join(", ", result.get("dates")));
        System.out.println("Prices: " + String.join(", ", result.get("prices")));
        System.out.println("Quantities: " + String.join(", ", result.get("quantities")));

        // Verify the result
        assertArrayEquals(new String[]{"2023-01-01", "2023-02-01"}, result.get("dates"));
        assertArrayEquals(new String[]{"100.0", "150.0"}, result.get("prices"));
        assertArrayEquals(new String[]{"10", "5"}, result.get("quantities"));
    }

    @Test
    void testBackButtonPressed() {
        // Execute the method
        interactor.backButtonPressed();

        // Verify that the output boundary's method was called
        assertArrayEquals(new boolean[]{true}, new boolean[]{outputBoundary.isDashboardPrepared()});
    }

    // Implementation of the output boundary
    private class TestOutputBoundary implements UserPurchaseHistoryOutputBoundary {
        private boolean dashboardPrepared = false;

        @Override
        public void prepareDashboardView() {
            dashboardPrepared = true;
        }

        public boolean isDashboardPrepared() {
            return dashboardPrepared;
        }
    }
}
