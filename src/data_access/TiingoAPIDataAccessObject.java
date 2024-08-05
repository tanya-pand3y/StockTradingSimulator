package data_access;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

abstract class TiingoAPIDataAccessObject implements TiingoAPIDataAccessInterface{
    private final JSONArray apiArray;
    private final String url;
    private final String ticker;
    private final String name;

    public TiingoAPIDataAccessObject(String ticker) {
        this.ticker = ticker;
        this.name = this.getNameOfStock();
        this.url = "https://api.tiingo.com/tiingo/daily/" + ticker + "/prices";
        this.apiArray = this.getAPIData(this.ticker);
    }

    public TiingoAPIDataAccessObject(String ticker, String startDate, String endDate) {
        this.ticker = ticker;
        this.name = this.getNameOfStock();
        this.url = "https://api.tiingo.com/tiingo/daily/" + ticker + "/prices?startDate=" + startDate + "&endDate=" + endDate;
        this.apiArray = this.getAPIData(this.ticker);
    }

    private String getNameOfStock(){
        String name = "";
        String urlString = "https://api.tiingo.com/tiingo/daily/" + this.ticker;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            String API_KEY = "e721e5590df56f3f1cda874c922ee7a0bb1a5079";
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
                JSONObject jsonObject = new JSONObject(rawResponse);
                name = jsonObject.getString("name");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return name;

    }

    private JSONArray getAPIData(String ticker) {
        JSONArray jsonArray = null;
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Add request headers
            connection.setRequestProperty("Accept", "application/json");
            String API_KEY = "e721e5590df56f3f1cda874c922ee7a0bb1a5079";
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
                jsonArray = new JSONArray(rawResponse);

            } else {
                System.out.println("GET request not worked. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public JSONArray getApiArray() {
        return apiArray;
    }

    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return this.name;
    }


}
