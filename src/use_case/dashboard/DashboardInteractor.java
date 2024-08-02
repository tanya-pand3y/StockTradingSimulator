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
        Portfolio portfolio = new Portfolio(username, 0, stockQuantityDao);
        portfolio.setData();
        return portfolio;

    }

    public Object[][] getUserPortfolioArray(Portfolio portfolio){
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
