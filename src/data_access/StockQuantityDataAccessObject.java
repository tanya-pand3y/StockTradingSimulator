package data_access;

import java.io.BufferedReader;
import java.util.ArrayList;


import java.io.FileReader;
import java.io.IOException;

public class StockQuantityDataAccessObject implements StockQuantityDataAccessInterface{
    private ArrayList<String> tickers;
    private ArrayList<Integer> quantities;
    private ArrayList<Double> purchasePrices;

    public StockQuantityDataAccessObject(){
        this.tickers = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.purchasePrices = new ArrayList<>();
    }

    public void fetchData(String username) {
        String csvPath = "UserPortfolioData/" + username + ".csv";
        String line;
        String csvSeparator = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
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

}

