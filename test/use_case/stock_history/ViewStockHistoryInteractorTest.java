package use_case.stock_history;


import data_access.StockHistoryAPIDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class ViewStockHistoryInteractorTest {

    private ViewStockHistoryInteractor interactor;
    private TestOutputBoundary outputBoundary;

    // Helper class to capture the output
    private static class TestOutputBoundary implements ViewStockHistoryOutputBoundary {
        private ViewStockHistoryOutputData receivedOutput;

        @Override
        public void present(ViewStockHistoryOutputData outputData) {
            this.receivedOutput = outputData;
        }

        public ViewStockHistoryOutputData getReceivedOutput() {
            return receivedOutput;
        }
    }

    @BeforeEach
    public void setUp() {
        outputBoundary = new TestOutputBoundary();
        interactor = new ViewStockHistoryInteractor(outputBoundary);
    }

    @Test
    public void testExecute() {
        // Create input data
        ViewStockHistoryInputData inputData = new ViewStockHistoryInputData("AAPL", "2024-07-15", "2024-07-21");

        // Execute the interactor
        interactor.execute(inputData);

        // Verify the output data is not null
        ViewStockHistoryOutputData outputData = outputBoundary.getReceivedOutput();
        assertNotNull(outputData, "Output data should not be null");

        // Verify the ticker symbol
        assertEquals("AAPL", outputData.getTicker(), "Ticker symbol should match the input");

        // Verify the stock prices are not empty
        List<Double> stockPrices = outputData.getStockPrices();
        assertNotNull(stockPrices, "Stock prices should not be null");
        assertFalse(stockPrices.isEmpty(), "Stock prices list should not be empty");

        // Optionally, check the size or other properties of the stock prices list
        // assertEquals(7, stockPrices.size(), "Stock prices list should contain 7 entries");

        // Verify all values in the stock prices list are valid numbers
        for (Double price : stockPrices) {
            assertNotNull(price, "Stock price should not be null");
            assertTrue(price > 0, "Stock price should be greater than zero");
        }
    }
}