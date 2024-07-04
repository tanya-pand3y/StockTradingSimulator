package src.entity;

import java.util.ArrayList;

public class Portfolio {
    private double value;
    private double cash;
    private ArrayList<Holding> holdings;

    public Portfolio (double StartingCash){
        value = StartingCash;
        cash = StartingCash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCash() {
        return cash;
    }
}
