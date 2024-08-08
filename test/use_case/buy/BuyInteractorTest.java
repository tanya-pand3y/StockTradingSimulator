package use_case.buy;

import data_access.StockQuantityDataAccessInterface;
import data_access.StockQuantityDataAccessObject;
import entity.Portfolio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuyInteractorTest {

    @Test
    void execute_success() {
        // Arrange
        BuyOutputBoundaryStub buyPresenterStub = new BuyOutputBoundaryStub();
        PortfolioStub portfolioStub = new PortfolioStub("test", new StockQuantityDataAccessObject());
        BuyInputData buyInputData = new BuyInputData("AAPL", 10, portfolioStub);
        BuyInteractor buyInteractor = new BuyInteractor(buyPresenterStub);

        // Act
        buyInteractor.execute(buyInputData);

        // Assert
        assertTrue(buyPresenterStub.successViewCalled);
        assertFalse(buyPresenterStub.failViewCalled);
    }

    @Test
    void execute_failure() {
        // Arrange
        BuyOutputBoundaryStub buyPresenterStub = new BuyOutputBoundaryStub();
        PortfolioStub portfolioStub = new PortfolioStub("test", new StockQuantityDataAccessObject());
        portfolioStub.shouldFail = true;
        BuyInputData buyInputData = new BuyInputData("AAPL", 10, portfolioStub);
        BuyInteractor buyInteractor = new BuyInteractor(buyPresenterStub);

        // Act
        buyInteractor.execute(buyInputData);

        // Assert
        assertFalse(buyPresenterStub.successViewCalled);
        assertTrue(buyPresenterStub.failViewCalled);
        assertEquals("Insufficient funds", buyPresenterStub.errorMessage);
    }

    @Test
    void cancel() {
        // Arrange
        BuyOutputBoundaryStub buyPresenterStub = new BuyOutputBoundaryStub();
        BuyInteractor buyInteractor = new BuyInteractor(buyPresenterStub);

        // Act
        buyInteractor.cancel();

        // Assert
        assertTrue(buyPresenterStub.cancelViewCalled);
    }

    @Test
    void getSharePrice() {
        // Arrange
        BuyOutputBoundaryStub buyPresenterStub = new BuyOutputBoundaryStub();
        BuyInteractor buyInteractor = new BuyInteractor(buyPresenterStub);

        // Act
        double price = buyInteractor.getSharePrice("AAPL");

        // Assert
        assertEquals(150.0, price);
        assertTrue(buyPresenterStub.setSharePriceCalled);
        assertEquals(150.0, buyPresenterStub.sharePrice);
    }

    // Stub classes to simulate dependencies
    class PortfolioStub extends Portfolio {
        boolean shouldFail = false;

        public PortfolioStub(String username, StockQuantityDataAccessInterface dao) {
            super(username, dao);
        }

        @Override
        public String buyStock(String ticker, int quantity) {
            return shouldFail ? "Insufficient funds" : "";
        }
    }

    class BuyOutputBoundaryStub implements BuyOutputBoundary {
        boolean successViewCalled = false;
        boolean failViewCalled = false;
        boolean cancelViewCalled = false;
        boolean setSharePriceCalled = false;
        double sharePrice;
        String errorMessage;

        @Override
        public void prepareSuccessView(BuyOutputData outputData) {
            successViewCalled = true;
        }

        @Override
        public void prepareFailView(String error) {
            failViewCalled = true;
            errorMessage = error;
        }

        @Override
        public void prepareCancelView() {
            cancelViewCalled = true;
        }

        @Override
        public void setSharePrice(double price) {
            setSharePriceCalled = true;
            sharePrice = price;
        }
    }
}
