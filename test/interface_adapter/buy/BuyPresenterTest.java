package interface_adapter.buy;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import interface_adapter.buy.BuyPresenter;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.buy.BuyOutputData;

public class BuyPresenterTest {

    private BuyViewModel mockBuyViewModel;
    private DashboardViewModel mockDashboardViewModel;
    private ViewManagerModel mockViewManagerModel;
    private BuyPresenter buyPresenter;

    @BeforeEach
    public void setUp() {
        mockBuyViewModel = mock(BuyViewModel.class);
        mockDashboardViewModel = mock(DashboardViewModel.class);
        mockViewManagerModel = mock(ViewManagerModel.class);
        buyPresenter = new BuyPresenter(mockBuyViewModel, mockDashboardViewModel, mockViewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        BuyOutputData mockResponse = mock(BuyOutputData.class);
        String dashboardViewName = mockDashboardViewModel.getViewName();
        when(mockDashboardViewModel.getViewName()).thenReturn(dashboardViewName);

        buyPresenter.prepareSuccessView(mockResponse);

        verify(mockDashboardViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView(dashboardViewName);
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareCancelView() {
        String dashboardViewName = mockDashboardViewModel.getViewName();
        when(mockDashboardViewModel.getViewName()).thenReturn(dashboardViewName);

        buyPresenter.prepareCancelView();

        verify(mockViewManagerModel).setActiveView(dashboardViewName);
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        String errorMessage = "An error occurred";

        buyPresenter.prepareFailView(errorMessage);

        verify(mockBuyViewModel).setErrorMessage(errorMessage);
    }

    @Test
    public void testSetSharePrice() {
        double sharePrice = 150.0;

        buyPresenter.setSharePrice(sharePrice);

        verify(mockBuyViewModel).setSharePrice(sharePrice);
    }
}
