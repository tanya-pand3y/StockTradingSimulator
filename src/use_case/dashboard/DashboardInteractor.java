package use_case.dashboard;

import data_access.StockCurrentAPIDataAccessInterface;
import data_access.StockQuantityDataAccessInterface;
import entity.Holding;
import entity.Portfolio;
import entity.Stock;

import java.util.ArrayList;

public class DashboardInteractor implements DashboardInputBoundary{

    private final StockQuantityDataAccessInterface stockQuantityDao;

    public DashboardInteractor(StockQuantityDataAccessInterface stockQuantityDao){
        this.stockQuantityDao = stockQuantityDao;
    }

    @Override
    public Portfolio getUserPorfolio(DashboardInputData username) {
        // TODO: Implement stockQuantityDao and add it here.
        Portfolio portfolio = new Portfolio(0);
        ArrayList<String> tickers = this.stockQuantityDao.getTicker();
        ArrayList<Integer> quantities = this.stockQuantityDao.getQuantities();
        ArrayList<Double> costBasis = this.stockQuantityDao.getPurchasePrices();
        ArrayList<Stock> stocks = new ArrayList<>();
        for (String ticker : tickers) {
            Stock stock = new Stock(ticker);
            stocks.add(stock);
        }
        for (int i = 0; i<tickers.size(); i++){
            Holding holding = new Holding(stocks.get(i), costBasis.get(i), quantities.get(i));
            portfolio.addHolding(holding);
        }
        return portfolio;

    }
}
