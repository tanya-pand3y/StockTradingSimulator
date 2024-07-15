package entity;

import data_access.TiingoAPIDataAccessObject;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Stock {
    private String name;
    private String ticker;
    private double currentPrice;
    private int volume;
    private ZonedDateTime date;

    public Stock(String ticker, String name) {
        this.ticker = ticker;
        this.name = name;
        TiingoAPIDataAccessObject apiDataAccessObject = new TiingoAPIDataAccessObject(this.ticker);
        this.currentPrice = apiDataAccessObject.getClose();
        this.volume = apiDataAccessObject.getVolume();
        this.date = apiDataAccessObject.getDate();
    }

    private void updateValues() {
        TiingoAPIDataAccessObject apiDataAccessObject = new TiingoAPIDataAccessObject(this.ticker);
        this.currentPrice = apiDataAccessObject.getClose();
        this.volume = apiDataAccessObject.getVolume();
        this.date = apiDataAccessObject.getDate();

    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getTicker() {
        return ticker;
    }
}
