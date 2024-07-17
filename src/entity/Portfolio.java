package entity;

import java.util.ArrayList;


public class Portfolio {
    private double AccountValue;
    private double cash;
    private double PnL;
    private ArrayList<Holding> holdings;

    /**
     * Initializes a portfolio
     * @param StartingCash the amount of starting cash
     */
    public Portfolio (double StartingCash){
        AccountValue = StartingCash;
        cash = StartingCash;
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
    }

}
