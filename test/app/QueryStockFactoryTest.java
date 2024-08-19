package app;

import interface_adapter.QueryStock.QueryStockViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.QueryStockView;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class QueryStockFactoryTest {

    private ViewManagerModel viewManagerModel;
    private QueryStockViewModel queryStockViewModel;
    private DashboardViewModel dashboardViewModel;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        viewManagerModel = mock(ViewManagerModel.class);
        queryStockViewModel = mock(QueryStockViewModel.class);
        dashboardViewModel = mock(DashboardViewModel.class);
    }

    @Test
    void testCreateQueryStockViewNotNull() {
        // Act
        QueryStockView queryStockView = null;
        try {
            queryStockView = QueryStockFactory.create(viewManagerModel, queryStockViewModel, dashboardViewModel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Assert
        assertNotNull(queryStockView, "The created QueryStockView should not be null");
    }
}
