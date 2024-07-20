package interface_adapter.dashboard;

import entity.Holding;
import entity.Portfolio;
import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInteractor;

import java.util.ArrayList;

public class DashboardViewModel {
    private String username;
    private Portfolio portfolio;
    private DashboardController dashboardController;


    public DashboardViewModel(DashboardController dashboardController, String username) {
        this.username = username;
        this.dashboardController = dashboardController;
        this.portfolio = this.dashboardController.getUserPortfolio();
    }

    public String getUsername() {
        return username;
    }

    public double getPortfolioValue(){
        return this.portfolio.getAccountValue();
    }

    public Object[][] getStructuredDashboard(){
        ArrayList<Object[]> objList = new ArrayList<>();
        for(Holding holding : this.portfolio.getHoldings()) {
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


}
