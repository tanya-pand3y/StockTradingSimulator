package interface_adapter.sell;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.sell.SellOutputData;

import static org.mockito.Mockito.*;

class SellPresenterTest {

    private SellPresenter sellPresenter;
    private SellViewModel mockSellViewModel;
    private DashboardViewModel mockDashboardViewModel;
    private ViewManagerModel mockViewManagerModel;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockSellViewModel = mock(SellViewModel.class);
        mockDashboardViewModel = mock(DashboardViewModel.class);
        mockViewManagerModel = mock(ViewManagerModel.class);

        // Initialize the presenter with mocked dependencies
        sellPresenter = new SellPresenter(mockDashboardViewModel, mockSellViewModel, mockViewManagerModel);
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        SellOutputData mockResponse = mock(SellOutputData.class);

        // Act
        sellPresenter.prepareSuccessView(mockResponse);

        // Assert
        // Verify that the dashboardViewModel fired a property change
        verify(mockDashboardViewModel, times(1)).firePropertyChanged();

        // Verify that the active view was set to the dashboard view in the view manager model
        verify(mockViewManagerModel, times(1)).setActiveView(mockDashboardViewModel.getViewName());

        // Verify that the view manager model fired a property change
        verify(mockViewManagerModel, times(1)).firePropertyChanged();
    }
}