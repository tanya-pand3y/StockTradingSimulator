package interface_adapter.QueryStock;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.query_stock.QueryStockOutputData;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueryStockPresenterTest {

    private QueryStockViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private DashboardViewModel dashboardViewModel;
    private QueryStockPresenter presenter;

    @BeforeEach
    public void setUp() {
        viewModel = mock(QueryStockViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        dashboardViewModel = mock(DashboardViewModel.class);
        presenter = new QueryStockPresenter(viewManagerModel, viewModel, dashboardViewModel);
    }

    @Test
    public void testPresent() {
        // Arrange
        String ticker = "AAPL";
        double currentPrice = 150.0;
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        ArrayList<Double> priceHistory = new ArrayList<>();
        ArrayList<ZonedDateTime> dates = new ArrayList<>();
        QueryStockOutputData outputData = new QueryStockOutputData(ticker, currentPrice, startDate, endDate, priceHistory, dates);

        // Act
        presenter.present(outputData);

        // Verifies that viewModel.updateView was called exactly once with the output data created above
        verify(viewModel, times(1)).updateView((ticker), (currentPrice), (startDate), (endDate), (priceHistory), (dates));
    }

    @Test
    public void testprepareDashboardView(){
        presenter.prepareDashboardView();
        verify(viewManagerModel, times(1)).setActiveView(dashboardViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }
}