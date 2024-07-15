package data_access;

import java.time.ZonedDateTime;

public interface TiingoAPIDataAccessInterface {
    Double getClose();
    ZonedDateTime getDate();
    String getTicker();
    int getVolume();
}
