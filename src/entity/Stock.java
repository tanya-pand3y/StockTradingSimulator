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

    public Stock(String ticker, String name) {
        this.ticker = ticker;
        this.name = name;
        StockCurrentAPIDataAccessInterface apiDataAccessObject = new StockCurrentAPIDataAccessObject(this.ticker);
        this.currentPrice = apiDataAccessObject.getClose();
        this.volume = apiDataAccessObject.getVolume();
        this.date = apiDataAccessObject.getDate();

    }

    private void updateValues() {
        StockCurrentAPIDataAccessInterface apiDataAccessObject = new StockCurrentAPIDataAccessObject(this.ticker);
        this.currentPrice = apiDataAccessObject.getClose();
        this.volume = apiDataAccessObject.getVolume();
        this.date = apiDataAccessObject.getDate();
    }

    public String getName() {return this.name;}

    public double getCurrentPrice() {
        return this.currentPrice;
    }

    public String getTicker() {
        return this.ticker;
    }
}
