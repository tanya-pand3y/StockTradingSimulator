package interface_adapter.sell;

import entity.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class SellControllerTest {

    private SellController sellController;
    private SellInputBoundary mockSellInteractor;
    private Portfolio mockPortfolio;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        mockSellInteractor = mock(SellInputBoundary.class);
        mockPortfolio = mock(Portfolio.class);

        // Initialize the controller with the mocked interactor
        sellController = new SellController(mockSellInteractor);
    }

    @Test
    void testGetHeldStocks() {
        // Arrange
        ArrayList<String> expectedStocks = new ArrayList<>();
        expectedStocks.add("AAPL");
        expectedStocks.add("TSLA");

        // Mock the interactor to return the expected stocks
        when(mockSellInteractor.getHeldStocks(mockPortfolio)).thenReturn(expectedStocks);

        // Act
        ArrayList<String> heldStocks = sellController.getHeldStocks(mockPortfolio);

        // Assert
        assertEquals(expectedStocks, heldStocks);
        // Verify that the interactor's getHeldStocks method was called with the correct portfolio
        verify(mockSellInteractor, times(1)).getHeldStocks(mockPortfolio);
    }

    @Test
    void testExecuteCallsInteractorWithCorrectData() {
        // Arrange
        String ticker = "AAPL";
        int quantity = 10;

        // Act
        sellController.execute(ticker, quantity, mockPortfolio);

        // Assert
        // Verify that the interactor's execute method was called once with any instance of SellInputData
        verify(mockSellInteractor, times(1)).execute(any(SellInputData.class));

        // Verify that the SellInputData passed to the interactor has the correct values
        verify(mockSellInteractor).execute(argThat(sellInputData ->
                sellInputData.getTicker().equals(ticker) &&
                        sellInputData.getQuantity() == quantity &&
                        sellInputData.getPortfolio() == mockPortfolio
        ));
    }
}