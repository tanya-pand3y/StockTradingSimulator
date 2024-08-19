package interface_adapter.buy;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInputData;
import entity.Portfolio;

public class BuyControllerTest {

    private BuyInputBoundary mockInteractor;
    private BuyController buyController;
    private Portfolio mockPortfolio;

    @BeforeEach
    public void setUp() {
        mockInteractor = mock(BuyInputBoundary.class);
        buyController = new BuyController(mockInteractor);
        mockPortfolio = mock(Portfolio.class); // Initialize this with required parameters or mock if necessary
    }

    @Test
    public void testFetchSharePrice() {
        String ticker = "AAPL";
        double expectedPrice = 150.00;

        // Simulate the behavior of the interactor
        when(mockInteractor.getSharePrice(ticker)).thenReturn(expectedPrice);

        // Call the method
        buyController.fetchSharePrice(ticker);

        // Verify the interaction
        verify(mockInteractor).getSharePrice(ticker);
    }

    @Test
    public void testExecute() {
        String stockTicker = "AAPL";
        int quantity = 10;
        BuyInputData inputData = new BuyInputData(stockTicker, quantity, mockPortfolio); // Use the mock Portfolio

        // Call the method
        buyController.execute(stockTicker, quantity, mockPortfolio);

        // Verify the interaction
        //verify(mockInteractor).execute(inputData);
    }

    @Test
    public void testCancel() {
        // Call the method
        buyController.cancel();

        // Verify the interaction
        verify(mockInteractor).cancel();
    }
}
