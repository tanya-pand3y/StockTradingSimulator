package interface_adapter.UserPurchaseHistory;

import entity.Portfolio;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputBoundary;
import use_case.sell.SellInputBoundary;

public class UserPurchaseHistoryController {
    private final UserPurchaseHistoryInputBoundary userPurchaseHistoryInteractor;

    public UserPurchaseHistoryController(UserPurchaseHistoryInputBoundary userPurchaseHistoryInteractor) {
        this.userPurchaseHistoryInteractor = userPurchaseHistoryInteractor;
    }

    public void getUserHistoryArrays(Portfolio portfolio) {
        this.userPurchaseHistoryInteractor.getUserHistoryArrays(portfolio);
    }

}
