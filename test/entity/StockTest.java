package entity;

import static org.junit.jupiter.api.Assertions.*;

import entity.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockTest {

    private Stock stock;

    @BeforeEach
    public void setUp() {
        stock = new Stock("AAPL");
    }

    @Test
    public void testStockCreation() {
        assertEquals("AAPL", stock.getTicker());
        assertEquals("Apple Inc", stock.getName());
    }

    @Test
    public void testGetCurrentPrice() {
        stock.updateValues();
        assertTrue(stock.getCurrentPrice() > 0);
    }


    @Test
    public void testUpdateValues() {
        double prevPrice = stock.getCurrentPrice();
        System.out.println("Prev price: " + prevPrice);
        stock.updateValues();
        double newPrice = stock.getCurrentPrice();
        System.out.println("New price: " + newPrice);
        assertEquals(prevPrice, newPrice);
    }
}
