package use_case.query_stock;

import data_access.StockCurrentAPIDataAccessObject;
import data_access.StockHistoryAPIDataAccessObject;
import interface_adapter.QueryStock.QueryStockPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.query_stock.QueryStockOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class QueryStockInteractorTest {
    private TestQueryStockOutputBoundary outputBoundary;
    private QueryStockInteractor interactor;

    @BeforeEach
    void setUp() {
        outputBoundary = new TestQueryStockOutputBoundary();
        interactor = new QueryStockInteractor(outputBoundary);
    }

    @Test
    void testExecuteWithValidDates() {
        // Arrange
        String ticker = "AAPL";
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";



        // Act
        interactor.execute(new QueryStockInputData(ticker, startDate, endDate));

    }

    @Test
    void testExecuteWithoutDates() {
        // Arrange
        String ticker = "AAPL";

        // Act
        interactor.execute(new QueryStockInputData(ticker));

        // Assert
        QueryStockOutputData outputData = outputBoundary.getOutputData();
        assertNotNull(outputData);
        assertEquals(ticker, outputData.getTicker());
        assertNotNull(outputData.getCurrentPrice()); // Check if current price is retrieved
        assertTrue(outputData.getStartDate().isEmpty());
        assertTrue(outputData.getEndDate().isEmpty());
        assertNull(outputData.getPriceHistory()); // No price history should be retrieved
        assertNull(outputData.getDates()); // No dates should be retrieved
    }

    @Test
    void testBackButtonPressed() {
        // Act
        interactor.backButtonPressed();

        // Assert
        assertTrue(outputBoundary.isDashboardPrepared());
    }
}
