package data_access;

import java.time.ZonedDateTime;

public interface StockCurrentAPIDataAccessInterface {
    Double getClose();
    ZonedDateTime getDate();
    int getVolume();
}
