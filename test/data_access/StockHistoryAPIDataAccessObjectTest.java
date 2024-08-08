package data_access;

import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockHistoryAPIDataAccessObjectTest {

    private StockHistoryAPIDataAccessObject dataAccessObject;

    @BeforeEach
    public void setUp() {
        dataAccessObject = new StockHistoryAPIDataAccessObject("AAPL", "2023-01-01", "2023-01-05") {
            @Override
            public JSONArray getApiArray() {
                JSONArray jsonArray = new JSONArray();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

                for (int i = 1; i <= 5; i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("close", 150.0 + i);
                    jsonObject.put("date", ZonedDateTime.parse("2023-01-0" + i + "T00:00:00Z", formatter).toString());
                    jsonObject.put("volume", 1000 + i);
                    jsonArray.put(jsonObject);
                }

                return jsonArray;
            }
        };
    }

    @Test
    public void testGetClose() {
        ArrayList<Double> expectedClose = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            expectedClose.add(150.0 + i);
        }

        ArrayList<Double> actualClose = dataAccessObject.getClose();
        assertEquals(expectedClose, actualClose, "Close prices should match the expected values.");
    }

    @Test
    public void testGetDate() {
        ArrayList<ZonedDateTime> expectedDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        for (int i = 1; i <= 5; i++) {
            expectedDates.add(ZonedDateTime.parse("2023-01-0" + i + "T00:00:00Z", formatter));
        }

        ArrayList<ZonedDateTime> actualDates = dataAccessObject.getDate();
        assertEquals(expectedDates, actualDates, "Dates should match the expected values.");
    }

    @Test
    public void testGetVolume() {
        ArrayList<Integer> expectedVolumes = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            expectedVolumes.add(1000 + i);
        }

        ArrayList<Integer> actualVolumes = dataAccessObject.getVolume();
        assertEquals(expectedVolumes, actualVolumes, "Volumes should match the expected values.");
    }
}