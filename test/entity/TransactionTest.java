package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        // Initialize with sample data
        transaction = new Transaction("2024-08-08", 50.0, 10);
    }

    @Test
    void testConstructor() {
        assertNotNull(transaction);
        assertEquals("2024-08-08", transaction.getDate());
        assertEquals(50.0, transaction.getPrice());
        assertEquals(10, transaction.getQuantity());
    }

    @Test
    void testGetPrice() {
        assertEquals(50.0, transaction.getPrice());
    }

    @Test
    void testGetQuantity() {
        assertEquals(10, transaction.getQuantity());
    }

    @Test
    void testGetDate() {
        assertEquals("2024-08-08", transaction.getDate());
    }

    @Test
    void testGetTotalAmount() {
        assertEquals(500.0, transaction.getTotalAmount()); // 50.0 * 10
    }

    @Test
    void testNegativeValues() {
        Transaction negativeTransaction = new Transaction("2024-08-08", -50.0, -10);
        assertEquals(-50.0, negativeTransaction.getPrice());
        assertEquals(-10, negativeTransaction.getQuantity());
        assertEquals(500.0, negativeTransaction.getTotalAmount()); // -50.0 * -10
    }
}