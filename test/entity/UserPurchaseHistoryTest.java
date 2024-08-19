package entity;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserPurchaseHistoryTest {

    @Test
    void testUserPurchaseHistoryConstructorAndGetters() {
        // Arrange
        String expectedTicker = "AAPL";
        double expectedPrice = 150.25;
        int expectedQuantity = 10;
        ZonedDateTime expectedDate = ZonedDateTime.now();

        // Act
        UserPurchaseHistory purchaseHistory = new UserPurchaseHistory(expectedTicker, expectedPrice, expectedQuantity, expectedDate);

        // Assert
        assertNotNull(purchaseHistory, "UserPurchaseHistory object should not be null");
        assertEquals(expectedTicker, purchaseHistory.getTicker(), "The ticker should match");
        assertEquals(expectedPrice, purchaseHistory.getPrice(), "The price should match");
        assertEquals(expectedQuantity, purchaseHistory.getQuantity(), "The quantity should match");
        assertEquals(expectedDate, purchaseHistory.getDate(), "The date should match");
    }

    @Test
    void testToString() {
        // Arrange
        String ticker = "GOOG";
        double price = 2500.75;
        int quantity = 5;
        ZonedDateTime date = ZonedDateTime.now();

        UserPurchaseHistory purchaseHistory = new UserPurchaseHistory(ticker, price, quantity, date);

        // Act
        String result = purchaseHistory.toString();

        // Assert
        String expectedString = "PurchaseHistory{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
        assertEquals(expectedString, result, "The toString method should return the expected string");
    }
}
