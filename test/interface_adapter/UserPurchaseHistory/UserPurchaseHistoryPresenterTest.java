package interface_adapter.UserPurchaseHistory;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;

public class UserPurchaseHistoryPresenterTest {

    private ViewManagerModel mockViewManagerModel;
    private UserPurchaseHistoryViewModel mockUserPurchaseHistoryViewModel;
    private DashboardViewModel mockDashboardViewModel;
    private UserPurchaseHistoryPresenter presenter;

    @BeforeEach
    public void setUp() {
        mockViewManagerModel = mock(ViewManagerModel.class);
        mockUserPurchaseHistoryViewModel = mock(UserPurchaseHistoryViewModel.class);
        mockDashboardViewModel = mock(DashboardViewModel.class);
        presenter = new UserPurchaseHistoryPresenter(mockViewManagerModel, mockUserPurchaseHistoryViewModel, mockDashboardViewModel);
    }

    @Test
    public void testPrepareDashboardView() {
        // Prepare test data
        String expectedViewName = "dashboard";
        when(mockDashboardViewModel.getViewName()).thenReturn(expectedViewName);

        // Call the method under test
        presenter.prepareDashboardView();

        // Verify interactions
        verify(mockViewManagerModel).setActiveView(expectedViewName);
        verify(mockViewManagerModel).firePropertyChanged();
    }
}
