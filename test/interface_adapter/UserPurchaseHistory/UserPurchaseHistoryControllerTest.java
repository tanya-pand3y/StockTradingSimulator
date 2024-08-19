package interface_adapter.UserPurchaseHistory;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import entity.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputBoundary;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputData;
import java.util.HashMap;

public class UserPurchaseHistoryControllerTest {

    private UserPurchaseHistoryInputBoundary mockInteractor;
    private UserPurchaseHistoryController controller;

    @BeforeEach
    public void setUp() {
        mockInteractor = mock(UserPurchaseHistoryInputBoundary.class);
        controller = new UserPurchaseHistoryController(mockInteractor);
    }

    @Test
    public void testGetUserHistoryArrays() {
        // Prepare test data
        Portfolio portfolio = mock(Portfolio.class);
        String ticker = "AAPL";
        HashMap<String, String[]> expectedHistory = new HashMap<>();
        when(mockInteractor.getUserHistoryArrays(any(UserPurchaseHistoryInputData.class)))
                .thenReturn(expectedHistory);

        // Call the method under test
        HashMap<String, String[]> result = controller.getUserHistoryArrays(portfolio, ticker);

        // Verify interactions and assertions
        verify(mockInteractor).getUserHistoryArrays(argThat(inputData ->
                ticker.equals(inputData.getTicker()) &&
                        portfolio.equals(inputData.getPortfolio())
        ));
        assertEquals(expectedHistory, result);
    }

    @Test
    public void testBackButtonPressed() {
        // Call the method under test
        controller.backButtonPressed();

        // Verify interaction
        verify(mockInteractor).backButtonPressed();
    }
}
