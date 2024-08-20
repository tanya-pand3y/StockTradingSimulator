package interface_adapter.QueryStock;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.query_stock.QueryStockOutputBoundary;
import use_case.query_stock.QueryStockOutputData;

import javax.swing.text.View;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class QueryStockPresenter implements QueryStockOutputBoundary {
    private final QueryStockViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;

    public QueryStockPresenter(ViewManagerModel viewManagerModel, QueryStockViewModel viewModel, DashboardViewModel dashboardViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    /**
     * Presents the output data
     * @param outputData the output data
     */
    @Override
    public void present(QueryStockOutputData outputData) {
        // Prepare the output data to be displayed
        String ticker = outputData.getTicker();
        Double currentPrice = outputData.getCurrentPrice();
        String startDate = outputData.getStartDate();
        String endDate = outputData.getEndDate();
        ArrayList<Double> priceHistory = outputData.getPriceHistory();
        ArrayList<ZonedDateTime> dates = outputData.getDates();

        // Pass the prepared data to the ViewModel
        viewModel.updateView(ticker, currentPrice, startDate, endDate, priceHistory, dates);

    }

    /**
     * Prepares the dashboard view
     */
    @Override
    public void prepareDashboardView() {
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
