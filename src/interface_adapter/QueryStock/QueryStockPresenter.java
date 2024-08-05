package interface_adapter.QueryStock;

import use_case.query_stock.QueryStockOutputBoundary;
import use_case.query_stock.QueryStockOutputData;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class QueryStockPresenter implements QueryStockOutputBoundary {
    private final QueryStockViewModel viewModel;

    public QueryStockPresenter(QueryStockViewModel viewModel) {
        this.viewModel = viewModel;
    }

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
}
