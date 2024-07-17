package data_access;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.json.JSONObject;

public class StockHistoryAPIDataAccessObject extends TiingoAPIDataAccessObject implements StockHistoryDataInterface {
    private final ArrayList<Double> closeArray;
    private final ArrayList<ZonedDateTime> dateArray;
    private final ArrayList<Integer> volumeArray;

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

    @Override
    public ArrayList<Double> getClose() {
        return this.closeArray;
    }

    @Override
    public ArrayList<ZonedDateTime> getDate() {
        return this.dateArray;
    }

    @Override
    public ArrayList<Integer> getVolume() {
        return this.volumeArray;
    }

    public static void main(String[] args) {
        StockCurrentAPIDataAccessObject accessObject = new StockCurrentAPIDataAccessObject("MSFT");
    }
}
