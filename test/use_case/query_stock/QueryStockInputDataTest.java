package use_case.query_stock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryStockInputDataTest {
    @Test
    void testConstructorWithSingleParameter() {
        // Given
        String ticker = "AAPL";

        // When
        QueryStockInputData inputData = new QueryStockInputData(ticker);

        // Then
        assertEquals(ticker, inputData.getTicker());
        assertEquals("", inputData.getStart_date());
        assertEquals("", inputData.getEnd_date());
    }

    @Test
    void testConstructorWithThreeParameters() {
        // Given
        String ticker = "AAPL";
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";

        // When
        QueryStockInputData inputData = new QueryStockInputData(ticker, startDate, endDate);

        // Then
        assertEquals(ticker, inputData.getTicker());
        assertEquals(startDate, inputData.getStart_date());
        assertEquals(endDate, inputData.getEnd_date());
    }

    @Test
    void testGetTicker() {
        // Given
        String ticker = "AAPL";
        QueryStockInputData inputData = new QueryStockInputData(ticker);

        // When
        String result = inputData.getTicker();

        // Then
        assertEquals(ticker, result);
    }

    @Test
    void testGetStartDate() {
        // Given
        String startDate = "2024-01-01";
        QueryStockInputData inputData = new QueryStockInputData("AAPL", startDate, "2024-12-31");

        // When
        String result = inputData.getStart_date();

        // Then
        assertEquals(startDate, result);
    }

    @Test
    void testGetEndDate() {
        // Given
        String endDate = "2024-12-31";
        QueryStockInputData inputData = new QueryStockInputData("AAPL", "2024-01-01", endDate);

        // When
        String result = inputData.getEnd_date();

        // Then
        assertEquals(endDate, result);
    }

}