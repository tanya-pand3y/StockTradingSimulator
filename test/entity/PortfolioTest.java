package entity;

import data_access.StockQuantityDataAccessInterface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {

    @Test
    void testRecalculate() {
        var dao = new MockDataAccess();
        Portfolio portfolio = new Portfolio("user", dao);
        System.out.println(portfolio.getCash());

        portfolio.recalculate();

        assertNotEquals(200.0, portfolio.getPortfolioValue());
        assertEquals(4800.0, portfolio.getCash());
        assertNotEquals(0.0, portfolio.getPnL());
    }

    @Test
    void testGetUsername() {
        var dao = new MockDataAccess();
        Portfolio portfolio = new Portfolio("user", dao);

        assertEquals("user", portfolio.getUsername());
    }

    @Test
    void testGetCash() {
        var dao = new MockDataAccess();
        Portfolio portfolio = new Portfolio("user", dao);

        assertEquals(4800.0, portfolio.getCash());
    }

    @Test
    void testHeldStocks() {
        var dao = new MockDataAccess();
        Portfolio portfolio = new Portfolio("user", dao);

        var tickers = portfolio.getHeldStocks();

        assertEquals(1, tickers.size());
        assertEquals("AAPL", tickers.get(0));
    }


    @Test
    void testGetHoldings() {
        var dao = new MockDataAccess();
        Portfolio portfolio = new Portfolio("user", dao);

        var holdingsList = portfolio.getHoldings();

        assertEquals(1, holdingsList.size());
        assertEquals("AAPL", holdingsList.get(0).getStock().getTicker());
        assertEquals(200.0, holdingsList.get(0).getStockTransactionHistory().getTransactions().get(0).getPrice());
        assertEquals(1, holdingsList.get(0).getStockTransactionHistory().getTransactions().get(0).getQuantity());
    }
}
class MockDataAccess implements StockQuantityDataAccessInterface {

    @Override
    public ArrayList<String> getTicker() {
        ArrayList<String> tickers = new ArrayList<>();
        tickers.add("AAPL");
        return tickers;
    }

    @Override
    public Map<String, Map<String, Map<String, Object>>> getPriceQuantityMap() {
        Map<String, Map<String, Map<String, Object>>> data = new HashMap<>();
        Map<String, Map<String, Object>> dates = new HashMap<>();
        Map<String, Object> details = new HashMap<>();
        details.put("Price", 200.0);
        details.put("Quantity", 1L);
        dates.put("2022-01-01 00:00:00", details);
        data.put("AAPL", dates);
        return data;
    }

    @Override
    public void fetchData(String username) {
    }

    @Override
    public void addEntry(String username, String ticker, String date, Double price, int quantity) {
        if (getPriceQuantityMap().containsKey(ticker)) {
            Map<String, Map<String, Object>> dateMap = getPriceQuantityMap().get(ticker);
            Map<String, Object> detailsMap = new HashMap<>();
            detailsMap.put("Price", price);
            detailsMap.put("Quantity", quantity);
            dateMap.put(date, detailsMap);
        } else {
            Map<String, Map<String, Object>> dateMap = new HashMap<>();
            Map<String, Object> detailsMap = new HashMap<>();
           detailsMap.put("Price", price);
           detailsMap.put("Quantity", quantity);
           dateMap.put(date, detailsMap);
           getPriceQuantityMap().put(ticker, dateMap);
       }
   }

    @Override
    public void createUserInFirebase(String username) {
    }
}