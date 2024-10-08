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
        return new Portfolio(username, stockQuantityDao);

    }

    public Object[][] getUserPortfolioArray(Portfolio portfolio){
        ArrayList<Object[]> objList = new ArrayList<>();

        for(Holding holding : portfolio.getPositiveQuantityHoldings()) {
            String name = holding.getStock().getName();
            String ticker = holding.getStock().getTicker();
            int quantity = holding.getQuantity();
            double currentPrice = holding.getStock().getCurrentPrice();

            Object[] temp = {name, ticker, quantity, currentPrice};
            objList.add(temp);
        }
        Object[][] obj = new Object[objList.size()][];
        obj = objList.toArray(obj);
        return obj;
    }

    @Override
    public void prepareSellView(DashboardOutputData dashboardOutputData){
        this.dashboardPresenter.prepareSellView(dashboardOutputData);
    }

    @Override
    public void logoutPressed(){
        this.dashboardPresenter.prepareLogout();
    }

    @Override
    public void transactionHistoryPressed(DashboardOutputData dashboardOutputData) {
        this.dashboardPresenter.prepareUserPurchaseHistoryView(dashboardOutputData);
    }

    @Override
    public void prepareBuyView(DashboardOutputData dashboardOutputData) {
        this.dashboardPresenter.prepareBuyView(dashboardOutputData);
    }

    @Override
    public void stockQueryPressed() {
        this.dashboardPresenter.prepareStockQuery();
    }
}
