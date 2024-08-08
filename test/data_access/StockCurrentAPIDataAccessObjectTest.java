package data_access;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class StockCurrentAPIDataAccessObjectTest {

    private StockCurrentAPIDataAccessObject dataAccessObject;

    @BeforeEach
    public void setUp() {
        dataAccessObject = new StockCurrentAPIDataAccessObject("AAPL") {
            @Override
            public JSONArray getApiArray() {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("close", 150.0);
                jsonObject.put("date", "2023-01-01T00:00:00Z");
                jsonObject.put("volume", 1000);
                jsonArray.put(jsonObject);
                return jsonArray;
            }
        };
    }


    @Test
    public void testGetClose() {
        Double expectedClose = 150.0;
        Double actualClose = dataAccessObject.getClose();
        assertEquals(expectedClose, actualClose, "Close price should be 150.0");
    }

    @Test
    public void testGetDate() {
        ZonedDateTime expectedDate = ZonedDateTime.parse("2023-01-01T00:00:00Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZonedDateTime actualDate = dataAccessObject.getDate();
        assertEquals(expectedDate, actualDate, "Date should be 2023-01-01T00:00:00Z");
    }

    @Test
    public void testGetVolume() {
        int expectedVolume = 1000;
        int actualVolume = dataAccessObject.getVolume();
        assertEquals(expectedVolume, actualVolume, "Volume should be 1000");
    }

}