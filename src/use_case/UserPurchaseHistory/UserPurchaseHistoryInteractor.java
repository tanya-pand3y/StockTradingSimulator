package use_case.UserPurchaseHistory;

import data_access.UserPurchaseHistoryDataAccessInterface;
import entity.Portfolio;
import entity.Transaction;
import entity.UserPurchaseHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserPurchaseHistoryInteractor implements UserPurchaseHistoryInputBoundary {

    private final UserPurchaseHistoryOutputBoundary outputBoundary;

    public UserPurchaseHistoryInteractor(UserPurchaseHistoryOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


    @Override
    public HashMap<String, String[]> getUserHistoryArrays(UserPurchaseHistoryInputData inputData) {
        ArrayList<Transaction> transactions = inputData.getPortfolio().getUserPurchaseHistory(inputData.getTicker());
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> quantities = new ArrayList<>();
        for (Transaction transaction : transactions) {
            dates.add(transaction.getDate());
            prices.add(String.valueOf(transaction.getPrice()));
            quantities.add(String.valueOf(transaction.getQuantity()));
        }
        String[] datesArray = dates.toArray(new String[0]);
        String[] pricesArray = prices.toArray(new String[0]);
        String[] quantitiesArray = quantities.toArray(new String[0]);
        HashMap<String, String[]> result = new HashMap<>();
        result.put("dates", datesArray);
        result.put("prices", pricesArray);
        result.put("quantities", quantitiesArray);
        return result;
    }

    public void backButtonPressed(){
        this.outputBoundary.prepareDashboardView();
    }



}

