package data_access;

import java.io.*;
import java.util.ArrayList;

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
        this.tickers = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.purchasePrices = new ArrayList<>();
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
    public void deleteStocks(String username, String ticker, Integer quantity) {
        String filePath = "UserPortfolioData/" + username + ".csv";
        ArrayList<String> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(ticker + ",")) {
                    String[] parts = line.split(",");
                    int currentQuantity = Integer.parseInt(parts[2]);
                    if (currentQuantity < quantity) {
                        System.out.println("Error: Not enough quantity to delete");
                        return;
                    }
                    int newQuantity = currentQuantity - quantity;
                    if (newQuantity > 0) {
                        fileContent.add(parts[0] + "," + parts[1] + "," + newQuantity);
                    }
                    found = true;
                } else {
                    fileContent.add(line);
                }
            }

            if (!found) {
                System.out.println("Error: Ticker not found");
                return;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String content : fileContent) {
                writer.write(content);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }

        int index = this.tickers.indexOf(ticker);
        this.quantities.set(index, this.quantities.get(index) - quantity);
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

