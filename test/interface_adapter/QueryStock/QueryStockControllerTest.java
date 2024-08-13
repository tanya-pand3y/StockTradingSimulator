package interface_adapter.QueryStock;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.query_stock.QueryStockInputBoundary;
import use_case.query_stock.QueryStockInputData;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class QueryStockControllerTest {

    private QueryStockController controller;
    private QueryStockInputBoundary interactor;

    @BeforeEach
    public void setUp() {
        interactor = mock(QueryStockInputBoundary.class);
        controller = new QueryStockController(interactor);
    }

    @Test
    public void testExecute() {
        QueryStockInputBoundary interactor = mock(QueryStockInputBoundary.class);
        QueryStockController controller = new QueryStockController(interactor);

        controller.execute("AAPL", "2024-01-01", "2024-12-31");

        verify(interactor, times(1)).execute(any(QueryStockInputData.class));
        // Further checks can be added to verify the correctness of the passed input
    }

    @Test
    public void testBackButtonPressedCallsInteractor() {
        // Act
        controller.backButtonPressed();

        // Assert
        verify(interactor).backButtonPressed();
    }
}