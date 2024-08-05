package use_case.UserPurchaseHistory;

import entity.Portfolio;
import entity.StockTransactionHistory;

import java.util.HashMap;

public interface UserPurchaseHistoryInputBoundary {

    HashMap<String, String[]> getUserHistoryArrays(UserPurchaseHistoryInputData inputData);
    void backButtonPressed();
}

