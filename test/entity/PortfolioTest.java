package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import data_access.StockCurrentAPIDataAccessObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


class PortfolioTest {

    private Stock stock;

    private Portfolio portfolio;

    private StockCurrentAPIDataAccessObject CurrentStockData;

    @BeforeEach
    public void setUp() {
        stock = new Stock("AAPL");
        portfolio = new Portfolio(1000.0);
        CurrentStockData = new StockCurrentAPIDataAccessObject("AAPL");
    }

    @Test
    void setCash() {
        portfolio.setCash(2000.0);

        assertEquals(2000.0, portfolio.getCash());
    }

    @Test
    void deductCash() {
        portfolio.deductCash(500.0);

        assertEquals(500.0, portfolio.getCash());
    }

    @Test
    void getHoldings() {
        Holding holding = new Holding(stock, 150.0, 2);
        portfolio.addHolding(holding);

        Holding foundHolding = portfolio.getHolding(stock);

        assertEquals(holding, foundHolding);
    }

    @Test
    void addHolding() {
        //End of day stock price of Apple was 224.31, so the Account value should be 1000 + 224.31(10)
        Holding holding = new Holding(stock, 150.0, 10);
        portfolio.addHolding(holding);
        double currentValue = CurrentStockData.getClose() * 10;
        assertEquals(1000 + currentValue, portfolio.getAccountValue(), 0.01);
    }

    @org.junit.jupiter.api.Test
    void removeHolding() {
        Holding holding = new Holding(stock, 150.0, 2);
        portfolio.addHolding(holding);

        portfolio.removeHolding(stock.getTicker());

        assertEquals(0, portfolio.getHoldings().size());

        assertEquals(1000.0, portfolio.getAccountValue(), 0.1);
    }

    @org.junit.jupiter.api.Test
    void getHolding() {
        Holding holding = new Holding(stock, 150.0, 2);
        portfolio.addHolding(holding);

        Holding foundHolding = portfolio.getHolding(stock);

        assertEquals(holding, foundHolding);
    }

    @org.junit.jupiter.api.Test
    void getQuantityByStock() {
        Holding holding = new Holding(stock, 150.0, 2);
        portfolio.addHolding(holding);

        int quantity = portfolio.getQuantityByStock(stock);

        assertEquals(2, quantity);
    }

    @org.junit.jupiter.api.Test
    void updateAccountValue() {
        Holding holding = new Holding(stock, 150.0, 10);
        portfolio.addHolding(holding);
        portfolio.updateAccountValue(holding, true);
        assertTrue(portfolio.getAccountValue() > 1000.0);
    }

    @org.junit.jupiter.api.Test
    void getAccountValue() {
        Holding holding = new Holding(stock, 150.0, 2);
        portfolio.addHolding(holding);
        double currentValue = CurrentStockData.getClose() * 2;
        assertEquals(1000 + currentValue, portfolio.getAccountValue());
    }
}