package data_access;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.json.JSONObject;

public class StockHistoryAPIDataAccessObject extends TiingoAPIDataAccessObject implements StockHistoryDataInterface {
    private final ArrayList<Double> closeArray;
    private final ArrayList<ZonedDateTime> dateArray;
    private final ArrayList<Integer> volumeArray;

    /**
     * Creates a Stock History DAO given a ticker and dates
     * @param ticker the ticker
     * @param startDate the start date
     * @param endDate the end date
     */
    public StockHistoryAPIDataAccessObject(String ticker, String startDate, String endDate) {
        super(ticker, startDate, endDate);
        this.closeArray = new ArrayList<>();
        this.dateArray = new ArrayList<>();
        this.volumeArray = new ArrayList<>();
        for (int i = 0; i < this.getApiArray().length(); i++) {
            JSONObject jsonObject = this.getApiArray().getJSONObject(i);
            Double close = jsonObject.getDouble("close");
            Integer Volume = jsonObject.getInt("volume");
            ZonedDateTime date = ZonedDateTime.parse(jsonObject.getString("date"));
            this.closeArray.add(close);
            this.volumeArray.add(Volume);
            this.dateArray.add(date);
        }

    }

    /**
     * Returns the close price of the stock
     * @return the close price of the stock
     */
    @Override
    public ArrayList<Double> getClose() {
        return this.closeArray;
    }

    /**
     * Returns the date of the data
     * @return the date of the data
     */
    @Override
    public ArrayList<ZonedDateTime> getDate() {
        return this.dateArray;
    }

    /**
     * Returns an ArrayList containing trading volumes on each day
     * @return an ArrayList containing trading volumes on each day
     */
    @Override
    public ArrayList<Integer> getVolume() {
        return this.volumeArray;
    }
}
