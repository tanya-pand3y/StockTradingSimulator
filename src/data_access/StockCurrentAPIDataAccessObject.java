package data_access;

import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StockCurrentAPIDataAccessObject extends TiingoAPIDataAccessObject implements StockCurrentAPIDataAccessInterface {
    private final Double close;
    private final ZonedDateTime date;
    private final int volume;

    /**
     * Creates a data access object for a given stock ticker
     * @param ticker the stock ticker
     */
    public StockCurrentAPIDataAccessObject(String ticker) {
        super(ticker);
        JSONObject jsonObject = this.getApiArray().getJSONObject(0);
        this.close = jsonObject.getDouble("close");
        this.date = ZonedDateTime.parse(jsonObject.getString("date"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.volume = jsonObject.getInt("volume");
    }

    /**
     * Returns the close price
     * @return the close price
     */
    @Override
    public Double getClose() {
        return close;
    }

    /**
     * Returns the trading volume
     * @return the trading volume
     */
    @Override
    public int getVolume() {
        return volume;
    }

    /**
     * Returns the date of the data
     * @return the date of the data
     */
    @Override
    public ZonedDateTime getDate() {
        return date;
    }
}
