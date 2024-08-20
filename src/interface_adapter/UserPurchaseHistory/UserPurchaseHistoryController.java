package interface_adapter.UserPurchaseHistory;

import entity.Portfolio;
import entity.StockTransactionHistory;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputBoundary;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputData;
import use_case.sell.SellInputBoundary;

import java.util.HashMap;

public class UserPurchaseHistoryController {
    private final UserPurchaseHistoryInputBoundary userPurchaseHistoryInteractor;

    /**
     * Creates a user purchase history controller
     * @param userPurchaseHistoryInteractor the user purchase history interactor
     */
    public UserPurchaseHistoryController(UserPurchaseHistoryInputBoundary userPurchaseHistoryInteractor) {
        this.userPurchaseHistoryInteractor = userPurchaseHistoryInteractor;
    }

    /**
     * Gets the user history arrays
     * @param portfolio the portfolio
     * @param ticker the ticker
     * @return the user history arrays
     */
    public HashMap<String, String[]> getUserHistoryArrays(Portfolio portfolio, String ticker) {
        UserPurchaseHistoryInputData inputData = new UserPurchaseHistoryInputData(ticker, portfolio);
        return this.userPurchaseHistoryInteractor.getUserHistoryArrays(inputData);

    }

    /**
     * Goes back
     */
    public void backButtonPressed(){
        this.userPurchaseHistoryInteractor.backButtonPressed();
    }

}
