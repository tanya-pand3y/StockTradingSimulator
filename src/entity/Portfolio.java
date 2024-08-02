package entity;

import com.sun.management.HotSpotDiagnosticMXBean;
import data_access.StockQuantityDataAccessInterface;

import java.util.ArrayList;


public class Portfolio {
    private String username;
    private double accountValue;
    private double cash;
    private double PnL;
    private ArrayList<Holding> holdings;
    private StockQuantityDataAccessInterface stockQuantityDao;

    /**
     * Initializes a portfolio
     * @param StartingCash the amount of starting cash
     */
    public Portfolio (String username, double StartingCash, StockQuantityDataAccessInterface dao){
        this.username = username;
        this.stockQuantityDao = dao;
        this.accountValue = StartingCash;
        this.cash = StartingCash;
        this.holdings = new ArrayList<>();
    }

    /**
     * Sets cash
     * @param cash the cash to set
     */
    public void setCash(double cash) {
        this.cash = cash;
    }

    /**
     * Removes cash
     * @param cash the cash to remove
     */
    public void deductCash(double cash) {
        this.cash = this.cash - cash;
    }

    /**
     * Returns the amount of cash in the portfolio
     * @return the amount of cash in the portfolio
     */
    public double getCash() {
        return cash;
    }

    public ArrayList<Holding> getHoldings() {
        return holdings;
    }

    /**
     * Adds a holding to the portfolio
     * @param holding The holding to add
     */
    public void addHolding (Holding holding) {
        this.holdings.add(holding);
        this.updateAccountValue(holding, true);
    }

    public void removeHolding (String ticker) {
        for (Holding holding : holdings) {
            if (holding.getStock().getTicker().equals(ticker)) {
                holdings.remove(holding);
                updateAccountValue();
                break;
            }

        }
    }

    private void updateAccountValue(){
        Double value = 0.0;
        for (Holding holding : holdings) {
            value += holding.getChangeInValueValue();
        }
        this.accountValue = value;
    }

    public void updateAccountValue(Holding holding, boolean add) {
        if (add){
            this.accountValue += holding.getStock().getCurrentPrice() * holding.getQuantity();
        }else{
            this.accountValue -= holding.getStock().getCurrentPrice() * holding.getQuantity();
        }

    }

    public Holding getHolding (Stock stock) {
        for (Holding holding : holdings) {
            if (holding.getStock().equals(stock)) {
                return holding;
            }
        }
        return null;
    }

    /**
     * Returns the quantity of a stock held in a portfolio
     * @param stock the stock to search for
     * @return the quantity of that stock in a holding in a portfolio
     */
    public int getQuantityByStock(Stock stock) {
        for (Holding holding : holdings) {
            if (holding.getStock().equals(stock)) {
                return holding.getQuantity();
            }
        }
        return 0; // Return 0 if no holding with the given stock is found
    }

    public double getAccountValue() {
        return accountValue;
    }

    public ArrayList<String> getHeldStocks(){
        ArrayList<String> tickers = new ArrayList<>();
        for (Holding holding : holdings) {
            tickers.add(holding.getStock().getTicker());
        }
        return tickers;
    }

    public void setData(){
        ArrayList<String> tickers = this.stockQuantityDao.getTicker();
        ArrayList<Integer> quantities = this.stockQuantityDao.getQuantities();
        ArrayList<Double> costBasis = this.stockQuantityDao.getPurchasePrices();
        ArrayList<Stock> stocks = new ArrayList<>();
        for (String ticker : tickers) {
            Stock stock = new Stock(ticker);
            stocks.add(stock);
        }
        for (int i = 0; i<tickers.size(); i++){
            Holding holding = new Holding(stocks.get(i), costBasis.get(i), quantities.get(i));
            this.addHolding(holding);
        }
    }

    public void deleteStocks(String ticker, Integer quantity){
        stockQuantityDao.deleteStocks(this.username, ticker, quantity);
        System.out.println("Deleting stock " + ticker + " with quantity " + quantity);
        for (Holding holding : holdings) {
            if (holding.getStock().getTicker().equals(ticker)) {
                holding.reduceQuantity(quantity);
                if (holding.getQuantity() == 0){
                    this.removeHolding(holding.getStock().getTicker());
                }
            }
        }


    }




}
