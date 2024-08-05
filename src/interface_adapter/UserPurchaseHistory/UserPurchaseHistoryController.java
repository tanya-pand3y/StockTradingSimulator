package interface_adapter.UserPurchaseHistory;

import entity.Portfolio;
import entity.StockTransactionHistory;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputBoundary;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputData;
import use_case.sell.SellInputBoundary;

import java.util.HashMap;

public class UserPurchaseHistoryController {
    private final UserPurchaseHistoryInputBoundary userPurchaseHistoryInteractor;

    public UserPurchaseHistoryController(UserPurchaseHistoryInputBoundary userPurchaseHistoryInteractor) {
        this.userPurchaseHistoryInteractor = userPurchaseHistoryInteractor;
    }

    public HashMap<String, String[]> getUserHistoryArrays(Portfolio portfolio, String ticker) {
        UserPurchaseHistoryInputData inputData = new UserPurchaseHistoryInputData(ticker, portfolio);
        return this.userPurchaseHistoryInteractor.getUserHistoryArrays(inputData);

    }

    public void backButtonPressed(){
        this.userPurchaseHistoryInteractor.backButtonPressed();
    }

}
