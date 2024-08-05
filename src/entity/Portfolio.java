package entity;

import data_access.StockQuantityDataAccessInterface;
import data_access.StockQuantityDataAccessObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Portfolio {
    private String username;
    private double portfolioValue;
    private double cash;
    private double PnL;
    private ArrayList<Holding> holdings;
    private StockQuantityDataAccessInterface stockQuantityDao;

    private static double convertToDouble(Object number) {
        if (number instanceof Double) {
            return (Double) number;
        } else if (number instanceof Long) {
            return ((Long) number).doubleValue();
        } else {
            throw new IllegalArgumentException("The input number is neither a Double nor a Long");
        }
    }

    /**
     * Initializes a portfolio
     * @param StartingCash the amount of starting cash
     */
    public Portfolio (String username, double StartingCash, StockQuantityDataAccessInterface dao){
        this.username = username;
        this.stockQuantityDao = dao;

        dao.fetchData(this.username);
        this.holdings = new ArrayList<>();
        this.stockQuantityDao.getPriceQuantityMap().forEach((ticker, dates) -> {
            StockTransactionHistory stockTransactionHistory = new StockTransactionHistory();
            stockTransactionHistory.setTicker(ticker);
            dates.forEach((date, details) -> {
                Transaction transaction = new Transaction(date, convertToDouble(details.get("Price")), (int)(long) details.get("Quantity"));
                stockTransactionHistory.addTransaction(transaction);
            });
            Stock stock = new Stock(ticker);
            Holding holding = new Holding(stock,stockTransactionHistory);
            this.holdings.add(holding);
        });
        this.portfolioValue = 0.0;
        this.PnL = 0.0;
        for (Holding holding : this.holdings) {
            this.portfolioValue += holding.getCurrentValue();
            this.PnL += holding.getPnL();
        }

        this.cash = StartingCash;

    }

    /**
     * Recalculates values in the portfolio
     */
    public void recalculate(){
        this.portfolioValue = 0.0;
        this.PnL = 0.0;
        for (Holding holding : this.holdings) {
            holding.recalculate();
            this.portfolioValue += holding.getCurrentValue();
            this.PnL += holding.getPnL();
        }
    }

    /**
     * Returns the username of the portfolio owner
     * @return the username of the portfolio owner
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets cash
     * @param cash the cash to set
     */
    public void setCash(double cash) {
        this.cash = cash;
    }

    /**
     * Returns the amount of cash in the portfolio
     * @return the amount of cash in the portfolio
     */
    public double getCash() {
        return cash;
    }

    /**
     * Returns the dollar value of profit/loss in the portfolio
     * @return the dollar value of profit/loss in the portfolio
     */
    public double getPnL() {
        return PnL;
    }

    /**
     * Returns a list of holdings in the portfolio
     * @return a list of holdings in the portfolio
     */
    public ArrayList<Holding> getHoldings() {
        return holdings;
    }

    /**
     * Removes a holding given a ticker
     * @param ticker the ticker to remove
     */
    public void removeHolding (String ticker) {
        for (Holding holding : holdings) {
            if (holding.getStock().getTicker().equals(ticker)) {
                holdings.remove(holding);
                recalculate();
                break;
            }

        }
    }

    /**
     * Returns a holding object corresponding to the string ticker input
     * @param ticker the ticker of the stock
     * @return a holding object of that stock, if found
     */
    public Holding getHolding (String ticker) {
        for (Holding holding : holdings) {
            if (holding.getStock().getTicker().equals(ticker)) {
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

    /**
     * Returns the dollar value of the portfolio
     * @return the dollar value of the portfolio
     */
    public double getPortfolioValue() {
        return portfolioValue;
    }

    /**
     * Returns a list of tickers corresponding to held stocks
     * @return a list of tickers corresponding to held stocks
     */
    public ArrayList<String> getHeldStocks(){
        ArrayList<String> tickers = new ArrayList<>();
        for (Holding holding : this.getPositiveQuantityHoldings()) {
            tickers.add(holding.getStock().getTicker());
        }
        return tickers;
    }

    /**
     * Buys a given quantity of a stock given the ticker
     * @param ticker the ticker of stock
     * @param quantity the quantity of stock
     */
    public void buyStock(String ticker, int quantity){
        Stock stock = new Stock(ticker);
        if (stock.getCurrentPrice()*quantity > this.cash){
            System.out.println("Not enough money");
        }
        else{
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = currentDate.format(formatter);
            Transaction transaction = new Transaction(formattedDate, stock.getCurrentPrice(), quantity);
            if (this.getHolding(ticker) != null){
                Holding holding = this.getHolding(ticker);
                holding.addTransaction(transaction, username, this.stockQuantityDao);
            }else{
                StockTransactionHistory stockTransactionHistory = new StockTransactionHistory();
                stockTransactionHistory.setTicker(ticker);
                Holding newHolding = new Holding(stock,stockTransactionHistory);
                newHolding.addTransaction(transaction, username, this.stockQuantityDao);
                holdings.add(newHolding);
            }
            this.cash -= stock.getCurrentPrice()*quantity;
            recalculate();
        }

    }

    /**
     * Deletes a given quantity of a stock given the ticker
     * @param ticker the ticker of the stock
     * @param quantity the quantity of stock
     */
    public void deleteStocks(String ticker, Integer quantity){
        Stock stock = new Stock(ticker);
        Holding holding = this.getHolding(ticker);
        if (holding.getQuantity() < quantity){
            System.out.println("Not enough stocks");
        }else {
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = currentDate.format(formatter);
            Transaction transaction = new Transaction(formattedDate, stock.getCurrentPrice(), quantity * -1);
            holding.addTransaction(transaction, username, this.stockQuantityDao);
            this.cash += stock.getCurrentPrice() * quantity;
            recalculate();
        }
    }

    /**
     * Returns a list of holdings with a quantity greater than zero
     * @return a list of holdings with a quantity greater than zero
     */
    public ArrayList<Holding> getPositiveQuantityHoldings(){
        ArrayList<Holding> holdings = new ArrayList<>();
        for (Holding holding : this.holdings) {
            if (holding.getQuantity() > 0) {
                holdings.add(holding);
            }
        }
        return holdings;
    }

    /**
     * Returns the purchase history of the user, given a stock ticker
     * @param ticker the ticker of stock
     * @return the purchase history
     */
    public ArrayList<Transaction> getUserPurchaseHistory(String ticker) {
        Holding holding = getHolding(ticker);
        return holding.getStockTransactionHistory().getTransactions();
    }
}
