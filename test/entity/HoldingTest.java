package entity;

import data_access.StockQuantityDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Map;

public class HoldingTest {

    private class MockStock extends Stock {
        private double mockPrice;
        private String mockTicker;

        public MockStock(String ticker, double price) {
            super(ticker);
            this.mockTicker = ticker;
            this.mockPrice = price;
        }

        @Override
        public double getCurrentPrice() {
            return mockPrice;
        }

        @Override
        public String getTicker() {
            return mockTicker;
        }
    }

    private class MockStockQuantityDataAccess implements StockQuantityDataAccessInterface {
        @Override
        public ArrayList<String> getTicker() {
            return new ArrayList<>();
        }

        @Override
        public Map<String, Map<String, Map<String, Object>>> getPriceQuantityMap() {
            return null;
        }

        @Override
        public void fetchData(String username) {
        }

        @Override
        public void addEntry(String username, String ticker, String date, Double price, int quantity) {
            // Mock implementation
        }

        @Override
        public void createUserInFirebase(String username) {
            // Mock implementation
        }
    }

    private Stock stock;
    private StockTransactionHistory stockTransactionHistory;
    private Holding holding;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        stock = new MockStock("AAPL", 150.0); // Mock stock with a price of 150.0
        stockTransactionHistory = new StockTransactionHistory();
        holding = new Holding(stock, stockTransactionHistory);
        transaction = new Transaction("2024-08-08", 150.0, 10); // TotalAmount = 1500.0
    }

    @Test
    void testInitialValues() {
        assertNotNull(holding);
        assertEquals(stock, holding.getStock());
        assertEquals(0, holding.getQuantity());
        assertEquals(0.0, holding.getCurrentValue());
        assertEquals(0.0, holding.getPnL());
    }

    @Test
    void testAddTransaction() {
        holding.addTransaction(transaction, "user1", new MockStockQuantityDataAccess());

        assertEquals(10, holding.getQuantity());
        assertEquals(1500.0, holding.getCurrentValue()); // 150.0 * 10
        assertEquals(0.0, holding.getPnL()); // TotalTransactionValue is 0 initially
    }

    @Test
    void testRecalculate() {
        holding.addTransaction(transaction, "user1", new MockStockQuantityDataAccess());

        // Change the stock price and recalculate
        stock = new MockStock("AAPL", 160.0); // Update stock price to 160.0
        holding = new Holding(stock, stockTransactionHistory);
        holding.recalculate();

        assertEquals(10, holding.getQuantity());
        assertEquals(1600.0, holding.getCurrentValue()); // 160.0 * 10
        assertEquals(100.0, holding.getPnL()); // New PnL after stock price update (1600.0 - 1500.0)
    }
}
