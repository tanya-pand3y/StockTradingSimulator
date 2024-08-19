package use_case.sell;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.StockQuantityDataAccessInterface;
import entity.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class SellInteractorTest {

    private SellInteractor sellInteractor;
    private SellOutputBoundary mockSellPresenter;
    private StockQuantityDataAccessInterface mockStockQuantityDataAccessObject;
    private Portfolio mockPortfolio;

    @BeforeEach
    public void setUp() {
        mockSellPresenter = mock(SellOutputBoundary.class);
        mockStockQuantityDataAccessObject = mock(StockQuantityDataAccessInterface.class);
        sellInteractor = new SellInteractor(mockSellPresenter, mockStockQuantityDataAccessObject);
        mockPortfolio = mock(Portfolio.class);
    }

    @Test
    public void testGetHeldStocks() {
        ArrayList<String> heldStocks = new ArrayList<>();
        heldStocks.add("AAPL");
        heldStocks.add("GOOGL");

        when(mockPortfolio.getHeldStocks()).thenReturn(heldStocks);

        ArrayList<String> result = sellInteractor.getHeldStocks(mockPortfolio);

        assertEquals(heldStocks, result);
    }

    @Test
    public void testExecute() {
        String ticker = "AAPL";
        int quantity = 10;
        SellInputData sellInputData = new SellInputData(ticker, quantity, mockPortfolio);

        // No need to set up the StockQuantityDataAccessInterface mock here as it's not used in this method.

        // Mock the portfolio's deleteStocks method
        doNothing().when(mockPortfolio).deleteStocks(ticker, quantity);

        sellInteractor.execute(sellInputData);

        // Verify that the deleteStocks method was called with the correct parameters
        verify(mockPortfolio).deleteStocks(ticker, quantity);

        // Verify that prepareSuccessView was called on the sellPresenter
        verify(mockSellPresenter).prepareSuccessView(any(SellOutputData.class));
    }
}
