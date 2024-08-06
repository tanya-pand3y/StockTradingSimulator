package use_case.dashboard;

import use_case.login.LoginOutputData;

public interface DashboardOutputBoundary {
    void prepareSellView(DashboardOutputData dashboardOutputData);
    void prepareUserPurchaseHistoryView(DashboardOutputData dashboardOutputData);
    void prepareLogout();
    void prepareBuyView(DashboardOutputData dashboardOutputData);
    void prepareStockQuery();

}

