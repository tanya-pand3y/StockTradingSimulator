package data_access;

import java.time.ZonedDateTime;

public interface StockCurrentAPIDataAccessInterface {
    /**
     * Returns the close price
     * @return the close price
     */
    Double getClose();

    /**
     * Returns the date of the data
     * @return the date of the data
     */
    ZonedDateTime getDate();

    /**
     * Returns the trading volume
     * @return the trading volume
     */
    int getVolume();

    /**
     * Returns the company name
     * @return the company name
     */
    String getName();
}
