package data_access;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public interface StockHistoryDataInterface {
    /**
     * Returns the close price of the stock
     * @return the close price of the stock
     */
    ArrayList<Double> getClose();

    /**
     * Returns the date of the data
     * @return the date of the data
     */
    ArrayList<ZonedDateTime> getDate();

    /**
     * Returns an ArrayList containing trading volumes on each day
     * @return an ArrayList containing trading volumes on each day
     */
    ArrayList<Integer> getVolume();

    /**
     * Returns the name of the stock
     * @return the name of the stock
     */
    String getName();
}
