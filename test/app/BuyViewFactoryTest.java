package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyPresenter;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.dashboard.DashboardViewModel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.BuyView;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class BuyViewFactoryTest {

    @Mock
    private ViewManagerModel viewManagerModel;

    @Mock
    private DashboardViewModel dashboardViewModel;

    @Mock
    private BuyViewModel buyViewModel;

    @InjectMocks
    private BuyViewFactory buyViewFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    void testCreateBuyUseCaseReturnsBuyController() {
        BuyView buyView = BuyViewFactory.create(viewManagerModel, dashboardViewModel, buyViewModel);
        assertNotNull(buyView);  // Verify that the BuyController is created
    }

//    @Test
//    void testBuyPresenterIsCreatedCorrectly() {
//        BuyPresenter buyPresenter = mock(BuyPresenter.class);
//
//        // Mock the BuyInteractor using the mocked BuyPresenter
//        BuyController buyController = buyViewFactory.createBuyUseCase(viewManagerModel, dashboardViewModel, buyViewModel);
//
//        assertNotNull(buyController);  // Verify that the BuyController is created correctly
//
//        // Additional verification if necessary
//        verifyNoMoreInteractions(viewManagerModel, dashboardViewModel, buyViewModel);
//    }
}