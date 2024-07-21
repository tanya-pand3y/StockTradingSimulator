package use_case.dashboard;

import data_access.StockQuantityDataAccessInterface;
import entity.Holding;
import entity.Portfolio;
import entity.Stock;

import java.util.ArrayList;

public class DashboardInteractor implements DashboardInputBoundary{

    private final StockQuantityDataAccessInterface stockQuantityDao;
    private final DashboardOutputBoundary dashboardPresenter;

    public DashboardInteractor(StockQuantityDataAccessInterface stockQuantityDao, DashboardOutputBoundary dashboardOutputBoundary){
        this.stockQuantityDao = stockQuantityDao;
        this.dashboardPresenter = dashboardOutputBoundary;
    }

    public Portfolio getUserPortfolio(String username) {
        stockQuantityDao.fetchData(username);
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

    public Object[][] getUserPortfolioArray(String username){
        Portfolio portfolio = this.getUserPortfolio(username);
        ArrayList<Object[]> objList = new ArrayList<>();
        for(Holding holding : portfolio.getHoldings()) {
            String name = holding.getStock().getName();
            String ticker = holding.getStock().getTicker();
            int quantity = holding.getQuantity();
            double costbasis = holding.getCostBasis();
            double currentPrice = holding.getStock().getCurrentPrice();
            double gain = holding.getChangeInValueValue();
            double gainPercent = currentPrice/costbasis;

            Object[] temp = {name, ticker, quantity, costbasis, currentPrice, gain, gainPercent};
            objList.add(temp);
        }
        Object[][] obj = new Object[objList.size()][];
        obj = objList.toArray(obj);
        return obj;
    }

    public void prepareSellView(DashboardOutputData dashboardOutputData){
        this.dashboardPresenter.prepareSellView(dashboardOutputData);
    }
}
