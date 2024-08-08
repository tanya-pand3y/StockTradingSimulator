package entity;

import data_access.StockQuantityDataAccessInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;


public class Portfolio {
    private final String username;
    private double portfolioValue;
    private double cash;
    private double PnL;
    private final ArrayList<Holding> holdings;
    private final StockQuantityDataAccessInterface stockQuantityDao;

    private static double convertToDouble(Object number) {
        if (number instanceof Double) {
            return (Double) number;
        } else if (number instanceof Long) {
            return ((Long) number).doubleValue();
        } else {
            throw new IllegalArgumentException("The input number is neither a Double nor a Long");
        }
    }

    public Portfolio (String username, StockQuantityDataAccessInterface dao){
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
        this.cash = 5000;
        for (Holding holding : this.holdings) {
            this.portfolioValue += holding.getCurrentValue();
            this.PnL += holding.getPnL();
            this.cash -= holding.getStockTransactionHistory().getTotalTransactionValue();
        }


    }

    /**
     * Recalculates values in the portfolio
     */
    public void recalculate(){
        this.portfolioValue = 0.0;
        this.PnL = 0.0;
        this.cash = 5000;
        for (Holding holding : this.holdings) {
            holding.recalculate();
            this.portfolioValue += holding.getCurrentValue();
            this.PnL += holding.getPnL();
            this.cash -= holding.getStockTransactionHistory().getTotalTransactionValue();
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
    public String buyStock(String ticker, int quantity){
        Stock stock = new Stock(ticker);
        if (stock.getCurrentPrice()*quantity > this.cash){
            System.out.println("Not enough money");
            return "Insufficient funds!";
        }
        else {
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = currentDate.format(formatter);
            Transaction transaction = new Transaction(formattedDate, stock.getCurrentPrice(), quantity);
            if (this.getHolding(ticker) != null){
                Holding holding = this.getHolding(ticker);
                holding.addTransaction(transaction, username, this.stockQuantityDao);
            } else {
                StockTransactionHistory stockTransactionHistory = new StockTransactionHistory();
                stockTransactionHistory.setTicker(ticker);
                Holding newHolding = new Holding(stock,stockTransactionHistory);
                newHolding.addTransaction(transaction, username, this.stockQuantityDao);
                holdings.add(newHolding);
            }
            this.cash -= stock.getCurrentPrice()*quantity;
            recalculate();
        }
        return "";
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
        ArrayList<Transaction> transactions = holding.getStockTransactionHistory().getTransactions();
        Collections.sort(transactions, new Comparator<Transaction>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            @Override
            public int compare(Transaction o1, Transaction o2) {
                try {
                    return dateFormat.parse(o1.getDate()).compareTo(dateFormat.parse(o2.getDate()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        return holding.getStockTransactionHistory().getTransactions();
    }
}
