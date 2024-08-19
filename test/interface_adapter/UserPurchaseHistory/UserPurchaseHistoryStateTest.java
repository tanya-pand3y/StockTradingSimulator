package interface_adapter.UserPurchaseHistory;

import data_access.StockQuantityDataAccessInterface;
import entity.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class UserPurchaseHistoryStateTest {

    private UserPurchaseHistoryState state;
    private StockQuantityDataAccessInterface mockDAO;

    @BeforeEach
    void setUp() {
        state = new UserPurchaseHistoryState();
        mockDAO = mock(StockQuantityDataAccessInterface.class);
    }

    @Test
    void testInitialPortfolioIsNull() {
        // Act
        Portfolio initialPortfolio = state.getPortfolio();

        // Assert
        assertNull(initialPortfolio, "The initial portfolio should be null.");
    }

    @Test
    void testSetAndGetPortfolio() {
        // Arrange
        Portfolio portfolio = new Portfolio("user123", mockDAO);

        // Act
        state.setPortfolio(portfolio);
        Portfolio retrievedPortfolio = state.getPortfolio();

        // Assert
        assertEquals(portfolio, retrievedPortfolio, "The retrieved portfolio should match the portfolio that was set.");
    }
}
