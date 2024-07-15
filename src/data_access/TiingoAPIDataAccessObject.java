package data_access;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class TiingoAPIDataAccessObject implements TiingoAPIDataAccessInterface{
    private final String ticker;
    private final Double close;
    private final ZonedDateTime date;
    private final int volume;

    public TiingoAPIDataAccessObject(String ticker) {
        this.ticker = ticker;
        JSONObject jsonObject = this.getAPIData(this.ticker);
        this.close = this.parseClosePrice(jsonObject);
        this.date = this.parseDate(jsonObject);
        this.volume = this.parseVolume(jsonObject);

    }

    private JSONObject getAPIData(String ticker) {
        JSONObject jsonObject = null;
        try {
            String urlString = "https://api.tiingo.com/tiingo/daily/" + ticker + "/prices";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Add request headers
            connection.setRequestProperty("Accept", "application/json");
            String API_KEY = "bf408e3ee6e0a153be501f96b468efecc351c902";
            connection.setRequestProperty("Authorization", "Token " + API_KEY);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                String rawResponse = response.toString();
                System.out.println(rawResponse);
                JSONArray jsonArray = new JSONArray(rawResponse);
                jsonObject = jsonArray.getJSONObject(0);

            } else {
                System.out.println("GET request not worked. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private Double parseClosePrice(JSONObject jsonObject) {
        return jsonObject.getDouble("close");
    }

    private ZonedDateTime parseDate(JSONObject jsonObject) {
        return ZonedDateTime.parse(jsonObject.getString("date"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    }

    private int parseVolume(JSONObject jsonObject) {
        return jsonObject.getInt("volume");
    }

    public Double getClose() {
        return this.close;
    }

    public ZonedDateTime getDate() {
        return this.date;
    }

    public String getTicker() {
        return this.ticker;
    }

    public int getVolume() {
        return this.volume;
    }

    public static void main(String[] args) {
        TiingoAPIDataAccessObject dao = new TiingoAPIDataAccessObject("MSFT");
        System.out.println(dao.getClose());
        System.out.println(dao.getDate());
        System.out.println(dao.getTicker());
        System.out.println(dao.getVolume());
    }


}
