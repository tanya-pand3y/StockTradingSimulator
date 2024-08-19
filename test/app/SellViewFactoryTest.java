package app;

import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.sell.SellViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.SellView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class SellViewFactoryTest {

    private ViewManagerModel viewManagerModel;
    private DashboardViewModel dashboardViewModel;
    private SellViewModel sellViewModel;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        viewManagerModel = mock(ViewManagerModel.class);
        dashboardViewModel = mock(DashboardViewModel.class);
        sellViewModel = mock(SellViewModel.class);
    }

    @Test
    void testCreateSellViewNotNull() {
        // Act
        SellView sellView = SellViewFactory.create(viewManagerModel, dashboardViewModel, sellViewModel);

        // Assert
        assertNotNull(sellView, "The created SellView should not be null");
    }
}
