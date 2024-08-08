package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StockTransactionHistoryTest {
    private StockTransactionHistory stockTransactionHistory;
    private Transaction transaction1;
    private Transaction transaction2;

    @BeforeEach
    void setUp() {
        stockTransactionHistory = new StockTransactionHistory();

        // Initialize with sample transactions
        transaction1 = new Transaction("2024-08-01", 50.0, 10); // TotalAmount = 500.0
        transaction2 = new Transaction("2024-08-02", 60.0, 5);  // TotalAmount = 300.0
    }

    @Test
    void testInitialValues() {
        assertNotNull(stockTransactionHistory);
        assertEquals(" ", stockTransactionHistory.getTicker());
        assertEquals(0.0, stockTransactionHistory.getTotalTransactionValue());
        assertEquals(0, stockTransactionHistory.getTotalQuantity());
        assertTrue(stockTransactionHistory.getTransactions().isEmpty());
    }

    @Test
    void testAddTransaction() {
        stockTransactionHistory.addTransaction(transaction1);
        stockTransactionHistory.addTransaction(transaction2);

        ArrayList<Transaction> transactions = stockTransactionHistory.getTransactions();

        assertEquals(2, transactions.size());
        assertEquals(transaction1, transactions.get(0));
        assertEquals(transaction2, transactions.get(1));

        assertEquals(800.0, stockTransactionHistory.getTotalTransactionValue()); // 500.0 + 300.0
        assertEquals(15, stockTransactionHistory.getTotalQuantity()); // 10 + 5
    }

    @Test
    void testRecalculate() {
        stockTransactionHistory.addTransaction(transaction1);
        stockTransactionHistory.addTransaction(transaction2);

        stockTransactionHistory.recalculate();

        assertEquals(800.0, stockTransactionHistory.getTotalTransactionValue()); // 500.0 + 300.0
        assertEquals(15, stockTransactionHistory.getTotalQuantity()); // 10 + 5
    }

    @Test
    void testSetTicker() {
        stockTransactionHistory.setTicker("AAPL");
        assertEquals("AAPL", stockTransactionHistory.getTicker());
    }

    @Test
    void testEmptyHistory() {
        stockTransactionHistory.recalculate();
        assertEquals(0.0, stockTransactionHistory.getTotalTransactionValue());
        assertEquals(0, stockTransactionHistory.getTotalQuantity());
    }
}