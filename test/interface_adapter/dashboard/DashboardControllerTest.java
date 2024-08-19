package interface_adapter.dashboard;

import entity.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardOutputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardControllerTest {

    private DashboardInputBoundary mockDashboardInteractor;
    private DashboardController dashboardController;

    @BeforeEach
    void setUp() {
        // Initialize the mock DashboardInputBoundary
        mockDashboardInteractor = mock(DashboardInputBoundary.class);

        // Initialize the DashboardController with the mock interactor
        dashboardController = new DashboardController(mockDashboardInteractor);
    }

    @Test
    void testGetUserPortfolioArrays() {
        // Arrange
        Portfolio mockPortfolio = mock(Portfolio.class);
        Object[][] expectedArray = new Object[][]{{"data"}};
        when(mockDashboardInteractor.getUserPortfolioArray(mockPortfolio)).thenReturn(expectedArray);

        // Act
        Object[][] result = dashboardController.getUserPortfolioArrays(mockPortfolio);

        // Assert
        verify(mockDashboardInteractor, times(1)).getUserPortfolioArray(mockPortfolio);
        assert(result == expectedArray);
    }

    @Test
    void testGetUserPortfolio() {
        // Arrange
        String username = "testUser";
        Portfolio mockPortfolio = mock(Portfolio.class);
        when(mockDashboardInteractor.getUserPortfolio(username)).thenReturn(mockPortfolio);

        // Act
        Portfolio result = dashboardController.getUserPortfolio(username);

        // Assert
        verify(mockDashboardInteractor, times(1)).getUserPortfolio(username);
        assert(result == mockPortfolio);
    }

    @Test
    void testSellButtonClicked() {
        // Arrange
        DashboardOutputData dashboardOutputData = mock(DashboardOutputData.class);

        // Act
        dashboardController.sellButtonClicked(dashboardOutputData);

        // Assert
        verify(mockDashboardInteractor, times(1)).prepareSellView(dashboardOutputData);
    }

    @Test
    void testLogoutPressed() {
        // Act
        dashboardController.logoutPressed();

        // Assert
        verify(mockDashboardInteractor, times(1)).logoutPressed();
    }

//    @Test
//    void testTransactionHistoryPressed() {
//        // Arrange
//        Portfolio mockPortfolio = mock(Portfolio.class);
//        DashboardOutputData dashboardOutputData = new DashboardOutputData(mockPortfolio.getUsername(), mockPortfolio);
//
//        // Act
//        dashboardController.transactionHistoryPressed(mockPortfolio);
//
//        // Assert
//        //verify(mockDashboardInteractor, times(1)).transactionHistoryPressed(dashboardOutputData);
//    }


    @Test
    void testBuyButtonClicked() {
        // Arrange
        DashboardOutputData dashboardOutputData = mock(DashboardOutputData.class);

        // Act
        dashboardController.buyButtonClicked(dashboardOutputData);

        // Assert
        verify(mockDashboardInteractor, times(1)).prepareBuyView(dashboardOutputData);
    }

    @Test
    void testStockQueryPressed() {
        // Act
        dashboardController.stockQueryPressed();

        // Assert
        verify(mockDashboardInteractor, times(1)).stockQueryPressed();
    }
}