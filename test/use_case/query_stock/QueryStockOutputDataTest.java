package use_case.query_stock;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QueryStockOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String ticker = "AAPL";
        double currentPrice = 150.0;
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        ArrayList<Double> priceHistory = new ArrayList<>();
        priceHistory.add(150.0);
        priceHistory.add(155.0);
        ArrayList<ZonedDateTime> dates = new ArrayList<>();
        dates.add(ZonedDateTime.parse("2024-01-01T00:00:00Z"));
        dates.add(ZonedDateTime.parse("2024-01-02T00:00:00Z"));

        // Act
        QueryStockOutputData outputData = new QueryStockOutputData(ticker, currentPrice, startDate, endDate, priceHistory, dates);

        // Assert
        assertEquals(ticker, outputData.getTicker());
        assertEquals(currentPrice, outputData.getCurrentPrice());
        assertEquals(priceHistory, outputData.getPriceHistory());
        assertEquals(startDate, outputData.getStartDate());
        assertEquals(endDate, outputData.getEndDate());
        assertEquals(dates, outputData.getDates());
    }

    @Test
    void testConstructorWithNullPriceHistory() {
        // Arrange
        String ticker = "AAPL";
        double currentPrice = 150.0;
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        ArrayList<Double> priceHistory = null;
        ArrayList<ZonedDateTime> dates = new ArrayList<>();
        dates.add(ZonedDateTime.parse("2024-01-01T00:00:00Z"));
        dates.add(ZonedDateTime.parse("2024-01-02T00:00:00Z"));

        // Act
        QueryStockOutputData outputData = new QueryStockOutputData(ticker, currentPrice, startDate, endDate, priceHistory, dates);

        // Assert
        assertEquals(ticker, outputData.getTicker());
        assertEquals(currentPrice, outputData.getCurrentPrice());
        assertNull(outputData.getPriceHistory());
        assertEquals(startDate, outputData.getStartDate());
        assertEquals(endDate, outputData.getEndDate());
        assertEquals(dates, outputData.getDates());
    }

    @Test
    void testHasPriceHistory() {
        // Arrange
        ArrayList<Double> priceHistory = new ArrayList<>();
        priceHistory.add(150.0);
        String ticker = "AAPL";
        double currentPrice = 150.0;
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        ArrayList<ZonedDateTime> dates = new ArrayList<>();
        dates.add(ZonedDateTime.parse("2024-01-01T00:00:00Z"));
        dates.add(ZonedDateTime.parse("2024-01-02T00:00:00Z"));

        // Act
        QueryStockOutputData outputDataWithHistory = new QueryStockOutputData(ticker, currentPrice, startDate, endDate, priceHistory, dates);

        // Assert
        assertTrue(outputDataWithHistory.hasPriceHistory());

        // Act with null priceHistory
        QueryStockOutputData outputDataWithoutHistory = new QueryStockOutputData(ticker, currentPrice, startDate, endDate, null, dates);

        // Assert
        assertFalse(outputDataWithoutHistory.hasPriceHistory());
    }

}