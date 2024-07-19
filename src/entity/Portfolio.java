package entity;

import com.sun.management.HotSpotDiagnosticMXBean;

import java.util.ArrayList;


public class Portfolio {
    private double accountValue;
    private double cash;
    private double PnL;
    private ArrayList<Holding> holdings;

    /**
     * Initializes a portfolio
     * @param StartingCash the amount of starting cash
     */
    public Portfolio (double StartingCash){
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
        this.updateAccountValue(holding);
    }

    public void removeHolding (Stock stock) {
        for (Holding holding : holdings) {
            if (holding.getStock().equals(stock)) {
                holdings.remove(holding);
                break;
            }
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

    public void updateAccountValue(Holding holding) {
        this.accountValue += holding.getStock().getCurrentPrice() * holding.getQuantity();
    }
    public double getAccountValue() {
        return accountValue;
    }


}
