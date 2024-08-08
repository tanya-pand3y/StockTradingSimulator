package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionTest {
  
    @Test
    public void testGetPrice() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", 15.0, 10);
        double expectedPrice = 15.0;
        Assertions.assertEquals(expectedPrice, transaction.getPrice(), "Expected transaction price does not match");
    }

    @Test
    public void testGetPriceWithZeroValue() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", 0.0, 0);
        double expectedPrice = 0.0;
        Assertions.assertEquals(expectedPrice, transaction.getPrice(), "Expected transaction price does not match");
    }

    @Test
    public void testGetPriceWithNegativeValue() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", -15.0, 10);
        double expectedPrice = -15.0;
        Assertions.assertEquals(expectedPrice, transaction.getPrice(), "Expected transaction price does not match");
    }
    @Test
    public void testGetQuantity() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", 15.0, 10);
        int expectedQuantity = 10;
        Assertions.assertEquals(expectedQuantity, transaction.getQuantity(), "Expected transaction quantity does not match");
    }

    @Test
    public void testGetQuantityWithZeroValue() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", 0.0, 0);
        int expectedQuantity = 0;
        Assertions.assertEquals(expectedQuantity, transaction.getQuantity(), "Expected transaction quantity does not match");
    }

    @Test
    public void testGetQuantityWithNegativeValue() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", -15.0, -10);
        int expectedQuantity = -10;
        Assertions.assertEquals(expectedQuantity, transaction.getQuantity(), "Expected transaction quantity does not match");
    }

    @Test
    public void testGetDate() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", 15.0, 10);
        String expectedDate = "2024-05-01T18:30:45Z";
        Assertions.assertEquals(expectedDate, transaction.getDate(), "Expected transaction date does not match");
    }

    @Test
    public void testGetDateWithDifferentFormat() {
        Transaction transaction = new Transaction("2024/05/01 6:30:45 PM", 15.0, 10);
        String expectedDate = "2024/05/01 6:30:45 PM";
        Assertions.assertEquals(expectedDate, transaction.getDate(), "Expected transaction date does not match");
    }

    @Test
    public void testGetTotalAmount() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", 15.0, 10);
        double expectedTotalAmount = 150.0;
        Assertions.assertEquals(expectedTotalAmount, transaction.getTotalAmount(), "Expected transaction total amount does not match");
    }

    @Test
    public void testGetTotalAmountWithZeroValues() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", 0.0, 0);
        double expectedTotalAmount = 0.0;
        Assertions.assertEquals(expectedTotalAmount, transaction.getTotalAmount(), "Expected transaction total amount does not match");
    }

    @Test
    public void testGetTotalAmountWithNegativeValues() {
        Transaction transaction = new Transaction("2024-05-01T18:30:45Z", -15.0, -10);
        double expectedTotalAmount = 150.0;
        Assertions.assertEquals(expectedTotalAmount, transaction.getTotalAmount(), "Expected transaction total amount does not match");
    }
}
