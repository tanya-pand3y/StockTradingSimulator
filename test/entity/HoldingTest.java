package entity;

import data_access.StockCurrentAPIDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HoldingTest {

    private Stock stock;
    private Holding holding;
    private StockCurrentAPIDataAccessObject CurrentStockData;


    @BeforeEach
    public void setUp() {
        stock = new Stock("TSLA");
        holding = new Holding(stock, 80.0, 10);
        CurrentStockData = new StockCurrentAPIDataAccessObject("TSLA");
    }
    @Test
    void getStock() {
        assertEquals(stock, holding.getStock());
    }

    @Test
    void getCostBasis() {
        assertEquals(80.0, holding.getCostBasis());
    }

    @Test
    void getQuantity() {
        assertEquals(10, holding.getQuantity());
    }

    @Test
    void getChangeInValueValue() {
        double currentvalue = CurrentStockData.getClose();
        double changeinvalue = (currentvalue - 80) * 10;
        assertEquals(changeinvalue, holding.getChangeInValueValue());
    }

    @Test
    void setQuantity() {
        holding.setQuantity(10);
        assertEquals(10, holding.getQuantity());
    }

    @Test
    void reduceQuantity() {
        holding.reduceQuantity(5);
        assertEquals(5, holding.getQuantity());
    }
}
