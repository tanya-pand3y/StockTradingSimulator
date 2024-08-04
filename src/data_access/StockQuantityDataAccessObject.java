package data_access;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class StockQuantityDataAccessObject implements StockQuantityDataAccessInterface {
    private Firestore db = null;
    private ArrayList<String> tickers = new ArrayList<>();
    private Map<String, Map<String, Map<String, Object>>> priceQuantityMap = new HashMap<>();

    public StockQuantityDataAccessObject(){
        // Use the application default credentials
        try{
            FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                    .setProjectId("stocksimulator-9cba4")
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("src/stocksimulator-9cba4-firebase-adminsdk-bnr7o-ae21adfb43.json")))
                    .build();
            db = firestoreOptions.getService();
        }catch(IOException e){
            System.out.println("Firebase File not found");
        }


    }

    public void fetchData(String username) {
        Map<String, Object> userData = new HashMap<>();
        ApiFuture<DocumentSnapshot> future = db.collection("UserPortfolioData").document(username).get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                userData = document.getData();
                System.out.println("Document data: " + userData);
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error fetching document: " + e.getMessage());
        }
        this.tickers = this.extractTickers(userData);
        this.priceQuantityMap = convertData(userData);
    }

    public void addEntry(String username, String ticker, String date, Double price, int quantity) {
        DocumentReference docRef = db.collection("UserPortfolioData").document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();
            Map<String, Object> data;
            if (document.exists()) {
                data = document.getData();
            } else {
                data = new HashMap<>();
            }

            if (data != null) {
                Map<String, Map<String, Map<String, Object>>> userStocks = convertData(data);
                userStocks.computeIfAbsent(ticker, k -> new HashMap<>());
                Map<String, Map<String, Object>> tickerData = userStocks.get(ticker);
                tickerData.computeIfAbsent(date, k -> new HashMap<>());
                Map<String, Object> dateData = tickerData.get(date);
                dateData.put("Price", price);
                dateData.put("Quantity", quantity);

                docRef.set(userStocks);
                System.out.println("Stock entry added successfully.");
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error adding document: " + e.getMessage());
        }
    }

    public void deleteHolding(String username, String ticker) {
        DocumentReference docRef = db.collection("UserPortfolioData").document(username);
        ApiFuture<WriteResult> writeResult = docRef.update(ticker, FieldValue.delete());
        try {
            // Wait for the operation to complete
            writeResult.get();
            System.out.println("Successfully deleted holding for ticker: " + ticker);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error deleting holding for ticker: " + ticker);
        }
    }

    public void createUserInFirebase(String username) {
        DocumentReference docRef = db.collection("UserPortfolioData").document(username);
        Map<String, Object> userData = new HashMap<>();

        // Sample entry
        Map<String, Object> dateData = new HashMap<>();
        dateData.put("Price", 240.7);
        dateData.put("Quantity", 10);

        Map<String, Map<String, Object>> tickerData = new HashMap<>();
        tickerData.put("2024-08-01", dateData);

        userData.put("MSFT", tickerData);
        ApiFuture<WriteResult> result = docRef.set(userData);
        try {
            result.get();
            System.out.println("User created successfully: " + username);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
        this.deleteHolding(username, "MSFT");
    }

    private Map<String, Map<String, Map<String, Object>>> convertData(Map<String, Object> data) {
        Map<String, Map<String, Map<String, Object>>> userStocks = new HashMap<>();
        data.forEach((ticker, dates) -> {
            Map<String, Map<String, Object>> dateMap = new HashMap<>();
            ((Map<String, Object>) dates).forEach((date, prices) -> {
                Map<String, Object> priceMap = new HashMap<>();
                priceMap.put("Price", ((Map<String, Object>) prices).get("Price"));
                priceMap.put("Quantity", ((Map<String, Object>) prices).get("Quantity"));
                dateMap.put(date, priceMap);
            });
            userStocks.put(ticker, dateMap);
        });
        return userStocks;
    }

    private ArrayList<String> extractTickers(Map<String, Object> data) {
        ArrayList<String> tickers = new ArrayList<>();
        for (String ticker : data.keySet()) {
            tickers.add(ticker);
        }
        return tickers;
    }

    public ArrayList<String> getTicker() {
        return tickers;
    }

    public Map<String, Map<String, Map<String, Object>>> getPriceQuantityMap() {
        return priceQuantityMap;
    }

}
