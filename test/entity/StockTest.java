package entity;

import data_access.StockCurrentAPIDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    private Stock stock;

    private StockCurrentAPIDataAccessObject CurrentStockData;

    @BeforeEach
    public void setUp() {
        CurrentStockData = new StockCurrentAPIDataAccessObject("AMZN");
        stock = new Stock("AMZN");
    }

    @Test
    void getName() {
        assertEquals("Amazon.com Inc", stock.getName());
    }

    @Test
    void getCurrentPrice() {
        assertEquals(CurrentStockData.getClose(), stock.getCurrentPrice());
    }

    @Test
    void getTicker() {
        assertEquals("AMZN", stock.getTicker());
    }
}