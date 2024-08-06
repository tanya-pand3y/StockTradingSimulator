package data_access;

import org.json.JSONArray;

public interface TiingoAPIDataAccessInterface {
    /**
     * Returns the API array in JSON
     * @return the API array in JSON
     */
    JSONArray getApiArray();

    /**
     * Returns the ticker as a string
     * @return the ticker as a string
     */
    String getTicker();
}
