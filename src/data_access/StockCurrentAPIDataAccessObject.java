package data_access;

import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StockCurrentAPIDataAccessObject extends TiingoAPIDataAccessObject implements StockCurrentAPIDataAccessInterface {
    private final Double close;
    private final ZonedDateTime date;
    private final int volume;

    public StockCurrentAPIDataAccessObject(String ticker) {
        super(ticker);
        JSONObject jsonObject = this.getApiArray().getJSONObject(0);
        this.close = jsonObject.getDouble("close");
        this.date = ZonedDateTime.parse(jsonObject.getString("date"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.volume = jsonObject.getInt("volume");
    }

    @Override
    public Double getClose() {
        return close;
    }

    @Override
    public int getVolume() {
        return volume;
    }
    @Override
    public ZonedDateTime getDate() {
        return date;
    }




    public static void main(String[] args) {
        StockCurrentAPIDataAccessObject accessObject = new StockCurrentAPIDataAccessObject("MSFT");
        System.out.println(accessObject.getClose());
        System.out.println(accessObject.getVolume());
        System.out.println(accessObject.getDate());
    }
}
