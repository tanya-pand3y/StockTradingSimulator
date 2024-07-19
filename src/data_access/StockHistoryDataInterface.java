package data_access;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public interface StockHistoryDataInterface {
    ArrayList<Double> getClose();
    ArrayList<ZonedDateTime> getDate();
    ArrayList<Integer> getVolume();
    String getName();
}
