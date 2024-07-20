package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


class PortfolioTest {

    private Stock stock;

    private Portfolio portfolio;

    @BeforeEach
    public void setUp() {
        stock = new Stock("AAPL");
        portfolio = new Portfolio(1000.0);
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
        Holding holding = new Holding(stock, 150.0, 10);
        portfolio.addHolding(holding);
        assertEquals(2500.0, portfolio.getAccountValue(), 0.01);
    }

    @org.junit.jupiter.api.Test
    void removeHolding() {
        Holding holding = new Holding(stock, 150.0, 2);
        portfolio.addHolding(holding);

        portfolio.removeHolding(stock);

        assertEquals(0, portfolio.getHoldings().size());
        assertEquals(1000.0, portfolio.getAccountValue());
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
        portfolio.updateAccountValue(holding);
        assertTrue(portfolio.getAccountValue() > 1000.0);
    }

    @org.junit.jupiter.api.Test
    void getAccountValue() {
        Holding holding = new Holding(stock, 150.0, 2);
        portfolio.addHolding(holding);
        portfolio.updateAccountValue(holding);
        assertEquals(1300.0, portfolio.getAccountValue());
    }
}