package data_access;

import java.io.BufferedReader;
import java.util.ArrayList;


import java.io.FileReader;
import java.io.IOException;

public class StockQuantityDataAccessObject implements StockQuantityDataAccessInterface{
    private final String username;
    private final String csvPath;
    private final ArrayList<String> tickers;
    private final ArrayList<Integer> quantities;
    private final ArrayList<Double> purchasePrices;

    public StockQuantityDataAccessObject(String username) {
        this.username = username;
        this.tickers = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.purchasePrices = new ArrayList<>();
        this.csvPath = "UserPortfolioData/" + this.username + ".csv";
        String line;
        String csvSeparator = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(this.csvPath))) {
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSeparator);

                // Assuming the columns are in order: Ticker, PurchasePrice, Quantity
                this.tickers.add(values[0]);
                this.purchasePrices.add(Double.parseDouble(values[1]));
                this.quantities.add(Integer.parseInt(values[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Printing the ArrayLists to verify
        System.out.println("Tickers: " + tickers);
        System.out.println("Purchase Prices: " + purchasePrices);
        System.out.println("Quantities: " + quantities);
    }
    @Override
    public ArrayList<String> getTicker() {
        return this.tickers;
    }

    @Override
    public ArrayList<Integer> getQuantities() {
        return this.quantities;
    }

    @Override
    public ArrayList<Double> getPurchasePrices() {
        return this.purchasePrices;
    }

    public static void main(String[] args) {
        StockQuantityDataAccessObject a = new StockQuantityDataAccessObject("Meer");
    }
}

