package entity;

import data_access.StockCurrentAPIDataAccessInterface;
import data_access.StockCurrentAPIDataAccessObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Stock {
    private final String name;
    private final String ticker;
    private double currentPrice;
    private int volume;
    private ZonedDateTime date;

    /**
     * Creates a stock object given a ticker
     * @param ticker the ticker
     */
    public Stock(String ticker) {
        this.ticker = ticker;
        StockCurrentAPIDataAccessInterface apiDataAccessObject = new StockCurrentAPIDataAccessObject(this.ticker);
        this.name = apiDataAccessObject.getName();
        this.currentPrice = apiDataAccessObject.getClose();
        this.volume = apiDataAccessObject.getVolume();
        this.date = apiDataAccessObject.getDate();

    }

    /**
     * Updates the values of the stock
     */
    private void updateValues() {
        StockCurrentAPIDataAccessInterface apiDataAccessObject = new StockCurrentAPIDataAccessObject(this.ticker);
        this.currentPrice = apiDataAccessObject.getClose();
        this.volume = apiDataAccessObject.getVolume();
        this.date = apiDataAccessObject.getDate();
    }

    /**
     * Returns the name of the stock
     * @return the name of the stock
     */
    public String getName() {return this.name;}

    /**
     * Returns the current stock price
     * @return the current stock price
     */
    public double getCurrentPrice() {
        return this.currentPrice;
    }

    /**
     * Returns the ticker
     * @return the ticker
     */
    public String getTicker() {
        return this.ticker;
    }
}
